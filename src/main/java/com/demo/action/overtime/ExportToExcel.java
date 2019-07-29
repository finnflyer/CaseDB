package com.demo.action.overtime;

import com.demo.dao.overtime.OvertimeDao;
import com.demo.service.overtime.OvertimeService;
import com.demo.util.CatoSetting;
import com.demo.util.DBToExcelFile;
import com.demo.util.Generator;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by finnf on 2018/6/23.
 */
@Namespace("/phase4")
@Scope("property")
@ParentPackage("custom-default")
public class ExportToExcel  extends ActionSupport{

    @Autowired
    public OvertimeService overtimeService;

    @Action(value = "ExportAll", results = {@Result(name = "download",
            type = "stream", params = {
            "contentType", "application/octet-stream",
            "inputName", "inputStream", "contentDisposition",
            "attachment;filename=OT.xlsx", "bufferSize",
            "4096"})})
    public String DownloadFile() {

        return "download";
    }

    /**
     * 获取下载流
     * 对应 annotation 注解里面的 "inputName", "inputStream"
     * 假如 annotation 注解改为 "inputName", "myStream"，则下面的方法则应改为：getMyStream
     *
     * @return InputStream
     */
    public InputStream getInputStream() {

        String title = "Issue";
        String[] rowsName = new String[]{" ","部门","用户","剩余年假(小时)",
                "剩余调休（小时）","总假期（小时）","TIL","Payment"};
        List<Object[]>  dataList = new ArrayList<Object[]>();
//
//        Object[] objs = null;
//        for(int i=0;i<list.size();i++){
//
//            objs = new Object[rowsName.length];
//            objs[0]=i;
////            objs[1]=
////            objs[2]=
////            objs[3]=
////            objs[4]=
////            objs[5]=
////            objs[6]=
////            objs[7]=
////            objs[8]=
////            objs[9]=
////            objs[10]=
////
////            objs[11]=;
////            objs[12]=;
//
//            dataList.add(objs);
//        }
//        System.out.println(dataList.size());
        try {
            XSSFWorkbook workbook = DBToExcelFile.dbDataToExcel(title, rowsName, dataList);
            String filePath = "D:\\CTDDataBase\\OT\\"+Generator.generatorID();// 定义一个到处文件名字的生成规则

            File fileDir = new File(filePath);
            if (!fileDir.exists()) {
                fileDir.mkdir();
            }
            String fileName = filePath+ "\\"+ Generator.generatorID()+".xlsx";
            File file = new File(fileName);
            FileOutputStream fs = new FileOutputStream(file);
            workbook.write(fs);

            return new FileInputStream(new File(fileName));
        }catch (Exception e){

        }
        return  null;
    }

    @Action(value = "ExportSelected", results = {@Result(name = "download",
            type = "stream", params = {
            "contentType", "application/octet-stream",
            "inputName", "inputSelectStream", "contentDisposition",
            "attachment;filename=OT.xlsx", "bufferSize",
            "4096"})})
    public String DownloadSelectFile() {

        return "download";
    }
    public InputStream getInputSelectStream() {

        String title = "Issue";
        String[] rowsName = new String[]{" ","部门","用户","剩余年假(小时)",
                "剩余调休（小时）","总假期（小时）","TIL","Payment"};
        List<Object[]>  dataList = new ArrayList<Object[]>();
//
//        Object[] objs = null;
//        for(int i=0;i<list.size();i++){
//
//            objs = new Object[rowsName.length];
//            objs[0]=i;
////            objs[1]=
////            objs[2]=
////            objs[3]=
////            objs[4]=
////            objs[5]=
////            objs[6]=
////            objs[7]=
////            objs[8]=
////            objs[9]=
////            objs[10]=
////
////            objs[11]=;
////            objs[12]=;
//
//            dataList.add(objs);
//        }
//        System.out.println(dataList.size());
        try {
            XSSFWorkbook workbook = DBToExcelFile.dbDataToExcel(title, rowsName, dataList);
            String filePath = "D:\\CTDDataBase\\OT\\"+Generator.generatorID();// 定义一个到处文件名字的生成规则

            File fileDir = new File(filePath);
            if (!fileDir.exists()) {
                fileDir.mkdir();
            }
            String fileName = filePath+ "\\"+ Generator.generatorID()+".xlsx";
            File file = new File(fileName);
            FileOutputStream fs = new FileOutputStream(file);
            workbook.write(fs);

            return new FileInputStream(new File(fileName));
        }catch (Exception e){

        }
        return  null;
    }

}
