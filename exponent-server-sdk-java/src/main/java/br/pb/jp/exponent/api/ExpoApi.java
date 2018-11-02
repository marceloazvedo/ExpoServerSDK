package br.pb.jp.exponent.api;

import java.util.List;
import java.util.Map;

import br.pb.jp.exponent.domain.request.ExpoPushMessage;
import br.pb.jp.exponent.domain.request.RequestGetReceipts;
import br.pb.jp.exponent.domain.response.ExpoResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ExpoApi {
	
	public final String EXPO_PUSH_TOKEN = null;
	
	
	@Headers({
		"accept: application/json",
		"accept-encoding: gzip, deflate",
		"content-type: application/json"
	})
	@POST("/push/send")
	Call<ExpoResponse> sendExpoPushMessages(@Body List<ExpoPushMessage> expoPushMessages);
	
	@Headers({
		"accept: application/json",
		"accept-encoding: gzip, deflate",
		"content-type: application/json"
	})
	@POST("/push/send")
	Call<ExpoResponse> sendExpoPushMessage(@Body ExpoPushMessage expoPushMessage);
	
	@Headers({
		"accept: application/json",
		"accept-encoding: gzip, deflate",
		"content-type: application/json"
	})
	@GET("/push/getReceipts")
	Call<Map<String, ExpoResponse>> getExpoReceipts(@Body RequestGetReceipts requestGetReceipts);
	
}
