package br.com.rk.config;

import br.com.rk.aop.LoggingAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;

/**
 * Configuration used to run the LoggingAspect
 *
 * @author Rhuan Karlus
 * @see LoggingAspect
 * @since 15/03/19
 */
@Configuration
@EnableAspectJAutoProxy
public class LoggingAspectConfiguration {

    @Bean
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }

}
