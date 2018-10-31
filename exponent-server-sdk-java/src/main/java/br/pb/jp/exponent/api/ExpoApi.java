package br.pb.jp.exponent.api;

import java.util.List;

import br.pb.jp.exponent.domain.ExpoPushMessage;
import br.pb.jp.exponent.domain.request.RequestGetReceipts;
import br.pb.jp.exponent.domain.response.Response;
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
	Call<Response> sendExpoPushMessages(@Body List<ExpoPushMessage> expoPushMessages);
	
	@Headers({
		"accept: application/json",
		"accept-encoding: gzip, deflate",
		"content-type: application/json"
	})
	@POST("/push/send")
	Call<Response> sendExpoPushMessage(@Body ExpoPushMessage expoPushMessage);
	
	@Headers({
		"accept: application/json",
		"accept-encoding: gzip, deflate",
		"content-type: application/json"
	})
	@GET("/push/send")
	Call<Response> getExpoReceipts(@Body RequestGetReceipts requestGetReceipts);
	
}
