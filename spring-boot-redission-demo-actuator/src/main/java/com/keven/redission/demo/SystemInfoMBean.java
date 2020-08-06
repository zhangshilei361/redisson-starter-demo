package com.keven.redission.demo;

public interface SystemInfoMBean {

    int getCpuCore();

    long getTotalMemory();

    void shutdown();
}
