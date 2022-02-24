package com.example.client.service;

import java.util.List;

import com.example.client.payload.ConsumeSchema;
import com.example.client.payload.cabang.OutputDataCabang;
import com.example.client.payload.cabang.create.InputCreateCabang;
import com.example.client.payload.cabang.delete.InputDeleteCabang;
import com.example.client.payload.cabang.get.ResponsesCabangs;
import com.example.client.payload.cabang.update.InputUpdateCabang;

public interface CabangService {

	//GER CABANG STATUS 
	public List<OutputDataCabang> getCabangByStatus(String input);
	
	//Create
	public ConsumeSchema createCabang(InputCreateCabang input);
	
	//Delete
	public ConsumeSchema deleteCabang (InputDeleteCabang input);
	
	//Update
	public ConsumeSchema updateCabang(InputUpdateCabang input);

}
