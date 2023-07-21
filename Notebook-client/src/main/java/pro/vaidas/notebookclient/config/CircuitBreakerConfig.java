package pro.vaidas.notebookclient.config;

import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;

import java.time.Duration;

//@Configuration
//public class CircuitBreakerConfig {
//
//    CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
//            .failureRateThreshold(50)
//            .waitDurationInOpenState(Duration.ofMillis(1000))
//            .slidingWindowSize(2)
//            .build();
//    TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()
//            .timeoutDuration(Duration.ofSeconds(4))
//            .build();

//    @Bean
//    public Customizer<Resilience4JCircuitBreakerFactory> specificCustomConfiguration1() {
//        return factory -> factory.configure(builder -> builder.circuitBreakerConfig(circuitBreakerConfig)
//                .timeLimiterConfig(timeLimiterConfig).build(), "circuitBreaker");
//    }

//    @Bean
//    public Customizer<Resilience4JCircuitBreakerFactory> globalCustomConfiguration() {
//        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
//                .timeLimiterConfig(timeLimiterConfig)
//                .circuitBreakerConfig(circuitBreakerConfig)
//                .build());
//    }
//}
