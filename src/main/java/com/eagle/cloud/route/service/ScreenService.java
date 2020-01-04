package com.eagle.cloud.route.service;

import com.eagle.cloud.route.vo.StepsRecord;
import com.eagle.cloud.route.vo.StepsRecordNew;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ScreenService {

	
	void getGDReadInfo();
	
	void getSectionCountInfo();
	/**
	 * 得到所有的一次查询的 所有的路况信息
	 *
	 * @return
	 */
	List<StepsRecord> getRoadAll() ;

	/**
	 * 想要的格式
	 * @param list
	 * @return
	 */
	List<StepsRecordNew> getRoadAllNew(List<StepsRecord> list);

}
