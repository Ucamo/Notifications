package carrillo.uriel.notifications;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, View.OnClickListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private Button btnMaxPriorityNotification;
    private Button btnHighPriorityNotification;
    private Button btnDefaultPriorityNotification;
    private Button btnLowPriorityNotification;
    private Button btnMinPriorityNotification;
    private Button btnDefaultNotification;
    private Button btnOldTypeNotification;
    private Button btnBigTextNotification;
    private Button btnBigImageNotification;
    private Button btnInboxTypeNotification;

    private int NOTIF_REF=1;
    private NotificationManager manager;
    private int count=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        //setting up the buttons
         btnMaxPriorityNotification = (Button)findViewById(R.id.btnMaxPriorityNotification);
         btnHighPriorityNotification = (Button)findViewById(R.id.btnHighPriorityNotification);
         btnDefaultPriorityNotification = (Button)findViewById(R.id.btnDefaultPriorityNotification);
         btnLowPriorityNotification = (Button)findViewById(R.id.btnLowPriorityNotification);
         btnMinPriorityNotification = (Button)findViewById(R.id.btnMinPriorityNotification);
         btnDefaultNotification = (Button)findViewById(R.id.btnDefaultNotification);
         btnOldTypeNotification = (Button)findViewById(R.id.btnOldTypeNotification);
         btnBigTextNotification = (Button)findViewById(R.id.btnBigTextNotification);
         btnBigImageNotification = (Button)findViewById(R.id.btnBigImageNotification);
         btnInboxTypeNotification = (Button)findViewById(R.id.btnInboxTypeNotification);

         manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

         btnMaxPriorityNotification.setOnClickListener(this);
         btnHighPriorityNotification.setOnClickListener(this);
         btnDefaultPriorityNotification.setOnClickListener(this);
         btnLowPriorityNotification.setOnClickListener(this);
         btnMinPriorityNotification.setOnClickListener(this);
         btnDefaultNotification.setOnClickListener(this);
         btnOldTypeNotification.setOnClickListener(this);
         btnBigTextNotification.setOnClickListener(this);
         btnBigImageNotification.setOnClickListener(this);
         btnInboxTypeNotification.setOnClickListener(this);

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v){
        Notification notif = null;
        Notification.Builder builder = new Notification.Builder(this).setSmallIcon(R.drawable.ic_launcher).setWhen(System.currentTimeMillis()).setContentText("Android Notifications");



        switch(v.getId()) {
            case R.id.btnMaxPriorityNotification:
                builder.setContentTitle("Maximun Priority Notification").setPriority(Notification.PRIORITY_MAX);
                sendNotification(builder.build());
                break;
            case R.id.btnHighPriorityNotification:
                builder.setContentTitle("High Priority Notification").setPriority(Notification.PRIORITY_HIGH);
                sendNotification(builder.build());
                break;
            case R.id.btnLowPriorityNotification:
                builder.setContentTitle("Low Priority Notification").setPriority(Notification.PRIORITY_LOW);
                sendNotification(builder.build());
                break;
            case R.id.btnMinPriorityNotification:
                builder.setContentTitle("Minimun Priority Notification").setPriority(Notification.PRIORITY_MIN);
                sendNotification(builder.build());
                break;

            case R.id.btnDefaultNotification:
                notif = getDefaultNotification(builder);
                sendNotification(notif);
                break;

            case R.id.btnOldTypeNotification:
                notif = getOldNotification();
                sendNotification(notif);
                break;

            case R.id.btnBigTextNotification:
                notif = getBigTextStyle(builder);
                sendNotification(notif);
                break;

            case R.id.btnBigImageNotification:
                notif = getBigPictureStyle(builder);
                sendNotification(notif);
                break;

            case R.id.btnInboxTypeNotification:
                inboxStyleNotifications();
                break;
        }
    }
    public void sendNotification(Notification notif){
        notif.defaults=Notification.DEFAULT_ALL;
        manager.notify(NOTIF_REF++, notif);
    }

    private Notification getDefaultNotification(Notification.Builder builder){
        builder
                .setSmallIcon(R.drawable.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setContentTitle("Default Notification")
                .setContentText("This is random text for default type notifications")
                .setContentInfo("Info");



        return builder.build();
    }

    private Notification getBigTextStyle(Notification.Builder builder){
        Bitmap icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.big_image);
        builder
                .setContentTitle("Reduced BigText title")
                .setContentText("Reduced content")
                .setContentInfo("Info")
                .setSmallIcon(R.drawable.ic_launcher)
                .setLargeIcon(icon);

        return  new Notification.BigTextStyle(builder)
                .bigText("")
                .setBigContentTitle("Android ATC")
                .setSummaryText(getResources().getString(R.string.summary_text))
                .build();

    }

    private Notification getBigPictureStyle(Notification.Builder builder){
        Bitmap icon = BitmapFactory.decodeResource(this.getResources(), R.drawable.big_image);
        builder
                .setContentTitle("Reduced BigPicture title")
                .setContentText("Reduced content")
                .setContentInfo("Info")
                .setSmallIcon(R.drawable.ic_launcher)
                .setLargeIcon(icon);

        return  new Notification.BigPictureStyle(builder)
                .bigPicture(icon)
                .bigLargeIcon(icon)
                .setBigContentTitle("Expanded BigPicture title")
                .setSummaryText(getResources().getString(R.string.summary_text))
                .build();
    }

    private void inboxStyleNotifications(){
        int ID=1;
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this).setSmallIcon(R.drawable.ic_launcher);
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setSummaryText(getResources().getString(R.string.summary_text));
        mBuilder.setStyle(inboxStyle);
        mBuilder.setNumber(1);

        NotificationManager mNotificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(ID, mBuilder.build());
    }

    public Notification getOldNotification() {
        Notification notif = new Notification(R.drawable.ic_launcher, null, System.currentTimeMillis());
       // notif.setLatestEventInfo(this, "old title", "Old notification content text", PendingIntent.getActivity(this, 0, new Intent(), 0));
        return notif;
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
