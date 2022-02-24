package com.example.client.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.example.client.payload.cabang.OutputDataCabang;
import com.example.client.payload.cabang.create.InputCreateCabang;
import com.example.client.payload.cabang.create.ResponseCreateCabang;
import com.example.client.payload.cabang.delete.InputDeleteCabang;
import com.example.client.payload.cabang.get.ResponsesCabangs;
import com.example.client.payload.cabang.update.InputUpdateCabang;
import com.example.client.service.CabangService;

@RestController
public class CabangController {
	@Autowired
	CabangService cabangService;
	
	//GET CABANG BY STATUS 
	@GetMapping("consume/tokenkeybca/cabang/{input}")
	public ResponseEntity<?> getCabangByStatus(@PathVariable("input") String input){
		ErrorSchema errorSchema = new ErrorSchema(ErrorEnum.GET_ALL);
		ResponseSchema<List<OutputDataCabang>> responseSchema = new ResponseSchema<>(errorSchema);
		List<OutputDataCabang> result = new ArrayList<OutputDataCabang>();
		try {
			System.out.println("Cabang Controller => Masuk Blok Try");
			result = cabangService.getCabangByStatus(input);
			System.out.println("Cabang Controller => Layer Service Telah Dipanggil");
		} catch(Exception e) {
			System.out.println("Cabang Controller => Masuk Blok Catch");
			ErrorSchema errorFail = new ErrorSchema(ErrorEnum.FAIL_GET_ALL);
			ResponseSchema<GagalOutputSchema> responFail = new ResponseSchema<>(errorFail);
			responFail.setOutputSchema(new GagalOutputSchema(e.getMessage()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responFail);
		}
		responseSchema.setOutputSchema(result);
		return ResponseEntity.ok(responseSchema);
	}
	
	//CREATE
	@PostMapping("consume/tokenkeybca/cabang")
	public ResponseEntity<?> createCabang (@RequestBody InputCreateCabang input){
		ErrorSchema errorSchema = new ErrorSchema(ErrorEnum.CREATE);
		ResponseSchema<Map<String, String>> responseSchema = new ResponseSchema<>(errorSchema);
		ConsumeSchema result = new ConsumeSchema();
		System.out.println("Cabang Controller");
		result = cabangService.createCabang(input);
		System.out.println("Controller-> isi result = "+result.toString());
			
		//Gagal create -> Kode Cabang Sudah Ada dalam DB
		if(result.getErrorSchema() == null) {
			//KODE CABANG SUDAH ADA DALAM DB
			if(result.getOutputSchema().get("reason").contains("406")) {
				System.out.println("Controller => Reason : Kode Cabang Sudah Ada Dalam DB");
				ErrorSchema errorFail = new ErrorSchema(ErrorEnum.FAIL_CREATE);
				ResponseSchema<GagalOutputSchema> responFail = new ResponseSchema<>(errorFail);
				responFail.setOutputSchema(new GagalOutputSchema("Kode Cabang Sudah Ada Dalam DB"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responFail);
			}
			//INPUT VALUE STATUS TIDAK SESUAI
			if(result.getOutputSchema().get("reason").contains("400")) {
				System.out.println("Controller => Reason : Value Status Tidak Memenuhi Syarat");
				ErrorSchema errorFail = new ErrorSchema(ErrorEnum.FAIL_CREATE);
				ResponseSchema<GagalOutputSchema> responFail = new ResponseSchema<>(errorFail);
				responFail.setOutputSchema(new GagalOutputSchema("Value Status Tidak Memenuhi Syarat"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responFail);
			}
		}	
		responseSchema.setOutputSchema(result.getOutputSchema());
		return ResponseEntity.status(HttpStatus.OK).body(responseSchema);
	}
	
	//UPDATE CABANG
	@PutMapping("consume/tokenkeybca/cabang")
	public ResponseEntity<?> updateCabang (@RequestBody InputUpdateCabang input){
		ErrorSchema errorSchema = new ErrorSchema(ErrorEnum.UPDATE);
		ResponseSchema<Map<String, String>> responseSchema = new ResponseSchema<>(errorSchema);
		ConsumeSchema result = new ConsumeSchema();
		System.out.println("Cabang Controller");
		result = cabangService.updateCabang(input);
		if(result.getErrorSchema() == null) {
			System.out.println("Controller => Masuk Blok If Gagal Update");
			
			//Kondisi Kdoe Cabang TIDAK ADA Dalam Db
			if(result.getOutputSchema().get("reason").contains("null")) {
				System.out.println("Controller => Kode Cabang Tidak Ada Dalam DB");
				ErrorSchema errorFail = new ErrorSchema(ErrorEnum.FAIL_UPDATE);
				ResponseSchema<String> responFail = new ResponseSchema<>(errorFail);
				responFail.setOutputSchema("null");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responFail);
			}
			//STATUS VALUE TIDAK SESUAI
			if(result.getOutputSchema().get("reason").contains("Value Status Tidak Memenuhi Syarat")) {
				System.out.println("Controller => Value Status Tidak Memenuhi Syarat");
				ErrorSchema errorFail = new ErrorSchema(ErrorEnum.FAIL_UPDATE);
				ResponseSchema<GagalOutputSchema> responFail = new ResponseSchema<>(errorFail);
				responFail.setOutputSchema(new GagalOutputSchema("Value Status Tidak Memenuhi Syarat"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responFail);
			}			
			//TIDAK ADA PERUBAHAN DATA
			if(result.getOutputSchema().get("reason").contains("Tidak Ada Perubahan Data")) {
				System.out.println("Controller => Tidak Ada Perubahan Data");
				ErrorSchema errorFail = new ErrorSchema(ErrorEnum.FAIL_UPDATE);
				ResponseSchema<GagalOutputSchema> responFail = new ResponseSchema<>(errorFail);
				responFail.setOutputSchema(new GagalOutputSchema("Tidak Ada Perubahan Data"));
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responFail);
			}
		}
		responseSchema.setOutputSchema(result.getOutputSchema());
		return ResponseEntity.status(HttpStatus.OK).body(responseSchema);
	}
	
	@DeleteMapping("consume/tokenkeybca/cabang")
	public ResponseEntity<?> deleteCabang (@RequestBody InputDeleteCabang input){
		ErrorSchema errorSchema = new ErrorSchema(ErrorEnum.DELETE);
		ResponseSchema<Map<String, String>> responseSchema = new ResponseSchema<>(errorSchema);
		ConsumeSchema result = new ConsumeSchema();
		Map<String, String> map = new LinkedHashMap<>();
		System.out.println("Cabang Controller");		
		result = cabangService.deleteCabang(input);
		System.out.println("Controller-> isi result = "+result.toString());
		
		//Gagal Hapus
		if(result.getErrorSchema() == null) {
			System.out.println("Controller => Masuk Blok If Gagal Delete");
			//TIDAK BISA MENGHAPUS DATA YG SUDAH TERHAPUS
			if(result.getOutputSchema().get("reason").contains("400")) {
				System.out.println("Cabang Controller => Tidak Bisa Menghapus Data Yang Sudah Terhapus");
				ErrorSchema errorFail = new ErrorSchema(ErrorEnum.FAIL_DELETE);
				ResponseSchema<Map<String, String>> responseFail = new ResponseSchema<Map<String,String>>(errorFail);
				map.put("kode_cabang", "");
				map.put("nama_cabang", "");
				map.put("status", "");
				result.setOutputSchema(map);
				System.out.println("Cabang Controller => isi result = "+result.toString());
				responseFail.setOutputSchema(result.getOutputSchema());
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseFail);
			}
		}
		
		responseSchema.setOutputSchema(result.getOutputSchema());
		System.out.println("controller -> ResponseSchema = "+ responseSchema.toString());
		return ResponseEntity.status(HttpStatus.OK).body(responseSchema);

	}
}
