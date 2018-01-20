package com.bl.book.dao;

import java.util.List;

import com.bl.book.beans.bo.BorrowInfo;
import com.bl.book.condition.child.BorrowInfoCond;

/**
 * @功能描述 还书登记dao接口
 * @author chao
 *
 */
public interface BackDao {
	/**
	 * @功能描述 根据条件统计个数
	 */
	int pageCount(BorrowInfoCond cond);
	/**
	 * @功能描述 分页查询
	 */
	List<BorrowInfo> queryByPage(BorrowInfoCond cond);
	/**
	 * @功能描述 还书时更新数据库表
	 */
	int update(BorrowInfo bi);
	/**
	 * @功能描述 丢失图书书时减少对应库存
	 */
	int updateLose(BorrowInfo bi);
	/**
	 * @功能描述 丢失图书书时更改对应位置状态
	 */
	int updateSeat(BorrowInfo bi);
	/**
	 * @功能描述 丢失图书书时更改对应类别数量
	 */
	int updateCategory(BorrowInfo bi);
	/**
	 * @功能描述 删除主表信息
	 */
	int deleteBookP(BorrowInfo bi);
}
