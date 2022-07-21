package com.example.hospitalfinder;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class next extends AppCompatActivity {
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    TextView name, email, address, coords;
    Button btn1, btn3, signOutBtn;

    //variable for address
    FusedLocationProviderClient fusedLocationProviderClient;

    //variable for server
    RequestQueue queue;



    final String URL = "http://atifcs251.site/hospitalfinder/api.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        queue = Volley.newRequestQueue(getApplicationContext());//server
        name = findViewById(R.id.tvname);
        email = findViewById(R.id.tvemail);
        address = findViewById(R.id.tvaddress);
        coords = findViewById(R.id.coord);
        signOutBtn = findViewById(R.id.signout);

        //Display Address
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(next.this);

        //display name and email (google)
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            name.setText(personName);
            email.setText(personEmail);
        }

        //ADDRESS CONDITION
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(next.this
                    , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                //when permission granted
                getLocation();


            } else {
                //when permission denied
                ActivityCompat.requestPermissions(next.this
                        , new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
            }
        }




        //all onclick event
        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
            }
        });

        btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), about.class);
                startActivity(intent);
            }
        });

        //sent data to database
        //String alamat2 = address.getText().toString();
        // String koords2 = coords.getText().toString();


    }

    private void getLocation() {
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                //initialize location
                Location location = task.getResult();
                if(location != null){
                    //initialize geocoder
                    Geocoder geocoder = new Geocoder(next.this, Locale.getDefault());
                    try {
                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(),location.getLongitude(),1
                        );

                        String alamat = addresses.get(0).getAddressLine(0);
                        String koords = "Lattitude " +addresses.get(0).getLatitude()+" "+"Longitude "+addresses.get(0).getLongitude();
                        address.setText(alamat);
                        coords.setText(koords);
                        makeRequest(alamat,koords);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

            }
        });
    }


    void signOut() {
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                finish();
                startActivity(new Intent(next.this, MainActivity.class));
            }
        });
    }



    //put into database
    public void makeRequest(String alamat, String koords) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();

            }
        }, errorListener){
            @Override
            protected Map<String,String> getParams (){
                Map <String, String> params = new HashMap<>();

                params.put("name", name.getText().toString());
                params.put("email", email.getText().toString());
                params.put("address",alamat);
                params.put("coords", koords);

                return params;
            }
        };
        queue.add(stringRequest);

    }

    public Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

        }
    };


}