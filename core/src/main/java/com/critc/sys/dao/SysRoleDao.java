package com.critc.sys.dao;

import com.critc.core.dao.BaseDao;
import com.critc.sys.model.SysRole;
import com.critc.sys.vo.SysRoleSearchVO;
import com.critc.util.model.ComboboxVO;
import com.critc.util.page.PageUtil;
import com.critc.util.string.StringUtil;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 * what:    系统角色管理Dao
 * 
 */
@Repository
public class SysRoleDao extends BaseDao<SysRole, SysRoleSearchVO> {
	/**
	 * 
	 * what: 按角色名称查询
	 * 
	 * @param sysRole sysRole
	 * @return int
	 */
	public int getNameNum(SysRole sysRole) {
		String sql = "select count(*) from t_sys_role where name = ?";
		if (StringUtil.isNotNullOrEmpty(String.valueOf(sysRole.getId()))) {
			sql += " and id != ?";
		}
		Object[] objects = new Object[] {sysRole.getName(), sysRole.getId()};
		return count(sql, objects);
	}

	/**
	 * 
	 * what: 新增并返回主键
	 * 
	 * @param sysRole sysRole
	 * @return int
	 */
	public int add(SysRole sysRole) {
		String sql = "insert into t_sys_role(id,name,description,display_order,created_by,created_at,last_modified_by,last_modified_at,is_delete)"
				+ " values(seq_t_sys_role.nextval,:name,:description,:displayOrder,:createdBy,sysdate,:lastModifiedBy,sysdate,:isDelete)";
		return insertForId(sql, sysRole, "id");
	}

	/**
	 * 
	 * what: 修改
	 * 
	 * @param sysRole sysRole
	 * @return int
	 *
	 */
	public int update(SysRole sysRole) {
		String sql = "update t_sys_role set name=:name,description=:description,display_order=:displayOrder,last_modified_by=:lastModifiedBy,last_modified_at=sysdate where id=:id";
		return update(sql, sysRole);
	}

	/**
	 * 
	 * what: 删除角色
	 * 
	 * @param id 角色id
	 * @return int
	 *
	 */
	public int delete(int id) {
		String sql = "delete from t_sys_role where id=? ";
		return delete(sql, id);
	}

	/**
	 * 
	 * what: 根据id获取
	 * 
	 * @param id 角色id
	 * @return 根据id获取的角色对象
	 *
	 */
	public SysRole get(int id) {
		String sql = "select t.id,t.name,t.description,t.display_order,t.created_by,t.created_at,t.last_modified_at,t.last_modified_by from t_sys_role t where id=? ";
		return get(sql, id);
	}

	/**
	 * 
	 * what: 角色列表
	 * 
	 * @param sysRoleSearchVO 角色查询VO
	 * @return list
	 *
	 */
	public List<SysRole> list(SysRoleSearchVO sysRoleSearchVO) {
		String sql = "select t.id,t.name,t.description,t.display_order,t.created_by,t.created_at,t.last_modified_at,t.last_modified_by,t.is_delete from t_sys_role t where 1=1 ";
		sql += createSearchSql(sysRoleSearchVO);
		sql += " order by display_order asc ";
		sql = PageUtil.createOraclePageSQL(sql, sysRoleSearchVO.getPageIndex());
		return list(sql, sysRoleSearchVO);
	}

	/**
	 * 
	 * what: 查询角色总数
	 * 
	 * @param sysRoleSearchVO 角色查询VO
	 * @return int
	 *
	 */
	public int count(SysRoleSearchVO sysRoleSearchVO) {
		String sql = "select count(*) from t_sys_role where 1=1 ";
		sql += createSearchSql(sysRoleSearchVO);
		return count(sql, sysRoleSearchVO);
	}

	/**
	 * 
	 * what: 创建查询语句
	 * 
	 * @param sysRoleSearchVO 角色查询VO
	 * @return sql语句
	 *
	 */
	private String createSearchSql(SysRoleSearchVO sysRoleSearchVO) {
		String sql = "";
		if (StringUtil.isNotNullOrEmpty(sysRoleSearchVO.getName())) {
			sql += " and name like :nameStr";
		}
		return sql;
	}

	/**
	 * 
	 * what: 角色下拉框
	 * 
	 * @return list
	 *
	 */
	public List<ComboboxVO> listCombo() {
		String sql = "select id value,name content from t_sys_role where 1=1 order by id ";
		return listCombobox(sql);
	}

	/**
	 * 
	 * what: 根据角色id查询用户的数量
	 * 
	 * @param id 部门id
	 * @return int
	 *
	 */
	public int countUserNum(int id) {
		String sql = "select count(*) from t_sys_user where role_id=?";
		return count(sql, id);
	}
}
