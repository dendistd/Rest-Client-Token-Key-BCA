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
import com.example.client.payload.cabang.OutputDataCabang;
import com.example.client.payload.cabang.create.InputCreateCabang;
import com.example.client.payload.cabang.get.ResponsesCabangs;
import com.example.client.payload.cabang.update.InputUpdateCabang;
import com.example.client.payload.cabang.delete.InputDeleteCabang;
import com.example.client.service.CabangService;
@Service
public class CabangServiceImple implements CabangService {
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
	
	//Get Cabang By Status
	public List<OutputDataCabang> getCabangByStatus(String input) {
		String valueLog = logNumber();
		HttpHeaders header = new HttpHeaders();
		HttpEntity<?> entity = new HttpEntity<>(null, header);
		System.out.println("["+valueLog + "]" +" - "+dateLog() + " - String Sebelum Response Entity");
		System.out.println("["+valueLog + "]" +" - "+dateLog() + " - Entity = " + entity.toString());
		ResponseEntity<ResponsesCabangs> respon = restTemplate.exchange("http://localhost:8089/tokenkeybca/cabang/" +input, HttpMethod.GET, entity, ResponsesCabangs.class);
		System.out.println("["+valueLog + "]" +" - "+dateLog() + " - isi ResponseEntity = " + respon.toString());
		List<OutputDataCabang> result = respon.getBody().getOutputSchema();
		System.out.println("["+valueLog + "]" +" - "+dateLog() + " - isi result = "+ result.toString());
		return result;
	}
	
	//Create Cabang
	public ConsumeSchema createCabang(InputCreateCabang input)  {
		String valueLog = logNumber();
		HttpHeaders header = new HttpHeaders();
		HttpEntity<InputCreateCabang> entity = new HttpEntity<InputCreateCabang>(input, header);
		System.out.println("["+valueLog + "]" +" - "+dateLog() + " - String Sebelum Response Entity");
		ConsumeSchema result = new ConsumeSchema();
		Map<String, String> map = new LinkedHashMap<String, String>();
		try {
		ResponseEntity<ConsumeSchema> respon = restTemplate.exchange("http://localhost:8089/tokenkeybca/cabang", HttpMethod.POST, entity, ConsumeSchema.class);
		System.out.println("["+valueLog + "]" +" - "+dateLog() + " - isi ResponseEntity = "+respon.toString());
		map.put("kode_cabang", respon.getBody().getOutputSchema().get("kode_cabang"));
		map.put("serial_number", respon.getBody().getOutputSchema().get("serial_number"));
		map.put("status", respon.getBody().getOutputSchema().get("status"));
		map.put("cabang", respon.getBody().getOutputSchema().get("cabang"));
		result.setOutputSchema(map);
		result.setErrorSchema(respon.getBody().getErrorSchema());
		System.out.println("["+valueLog + "]" +" - "+dateLog() + " - Isi Result = " + result.toString());
		} catch(Exception e) {
			System.out.println("["+valueLog + "]" +" - "+dateLog() + " - Masuk Catch, e  = " + e.getLocalizedMessage());
			map.put("reason", e.getMessage());
			result.setOutputSchema(map);
		}
		return result;
	}

	//UPDATE CABANG
	public ConsumeSchema updateCabang(InputUpdateCabang input) {
		String valueLog = logNumber();
		HttpHeaders header = new HttpHeaders();
		HttpEntity<InputUpdateCabang> entity = new HttpEntity<>(input, header);
		ConsumeSchema result = new ConsumeSchema();
		Map<String, String> map = new LinkedHashMap<>();
		System.out.println("["+valueLog + "]" +" - "+dateLog() + " - String Sebelum Response Entity");
		try {
			ResponseEntity<ConsumeSchema> respon = restTemplate.exchange("http://localhost:8089/tokenkeybca/cabang", HttpMethod.PUT, entity, ConsumeSchema.class);
			System.out.println("["+valueLog + "]" +" - "+dateLog() + " - isi ResponseEntity = "+respon.toString());
			result = respon.getBody();
			System.out.println("["+valueLog + "]" +" - "+dateLog() + " - Isi Result = " + result.toString());
		} catch(Exception e) {
			System.out.println("["+valueLog + "]" +" - "+dateLog() + " - Masuk Blok Catch");
			map.put("reason", e.getLocalizedMessage());
			result.setOutputSchema(map);
			System.out.println("["+valueLog + "]" +" - "+dateLog() + " - Isi Result = "+result.toString());
			return result;
		}
		return result;
	}
	
	//Delete Cabang By KodeCabang
	public ConsumeSchema deleteCabang (InputDeleteCabang input) {
		String valueLog = logNumber();
		HttpHeaders header = new HttpHeaders();
		HttpEntity<InputDeleteCabang> entity = new HttpEntity<>(input, header);
		ConsumeSchema result = new ConsumeSchema();
		Map<String, String> map = new LinkedHashMap<>();
		System.out.println("["+valueLog + "]" +" - "+dateLog() + " - String Sebelum Response Entity");
		
	try {
		System.out.println("["+valueLog + "]" +" - "+dateLog() + " - Masuk blok Try");
		ResponseEntity<ConsumeSchema> respon = restTemplate.exchange("http://localhost:8089/tokenkeybca/cabang", HttpMethod.DELETE, entity, ConsumeSchema.class);
		System.out.println("["+valueLog + "]" +" - "+dateLog() + " - isi ResponseEntity = "+respon.toString());
		result = respon.getBody();
		System.out.println("["+valueLog + "]" +" - "+dateLog() + " - Isi Result = " + result.toString());
	}catch(Exception e) {
		System.out.println("["+valueLog + "]" +" - "+dateLog() + " - Masuk Blok Catch");
		map.put("reason", e.getLocalizedMessage());
		result.setOutputSchema(map);
		System.out.println("["+valueLog + "]" +" - "+dateLog() + " - Isi Result = "+result.toString());
		return result;
	}
		return result;
	}

}
