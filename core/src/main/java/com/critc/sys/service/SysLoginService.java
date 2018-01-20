/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:cdpf_v1
 * Module Name:core
 */
package com.critc.sys.service;

import com.critc.sys.dao.SysUserLoginDao;
import com.critc.sys.model.SysUserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

/**
 * 
 * what: 系统登录Service
 *
 */
@Service
@Configuration
@EnableAsync
public class SysLoginService {
	/**
	 * 用户登录dao
	 */
    @Autowired
    private SysUserLoginDao sysUserLoginDao;

   /**
    * 
    * what:  登录时新增登录信息,异步处理
    * 
    * @param sysUserLogin 用户登录VO
    *
    */
    @Async
    public void add(SysUserLogin sysUserLogin) {
        sysUserLoginDao.add(sysUserLogin);
    }

}
