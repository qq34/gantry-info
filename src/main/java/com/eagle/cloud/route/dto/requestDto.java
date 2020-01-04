package com.eagle.cloud.route.dto;

import java.util.Date;

import lombok.Data;

@Data
public class requestDto {
	private Integer pathId;
	private Long durationSum;
	private Long distanceSum;
	private Date updateTime;
	
}
