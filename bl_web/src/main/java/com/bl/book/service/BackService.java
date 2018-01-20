package com.bl.book.service;

import java.util.List;

import com.bl.book.beans.bo.BorrowInfo;
import com.bl.book.condition.child.BorrowInfoCond;

/**
 * @功能描述 还书登记的service
 * @author chao
 *
 */
public interface BackService {
	
	/**
	 * @功能描述 分页查询
	 */
	List<BorrowInfo> queryByPage(BorrowInfoCond cond);
	/**
	 * @功能描述 还书时更新数据库表
	 */
	int update(String[] ids);
	/**
	 * @功能描述 丢失图书时更新数据库表
	 */
	int updateLose(String[] ids);
}
