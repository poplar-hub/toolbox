package com.dabbler.tools.utils.domain;


import lombok.Data;

import java.io.Serializable;


/**
* 余额（应付|应收）
* @TableName balance
*/
@Data
public class Balance implements Serializable {

    /**
    * 主键
    */

    private String id;
    /**
    * 年月eg:2023-01
    */
    private String month;
    /**
    * 部门编码
    */

    private String deptCode;
    /**
    * 部门名称
    */

    private String deptName;
    /**
    * 个人编码
    */

    private String personalCode;
    /**
    * 个人名称
    */

    private String personalName;
    /**
    * 期初余额
    */

    private double startAmount;
    /**
    * 贷 平 借
    */

    private String startDirection;
    /**
    * 期末余额
    */

    private double endAmount;
    /**
    * 贷 平 借
    */

    private String endDirection;
    /**
    * 应付员工款 单位往来
    */

    private String cate;
    /**
    * 类型（1=应付 2=应收）
    */

    private Integer balanceType;
    /**
    * 金额
    */
    private double amount;
    /**
    * 创建时间
    */




}
