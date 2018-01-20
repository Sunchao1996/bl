/**
 * Copyright 2017 弘远技术研发中心. 
 *  All rights reserved
 * Project Name:cdpf_v1
 * Module Name:cdpf_core_web
 */
package com.critc.common.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.baidu.ueditor.ActionEnter;
import com.critc.core.pub.PubConfig;
import com.critc.util.file.FileUploadUtil;
import com.critc.util.json.JsonUtil;
import com.critc.util.string.StringUtil;
import com.critc.util.web.WebUtil;

/**
 * 
 * what: 用于ueditor插件图片上传功能. <br/>
 *
 * @author 刘正荣 created on 2017年11月15日
 */
@Controller
@RequestMapping("/")
public class UeditorController {

	/**
	 * 全局参数配置
	 */
	@Autowired
	private PubConfig pubConfig;

	/**
	 * 
	 * what: 获取上传图片所需的参数. <br/>
	 * 
	 * @param request request
	 * 
	 * @param response response
	 * 
	 * 
	 * @author 刘正荣 created on 2017年11月15日
	 */
	@RequestMapping("/getUeditorConfig")
	public void getUeditorConfig(HttpServletRequest request, HttpServletResponse response) {
		String rootPath = request.getServletContext().getRealPath("/assets/ueditor");
		String action = request.getParameter("action");
		String result = new ActionEnter(request, rootPath).exec();
		if (StringUtil.isNotNullOrEmpty(action) && (action.equals("listfile") || action.equals("listimage"))) {
			rootPath = rootPath.replace("\\", "/");
			result = result.replaceAll(rootPath, "/");
		}
		WebUtil.out(response, result);
	}

	/**
	 * 
	 * what: 上传图片方法. <br/>
	 * 
	 * @param request request
	 * 
	 * @param response response
	 * @param basePath 上传图片的基本根路径
	 * 
	 *
	 * @author 刘正荣 created on 2017年11月15日
	 */
	@RequestMapping("/ueditorUploadImage")
	public void ueditorUploadImage(HttpServletRequest request, HttpServletResponse response, String basePath) {
		Map<String, Object> result = new HashMap<String, Object>();
		MultipartHttpServletRequest mReq = null;
		MultipartFile file = null;
		String fileName = "";
		try {
			mReq = (MultipartHttpServletRequest) request;
			// 从config.json中取得上传文件的ID
			file = mReq.getFile("upfile");
			// 取得文件的原始文件名称
			fileName = file.getOriginalFilename();

			if (StringUtil.isNotNullOrEmpty(fileName)) {
				fileName = new StringBuffer().append(new Date().getTime())
						.append(fileName.substring(fileName.indexOf("."))).toString();
				FileUploadUtil.upload(request, response, pubConfig.getImageUploadPath() + basePath + "/" + fileName);
			} else {
				throw new IOException("文件名为空!");
			}

			result.put("state", "SUCCESS");
			result.put("url", "/" + basePath + fileName);
			result.put("title", fileName);
			result.put("original", fileName);
			WebUtil.out(response, JsonUtil.toStr(result));
		} catch (Exception e) {
			e.getStackTrace();
		}

	}

}
