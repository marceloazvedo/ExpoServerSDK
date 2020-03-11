package br.com.marceloazvedo.expo.test;

import java.io.IOException;

import org.junit.Test;

import br.com.marceloazvedo.expo.Expo;
import br.com.marceloazvedo.expo.domain.exception.ExpoSDKException;
import br.com.marceloazvedo.expo.domain.exception.MessageTooBigException;
import br.com.marceloazvedo.expo.domain.request.ExpoPriority;
import br.com.marceloazvedo.expo.domain.request.ExpoPushMessage;

public class TestSendNotification {

	@Test
	public void send() throws MessageTooBigException, ExpoSDKException, IOException {
		ExpoPushMessage expoPushMessage = new ExpoPushMessage("ExponentPushToken[xxxxxxxxxxxxxxxxxxxxxx]", "{}", "Title", "message", null, null, ExpoPriority.HIGH, null, null, null);
		Expo.getInstance().sendExpoPushMessage(expoPushMessage);
	}
	
}
