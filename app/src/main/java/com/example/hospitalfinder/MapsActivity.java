package com.example.hospitalfinder;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;

import java.util.Vector;
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback
{
    private GoogleMap mMap;
    MarkerOptions marker;
    LatLng centerlocation;
    Vector<MarkerOptions> markerOptions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        centerlocation = new LatLng(3.0,101);

        markerOptions = new Vector<>();

        markerOptions.add(new MarkerOptions().title("Sistem Hospital Awasan Taraf Sdn. Bhd. ")
                .position(new LatLng(6.44216,100.19120))
                .snippet("Open: 8am - 10pm")
        );
        markerOptions.add(new MarkerOptions().title("Hospital Tuanku Fauziah, Kangar")
                .position(new LatLng(6.44201,100.19133))
                .snippet("Open: 8am - 10pm")
        );
        markerOptions.add(new MarkerOptions().title("KPJ PERLIS SPECIALIST HOSPITAL (HOSPITAL PAKAR KPJ PERLIS)")
                .position(new LatLng(6.45068, 100.20552))
                .snippet("Open: 8am - 10pm")
        );
        markerOptions.add(new MarkerOptions().title("Pusat Bersalin Dan Poliklinik Perubatan Dr. Suraya ")
                .position(new LatLng(6.44105, 100.17115))
                .snippet("Open: 8am - 10pm")
        );
        markerOptions.add(new MarkerOptions().title("Zaharah Dialysis Center Sdn. Bhd.")
                .position(new LatLng(4.61, 101.88))
                .snippet("Open: 8am - 10pm")
        );
        markerOptions.add(new MarkerOptions().title("Jitra Hospital")
                .position(new LatLng(6.27904,100.41908))
                .snippet("Open: 8am - 10pm")
        );
        markerOptions.add(new MarkerOptions().title("Kedah Medical Center")
                .position(new LatLng(6.14903,100.36938))
                .snippet("Open: 8am - 10pm")
        );
        markerOptions.add(new MarkerOptions().title("Putra Medical Center")
                .position(new LatLng(6.12396, 100.36555))
                .snippet("Open: 8am - 10pm")
        );
        markerOptions.add(new MarkerOptions().title("Klinik Kesihatan Kangar")
                .position(new LatLng(6.43982, 100.19046))
                .snippet("Open: 8am - 10pm")
        );
        markerOptions.add(new MarkerOptions().title("Poliklinik Dr Azhar Dan Rakan-Rakan Kangar")
                .position(new LatLng(6.42384, 100.19739))
                .snippet("Open: 8am - 10pm")
        );
        markerOptions.add(new MarkerOptions().title("Chenang Clinic Cawangan Kangar ")
                .position(new LatLng(6.43326,100.19584))
                .snippet("Open: 8am - 10pm")
        );
        markerOptions.add(new MarkerOptions().title("Klinik Kasih")
                .position(new LatLng(6.45247,100.20599))
                .snippet("Open: 8am - 10pm")
        );
        markerOptions.add(new MarkerOptions().title("POLIKLINIK PERUBATAN UTARA. DR HAMDI ")
                .position(new LatLng(6.44578, 100.20089))
                .snippet("Open: 8am - 10pm")
        );
        markerOptions.add(new MarkerOptions().title("KLINIK REFFLESIA")
                .position(new LatLng(6.44561, 100.22145))
                .snippet("Open: 8am - 10pm")
        );
        markerOptions.add(new MarkerOptions().title("Klinik Dr. Mohadzir")
                .position(new LatLng(6.446387,  100.205246))
                .snippet("Open: 8am - 10pm")
        );

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
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

        for (MarkerOptions mark: markerOptions) {
            mMap.addMarker(mark);
        }

        enableMyLocation();

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centerlocation,8));
    }
    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
            }
        } else {
            String perms[] = {"android.permission.ACCESS_FINE_LOCATION"};
            // Permission to access the location is missing. Show rationale and request permission
            ActivityCompat.requestPermissions(this, perms,200);
        }
    }

}
