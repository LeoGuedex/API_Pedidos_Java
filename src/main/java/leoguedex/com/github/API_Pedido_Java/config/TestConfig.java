package leoguedex.com.github.API_Pedido_Java.config;

import leoguedex.com.github.API_Pedido_Java.service.DBService;
import leoguedex.com.github.API_Pedido_Java.service.EmailService;
import leoguedex.com.github.API_Pedido_Java.service.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        dbService.instantiateTestDatabase();

        return true;
    }

    @Bean
    public EmailService emailService(){
        return new MockEmailService();
    }

}