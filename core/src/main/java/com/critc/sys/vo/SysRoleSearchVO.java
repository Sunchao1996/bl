package com.critc.sys.vo;

import com.critc.util.page.PageSearchVO;

/**
 * 
 * what:    角色查询VO
 * 
 *
 */
public class SysRoleSearchVO extends PageSearchVO {
	/**
	 * 是否内置角色，默认为0
	 */
    private Integer isBuildin = 0;
    
    /**
     * 角色名称
     */
    private String name;
    public Integer getIsBuildin() {
        return isBuildin;
    }

    public void setIsBuildin(Integer isBuildin) {
        this.isBuildin = isBuildin;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	//角色名称模糊查询
    public String getNameStr() {
        return "%" + name + "%";
    }

	@Override
    public String toString() {
        return "SysRoleSearchVO{"
        		+ "isBuildin=" + isBuildin
        		+ "name='" + name + '\''
                + '}';
    }
}
