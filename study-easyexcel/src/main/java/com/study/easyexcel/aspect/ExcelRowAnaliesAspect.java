package com.study.easyexcel.aspect;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.study.easyexcel.annotation.ExcelAnalyze;
import com.study.util.StringExpandUtil;
import com.study.util.ValidateExpandUtil;

/**
 * @Author x.zaft
 * @Date 2020/11/30
 * @Version v1
 **/
@Aspect
@Component
public class ExcelRowAnaliesAspect<T, E> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /*@Autowired
    private RedisUtil redisUtil;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ServiceCityLogic serviceCityLogic;

    SystemHelper systemHelper = null;*/


    /** 把切面的连接点放在了我们的注解上 */
    @Pointcut("@annotation(com.study.easyexcel.annotation.ExcelAnalyze)")
    public void point() {
    }


    /** 在这里定义前置切面 */
    @After("point()")
    public void before(JoinPoint joinPoint) {
        /**得到被切方法的参数*/
        Object[] args = joinPoint.getArgs();
        T entity = (T) args[0];
        String msg = checkParam(entity, (E) args[1]);
        if(StringUtils.isEmpty(msg)){
            msg = beanValidate(entity);
        }
        this.setError(entity, msg);
    }

    private void setError(T entity, String msg){
        if (null != msg && !msg.trim().equals("")) {
            try {
                Method error = entity.getClass().getSuperclass().getDeclaredMethod("error", String.class);
                error.invoke(entity, msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String checkParam(T t, E e) {
        Field[] declaredFields = e.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            try {
                String oe = String.valueOf(declaredField.get(e));
                Field declaredFieldt = t.getClass().getDeclaredField(declaredField.getName());
                declaredFieldt.setAccessible(true);
                if (!oe.equals(String.valueOf(declaredFieldt.get(t)))) {
                    ExcelAnalyze excelAnalyze = t.getClass().getAnnotation(ExcelAnalyze.class);
                    return excelAnalyze.message() + StringExpandUtil.DESC_ERROR;
                }
            } catch (NoSuchFieldException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }

        }
        return "";
    }

    /** bean */
    private String beanValidate(T entity) {
        for (Field declaredField : entity.getClass().getDeclaredFields()) {
            ExcelAnalyze excelAnalyze = declaredField.getAnnotation(ExcelAnalyze.class);
            if (null == excelAnalyze) {
                continue;
            }
            try {
                String reuslt = annoValidate(entity, declaredField, excelAnalyze);
                if (StringUtils.isEmpty(reuslt)) {
                    continue;
                }
                return reuslt;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return "";
    }

    private String annoValidate(T entity, Field declaredField, ExcelAnalyze excelAnalyze) throws Exception {
        declaredField.setAccessible(true);
        Object o = declaredField.get(entity);
        /**非空*/
        if (excelAnalyze.nonEmpty() && null == o) {
            return excelAnalyze.message() + StringExpandUtil.DESC_NOT_EMPTY;
        }
        /**格式*/
        if (excelAnalyze.formatCategory() != -1 && !formatValidate(entity, declaredField, excelAnalyze.format(),
                excelAnalyze.formatCategory())) {

            return excelAnalyze.message() + this.getErrorMsg(excelAnalyze.formatCategory());

        }


        return "";
    }

    private String getErrorMsg(int type) {
        switch (type) {
            case 5:
                return StringExpandUtil.DESC_NOT_EXIST;
            case 7:
                return StringExpandUtil.DESC_ERROR;
            default:
                return StringExpandUtil.DESC_ERROR_FORMAT;
        }
    }

    private boolean formatValidate(T entity, Field declaredField, String format, int type) throws Exception {

        Object o = declaredField.get(entity);
        if (null == o) {
            return true;
        }
        String value = String.valueOf(o);
        if (StringUtils.isEmpty(value.trim())) {
            return true;
        }
        boolean result;
        switch (type) {
            /**日期*/
            case 0:
                result = ValidateExpandUtil.valDate(format, value);
                break;
            /**地址*/
            case 1:
                result = ValidateExpandUtil.valAddress(format, value);
                break;
            /**邮箱*/
            case 2:
                result = ValidateExpandUtil.valEmail(value);
                break;
            /**手机号*/
            case 3:
                result = ValidateExpandUtil.valPhone(value);
                break;
            /**数字格式*/
            case 4:
                result = ValidateExpandUtil.valNumber(format, value);
                break;
            /**字典项*/
            case 5:
                result = this.checkDict(entity, declaredField, value, format);
                break;
            /**国籍*/
            case 6:
                result = this.checkNative(entity, declaredField, value);
                break;
            /**城市*/
            case 7:
                result = this.checkCity(entity, declaredField, value);
                break;
            /**民族*/
            case 8:
                result = this.checkEthnic(entity, declaredField, value);
                break;
            /**身份证*/
            case 9:
                result = ValidateExpandUtil.valIDCard(value);
                break;
            case 10:
                result = this.checkBank(entity, declaredField, value);
                break;
            /**参保地*/
            case 99:
                result = this.checkInsuredAddress(entity, declaredField, value);
                break;
            default:
                result = false;
        }
        return result;
    }

    private boolean checkBank(T entity, Field declaredField, String value) {
       /* String bankCode = systemHelper.getBankCode(value);
        if (StringUtils.isEmpty(bankCode)) {
            return false;
        }
        this.setDataMap(entity, declaredField, bankCode);*/
        return true;
    }

    private boolean checkCity(T entity, Field declaredField, String value) {
        String[] insuredAddresses = value.split(StringExpandUtil.V_ENGLISH_COMMA);
        /*String provinceCode = systemHelper.getProvinceCode(insuredAddresses[0]);
        if (StringUtils.isEmpty(provinceCode)) {
            return false;
        }
        if (insuredAddresses.length > 1) {
            String cityCode = systemHelper.getCityCode(provinceCode, insuredAddresses[1]);
            if (StringUtils.isEmpty(cityCode)) {
                return false;
            }
            this.setDataMap(entity, declaredField, new String[]{provinceCode, cityCode, ""});
            String areaCode = "";
            if (insuredAddresses.length - 1 > 1) {
                areaCode = systemHelper.getAreaCode(cityCode, insuredAddresses[2]);
                if (StringUtils.isEmpty(areaCode)) {
                    return false;
                }
                this.setDataMap(entity, declaredField, new String[]{provinceCode, cityCode, areaCode});
            }
        }*/
        return true;
    }

    private boolean checkInsuredAddress(T entity, Field declaredField, String value) {
        /*List<String> serviceCityNames = CodeToAddress.getAddresses(Arrays.asList(value));
        List<ServiceCityEntity> serviceCityList = serviceCityLogic.selectCityByNames(serviceCityNames);
        if (CollectionUtils.isEmpty(serviceCityList) || serviceCityList.size() != serviceCityNames.size()) {
            return false;
        }
        Map<String, String> cityMap = serviceCityList.stream()
                .collect(Collectors.toMap(ServiceCityEntity::getName, ServiceCityEntity::getCode));
        String[] split = value.split(StringExpandUtil.V_ENGLISH_COMMA);
        this.setDataMap(entity, declaredField,
                new String[]{cityMap.get(split[0]), cityMap.get(split[0] + "," + split[1]),
                        split.length > 2 ? cityMap.get(StringExpandUtil.getAscii(value)) : ""});*/
        return true;
    }

    /** 民族 */
    private boolean checkEthnic(T entity, Field declaredField, String value)
            throws NoSuchFieldException, IllegalAccessException {
        /*Field datamap = entity.getClass().getSuperclass().getDeclaredField("dataMap");
        datamap.setAccessible(true);
        Long ethnicId = systemHelper
                .getEthnicId((Long) ((Map<String, Object>) datamap.get(entity)).get(StringExpandUtil.KEY_NATIVE),
                        value);
        if (null == ethnicId) {
            return false;
        }
        this.setDataMap(entity, declaredField, ethnicId);*/
        return true;
    }

    /** 国籍 */
    private boolean checkNative(T entity, Field declaredField, String value) {
        /*Long nationalityId = systemHelper.getNationalityId(value);
        if (null == nationalityId) {
            return false;
        }
        this.setDataMap(entity, declaredField, nationalityId);*/
        return true;
    }

    /** 通用 */
    private boolean checkDict(T entity, Field declaredField, String value, String format) {
        Map<String, Integer> dict = this.getDict(format);
        Integer key = dict.get(value);
        if (null == key || key < -1) {
            return false;
        }
        this.setDataMap(entity, declaredField, key);
        return true;
    }

    private void setDataMap(T entity, Field declaredField, Object value) {
        Field dataMapField = null;
        try {
            dataMapField = entity.getClass().getSuperclass().getDeclaredField("dataMap");
        } catch (NoSuchFieldException e) {
            logger.info("=====解析异常-数据补全=====" + e);
        }
        dataMapField.setAccessible(true);
        Map<String, Object> dataMap = null;
        try {
            dataMap = (Map<String, Object>) dataMapField.get(entity);
        } catch (IllegalAccessException e) {
            logger.info("=====解析异常-数据补全=====" + e);
        }
        if (null == dataMap) {
            dataMap = new HashMap<>(8);
        }
        dataMap.put(declaredField.getName(), value);
        try {
            dataMapField.set(entity, dataMap);
        } catch (IllegalAccessException e) {
            logger.info("=====解析异常-数据补全=====" + e);
            e.printStackTrace();
        }
    }

    /** 字典key value 反转 */
    private Map<String, Integer> getDict(String key) {
        Map<String, Integer> reverse = new HashMap<>(8);
//        Map<Integer, String> dict = redisUtil.getDict(key);
//        dict.forEach((k, v) -> reverse.put(v, k));
        return reverse;
    }

}
