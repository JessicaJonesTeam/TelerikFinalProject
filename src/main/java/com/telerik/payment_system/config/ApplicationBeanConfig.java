package com.telerik.payment_system.config;


import com.telerik.payment_system.Utilities.JwtParser;
import com.telerik.payment_system.repositories.base.UserRepository;
import com.telerik.payment_system.services.base.AdminService;
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
