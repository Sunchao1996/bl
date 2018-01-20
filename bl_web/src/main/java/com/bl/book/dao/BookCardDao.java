package com.bl.book.dao;

import java.util.List;

import com.bl.book.beans.bo.BookCard;
import com.bl.book.beans.bo.BookCardStatus;
import com.bl.book.condition.child.BookCardCond;
/**
 * @功能描述 图书卡dao接口
 * @author chao
 *
 */
public interface BookCardDao {
	/**
	 * @功能描述 查询所有图书卡所有状态信息
	 */
	public List<BookCardStatus> queryAllObj();

	/**
	 * @功能描述 添加图书卡
	 */
	public int insert(BookCard bc);
	/**
	 * @功能描述 分页查询统计个数
	 */
	public int queryCount(BookCardCond cond);
	/**
	 * @功能描述 分页查询
	 */
	public List<BookCard> queryByPage(BookCardCond cond);
	/**
	 * @功能描述 禁用图书卡
	 */
	public void delete(BookCard card);
	/**
	 * @功能描述 根据图书卡编码查询
	 */
	public BookCard findById(String id);
	/**
	 * @功能描述 添加读者不成功的时候将开通的图书卡删除
	 */
	public void deleteT(BookCard card);
}
