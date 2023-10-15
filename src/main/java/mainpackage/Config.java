package mainpackage;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
//@EnableScheduling
@PropertySources(value = {@PropertySource("classpath:application.properties")})
public class Config {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
