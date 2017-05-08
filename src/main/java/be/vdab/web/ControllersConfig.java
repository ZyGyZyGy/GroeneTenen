package be.vdab.web;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan
@EnableSpringDataWebSupport 
public class ControllersConfig extends WebMvcConfigurerAdapter {

    @Bean
    InternalResourceViewResolver viewResolver() {
	InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	resolver.setPrefix("/WEB-INF/JSP/");
	resolver.setSuffix(".jsp");
	return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	registry.addResourceHandler("/images/**").addResourceLocations("/images/");
	registry.addResourceHandler("/styles/**").addResourceLocations("/styles/");
	registry.addResourceHandler("/scripts/**").addResourceLocations("/scripts/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
	registry.addViewController("/info").setViewName("info");
    }
    
    @Bean
    MessageSource messageSource() {
	ReloadableResourceBundleMessageSource source
		= new ReloadableResourceBundleMessageSource();
	source.setBasename("classpath:teksten");
	source.setFallbackToSystemLocale(false);
	return source;
    }
    
    @Bean
    LocaleResolver localeResolver() {
	CookieLocaleResolver resolver = new CookieLocaleResolver();
	resolver.setCookieMaxAge(604800);
	return resolver;
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
	registry.addInterceptor(new LocaleChangeInterceptor());
    }
    
    @Bean
    LocalValidatorFactoryBean validatorFactory() {
	LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
	factory.setValidationMessageSource(messageSource());
	return factory;
    }
    
    @Override
    public Validator getValidator() {
	return new SpringValidatorAdapter(validatorFactory().getValidator());
    }

}
