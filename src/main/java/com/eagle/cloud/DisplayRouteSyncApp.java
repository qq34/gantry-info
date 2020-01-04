package com.eagle.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;

/**
 * 用于与高德路况同步中心启动类
 * @class: DisplayRouteSyncApp
 * @package: com.eagle.cloud
 * @description: 用于与高德路况同步中心启动类
 * @author wzhz
 * @date 2019年6月29日 下午9:10:01
 * @version v1.0.0
 */
//@Configuration
//@EnableLogging
//@EnableDiscoveryClient
@SpringBootApplication
//@EnableScheduling
public class DisplayRouteSyncApp extends SpringBootServletInitializer {

	public static void main(String[] args) {
		// 固定端口启动
		SpringApplication.run(DisplayRouteSyncApp.class, args);
		// 随机端口启动
		// SpringApplication app = new SpringApplication(UserCenterApp.class);
		// app.addListeners(new PortApplicationEnvironmentPreparedEventListener());
		// app.run(args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(DisplayRouteSyncApp.class);
	}
}
