package com.example.demo;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.ipc.netty.http.server.HttpServer;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws IOException, InterruptedException {

		HandlerFunction hello = request -> ServerResponse.ok().body(fromObject("Hello"));

		RouterFunction router = route(GET("/"), hello);

		HttpHandler httpHandler = RouterFunctions.toHttpHandler(router);

		HttpServer
				.create("localhost", 8080)
				.newHandler(new ReactorHttpHandlerAdapter(httpHandler))
				.block();

		Thread.currentThread().join();
	}
}
