package br.com.marceloazvedo.expo.domain.request;


public class ExpoPushMessage {
	
	// Default constructor
	public ExpoPushMessage() {}
	
	public ExpoPushMessage(String to, String data, String title, String body, Integer ttl, Long expiration,
			ExpoPriority priority, String sound, Integer badge, String channelId) {
		this.to = to;
		this.data = data;
		this.title = title;
		this.body = body;
		this.ttl = ttl;
		this.expiration = expiration;
		this.priority = priority == null? ExpoPriority.DEFAULT.getPriority() : priority.getPriority();
		this.sound = sound;
		this.badge = badge;
		this.channelId = channelId;
	}

	/**
	 * An Expo push token specifying the recipient of this message.
	 */
	private String to = null;

	/**
	 * A JSON object delivered to your app. It may be up to about 4KiB; the total
	 * notification payload sent to Apple and Google must be at most 4KiB or else
	 * you will get a "Message Too Big" error.
	 */
	private String data = null;

	/**
	 * The title to display in the notification. Devices often display this in bold
	 * above the notification body. Only the title might be displayed on devices
	 * with smaller screens like Apple Watch.
	 */
	private String title = null;

	/**
	 * The message to display in the notification
	 */
	private String body = null;

	/**
	 * Time to Live: the number of seconds for which the message may be kept around
	 * for redelivery if it hasn't been delivered yet. Defaults to 0.
	 *
	 * On Android, we make a best effort to deliver messages with zero TTL
	 * immediately and do not throttle them
	 *
	 * This field takes precedence over `expiration` when both are specified.
	 */
	private Integer ttl = null;

	/**
	 * A timestamp since the UNIX epoch specifying when the message expires. This
	 * has the same effect as the `ttl` field and is just an absolute timestamp
	 * instead of a relative time.
	 */
	private Long expiration = null;

	/**
	 * The delivery priority of the message. Specify "default" or omit this field to
	 * use the default priority on each platform, which is "normal" on Android and
	 * "high" on iOS.
	 *
	 * On Android, normal-priority messages won't open network connections on
	 * sleeping devices and their delivery may be delayed to conserve the battery.
	 * High-priority messages are delivered immediately if possible and may wake
	 * sleeping devices to open network connections, consuming energy.
	 *
	 * On iOS, normal-priority messages are sent at a time that takes into account
	 * power considerations for the device, and may be grouped and delivered in
	 * bursts. They are throttled and may not be delivered by Apple. High-priority
	 * messages are sent immediately. Normal priority corresponds to APNs priority
	 * level 5 and high priority to 10.
	 */
	private String priority = ExpoPriority.DEFAULT.getPriority();

	// iOS-specific fields

	/**
	 * A sound to play when the recipient receives this notification. Specify
	 * "default" to play the device's default notification sound, or omit this field
	 * to play no sound.
	 *
	 * Note that on apps that target Android 8.0+ (if using `expo build`, built in
	 * June 2018 or later), this setting will have no effect on Android. Instead,
	 * use `channelId` and a channel with the desired setting.
	 * 
	 * possible values: 'default' or null
	 */
	private String sound = null;

	/**
	 * Number to display in the badge on the app icon. Specify zero to clear the
	 * badge.
	 */
	private Integer badge = null;

	// Android-specific fields

	/**
	 * ID of the Notification Channel through which to display this notification on
	 * Android devices. If an ID is specified but the corresponding channel does not
	 * exist on the device (i.e. has not yet been created by your app), the
	 * notification will not be displayed to the user.
	 *
	 * If left null, a "Default" channel will be used, and Expo will create the
	 * channel on the device if it does not yet exist. However, use caution, as the
	 * "Default" channel is user-facing and you may not be able to fully delete it.
	 */
	private String channelId = null;

	public String getTo() {
		return to;
	}

	public ExpoPushMessage to(String to) {
		this.to = to;
		return this;
	}

	public String getData() {
		return data;
	}

	public ExpoPushMessage data(String data) {
		this.data = data;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public ExpoPushMessage title(String title) {
		this.title = title;
		return this;
	}

	public String getBody() {
		return body;
	}

	public ExpoPushMessage body(String body) {
		this.body = body;
		return this;
	}

	public Integer getTtl() {
		return ttl;
	}

	public ExpoPushMessage ttl(Integer ttl) {
		this.ttl = ttl;
		return this;
	}

	public Long getExpiration() {
		return expiration;
	}

	public ExpoPushMessage expiration(Long expiration) {
		this.expiration = expiration;
		return this;
	}

	public String getPriority() {
		return priority;
	}

	public ExpoPushMessage priority(ExpoPriority priority) {
		this.priority = priority.getPriority();
		return this;
	}

	public String getSound() {
		return sound;
	}

	public ExpoPushMessage sound(String sound) {
		this.sound = sound;
		return this;
	}

	public Integer getBadge() {
		return badge;
	}

	public ExpoPushMessage badge(Integer badge) {
		this.badge = badge;
		return this;
	}

	public String getChannelId() {
		return channelId;
	}

	public ExpoPushMessage channelId(String channelId) {
		this.channelId = channelId;
		return this;
	}

}
