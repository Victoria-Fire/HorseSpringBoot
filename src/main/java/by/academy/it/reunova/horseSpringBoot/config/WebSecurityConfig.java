package by.academy.it.reunova.horseSpringBoot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/horse").hasAnyAuthority("USER", "ADMIN")
                // Доступ только для пользователей с ролью администратор
                .antMatchers("/horse/add").hasAnyAuthority( "ADMIN")
                .antMatchers("/horse/{id}/update").hasAnyAuthority( "ADMIN")
                .antMatchers("/horse/{id}/delete").hasAnyAuthority( "ADMIN")
                .antMatchers("/index", "/horse-rest").permitAll()
                .and()
                .formLogin().permitAll()
                // Перенаправление на главную страницу после успешного входа
                .defaultSuccessUrl("/horse", true)
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/login");
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
