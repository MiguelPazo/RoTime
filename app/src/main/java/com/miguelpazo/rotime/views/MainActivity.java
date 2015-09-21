package com.miguelpazo.rotime.views;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.miguelpazo.rotime.R;
import com.miguelpazo.rotime.aServices.Tracking;
import com.miguelpazo.rotime.contracts.ContractPointLocation;
import com.miguelpazo.rotime.contracts.ContractTrack;
import com.miguelpazo.rotime.models.PointLocation;
import com.miguelpazo.rotime.models.Track;

import java.util.Calendar;
import java.util.List;


public class MainActivity extends Activity {

    public static List<PointLocation> lstLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FragListTracks fragListTracks = new FragListTracks();
        fragListTracks.setiFragListTracks(new FragListTracks.IFragListTracks() {

            @Override
            public void onTrackSelected(Track oTrack) {
//                lstLocation = ContractPointLocation.getListPointLocationByTrack(oTrack.getId(), getContentResolver());
                lstLocation = ContractPointLocation.getListPointLocation(getContentResolver());

                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    addFragment(R.id.activity_main_placeholder_maps, new FragMaps(), null);
                } else {
                    addFragment(R.id.activity_main_placeholder_main, new FragMaps(), "FRAG_MAPS");
                }
            }
        });

        addFragment(R.id.activity_main_placeholder_main, fragListTracks, null);

        final Button btnTracking = (Button) findViewById(R.id.button_tracking);

        btnTracking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isActive()) {
                    final Dialog dialog = new Dialog(MainActivity.this);
                    dialog.setContentView(R.layout.frag_new);
                    dialog.setCancelable(false);

                    final BootstrapButton btnCancel = (BootstrapButton) dialog.findViewById(R.id.btn_cancel_new);
                    final BootstrapButton btnOk = (BootstrapButton) dialog.findViewById(R.id.btn_begin_new);
                    final TextView txtDescription = (TextView) dialog.findViewById(R.id.tracking_description);
                    Calendar calendar = Calendar.getInstance();

                    txtDescription.setText("Ruta " + calendar.get(Calendar.DATE) + "_" + calendar.get(Calendar.MILLISECOND));

                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.hide();
                        }
                    });

                    btnOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Track oTrack = new Track();
                            oTrack.setDescription(txtDescription.getText().toString());
                            oTrack.setCreationTime(System.currentTimeMillis());

                            Integer idTrack = ContractTrack.insert(oTrack, getContentResolver());

                            btnTracking.setText(R.string.stop);
                            btnTracking.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_circle_stop));

                            dialog.hide();
                            addFragment(R.id.activity_main_placeholder_main, new FragCapturing(), null);

                            startService(new Intent(MainActivity.this, Tracking.class).putExtra("idTrack", idTrack));
                        }
                    });

                    dialog.show();
                } else {
                    addFragment(R.id.activity_main_placeholder_main, fragListTracks, null);

                    btnTracking.setText(R.string.start);
                    btnTracking.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_circle));

                    stopService(new Intent(MainActivity.this, Tracking.class));
                }
            }
        });
    }

    public void addFragment(int placeHolder, Fragment fragment, String flag) {
        getFragmentManager()
                .beginTransaction()
                .replace(placeHolder, fragment, flag)
                .addToBackStack(flag)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    private Boolean isActive() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (Tracking.class.getName().equals(service.service.getClassName())) {
                return true;
            }
        }

        return false;
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
