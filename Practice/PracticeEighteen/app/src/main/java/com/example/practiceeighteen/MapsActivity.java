package com.example.practiceeighteen;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.practiceeighteen.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);


        } else {
            Log.d("locdemo", "getLocation: permissions granted");
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng svsu = new LatLng(43.514, -83.962);
        Marker marker = mMap.addMarker(new MarkerOptions().position(svsu).title("Marker in SVSU"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(svsu));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(svsu, 15));

//        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        marker.setSnippet("7400 Bay Rd.");
        marker.setInfoWindowAnchor(0f, .5f);
        //marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.svsu));
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);


//        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(LatLng latLng) {
//                Toast.makeText(getApplicationContext(), ":D " + latLng, Toast.LENGTH_SHORT).show();
//                mMap.addMarker(new MarkerOptions().position(latLng));
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//            }
//        });

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        Hotel h = new Hotel("Cardinal Suites", "7400 Bay Rd.", R.drawable.svsu);
        marker.setTag(h);
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Hotel temp = (Hotel) marker.getTag();
                Toast.makeText(getApplicationContext(), "Location:\n" + temp.name, Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(i);
            }
        });

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.hotel_ui, null);
                TextView txtName = v.findViewById(R.id.txtName);
                TextView txtAddr = v.findViewById(R.id.txtAddr);
                ImageView imgLogo = v.findViewById(R.id.idLogo);

                Hotel temp = (Hotel) marker.getTag();

                txtName.setText(temp.name);
                txtAddr.setText(temp.addr);
                imgLogo.setImageResource(temp.imageId);
                return v;
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }
        });
    }

    class Hotel{
        String name;
        String addr;
        int imageId;

        public Hotel(String name, String addr, int imageId) {
            this.name = name;
            this.addr = addr;
            this.imageId = imageId;
        }
    }

}