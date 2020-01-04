package com.eagle.cloud.route.controller;
/**
 * @title: RoadController
 * @projectName display-route-sync
 * @description: TODO
 * @date 2019-11-8 15:24
 */

//import com.eagle.cloud.route.service.RoadService;

import com.eagle.cloud.route.dao.SysScreenDao;
import com.eagle.cloud.route.service.ScreenService;
import com.eagle.cloud.route.vo.StepsRecord;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;

//import com.eagle.cloud.route.vo.StepsRecordNew;

/**
 *@Description: TODO
 *@Author: yunbao
 *@Date: 2019-11-8 15:24
 *@Verion: 1.0
 **/
@RestController
@RequestMapping(value = "/roadInfo")
public class RoadController {


    @Autowired
    private ScreenService screenService;

    @RequestMapping(value = "/getRoadAllTraffic",method = RequestMethod.GET)
    public Map<String,Object> getRoadAll() throws SQLException {
        Map<String,Object> modelMap  = new HashMap<String,Object>();
        List<StepsRecord> list = screenService.getRoadAll();
//        List<StepsRecordNew> listNew = screenService.getRoadAllNew(list);
//        modelMap.put("RoadInfo",listNew);
        modelMap.put("roadInfo",list);
        return modelMap;
    }
@Autowired
private SysScreenDao sysScreenDao;

    //路网交通密度画线
        @RequestMapping(value = "/getDensity",method = RequestMethod.GET)
    public Map<String,Object> getDensity() {

        List<Map> list = sysScreenDao.getDensity();
        System.out.println(list);

        for (Map aMap :list){
            System.out.println(aMap);
            if (aMap!=null) {
                String color = "#222269";
                int density = (int) aMap.get("density");
                if (density > 30) {
                    color = "#682222";
                } else if (density > 15) {
                    color = "#226922";
                }
                aMap.put("color", color);
                aMap.replace("polyline", aMap.get("polyline"));
                System.out.println(aMap);
            }
        }

        Map<String,Object> resulrMap  = new HashMap<String,Object>();
        resulrMap.put("roadInfo",list);

        return  resulrMap;
    }



//    //测试
//    @RequestMapping(value = "/ODExample",method = RequestMethod.GET)
//    public Map ODExample() throws SQLException {
//        Map<String, Map<String,Integer>> datamap1= new HashMap<String, Map<String,Integer>>();
//
//        Random r = new Random(1);
//        int ran = r.nextInt(1000);
//
//        for(int i = 0;i<26;i++){
//
//            Map<String,Integer> datamap2= new HashMap<String, Integer>();
//
//            for(int j =0 ; j<26;j++){
//                datamap2.put("end"+String.valueOf(j),r.nextInt(1000));
//            }
//            datamap1.put("star"+String.valueOf(i),datamap2);
//        }
//        return datamap1;
//    }

//        //测试
//    @RequestMapping(value = "/gantryInform",method = RequestMethod.GET)
//    public Map ODExample() throws SQLException {
//        Date date = new Date();
//        long time = date.getTime();
//        String time1 = String.valueOf(time);
//        String time2 = time1.substring(time1.length()-5,time1.length());
//        int i = Integer.parseInt(time2);
//
//        Map<String, Integer> datamap= new HashMap<String, Integer>();
//        datamap.put("todayMoney", i*85);
//        datamap.put("todayCarNum",i*85/15);
//
//
////        System.out.println(date.getTime());
//
//        return datamap;
//    }


    @RequestMapping(value = "/getGantryInfo", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
    @ResponseBody
    public Map getGantryInform( String gantryID) {

        System.out.println("gantryID: "+gantryID);

        Map result = sysScreenDao.getGantryInformFromTable(gantryID);

        System.out.println(result);
        return result;
    }




}
