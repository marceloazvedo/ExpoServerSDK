package br.com.marceloazvedo.expo.test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import br.com.marceloazvedo.expo.Expo;
import br.com.marceloazvedo.expo.domain.exception.ExpoSDKException;
import br.com.marceloazvedo.expo.domain.exception.MessageTooBigException;
import br.com.marceloazvedo.expo.domain.request.ExpoPriority;
import br.com.marceloazvedo.expo.domain.request.ExpoPushMessage;
import br.com.marceloazvedo.expo.domain.response.ExpoMultipleResponse;
import br.com.marceloazvedo.expo.domain.response.ExpoSingleResponse;

public class TestSendNotification {

	private static final String[] EXPO_TOKENS = new String[] {
			"ExponentPushToken[iDTvJxE7n9Q19BHJS0S7Gi]",
			"ExponentPushToken[VFsdXaBA9nxKs_lalSZcSV]",
			"ExponentPushToken[TnyE4QJvOXCWo7aLdrNUki]"
	};

	private static final String NOTIFICATION_SOUND = "default";

	public static void sendToList() throws ExpoSDKException, MessageTooBigException, IOException {
		List<ExpoPushMessage> expoTokens = Arrays.stream(EXPO_TOKENS).map((expoToken) -> new ExpoPushMessage(expoToken, "{}",
						"TESTE DE NOTIFICAÇÃO LISTA",
						"XXXXXXXXXXXXXXXXXXXXLISTAXXXXXXXXXXXXXXXXXXXXXXXXX",
						null, null,
						ExpoPriority.HIGH, NOTIFICATION_SOUND,
						null, null)).collect(Collectors.toList());
		ExpoMultipleResponse response = Expo.getInstance().sendExpoPushMessages(expoTokens);
	}

	public static void sendToSingle() throws MessageTooBigException, ExpoSDKException, IOException {
		ExpoPushMessage expoPushMessage = new ExpoPushMessage(EXPO_TOKENS[1], "{}", "TESTE DE NOTIFICAÇÃO UNITÁRIA", "message", null, null, ExpoPriority.HIGH, null, null, null);
		ExpoSingleResponse response = Expo.getInstance().sendExpoPushMessage(expoPushMessage);
	}

	public static void main(String[] args) throws ExpoSDKException, ExecutionException, InterruptedException, MessageTooBigException, IOException {
		sendToList();
		sendToSingle();
	}
	
}
