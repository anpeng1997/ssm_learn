package config;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.Resource;


@Configuration
@ComponentScan(basePackages = {"cn.pengan"})
@PropertySource("classpath:jdbcConfig.properties")
@Import(JdbcConfig.class)
public class springConfig {
}
