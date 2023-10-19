package mainpackage;

import lombok.RequiredArgsConstructor;
import mainpackage.services.CustomUserDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
@PropertySources(value = {@PropertySource("classpath:application.properties")}
)@ComponentScan(basePackages = {"mainpackage.*"})
public class Config extends WebSecurityConfigurerAdapter {//устарейший способ
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/", "/product/**", "/images/**", "/registration", "/user/**", "/login")//какие url не защищены
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
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
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
