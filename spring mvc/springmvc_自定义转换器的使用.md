# 使用自定义类型转换器

1. 创建一个类实现Converter接口

    ```java
        public class MyStringToDataConverter implements Converter<String, Date> {

        @Override
        public Date convert(String s) {
            if (s.isEmpty() || s == null){
                throw new RuntimeException("时间格式字符串为空");
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                return simpleDateFormat.parse(s);
            } catch (ParseException e) {
                e.printStackTrace();
                throw new RuntimeException("时间格式转换错误！");
            }
        }
    }

    ```

2. 在springmvc.xml配置使用自定义的转换器

    ```xml
    <!--    配置自定义转换器-->
    <bean id="serviceFactoryBean" class="org.springframework.context.support.ConversionServiceFactoryBean">
        <property name="converters">
            <set>
                <bean class="cn.pengan.utils.MyStringToDataConverter"></bean>
            </set>
        </property>
    </bean>

    <!--   使用配置的自定义转换器-->
    <mvc:annotation-driven conversion-service="serviceFactoryBean"></mvc:annotation-driven>
    ```
