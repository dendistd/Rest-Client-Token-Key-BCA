package com.example.client.service.imple;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.client.payload.ConsumeSchema;
import com.example.client.payload.koneksi.Koneksi;
import com.example.client.payload.koneksi.create.InputCreateKoneksi;
import com.example.client.payload.koneksi.create.ResponseCreateKoneksi;
import com.example.client.payload.koneksi.delete.InputDeleteKoneksi;
import com.example.client.payload.koneksi.delete.ResponseDeleteKoneksiSchema;
import com.example.client.payload.koneksi.get.ResponseGetKoneksiSchema;
import com.example.client.payload.koneksi.update.InputUpdateKoneksi;
import com.example.client.service.KoneksiService;

@Service
public class KoneksiServiceImple implements KoneksiService {
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
	
	//CREATE KONEKSI
	public ConsumeSchema createKoneksi (InputCreateKoneksi input) {
		String valueLog = logNumber();
		HttpHeaders header = new HttpHeaders();
		HttpEntity<InputCreateKoneksi> entity = new HttpEntity<>(input,header);
		ConsumeSchema result = new ConsumeSchema();
		Map<String, String> map = new LinkedHashMap<>();
		System.out.println("["+valueLog + "]" +" - "+dateLog() + " - String sebelum ResponseEntity");
		
		try {
			System.out.println("["+valueLog + "]" +" - "+dateLog() + " - Masuk Blok Try");
			ResponseEntity<ConsumeSchema> respon = restTemplate.exchange("http://localhost:8089/tokenkeybca/koneksi", HttpMethod.POST, entity, ConsumeSchema.class);
			System.out.println("["+valueLog + "]" +" - "+dateLog() + " - Isi ResponseEntity = "+respon.toString());
			result = respon.getBody();
			System.out.println("["+valueLog + "]" +" - "+dateLog() + " - Isi result = "+result.toString());
	
		} catch(Exception e) {
			System.out.println("["+valueLog + "]" +" - "+dateLog() + " - Masuk Blok Catch");
			map.put("reason", e.getMessage());
			result.setOutputSchema(map);
			System.out.println("["+valueLog + "]" +" - "+dateLog() + " - Isi result = "+result.toString());
			return result;
		}
		return result;
	}

	//UPDATE KONEKSI
	public ConsumeSchema updateKoneksi (InputUpdateKoneksi input) {
		String valueLog = logNumber();
		HttpHeaders header = new HttpHeaders();
		HttpEntity<InputUpdateKoneksi> entity = new HttpEntity<>(input,header);
		ConsumeSchema result = new ConsumeSchema();
		Map<String, String> map = new LinkedHashMap<>();		
		try {
			System.out.println("["+valueLog + "]" +" - "+dateLog() + " - Masuk Blok Try");
			ResponseEntity<ConsumeSchema> respon = restTemplate.exchange("http://localhost:8089/tokenkeybca/koneksi", HttpMethod.PUT, entity, ConsumeSchema.class);
			System.out.println("["+valueLog + "]" +" - "+dateLog() + " - Isi ResponseEntity = "+respon.toString());
			result = respon.getBody();
			System.out.println("["+valueLog + "]" +" - "+dateLog() + " - Isi result = "+result.toString());
	
		} catch(Exception e) {
			System.out.println("["+valueLog + "]" +" - "+dateLog() + " - Masuk Blok Catch");
			map.put("reason", e.getMessage());
			result.setOutputSchema(map);
			System.out.println("["+valueLog + "]" +" - "+dateLog() + " - Isi result = "+result.toString());
			return result;
		}
		return result;
	}
	
	//DELETE KONEKSI
	public ConsumeSchema deleteKoneksi (InputDeleteKoneksi input) {
		String valueLog = logNumber();
		HttpHeaders header = new HttpHeaders();
		HttpEntity<InputDeleteKoneksi> entity = new HttpEntity<>(input,header);
		ConsumeSchema result = new ConsumeSchema();
		Map<String, String> map = new LinkedHashMap<String, String>();
		System.out.println("["+valueLog + "]" +" - "+dateLog() + " - String sebelum ResponseEntity");
	try {
		ResponseEntity<ConsumeSchema> respon = restTemplate.exchange("http://localhost:8089/tokenkeybca/koneksi", HttpMethod.DELETE, entity, ConsumeSchema.class);
		System.out.println("["+valueLog + "]" +" - "+dateLog() + " - isi ResponseEntity = "+respon.toString());
		result = respon.getBody();
		System.out.println("["+valueLog + "]" +" - "+dateLog() + " - isi Result = "+result.toString());
	} catch(Exception e) {
		System.out.println("["+valueLog + "]" +" - "+dateLog() + " - Masuk Blok Catch");
		map.put("reason", e.getMessage());
		result.setOutputSchema(map);
		System.out.println("["+valueLog + "]" +" - "+dateLog() + " - Isi result = "+result.toString());
		return result;
	}
		return result;
	}
	
	//GET KONEKSI BY STATUS
	public List<Koneksi> getKoneksiByStatus(String input){
		String valueLog = logNumber();
		HttpHeaders header = new HttpHeaders();
		HttpEntity<?> entity = new HttpEntity<>(null,header);
		System.out.println("["+valueLog + "]" +" - "+dateLog() + " - String sebelum ResponseEntity");
		ResponseEntity<ResponseGetKoneksiSchema> respon = restTemplate.exchange("http://localhost:8089/tokenkeybca/koneksi/" + input.toUpperCase(), HttpMethod.GET, entity, ResponseGetKoneksiSchema.class);
		System.out.println("["+valueLog + "]" +" - "+dateLog() + " - isi ResponseEntity = "+respon.toString());
		List<Koneksi> result = respon.getBody().getOutputSchema();
		return result;
	}
}
