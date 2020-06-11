package com.beyond.auth.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author beyond
 * @date 2020/6/10 20:35
 */
public class MvcConfig implements WebMvcConfigurer{

    private static final Logger LOGGER = LoggerFactory.getLogger(MvcConfig.class);

    @Override
    public void configureMessageConverters(final List<HttpMessageConverter<?>> converters) {
        LOGGER.debug("ConfigureMessageConverters");
    }
}
