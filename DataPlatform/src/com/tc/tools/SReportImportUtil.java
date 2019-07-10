package com.tc.tools;

import com.tc.bean.DataBean;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  数据平台一个根据表样导报表的一个工具
 */
// 注意：表格模版中，除了要显示在页面上格子，其他不要编辑
public class SReportImportUtil
{

    private static String id = ""; // 主表id

    private static int rows = 0;// 总多少行，不是索引

    private static int cols = 0;// 总多少列，不是索引

  //  private final static String excelFile = "/Users/ming/Downloads/1104.xlsx";

    private final static String excelFile = "C:\\Users\\tangcheng\\Desktop\\1104表样.xlsx";

    // private final static String excelFile =
    // "/Users/chenchen/Documents/TEMP/人行大集中报表--导入样表_改表头.xls";
    public static void main(String[] args) throws Exception
    {
        System.out.println("=====开始处理！");
        long startTime = System.currentTimeMillis();
        System.out.println("========================================");
        SReportImportUtil t4 = new SReportImportUtil();

        t4.checkFile(excelFile);
        Workbook workbook = t4.getWorkbook(excelFile);
        int sheetSize = workbook.getNumberOfSheets();
        for (int sheetNum = 0; sheetNum < sheetSize; sheetNum++)
        {
            Sheet sheet = workbook.getSheetAt(sheetNum);
            List<DataBean> list1 = t4.readExcel(sheet, workbook);
            id = SReportImportUtil.getUUID();
            DataBean db1 = new DataBean();
            db1.setStr1(id); // 主键
            db1.setStr2(sheet.getSheetName()); // 表名称
            t4.insertData(db1, list1);
        }

        System.out.println("========================================");
        long endTime = System.currentTimeMillis();
        System.out.println("=====处理完成！");
        float excTime = (float) (endTime - startTime) / 1000;
        System.out.println("=====共耗时：" + (int) excTime + " 秒");
    }

    private void checkFile(String filePath) throws Exception
    {
        File file = new File(filePath);
        if (!file.exists())
        {
            throw new FileNotFoundException("文件不存在" + filePath);
        }
        if (!(filePath.endsWith("xls") || filePath.endsWith("xlsx")))
        {
            throw new FileFormatException("非excel文件");
        }
    }

    private String getCellValue(Workbook workbook, Cell cell)
    {
        if (cell != null)
        {
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            switch (evaluator.evaluateInCell(cell).getCellType()) {
            case Cell.CELL_TYPE_BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case Cell.CELL_TYPE_NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case Cell.CELL_TYPE_STRING:
                return String.valueOf(cell.getStringCellValue());
            case Cell.CELL_TYPE_ERROR:
                return String.valueOf(cell.getErrorCellValue());
            case Cell.CELL_TYPE_BLANK:
                return String.valueOf(cell.getStringCellValue());
            case Cell.CELL_TYPE_FORMULA:
                return String.valueOf(cell.getStringCellValue());
            }
        }
        return "";
    }

    public static String getUUID()
    {
        String str = UUID.randomUUID().toString();
        StringBuffer sbr = new StringBuffer();
        sbr.append(str.substring(0, 8));
        sbr.append(str.substring(9, 13));
        sbr.append(str.substring(14, 18));
        sbr.append(str.substring(19, 23));
        sbr.append(str.substring(24));
        return sbr.toString();
    }

    private List<DataBean> readExcel(Sheet sheet, Workbook workbook) throws Exception
    {
        List<DataBean> list = new ArrayList<DataBean>();
        try
        {

            List<DataBean> list1 = new ArrayList<DataBean>();
            for (int i = 0; i < sheet.getNumMergedRegions(); i++)
            {
                CellRangeAddress cra = sheet.getMergedRegion(i);
                int firstRow = cra.getFirstRow(); // 开始行
                int lastRow = cra.getLastRow(); // 结束行
                int firstCol = cra.getFirstColumn(); // 开始列
                int lastCol = cra.getLastColumn(); // 结束列

                DataBean db1 = new DataBean();
                db1.setStr1(String.valueOf(firstRow));
                db1.setStr2(String.valueOf(lastRow));
                db1.setStr3(String.valueOf(firstCol));
                db1.setStr4(String.valueOf(lastCol));
                list1.add(db1);
            }

            rows = sheet.getLastRowNum() + 1;
            for (int n = 0; n <= sheet.getLastRowNum(); n++)
            {
                Row currentRow = sheet.getRow(n);
//                System.out.println(sheet.getSheetName());
//                System.out.println("rows:"+n);
//                System.out.println(currentRow);
                if (currentRow.getLastCellNum() > cols)
                {
                    cols = currentRow.getLastCellNum();
                }
                for (int m = 0; m < currentRow.getLastCellNum(); m++)
                {
                    Cell currentCol = currentRow.getCell(m);

                    boolean flag = false;
                    for (int i = 0; i < sheet.getNumMergedRegions(); i++)
                    {
                        CellRangeAddress cra = sheet.getMergedRegion(i);
                        int firstRow = cra.getFirstRow(); // 开始行
                        int lastRow = cra.getLastRow(); // 结束行
                        int firstCol = cra.getFirstColumn(); // 开始列
                        int lastCol = cra.getLastColumn(); // 结束列
                        if (n == firstRow && m == firstCol)
                        {
                            if (firstRow == lastRow && firstCol != lastCol)
                            {
                                DataBean db1 = new DataBean();
                                db1.setStr1("列合并");
                                db1.setStr2(String.valueOf(n));// 行坐标
                                db1.setStr3(String.valueOf(m));// 列坐标
                                db1.setStr4(String.valueOf(lastCol - firstCol + 1));// colspan
                                db1.setStr5(String.valueOf("1"));// rowspan
                                db1.setStr6("HEADER_CONSTANT");
                                db1.setStr7("200");
                                db1.setStr8("30");
                                db1.setStr9(this.getCellValue(workbook, currentCol));
                                list.add(db1);
                            }
                            else if (firstRow != lastRow && firstCol == lastCol)
                            {
                                DataBean db1 = new DataBean();
                                db1.setStr1("行合并");
                                db1.setStr2(String.valueOf(n));// 行坐标
                                db1.setStr3(String.valueOf(m));// 列坐标
                                db1.setStr4(String.valueOf("1"));// colspan
                                db1.setStr5(String.valueOf(lastRow - firstRow + 1));// rowspan
                                db1.setStr6("HEADER_CONSTANT");
                                db1.setStr7("200");
                                db1.setStr8("30");
                                db1.setStr9(this.getCellValue(workbook, currentCol));
                                list.add(db1);
                            }
                            else if (firstRow != lastRow && firstCol != lastCol)
                            {
                                DataBean db1 = new DataBean();
                                db1.setStr1("行列合并");
                                db1.setStr2(String.valueOf(n));// 行坐标
                                db1.setStr3(String.valueOf(m));// 列坐标
                                db1.setStr4(String.valueOf(lastCol - firstCol + 1));// colspan
                                db1.setStr5(String.valueOf(lastRow - firstRow + 1));// rowspan
                                db1.setStr6("HEADER_CONSTANT");
                                db1.setStr7("200");
                                db1.setStr8("30");
                                db1.setStr9(this.getCellValue(workbook, currentCol));
                                list.add(db1);
                            }
                            else
                            {
                                System.out.println("===================");
                            }
                        }
                        if (!(n < firstRow || n > lastRow) && !(m < firstCol || m > lastCol))
                        {
                            flag = true;
                        }
                    }

                    if (!flag)
                    {
                        DataBean db1 = new DataBean();
                        db1.setStr1("不合并");
                        db1.setStr2(String.valueOf(n));// 行坐标
                        db1.setStr3(String.valueOf(m));// 列坐标
                        db1.setStr4(String.valueOf("1"));// colspan
                        db1.setStr5(String.valueOf("1"));// rowspan
                        db1.setStr6("HEADER_CONSTANT");
                        db1.setStr7("200");
                        db1.setStr8("30");
                        db1.setStr9(this.getCellValue(workbook, currentCol));
                        list.add(db1);
                    }
                }
            }

            for (int i = 0; i < list.size(); i++)
            {
                DataBean db = list.get(i);
                db.setStr10(String.valueOf(i));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
        return list;
    }

    private Workbook getWorkbook(String filePath) throws Exception
    {
        Workbook workbook = null;
        InputStream is = new FileInputStream(filePath);
        if (filePath.endsWith("xls"))
        {
            workbook = new HSSFWorkbook(is);
        }
        else if (filePath.endsWith("xlsx"))
        {
            workbook = new XSSFWorkbook(is);
        }
        return workbook;
    }

    private void insertData(DataBean db1, List<DataBean> list)
    {
//        Pattern idxPartten = Pattern.compile("(F[a-zA-Z0-9]+)\\([a-zA-Z0-9_]+\\.[a-zA-Z0-9]+\\)", Pattern.CASE_INSENSITIVE);
        Pattern idxPartten = Pattern.compile("[a-zA-Z0-9]\\d*", Pattern.CASE_INSENSITIVE);

        Connection conn = null;
        Statement stmt = null;
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.20.38:1521:kfdb", "xizang", "111111");
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            String rows_str = "";
            String cols_str = "";
            for (int i = 0; i < rows; i++)
            {
                rows_str += "30,";
            }
            for (int i = 0; i < cols; i++)
            {
                cols_str += "200,";
            }
            rows_str = rows_str.substring(0, rows_str.lastIndexOf(","));
            cols_str = cols_str.substring(0, cols_str.lastIndexOf(","));

            StringBuffer sbr1 = new StringBuffer();
            sbr1.append("insert into t_sr_report(id, name, type, sys_no, width, date_level, ent_no, colwidths, rowheights, created_dt, created_by, status, remark) values (");
            sbr1.append("'").append(db1.getStr1()).append("',");
            sbr1.append("'").append(db1.getStr2()).append("',");
            sbr1.append("'FIX_SREPORT',");
            sbr1.append("'M111',");
            sbr1.append("'DAY',");
            sbr1.append("'日',");
            sbr1.append("'M0150103006',");
            sbr1.append("'[").append(cols_str).append("]',");
            sbr1.append("'[").append(rows_str).append("]',");
            sbr1.append("sysdate,");
            sbr1.append("'69AEEEA16A8F4B4CBE1F61435F70EBD4',");
            sbr1.append("'NEW','自动生成')");
            stmt.execute(sbr1.toString());
            System.out.println("-------------" + sbr1);

            StringBuffer sbr2 = new StringBuffer();
            sbr2.append("insert into T_SYS_TAG_REL(buss_id, sub_tag_id, tag_id, buss_type, id) values (");
            sbr2.append("'").append(db1.getStr1()).append("',");
            sbr2.append("'").append("2BC7480D53004D4CAE908B226BACA9C2").append("',");
            sbr2.append("'").append("2E63E4B74DEC45F9A5DCAA551588B3BC").append("',");
            sbr2.append("'").append("SREPORT").append("',");
            sbr2.append("'").append(SReportImportUtil.getUUID()).append("')");
            stmt.execute(sbr2.toString());
            System.out.println("-------------" + sbr2);

            StringBuffer sbr3 = new StringBuffer();
            sbr3.append("insert into T_SR_REPORT_CONDITION(ID, NAME, SREPORT_ID, DIM_CODE) values (");
            sbr3.append("'").append(SReportImportUtil.getUUID()).append("',");
            sbr3.append("'机构',");
            sbr3.append("'").append(db1.getStr1()).append("',");
            sbr3.append("'DIM_ORG'");
            sbr3.append(")");
            System.out.println("-------------" + sbr3);
            stmt.execute(sbr3.toString());

            StringBuffer sbr4 = new StringBuffer();
            sbr4.append("insert into T_SR_REPORT_CONDITION(ID, NAME, SREPORT_ID, DIM_CODE) values (");
            sbr4.append("'").append(SReportImportUtil.getUUID()).append("',");
            sbr4.append("'日期',");
            sbr4.append("'").append(db1.getStr1()).append("',");
            sbr4.append("'DIM_DATE'");
            sbr4.append(")");
            System.out.println("-------------" + sbr4);
            stmt.execute(sbr4.toString());

            StringBuffer sbr5 = new StringBuffer();
            sbr5.append("insert into T_SR_REPORT_CONDITION(ID, NAME, SREPORT_ID, DIM_CODE) values (");
            sbr5.append("'").append(SReportImportUtil.getUUID()).append("',");
            sbr5.append("'币种',");
            sbr5.append("'").append(db1.getStr1()).append("',");
            sbr5.append("'G010018'");
            sbr5.append(")");
            System.out.println("-------------" + sbr5);
            stmt.execute(sbr5.toString());

            if (null != list && !list.isEmpty())
            {
                for (int i = 0; i < list.size(); i++)
                {
                    // 指标处理 start
                    DataBean db = list.get(i);
                    String content = db.getStr9();
                    Matcher matcher = idxPartten.matcher(content);
                    if(matcher.matches()) {
                        db.setStr9(content+".13");
                        db.setStr6("INDICATOR");
                    }
                    // 指标处理 end

                    StringBuffer sbr = new StringBuffer();
                    sbr.append("insert into t_sr_report_column (id,sreport_id,content,c_type,");
                    sbr.append("r_row,r_col,rowspan,colspan,width,height,sort) values(");
                    sbr.append("'").append(SReportImportUtil.getUUID()).append("',");// id
                    sbr.append("'").append(db1.getStr1()).append("',");// seport_id
                    sbr.append("'").append(db.getStr9()).append("',");// content
                    sbr.append("'").append(db.getStr6()).append("',");// c_type
                    sbr.append("'").append(db.getStr2()).append("',");// r_row
                    sbr.append("'").append(db.getStr3()).append("',");// r_col
                    sbr.append("'").append(db.getStr5()).append("',");// rowspan
                    sbr.append("'").append(db.getStr4()).append("',");// colspan
                    sbr.append("'").append(db.getStr7()).append("',");// width
                    sbr.append("'").append(db.getStr8()).append("',");// height
                    sbr.append("'").append(db.getStr10()).append("')");// sort
                    System.out.println("-------------" + sbr);
                    stmt.execute(sbr.toString());
                }
            }

            conn.commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            try
            {
                conn.rollback();
            }
            catch (SQLException e1)
            {
                e1.printStackTrace();
            }
        }
        finally
        {
            try
            {
                conn.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            try
            {
                stmt.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

}
