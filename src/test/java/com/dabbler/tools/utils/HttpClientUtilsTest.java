package com.dabbler.tools.utils;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;


public class HttpClientUtilsTest {

    String host="https://api.hbdm.com/";
    @Test
    public void postForJSON() {
  //      HttpClientUtils.postForJSON(host+"api/v1/contract_account_info",)
    }

    @Test
    public void getForJSON() {
        JSONObject response = HttpClientUtils.getForJSON("https://api.hbdm.com/api/v1/contract_contract_info?symbol=BTC");
        System.out.println("获取合约信息contract_contract_info"+response);
    }

    @Test
    public void getcontract_index(){
        //获取合约指数信息
        JSONObject response = HttpClientUtils.getForJSON(host+"/api/v1/contract_index?symbol=BTC");
        System.out.println("获取合约指数信息contract_index"+response);
    }

    @Test
    public void getcontract_price_limit(){
        //获取合约最高限价和最低限价
        JSONObject response = HttpClientUtils.getForJSON(host+"/api/v1/contract_price_limit?symbol=BTC");
        System.out.println("获取合约最高限价和最低限价contract_price_limit"+response);
    }

    @Test
    public void getcontract_open_interest(){
        JSONObject response = HttpClientUtils.getForJSON(host+"/api/v1/contract_open_interest?symbol=BTC");
        System.out.println("获取当前可用合约总持仓量contract_open_interest"+response);
    }

    @Test
    public void getcontract_delivery_price(){
        JSONObject response = HttpClientUtils.getForJSON(host+"/api/v1/contract_delivery_price?symbol=BTC");
        System.out.println("获取预估交割价contract_delivery_price"+response);
    }

    @Test
    public void getmarketdepth(){
        JSONObject response = HttpClientUtils.getForJSON(host+"/market/depth?symbol=BTC_CQ&type=step5");
        System.out.println("获取行情深度数据getmarketdepth"+response);
    }

    @Test
    public void getkline(){
        JSONObject response = HttpClientUtils.getForJSON(host+"/market/history/kline?symbol=BTC_CQ&size=200&period=4hour");
        System.out.println("获取K线数据getkline"+response);
    }

    @Test
    public void getdetailmerged(){
        JSONObject response = HttpClientUtils.getForJSON(host+"/market/detail/merged?symbol=BTC");
        System.out.println("获取聚合行情getdetailmerged"+response);
    }

    @Test
    public void getcontract_his_open_interest(){
        JSONObject response = HttpClientUtils.getForJSON(host+"api/v1/contract_his_open_interest?symbol=BTC");
        System.out.println("平台持仓量的查询contract_his_open_interest"+response);
    }

    @Test
    public void history_trade(){
        JSONObject response = HttpClientUtils.getForJSON(host+"/market/history/trade?symbol=BTC");
        System.out.println("批量获取最近的交易记录history_trade"+response);
    }

    @Test
    public void contract_risk_info(){
        JSONObject response = HttpClientUtils.getForJSON(host+"api/v1/contract_risk_info?symbol=BTC");
        System.out.println("查询合约风险准备金余额和预估分摊比例"+response);
    }

    @Test
    public void contract_insurance_fund(){
        JSONObject response = HttpClientUtils.getForJSON(host+"api/v1/contract_insurance_fund?symbol=BTC");
        System.out.println("查询合约风险准备金余额历史数据"+response);
    }
    @Test
    public void contract_adjustfactor(){
        JSONObject response = HttpClientUtils.getForJSON(host+"api/v1/contract_adjustfactor?symbol=BTC");
        System.out.println("查询平台阶梯调整系数contract_adjustfactor"+response);
    }
}