package com.app.configuration;


import com.app.DAO.TitanicPassengerCsvDaoImpl;
import com.app.DAO.TitanicPassengerSqlDaoImpl;
import com.app.Service.TitanicPassengerService;
import com.app.Service.TitanicPassengerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import javax.sql.DataSource;
import java.io.File;
import java.sql.SQLException;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.app")
public class AppConfig {
    @Bean
    @Primary
    public DataSource dataSource() throws SQLException {
        File file = new File("passengers.db");
        String path = file.getAbsolutePath();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:" + path);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public TitanicPassengerService TitanicPassengerService() {
        return new TitanicPassengerServiceImpl();
    }

    @Bean
    public TitanicPassengerCsvDaoImpl TitanicPassengerCsvDao() {
        return new TitanicPassengerCsvDaoImpl();
    }
    @Bean
    public TitanicPassengerSqlDaoImpl TitanicPassengerSqlDao() {
        return new TitanicPassengerSqlDaoImpl();
    }


}
