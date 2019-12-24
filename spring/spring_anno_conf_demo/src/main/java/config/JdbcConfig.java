package config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class JdbcConfig {
    @Value("${url}")
    private String url;
    @Value("${driverClassName}")
    private String driverClassName;
    @Value("${username}")
    private String username;
    @Value("${password}")
    private String password;
    @Value("${maxActive}")
    private Integer maxActive;
    @Value("${maxWait}")
    private Integer maxWait;
    @Value("${initialSize}")
    private Integer initialSize;

    @Bean
    @Scope("prototype")
    public JdbcTemplate createJdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
    @Bean
    public DataSource createDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url);
        druidDataSource.setPassword(password);
        druidDataSource.setUsername(username);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setMaxWait(maxWait);
        druidDataSource.setInitialSize(initialSize);
        return druidDataSource;
    }
}
