package com.bl.sys.model;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.bl.util.model.BaseModel;

/**
 * 
 * what:   系统角色
 * 
 */
public class SysRole extends BaseModel {
	/**
	 * 角色id
	 */
    private int id;
    /**
     * 角色名称
     */
    @NotEmpty
    @Size(max = 20)
    private String name;
    /**
     * 描述
     */
    @Size(max = 50)
    private String description;
    /**
     * 排序
     */
    @NotNull
    @Digits(integer = 6, fraction = 0)
    private int displayOrder;
    /**
     * 创建人的id
     */
    private int createUserId;
    /**
     * 是否能够删除，0:不可以被删除；1：可以被删除。默认为1
     */
    private int isDelete;

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "SysRole{" 
        		+ "id=" + id
        		+ ", name='" + name + '\''
                + ", description='" + description + '\''
                + ", displayOrder=" + displayOrder
                + ", createUserId=" + createUserId
                + '}';
    }


    public int getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
    }


    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
