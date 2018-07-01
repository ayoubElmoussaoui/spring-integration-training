package com.intertech.lab8;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Startup {

	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"/META-INF/spring/si-components.xml");
//
//		MessageChannel channel = context.getBean("requestChannel", MessageChannel.class);
//		Message<String> message = MessageBuilder.withPayload("Hello brave new world").build();
//		channel.send(message);
		PigLatinService service = context.getBean("latinService",
				PigLatinService.class);
		Future<String> future = service.translate("Hello brave new world");
// do more work here in a real application
		String serviceOutput = future.get(5000, TimeUnit.SECONDS);
		System.out.println(serviceOutput);

		context.close();
	}
}
