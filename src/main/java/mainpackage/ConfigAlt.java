package mainpackage;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
/*
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigAlt {//современный способ (более новые версии роекта и зависимостей)

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests((requests) -> requests.requestMatchers("/", "/product", "/images", "/registration", "/login").permitAll()
                        .requestMatchers("/product/**", "/image/**")
                        .hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());
        return http.build();
    }

    /*
    private final CustomUserDetailsService userDetailsService;
    @Bean
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {//автоматически
        //auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder(8));
    }*/

   /* @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(8);//защита пароля шифрованием
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();//для облегчения преобразования переданного файла в фото
    }
}
*/