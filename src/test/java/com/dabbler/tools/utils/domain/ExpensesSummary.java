package com.dabbler.tools.utils.domain;



import lombok.Data;

import java.io.Serializable;

/**
* 销售、财务费用汇总
* @TableName expenses_summary
*/
@Data
public class ExpensesSummary implements Serializable {

    /**
    * 主键
    */
    private String id;
    /**
    * 年月eg:2023-01
    */

    private String month;
    /**
    * 项目（工资 奖金 福利费 伙食费 保险费 办公费 差旅费 折旧 水电费 电话费 油补 邮寄费 运费 外租房房租 培训费 节日礼金 日常礼金 业务招待费 部门会餐费 加班餐费 汽车维护费 文化活动费 低值易耗品摊销 业务宣传推广费 手续费 办事处费用 投标费 维修费 售前费用 售后费用 安装调试费 房屋折旧费 劳务费 软件服务费 展览费 劳保费 咨询服务费 业务咨询费 业务提成 折让/折扣 |  银行手续费 利息支出   贷款利息 利息收入 贷款担保费 ）
    */
    private String item;
    /**
    * 费用类型（云防爆 储能业务 ）
    */
    private String type;
    /**
    * 金额
    */
    private double amount;


    public ExpensesSummary(String id ,String item,String type){
        this.item = item;
        this.id = id;
        this.type = type;
    }



}
