package com.eagle.cloud.route.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.eagle.cloud.route.vo.StepsRecordNew;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eagle.cloud.route.dao.SysScreenDao;
import com.eagle.cloud.route.dto.requestDto;
import com.eagle.cloud.route.service.ScreenService;
import com.eagle.cloud.route.utils.Config_Road;
import com.eagle.cloud.route.utils.idgenerator.IdGenerator;
import com.eagle.cloud.route.vo.Section;
import com.eagle.cloud.route.vo.StepsRecord;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import static com.eagle.cloud.route.utils.StringUtil.myreplace;
import static com.eagle.cloud.route.utils.StringUtil.stringResult;

@Slf4j
@Service
public class ScreenServiceImpl implements ScreenService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SysScreenDao screenDao;


	/** 	方法：从高德获取全部路段区间的导航请求数据，并更新到存到数据库表
	 * 获取高德地图的功能url和key，用工具类从配置文件中取
	 * 获取目标路段区间的基础数据，封装成表对象，存到列表集合。从数据库获取，调用dao方法
	 * 给本次操作任务一个编号-—sort，用工具类
	 * 遍历全部路段区间，从高德获取导航数据：
	 * 		把每个区间等需要发送请求参数装为HashMap：paramMap
	 * 		调用getHttpResult方法（需自定义），从高德获取导航数据含有路况
	 * 		将导航数据存到数据库，用getResolverResult（自定义的dao）,分布存到了两个表
	 *
	 * 		getHttpResult方法（自定义）从高德获取导航数据：
		 * 	 *  用HttpClient工具类，创建HttpClient对象、和HttpGet对象。HttpClient对象发送请求需要以ttpGet对象作为参数。
		 * 	 *  HttpGet对象需要设置请求参数，因此：
			 * 		先用 setHttpParams(自定义) 将将Map转为List<NameValuePair>
			 * 		再用 URLEncodedUtils工具将参数的NameValuePair键值对对象的List转为String
	 	 * 	 * 	即可用httpGet.setURI 设置请求参数
		 * 	 * 	调用httpClient.execute(httpGet) 即可发送请求，返回HttpResponse响应对象
		 * 	 * 	最后：调用getHttpEntityContent（自定义）将返回的响应对象 转为能够看的字符串：
			 * 	 	 * 1.通过HttpResponse 的getEntity()方法获取返回信息：HttpEntity类对象
				 * 	 * 2.获得输入流。entity.getContent() 返回输入流
				 * 	 * 3.从输入流读取数据，存于BufferedReader br
				 * 	 * 4.将br 逐行存于StringBuilder sb
				 * 	 * 5.sb.toString() 即返回字符串。（响应数据的字符串）
	 */
	@Override
	public void getGDReadInfo() {
		// 获取url以及key
		String gdUrl = Config_Road.getString("GdUrl");
		String key = Config_Road.getString("key");
		List<Section> tblList = screenDao.getTbl_sectionInfo();
		// 一次定时任务
		Long sort = IdGenerator.nextId();
		// 循环调用url
		for (int i = 0; i < tblList.size(); i++) {
			// https://restapi.amap.com/v3/direction/driving?key=f4bf613421948850c378867ff3760e81
			// &origin=117.20992,36.676523&destination=117.214948,36.85863&extensions=all&strategy=0
			// "&destination="+tblList.get(i).getEndLng()+","+tblList.get(i).getEndLat()+"&extensions=all&strategy=0";
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("key", key);
			paramMap.put("origin", tblList.get(i).getStartLng() + "," + tblList.get(i).getStartLat());
			paramMap.put("destination", tblList.get(i).getEndLng() + "," + tblList.get(i).getEndLat());
			paramMap.put("extensions", "all");
			paramMap.put("strategy", "0");
			String responseData;
			try {
				//getHttpResult(自定义)请求高德，并将高德响应做了处理成易阅读的字符串
				responseData = getHttpResult(gdUrl, paramMap);
				if (responseData != null) {
					// getResolverResult（自定义）解析字符串数据,并入库更新
					getResolverResult(responseData, tblList.get(i).getPathId(), tblList.get(i).getSectionId(), sort);

					//测试用，查看responseData，经过了请求高德，并将高德响应做了处理成易阅读的字符串
					log.info("responseData = " +responseData);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	// 调取高德后台，并调用getHttpEntityContent（自定义）将高德响应做了处理成易阅读的字符串
	/**
	 * 用HttpClient工具类，创建HttpClient对象、和HttpGet对象。HttpClient对象发送请求需要以HttpGet对象作为参数。
	 * HttpGet对象需要设置请求参数，因此：
	 * 		先用 setHttpParams(自定义) 将将Map转为List<NameValuePair>
	 * 		再用 URLEncodedUtils工具将参数的NameValuePair键值对对象的List转为String
	 * 	即可用httpGet.setURI 设置请求参数
	 * 	调用httpClient.execute(httpGet) 即可发送请求，返回HttpResponse响应对象
	 * 	最后：
	 * 	调用getHttpEntityContent（自定义）将返回的响应对象 转为能够看的字符串
	 */
	public  String getHttpResult(String url, Map<String, String> paramMap)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet();
		List<NameValuePair> formparams = setHttpParams(paramMap);
		String param = URLEncodedUtils.format(formparams, "UTF-8");
		httpGet.setURI(URI.create(url + "?" + param));
//		System.out.println(httpGet.getURI());
		HttpResponse response = httpClient.execute(httpGet);
		String httpEntityContent = getHttpEntityContent(response);
		httpGet.abort();
	    if (response.getStatusLine().getStatusCode()!=200) {
	       logger.info("访问接口失败，状态码="+response.getStatusLine().getStatusCode());
        }else{
           logger.info("访问接口成功，状态码="+response.getStatusLine().getStatusCode());
        }
		return httpEntityContent;
	}

	//相当于将Map转为List。Map的每一个键值对封装为NameValuePair对象，装在List中，NameValuePair对象只能get，不能set
	//这里用到了Map.Entry类：表示Map集合的映射关系，可用entry.getKey(), entry.getValue()返回键、值。
	//用到BasicNameValuePair类。
	private static List<NameValuePair> setHttpParams(Map<String, String> paramMap) {
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		Set<Map.Entry<String, String>> set = paramMap.entrySet();
		for (Map.Entry<String, String> entry : set) {
			formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		return formparams;
	}

	/** getHttpEntityContent 方法（自定义）将返回的响应数据对象（HttpResponse） 转为能看的字符串
	 * 1.通过HttpResponse 的getEntity()方法获取返回信息：HttpEntity类对象
	 * 2.获得输入流。entity.getContent() 返回输入流
	 * 3.从输入流读取数据，存于BufferedReader br
	 * 4.将br 逐行存于StringBuilder sb
	 * 5.sb.toString() 即返回字符串。（响应数据的字符串）
	 */
	private static String getHttpEntityContent(HttpResponse response) throws IOException, UnsupportedEncodingException {
		// 通过HttpResponse 的getEntity()方法获取返回信息
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			InputStream is = entity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String line = br.readLine();
			StringBuilder sb = new StringBuilder();
			while (line != null) {
				sb.append(line + "\n");
				line = br.readLine();
			}
			br.close();
			is.close();
			return sb.toString();
		}
		return "";
	}

	// 解析字符串，并用dao，入库更新
	/*
	1.将字符串转为Json对象，json对象可以用操作对象的方法来操作：get（"key"）
	2.可强转类型，根据字符串情况，强转为对于类型：JSONObject、JSONArray
	3.一点一点把需要的数据取出，另设变量存放
	4.实例化一个数据实体类对象，将数据存入对象
	5.用dao，将实体类对象存入数据库
	6.分布存到了两个表
	 */
	public void getResolverResult(String resultDate, Integer pathid, Integer sectionid, Long sort) {
		JSONObject jsonObject = JSONObject.fromObject(resultDate);
		String status = (String) jsonObject.get("status");
		if (status.equals("1")) {
			logger.info("steps入库成功：status=" + status);
			JSONObject route = (JSONObject) jsonObject.get("route");
			JSONArray paths = (JSONArray) route.get("paths");
			JSONObject pathsjsob = (JSONObject) paths.get(0);
			Section section = new Section();
			// 距离
			section.setDistance(
					pathsjsob.get("distance").equals("null") ? 0 : Long.parseLong((String) pathsjsob.get("distance")));
			// 时间
			section.setDuration(
					pathsjsob.get("duration").equals("null") ? 0 : Long.parseLong(pathsjsob.getString("duration")));
			//更新速度
            section.setSpeed((Double.parseDouble(pathsjsob.getString("distance"))/Double.parseDouble(pathsjsob.getString("duration"))));
			// 序号，更新时间
			section.setSort(sort);
			section.setUpdateTime(new Date());
			section.setPathId(pathid);
			section.setSectionId(sectionid);
			// 更新`tbl_section` 表中
			int valueSection = screenDao.updateSectionBysectionId(section);
			if (valueSection > 0) {
				logger.info("section更新成功");
			} else {
				logger.info("section更新失败");
			}
			JSONArray steps = new JSONArray();
			steps = (JSONArray) pathsjsob.get("steps");
			JSONArray tmcs = new JSONArray();
			JSONObject stepsJsob = (JSONObject) steps.get(0);
			tmcs = (JSONArray) stepsJsob.get("tmcs");
			logger.info("tmcs.size()==" + tmcs.size());
			for (int i = 0; i < tmcs.size(); i++) {
				StepsRecord record = new StepsRecord();
				JSONObject tmcsjsob = (JSONObject) tmcs.get(i);
				record.setStatus(tmcsjsob.getString("status"));
				record.setPathId(pathid);
				record.setPolyline(tmcsjsob.getString("polyline"));
				record.setSectionId(sectionid);
				record.setSort(sort);
				record.setCreateTime(new Date());
				// 插入数据
				int valueRecord = screenDao.addRecordInfo(record);
				if (valueRecord > 0) {
					logger.info("记录表插入成功");
				} else {
					logger.info("记录表插入失败");
				}
			}
		} else {
			logger.info("返回失败status" + status);
		}
	}

	/**
	 * 更新tbl_path数据
	 * tbl_path表 根据tbl_section表进行更新
	 */
	@Override
	public void getSectionCountInfo() {
		List<requestDto> resultList = screenDao.getScrtionCountBypathId();
		for (int i = 0; i < resultList.size(); i++) {
			resultList.get(i).setUpdateTime(new Date());
			int value = screenDao.updatePathDurationAndDistance(resultList.get(i));
			if (value > 0) {
				logger.info("Path表更新成功");
			} else {
				logger.info("Path表更新失败");
			}
		}
	}


	//从tbl_steps_record表中查最近一次的传入数据
	@Override
	public List<StepsRecord> getRoadAll() {
		BigDecimal bigDecimal;
		bigDecimal = screenDao.getSortMax();
		if(screenDao.getSortMaxCount(bigDecimal)<684){
			bigDecimal = screenDao.getSortSecondMax();
		}
		return screenDao.getRoadAll(bigDecimal);
	}


	//没有用到
	@Override
	public List<StepsRecordNew> getRoadAllNew(List<StepsRecord> list) {
		List<StepsRecordNew> list_new = new ArrayList<>(list.size());
		StepsRecordNew  stepsRecordNew = new StepsRecordNew();
		for(int i =0;i<list.size();i++){

			//list_new.add(i,);
			//  list_new.add(i,stepsRecordNew);
			stepsRecordNew.setStatus(list.get(i).getStatus());
//            stepsRecordNew.setCreateTime(list.get(i).getCreateTime());
//            stepsRecordNew.setPathId(list.get(i).getPathId());
//            stepsRecordNew.setRecordId(list.get(i).getRecordId());
//            stepsRecordNew.setSort(list.get(i).getSort());
			String s = "[["+myreplace((list.get(i).getPolyline()),";","],[")+"]]";
			Double[][] d_polyling = stringResult(s);
			stepsRecordNew.setPolyline(d_polyling);
			list_new.add(i,stepsRecordNew);
		}
		return list_new;
	}

}
