package com.bernardtm.planets.domain.httpclients;

import java.io.ByteArrayOutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bernardtm.planets.domain.models.ResponseSwapi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class HttpClientSwapi {

	@Value("${swapi-url}")
	private String url;
	
	
	public String getMovieAppearances(String planetId) {
		
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {

			HttpGet request = new HttpGet(url + "planets/" + planetId);
			request.addHeader("Content-Type", "application/json");

			try (CloseableHttpResponse response = httpClient.execute(request)) {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				HttpEntity e = response.getEntity();
	
				if (e != null) {
					e.writeTo(baos);
					EntityUtils.consume(e);
	
					Gson gson = new GsonBuilder().create();
					ResponseSwapi responseApi = gson.fromJson(baos.toString("UTF-8"), ResponseSwapi.class);
	
					return String.valueOf(responseApi.getFilms().size());
				}

			}
		} catch (Exception es) {
			System.err.println("HttpClientSwapi || getMovie");
		}
		return "0";

	}
	
}

