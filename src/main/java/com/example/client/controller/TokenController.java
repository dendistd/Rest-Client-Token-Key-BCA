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
import com.example.client.payload.cabang.update.InputUpdateCabang;
import com.example.client.payload.token.TokenModel;
import com.example.client.payload.token.create.InputCreateToken;
import com.example.client.payload.token.delete.InputDeleteToken;
import com.example.client.payload.token.get.InputGetToken;
import com.example.client.payload.token.get.ResponseGetToken;
import com.example.client.payload.token.update.InputUpdateToken;
import com.example.client.service.TokenService;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

@RestController
public class TokenController {
	@Autowired
	TokenService tokenService;
	
	@PostMapping("consume/tokenkeybca/token")
	public ResponseEntity<?> createToken (@RequestBody InputCreateToken input){
		ErrorSchema errorSchema = new ErrorSchema(ErrorEnum.CREATE);
		ResponseSchema<Map<String, String>> responseSchema = new ResponseSchema<>(errorSchema);
		ConsumeSchema result = new ConsumeSchema();
		System.out.println("Token Controller");
		result = tokenService.createToken(input);
		System.out.println("Controller-> isi result = "+result.toString());
		//GAGAL CREATE
		if(result.getErrorSchema() == null) {
			System.out.println("Controller => Masuk Blok IF Gagal");
			
			//SN IS ALREADY EXIST
			if(result.getOutputSchema().get("reason").contains("SN Is Already Exist On DB")) {
				System.out.println("Controller => SN Is Already Exist");
				ErrorSchema errorFail = new ErrorSchema(ErrorEnum.FAIL_CREATE);
				ResponseSchema<GagalOutputSchema> responFail = new ResponseSchema<>(errorFail);
				responFail.setOutputSchema(new GagalOutputSchema("SN Is Already Exist"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responFail);
			}
			
			//Status Value Is Incorrect !
			if(result.getOutputSchema().get("reason").contains("Status Value Is Incorrect !")) {
				System.out.println("Controller => Status Value Is Incorrect !");
				ErrorSchema errorFail = new ErrorSchema(ErrorEnum.FAIL_CREATE);
				ResponseSchema<GagalOutputSchema> responFail = new ResponseSchema<>(errorFail);
				responFail.setOutputSchema(new GagalOutputSchema("Status Value Is Incorrect !"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responFail);
			}
		}

		responseSchema.setOutputSchema(result.getOutputSchema());
		return ResponseEntity.status(HttpStatus.OK).body(responseSchema);
	}
	
	//Delete Token
	@DeleteMapping("consume/tokenkeybca/token")
	public ResponseEntity<?> deleteToken(@RequestBody InputDeleteToken input){
		ErrorSchema errorSchema = new ErrorSchema(ErrorEnum.DELETE);
		ResponseSchema<Map<String, String>> responseSchema = new ResponseSchema<>(errorSchema);
		ConsumeSchema result = new ConsumeSchema();
		Map<String, String> map = new LinkedHashMap<>();
		System.out.println("Token Controller");
		result = tokenService.deleteToken(input);
		System.out.println("CONTROLLER => token Service telah dipanggil");
		//Gagal Delete Token
		if(result.getErrorSchema() == null) {
			System.out.println("CONTROLLER => Masuk Blok IF Gagal Delete");
			//SN TIDAK ADA DALAM DB
			if(result.getOutputSchema().get("reason").contains("SN Tidak Ada Dalam DB")) {
				System.out.println("CONTROLLER => SN TIDAK ADA Dalam DB");
				ErrorSchema errorFail = new ErrorSchema(ErrorEnum.FAIL_DELETE);
				ResponseSchema<GagalOutputSchema> responFail = new ResponseSchema<>(errorFail);
				responFail.setOutputSchema(new GagalOutputSchema("SN Doesnt Exist On DB"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responFail);
			}
			//Can't Delete Data Because Status = 'AKTIF'
			if(result.getOutputSchema().get("reason").contains("Can't Delete Data Because Status = 'AKTIF'")) {
				System.out.println("CONTROLLER => Can't Delete Data Because Status = 'AKTIF'");
				ErrorSchema errorFail = new ErrorSchema(ErrorEnum.FAIL_DELETE);
				ResponseSchema<GagalOutputSchema> responFail = new ResponseSchema<>(errorFail);
				responFail.setOutputSchema(new GagalOutputSchema("Can't Delete Data Because Status = 'AKTIF'"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responFail);
			}
			//TIDAK BISA MENGHAPUS DATA YG SUDAH TERHAPUS
			if(result.getOutputSchema().get("reason").contains("400")) {
				System.out.println("CONTROLLER => Tidak Bisa Menghapus Data Yang Sudah Terhapus");
				ErrorSchema errorFail = new ErrorSchema(ErrorEnum.FAIL_DELETE);
				ResponseSchema<Map<String, String>> responFail = new ResponseSchema<Map<String,String>>(errorFail);
				map.put("id", "");
				map.put("serial_number", "");
				map.put("cabang", "");
				map.put("status", "");
				result.setOutputSchema(map);
				responFail.setOutputSchema(result.getOutputSchema());
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responFail);
			}
						
		}
		
		responseSchema.setOutputSchema(result.getOutputSchema());
		return ResponseEntity.status(HttpStatus.OK).body(responseSchema);
	}
	
	//GET TOKEN BY STATUS 
	@GetMapping("consume/tokenkeybca/token/{input}")
	public ResponseEntity<?> getTokenByStatus (@PathVariable("input") String input){
		ErrorSchema errorSchema = new ErrorSchema(ErrorEnum.GET_ALL);
		ResponseSchema<List<TokenModel>> responseSchema = new ResponseSchema<>(errorSchema);
		List<TokenModel> result = new ArrayList<TokenModel>();
		try {
			System.out.println("CONTROLLER => BLOK TRY");
			result = tokenService.getTokenByStatus(input);
			System.out.println("CONTROLLER => LAYER SERVICE TELAH DIPANGGIL");
			System.out.println("CONTROLLER => isi Result = "+result.toString());
		} catch (Exception e) {
			System.out.println("CONTROLLER => BLOK CATCH");
			ErrorSchema errorFail = new ErrorSchema(ErrorEnum.FAIL_GET_ALL);
			ResponseSchema<GagalOutputSchema> responFail = new ResponseSchema<GagalOutputSchema>(errorFail);
			responFail.setOutputSchema(new GagalOutputSchema(e.getLocalizedMessage()));
			System.out.println("CONTROLLER => isi responFail = "+responFail.toString());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responFail);
		}

		responseSchema.setOutputSchema(result);
		System.out.println("CONTROLLER => ISI RESPONSESCHEMA = "+responseSchema.toString());
		return ResponseEntity.status(HttpStatus.OK).body(responseSchema);
	}
	
	//UPDATE TOKEN
	@PutMapping("consume/tokenkeybca/token")
	public ResponseEntity<?> updateToken (@RequestBody InputUpdateToken input){
		ErrorSchema errorSchema = new ErrorSchema(ErrorEnum.UPDATE);
		ResponseSchema<Map<String, String>> responseSchema = new ResponseSchema<>(errorSchema);
		ConsumeSchema result = new ConsumeSchema();
		Map<String, String> map = new LinkedHashMap<>();
		System.out.println("Cabang Controller");
		result = tokenService.updateToken(input);
		if(result.getErrorSchema() == null) {
			System.out.println("Controller => Masuk Blok If Gagal Update");
			
			//SN TIDAK ADA DALAM DB
			if(result.getOutputSchema().get("reason").contains("null")) {
				System.out.println("Controller => SN TIDAK ADA DALAM DB");
				ErrorSchema errorFail = new ErrorSchema(ErrorEnum.FAIL_UPDATE);
				ResponseSchema<GagalOutputSchema> responFail = new ResponseSchema<>(errorFail);
				responFail.setOutputSchema(new GagalOutputSchema("Serial Number Doesnt Exist On DB"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responFail);
			}
			//KODE CABANG TIDAK ADA DALAM DB
			if(result.getOutputSchema().get("reason").contains("No value present")) {
				System.out.println("Controller => Kode Cabang TIDAK ADA DALAM DB");
				ErrorSchema errorFail = new ErrorSchema(ErrorEnum.FAIL_UPDATE);
				ResponseSchema<GagalOutputSchema> responFail = new ResponseSchema<>(errorFail);
				responFail.setOutputSchema(new GagalOutputSchema("Kode Cabang Doesnt Exist On DB"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responFail);
			}
			//Status KODE CABANG = 'HAPUS'
			if(result.getOutputSchema().get("reason").contains("Can't Update Cabang, Because Status Cabang is 'HAPUS'")) {
				System.out.println("Controller => Can't Update Cabang, Because Status Cabang is 'HAPUS'");
				ErrorSchema errorFail = new ErrorSchema(ErrorEnum.FAIL_UPDATE);
				ResponseSchema<GagalOutputSchema> responFail = new ResponseSchema<>(errorFail);
				responFail.setOutputSchema(new GagalOutputSchema("Can't Update Cabang, Because Status Cabang is 'HAPUS'"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responFail);
			}
			//Value Cabang String Kosong atau HANYA SPASI
			if(result.getOutputSchema().get("reason").contains("Value Cabang For Updating Data Is Incorrect !")) {
				System.out.println("Controller => Value Cabang String Kosong atau HANYA SPASI");
				ErrorSchema errorFail = new ErrorSchema(ErrorEnum.FAIL_UPDATE);
				ResponseSchema<GagalOutputSchema> responFail = new ResponseSchema<>(errorFail);
				responFail.setOutputSchema(new GagalOutputSchema("Value Cabang For Updating Data Is Incorrect !"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responFail);
			}
			//Value Status For Updating Data Is Incorrect !
			if(result.getOutputSchema().get("reason").contains("Value Status For Updating Data Is Incorrect !")) {
				System.out.println("Controller => Value Status TIDAK Memenuhi Syarat");
				ErrorSchema errorFail = new ErrorSchema(ErrorEnum.FAIL_UPDATE);
				ResponseSchema<GagalOutputSchema> responFail = new ResponseSchema<>(errorFail);
				responFail.setOutputSchema(new GagalOutputSchema("Value Status For Updating Data Is Incorrect !"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responFail);
			}
			//Tidak Ada Perubahan Data !
			if(result.getOutputSchema().get("reason").contains("Tidak Ada Perubahan Data !")) {
				System.out.println("Controller => Tidak Ada Perubahan Data !");
				ErrorSchema errorFail = new ErrorSchema(ErrorEnum.FAIL_UPDATE);
				ResponseSchema<GagalOutputSchema> responFail = new ResponseSchema<>(errorFail);
				responFail.setOutputSchema(new GagalOutputSchema("Tidak Ada Perubahan Data !"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responFail);
			}
		}

		responseSchema.setOutputSchema(result.getOutputSchema());
		System.out.println("CONTROLLER => ISI RESPONSESCHEMA = "+responseSchema.toString());
		return ResponseEntity.status(HttpStatus.OK).body(responseSchema);
	}
	
}