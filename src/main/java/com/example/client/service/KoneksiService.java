package com.example.client.service;

import java.util.List;

import com.example.client.payload.ConsumeSchema;
import com.example.client.payload.koneksi.Koneksi;
import com.example.client.payload.koneksi.create.InputCreateKoneksi;
import com.example.client.payload.koneksi.delete.InputDeleteKoneksi;
import com.example.client.payload.koneksi.delete.ResponseDeleteKoneksiSchema;
import com.example.client.payload.koneksi.update.InputUpdateKoneksi;

public interface KoneksiService {
	//CREATE
	public ConsumeSchema createKoneksi (InputCreateKoneksi input);
	
	//Delete
	public ConsumeSchema deleteKoneksi (InputDeleteKoneksi input);
	
	//GET KONEKSI BY STATUS
	public List<Koneksi> getKoneksiByStatus(String input);
	
	//Update
	public ConsumeSchema updateKoneksi (InputUpdateKoneksi input);


}
