package com.eagle.cloud.route.controller;

import java.util.List;
import java.util.Map;

import com.eagle.cloud.model.system.SysUser;
import com.eagle.cloud.route.model.SysScreen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.eagle.cloud.commons.PageResult;
import com.eagle.cloud.commons.Result;
import com.eagle.cloud.route.service.ScreenService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 场景管理
 * @class: ScreenController
 * @package: com.eagle.cloud.route.controller
 * @description: 场景管理
 * @author wzhz
 * @date 2019年6月29日 下午7:28:44
 * @version v1.0.0
 */
@Slf4j
@RestController
@Api(tags = "大屏模块api")
public class ScreenController {

	@Autowired
	private ScreenService screenService;

//	@ApiOperation(value = "查询大屏模块信息")
//	@GetMapping("/screen/list")
//	public List<SysScreen> getScreenList(@RequestParam Map<String, Object> params) throws JsonProcessingException {
//		return screenService.getScreenList(params);
//	}
//	@ApiOperation(value = "根据用户名删除自定义大屏信息,参数 userId")
//	@DeleteMapping("/screen/delete")
//	public Result<?> deleteScreen(@RequestParam Long id){
//		try {
//			log.debug("删除自定义大屏信息[id]: " + id);
//			screenService.deleteScreen(id);
//			return Result.succeed("操作成功");
//		} catch (Exception e) {
//			e.printStackTrace();
//			return Result.failed("操作失败");
//		}
//	}
//	@ApiOperation(value = "给用户添加自定义大屏信息,参数 modelList{modelId,customNum}")
//	@DeleteMapping("/screen/add")
//	public Result<?> addScreen(@RequestBody SysScreen sysScreen){
//		try {
//			log.debug("新增自定义大屏[id]: " + sysScreen.getId());
//			screenService.addScreen(sysScreen);
//			return Result.succeed("操作成功");
//		} catch (Exception e) {
//			e.printStackTrace();
//			return Result.failed("操作失败");
//		}
//	}

}
