package com.eagle.cloud.route.vo;

import java.util.Date;

/**
 * 	记录表
 * @author xiongxue
 * @Description
 *2019年10月11日
 */
public class StepsRecord {
	private Integer recordId;//记录编号 自动生成
	private Integer pathId;// '区间路径编号',
	private Integer sectionId;//'分段编号',
	private String status;//'此段路的交通情况 未知、畅通、缓行、拥堵、严重拥堵',
	private String polyline;//'此路段坐标点,格式为坐标串，
	private Long sort;//'顺序号',//一次操作记录一次
	private Date createTime;// '创建时间',
	private String createTimeStr;
	public Integer getRecordId() {
		return recordId;
	}
	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
	public Integer getPathId() {
		return pathId;
	}
	public void setPathId(Integer pathId) {
		this.pathId = pathId;
	}
	public Integer getSectionId() {
		return sectionId;
	}
	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPolyline() {
		return polyline;
	}
	public void setPolyline(String polyline) {
		this.polyline = polyline;
	}
	public Long getSort() {
		return sort;
	}
	public void setSort(Long sort) {
		this.sort = sort;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
	
	
}
