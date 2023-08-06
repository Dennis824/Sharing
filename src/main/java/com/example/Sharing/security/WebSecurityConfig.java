package com.example.Sharing.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    @Value("select email, password, active from users where email=?")
    private String usersQuery;

    @Value("select u.email, r.role from users u inner join user_role ur on(u.id=ur.user_id) inner join roles r on(ur.role_id=r.id) where u.email=?")
    private String rolesQuery;

    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(DataSource dataSource, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.dataSource = dataSource;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource).passwordEncoder(passwordEncoder);
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(8);
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().
                authorizeRequests().antMatchers("/login*","/registration*").anonymous()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .failureUrl("/login?error=true").defaultSuccessUrl("/profile").usernameParameter("email")
                .passwordParameter("password")	.and().logout()
                .logoutSuccessUrl("/").and().exceptionHandling().accessDeniedPage("/access-denied");

    }

}
