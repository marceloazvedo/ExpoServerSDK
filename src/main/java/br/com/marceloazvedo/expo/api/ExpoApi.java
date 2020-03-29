package br.com.marceloazvedo.expo.api;

import java.util.Collection;
import java.util.Map;

import br.com.marceloazvedo.expo.domain.request.ExpoPushMessage;
import br.com.marceloazvedo.expo.domain.request.RequestGetReceipts;
import br.com.marceloazvedo.expo.domain.response.ExpoMultipleResponse;
import br.com.marceloazvedo.expo.domain.response.ExpoSingleResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ExpoApi {

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
