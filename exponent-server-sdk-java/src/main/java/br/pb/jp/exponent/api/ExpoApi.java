package br.pb.jp.exponent.api;

import java.util.Collection;
import java.util.Map;

import br.pb.jp.exponent.domain.request.ExpoPushMessage;
import br.pb.jp.exponent.domain.request.RequestGetReceipts;
import br.pb.jp.exponent.domain.response.ExpoMultipleResponse;
import br.pb.jp.exponent.domain.response.ExpoSingleResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ExpoApi {
	
	public final String EXPO_PUSH_TOKEN = null;
	
	
	@Headers({
		"accept: application/json",
		"content-type: application/json"
	})
	// Endpoint starts without '/' to not be a absolutely path
	@POST("push/send")
	Call<ExpoMultipleResponse> sendExpoPushMessages(@Body Collection<ExpoPushMessage> expoPushMessages);
	
	@Headers({
		"accept: application/json",
		"content-type: application/json"
	})
	// Endpoint starts without '/' to not be a absolutely path
	@POST("push/send")
	Call<ExpoSingleResponse> sendExpoPushMessage(@Body ExpoPushMessage expoPushMessage);
	
	@Headers({
		"accept: application/json",
		"content-type: application/json"
	})
	// Endpoint starts without '/' to not be a absolutely path
	@GET("push/getReceipts")
	Call<Map<String, ExpoSingleResponse>> getExpoReceipts(@Body RequestGetReceipts requestGetReceipts);
	
}
