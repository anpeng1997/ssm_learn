# spring mvc中的拦截器

* 拦截器与过滤器的区别：

    1. 拦截器只拦截controller中的方法，而过滤器什么都可以拦截

    2. 过滤器是java web中的一种技术，只要是java web项目可以使用，而拦截器是spring mvc中的一种组件，只有在spring mvc框架中才有。

    3. 两者都是APO思想的实现

## 使用拦截器的方式

1. 编写自定义类实现的HandlerInterceptor

    ```java
    public class MyInterceptor implements HandlerInterceptor {

        //preHandle方法是controller方法执行前拦截的方法
        // 1. 可以使用request或者response跳转到指定的页面
        // 2. return true放行，执行下一个拦截器，如果没有拦截器，执行controller中的方法。
        // 3. return false不放行，不会执行controller中的方法。
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            System.out.println("preHandle......");
            return true;
            //如果这里返回false，下面的两个方法也不会执行了。
        }

        //postHandle是controller方法执行后执行的方法，在JSP视图执行前
        //1. 可以使用request或者response跳转到指定的页面
        //2. 如果指定了跳转的页面，那么controller方法跳转的页面将不会显示。

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
            System.out.println("postHandle.......");
        }

        //postHandle方法是在JSP执行后执行
        //request或者response不能再跳转页面了
        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
            System.out.println("afterCompletion........");
        }
    }
    ```

2. 在springmvc.xml中配置拦截器

    ```xml
    <mvc:interceptors>
        <mvc:interceptor>
        <!--要拦截的方法-->
        <mvc:mapping path="/home/index"/>
        <!-- 不要拦截的方法-->
        <!-- <mvc:exclude-mapping path=""/>-->
        <!-- 实现了HandelInterceptor的类-->
        <bean class="cn.pengan.interceptor.MyInterceptor"><bean>
        </mvc:interceptor>
    </mvc:interceptors>
    ```
