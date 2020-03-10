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
		ExpoPushMessage expoPushMessage = new ExpoPushMessage("ExponentPushToken[VFsdXaBA9nxKs_lalSZcSV]", "Algum valor", "Essa é uma notificação", "Sanderson Sanderson", null, null, ExpoPriority.HIGH, null, null, null);
		Expo.getInstance().sendExpoPushMessage(expoPushMessage);
	}
	
}
