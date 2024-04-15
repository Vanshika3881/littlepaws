package com.example.littlepaws;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationRequest;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    Location currentLocation;


    double currentLatitude, currentLongitude;


    GoogleMap gMap;
    Marker marker;

    SearchView searchView;
    FusedLocationProviderClient fusedClient;


    private static final int REQUEST_CODE = 101;
    private double lat,lng;
//    ImageButton ngo,shelter;


//    private GoogleMap gMap; // Corrected variable name

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


//        ngo=findViewById(R.id.NGO);
//        shelter=findViewById(R.id.SHELTER);

        searchView = findViewById(R.id.search);
        searchView.clearFocus();
        fusedClient = LocationServices.getFusedLocationProviderClient(this);
        getLocation();


//        shelter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                StringBuilder stringBuilder=new StringBuilder("http://maps.googleapis.com/maps/api/place/nearbysearch/json?");
//                stringBuilder.append("location="+ lat +","+lng);
//                stringBuilder.append("&radius=1000");
//                stringBuilder.append("&type=shelter");
//                stringBuilder.append("&sensor+true");
//                stringBuilder.append("&key=" + getResources().getString(R.string.google_maps_keys));
//
//
//                String url=stringBuilder.toString();
//                Object dataFetch[]=new Object[2];
//                Object mMap = null;
//                dataFetch[0]=mMap;
//                dataFetch[1]=url;
//                FetchData fetchData=new FetchData();
//                fetchData.execute(dataFetch);
//
//
//            }
//        });
//
//
//        ngo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                StringBuilder stringBuilder=new StringBuilder("http://maps.googleapis.com/maps/api/place/nearbysearch/json?");
//                stringBuilder.append("location="+ lat +","+lng);
//                stringBuilder.append("&radius=1000");
//                stringBuilder.append("&type=ngo");
//                stringBuilder.append("&sensor+true");
//                stringBuilder.append("&key=" + getResources().getString(R.string.google_maps_keys));
//
//
//                String url=stringBuilder.toString();
//                Object dataFetch[]=new Object[2];
//                Object mMap = null;
//                dataFetch[0]=mMap;
//                dataFetch[1]=url;
//                FetchData fetchData=new FetchData();
//                fetchData.execute(dataFetch);
//            }
//        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                String loc = searchView.getQuery().toString();
                if (loc == null) {
                    Toast.makeText(MapsActivity.this, "Location Not Found", Toast.LENGTH_SHORT).show();
                } else {
                    Geocoder geocoder = new Geocoder(MapsActivity.this, Locale.getDefault());
                    try {
                        List<Address> addressList = geocoder.getFromLocationName(loc, 1);
                        if (addressList.size() > 0) {
                            LatLng latLng = new LatLng(addressList.get(0).getLatitude(), addressList.get(0).getLongitude());
                            if (marker != null) {
                                marker.remove();
                            }
                            MarkerOptions markerOptions = new MarkerOptions().position(latLng).title(loc);
                            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 5);
                            gMap.animateCamera(cameraUpdate);
                            marker = gMap.addMarker(markerOptions);

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

//        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//
//        mapFragment.getMapAsync(this); // Set this activity as the OnMapReadyCallback
    }


    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;

        }

        Task<Location> task = fusedClient.getLastLocation();

        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;
                    SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    assert supportMapFragment != null;
                    supportMapFragment.getMapAsync(MapsActivity.this);
                }
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
//        gMap = googleMap; // Initialize gMap with the GoogleMap object
//
//        LatLng mapIndia = new LatLng(20.5937, 78.9629);
//
//        gMap.addMarker(new MarkerOptions().position(mapIndia).title("Marker in India"));
//        gMap.moveCamera(CameraUpdateFactory.newLatLng(mapIndia));
        this.gMap = googleMap;
        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("My Current Location");
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
        googleMap.addMarker(markerOptions);



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation();

            }
        }
    }
}


