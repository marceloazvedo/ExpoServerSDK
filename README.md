# exponent-server-sdk-java
Exponent push notification Java library based on [Expo's node sdk](https://github.com/expo/expo-server-sdk-node)

## Installation

```
WIP
```

## Usage

Use to send push notifications to Exponent Experiences from a Java server application.

[Full documentation](https://docs.expo.io/versions/latest/guides/push-notifications.html#http2-api) on the API is available if you want to dive into the details.

Example usage
```
public class ExpoMainTest {
	
	public static void main(String[] args) throws ExpoSDKException{
        System.out.println("Initializing Test");
        
        if (args[0] == null) {
        	System.out.println("ExpoToken cannot be null in test");
        	throw new ExpoSDKException("ExpoToken cannot be null in test");
        }
        
        // Get the expo class
        Expo expo = new Expo();
        
        // Connects and configures rest caller interface
        expo.connect();
        
        //Create a list or single message
        ExpoPushMessage message = new ExpoPushMessage("ExponentPushToken[X0w5peGUalgByuJR26qEi2]", null, "Test message", "Test Message", null, null, null, "default", null, null);
        ExpoPushMessage message2 = new ExpoPushMessage()
        							.to("ExponentPushToken[X0w5peGUalgByuJR26qEi2]")
        							.title("Test message")
        							.body("Test Message")
        							.sound("default");
        
        Collection<ExpoPushMessage> messages = new LinkedList<ExpoPushMessage>();
        messages.add(message);
        messages.add(message2);
        
        
        
        try {
        	ExpoSingleResponse response = expo.sendExpoPushMessage(message2);
        	ExpoMultipleResponse multipleResponse = expo.sendExpoPushMessages(messages);
        	
      /**
			 * If the collection Erros inside of multipleResponse is != null and size > 0
			 * Then, the request failed
			 * 
			 * But if errors is empty or null this doesn't mean that there is no error in request
			 * you need to verify every "status" property in response
			 */
        	
			System.out.println(response.getData());
			
			
			System.out.println(multipleResponse.getData());
			
		} catch (MessageTooBigException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
        
        
	}	

}
```

## TODO

  * Unit tests

## See Also

  * https://github.com/exponent/exponent-server-sdk-ruby
  * https://github.com/exponent/exponent-server-sdk-python
  * https://github.com/exponent/exponent-server-sdk-node
  * https://github.com/oliveroneill/exponent-server-sdk-golang
