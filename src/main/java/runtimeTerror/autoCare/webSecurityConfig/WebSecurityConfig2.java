//package runtimeTerror.autoCare.webSecurityConfig;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import runtimeTerror.autoCare.repository.UserServiceImpl;
//
//@Configuration
//@EnableWebSecurity
//@Order(2)
//public class WebSecurityConfig2  extends WebSecurityConfigurerAdapter {
//
//
//    @Autowired
//    private UserServiceImpl userDetailsService;
//
//
//
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//
//    @Override
//    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
//
//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//        http
//                .cors().disable()
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/User**").permitAll()
//                .antMatchers("/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/User/login")
//                .defaultSuccessUrl("/")
//                .and()
//                .logout()
//                .logoutSuccessUrl("/User/login")
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID");
//
//    }
//
//}
