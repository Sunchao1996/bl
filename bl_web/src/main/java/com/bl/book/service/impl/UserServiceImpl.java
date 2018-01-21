package com.bl.book.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bl.book.beans.bo.User;
import com.bl.book.condition.child.UserCond;
import com.bl.book.dao.UserDao;
import com.bl.book.service.UserService;

/**
 * @功能描述 用户管理的service接口实现类
 * @author chao
 *
 */
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao dao;
	private Log logger = LogFactory.getLog(getClass());
	
	@Override
	public int insert(User user) {
		
		return dao.insert(user);
	}
	@Override
	public List<User> getByPage(UserCond cond) {
		int total = dao.queryCount(cond);
		cond.setTotal(total);
		logger.info("总记录数:"+total);
		int startLine = (cond.getPageNo()-1) * cond.getPageSize();
		int pageCount = total%cond.getPageSize()==0?total/cond.getPageSize():total/cond.getPageSize()+1;
		cond.setStartLine(startLine);
		cond.setPageCount(pageCount);
		List<User> userList = dao.queryByList(cond);
		return userList;
	}
	@Override
	public void delete(String[] managerUser) {
		for (String userId : managerUser) {
			
			dao.delete(userId);
		}
	}
	@Override
	public void updatePwd(User user) {
		dao.updatePwd(user);
		
	}
	@Override
	public int updatePwdByUser(User user) {
		User u = dao.findByUser(user);
		if(u == null){
			return 0;
		}
		u.setUserPassword(user.getNewPassword());
		int count = dao.updatePwd(u);
		return count;
	}

}
