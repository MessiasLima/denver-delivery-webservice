package br.com.cajumobile.delivery.configuration;

import br.com.cajumobile.delivery.configuration.interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebMvcConfigurerAdapter {

    public static final String AUTHETICATED_USER = "br.com.cajumobile.delivery.AUTHENTICATED_USER";

    @Bean
    public AuthenticationInterceptor getAuthenticationInterceptor() {
        return new AuthenticationInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getAuthenticationInterceptor())
                .addPathPatterns("/**/*")
                .excludePathPatterns(getExcludePathPatterns());
    }

    private String[] getExcludePathPatterns() {
        return new String[]{
                "/usuario/login",
                "/error",
                "/download/**/*",
                "/cliente"
        };
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**/*")
                .allowedMethods("GET", "POST", "OPTIONS", "PUT", "DELETE")
                .allowedHeaders(
                        "Tipo",
                        "Authorization",
                        "Content-Type",
                        "X-Requested-With",
                        "accept",
                        "Origin",
                        "Access-Control-Request-Method",
                        "Access-Control-Request-Headers"
                );
    }
}
