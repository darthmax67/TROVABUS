package android.battistim.it.busfinder;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

public class SplashActivity extends Activity {

    private static final String TAG_LOG = "BUSFINDER";

    private static final long MAX_WAIT_INTERVAL = 3000L;
    private static final long MIN_WAIT_INTERVAL = 1000L;
    private static final int GO_AHEAD_WHAT = 1;

    private long mStartTime = -1L;
    private boolean mIsDone;
    private UiHandler mHandler;

    private static final String IS_DONE_KEY = "android.battistim.it.busfinder.IS_DONE_KEY";
    private static final String START_TIME_KEY = "android.battistim.it.busfinder.START_TIME_KEY";





    private static class UiHandler extends Handler
    {
        private WeakReference<SplashActivity> mActivityRef;

        public UiHandler(final SplashActivity srcActivity )
        {
            this.mActivityRef = new WeakReference<SplashActivity>(srcActivity);
        }

        @Override
        public void handleMessage(Message msg)
        {
            //super.handleMessage(msg);
            final SplashActivity srcActivity = mActivityRef.get();

            if (srcActivity ==null)
            {
                Log.d(TAG_LOG, SplashActivity.class.getName() + ": reference to SplashActivity is null");
                return;
            }

            switch (msg.what) {
                case GO_AHEAD_WHAT:
                    long elapsedTime = SystemClock.uptimeMillis() - srcActivity.mStartTime;
                    if(elapsedTime >= MIN_WAIT_INTERVAL && !srcActivity.mIsDone ) {

                        Log.d(TAG_LOG, SplashActivity.class.getName() + ":handleMessage: messaggio ricevuto");
                        srcActivity.mIsDone = true;
                        srcActivity.goAhead();
                    }
                    break;
            }

        }
    };


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(IS_DONE_KEY, mIsDone);
        outState.putLong(START_TIME_KEY, mStartTime);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.mIsDone = savedInstanceState.getBoolean(IS_DONE_KEY);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /*** nascondo i pulsanti di navigazione nello splash*/

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);

        //Se la persistenza nel cambiostato è stata salvata
        //recuperiamo i dati
        if(savedInstanceState != null)
            this.mStartTime = savedInstanceState.getLong(START_TIME_KEY);

        mHandler = new UiHandler(this);
        Log.d(TAG_LOG, SplashActivity.class.getName() + ":On create: Splash Avviato");

        // agiungiamo un evento di touch solo sull'Immagiene
        ImageView imgBus = (ImageView) findViewById(R.id.splashImage);
        imgBus.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(mStartTime >= MIN_WAIT_INTERVAL&& !mIsDone)
                {
                    mIsDone = true;
                    goAhead();
                }
                else
                    Log.d(TAG_LOG, SplashActivity.class.getName() + ": Touch prima del tempo");

                return false;
            }
        });

    }

    private void goAhead()
    {
        final Intent intent = new Intent(this, MainActivity.class );
        startActivity(intent);
        this.finish();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG_LOG, SplashActivity.class.getName() + ": onStart");
        if (mStartTime == -1L)
            mStartTime = SystemClock.uptimeMillis();

        final Message goAheadMessage;
        goAheadMessage = mHandler.obtainMessage(GO_AHEAD_WHAT);
        mHandler.sendMessageAtTime(goAheadMessage, mStartTime+ MAX_WAIT_INTERVAL);

        Log.d(TAG_LOG, SplashActivity.class.getName() + ": Handler message sent");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG_LOG, SplashActivity.class.getName() + ": onDestroy");
        mHandler.removeCallbacksAndMessages(null);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG_LOG, SplashActivity.class.getName() + ": onResume");
    }

    @Override

    protected void onPause() {
        super.onPause();

        Log.d(TAG_LOG, SplashActivity.class.getName() + ": onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG_LOG, SplashActivity.class.getName() + ": onStop");
    }
}
