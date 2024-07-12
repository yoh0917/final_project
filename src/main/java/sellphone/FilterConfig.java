package sellphone;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.Filter;
import sellphone.dashboard.user.controller.DashBoardFilter;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<Filter> loggingFilter() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        
        registrationBean.setFilter(new DashBoardFilter());
        registrationBean.addUrlPatterns("/DashBoard/*"); // Apply to all URLs
        registrationBean.setOrder(1); // Set the order of the filter

        return registrationBean;
    }
}

