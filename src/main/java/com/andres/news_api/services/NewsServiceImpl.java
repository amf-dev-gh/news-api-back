package com.andres.news_api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andres.news_api.dtos.NewsDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private ChatClient chatClient;

	/*
	 * Hace el llamado con el prompt generado y parsea el JSON al modelo newsDTO y
	 * retorna una lista con las noticias
	 */
	@Override
	public List<NewsDTO> getNews(String about) {
		String response = chatClient.prompt().user(generatePrompt(about)).call().content();

		response = response.trim();
		response = response.replaceAll("```json", "").replaceAll("```", "");

		List<NewsDTO> newsList = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			JsonNode rootNode = objectMapper.readTree(response);
			JsonNode newsArray = rootNode.get("news");

			if (newsArray != null && newsArray.isArray()) {
				for (JsonNode newsNode : newsArray) {
					NewsDTO news = new NewsDTO(newsNode.get("title").asText(), newsNode.get("subtitle").asText(),
							newsNode.get("body").asText());
					newsList.add(news);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newsList;
	}

	/*
	 * Genera el prompt para obtener el JSON con las FAKE News a partir del tema
	 * about
	 */
	private String generatePrompt(String about) {
		return "Genera un JSON con un minimo de tres noticias de la actualidad referidas a " + about
				+ "El JSON debe contener un array de 'news' y cada noticia debe que tener: "
				+ "un 'title' son su titulo original, " + "un 'subtitle', " + "un 'body' de la noticia resumido. "
				+ "Asegúrate de devolver solo el JSON válido, sin texto adicional. "
				+ "Deben ser noticias REALES del 2025 y en español.";
	}

}
