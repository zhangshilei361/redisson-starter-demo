package com.keven.redission.demo;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    RedissonClient redissonClient;

    @GetMapping("/say")
    public String say(){
        RBucket bucket=redissonClient.getBucket("name");
        if(bucket.get() == null){
            bucket.set("keven.com");
        }
        return bucket.get().toString();
    }
}
