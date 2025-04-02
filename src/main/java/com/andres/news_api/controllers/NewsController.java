package com.andres.news_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andres.news_api.dtos.NewsDTO;
import com.andres.news_api.services.NewsService;

@RestController
@RequestMapping("/api/news")
@CrossOrigin(value = "http://localhost:4200")
public class NewsController {

	@Autowired
	NewsService newsService;

	/*
	 * Recibe como un parámetro about y retorna un status.OK con noticias referias
	 * al tema. De recibir una lista vacía retorna status.NO CONTENT
	 */
	@GetMapping("/{about}")
	public ResponseEntity<List<NewsDTO>> getNotices(@PathVariable String about) {
		List<NewsDTO> news = newsService.getNews(about);
		if (news.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(news);
	}

}
