package mainpackage;

import lombok.RequiredArgsConstructor;
import mainpackage.services.CustomUserDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;

//@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@PropertySources(value = {@PropertySource("classpath:application.properties")})
public class Config extends WebSecurityConfigurerAdapter {
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        //http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/", "/product/**", "/images/**", "/registration")//какие url не защищены
                .permitAll()
                .anyRequest().authenticated()//для отсальных url без входа доступ не получить
                .and()
                .formLogin()
                .loginPage("/login")//страница логина
                .permitAll()//разрешить
                .and()
                .logout()
                .permitAll();
    }
    /*@Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests((requests) -> requests.antMatchers("/", "/registration").permitAll()
                        .antMatchers("/product/**", "/image/**")
                        .hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()

                )
                .logout((logout) -> logout.permitAll());
        return http.build();
    }*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        auth.userDetailsService(userDetailsService) // auth.userDetailsService(uRepo.findByEmail(email));
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(8);//защита пароля шифрованием
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();//для облегчения преобразования переданного файла в фото
    }
}
