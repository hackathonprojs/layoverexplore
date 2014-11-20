package me.xbt.weartrial17;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubError;
import com.pubnub.api.PubnubException;

import org.json.JSONObject;

public class PhoneActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);

        Button wearButton = (Button)findViewById(R.id.wearButton);
        wearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyWatch();
            }
        });

        Button mapButton = (Button)findViewById(R.id.mapButton);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyMap101();
            }
        });

        subscribePubnub();
        subscribePubnubMap();
        subscribePubnubWeather();

    }

    private void notifyWatch() {
        notify5();
    }

    /**
     * create a notification.
     * using the simple way.
     */
    private void notify1() {
        int notificationId = 001;

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(PhoneActivity.this)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("Layover Activities")
                        .setContentText("View your recommended layover activities");

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(PhoneActivity.this);

        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    /**
     * create notification
     * using a slight different code to do notification from notify1(), but does basically the same thing.
     */
    private void notify2() {

        int notificationId = 001;
        final String EXTRA_EVENT_ID = "extra_event_id";
        String eventId = "1";
        String notiTitle = "Layover Activities";
        String notiText = "View your recommended layover activities";

        // Build intent for notification content
        Intent viewIntent = new Intent(this, PhoneActivity.class);
        viewIntent.putExtra(EXTRA_EVENT_ID, eventId);
        PendingIntent viewPendingIntent =
                PendingIntent.getActivity(this, 0, viewIntent, 0);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle(notiTitle)
                        .setContentText(notiText)
                        .setContentIntent(viewPendingIntent);

        // Get an instance of the NotificationManager service
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);

        // Build the notification and issues it with notification manager.
        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    /**
     * build a notification with action button that launches a map intent.
     */
    private void notify3() {
        int notificationId = 001;
        final String EXTRA_EVENT_ID = "extra_event_id";
        String eventId = "1";
        String notiTitle = "Layover Activities";
        String notiText = "View your recommended layover activities";
        String location = "1600 Amphitheatre Parkway, CA";

        // Build intent for notification content
        Intent viewIntent = new Intent(this, PhoneActivity.class);
        viewIntent.putExtra(EXTRA_EVENT_ID, eventId);
        PendingIntent viewPendingIntent =
                PendingIntent.getActivity(this, 0, viewIntent, 0);

        // Build an intent for an action to view a map
        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        Uri geoUri = Uri.parse("geo:0,0?q=" + Uri.encode(location));
        mapIntent.setData(geoUri);
        PendingIntent mapPendingIntent =
                PendingIntent.getActivity(this, 0, mapIntent, 0);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle(notiTitle)
                        .setContentText(notiText)
                        .setContentIntent(viewPendingIntent)
                        .addAction(R.drawable.ic_launcher,
                                getString(R.string.map), mapPendingIntent);

        // Get an instance of the NotificationManager service
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);

        // Build the notification and issues it with notification manager.
        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    /**
     * build a notification with action button that launches a map intent.
     * using the correct icon
     */
    private void notify4() {
        int notificationId = 001;
        final String EXTRA_EVENT_ID = "extra_event_id";
        String eventId = "1";
        String notiTitle = "Layover Activities";
        String notiText = "View your recommended layover activities";
        String location = "San Francisco International Airport";

        // Build intent for notification content
        Intent viewIntent = new Intent(this, PhoneActivity.class);
        viewIntent.putExtra(EXTRA_EVENT_ID, eventId);
        PendingIntent viewPendingIntent =
                PendingIntent.getActivity(this, 0, viewIntent, 0);

        // Build an intent for an action to view a map
        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        Uri geoUri = Uri.parse("geo:0,0?q=" + Uri.encode(location));
        mapIntent.setData(geoUri);
        PendingIntent mapPendingIntent =
                PendingIntent.getActivity(this, 0, mapIntent, 0);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_sail)
                        .setContentTitle(notiTitle)
                        .setContentText(notiText)
                        .setContentIntent(viewPendingIntent)
                        .addAction(R.drawable.ic_summer,
                                getString(R.string.activities), mapPendingIntent);

        // Get an instance of the NotificationManager service
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);

        // Build the notification and issues it with notification manager.
        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    /**
     * build a notification with action button that launches a browser intent.
     */
    private void notify5() {
        int notificationId = 001;
        final String EXTRA_EVENT_ID = "extra_event_id";
        String eventId = "1";
        String notiTitle = "Welcome to SF";
        String notiText = "Ready for your adventures!";
        //String location = "San Francisco International Airport";
        String url = "http://code.jeancarl.com/layoverexplore/experience.php";

        // Build intent for notification content
        Intent viewIntent = new Intent(this, PhoneActivity.class);
        viewIntent.putExtra(EXTRA_EVENT_ID, eventId);
        PendingIntent viewPendingIntent =
                PendingIntent.getActivity(this, 0, viewIntent, 0);

        // Build an intent for an action to view a map
        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        //Uri geoUri = Uri.parse("geo:0,0?q=" + Uri.encode(location));
        Uri httpUri = Uri.parse(url);
        mapIntent.setData(httpUri);
        PendingIntent mapPendingIntent =
                PendingIntent.getActivity(this, 0, mapIntent, 0);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_sail)
                        .setContentTitle(notiTitle)
                        .setContentText(notiText)
                        .setContentIntent(viewPendingIntent)
                        .addAction(R.drawable.ic_summer,
                                getString(R.string.activities), mapPendingIntent);

        // Get an instance of the NotificationManager service
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);

        // Build the notification and issues it with notification manager.
        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    /**
     * build a notification with action button that launches a map intent.
     * using the map icon.  with different id and different wordings
     */
    private void notifyMap100() {
        int notificationId = 002;
        final String EXTRA_EVENT_ID = "extra_event_id";
        String eventId = "2";
        String notiTitle = "Map";
        String notiText = "Navigate to the activity that you just purchased";
        String location = "San Francisco International Airport";

        // Build intent for notification content
        Intent viewIntent = new Intent(this, PhoneActivity.class);
        viewIntent.putExtra(EXTRA_EVENT_ID, eventId);
        PendingIntent viewPendingIntent =
                PendingIntent.getActivity(this, 0, viewIntent, 0);

        // Build an intent for an action to view a map
        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        Uri geoUri = Uri.parse("geo:0,0?q=" + Uri.encode(location));
        mapIntent.setData(geoUri);
        PendingIntent mapPendingIntent =
                PendingIntent.getActivity(this, 0, mapIntent, 0);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_wine)
                        .setContentTitle(notiTitle)
                        .setContentText(notiText)
                        .setContentIntent(viewPendingIntent)
                        .addAction(R.drawable.ic_map,
                                getString(R.string.map), mapPendingIntent);

        // Get an instance of the NotificationManager service
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);

        // Build the notification and issues it with notification manager.
        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    /**
     * build a notification with action button that launches a map intent.
     * using the map icon.  with different id and different wordings
     */
    private void notifyMap101() {
        int notificationId = 002;
        final String EXTRA_EVENT_ID = "extra_event_id";
        String eventId = "2";
        String notiTitle = "Map";
        String notiText = "Navigate to the activity that you just purchased";
        String location = "San Francisco International Airport";

        // Build intent for notification content
        Intent viewIntent = new Intent(this, PhoneActivity.class);
        viewIntent.putExtra(EXTRA_EVENT_ID, eventId);
        PendingIntent viewPendingIntent =
                PendingIntent.getActivity(this, 0, viewIntent, 0);

        // Build an intent for an action to view a map
        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        //Uri geoUri = Uri.parse("geo:0,0?q=" + Uri.encode(location));
        Uri geoUri = Uri.parse("http://map.spotvite.com/directions.html?lat=37.770171965079&long=-122.42546081543");
        mapIntent.setData(geoUri);
        PendingIntent mapPendingIntent =
                PendingIntent.getActivity(this, 0, mapIntent, 0);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_wine)
                        .setContentTitle(notiTitle)
                        .setContentText(notiText)
                        .setContentIntent(viewPendingIntent)
                        .addAction(R.drawable.ic_map,
                                getString(R.string.map), mapPendingIntent);

        // Get an instance of the NotificationManager service
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);

        // Build the notification and issues it with notification manager.
        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    /**
     * build a notification with action button that launches a map intent.
     * using the map icon.  with different id and different wordings
     */
    private void notifyMap102(String notiTitle, String notiText) {
        int notificationId = 002;
        final String EXTRA_EVENT_ID = "extra_event_id";
        String eventId = "2";
        //String notiTitle = "Map";
        //String notiText = "Navigate to the activity that you just purchased";
        String location = "San Francisco International Airport";

        // Build intent for notification content
        Intent viewIntent = new Intent(this, PhoneActivity.class);
        viewIntent.putExtra(EXTRA_EVENT_ID, eventId);
        PendingIntent viewPendingIntent =
                PendingIntent.getActivity(this, 0, viewIntent, 0);

        // Build an intent for an action to view a map
        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        //Uri geoUri = Uri.parse("geo:0,0?q=" + Uri.encode(location));
        Uri geoUri = Uri.parse("http://map.spotvite.com/directions.html?lat=37.770171965079&long=-122.42546081543");
        mapIntent.setData(geoUri);
        PendingIntent mapPendingIntent =
                PendingIntent.getActivity(this, 0, mapIntent, 0);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_wine)
                        .setContentTitle(notiTitle)
                        .setContentText(notiText)
                        .setContentIntent(viewPendingIntent)
                        .addAction(R.drawable.ic_map,
                                getString(R.string.map), mapPendingIntent);

        // Get an instance of the NotificationManager service
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);

        // Build the notification and issues it with notification manager.
        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    /**
     * subscribe to pubnub channel
     */
    private void subscribePubnub() {
        String pubkey = "demo";
        String subkey = "demo";
        String channel = "layoverfun";

        Pubnub pubnub = new Pubnub(pubkey, subkey);

        try {
            pubnub.subscribe(channel, new Callback() {

                        @Override
                        public void connectCallback(String channel, Object message) {
                            System.out.println("SUBSCRIBE : CONNECT on channel:" + channel
                                    + " : " + message.getClass() + " : "
                                    + message.toString());
                        }

                        @Override
                        public void disconnectCallback(String channel, Object message) {
                            System.out.println("SUBSCRIBE : DISCONNECT on channel:" + channel
                                    + " : " + message.getClass() + " : "
                                    + message.toString());
                        }

                        public void reconnectCallback(String channel, Object message) {
                            System.out.println("SUBSCRIBE : RECONNECT on channel:" + channel
                                    + " : " + message.getClass() + " : "
                                    + message.toString());
                        }

                        @Override
                        public void successCallback(String channel, Object message) {
                            System.out.println("pubnub success callback : " + channel + " : "
                                    + message.getClass() + " : " + message.toString());
                            String sMsg = message.toString();
                            notifyWatch();
                        }

                        @Override
                        public void errorCallback(String channel, PubnubError error) {
                            System.out.println("SUBSCRIBE : ERROR on channel " + channel
                                    + " : " + error.toString());
                        }
                    }
            );
        } catch (PubnubException e) {
            System.out.println(e.toString());
        }

        Callback callback = new Callback() {
            public void successCallback(String channel, Object response) {
                System.out.println(response.toString());
            }
            public void errorCallback(String channel, PubnubError error) {
                System.out.println(error.toString());
            }
        };
        pubnub.publish("demo", "Hello World !!" , callback);
    }

    /**
     * subscribe to pubnub channel
     */
    private void subscribePubnubMap() {
        String pubkey = "pub-c-0c67edc6-4b96-44f2-86bc-1b0ebb82a358";
        String subkey = "sub-c-f25f2e68-7a85-11e3-9cac-02ee2ddab7fe";
        String channel = "layovermap";

        Pubnub pubnub = new Pubnub(pubkey, subkey);

        try {
            pubnub.subscribe(channel, new Callback() {

                        @Override
                        public void connectCallback(String channel, Object message) {
                            System.out.println("SUBSCRIBE : CONNECT on channel:" + channel
                                    + " : " + message.getClass() + " : "
                                    + message.toString());
                        }

                        @Override
                        public void disconnectCallback(String channel, Object message) {
                            System.out.println("SUBSCRIBE : DISCONNECT on channel:" + channel
                                    + " : " + message.getClass() + " : "
                                    + message.toString());
                        }

                        public void reconnectCallback(String channel, Object message) {
                            System.out.println("SUBSCRIBE : RECONNECT on channel:" + channel
                                    + " : " + message.getClass() + " : "
                                    + message.toString());
                        }

                        @Override
                        public void successCallback(String channel, Object message) {
                            System.out.println("pubnub success callback : " + channel + " : "
                                    + message.getClass() + " : " + message.toString());
                            String sMsg = message.toString();

                            notifyMap102("Layover Explore", sMsg);
                        }

                        @Override
                        public void errorCallback(String channel, PubnubError error) {
                            System.out.println("SUBSCRIBE : ERROR on channel " + channel
                                    + " : " + error.toString());
                        }
                    }
            );
        } catch (PubnubException e) {
            System.out.println(e.toString());
        }

        Callback callback = new Callback() {
            public void successCallback(String channel, Object response) {
                System.out.println(response.toString());
            }
            public void errorCallback(String channel, PubnubError error) {
                System.out.println(error.toString());
            }
        };
        //pubnub.publish("demo", "Hello World !!" , callback);
    }

    private void subscribePubnubWeather() {
        String pubkey = "demo";
        String subkey = "demo";
        String channel = "layoverweather";

        Pubnub pubnub = new Pubnub(pubkey, subkey);

        try {
            pubnub.subscribe(channel, new Callback() {

                        @Override
                        public void connectCallback(String channel, Object message) {
                            System.out.println("SUBSCRIBE : CONNECT on channel:" + channel
                                    + " : " + message.getClass() + " : "
                                    + message.toString());
                        }

                        @Override
                        public void disconnectCallback(String channel, Object message) {
                            System.out.println("SUBSCRIBE : DISCONNECT on channel:" + channel
                                    + " : " + message.getClass() + " : "
                                    + message.toString());
                        }

                        public void reconnectCallback(String channel, Object message) {
                            System.out.println("SUBSCRIBE : RECONNECT on channel:" + channel
                                    + " : " + message.getClass() + " : "
                                    + message.toString());
                        }

                        @Override
                        public void successCallback(String channel, Object message) {
                            System.out.println("pubnub success callback : " + channel + " : "
                                    + message.getClass() + " : " + message.toString());
                            String sMsg = message.toString();

                            notifyWeather1();
                        }

                        @Override
                        public void errorCallback(String channel, PubnubError error) {
                            System.out.println("SUBSCRIBE : ERROR on channel " + channel
                                    + " : " + error.toString());
                        }
                    }
            );
        } catch (PubnubException e) {
            System.out.println(e.toString());
        }

        Callback callback = new Callback() {
            public void successCallback(String channel, Object response) {
                System.out.println(response.toString());
            }
            public void errorCallback(String channel, PubnubError error) {
                System.out.println(error.toString());
            }
        };
        //pubnub.publish("demo", "Hello World !!" , callback);
    }

    private void notifyWeather1() {
        int notificationId = 001;

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(PhoneActivity.this)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle("Cold weather.  62.7F")
                        .setContentText("Bring a jacket.");

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(PhoneActivity.this);

        notificationManager.notify(notificationId, notificationBuilder.build());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_phone, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
