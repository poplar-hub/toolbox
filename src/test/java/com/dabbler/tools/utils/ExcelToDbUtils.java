package com.dabbler.tools.utils;

import com.github.yitter.idgen.YitIdHelper;
import com.dabbler.tools.utils.domain.Balance;
import com.dabbler.tools.utils.domain.BaseDept;
import com.dabbler.tools.utils.domain.ExpensesSummary;
import com.dabbler.tools.utils.domain.InventoryStatistics;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author poplar-hub
 * @version 1.0
 * @date 2023/8/2
 */
@Slf4j
public class ExcelToDbUtils {



    private static Connection getConnect(){
        return JDBCUtils.getConnect("com.mysql.cj.jdbc.Driver","jdbc:mysql://rm-2ze3ija8bm6h21k4qho.mysql.rds.aliyuncs.com:3306/big_brain","tyjh_2022","MYlbK48%3WPOItBb4bf*GOsj");
    }



    public static  List<BaseDept> getAll() throws SQLException {
        Connection connection = getConnect();
        String sql = "select * from `big_brain`.base_dept ;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<BaseDept> baseDepts = Lists.newArrayList();
        while (resultSet.next()){
            baseDepts.add(BaseDept.createStudentFromResultSet(resultSet));
        }
        System.out.println(baseDepts.size());
        log.info("获取部门：{}条",baseDepts.size());
        return baseDepts;
    }

    public static void insertBalance(Balance balance)throws SQLException{
        String sql = "INSERT INTO `big_brain`.`balance`(`id`, `month`, `dept_code`, `dept_name`, `personal_code`, `personal_name`, `start_amount`, `start_direction`, `end_amount`, `end_direction`, `cate`, `balance_type`, `amount`) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";

        PreparedStatement  ps = getConnect().prepareStatement(sql.toString());
        ps.setObject(1,balance.getId());
        ps.setObject(2,balance.getMonth());
        ps.setObject(3,balance.getDeptCode());
        ps.setObject(4,balance.getDeptName());
        ps.setObject(5,balance.getPersonalCode());
        ps.setObject(6,balance.getPersonalName());
        ps.setObject(7,balance.getStartAmount());
        ps.setObject(8,balance.getStartDirection());
        ps.setObject(9,balance.getEndAmount());
        ps.setObject(10,balance.getEndDirection());
        ps.setObject(11,balance.getCate());
        ps.setObject(12,balance.getBalanceType());
        ps.setObject(13,balance.getAmount());


        ps.executeUpdate();
      //  getConnect().commit();
    }

    public static void insertEx(ExpensesSummary expensesSummary)throws SQLException{
        String sql = "INSERT INTO `big_brain`.`expenses_summary`(`id`, `month`, `item`, `type`, `amount`) " +
                "VALUES (?, ?, ?, ?, ?);";

        PreparedStatement  ps = getConnect().prepareStatement(sql.toString());
        ps.setObject(1,expensesSummary.getId());
        ps.setObject(2,expensesSummary.getMonth());
        ps.setObject(3,expensesSummary.getItem());
        ps.setObject(4,expensesSummary.getType());
        ps.setObject(5,expensesSummary.getAmount());
        ps.executeUpdate();
    }


    public static void insertIn(InventoryStatistics inventoryStatistics)throws SQLException{
        String sql = "INSERT INTO `big_brain`.`inventory_statistics`(`id`, `month`, `inventory_type`, `item_type`, `item`, `amount`) " +
                "VALUES (?, ?, ?, ?, ?,?);";

        PreparedStatement  ps = getConnect().prepareStatement(sql.toString());
        ps.setObject(1,inventoryStatistics.getId());
        ps.setObject(2,inventoryStatistics.getMonth());
        ps.setObject(3,inventoryStatistics.getInventoryType());
        ps.setObject(4,inventoryStatistics.getItem_type());
        ps.setObject(5,inventoryStatistics.getItem());
        ps.setObject(6,inventoryStatistics.getAmount());
        ps.executeUpdate();
    }



    public static void insertPay()throws Exception {
        String file = "F:\\2023年6月份其他应付款余额表.xls";
        Predicate<Row> predicate = row -> row.getRowNum() == 1|| row.getRowNum() ==0||row.getRowNum() ==2;
        parseExcel(file,1,predicate);
    }

    public static  void insertIncome()throws Exception {
        String file = "F:\\2023年6月份其他应收款余额表.xls";
        Predicate<Row> predicate = row -> row.getRowNum() == 1|| row.getRowNum() ==0||row.getRowNum() ==2;
        parseExcel(file,2,predicate);
    }

    public static void insertFee()throws Exception {
        String file ="F:\\销售、财务费用汇总表.xlsx";
        Predicate<Row> predicate = row -> row.getRowNum() == 1|| row.getRowNum() ==0||StringUtils.equalsIgnoreCase(row.getCell(0).getStringCellValue(),"费用比率");
        parseExcel(file,0,predicate);
    }

    public static void insertInventory()throws Exception{
        String file = "F:\\2023年6月份存货统计表.XLS";
        Predicate<Row> predicate = row -> row.getRowNum() == 1|| row.getRowNum() ==0||row.getRowNum()==2|| (row.getRowNum()+2) % 4 ==0;
        parseExcel(file,0,predicate);
    }


    public static void parseExcel(String file, int balanceType, Predicate<Row> predicate) throws Exception {
        Iterator<Sheet> sheetIter = ExcelUtils.getSheetIterator(file);
        List<Balance> result = Lists.newArrayList();
        String group = null;
        while (sheetIter.hasNext()){
           Sheet sheet =  sheetIter.next();
           String sheetName = sheet.getSheetName();
           String type = StringUtils.substringBetween(sheetName,"（","）");
           log.info("sheet:{}行数：{}",sheetName,sheet.getPhysicalNumberOfRows());
           Iterator<Row> rowIterator = sheet.rowIterator();
           String month = null;
           while (rowIterator.hasNext()){
              Row row =  rowIterator.next();
               log.info("rowNUm:{},cellNum:{}",row.getRowNum(),row.getLastCellNum());
               if (row.getRowNum() == sheet.getLastRowNum()-1 ||row.getCell(0)==null){
                   break;
               }
              if (predicate.test(row)){
                  continue;
              }

              String groupStr = row.getCell(0).getStringCellValue();
              if (StringUtils.isNotEmpty(groupStr)){
                  month = groupStr;
              }

//              toInventory(row,month);

//               List<ExpensesSummary> summaries= toExpensesSummary(row,type);
              if (StringUtils.isNotBlank(groupStr)){
                  group = groupStr;
              }
              List<Balance>   balances =    convertAndInsert(row,group,balanceType);
              result.addAll(balances);
           }
           log.info("解析{}完成",sheet.getSheetName());
        }
    }

    private static List<InventoryStatistics> toInventory(Row row,String month){
        Iterator<Cell> cellIterator =  row.cellIterator();

        if (StringUtils.equalsIgnoreCase(month,"1月")){
            month ="2023-01";
        }else if(StringUtils.equalsIgnoreCase(month,"2月")){
            month ="2023-02";
        }else if(StringUtils.equalsIgnoreCase(month,"3月")){
            month ="2023-03";
        } else if(StringUtils.equalsIgnoreCase(month,"4月")){
            month ="2023-04";
        }else if(StringUtils.equalsIgnoreCase(month,"5月")){
            month ="2023-05";
        }else if(StringUtils.equalsIgnoreCase(month,"6月")){
            month ="2023-06";
        }
        List<InventoryStatistics> inventoryStatisticsList = Lists.newArrayList();
        InventoryStatistics inventoryStatistics = new InventoryStatistics(YitIdHelper.nextId()+"",month,"原材料","材料");
        InventoryStatistics inventoryStatistics1 = new InventoryStatistics(YitIdHelper.nextId()+"",month,"原材料","外购半成品");
        InventoryStatistics inventoryStatistics2 = new InventoryStatistics(YitIdHelper.nextId()+"",month,"原材料","批次仓");
        InventoryStatistics inventoryStatistics3 = new InventoryStatistics(YitIdHelper.nextId()+"",month,"办公用品","办公用品");
        InventoryStatistics inventoryStatistics4 = new InventoryStatistics(YitIdHelper.nextId()+"",month,"易耗品","易耗品");
        InventoryStatistics inventoryStatistics5 = new InventoryStatistics(YitIdHelper.nextId()+"",month,"协作件备货","协作件备货");
        InventoryStatistics inventoryStatistics6 = new InventoryStatistics(YitIdHelper.nextId()+"",month,"成品","成品");
        InventoryStatistics inventoryStatistics7 = new InventoryStatistics(YitIdHelper.nextId()+"",month,"在制生产成本","在制生产成本");
        InventoryStatistics inventoryStatistics8 = new InventoryStatistics(YitIdHelper.nextId()+"",month,"委外加工物资","委外加工物资");
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            int index = cell.getColumnIndex();
            if (index == 1) {
                inventoryStatistics.setInventoryType(cell.getStringCellValue());
                inventoryStatistics1.setInventoryType(cell.getStringCellValue());
                inventoryStatistics2.setInventoryType(cell.getStringCellValue());
                inventoryStatistics3.setInventoryType(cell.getStringCellValue());
                inventoryStatistics4.setInventoryType(cell.getStringCellValue());
                inventoryStatistics5.setInventoryType(cell.getStringCellValue());
                inventoryStatistics6.setInventoryType(cell.getStringCellValue());
                inventoryStatistics7.setInventoryType(cell.getStringCellValue());
                inventoryStatistics8.setInventoryType(cell.getStringCellValue());

            }
            if (index ==2){
                inventoryStatistics.setAmount(cell.getNumericCellValue());
            }
            if (index ==3){
                inventoryStatistics1.setAmount(cell.getNumericCellValue());
            }
            if (index ==4){
                inventoryStatistics2.setAmount(cell.getNumericCellValue());
            }
            if (index ==5){
                inventoryStatistics3.setAmount(cell.getNumericCellValue());
            }
            if (index ==6){
                inventoryStatistics4.setAmount(cell.getNumericCellValue());
            }
            if (index ==7){
                inventoryStatistics5.setAmount(cell.getNumericCellValue());
            }
            if (index ==8){
                inventoryStatistics6.setAmount(cell.getNumericCellValue());
            }
            if (index ==10){
                inventoryStatistics7.setAmount(cell.getNumericCellValue());
            }
            if (index == 11){
                inventoryStatistics8.setAmount(cell.getNumericCellValue());
            }
        }

        inventoryStatisticsList.add(inventoryStatistics);
        inventoryStatisticsList.add(inventoryStatistics1);
        inventoryStatisticsList.add(inventoryStatistics2);
        inventoryStatisticsList.add(inventoryStatistics3);
        inventoryStatisticsList.add(inventoryStatistics4);
        inventoryStatisticsList.add(inventoryStatistics5);
        inventoryStatisticsList.add(inventoryStatistics6);
        inventoryStatisticsList.add(inventoryStatistics7);
        inventoryStatisticsList.add(inventoryStatistics8);

        inventoryStatisticsList.stream().forEach(inventoryStatistic -> {
            try {
                insertIn(inventoryStatistic);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return inventoryStatisticsList;

    }

    private static List<ExpensesSummary> toExpensesSummary(Row row,String type){
        Iterator<Cell> cellIterator =  row.cellIterator();
        List<ExpensesSummary> expensesSummaries = Lists.newArrayList();
        String project = row.getCell(0).getStringCellValue();
        if (StringUtils.contains(project,"编制部门")||StringUtils.isEmpty(project)){
            return expensesSummaries;
        }
        ExpensesSummary expensesSummary1 = new ExpensesSummary(YitIdHelper.nextId()+"",project,type);
        ExpensesSummary expensesSummary2 = new ExpensesSummary(YitIdHelper.nextId()+"",project,type);
        ExpensesSummary expensesSummary3 = new ExpensesSummary(YitIdHelper.nextId()+"",project,type);
        ExpensesSummary expensesSummary4 = new ExpensesSummary(YitIdHelper.nextId()+"",project,type);
        ExpensesSummary expensesSummary5 = new ExpensesSummary(YitIdHelper.nextId()+"",project,type);
        ExpensesSummary expensesSummary6 = new ExpensesSummary(YitIdHelper.nextId()+"",project,type);

        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            int index = cell.getColumnIndex();
            if (index == 1){
                expensesSummary1.setMonth("2023-01");
                expensesSummary1.setAmount(cell.getNumericCellValue());
            }
            if (index ==2){
                expensesSummary2.setMonth("2023-02");
                expensesSummary2.setAmount(cell.getNumericCellValue());
            }
            if (index ==3){
                expensesSummary3.setMonth("2023-03");
                expensesSummary3.setAmount(cell.getNumericCellValue());
            }

            if (index == 4){
                expensesSummary4.setMonth("2023-04");
                expensesSummary4.setAmount(cell.getNumericCellValue());
            }
            if (index ==5){
                expensesSummary5.setMonth("2023-05");
                expensesSummary5.setAmount(cell.getNumericCellValue());
            }
            if (index ==6){
                expensesSummary6.setMonth("2023-06");
                expensesSummary6.setAmount(cell.getNumericCellValue());
            }

        }

        expensesSummaries.add(expensesSummary1);
        expensesSummaries.add(expensesSummary2);
        expensesSummaries.add(expensesSummary3);
        expensesSummaries.add(expensesSummary4);
        expensesSummaries.add(expensesSummary5);
        expensesSummaries.add(expensesSummary6);

        expensesSummaries.stream().forEach(expensesSummary -> {
            try {
                insertEx(expensesSummary);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return expensesSummaries;

    }

    private static List<Balance> convertAndInsert(Row row, String cate,int balanceType){
        Iterator<Cell> cellIterator =  row.cellIterator();
        List<Balance> balances = Lists.newArrayList();
        Balance balanceInit = new Balance();
        balanceInit.setId(YitIdHelper.nextId()+"");
        balanceInit.setCate(cate);
        Balance balance1 = new Balance();
        balance1.setId(YitIdHelper.nextId()+"");
        balance1.setCate(cate);
        Balance balance2 = new Balance();
        balance2.setId(YitIdHelper.nextId()+"");
        balance2.setCate(cate);
        Balance balance3 = new Balance();
        balance3.setId(YitIdHelper.nextId()+"");
        balance3.setCate(cate);
        Balance balance4 = new Balance();
        balance4.setId(YitIdHelper.nextId()+"");
        balance4.setCate(cate);
        while (cellIterator.hasNext()){
            Cell cell = cellIterator.next();
            int index =  cell.getColumnIndex();

            if (index == 1){
                balanceInit.setDeptCode( cell.getStringCellValue());
            }
            if (index == 2) {
                balanceInit.setDeptName(cell.getStringCellValue());
            }
            if (index == 3){
                balanceInit.setPersonalCode(cell.getStringCellValue());
            }

            if (index == 4){
                balanceInit.setPersonalName( cell.getStringCellValue());
            }
            if (index == 5) {
                balanceInit.setEndDirection(cell.getStringCellValue());
            }
            if (index == 6){
                balanceInit.setEndAmount(cell.getNumericCellValue());
            }
            balanceInit.setBalanceType(balanceType);

            balance1.setDeptName(balanceInit.getDeptName());
            balance1.setDeptCode(balanceInit.getDeptCode());
            balance1.setPersonalName(balanceInit.getPersonalName());
            balance1.setPersonalCode(balanceInit.getPersonalCode());
            balance1.setCate(balanceInit.getCate());
            balance1.setStartDirection(balanceInit.getEndDirection());
            balance1.setStartAmount(balanceInit.getEndAmount());
            balance1.setMonth("2023-03");
            balance1.setBalanceType(balanceType);

            if (index == 7) {
                balance1.setEndDirection(cell.getStringCellValue());
            }
            if (index == 8){
                balance1.setEndAmount(cell.getNumericCellValue());
            }


            balance2.setDeptName(balanceInit.getDeptName());
            balance2.setDeptCode(balanceInit.getDeptCode());
            balance2.setPersonalName(balanceInit.getPersonalName());
            balance2.setPersonalCode(balanceInit.getPersonalCode());
            balance2.setStartDirection(balance1.getEndDirection());
            balance2.setStartAmount(balance1.getEndAmount());
            balance2.setCate(balance1.getCate());
            balance2.setMonth("2023-04");
        //    balance2.setCate("应付员工款");
            balance2.setBalanceType(balanceType);


            if (index == 9){
                balance2.setEndDirection(cell.getStringCellValue());
            }
            if (index == 10) {
                balance2.setEndAmount(cell.getNumericCellValue());
            }


            balance3.setDeptName(balanceInit.getDeptName());
            balance3.setDeptCode(balanceInit.getDeptCode());
            balance3.setPersonalName(balanceInit.getPersonalName());
            balance3.setPersonalCode(balanceInit.getPersonalCode());
            balance3.setStartDirection(balance2.getEndDirection());
            balance3.setStartAmount(balance2.getEndAmount());
            balance3.setCate(balance2.getCate());
        //    balance3.setCate("应付员工款");
            balance3.setMonth("2023-05");
            balance3.setBalanceType(balanceType);
            if (index == 11) {
                balance3.setEndDirection(cell.getStringCellValue());
            }
            if (index == 12) {
                balance3.setEndAmount(cell.getNumericCellValue());
            }


            balance4.setDeptName(balanceInit.getDeptName());
            balance4.setDeptCode(balanceInit.getDeptCode());
            balance4.setPersonalName(balanceInit.getPersonalName());
            balance4.setPersonalCode(balanceInit.getPersonalCode());
            balance4.setStartDirection(balance3.getEndDirection());
            balance4.setStartAmount(balance3.getEndAmount());
            balance4.setCate(balance3.getCate());
            balance4.setMonth("2023-06");
        //    balance4.setCate("应付员工款");
            balance4.setBalanceType(balanceType);
            if (index == 13) {
                balance4.setEndDirection(cell.getStringCellValue());
            }
            if (index == 14) {
                balance4.setEndAmount(cell.getNumericCellValue());
            }

        }
        balances.add(balanceInit);
        balances.add(balance1);
        balances.add(balance2);
        balances.add(balance3);
        balances.add(balance4);

        balances.stream().forEach(balance -> {
            try {
                insertBalance(balance);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return balances;
    }

    public static void main(String[] args) throws Exception {
     //  insertPay();
      // insertIncome();
      //  insertFee();

     //   insertInventory();
    }



}
