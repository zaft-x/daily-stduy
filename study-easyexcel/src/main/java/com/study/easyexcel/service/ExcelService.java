package com.study.easyexcel.service;

import java.util.List;
import org.springframework.stereotype.Component;
import com.study.easyexcel.annotation.ExcelAnalyze;
import com.study.pojo.base.ImportResult;

/**
 * @Author x.zaft
 * @Date 2020/12/2
 * @Version
 **/
@Component
public class ExcelService<T, E> {
     /**
      * single bean analy
      * @author x.zaft
      * @param t
      * @param e
      * @date 2020/12/10
      * @version : v
      */
    public void analiesSingle(T t,E e){}

     /**
      *
      * @author x.zaft
      * @param rows 解析后行
      * @param e excel外的参数
      * @return
      * @date 2020/12/25
      * @version : v
      */
    public ImportResult deal(List<T> rows, /*JwtParam jwtParam,*/ E e) {
        return null;
    }

}
