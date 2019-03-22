package iehr.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@Order(-1)
public class CORSConfig extends WebSecurityConfigurerAdapter {

  /*
   * (non-Javadoc)
   *
   * @see
   * org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#
   * configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.requestMatchers()
            .antMatchers(HttpMethod.OPTIONS, "/oauth/token", "/api/**", "/jsnlog.logger")
            .and().requestMatchers()
            .antMatchers(HttpMethod.POST, "/jsnlog.logger")
            .and().requestMatchers()
            .antMatchers(HttpMethod.GET, "/health", "/health.json", "/info", "/info.json", "/metrics", "/metrics.json", "/upload/**")
            .and().csrf()
            .disable()
            .authorizeRequests().anyRequest().permitAll()
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }
}
