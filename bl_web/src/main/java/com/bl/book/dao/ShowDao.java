package com.bl.book.dao;

import java.util.List;
import java.util.Map;

import com.bl.book.beans.vo.ShowCharts;

/**
 * @功能描述 显示报表dao接口
 * @author chao
 *
 */
public interface ShowDao {
	/**
	 * @功能描述 查询报表数据
	 */
	List<ShowCharts> queryList(Map<String, Object> condMap);
	
}
