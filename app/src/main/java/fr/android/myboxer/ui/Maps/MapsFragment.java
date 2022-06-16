package fr.android.myboxer.ui.Maps;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import fr.android.myboxer.Database;
import fr.android.myboxer.Match;
import fr.android.myboxer.R;

public class MapsFragment extends Fragment {

    private GoogleMap mMap;
    private Database dBase = new Database();
    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {

            mMap = googleMap;
            LatLng MyPos;
            ArrayList<Match> matchs = dBase.getAllMatchs();
            for (Match m:
                 matchs) {
                MyPos = new LatLng(m.getLat(),m.getLng());
                mMap.addMarker(new MarkerOptions().position(MyPos).title(m.getMatchDescription()));
            }
            // Add a marker in Sydney and move the camera
            /*LatLng MyPos = new LatLng(48.8649865,2.3261923);
            LatLng MyPos2 = new LatLng(48.8816522,2.3542416);
            mMap.addMarker(new MarkerOptions().position(MyPos).title(getaddressFromLatLong(MyPos.latitude,MyPos.longitude)));
            mMap.addMarker(new MarkerOptions().position(MyPos2).title(getaddressFromLatLong(MyPos2.latitude,MyPos2.longitude)));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(MyPos));*/
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
    public String getaddressFromLatLong(double latitude,double longitude){
        Geocoder geocoder;
        Address location = null;
        geocoder = new Geocoder(this.getContext(), Locale.getDefault());
        try {
            location = geocoder.getFromLocation(latitude, longitude, 1).get(0); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        }
        catch (IOException e1){

        }

        String address = location.getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        return address;
    }
}