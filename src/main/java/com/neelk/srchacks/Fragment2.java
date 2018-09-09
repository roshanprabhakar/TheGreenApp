package com.neelk.srchacks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;

import static com.android.volley.VolleyLog.TAG;


public class Fragment2 extends Fragment implements OnMapReadyCallback {

    private ArrayList<ArrayList> placesInfo;
    private GoogleMap mMap;
    private double lat;
    private double lng;
    public Double[] centerLatitudes = new Double[3];
    public Double[] centerLongitudes = new Double[3];
    public String[] centerNames = new String[3];


    public Fragment2() {
        // Required empty public constructor
    }


    public static Fragment2 newInstance() {
        Fragment2 fragment = new Fragment2();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLatLong();
        NearbyCenters.findNearbyCenters();
        try {
           placesInfo =  NearbyCenters.parseCenterJson();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_2, container, false);
        if (this.mMap == null) {
            SupportMapFragment mapFrag = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
            mapFrag.getMapAsync(this);

        }

        return view;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        HomeFragment homeFragment = new HomeFragment();
        mMap = googleMap;
        LatLng userLOC = new LatLng(getLat(), getLng());
        mMap.addMarker(new MarkerOptions().position(userLOC).title("Current Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(userLOC));




        for (int i = 0; i < 3; i++) {
            LatLng LOC = new LatLng((Double) placesInfo.get(i).get(2), (Double) placesInfo.get(i).get(3));
            mMap.addMarker(new MarkerOptions().position(LOC).title( placesInfo.get(i).get(0) + "-" + placesInfo.get(i).get(1)));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(LOC));
        }
        CameraPosition camera_position = new CameraPosition.Builder().target(new LatLng(lat, lng)).zoom(10).build();
        CameraUpdate updateCamera = CameraUpdateFactory.newCameraPosition(camera_position);
        googleMap.animateCamera(updateCamera);

    }

    public void initLatLong() {
       /* LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if (location != null) {
                lat = location.getLatitude();
                lng = location.getLongitude();

            }*/
        lat = 37.548700;
        lng  = -122.058975;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

}

