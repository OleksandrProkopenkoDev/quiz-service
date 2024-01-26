package ua.com.quizservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ua.com.quizservice.security.WebSecurityConfig;

/**
 * Configuration which set CORS (cross-origin request processing).
 *
 * @deprecated This config is redundant. Cors configured in the {@link WebSecurityConfig}
 */
@Deprecated(forRemoval = true)
@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
        .addMapping("/**")
        .allowedOrigins("http://localhost:3000") // Разрешенный источник
        .allowedMethods("*") // Разрешение всех методов
        .allowedHeaders("*"); // Разрешение всех заголовков
  }
}
