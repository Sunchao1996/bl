package com.bl.sys.dao;

import com.bl.core.dao.BaseDao;
import com.bl.sys.vo.SysUserSearchVO;
import com.bl.util.model.ComboboxVO;
import com.bl.util.page.PageUtil;
import com.bl.util.string.StringUtil;
import com.bl.sys.model.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统用户Dao
 *
 * @date 2017-06-13
 */
@Repository
public class SysUserDao extends BaseDao<SysUser, SysUserSearchVO> {

    /**
     * what:    (这里用一句话描述这个方法的作用)
     *
     * @param sysUser
     * @return
     */
    public int add(SysUser sysUser) {
        String sql = "insert into t_sys_user(id,username,password,avatar,randomcode,status,realname,mobile," +
                "created_at,created_by,role_id,department_id,pinyin,email,nation,gender,political,education," +
                "graduated_school,major,idcard,telephone,post,job_title,display_order,last_login_date,completion)";
        sql += " values(seq_t_sys_user.nextval,:username,:password,:avatar,:randomcode,1,:realname,:mobile,sysdate," +
                ":createdBy,:roleId,:departmentId,:pinyin,:email,:nation,:gender,:political,:education," +
                ":graduatedSchool,:major,:idcard,:telephone,:post,:jobTitle,:displayOrder,:lastLoginDate,:completion)";
        return insertForId(sql, sysUser, "id");
    }

    /**
     * 修改用户
     *
     * @param sysUser
     * @return
     */
    public int update(SysUser sysUser) {
        String sql = "update t_sys_user set realname=:realname,role_id=:roleId,mobile=:mobile,last_modified_by=:lastModifiedBy," +
                "last_modified_at=sysdate,department_id=:departmentId, pinyin=:pinyin,avatar=:avatar," +
                "email=:email,nation=:nation,gender=:gender,political=:political,education=:education," +
                "graduated_school=:graduatedSchool,major=:major,idcard=:idcard,telephone=:telephone,post=:post," +
                "job_title=:jobTitle,display_order=:displayOrder,completion=:completion " +
                "where id=:id ";
        return update(sql, sysUser);
    }


    /**
     * 修改密码
     *
     * @param id
     * @param newPass
     * @param randowmcode
     * @return
     */
    public int updatePass(int id, String newPass, String randowmcode) {
        String sql = "update t_sys_user set password=?,randomcode=?  where id=? ";
        return update(sql, newPass, randowmcode, id);
    }


    /**
     * 修改状态
     *
     * @param id
     * @param status
     * @return
     */
    public int updateStatus(int id, int status) {
        String sql = "update t_sys_user set status=?  where id=?";
        return update(sql, status, id);
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    public int delete(int id) {
        String sql = "delete from t_sys_user where id=?";
        return delete(sql, id);
    }

    public SysUser get(int id) {
        String sql = "select t.id,t.id,t.username,t.password,t.randomcode,t.status,t.realname,t.mobile,t.avatar,t" +
                ".created_at,t.created_by,t.role_id,t.last_modified_by,t.last_modified_at,t.department_id,t.is_admin," +
                "t.is_check,t.pinyin,t.email,t.gender,t.nation,t.political,t.education,t.graduated_school,t.major," +
                "t.idcard,t.telephone,t.post,t.job_title,t.display_order,t.last_login_date,t.completion,(select name " +
                "from t_sys_department d where t.department_id=d.id) as department_name,(select name " +
                "from t_sys_role d where t.role_id=d.id) as role_name from t_sys_user t where " +
                "id=? ";
        return get(sql, id);
    }

    /**
     * what:    根据username获取sysUser
     *
     * @param username
     * @return
     */
    public SysUser getByUsername(String username) {
        String sql = "select t.id,t.username,t.password,t.randomcode,t.status,t.realname,t.mobile,t.avatar,t.created_at,t.created_by,t.role_id,t.last_modified_by,t.last_modified_at,(select name from t_sys_role where id=role_id) roleName from t_sys_user t where username=?";
        return get(sql, username);
    }

    /**
     * 根据手机号获取用户
     *
     * @param mobile
     * @return
     */
    public SysUser getByMobile(String mobile) {
        String sql = "select t.id,t.username,t.password,t.randomcode,t.status,t.realname,t.mobile,t.avatar,t.created_at,t.created_by,t.role_id,t.last_modified_by,t.last_modified_at,(select name from t_sys_role where id=role_id) roleName from t_sys_user t where mobile=?";
        return get(sql, mobile);
    }

    /**
     * 查询用户信息
     *
     * @param sysUserSearchVO
     * @return
     */
    public List<SysUser> list(SysUserSearchVO sysUserSearchVO) {
        String sql = "select t.id, t.username, t.password, t.randomcode, t.status, t.realname, t.mobile, t.avatar," +
                " t.created_at, t.created_by, t.role_id, t.last_modified_by, t.last_modified_at, t.department_id," +
                "t.is_admin, t.is_check, t.pinyin, t.email, t.gender, t.nation, t.political, t.education," +
                "t.graduated_school,t.major, t.idcard, t.telephone, t.post, t.job_title, t" +
                ".display_order," +
                "t.last_login_date," +
                "(select name from t_sys_department d where t.department_id = d.id) as department_name,  " +
                "(select name from t_sys_role where id = t.role_id) roleName from t_sys_user t where " +
                "1=1 ";
        sql += createSearchSql(sysUserSearchVO);
        sql += " order by t.id asc";
        sql = PageUtil.createOraclePageSQL(sql, sysUserSearchVO.getPageIndex());
        return list(sql, sysUserSearchVO);
    }

    public List<SysUser> listAll() {
        String sql = "select t.id, t.username, t.password, t.randomcode, t.status, t.realname, t.mobile, t.avatar," +
                " t.created_at, t.created_by, t.role_id, t.last_modified_by, t.last_modified_at, t.department_id," +
                "t.is_admin, t.is_check, t.pinyin, t.email, t.gender, t.nation, t.political, t.education," +
                "t.graduated_school, t.major, t.idcard, t.telephone, t.post, t.job_title, t.display_order," +
                "t.last_login_date," +
                "(select name from t_sys_department d where t.department_id = d.id) as department_name,  " +
                "(select name from t_sys_role where id = t.role_id) roleName from t_sys_user t ";
        sql += " order by t.id asc";
        return list(sql);
    }

    /**
     * 查询用户总数
     *
     * @param sysUserSearchVO
     * @return
     */
    public int count(SysUserSearchVO sysUserSearchVO) {
        String sql = "select count(*) from t_sys_user where 1=1 ";
        sql += createSearchSql(sysUserSearchVO);
        return count(sql, sysUserSearchVO);
    }

    private String createSearchSql(SysUserSearchVO sysUserSearchVO) {
        String sql = "";
        if (StringUtil.isNotNullOrEmpty(sysUserSearchVO.getUsername())) {
            sql += " and username=:username";
        }
        if (StringUtil.isNotNullOrEmpty(sysUserSearchVO.getRealname())) {
            sql += " and realname like :realnameStr";
        }
        if (sysUserSearchVO.getRoleId() != null) {
            sql += " and role_id=:roleId";
        }
        if (sysUserSearchVO.getStatus() != null) {
            sql += " and status=:status";
        }
        if (sysUserSearchVO.getDepartmentId() != null) {
            sql += " and department_id=:departmentId";
        }
        if ("1".equals(sysUserSearchVO.getIsCompletion())) {
            sql += " and completion > 49";
        }
        if ("0".equals(sysUserSearchVO.getIsCompletion())) {
            sql += "and completion < 50";
        }


        return sql;
    }

    /**
     * 所有人员列表，查询日志使用
     *
     * @return
     */
    public List<ComboboxVO> listAllUser() {
        String sql = "select id value,username content from t_sys_user  order by id";
        return listCombobox(sql);
    }

    /**
     * 修改头像
     *
     * @param id
     * @param avatar 头像
     * @return
     */
    public int updateAvatar(int id, String avatar) {
        String sql = "update t_sys_user set avatar=?  where id=?";
        return update(sql, avatar, id);
    }

    /**
     * 根据部门ID查询系统用户
     *
     * @param departmentId
     * @return
     */
    public List<SysUser> listUserByDepartmentId(int departmentId) {
        String sql = "select t.id,t.username,t.realname,t.post,t.telephone,t.mobile "
                + "from t_sys_user t "
                + "where t.department_id = ?";
        sql += " order by id asc";
        return list(sql, departmentId);
    }

    /**
     * 根据部门ID查询系统用户数量
     *
     * @param departmentId
     * @return
     */
    public int countUserByDepartmentId(int departmentId) {
        String sql = "select count(*) from t_sys_user t "
                + "where t.department_id = ?";
        return count(sql, departmentId);
    }

}
