package android.battistim.it.busfinder;

import android.app.Activity;
import android.battistim.it.busfinder.pojos.BusStop;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import static android.battistim.it.busfinder.R.id.time;


public class MainActivity extends Activity {

    private TextView txtView;
    private static final String TAG_LOG = "BUSFINDER";

    /**
     * The request code for the bus stop pick
     */
    private static final int PICK_BUS_STOP_REQUEST_CODE = 1;
    private long mStartTime = 0L;
    Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.d(TAG_LOG, MainActivity.class.getName() + ": onCreate");
        //add a test comment
        // txtView = (TextView) this.findViewById(R.id.textView);
        Button btnInvio = (Button) this.findViewById(R.id.send_bus_stop_button);

        btnInvio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendBusStopData(view);
            }
        });


        //startTimer();
/*
        if (mStartTime == 0L) {
            mStartTime = System.currentTimeMillis();
            mHandler.removeCallbacks(mUpdateTimeTask);
            mHandler.postDelayed(mUpdateTimeTask, 100);
        }*/

    }




  private void startTimer()
   {
    //Declare the timer
    Timer t = new Timer();

    //Set the schedule function and rate
        t.scheduleAtFixedRate(
                new

    TimerTask() {
        int time = 0;

        @Override
        public void run () {
            //Called each time when 1000 milliseconds (1 second) (the period parameter)

            //We must use this function in order to change the text view text
            runOnUiThread(new Runnable() {


                @Override
                public void run() {
                    TextView tv = (TextView) findViewById(R.id.main_timer_text);
                    tv.setText(String.valueOf(time));
                    time += 1;
                }

            });
        }

    },
            //Set how long before to start calling the TimerTask (in milliseconds)
            3000,
            //Set the amount of time between each execution (in milliseconds)
            1000);

}

/*
    private Runnable mUpdateTimeTask = new Runnable()
    {
        public void run() {
            long start = 0;
            long millis = SystemClock.uptimeMillis() - start;
            TextView tv = (TextView) findViewById(R.id.main_timer_text);
            int seconds = (int) (millis / 1000);
            int minutes = seconds / 60;
            seconds     = seconds % 60;

            if (seconds < 10) {
                tv.setText("" + minutes + ":0" + seconds);
            } else {
                tv.setText("" + minutes + ":" + seconds);
            }

            mHandler.postAtTime(this,
                    start + (((minutes * 60) + seconds + 1) * 1000));
        }
    };*/


/*    public void sendBusStopData(final View view) {
        // We create a BudStop data
        final BusStop busStop = BusStop.Builder.create("1001", "BARBERINI")
                .withDirection("CENTRO")
                .withLocation(51.5085300f, -0.1257400f)
                .build();
        // We create the Intent
        final Intent intent = new Intent(this, BusStopActivity.class);
        // We set the data
        intent.putExtra(BusStop.keys.ID, busStop.id);
        intent.putExtra(BusStop.keys.NAME, busStop.name);
        intent.putExtra(BusStop.keys.DIRECTION, busStop.direction);
        intent.putExtra(BusStop.keys.LATITUDE, busStop.latitude);
        intent.putExtra(BusStop.keys.LONGITUDE, busStop.longitude);
        Log.d(TAG_LOG, "Extras : + " + intent.getExtras());
        // Launch the Activity
        startActivity(intent);
    }*/

    public void sendBusStopData(final View view) {
        // We create a BudStop data
        final BusStop busStop = BusStop.Builder.create("1001", "BARBERINI")
                .withDirection("CENTRO")
                .withLocation(51.5085300f, -0.1257400f)
                .build();
        // We create the Intent
        final Intent intent = new Intent(this, BusStopActivity.class);
        // We set the data
        intent.putExtra(BusStop.Keys.ID, busStop);

        // Launch the Activity
        startActivity(intent);
    }


    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

  */


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        switch(id)
        {
            case R.id.MENU_1:
            /*
                Codice di gestione della voce MENU_1
             */
                break;
            case R.id.MENU_2:
            /*
                Codice di gestione della voce MENU_2
             */
        }
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG_LOG, MainActivity.class.getName() + ": onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG_LOG, MainActivity.class.getName() + ": onResume");
    }

    @Override

    protected void onPause() {
        super.onPause();

         Log.d(TAG_LOG, MainActivity.class.getName() + ": onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG_LOG, MainActivity.class.getName() + ": onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG_LOG, MainActivity.class.getName() + ": onDestroy");


    }

    /**
     * Method invoked when we select the Button to pick a BusStop
     *
     * @param view The clicked View
     */
    public void pickBusStop(final View view) {
        // We create the Intent
        final Intent pickBusStopIntent = new Intent(BusStopListActivity.PICK_BUS_STOP_ACTION);
        // We pass the parameter for the location
        pickBusStopIntent.putExtra(BusStop.Keys.LATITUDE, 51.5085300f);
        pickBusStopIntent.putExtra(BusStop.Keys.LONGITUDE, -0.1257400f);
        // We launch the Intent
        startActivityForResult(pickBusStopIntent, PICK_BUS_STOP_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_BUS_STOP_REQUEST_CODE) {
            switch (resultCode) {
                case RESULT_OK:
                    // We got the result
                    final BusStop busStop = data.getParcelableExtra(BusStop.Keys.ID);
                    Toast.makeText(this, "BusStop:" + busStop, Toast.LENGTH_SHORT).show();
                    break;
                case RESULT_CANCELED:
                    // Operation cancelled
                    Toast.makeText(this, R.string.cancelled_operation, Toast.LENGTH_SHORT).show();
                    break;
                default:
                    // Other values
                    Toast.makeText(this, R.string.custom_operation, Toast.LENGTH_SHORT).show();
            }
        }
    }


}
