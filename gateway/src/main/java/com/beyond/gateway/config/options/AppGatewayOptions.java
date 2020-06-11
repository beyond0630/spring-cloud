package com.beyond.gateway.config.options;

import java.util.List;

import com.beyond.gateway.config.props.AppGatewayProperties;


/**
 * @author beyond
 * @date 2020/6/11 16:01
 */
public interface AppGatewayOptions {

    List<AppGatewayProperties.ExcludeRequest> getExcludeRequests();
}
