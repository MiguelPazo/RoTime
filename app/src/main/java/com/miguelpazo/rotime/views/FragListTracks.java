package com.miguelpazo.rotime.views;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.miguelpazo.rotime.R;
import com.miguelpazo.rotime.models.Track;
import com.miguelpazo.rotime.services.ServiceFactory;

import java.util.List;

/**
 * Created by Miguel R. Pazo Sánchez (miguelpazo.com) on 06/09/2015.
 */
public class FragListTracks extends Fragment {

    private ListView lvLocations;
    private List<Track> lstTrack;
    private IFragListTracks iFragListTracks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vFragment = inflater.inflate(R.layout.frag_list_tracks, container, false);
        lstTrack = ServiceFactory.getInstance().getServiceTrack().getListTracks();

        return vFragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        lvLocations = (ListView) getView().findViewById(R.id.fragment_list_traks);
        lvLocations.setAdapter(new LocationListView(this, lstTrack));
        lvLocations.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (iFragListTracks != null) {
                    iFragListTracks.onTrackSelected((Track) adapterView.getItemAtPosition(i));
                }
            }
        });
    }

    public void setiFragListTracks(IFragListTracks iFragListTracks) {
        this.iFragListTracks = iFragListTracks;
    }

    private class LocationListView extends ArrayAdapter<Track> {
        private Fragment oFragment;

        public LocationListView(Fragment oFragment, List arrTrack) {
            super(oFragment.getActivity(), 0, arrTrack);

            this.oFragment = oFragment;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View vFragElement = this.oFragment.getActivity().getLayoutInflater().inflate(R.layout.frag_list_tracks_element, null);
            TextView tvDescription = (TextView) vFragElement.findViewById(R.id.frag_track_description);
            TextView tvDate = (TextView) vFragElement.findViewById(R.id.frag_track_date);

            tvDescription.setText(getItem(position).getDescription());
//            tvDate.setText(getItem(position).getDate().toString());
            return vFragElement;
        }
    }

    public interface IFragListTracks {
        public void onTrackSelected(Track oTrack);
    }

}