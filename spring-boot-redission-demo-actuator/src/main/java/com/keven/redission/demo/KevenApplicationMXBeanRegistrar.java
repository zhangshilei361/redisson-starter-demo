package com.keven.redission.demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.stereotype.Component;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * 自己注入 （加载、销毁bean）JMX形式暴露出信息
 */
@Component
public class KevenApplicationMXBeanRegistrar implements ApplicationContextAware,
        EnvironmentAware, InitializingBean, DisposableBean {

    private ConfigurableApplicationContext applicationContext;

    private Environment environment = new StandardEnvironment();

    // 发布Bean
    private final ObjectName objectName = new ObjectName("com.keven.redission.demo:type=SystemInfo");

    public KevenApplicationMXBeanRegistrar() throws MalformedObjectNameException {
    }

    @Override
    public void destroy() throws Exception {
        // 销毁bean
        ManagementFactory.getPlatformMBeanServer().unregisterMBean(this.objectName);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 注入bean（Spring）
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        server.registerMBean(new SystemInfo(), this.objectName);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 获取上下文（Springboot信息）
        this.applicationContext = (ConfigurableApplicationContext)applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
