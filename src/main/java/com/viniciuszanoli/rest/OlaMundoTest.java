package com.viniciuszanoli.rest;

import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class OlaMundoTest {

	@Test
	public void testOlaMundo(){
		Response response = request(Method.GET, "http://restapi.wcaquino.me/ola");
		assertTrue(response.getBody().asString().equals("Ola Mundo!"));
		assertTrue(response.statusCode() == 200);
		assertTrue("O Status code deveria ser 200", response.statusCode() == 200);
		assertEquals(200, response.statusCode());

		ValidatableResponse validatableResponse = response.then();
		validatableResponse.statusCode(200);
	}

	@Test
	public void devoConhecerOutrasFormasRestAssured() {
		Response response = request(Method.GET, "http://restapi.wcaquino.me/ola");		
		ValidatableResponse validatableResponse = response.then();
		validatableResponse.statusCode(200);
		
		get("http://restapi.wcaquino.me/ola").then().statusCode(200);
		
		given()
		.when()
			.get("http://restapi.wcaquino.me/ola")
		.then()
			.statusCode(200);
	}
	
	@Test
	public void devoConhecerMatcherHamcrest() {
		assertThat("Maria", is("Maria"));
		assertThat(128, isA(Integer.class));
		assertThat(128d, isA(Double.class));
		assertThat(131d, greaterThan(130d));
		assertThat(128d, lessThan(130d));

		List<Integer> impares = Arrays.asList(1,3,5,7,9);
		assertThat(impares, hasSize(5));
		assertThat(impares, contains(1,3,5,7,9));
		assertThat(impares, containsInAnyOrder(1,3,5,7,9));
		assertThat(impares, hasItem(3));
		assertThat(impares, hasItems(1,9));

		assertThat("Maria", is(not("João")));
		assertThat("Maria", not("João"));
		assertThat("Joaquina", anyOf(is("Maria"), is("Joaquina")));
		assertThat("Joaquina", allOf(startsWith("Joa"), endsWith("ina"), containsString("ina")));
	}

	@Test
	public void devoValidarBody(){
		given()
		.when()
			.get("http://restapi.wcaquino.me/ola")
		.then()
			.statusCode(200)
			.body(is("Ola Mundo!"))
			.body(containsString("Mundo"))
			.body(is(not(nullValue())))
		;
	}
}
