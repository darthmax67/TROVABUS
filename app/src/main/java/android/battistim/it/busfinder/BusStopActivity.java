package android.battistim.it.busfinder;

import android.app.Activity;
import android.battistim.it.busfinder.pojos.BusStop;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.widget.TextView;


/**
 *
 */
public class BusStopActivity extends Activity {

  /**
   * Tag for the Log
   */
  //private static final String TAG = BusStopActivity.class.getSimpleName();
  private static final String TAG_LOG = "BUSFINDER";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bus_stop);
    final Intent srcIntent = getIntent();
    TextView txtView = (TextView) this.findViewById(R.id.textView1);

    if (srcIntent != null) {

        /*
      // Usage of literals
      final String id = srcIntent.getStringExtra(BusStop.keys.ID);
      final String name = srcIntent.getStringExtra(BusStop.keys.NAME);
      final String direction = srcIntent.getStringExtra(BusStop.keys.DIRECTION);
      final float latitude = srcIntent.getFloatExtra(BusStop.keys.LATITUDE, 0.0f);
      final float longitude = srcIntent.getFloatExtra(BusStop.keys.LONGITUDE, 0.0f);




      final BusStop busStop = BusStop.Builder.create(id, name)
              .withDirection(direction)
              .withLocation(latitude, longitude)
              .build();

      /*
      // Usage of the utility method
      final BusStop busStop = BusStop.fromIntent(srcIntent);
      */

      // In case of Serializable
      //final BusStop busStop = (BusStop) srcIntent.getSerializableExtra(BusStop.Keys.ID);

      // In case of Parcelable
      final BusStop busStop = (BusStop) srcIntent.getParcelableExtra(BusStop.Keys.ID);
      Log.d(TAG_LOG, "Received: " + busStop);
      txtView.setText(busStop.id + "- " + busStop.name );
    }
  }


  @Override
  protected void onStart() {
    super.onStart();
    Log.d(TAG_LOG, BusStopActivity.class.getName() + ": onStart");
  }

  @Override
  protected void onResume() {
    super.onResume();
    Log.d(TAG_LOG, BusStopActivity.class.getName() + ": onResume");
  }

  @Override

  protected void onPause() {
    super.onPause();

    Log.d(TAG_LOG, BusStopActivity.class.getName() + ": onPause");

  }

  @Override
  protected void onStop() {
    super.onStop();
    Log.d(TAG_LOG, BusStopActivity.class.getName() + ": onStop");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.d(TAG_LOG, BusStopActivity.class.getName() + ": onDestroy");


  }
}
