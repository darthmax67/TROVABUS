package android.battistim.it.busfinder;

import android.app.Activity;
import android.battistim.it.busfinder.pojos.BusStop;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class BusStopListActivity extends Activity {

    /**
     * The Action to use when we want to select a BusStop
     */
    public static final String PICK_BUS_STOP_ACTION = Const.PKG + ".action.PICK_BUS_STOP_ACTION";
    private static final String TAG_LOG = "BUSFINDER";
    private float mLatitude;

    private float mLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_stop_list);
        TextView txtView = (TextView) this.findViewById(R.id.textView4);
        // We get the Intent
        final Intent inputIntent = getIntent();
        if (inputIntent != null) {
            // We read input parameters
            mLatitude = inputIntent.getFloatExtra(BusStop.Keys.LATITUDE, 0.0f);
            mLongitude = inputIntent.getFloatExtra(BusStop.Keys.LONGITUDE, 0.0f);
            if (mLatitude != 0.0f && mLongitude != 0.0f) {
                // We do something with the data.
                final BusStop busStop = (BusStop) inputIntent.getParcelableExtra(BusStop.Keys.ID);
                Log.d(TAG_LOG, "Received: " + busStop);
                txtView.setText("Latitudine: " +mLatitude + "- Longitudine:" + mLongitude );
            } else {
                // We finish the Activity
                finish();
            }
        } else {
            // We finish the Activity
            finish();
        }
    }

    /**
     * We send the data back
     *
     * @param view The selected Button
     */
    public void sendBusStopBack(final View view) {
        final BusStop busStop = BusStop.Builder.create("1002", "VIENNA")
                .withLocation(mLatitude, mLongitude)
                .withDirection("NORD")
                .build();
        // The return intent
        final Intent backIntent = new Intent();
        backIntent.putExtra(BusStop.Keys.ID, busStop);
        // We send the result back
        setResult(RESULT_OK, backIntent);
        finish();
    }
}