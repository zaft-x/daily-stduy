package com.study.studythirdparty.easyexcel.simple;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.alibaba.excel.EasyExcel;
import com.study.studythirdparty.easyexcel.DemoData;

/**
 * @ClassName DemoWrite
 * @Description: TODO
 * @Author x.zaft
 * @Date 2020/10/20
 * @Version V1.0
 **/
public class DemoWrite {

    public static void write(){

        String basePath = DemoWrite.class.getResource("/").getPath();

        // 写法1
        String fileName = basePath + "demoWrite" + System.currentTimeMillis() + ".xlsx";
        System.out.println("====file name==="+fileName);
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, DemoData.class).sheet("模板").doWrite(data());

        /*// 写法2
        fileName = basePath + "demoWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(fileName, DemoData.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            excelWriter.write(data(), writeSheet);
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }*/
    }

    private static List<DemoData> data() {
        List<DemoData> list = new ArrayList<DemoData>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            if(i%2==0){
                data.setHook("√");
            }
            list.add(data);
        }
        return list;
    }

    public static void main(String[] args) {
        write();
    }

}
