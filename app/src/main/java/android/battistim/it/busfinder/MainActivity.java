package android.battistim.it.busfinder;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends Activity {

    private TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       // txtView = (TextView) this.findViewById(R.id.textView);


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



}
