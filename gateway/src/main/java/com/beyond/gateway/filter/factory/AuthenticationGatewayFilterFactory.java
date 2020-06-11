package com.beyond.gateway.filter.factory;


import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.alibaba.fastjson.JSONObject;
import com.beyond.common.result.ApiResult;
import com.beyond.common.result.AuthUser;
import com.beyond.gateway.client.service.AuthServiceClient;
import com.beyond.gateway.config.options.AppGatewayOptions;
import com.beyond.gateway.config.props.AppGatewayProperties;
import com.beyond.gateway.utils.ApplicationContextUtils;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;

/**
 * @author beyond
 * @date 2020/6/10 18:55
 */
@Component
public class AuthenticationGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationGatewayFilterFactory.class);

    private final AppGatewayOptions appGatewayOptions;

    public AuthenticationGatewayFilterFactory(final AppGatewayOptions appGatewayOptions) {
        super(Object.class);
        this.appGatewayOptions = appGatewayOptions;
        LOGGER.info("Loaded GatewayFilterFactory [Authorize]");
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("enabled");
    }

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            List<AppGatewayProperties.ExcludeRequest> excludeRequests = appGatewayOptions.getExcludeRequests();
            if (!CollectionUtils.isEmpty(excludeRequests)) {
                AppGatewayProperties.ExcludeRequest temp = new AppGatewayProperties.ExcludeRequest();
                temp.setUri(request.getURI().getPath());
                temp.setMethod(request.getMethod());
                if (excludeRequests.contains(temp)) {
                    return chain.filter(exchange);
                }
            }
            String token = getToken(request);
            AuthServiceClient authServiceClient = ApplicationContextUtils.getBean(AuthServiceClient.class);
            AuthUser authUser = authServiceClient.verifyAuth(token);
            ServerHttpResponse response = exchange.getResponse();
            if (Objects.isNull(authUser)) {
                byte[] data = JSONObject.toJSONString(ApiResult.error(401, "请先登录"))
                        .getBytes(StandardCharsets.UTF_8);
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                DataBuffer buffer = response.bufferFactory().wrap(data);
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
                return response.writeWith(Mono.just(buffer));
            }
            return chain.filter(exchange);
        };
    }

    private String getToken(final ServerHttpRequest request) {
        return this.getTokenFromHeader(request.getHeaders());
    }

    private String getTokenFromHeader(final HttpHeaders headers) {
        final String authValue = headers.getFirst(HttpHeaders.AUTHORIZATION);
        if (Strings.isNullOrEmpty(authValue)) {
            return null;
        }
        return authValue;
    }
}
