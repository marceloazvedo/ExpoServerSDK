package br.com.marceloazvedo.expo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

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
	private static final String EXPO_PRODUCTION_URL = "es";

	/**
	 * The expo api base url
	 */
	private static final String EXPO_BASE_API_URL = "/--/api/v2/";

	/**
	 * Max number of messages per request
	 */
	private static final Integer MAX_NUMBER_MESSAGES = 100;

	private ExpoApi expoApi = null;

	private Expo() {
	}

	private static Expo instance = null;

	/**
	 * Expo is a Singletone, you just need to use this method to get the instance
	 * and use to send the notifications, for example.
	 * 
	 * @return Expo instance
	 */
	public static Expo getInstance() {
		if (instance == null) {
			instance = new Expo();
		}
		return instance;
	}

	private void connect() throws ExpoSDKException {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(EXPO_PRODUCTION_URL + EXPO_BASE_API_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		this.expoApi = retrofit.create(ExpoApi.class);
	}

	/**
	 * @param expoPushMessages Collection with all information about recipients and
	 *                         message content.
	 * 
	 * @return Return information about the sended message, like the http status
	 *         code, api message, code and errors.
	 * 
	 * @throws ExpoSDKException
	 */
	public ExpoMultipleResponse sendExpoPushMessages(List<ExpoPushMessage> expoPushMessages)
			throws ExpoSDKException, MessageTooBigException, IOException {
		isConnected();
		final int splitRequisitionCount = (expoPushMessages.size() / MAX_NUMBER_MESSAGES) + 1;
		List<ExpoPushMessage>[] expoPushMessagesSplited = new List[splitRequisitionCount];
		for (int i = 0; i < expoPushMessagesSplited.length; i++) {
			List<ExpoPushMessage> partExpoPushMessage = new ArrayList<>();
			final int INSIDE_PART_COUNT = i * MAX_NUMBER_MESSAGES;
			int insidePartMaxLength = expoPushMessages.size() - i * MAX_NUMBER_MESSAGES;
			if (insidePartMaxLength > MAX_NUMBER_MESSAGES) {
				insidePartMaxLength = MAX_NUMBER_MESSAGES;
			}
			for (int j = 0; j < insidePartMaxLength; j++) {
				final int INTERNAL_INDEX = j + INSIDE_PART_COUNT;
				partExpoPushMessage.add(expoPushMessages.get(INTERNAL_INDEX));
			}
			expoPushMessagesSplited[i] = partExpoPushMessage;
		}
		ExpoMultipleResponse expoMultipleResponse = new ExpoMultipleResponse();
		expoMultipleResponse.setData(new ArrayList<>());
		expoMultipleResponse.setErrors(new ArrayList<>());
		for (List<ExpoPushMessage> pushMessages : expoPushMessagesSplited) {
			for (ExpoPushMessage expoPushMessage : pushMessages) {
				this.isExpoPushToken(expoPushMessage.getTo());
				this.checkMessageLength(expoPushMessage);
			}
			Response<ExpoMultipleResponse> response = this.expoApi.sendExpoPushMessages(pushMessages).execute();
			ExpoMultipleResponse data = response.body();
			expoMultipleResponse.getData()
					.addAll(data != null && data.getData() != null ? data.getData() : new ArrayList<>());
			expoMultipleResponse.getErrors()
					.addAll(data != null && data.getErrors() != null ? data.getErrors() : new ArrayList<>());
		}
		return expoMultipleResponse;
	}

	/**
	 * @param expoPushMessage Has all information about the single notification
	 *                        sended, like who receive the message, the message,
	 *                        title message, and others.
	 * 
	 * @return Return information about the sended message, like the http status
	 *         code, api message, code and errors.
	 * 
	 * @throws ExpoSDKException
	 * @throws MessageTooBigException
	 */
	public ExpoSingleResponse sendExpoPushMessage(ExpoPushMessage expoPushMessage)
			throws ExpoSDKException, MessageTooBigException, IOException {
		isConnected();

		/**
		 * Validations Check if is a valid token, if data is a Json valid and if message
		 * is too big
		 */
		this.isExpoPushToken(expoPushMessage.getTo());
		this.isJsonData(expoPushMessage.getData());
		this.checkMessageLength(expoPushMessage);

		return this.expoApi.sendExpoPushMessage(expoPushMessage).execute().body();
	}

	public Map<String, ExpoSingleResponse> getExpoReceipts(RequestGetReceipts requestGetReceipts)
			throws ExpoSDKException, IOException {
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
		 * This condition is to know if the pushToken is a valid token Regex that verify
		 * the ExpoPushToken;
		 */
		if (!expoPushToken.startsWith("ExponentPushToken[") || !expoPushToken.endsWith("]")) {
			throw new ExpoSDKException(
					"Invalid ExponentPushToken. Valid example: 'ExponentPushToken[xxxxxxxxxxxxxxxxxxxxxx]' ");
		}
	}

	private void isJsonData(String jsonData) throws ExpoSDKException {
		try {
			JsonParser.parseString(jsonData);
		} catch (JsonSyntaxException ex) {
			throw new ExpoSDKException("The data you are trying do send is not a valid Json");
		}

	}

	private void checkMessageLength(ExpoPushMessage message) throws MessageTooBigException {
		if (message.getBody().length() > 1024) {
			throw new MessageTooBigException(
					"Body from " + message.getTo() + " token is too big. Limit is 1024 length.", null, null);
		}
	}

}
