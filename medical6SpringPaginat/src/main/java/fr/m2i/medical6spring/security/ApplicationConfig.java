package fr.m2i.medical6spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class ApplicationConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dSource;
    @Autowired
    private UserDetailsServiceImpl  userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println(passwordEncoder().encode("1234"));

       // accée la methode get
       // super.configure(auth);

       // auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("ADMIN");

       // auth.jdbcAuthentication().dataSource(dSource).usersByUsernameQuery( "SELECT username, password, 1 enabled FROM user WHERE username=?")
       //         .authoritiesByUsernameQuery( "SELECT username,roles FROM user WHERE username=?")
       //         .passwordEncoder(passwordEncoder());


auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()) ;
    }
    @Bean
    public  PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//  les accées sur les methodes post et delete
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }
}
