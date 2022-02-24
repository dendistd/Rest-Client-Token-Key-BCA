package com.example.client.service.imple;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.client.enumeration.ErrorEnum;
import com.example.client.payload.ConsumeSchema;
import com.example.client.payload.ErrorSchema;
import com.example.client.payload.cabang.create.InputCreateCabang;
import com.example.client.payload.cabang.create.ResponseCreateCabang;
import com.example.client.payload.token.TokenModel;
import com.example.client.payload.token.create.InputCreateToken;
import com.example.client.payload.token.delete.InputDeleteToken;
import com.example.client.payload.token.get.InputGetToken;
import com.example.client.payload.token.get.ResponseGetToken;
import com.example.client.payload.token.update.InputUpdateToken;
import com.example.client.service.TokenService;
import com.fasterxml.jackson.core.JsonParseException;
@Service
public class TokenServiceImple implements TokenService {
	@Autowired
	RestTemplate restTemplate;
	
	//FUNGSI UNTUK NUMBER LOG
	public String logNumber() {
		StringBuilder builder = new StringBuilder(6);
		Random random = new Random();	
		for(int i = 0; i < 6; i++) {
			builder.append(random.nextInt(10));
		}
		return builder.toString();
	}					
	//FUNGSI DATE UNTUK LOG
	public String dateLog() {
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date();
		return formatter.format(date);
	}

	//CREATE TOKEN
	public ConsumeSchema createToken(InputCreateToken input) {
		String valueLog = logNumber();
		HttpHeaders header = new HttpHeaders();
		HttpEntity<InputCreateToken> entity = new HttpEntity<InputCreateToken>(input, header);
		ConsumeSchema result = new ConsumeSchema();		
		Map<String, String> map = new LinkedHashMap<>();
		System.out.println("["+valueLog + "]" +" - "+dateLog() + " - String Sebelum Response Entity");

		try {
		System.out.println("["+valueLog + "]" +" - "+dateLog() + " - Masuk Blok try");
		ResponseEntity<ConsumeSchema> respon = restTemplate.exchange("http://localhost:8089/tokenkeybca/token", HttpMethod.POST, entity, ConsumeSchema.class);
		System.out.println("["+valueLog + "]" +" - "+dateLog() + " - isi ResponseEntity = "+respon.toString());

		result = respon.getBody();
		System.out.println("["+valueLog + "]" +" - "+dateLog() + " - Isi Result = " + result.toString());
		
		} catch(Exception e) {
			System.out.println("["+valueLog + "]" +" - "+dateLog() + " - Masuk blok Catch");
			map.put("reason", e.getMessage());
			result.setOutputSchema(map);
			System.out.println("["+valueLog + "]" +" - "+dateLog() + " - Isi result = " + result.toString());
			return result;
		}
		return result;	
	}
	
	//Update Token
	public ConsumeSchema updateToken (InputUpdateToken input) {
		String valueLog = logNumber();
		HttpHeaders header = new HttpHeaders();
		HttpEntity<InputUpdateToken> entity = new HttpEntity<>(input, header);
		Map<String, String> map = new LinkedHashMap<String, String>();
		ConsumeSchema result = new ConsumeSchema();
		
		try {
			System.out.println("["+valueLog + "]" +" - "+dateLog() + " - Masuk Blok Try");
			ResponseEntity<ConsumeSchema> respon = restTemplate.exchange("http://localhost:8089/tokenkeybca/token", HttpMethod.PUT, entity, ConsumeSchema.class);
			System.out.println("["+valueLog + "]" +" - "+dateLog() + " - isi ResponseEntity = "+respon.toString());
			result = respon.getBody();
			System.out.println("["+valueLog + "]" +" - "+dateLog() + " - isi Result = "+result.toString());
		} catch(Exception e) {
			System.out.println("["+valueLog + "]" +" - "+dateLog() + " - Masuk Block catch");
			map.put("reason", e.getLocalizedMessage());
			result.setOutputSchema(map);
			System.out.println("["+valueLog + "]" +" - "+dateLog() + " - isi Result = "+result.toString());
			return result;
		}
		return result;

	}
	
	//DELETE TOKEN BY SERIAL NUMBER
	public ConsumeSchema deleteToken (InputDeleteToken input) {
		String valueLog = logNumber();
		HttpHeaders header = new HttpHeaders();
		HttpEntity<InputDeleteToken> entity = new HttpEntity<InputDeleteToken>(input, header);
		Map<String, String> map = new LinkedHashMap<String, String>();
		ConsumeSchema result = new ConsumeSchema();

		try {
		System.out.println("["+valueLog + "]" +" - "+dateLog() + " - Masuk Blok Try");
		ResponseEntity<ConsumeSchema> respon = restTemplate.exchange("http://localhost:8089/tokenkeybca/token", HttpMethod.DELETE, entity, ConsumeSchema.class);
		System.out.println("["+valueLog + "]" +" - "+dateLog() + " - isi ResponseEntity = "+respon.toString());
		result = respon.getBody();
		System.out.println("["+valueLog + "]" +" - "+dateLog() + " - isi Result = "+result.toString());
		} catch (Exception e) {
			System.out.println("["+valueLog + "]" +" - "+dateLog() + " - Masuk Block catch");
			map.put("reason", e.getLocalizedMessage());
			result.setOutputSchema(map);
			System.out.println("["+valueLog + "]" +" - "+dateLog() + " - isi Result = "+result.toString());
			return result;
		}		
		return result;
	}
	
	//GET TOKEN BY STATUS 
	public List<TokenModel> getTokenByStatus (String input){
		String valueLog = logNumber();
		HttpHeaders header = new HttpHeaders();
		HttpEntity<?> entity = new HttpEntity<InputGetToken>(null, header);
		System.out.println("["+valueLog + "]" +" - "+dateLog() + " - String sebelum ResponseEntity");
		ResponseEntity<ResponseGetToken> respon = restTemplate.exchange("http://localhost:8089/tokenkeybca/token/" +input, HttpMethod.GET, entity, ResponseGetToken.class);
		List<TokenModel> result = respon.getBody().getOutputSchema();
		return result;
	}
}
