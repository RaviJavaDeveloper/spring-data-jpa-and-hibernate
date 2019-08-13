package org.ravi.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class HibernateSessionFactoryConfig {


    private EntityManagerFactory emf;

    @Bean("hbDataSource")
@ConfigurationProperties(prefix = "spring.datasource.hb")
public DataSource createDataSource(){
    return DataSourceBuilder.create().build();
}

    public Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        return properties;
    }

    @Bean("hbSessionFactory")
    public LocalSessionFactoryBean getSessionFactory(@Qualifier("hbDataSource") DataSource dataSource) {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource);
        localSessionFactoryBean.setHibernateProperties(additionalProperties());
        localSessionFactoryBean.setPackagesToScan("org.ravi.models");
        return localSessionFactoryBean;
    }



    @Bean("entityManagerFactory")
    @Primary
    public EntityManagerFactory createEMF(@Qualifier("hbDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setPackagesToScan("org.ravi.models");
        emf.setDataSource(dataSource);
        emf.setJpaVendorAdapter(createHBAdaptor());
        emf.setJpaDialect(new HibernateJpaDialect());
        emf.setPersistenceUnitName("user_mgmt");
        emf.afterPropertiesSet();
        this.emf = emf.getObject();
        return emf.getObject();
    }

    private JpaVendorAdapter createHBAdaptor() {
        HibernateJpaVendorAdapter hb = new HibernateJpaVendorAdapter();
        hb.setDatabasePlatform("org.hibernate.dialect.MySQL55Dialect");
        hb.setShowSql(false);
        return hb;
    }

    public Properties hibernateProperties() {
        Properties props = new Properties();
        props.setProperty("hibernate.hdm2ddl.auto", "validate");
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL55Dialect");
        return props;
    }

    @Bean("transactionManager")
    public PlatformTransactionManager createTransManager(@Qualifier("hbDataSource") DataSource dataSource) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(emf);
        jpaTransactionManager.setDataSource(dataSource);
        jpaTransactionManager.setJpaDialect(new HibernateJpaDialect());
        return jpaTransactionManager;
    }

}
