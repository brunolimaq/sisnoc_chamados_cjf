package br.com.sisnoc.chamados.config;

import java.util.Properties;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import br.com.sisnoc.chamados.mail.Mailer;

@Configuration
@ComponentScan(basePackageClasses = Mailer.class)
@PropertySource({ "classpath:/br/com/sisnoc/chamados/service/mail.properties" })
public class MailConfig {


	
	
	@Autowired
	private Environment env;
	
	@Bean
	public JavaMailSender mailSender(){
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("correio.cjf.local");
		mailSender.setPort(587);
		mailSender.setUsername(env.getProperty("email.username"));
		mailSender.setPassword(env.getProperty("email.password"));

		
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", false);
		props.put("mail.debug", false);
		props.put("mail.smtp.connectiontimeout", 10000); // miliseconds

		mailSender.setJavaMailProperties(props);
		
		
		return mailSender;
	}

	
 
}
