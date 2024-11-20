package com.mimu.application.mvc.application;

import com.mimu.common.config.LogTraceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(LogTraceConfig.class)
public class ApplicationConfiguration {
}