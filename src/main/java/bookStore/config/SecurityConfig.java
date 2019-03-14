package bookStore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SimpleAuthenticationControlHandler successHandler;

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                    .dataSource(dataSource)
                    .usersByUsernameQuery("select username, password, enabled from users where username = ?")
                    .authoritiesByUsernameQuery("select user_roles.username, role from user_roles inner join users on users.username = user_roles.username where user_roles.username = ?")
                    .rolePrefix("ROLE_")
                    .passwordEncoder(new ShaPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                    .disable()
                .authorizeRequests()
                    /*.antMatchers("/js/**").hasRole("ADMIN")
                    .antMatchers("js/users/").hasRole("EMPLOYEE")
                    .anyRequest().authenticated()*/
                    .antMatchers("/js/**").permitAll()
                    .anyRequest().access("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYEE')")
                    .and()
                .formLogin()
                    .successHandler(successHandler)
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();
    }
}
