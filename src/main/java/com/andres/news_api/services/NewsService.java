package com.andres.news_api.services;

import java.util.List;

import com.andres.news_api.dtos.NewsDTO;

public interface NewsService {

	/*
	 * Obtiene una lista de newsDTO a partir de un tema pasado por parametro en
	 * about
	 */
	public List<NewsDTO> getNews(String about);

}
