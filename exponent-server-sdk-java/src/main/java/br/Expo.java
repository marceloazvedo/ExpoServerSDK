package br;

import br.pb.jp.exponent.api.ExpoApi;
import br.pb.jp.exponent.exception.ExpoSDKException;
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
	
	
	/**
	 * This method returns an interface ExpoApi which
	 * is the workable caller to expo's endpoints
	 * 
	 * @param expoPushToken
	 * @return
	 * @throws ExpoSDKException
	 */
	public static ExpoApi getExpoConnection(String expoPushToken) throws ExpoSDKException {
		if (expoPushToken == null || expoPushToken.isEmpty()) {
			throw new ExpoSDKException("You cannot create a connection without a push-token");
		}
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(EXPO_PRODUCTION_URL)
				.build();
		
		return retrofit.create(ExpoApi.class);
		
	}


}
