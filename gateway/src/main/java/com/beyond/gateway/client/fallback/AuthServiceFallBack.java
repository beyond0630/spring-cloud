package com.beyond.gateway.client.fallback;

import com.beyond.gateway.client.service.AuthServiceClient;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author beyond
 * @date 2020/6/10 19:29
 */
@Component
public class AuthServiceFallBack implements FallbackFactory<AuthServiceClient> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceFallBack.class);

    @Override
    public AuthServiceClient create(final Throwable throwable) {
        return (token) -> {
            LOGGER.debug("auth service hystrix ...");
            return null;
        };
    }
}
