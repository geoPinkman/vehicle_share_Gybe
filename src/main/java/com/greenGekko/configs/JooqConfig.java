package com.greenGekko.configs;

import org.jooq.ConnectionProvider;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.ThreadLocalTransactionProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
public class JooqConfig {

    @Bean
    public org.jooq.Configuration jooqConfiguration(DataSource dataSource) {
        // To support native jOOQ transactions instead of spring annotations
        ConnectionProvider connectionProvider = new DataSourceConnectionProvider(dataSource);
        return new DefaultConfiguration()
                .set(connectionProvider)
                .set(new ThreadLocalTransactionProvider(connectionProvider))
                .set(SQLDialect.POSTGRES);
    }

}
