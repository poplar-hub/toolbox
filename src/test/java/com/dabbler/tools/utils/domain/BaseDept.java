package com.dabbler.tools.utils.domain;



import lombok.Data;

import java.io.Serializable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
* 部门
* @TableName base_dept
*/
@Data
public class BaseDept implements Serializable {

    /**
    * 主键
    */

    private String id;
    /**
    * 部门编号
    */

    private String code;
    /**
    * 部门名称
    */

    private String name;
    /**
    * 上级节点
    */

    private String pid;
    /**
    * 下级节点逗号拼接
    */

    private String cid;
    /**
    * 部门类型1=事业部门
    */

    private Integer type;
    /**
    * 排序0优先级高
    */

    private Integer sort;
    /**
    * 创建时间
    */

    private Date crtTm;
    /**
    * 更新时间
    */

    private Date updTm;
    /**
    * 创建人
    */

    private String crtUid;
    /**
    * 更新人
    */

    private String updUid;


    public static BaseDept createStudentFromResultSet(ResultSet rs) throws SQLException {
        BaseDept dept = new BaseDept();
        dept.setId(rs.getString("id"));
        dept.setName(rs.getString("name"));
        dept.setCode(rs.getString("code"));
        return dept;
    }

}
