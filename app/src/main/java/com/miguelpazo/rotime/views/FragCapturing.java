package com.miguelpazo.rotime.views;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.miguelpazo.rotime.R;

/**
 * Created by Miguel R. Pazo Sánchez (miguelpazo.com) on 20/09/2015.
 */
public class FragCapturing extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vFragment = inflater.inflate(R.layout.frag_capturing_track, container, false);
        return vFragment;
    }
}
