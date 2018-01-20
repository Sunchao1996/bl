package com.bl.book.dao;

import java.util.List;

import com.bl.book.beans.bo.User;
import com.bl.book.condition.child.UserCond;
/**
 * @功能描述 操作用户的dao接口
 * @author chao
 *
 */
public interface UserDao {
	/**
	 * @功能描述 新增用户
	 */
	int insert(User user);
	/**
	 * @功能描述 根据用户名统计个数
	 */
	int queryCount(UserCond cond);
	/**
	 * @功能描述 分页查询
	 */
	List<User> queryByList(UserCond cond);
	/**
	 * @功能描述 删除借阅员信息
	 */
	void delete(String userId);
	/**
	 * @return 
	 * @功能描述 重置密码
	 */
	int updatePwd(User user);
	/**
	 * @功能描述 获取用户
	 */
	User findByUser(User user);
}
