package com.keven.redission.demo;

import javax.management.*;
import java.io.IOException;
import java.lang.management.ManagementFactory;

public class JMXMain {
    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, IOException {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        ObjectName objectName = new ObjectName("com.keven.redission.demo:type=SystemInfo");
        SystemInfo systemInfo = new SystemInfo();
        mBeanServer.registerMBean(systemInfo, objectName);
        System.in.read();
    }
}
