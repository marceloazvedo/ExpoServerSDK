package br.pb.jp.exponent;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import br.pb.jp.exponent.api.ExpoApi;
import br.pb.jp.exponent.domain.exception.ExpoSDKException;
import br.pb.jp.exponent.domain.exception.MessageTooBigException;
import br.pb.jp.exponent.domain.request.ExpoPushMessage;
import br.pb.jp.exponent.domain.request.RequestGetReceipts;
import br.pb.jp.exponent.domain.response.ExpoResponse;
import retrofit2.Retrofit;

public class Expo {
	
	/**
	 * The expo production base URL
	 */
	public static final String EXPO_PRODUCTION_URL = "https://exp.host";
	
	/**
	 * The expo api base url
	 */
	public static final String EXPO_BASE_API_URL = "/--/api/v2";
	
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
				.build();
		
		this.expoApi = retrofit.create(ExpoApi.class);
		
	}
	
	public ExpoResponse sendExpoPushMessages(List<ExpoPushMessage> expoPushMessages) throws ExpoSDKException, IOException, MessageTooBigException {
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
		
		ExpoResponse response = null;
		
		try {
			response = this.expoApi.sendExpoPushMessages(expoPushMessages).execute().body();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		
		return response;
	}

	public ExpoResponse sendExpoPushMessage(ExpoPushMessage expoPushMessage) throws ExpoSDKException, IOException, MessageTooBigException {
		isConnected();
		
		/**
		 * Check if is a valid token and if message is too big
		 */
		this.isExpoPushToken(expoPushMessage.getTo());
		this.checkMessageLength(expoPushMessage);
		
		ExpoResponse response = null;
		try {
			response = this.expoApi.sendExpoPushMessage(expoPushMessage).execute().body();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		
		return response;
	}

	public Map<String, ExpoResponse> getExpoReceipts(RequestGetReceipts requestGetReceipts) throws ExpoSDKException, IOException {
		isConnected();
		
		Map<String, ExpoResponse> response = null;
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
				|| !expoPushToken.startsWith("]")
				|| !Pattern.matches("^[a-z\\d]{8}-[a-z\\d]{4}-[a-z\\d]{4}-[a-z\\d]{4}-[a-z\\d]{12}$", expoPushToken)) 
		{
			throw new ExpoSDKException("Invalid ExponentPushToken. Valid example: 'ExponentPushToken[xxxxxxxxxxxxxxxxxxxxxx]' ");
		}
	}
	
	private void checkMessagesQuantities(List<ExpoPushMessage> messages) throws ExpoSDKException {
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
