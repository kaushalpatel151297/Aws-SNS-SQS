package com.awssqs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Application {

	@Autowired
	private QueueMessagingTemplate queueMessagingTemplate;

	@Value("${cloud.aws.end-point.uri}")
	private String endpoint;

	@GetMapping("/send/{message}")
	public void messagesend(@PathVariable String message)
	{
		try {
			queueMessagingTemplate.send(endpoint, MessageBuilder.withPayload(message).build());
			System.out.println("*******************************"+message);
		}
		catch (Exception e) {
			System.out.println("EXCEPTION------"+e);
		}

	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
