package br.com.marceloazvedo.expo;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import br.com.marceloazvedo.expo.api.ExpoApi;
import br.com.marceloazvedo.expo.domain.exception.ExpoSDKException;
import br.com.marceloazvedo.expo.domain.exception.MessageTooBigException;
import br.com.marceloazvedo.expo.domain.request.ExpoPushMessage;
import br.com.marceloazvedo.expo.domain.request.RequestGetReceipts;
import br.com.marceloazvedo.expo.domain.response.ExpoMultipleResponse;
import br.com.marceloazvedo.expo.domain.response.ExpoSingleResponse;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Expo {
	
	/**
	 * The expo production base URL
	 */
	public static final String EXPO_PRODUCTION_URL = "https://exp.host";
	
	/**
	 * The expo api base url
	 */
	public static final String EXPO_BASE_API_URL = "/--/api/v2/";
	
	private ExpoApi expoApi = null;
	
	public Expo() {}

	/**
	 * This method instantiate an retrofit interface which is used in all lib
	 * 
	 * @param expoPushToken
	 * @return
	 * @throws ExpoSDKException
	 */
	public void connect() throws ExpoSDKException {
		
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(EXPO_PRODUCTION_URL + EXPO_BASE_API_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		
		this.expoApi = retrofit.create(ExpoApi.class);
		
	}
	
	public ExpoMultipleResponse sendExpoPushMessages(Collection<ExpoPushMessage> expoPushMessages) throws ExpoSDKException, IOException, MessageTooBigException {
		isConnected();
		
		/**
		 * Check if has more than allowed
		 */
		checkMessagesQuantities(expoPushMessages);
		
		/**
		 * Check if is a valid token and if message is too big
		 */
		for (ExpoPushMessage expoPushMessage : expoPushMessages) {
			this.isExpoPushToken(expoPushMessage.getTo());
			this.checkMessageLength(expoPushMessage);
		}
		
		ExpoMultipleResponse response = null;
		
		try {
			Response<ExpoMultipleResponse> responseCalled = this.expoApi.sendExpoPushMessages(expoPushMessages).execute();
			response = responseCalled.body();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		
		return response;
	}

	public ExpoSingleResponse sendExpoPushMessage(ExpoPushMessage expoPushMessage) throws ExpoSDKException, IOException, MessageTooBigException {
		isConnected();
		
		/**
		 * Check if is a valid token and if message is too big
		 */
		this.isExpoPushToken(expoPushMessage.getTo());
		this.checkMessageLength(expoPushMessage);
		
		ExpoSingleResponse response = null;
		try {
			Response<ExpoSingleResponse> responseCalled = this.expoApi.sendExpoPushMessage(expoPushMessage).execute();
			response = responseCalled.body();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		
		return response;
	}

	public Map<String, ExpoSingleResponse> getExpoReceipts(RequestGetReceipts requestGetReceipts) throws ExpoSDKException, IOException {
		isConnected();
		
		Map<String, ExpoSingleResponse> response = null;
		try {
			response = this.expoApi.getExpoReceipts(requestGetReceipts).execute().body();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		
		return response;
	}
	
	private void isConnected() throws ExpoSDKException {
		if (this.expoApi == null) {
			connect();
		}
	}
	
	private void isExpoPushToken(String expoPushToken) throws ExpoSDKException {
		
		if (expoPushToken == null || expoPushToken.isEmpty()) {
			throw new ExpoSDKException("You cannot create a connection without a push-token");
		}
		
		/**
		 * This condition is to know if the pushToken is a valid token
		 * Regex that verify the ExpoPushToken;
		 */
		if (!expoPushToken.startsWith("ExponentPushToken[") 
				|| !expoPushToken.endsWith("]")
			) 
		{
			throw new ExpoSDKException("Invalid ExponentPushToken. Valid example: 'ExponentPushToken[xxxxxxxxxxxxxxxxxxxxxx]' ");
		}
	}
	
	private void checkMessagesQuantities(Collection<ExpoPushMessage> messages) throws ExpoSDKException {
		if (messages.size() > 100) {
			throw new ExpoSDKException("There is more than 100 messages, limit is 100 messages per request");
		}
	}
	
	private void checkMessageLength(ExpoPushMessage message) throws MessageTooBigException {
		if (message.getBody().length() > 1024) {
			throw new MessageTooBigException("Body from " + message.getTo() + " token is too big. Limit is 1024 length.", null, null);
		}
	}


}
