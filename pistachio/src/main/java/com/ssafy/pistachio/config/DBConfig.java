package com.ssafy.pistachio.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.ssafy.pistachio.model.dao")
public class DBConfig {
}
