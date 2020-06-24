package com.vmware.backend.controller;

import com.vmware.backend.model.Charity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CharityControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;


	String url = "http://localhost:8080/charities";


	@Test
	public void testGetCharityListSuccess() throws URISyntaxException
	{
		//Given
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = url +"/all";
		URI uri = new URI(baseUrl);

		//When
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		//Then
		assertEquals(200, result.getStatusCodeValue());
		assertEquals(true, result.getBody().contains("Smile for Africa"));
		assertEquals(false, result.getBody().contains("WOW"));
	}


	@Test
	public void testGetCharityThatExist() throws URISyntaxException
	{
		//Given
		final String title = "Smile for Africa";
		final String charityId = "2";
		final String baseUrl = url +"/get/" + charityId;
		URI uri = new URI(baseUrl);

		//When
		ResponseEntity<Charity> result = restTemplate.getForEntity(uri, Charity.class);
		Charity charity = result.getBody();

		//Then
		assertEquals(200, result.getStatusCodeValue());
		assertEquals(2, charity.getId());
		assertEquals(title, charity.getTitle());
	}

	@Test
	public void testGetCharityThatDoesntExist() throws URISyntaxException
	{
		//Given
		final String charityId = "8888";
		final String baseUrl = url +"/get/" + charityId;
		URI uri = new URI(baseUrl);

		//When
		ResponseEntity<Charity> result = restTemplate.getForEntity(uri, Charity.class);
		HttpStatus status = result.getStatusCode();
		Charity charity = result.getBody();

		//Then
		assertEquals(null, charity);
		assertEquals(HttpStatus.NOT_FOUND, status);
	}


	@Test
	public void testAddAndUpdateCharity() throws URISyntaxException {
		//1.ADD
		//==============
		// Given
		final Charity charity = new Charity("Charity Name", "Description", "Image", (double) 155, (double) 133);
		final String baseUrl = url;
		URI uri = new URI(baseUrl);

		//When
		ResponseEntity<Charity> responseEntity = restTemplate.postForEntity(uri, charity, Charity.class);
		HttpStatus responseStatus = responseEntity.getStatusCode();
		Charity addedCharity = responseEntity.getBody();

		//Then
		assertNotEquals(null, addedCharity.getId());
		assertEquals("Charity Name", addedCharity.getTitle());
		assertEquals(HttpStatus.OK, responseStatus);

		//2.UPDATE
		//================
		//Given
		URI uri2 = new URI(url+"/put/"+addedCharity.getId().toString());
		Charity charityUpdated = new Charity("Update Name", "Update Description", "Image", (double) 155, (double) 133);

		//When
		HttpEntity<Charity> requestUpdate = new HttpEntity<>(charityUpdated, new HttpHeaders());
		restTemplate.put(uri2, charityUpdated);
		//Then

		//When
		ResponseEntity<Charity> responseEntity4 = restTemplate.getForEntity(new URI(url + "/get/" + addedCharity.getId().toString()), Charity.class);
		HttpStatus responseStatus4 = responseEntity4.getStatusCode();
		Charity updatedCharity = responseEntity4.getBody();
		assertEquals(charityUpdated.toString(), updatedCharity.toString());
		assertEquals(updatedCharity.getId(), addedCharity.getId());

		//3.delete
		URI uri3 = new URI(url+"/delete/"+ addedCharity.getId().toString());
		ResponseEntity responseEntity3 = restTemplate.exchange(uri3, HttpMethod.DELETE, null, Void.class );
	}

	@Test
	public void testAddAndDeleteCharity() throws URISyntaxException {

		//ADD
		//==============
		// Given
		final Charity charity = new Charity("Charity Name", "Description", "Image", (double) 155, (double) 133);
		final String baseUrl = url;
		URI uri = new URI(baseUrl);

		//When
		ResponseEntity<Charity> responseEntity = restTemplate.postForEntity(uri, charity, Charity.class);
		HttpStatus responseStatus = responseEntity.getStatusCode();
		Charity addedCharity = responseEntity.getBody();

		//Then
		assertNotEquals(null, addedCharity.getId());
		assertEquals("Charity Name", addedCharity.getTitle());
		assertEquals(HttpStatus.OK, responseStatus);

		//DELETE
		//================
		URI uri2 = new URI(url+"/delete/"+addedCharity.getId().toString());
		ResponseEntity responseEntity2 = restTemplate.exchange(uri2, HttpMethod.DELETE, null, Void.class );
		HttpStatus responseStatus2 = responseEntity2.getStatusCode();
		assertEquals(HttpStatus.OK, responseStatus2);

		ResponseEntity<Charity> result = restTemplate.getForEntity(new URI(url+"/get/"+addedCharity.getId().toString()), Charity.class);
		HttpStatus responseStatus3 = result.getStatusCode();
		assertEquals(HttpStatus.NOT_FOUND, responseStatus3);
	}

}
