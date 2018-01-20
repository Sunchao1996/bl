/**
 * Copyright 2017 弘远技术研发中心. All rights reserved
 * Project Name:cdpf_v1
 * Module Name:core
 */
package com.bl.sys.service;

import com.bl.sys.dao.SysUserLoginDao;
import com.bl.sys.vo.SysUserLoginSearchVO;
import com.bl.sys.model.SysUserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 系统登录Service
 *
 * @date 2017-06-13
 */
@Service
public class SysUserLoginService {
    @Autowired
    private SysUserLoginDao sysUserLoginDao;

    /**
     * 
     * what: 登录时新增登录信息
     * 
     * @param sysUserLogin
     *
     */
    @Async
    public void add(SysUserLogin sysUserLogin) {
        sysUserLoginDao.add(sysUserLogin);
    }

   /**
    * 
    * what: 取得最后登录信息
    * 
    * @param user_id
    * @return
    *
    */
    public SysUserLogin getLastLogin(int user_id) {
        return sysUserLoginDao.getLastLogin(user_id);
    }

    public List<SysUserLogin> list(SysUserLoginSearchVO sysUserloginSearchVO) {
        return sysUserLoginDao.list(sysUserloginSearchVO);
    }

   /**
    * 
    * what: 查询用户登录总数
    * 
    * @param sysUserloginSearchVO 用户登录信息搜索VO
    * @return int
    *
    */
    public int count(SysUserLoginSearchVO sysUserloginSearchVO) {
        return sysUserLoginDao.count(sysUserloginSearchVO);
    }

}
