package com.example.azusa.bokoboko;

/**
 * Created by AZUSA on 2016/06/15.
 */

import android.app.ActionBar;
import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Wearable;


public class MyActivity1 extends Activity  implements GoogleApiClient.ConnectionCallbacks, MessageApi.MessageListener {
    private static final String TAG = MyActivity1.class.getName();
    private final String[] SEND_MESSAGES = {"/Action/NONE", "/Action/PUNCH", "/Action/UPPER", "/Action/HOOK"};

    private GoogleApiClient mGoogleApiClient;
    private MySurfaceView2 mSurfaceView2;
    //private MySurfaceView2 mSurfaceView2;
    private SoundPool mSoundPool;
    private int mSE1, mSE2, mSE3;
    ActionBar ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my2);

        ab = getActionBar();


        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mSurfaceView2 = (MySurfaceView2) findViewById(R.id.surfaceView2_main);

        // ATTENTION: This "addApi(AppIndex.API)"was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult connectionResult) {
                        Log.d(TAG, "onConnectionFailed:" + connectionResult.toString());
                    }
                })
                .addApi(Wearable.API)
                .addApi(AppIndex.API).build();

//        Button punch = (Button)findViewById(R.id.button_p);
//        punch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mSurfaceView.punch();
//                mSoundPool.play(mSE1,1.0f, 1.0f, 0, 0, 1.0f);
//            }
//        });
//
//        Button upper = (Button)findViewById(R.id.button_u);
//        upper.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mSurfaceView.upper();
//                mSoundPool.play(mSE2, 1.0f, 1.0f, 0, 0, 1.0f);
//            }
//        });
//
//        Button hook = (Button)findViewById(R.id.button_h);
//        hook.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mSurfaceView.hook();
//                mSoundPool.play(mSE3, 1.0f, 1.0f, 0, 0, 1.0f);
//            }
//        });
    }

    public void hide_bar(View v) {
        ab.hide();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
        mSoundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        mSE1 = mSoundPool.load(this, R.raw.se1, 1);
        mSE2 = mSoundPool.load(this, R.raw.se2, 1);
        mSE3 = mSoundPool.load(this, R.raw.se3, 1);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "My Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.azusa.bokoboko/http/host/path")
        );
        AppIndex.AppIndexApi.start(mGoogleApiClient, viewAction);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "My Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.azusa.bokoboko/http/host/path")
        );
        AppIndex.AppIndexApi.end(mGoogleApiClient, viewAction);
        if (null != mGoogleApiClient && mGoogleApiClient.isConnected()) {
            Wearable.MessageApi.removeListener(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }
        mSoundPool.release();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d(TAG, "onConnected");
        Wearable.MessageApi.addListener(mGoogleApiClient, this);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "onConnectionSuspended");

    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d(TAG, "onMessageReceived : " + messageEvent.getPath());

        String msg = messageEvent.getPath();
        if (SEND_MESSAGES[1].equals(msg)) {
            mSurfaceView2.punch();
            mSoundPool.play(mSE1, 1.0f, 1.0f, 0, 0, 1.0f);
        } else if (SEND_MESSAGES[2].equals(msg)) {
            mSurfaceView2.upper();
            mSoundPool.play(mSE2, 1.0f, 1.0f, 0, 0, 1.0f);
        } else if (SEND_MESSAGES[3].equals(msg)) {
            mSurfaceView2.hook();
            mSoundPool.play(mSE3, 1.0f, 1.0f, 0, 0, 1.0f);
        }
    }
}
