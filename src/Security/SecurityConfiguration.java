import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    
@Bean
SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(requests -> requests
    .requestMatcher("/pizzas", "/pizzas/{id}").hasAnyAuthority("USER","ADMIN")
    .requestMatchers(HttpMethod.POST, "/pizzas/**").hasAuthority("ADMIN")
                .requestMatchers("/ingredients", "/ingredients/**").hasAuthority("ADMIN")
                .requestMatchers("/pizzas", "/pizzas/**").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/**").permitAll())
                .formLogin(Customizer.withDefaults());
                 return http.build();
}

@Bean
@SuppressWarnings("deprecation")
DaoAuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider authProvider= new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailService());
    authProvider.setPasswordEncoder(setPasswordEncoder());
    return authProvider;
}

@Bean 
DatabaseUserDetailsService userDetailService(){
    return new DatabaseUserDetailsService();
}
@Bean
PasswordEncoder passwordEncoder(){
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
}

}
