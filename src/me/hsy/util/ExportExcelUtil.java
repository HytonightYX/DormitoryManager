package me.hsy.util;

import me.hsy.pojo.Student;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * 生成Excel报表工具类
 *
 * @author HytonightYX
 * @date 2019/1/4 16:04
 */
public class ExportExcelUtil {

    public static void createExcel(List<Student> studentList, String savePath, String fileName) {
        // 创建一个excel文件（工作簿）
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建一个sheet（工作表）
        HSSFSheet sheet = workbook.createSheet("学生入住详情");

        // 添加标题
        HSSFRow titleRow = sheet.createRow(0);
        // 设置标题
        HSSFCell titleCell = titleRow.createCell(4);
        titleCell.setCellValue("学生入住详情 " + LocalDateTimeUtil.getTime());

        // 添加表头
        HSSFRow headRow = sheet.createRow(1);
        HSSFCell idCell = headRow.createCell(0);
        idCell.setCellValue("学号");

        HSSFCell nameCell = headRow.createCell(1);
        nameCell.setCellValue("姓名");

        HSSFCell collegeCell = headRow.createCell(2);
        collegeCell.setCellValue("学院");

        HSSFCell departmentCell = headRow.createCell(3);
        departmentCell.setCellValue("专业");

        HSSFCell classCell = headRow.createCell(4);
        classCell.setCellValue("班级");

        HSSFCell checkedCell = headRow.createCell(5);
        checkedCell.setCellValue("是否入住");

        HSSFCell inTimeCell = headRow.createCell(6);
        inTimeCell.setCellValue("入住时间");

        HSSFCell outTimeCell = headRow.createCell(7);
        outTimeCell.setCellValue("退房时间");

        // 设置入住退房列列宽
        sheet.setColumnWidth(6, 256*20+184);
        sheet.setColumnWidth(7, 256*20+184);

        HSSFRow currRow;
        // 添加数据内容
        for (int i = 0; i < studentList.size(); i++) {
            currRow = sheet.createRow((int) i + 2);
            Student student = studentList.get(i);

            HSSFCell cell = currRow.createCell(0);
            cell.setCellValue(student.getStuId().toString());

            cell = currRow.createCell(1);
            cell.setCellValue(student.getStuName());

            cell = currRow.createCell(2);
            cell.setCellValue(student.getStuCollege());

            cell = currRow.createCell(3);
            cell.setCellValue(student.getStuDepartment());

            cell = currRow.createCell(4);
            cell.setCellValue(student.getStuClass());

            cell = currRow.createCell(5);
            cell.setCellValue(student.getChecked() ? "1" : "0");

            cell = currRow.createCell(6);
            cell.setCellValue(student.getCheckInTime() == null ? "" : student.getCheckInTime().toString());

            cell = currRow.createCell(7);
            cell.setCellValue(student.getCheckOutTime() == null ? "" : student.getCheckOutTime().toString());
        }

        try {
            if (!fileName.endsWith(".xls")) {
                fileName += ".xls";
            }

            OutputStream outputStream = new FileOutputStream(savePath +"/" + fileName);
            workbook.write(outputStream);
            outputStream.close();
            new AlertInfoUtil("提示", "备份完成!").showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
