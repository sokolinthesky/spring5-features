package com.example.demo;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

public class DemoApplicationTests {

	private final WebTestClient webTestClient = WebTestClient.bindToRouterFunction(DemoApplication.getRouter()).build();

	@Test
	public void getTest() {
		webTestClient.get().uri("/").exchange()
				.expectStatus().is2xxSuccessful()
				.expectBody(String.class)
				.isEqualTo("Hello");
	}

	@Test
	public void jsonTest() {
		webTestClient.get().uri("/json").exchange()
				.expectStatus().is2xxSuccessful()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody(Hello.class)
				.isEqualTo(new Hello("world"));

	}
}
