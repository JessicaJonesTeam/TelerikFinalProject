package com.payment_system.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
public class ApplicationBeanConfig {

//    private UserRepository userRepository;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    @Bean
//
//    public JwtParser jwtParser() {
//        return new JwtParser(userRepository);
//    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
