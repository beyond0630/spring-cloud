package com.beyond.gateway.config.props;

import java.util.List;

import com.beyond.gateway.config.options.AppGatewayOptions;
import com.google.common.base.Objects;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

/**
 * @author beyond
 * @date 2020/6/11 16:03
 */
@Component
@ConfigurationProperties(prefix = "app.gateway")
public class AppGatewayProperties implements AppGatewayOptions {

    private List<ExcludeRequest> excludeRequests;

    @Override
    public List<ExcludeRequest> getExcludeRequests() {
        return excludeRequests;
    }

    public void setExcludeRequests(final List<ExcludeRequest> excludeRequests) {
        this.excludeRequests = excludeRequests;
    }

    public static class ExcludeRequest {

        private String uri;
        private HttpMethod method;

        public String getUri() {
            return uri;
        }

        public void setUri(final String uri) {
            this.uri = uri;
        }

        public HttpMethod getMethod() {
            return method;
        }

        public void setMethod(final HttpMethod method) {
            this.method = method;
        }


        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ExcludeRequest that = (ExcludeRequest) o;
            return Objects.equal(uri, that.uri) &&
                    method == that.method;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(uri, method);
        }
    }
}



