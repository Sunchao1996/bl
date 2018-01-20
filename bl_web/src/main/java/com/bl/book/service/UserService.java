package com.bl.book.service;

import java.util.List;

import com.bl.book.beans.bo.User;
import com.bl.book.condition.child.UserCond;

/**
 * @功能描述 用户管理的service接口
 * @author chao
 *
 */
public interface UserService {
	/**
	 * @功能描述 新增用户
	 */
	int insert(User user);
	/**
	 * @功能描述 显示所有图书借阅员信息
	 * @return
	 */
	List<User> getByPage(UserCond cond);
	/**
	 * @功能描述 删除图书借阅员信息
	 * @return
	 */
	void delete(String[] ids);
	/**
	 * @功能描述 重置密码
	 * @return
	 */
	void updatePwd(User user);
	/**
	 *@功能描述 用户修改密码 
	 */
	int updatePwdByUser(User user);
}
