package com.pedistack.common.managers;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RestTemplateConfigurationManager {

  @Bean
  @SuppressWarnings("PMD")
  public RestTemplate restTemplate() throws NoSuchAlgorithmException, KeyManagementException {
    TrustManager[] trustAllCerts =
        new TrustManager[] {
          new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
              return new X509Certificate[0];
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {}

            public void checkServerTrusted(X509Certificate[] certs, String authType) {}
          }
        };
    final SSLContext sslContext = SSLContext.getInstance("SSL");
    sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
    final CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
    final HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory =
        new HttpComponentsClientHttpRequestFactory();
    httpComponentsClientHttpRequestFactory.setHttpClient(closeableHttpClient);
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setRequestFactory(httpComponentsClientHttpRequestFactory);
    restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    final ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setDefaultPrettyPrinter(new DefaultPrettyPrinter());
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    final MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter =
        new MappingJackson2HttpMessageConverter();
    mappingJackson2HttpMessageConverter.setSupportedMediaTypes(
        Arrays.asList(MediaType.APPLICATION_JSON, MediaType.TEXT_XML, MediaType.TEXT_HTML));
    mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
    restTemplate.setMessageConverters(
        Arrays.asList(new FormHttpMessageConverter(), mappingJackson2HttpMessageConverter));
    return restTemplate;
  }

  @Bean
  public CommonsRequestLoggingFilter requestLoggingFilter() {
    CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
    loggingFilter.setIncludeClientInfo(true);
    loggingFilter.setIncludeQueryString(true);
    loggingFilter.setIncludePayload(true);
    loggingFilter.setMaxPayloadLength(64000);
    return loggingFilter;
  }

  @Bean
  public WebMvcConfigurer webMvcConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry
            .addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("POST", "GET", "PUT", "DELETE");
      }
    };
  }
}
