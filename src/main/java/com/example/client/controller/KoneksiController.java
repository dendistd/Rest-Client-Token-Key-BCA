package com.example.client.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.client.enumeration.ErrorEnum;
import com.example.client.payload.ConsumeSchema;
import com.example.client.payload.ErrorSchema;
import com.example.client.payload.GagalOutputSchema;
import com.example.client.payload.ResponseSchema;
import com.example.client.payload.koneksi.Koneksi;
import com.example.client.payload.koneksi.create.InputCreateKoneksi;
import com.example.client.payload.koneksi.create.ResponseCreateKoneksi;
import com.example.client.payload.koneksi.delete.InputDeleteKoneksi;
import com.example.client.payload.koneksi.delete.ResponseDeleteKoneksiSchema;
import com.example.client.payload.koneksi.update.InputUpdateKoneksi;
import com.example.client.payload.token.update.InputUpdateToken;
import com.example.client.service.KoneksiService;

@RestController
public class KoneksiController {
	@Autowired
	KoneksiService koneksiService;
	
	//CREATE KONEKSI
	@PostMapping("consume/tokenkeybca/koneksi")
	public ResponseEntity<?> createKoneksi(@RequestBody InputCreateKoneksi input){
		ErrorSchema errorSchema = new ErrorSchema(ErrorEnum.CREATE);
		ResponseSchema<Map<String, String>> responseSchema = new ResponseSchema<>(errorSchema);
		ConsumeSchema result = new ConsumeSchema();
		System.out.println("Koneksi Controller");
		result = koneksiService.createKoneksi(input);
		System.out.println("Koneksi Controller => Layer Service Telah Dipanggil");
		
		//GAGAL CREATE
		if(result.getErrorSchema() == null) {
			System.out.println("Controller => Masuk Blok IF Gagal");
					
			//Gagal Menyimpan Data, Input Param Tidak Memenuhi Syarat
			if(result.getOutputSchema().get("reason").contains("Gagal Menyimpan Data, Input Param Tidak Memenuhi Syarat")) {
				System.out.println("Controller => Gagal Menyimpan Data, Input Param Tidak Memenuhi Syarat");
				ErrorSchema errorFail = new ErrorSchema(ErrorEnum.FAIL_CREATE);
				ResponseSchema<GagalOutputSchema> responFail = new ResponseSchema<>(errorFail);
				responFail.setOutputSchema(new GagalOutputSchema("Gagal Menyimpan Data, Input Param Tidak Memenuhi Syarat"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responFail);
			}
			// SN TIDAK ADA DALAM DB
			if(result.getOutputSchema().get("reason").contains("SN Doesn't Exist On DB")) {
				System.out.println("Controller => SN Doesn't Exist On DB");
				ErrorSchema errorFail = new ErrorSchema(ErrorEnum.FAIL_CREATE);
				ResponseSchema<GagalOutputSchema> responFail = new ResponseSchema<>(errorFail);
				responFail.setOutputSchema(new GagalOutputSchema("SN Doesn't Exist On DB"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responFail);
			}
		}

		responseSchema.setOutputSchema(result.getOutputSchema());
		System.out.println("Koneksi Controller => Isi responseSchema = "+responseSchema.toString());
		return ResponseEntity.ok(result);
	}
	
	//Update koneksi
	@PutMapping("consume/tokenkeybca/koneksi")
	public ResponseEntity<?> updateToken (@RequestBody InputUpdateKoneksi input){
		ErrorSchema errorSchema = new ErrorSchema(ErrorEnum.UPDATE);
		ResponseSchema<Map<String, String>> responseSchema = new ResponseSchema<>(errorSchema);
		ConsumeSchema result = new ConsumeSchema();
		Map<String, String> map = new LinkedHashMap<>();
		System.out.println("Cabang Controller");
		result = koneksiService.updateKoneksi(input);
		if(result.getErrorSchema() == null) {
			System.out.println("Controller => Masuk Blok If Gagal Update");
			
			//ID TIDAK ADA DALAM DB
			if(result.getOutputSchema().get("reason").contains("No value present")) {
				System.out.println("Controller => Tidak Ada Perubahan Data");
				ErrorSchema errorFail = new ErrorSchema(ErrorEnum.FAIL_UPDATE);
				ResponseSchema<GagalOutputSchema> responFail = new ResponseSchema<>(errorFail);
				responFail.setOutputSchema(new GagalOutputSchema("ID Doesnt Exist On DB"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responFail);
			}
			//KONDISI TIDAK ADA UPDATE UNTUK SEMUA FIELD
			if(result.getOutputSchema().get("reason").contains("Tidak Ada Perubahan Data")) {
				System.out.println("Controller => Tidak Ada Perubahan Data");
				ErrorSchema errorFail = new ErrorSchema(ErrorEnum.FAIL_UPDATE);
				ResponseSchema<GagalOutputSchema> responFail = new ResponseSchema<>(errorFail);
				responFail.setOutputSchema(new GagalOutputSchema("Tidak Ada Perubahan Data"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responFail);
			}
			//TIDAK BISA UPDATE -> NILAI INPUT PARAM TIDAK MEMENUHI SYARAT
			if(result.getOutputSchema().get("reason").contains("Value Input Update Param Tidak Memenuhi SYarat")) {
				System.out.println("Controller => Value Input Update Param Tidak Memenuhi SYarat");
				ErrorSchema errorFail = new ErrorSchema(ErrorEnum.FAIL_UPDATE);
				ResponseSchema<GagalOutputSchema> responFail = new ResponseSchema<>(errorFail);
				responFail.setOutputSchema(new GagalOutputSchema("Value Input Update Param Tidak Memenuhi SYarat"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responFail);
			}
						
		}

		responseSchema.setOutputSchema(result.getOutputSchema());
		System.out.println("CONTROLLER => ISI RESPONSESCHEMA = "+responseSchema.toString());
		return ResponseEntity.status(HttpStatus.OK).body(responseSchema);
	}

	
	//DELETE KONEKSI
	@DeleteMapping("consume/tokenkeybca/koneksi")
	public ResponseEntity<?> deleteKoneksi(@RequestBody InputDeleteKoneksi input){
		ErrorSchema errorSchema = new ErrorSchema(ErrorEnum.DELETE);
		ResponseSchema<Map<String, String>> responseSchema = new ResponseSchema<>(errorSchema);
		Map<String, String> map = new LinkedHashMap<String, String>();
		ConsumeSchema result = new ConsumeSchema();
		System.out.println("Koneksi Controller");
		
		result = koneksiService.deleteKoneksi(input);
		if(result.getErrorSchema() == null) {
			if(result.getOutputSchema().get("reason").contains("400")) {
				ErrorSchema errorFail = new ErrorSchema(ErrorEnum.FAIL_DELETE);
				ResponseSchema<Map<String, String>> responseFail = new ResponseSchema<Map<String,String>>(errorFail);
				map.put("id", "");
				map.put("date_koneksi", null);
				map.put("serial_number", "");
				map.put("user_id", "");
				map.put("jenis_koneksi", "");
				map.put("status", "");
				result.setOutputSchema(map);
				responseFail.setOutputSchema(result.getOutputSchema());
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseFail);
			}
		}
		
		responseSchema.setOutputSchema(result.getOutputSchema());
		return ResponseEntity.ok(result);

	}
	
	//GET KONEKSI
	@GetMapping("consume/tokenkeybca/koneksi/{input}")
	public ResponseEntity<?> getKoneksi(@PathVariable("input") String input){
		ErrorSchema errorSchema = new ErrorSchema(ErrorEnum.GET_ALL);
		ResponseSchema<List<Koneksi>> responseSchema = new ResponseSchema<List<Koneksi>>(errorSchema);
		List<Koneksi> result = new ArrayList<Koneksi>();
		try {
			System.out.println("Koneksi Controller => Masuk Blok Try");
			result = koneksiService.getKoneksiByStatus(input);
			System.out.println("Koneksi Controller => Koneksi Service telah dipanggil");
		} catch(Exception e) {
			System.out.println("Koneksi Controller => Masuk Blok Catch");
			ErrorSchema errorFail = new ErrorSchema(ErrorEnum.FAIL_GET_ALL);
			ResponseSchema<GagalOutputSchema> responseFail = new ResponseSchema<GagalOutputSchema>(errorFail);
			responseFail.setOutputSchema(new GagalOutputSchema(e.getLocalizedMessage()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseFail);
		}
		responseSchema.setOutputSchema(result);
		return ResponseEntity.ok(responseSchema);
	}
	
}
