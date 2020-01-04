package com.eagle.cloud.route.listener;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.eagle.cloud.route.utils.DateUtil;
/**
 * 项目运行时自动加载，适用于加载一些参数或者发送鉴权请求等。
 * @author LDQ
 *
 */
@Component
public class FirstApplicationRunner implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		
	}


	//private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
