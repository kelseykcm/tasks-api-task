package br.ce.wcaquino.tasks.apitest;

import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class APITest {
	
	@BeforeClass
	public static void setup() {
		RestAssured.baseURI ="http://127.0.0.1:8001/tasks-backend";
	}
	
	@Test
	public void deveraRetornarTarefas() {
		RestAssured.given()
		.when()
			 .get("/todo")
		.then()
			 .statusCode(200)
		;
	}
	
	@Test
	public void deveAdicionarTarefaComSucesso() {
		RestAssured.given()
			 .body("{ \"task\": \"Teste via API\", \"dueDate\": \"2023-11-11\" }")
			 .contentType(ContentType.JSON)
		.when()
			 .post("/todo")
		.then()
			 .statusCode(201)
		;
	}
	
	@Test
	public void naodeveAdicionarTarefainvalida() {
		RestAssured.given()
			 .body("{ \"task\": \"Teste via API\", \"dueDate\": \"2010-12-30\" }")
			 .contentType(ContentType.JSON)
		.when()
			 .post("/todo")
		.then()
			 .statusCode(400)
			 
		;
	}
}

