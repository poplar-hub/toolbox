package com.dabbler.tools.utils.domain;

import lombok.Data;

import java.io.Serializable;

/**
* 存货统计
* @TableName inventory_statistics
*/
@Data
public class InventoryStatistics implements Serializable {

    /**
    * 主键
    */

    private String id;
    /**
    * 年月eg:2023-01
    */

    private String month;
    /**
    * 出入库存量类型(月初 入库 出库 【月初+入库-出库=月末】)
    */

    private String inventoryType;
    /**
    * 项目类型（原材料  办公用品 易耗品 协作件备货 成品  在制生产成本 委外加工物资）
    */

    private String item_type;
    /**
    * 项目（材料 外购半成品 批次仓 办公用品 易耗品 协作件备货 成品）
    */

    private String item;
    /**
    * 金额
    */

    private double amount;


    public InventoryStatistics(String id,String month,String item_type,String item){
        this.id = id;
        this.month = month;
        this.item = item;
        this.item_type = item_type;
    }



}
