package com.eagle.cloud.route.vo;

import java.util.Date;

import com.eagle.cloud.route.utils.DateUtil;

import lombok.Data;

/**
 * 分段路径记录表
 * @author xiongxue
 * @Description
 *2019年10月11日
 */
@Data
public class Section {
	private Integer sectionId;//分段编号
	private Integer pathId;//区间路径编号
	private String cameraInformation;//摄像枪编号（描述）
	private Double startLng;//起始点纬度
	private Double startLat;//起始点经度
	private Double endLng;//终止点纬度
	private Double endLat;//终止点经度
	private Long duration;//步行时间预计,单位：秒
	private Long distance;//起点和终点的步行距离,单位：米
	private Double speed;//速度
	private Long sort;//顺序号
	private Date updateTime;//更新时间
	private String updateTimeStr;
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		this.updateTimeStr = DateUtil.formatDateTime(updateTime);
	}
	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
		this.updateTime = DateUtil.convert2Date(updateTimeStr);
	}
	
	
	
	
}
