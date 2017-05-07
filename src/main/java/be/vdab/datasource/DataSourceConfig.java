package be.vdab.datasource;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

public class DataSourceConfig {

    @Bean
    DataSource dataSource() {
	return new JndiDataSourceLookup().getDataSource("jdbc/groenetenen");
    }

}
