package com.miguelpazo.rotime.views;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.miguelpazo.rotime.R;
import com.miguelpazo.rotime.models.Track;

/**
 * Created by Miguel R. Pazo Sánchez (miguelpazo.com) on 06/09/2015.
 */
public class FragMaps extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragMaps = inflater.inflate(R.layout.frag_maps, container, false);

        return fragMaps;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView tvDescription = (TextView) getView().findViewById(R.id.frag_maps_description);
        tvDescription.setText(MainActivity.oTrack.getDescription());
    }
}
