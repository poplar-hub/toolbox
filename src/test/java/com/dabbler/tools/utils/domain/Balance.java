package com.dabbler.tools.utils.domain;


import com.dabbler.tools.anonation.Table;
import com.dabbler.tools.anonation.TableColumn;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
* 余额（应付|应收）
* @TableName balance
*/
@Data
@Table(name = "balance")
public class Balance implements Serializable {

    /**
    * 主键
    */

    @TableColumn(name="id",comment = "主键")
    private String id;
    /**
    * 年月eg:2023-01
    */
    @TableColumn(name="month")
    private String month;
    /**
    * 部门编码
    */

    @TableColumn(name="dept_code")
    private String deptCode;
    /**
    * 部门名称
    */

    @TableColumn(name="dept_name")
    private String deptName;
    /**
    * 个人编码
    */

    @TableColumn(name="personal_code")
    private String personalCode;
    /**
    * 个人名称
    */
    @TableColumn(name="personal_name")
    private String personalName;
    /**
    * 期初余额
    */

    @TableColumn(name="start_amount")
    private double startAmount;
    /**
    * 贷 平 借
    */

    @TableColumn(name="start_direction")
    private String startDirection;
    /**
    * 期末余额
    */

    @TableColumn(name="end_amount")
    private double endAmount;
    /**
    * 贷 平 借
    */

    @TableColumn(name="end_direction")
    private String endDirection;
    /**
    * 应付员工款 单位往来
    */

    @TableColumn(name="cate")
    private String cate;
    /**
    * 类型（1=应付 2=应收）
    */

    @TableColumn(name="balance_type")
    private Integer balanceType;
    /**
    * 金额
    */
    @TableColumn(name="amount")
    private double amount;


    @TableColumn(name="crt_tm")
    private LocalDateTime crtTm;




}
