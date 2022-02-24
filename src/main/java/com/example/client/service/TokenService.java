package com.example.client.service;
import java.util.List;
import com.example.client.payload.ConsumeSchema;
import com.example.client.payload.token.TokenModel;
import com.example.client.payload.token.create.InputCreateToken;
import com.example.client.payload.token.delete.InputDeleteToken;
import com.example.client.payload.token.get.InputGetToken;
import com.example.client.payload.token.get.ResponseGetToken;
import com.example.client.payload.token.update.InputUpdateToken;

public interface TokenService {
	//Create
	public ConsumeSchema createToken(InputCreateToken input);

	//Delete 
	public ConsumeSchema deleteToken (InputDeleteToken input);
	
	//GET TOKEN BY STATUS
	public List<TokenModel> getTokenByStatus(String input);
	
	//Update
	public ConsumeSchema updateToken (InputUpdateToken input);
}
