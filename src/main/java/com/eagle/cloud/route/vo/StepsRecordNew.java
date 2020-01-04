package com.eagle.cloud.route.vo;

import lombok.Data;

import java.util.Date;

/**
 * 	记录表
 * @author xiongxue
 * @Description
 *2019年10月11日
 */
@Data
public class StepsRecordNew {
	private Integer recordId;//记录编号 自动生成
	private Integer pathId;// '区间路径编号',
	private Integer sectionId;//'分段编号',
	private String status;//'此段路的交通情况 未知、畅通、缓行、拥堵、严重拥堵',
	private Double[][] polyline;//二维数组
	private Long sort;//'顺序号',//一次操作记录一次
	private Date createTime;// '创建时间',
	private String createTimeStr;

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
	
	
}
