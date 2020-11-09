package com.metacoders.cakri.Utils;

import com.google.firebase.messaging.FirebaseMessagingService;

public class FireBase_notification extends FirebaseMessagingService {

  /*  @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.e("NEW_TOKEN", s);



    }



    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

    // super.onMessageReceived(remoteMessage);
 //    sendNoti(remoteMessage.getNotification().getBody());




        Map<String, String> params = remoteMessage.getData();
        JSONObject object = new JSONObject(params);
        Log.e("JSON_OBJECT", object.toString());

        String NOTIFICATION_CHANNEL_ID = "Nilesh_channel";

        long pattern[] = {0, 1000, 500, 1000};

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Your Notifications",
                    NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.setDescription("");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(pattern);
            notificationChannel.enableVibration(true);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }

        // to diaplay notification in DND Mode
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = mNotificationManager.getNotificationChannel(NOTIFICATION_CHANNEL_ID);
            channel.canBypassDnd();
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);

        notificationBuilder.setAutoCancel(true)
                .setPriority(PRIORITY_MAX)
                .setColor(ContextCompat.getColor(this, R.color.colorAccent))
                .setContentTitle(getString(R.string.app_name))
                .setContentText(remoteMessage.getNotification().getBody())
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setAutoCancel(true);



        mNotificationManager.notify(1000, notificationBuilder.build());



    }
    private  void sendNoti(String Msgbody){
         Intent intent = new Intent(this, Home_Activity.class);
         intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
         PendingIntent pendingIntent;
        Uri deafultSonud = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder  notificationBuilder = new NotificationCompat.Builder(this );
        notificationBuilder.setSmallIcon(R.drawable.ic_stat_name);
        notificationBuilder.setContentText(Msgbody);
        notificationBuilder.setContentTitle("Home");
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setPriority(PRIORITY_MAX);
        notificationBuilder.setSound(deafultSonud);
        notificationBuilder.setContentIntent(PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT));


        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


            mNotificationManager.notify(10000,notificationBuilder.build());


    }
*/
}