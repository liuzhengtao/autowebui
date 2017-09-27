package com.yuminsoft.com.autoweb.selenium.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yuminsoft.com.autoweb.base.exception.BasicException;
import com.yuminsoft.com.autoweb.common.util.DateUtil;
import com.yuminsoft.com.autoweb.common.util.StringUtil;
import com.yuminsoft.com.autoweb.selenium.model.SlnmLocator;
import com.yuminsoft.com.autoweb.selenium.model.SlnmPage;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * 
 * FileName:    ExcelUtil.java  
 * @author: YM10095
 * @version:    1.0  
 * @date:   	2017年7月29日 下午10:45:34
 */
public class ExcelUtil {
    
    /**
     * 解析Excel文件为数组对象
     * @param filePath
     * @return
     * @author: YM10095
     * @date:	2017年7月11日 下午1:50:03
     */
    public static Object[][] parseExcelByJxl(String filePath) {
        Object[][] data_object = new Object[1][1];
        //开始的行数
        int start_row = 1;
        //开始的列数
        int start_col = 1;
        try {
            File datafile = new File(filePath);
            if (!datafile.exists()) {
                //throw new BaseException("文件不存在");
                return null;
            }
            FileInputStream fileInputStream = new FileInputStream(datafile);
            Workbook workbook = Workbook.getWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheet(0);
            //总的行数
            int rows = sheet.getRows();
            if(start_row > rows){
                throw new BasicException("开始的行数大于总的行数");
            }
            //总的列数
            int cols = sheet.getColumns();
            if(start_col > cols){
                throw new BasicException("开始的列数大于总的列数");
            }
            List<Object> row_list = new ArrayList<Object>();
            String[][] data_array = new String[rows-start_row][cols - start_col];
            for (int i = start_row; i < rows; i++) {
                String[] col_array = new String[cols - start_col];
                Cell[] cells = sheet.getRow(i);
                for (int j = start_col; j < cells.length; j++) {
                    Cell cell = cells[j];
                    String cell_value = "";
                    if (cell.getType() == CellType.DATE) {
                        DateCell dc = (DateCell) cell;
                        Date date = dc.getDate(); //获取单元格的date类型
                        cell_value = DateUtil.getDateStr(date, "yyyy-MM-dd");
                    } else {
                        cell_value = cell.getContents();
                    }
                    col_array[j-start_col] = cell_value;
                }
                row_list.add(col_array);                
            }
            //
            String[][] data_array_tmp = new String[row_list.size()][cols - start_col];
            data_array_tmp = row_list.toArray(data_array_tmp);
            data_array = data_array_tmp;
            data_object = (Object[][]) data_array;
            //
            fileInputStream.close();
        } catch (Exception e) {
            e.getMessage();
            throw new BasicException("解析文件失败");
        }
        
        return data_object;
    }
    
    /**
     * 
     * @param datafilepath
     * @param datafilename
     * @param locatorlist
     * @author: YM10095
     * @date:	2017年7月13日 下午2:24:17
     */
    public static void createDataExcel(String datafilepath,String datafilename,SlnmPage bean) {
        try {
            if(StringUtil.isNull(datafilepath)){
                throw new BasicException("文件路径为空");
            }
            
            //
            File dirFile = new File(datafilepath);
            if (!dirFile.exists()){
                dirFile.mkdirs();
            }
            //打开文件
            WritableWorkbook workbook = Workbook.createWorkbook(new File(datafilepath + datafilename));

            //生成名为"第一页"的工作表，参数0表示这是第一页
            WritableSheet sheet1 = workbook.createSheet(bean.getPagename(), 0);

            //作用是指定第i+1行的高度，比如将第一行的高度设为200 
            //sheet1.setRowView(1, 2000);
            //作用是指定第i+1列的宽度，比如将第一列的宽度设为30 
            sheet1.setColumnView(0, 40);
            
            //设置单元格的样式
            WritableCellFormat cellFormat = new WritableCellFormat();
            //设置水平居中
            cellFormat.setAlignment(jxl.format.Alignment.CENTRE);
            //设置垂直居中
            cellFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            //设置自动换行
            cellFormat.setWrap(true);
            //设置显示的字体样式，字体，字号，是否粗体，字体颜色 
            cellFormat.setFont(new WritableFont(WritableFont.createFont("宋体"), 13,
                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK));
            //设置单元格背景色
            cellFormat.setBackground(jxl.format.Colour.WHITE);
            //设置边框线为实线(默认是黑色)
            cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN,Colour.GRAY_25);
            
            List<SlnmLocator> locatorlist = bean.getLocatorlist();
            
            //第一行
            int rownum = 0;
            int colnum = 0;
            Label label00 = new Label(colnum, rownum, "用例名称", cellFormat);
            sheet1.addCell(label00);
            colnum++;
            for(SlnmLocator slnmLocator : locatorlist){
                String opttype = slnmLocator.getOpttype();
                //操作类型 点击(Click)|输入(Type)|清空(Clear)
                if("type".equals(opttype) || "keybord_type".equals(opttype) || 
                        "prompt_type".equals(opttype)|| "prompt_type_accept".equals(opttype)){
                    sheet1.setColumnView(colnum, 30);
                    Label label = new Label(colnum, rownum, slnmLocator.getLocatorname(), cellFormat);
                    sheet1.addCell(label);
                    colnum++;
                }
            }
            
            //设置单元格的样式
            cellFormat = new WritableCellFormat();
            //设置水平居左
            cellFormat.setAlignment(jxl.format.Alignment.LEFT);
            //设置垂直居中
            cellFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            //设置自动换行
            cellFormat.setWrap(true);
            //设置显示的字体样式，字体，字号，是否粗体，字体颜色 
            cellFormat.setFont(new WritableFont(WritableFont.createFont("宋体"), 12,
                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK));            
            //设置单元格背景色
            cellFormat.setBackground(jxl.format.Colour.WHITE);
            //设置边框线为实线(默认是黑色)
            cellFormat.setBorder(Border.ALL, BorderLineStyle.THIN,Colour.GRAY_25);
            
            //第二行
            rownum = 1;
            colnum = 0;
            Label label10 = new Label(colnum, rownum, "测试用例名", cellFormat);
            sheet1.addCell(label10);
            colnum++;
            for(SlnmLocator slnmLocator : locatorlist){
                String opttype = slnmLocator.getOpttype();
                //操作类型 点击(Click)|输入(Type)|清空(Clear)
                if("type".equals(opttype) || "keybord_type".equals(opttype) || 
                        "prompt_type".equals(opttype)|| "prompt_type_accept".equals(opttype)){
                    sheet1.setColumnView(colnum, 30);
                    Label label = new Label(colnum, rownum, "", cellFormat);
                    sheet1.addCell(label);
                    colnum++;
                }
            }
            
            //开始执行写入操作
            workbook.write();
            //关闭流  
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BasicException("生成Excel文件异常");
        }
    }
    
}
