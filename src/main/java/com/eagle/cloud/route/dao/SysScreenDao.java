package com.eagle.cloud.route.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.eagle.cloud.route.dto.requestDto;
import com.eagle.cloud.route.model.SysScreen;
import com.eagle.cloud.route.vo.Section;
import com.eagle.cloud.route.vo.StepsRecord;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SysScreenDao {

	List<SysScreen> getScreenList(Map<String, Object> params);

	void deleteScreen(Long id);

	void addScreen(SysScreen sysScreen);
	
	List<Section> getTbl_sectionInfo();
	
	/**
	 * 更新tbl_section
	 * @param tbl_section
	 * @return
	 */
	int updateSectionBysectionId(Section section);
	
	/**
	 * 插入记录表
	 * @param tbl_steps_record
	 * @return
	 */
	int addRecordInfo(StepsRecord stepsrecord);
	
	/**
	 * 获取时间和距离总数
	 * @return
	 */
	List<requestDto> getScrtionCountBypathId();
	
	/**
	 * 更新tbl_path
	 * @param dto
	 * @return
	 */
	int updatePathDurationAndDistance(requestDto dto);
	/**
	 * 得到最大的 sort 值
	 * @return
	 */
	BigDecimal getSortMax();

	/**
	 * 通过最大的 sort 值  查询所有路况信息
	 * @param bigDecimal
	 * @return
	 */
	List<StepsRecord> getRoadAll(BigDecimal bigDecimal);

	/**
	 * 统计一次路段的数量
	 * @param bigDecimal
	 * @return
	 */
	int getSortMaxCount(BigDecimal bigDecimal);

	/*
	 * 得到第二大的 sort 值
	 */
	BigDecimal getSortSecondMax();


	/**
	 * 下面是getGantryInformation的DAO
	 */
	//根据门架ID查表，得到信息
	Map getGantryInformFromTable(String gantryID);

	//更新门架信息表的数据
//	int update_gantry_info(String gantryID,);

	//获取路网密度信息
	List<Map> getDensity();

	//根据门架ID，查找gantry_info 这个字段，全部行
	List<Map> getDataFormGantry_info();
	//根据门架ID,将数据存进去 gantry_info 表
	int updateWithGantryID(String gantryID,String colName,int value);

}
