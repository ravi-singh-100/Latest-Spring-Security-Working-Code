package SpringLatestSecurity.TryingLatestSecurity.Configuration;

import SpringLatestSecurity.TryingLatestSecurity.Service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class Security {
    @Value("${user.authority.developer}")
    private String DEVELOPER_AUTHORITY;


    @Value("${user.authority.devops}")
    private String DEVOPS_AUTHORITY;
    @Bean
    public UserDetailsService userDetailsService(){
        return new UserService();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((autoconfig) -> {
            autoconfig.requestMatchers(HttpMethod.GET,"/").permitAll();
            autoconfig.requestMatchers(HttpMethod.POST,"/signup").permitAll();
            autoconfig.requestMatchers("/developer").hasAuthority(DEVELOPER_AUTHORITY);
            autoconfig.requestMatchers("/devops").hasAuthority(DEVOPS_AUTHORITY);
            autoconfig.requestMatchers("/both").hasAnyAuthority(DEVELOPER_AUTHORITY,DEVOPS_AUTHORITY);
        })
                .csrf(csrf -> csrf.disable())

                .formLogin(withDefaults())
                .httpBasic(withDefaults());
        return http.build();
    }
}
