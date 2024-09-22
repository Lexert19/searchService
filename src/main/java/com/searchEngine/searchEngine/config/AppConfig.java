package com.searchEngine.searchEngine.config;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean
    public RestTemplate restTemplate() throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        // TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        // SSLContext sslContext = SSLContextBuilder.create()
        //         .loadTrustMaterial(null, acceptingTrustStrategy)
        //         .build();

        // org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory sslSocketFactory = SSLConnectionSocketFactoryBuilder.create()
        //         .setSslContext(sslContext)
        //         .setHostnameVerifier(NoopHostnameVerifier.INSTANCE)
        //         .build();

        // HttpClientConnectionManager cm = PoolingHttpClientConnectionManagerBuilder.create()
        //         .setSSLSocketFactory(sslSocketFactory)
        //         .build();

        // CloseableHttpClient httpClient = HttpClients.custom()
        //         .setConnectionManager(cm)
        //         .build();

        // HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        // RestTemplate restTemplate = new RestTemplate(requestFactory);
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate;
    }
}
