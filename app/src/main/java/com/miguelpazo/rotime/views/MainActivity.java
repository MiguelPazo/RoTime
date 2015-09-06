package com.miguelpazo.rotime.views;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.miguelpazo.rotime.R;
import com.miguelpazo.rotime.models.Track;


public class MainActivity extends Activity {

    public static Track oTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragListTracks fragListTracks = new FragListTracks();
        fragListTracks.setiFragListTracks(new FragListTracks.IFragListTracks() {
            @Override
            public void onTrackSelected(Track oTrackSelected) {
                oTrack = oTrackSelected;

                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    addFragment(R.id.activity_main_placeholder_maps, new FragMaps(), null);
                } else {
                    addFragment(R.id.activity_main_placeholder_main, new FragMaps(), "FRAG_MAPS");
                }
            }
        });

        addFragment(R.id.activity_main_placeholder_main, fragListTracks, null);
    }

    public void addFragment(int placeHolder, Fragment fragment, String flag) {
        getFragmentManager()
                .beginTransaction()
                .replace(placeHolder, fragment, flag)
                .addToBackStack(flag)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
