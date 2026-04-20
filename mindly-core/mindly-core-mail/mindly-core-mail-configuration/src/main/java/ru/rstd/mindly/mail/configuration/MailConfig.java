package ru.rstd.mindly.mail.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import ru.rstd.mindly.mail.properties.MailProperties;

@ComponentScan(basePackages = {
        "ru.rstd.mindly.mail"
})
@Configuration
@RequiredArgsConstructor
public class MailConfig {
    private final MailProperties mailProperties;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailProperties.getHost());
        mailSender.setUsername(mailProperties.getUsername());
        mailSender.setPassword(mailProperties.getPassword());
        mailSender.setPort(Integer.parseInt(mailProperties.getPort()));
        mailSender.setJavaMailProperties(mailProperties.getProperties());
        return mailSender;
    }
}
