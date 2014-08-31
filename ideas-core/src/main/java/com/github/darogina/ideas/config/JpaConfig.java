package com.github.darogina.ideas.config;

import com.jolbox.bonecp.BoneCPConfig;
import com.jolbox.bonecp.BoneCPDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;
import static org.hibernate.jpa.AvailableSettings.NAMING_STRATEGY;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.github.darogina.ideas.repository")
@PropertySource("classpath:persistence.properties")
public class JpaConfig implements TransactionManagementConfigurer {
    private static final String PROPERTY_NAME_DATABASE_DRIVER   = "db.driver";
    private static final String PROPERTY_NAME_DATABASE_TYPE     = "db.type";
    private static final String PROPERTY_NAME_DATABASE_URL      = "db.url";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
    private static final String PROPERTY_NAME_DATABASE_MAX_CONNECTIONS_PER_PARTITION = "db.maxConnectionsPerPartition";
    private static final String PROPERTY_NAME_DATABASE_MIN_CONNECTIONS_PER_PARTITION = "db.minConnectionsPerPartition";
    private static final String PROPERTY_NAME_DATABASE_PARTITION_COUNT = "db.partitionCount";
    private static final String PROPERTY_NAME_DATABASE_CONNECTION_TEST_STATEMENT = "db.connectionTestStatement";
    private static final String PROPERTY_NAME_DATABASE_IDLE_CONNECTION_TEST_PERIOD = "db.idleConnectionTestPeriodInSeconds";
    private static final String PROPERTY_NAME_DATABASE_STATEMENTS_CACHE_SIZE = "db.statementsCacheSize";

    private static final String PROPERTY_NAME_HIBERNATE_HDM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String PROPERTY_NAME_HIBERNATE_DIALECT         = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL      = "hibernate.format_sql";
    private static final String PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY = "hibernate.ejb.naming_strategy";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL        = "hibernate.show_sql";
    private static final String PROPERTY_NAME_HIBERNATE_LAZY_LOADING = "hibernate.enable_lazy_load_no_trans";
    private static final String PROPERTY_NAME_HIBERNATE_SECOND_LEVEL_CACHE = "hibernate.cache.use_second_level_cache";
    private static final String PROPERTY_NAME_HIBERNATE_QUERY_CACHE = "hibernate.cache.use_query_cache";
    private static final String PROPERTY_NAME_HIBERNATE_CACHE_REGION_FACTORY = "hibernate.cache.region.factory_class";

    private static final String PROPERTY_NAME_JADIRA_USERTYPE_AUTO_REGISTER_USER_TYPES = "jadira.usertype.autoRegisterUserTypes";
    private static final String PROPERTY_NAME_JADIRA_USERTYPE_DATABASE_ZONE = "jadira.usertype.databaseZone";
    private static final String PROPERTY_NAME_JADIRA_USERTYPE_JAVA_ZONE = "jadira.usertype.javaZone";

    private static final String PROPERTY_NAME_ENTITY_MANAGER_PACKAGES = "entitymanager.packages.to.scan";

    @Resource
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        final LazyConnectionDataSourceProxy dataSourceProxy = new LazyConnectionDataSourceProxy(createDataSource());

        return dataSourceProxy;
    }

    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setJpaDialect(new HibernateJpaDialect());
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan(environment.getRequiredProperty(PROPERTY_NAME_ENTITY_MANAGER_PACKAGES));
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);

        Properties jpaProterties = new Properties();
        jpaProterties.put(HBM2DDL_AUTO, environment.getProperty(PROPERTY_NAME_HIBERNATE_HDM2DDL_AUTO));
        jpaProterties.put(DIALECT, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
        jpaProterties.put(ENABLE_LAZY_LOAD_NO_TRANS, environment.getProperty(PROPERTY_NAME_HIBERNATE_LAZY_LOADING));
        jpaProterties.put(USE_SECOND_LEVEL_CACHE, environment.getProperty(PROPERTY_NAME_HIBERNATE_SECOND_LEVEL_CACHE));
        jpaProterties.put(USE_QUERY_CACHE, environment.getProperty(PROPERTY_NAME_HIBERNATE_QUERY_CACHE));
        jpaProterties.put(FORMAT_SQL, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL));
        jpaProterties.put(NAMING_STRATEGY, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY));
        jpaProterties.put(CACHE_REGION_FACTORY, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_CACHE_REGION_FACTORY));
        jpaProterties.put(PROPERTY_NAME_JADIRA_USERTYPE_AUTO_REGISTER_USER_TYPES, environment.getProperty(PROPERTY_NAME_JADIRA_USERTYPE_AUTO_REGISTER_USER_TYPES));
        jpaProterties.put(PROPERTY_NAME_JADIRA_USERTYPE_DATABASE_ZONE, environment.getProperty(PROPERTY_NAME_JADIRA_USERTYPE_DATABASE_ZONE));
        jpaProterties.put(PROPERTY_NAME_JADIRA_USERTYPE_JAVA_ZONE, environment.getProperty(PROPERTY_NAME_JADIRA_USERTYPE_JAVA_ZONE));

        entityManagerFactoryBean.setJpaProperties(jpaProterties);
        entityManagerFactoryBean.afterPropertiesSet();

        return entityManagerFactoryBean.getObject();
    }

    @Bean(name = "jpaVendorAdapter")
    public JpaVendorAdapter jpaVendorAdapter() {
        final HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(Boolean.valueOf(environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL)));

        if (!ObjectUtils.containsConstant(Database.values(), environment.getRequiredProperty(PROPERTY_NAME_DATABASE_TYPE), false)) {
            throw new RuntimeException(String.format("%s must be configured", PROPERTY_NAME_DATABASE_TYPE));
        }

        Database databaseType = ObjectUtils.caseInsensitiveValueOf(Database.values(), environment.getRequiredProperty(PROPERTY_NAME_DATABASE_TYPE));
        jpaVendorAdapter.setDatabase(databaseType);

//        jpaVendorAdapter.setGenerateDdl(true);
        return jpaVendorAdapter;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory());
        return jpaTransactionManager;
    }

    private DataSource createDataSource() {
        final BoneCPDataSource dataSource = new BoneCPDataSource(createBoneCPConfig());
        dataSource.setDriverClass(environment.getProperty(PROPERTY_NAME_DATABASE_DRIVER));

        return dataSource;
    }

    private BoneCPConfig createBoneCPConfig() {
        final BoneCPConfig config = new BoneCPConfig();

        config.setJdbcUrl(environment.getProperty(PROPERTY_NAME_DATABASE_URL));
        config.setUsername(environment.getProperty(PROPERTY_NAME_DATABASE_USERNAME));
        config.setPassword(environment.getProperty(PROPERTY_NAME_DATABASE_PASSWORD));
        config.setMaxConnectionsPerPartition(environment.getProperty(PROPERTY_NAME_DATABASE_MAX_CONNECTIONS_PER_PARTITION, Integer.class));
        config.setMinConnectionsPerPartition(environment.getProperty(PROPERTY_NAME_DATABASE_MIN_CONNECTIONS_PER_PARTITION, Integer.class));
        config.setPartitionCount(environment.getProperty(PROPERTY_NAME_DATABASE_PARTITION_COUNT, Integer.class));
        config.setConnectionTestStatement(environment.getProperty(PROPERTY_NAME_DATABASE_CONNECTION_TEST_STATEMENT));
        config.setIdleConnectionTestPeriodInSeconds(environment.getProperty(PROPERTY_NAME_DATABASE_IDLE_CONNECTION_TEST_PERIOD, Long.class));
        config.setStatementsCacheSize(environment.getProperty(PROPERTY_NAME_DATABASE_STATEMENTS_CACHE_SIZE, Integer.class));

        return config;
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

}
