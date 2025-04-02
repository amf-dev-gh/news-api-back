package com.andres.news_api.configurations;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAiConfig {
	
	/*
	 * Inyecta un bean de chatClient para hacer consultas a OPENIA
	 */
	@Bean
	ChatClient chatClient(ChatClient.Builder builder) {
		return builder.build();
	}

}
