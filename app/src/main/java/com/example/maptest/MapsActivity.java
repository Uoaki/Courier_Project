package com.example.maptest;


import com.google.android.gms.maps.MapFragment;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import androidx.fragment.app.FragmentActivity;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
//    public class Stack {
//        private int MAX_SIZE;
//        private int[] stack;
//        private int top;
//
//        public Stack() {
//            MAX_SIZE = 5;
//            stack = new int[MAX_SIZE];
//            top = -1;
//        }
//
//        private boolean isEmpty() {
//            return top == -1 ? true : false;
//        }
//        private boolean isFull() {
//            return (top + 1 == MAX_SIZE) ? true : false;
//        }
//
//        public void push(int data) {
//            if (!isFull())
//                stack[++top] = data;
//        }
//
//        public int pop() {
//            if (!isEmpty())
//                return stack[top--];
//            return -1;
//        }
//
//        public void display() {
//            System.out.print("top : " + top + "\nstack : ");
//            for (int idx = 0; idx <= top; idx++)
//                System.out.print(stack[idx] + " ");
//            System.out.println();
//        }
//    }
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        // 맵 터치 이벤트 구현 //
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener(){
            @Override
            public void onMapClick(LatLng point) {

                Location locationA = new Location("point A");
                locationA.setLatitude(point.latitude);
                locationA.setLongitude(point.longitude);
                MarkerOptions mOptions = new MarkerOptions();
                // 마커 타이틀
                mOptions.title("마커 좌표");
                Double latitude = point.latitude; // 위도
                Double longitude = point.longitude; // 경도
                // 마커의 스니펫(간단한 텍스트) 설정
                mOptions.snippet("point A : " + latitude.toString() + ", " + longitude.toString());
                // LatLng: 위도 경도 쌍을 나타냄
                mOptions.position(new LatLng(latitude, longitude));
                // 마커(핀) 추가
                googleMap.addMarker(mOptions);
//                double[] locations = new double[locations.length];
//                locations.push(locationA);
//                Location locations = new Location("point B");
//                locations.setLatitude(point.latitude);
//                locations.setLongitude(point.longitude);
//                mOptions.snippet("point B : " + latitude.toString() + ", " + longitude.toString());
//                mOptions.position(new LatLng(latitude, longitude));

                Location hongic = new Location("hongik");
                hongic.setLatitude(36.62722650921884);
                hongic.setLongitude(127.28726137429476);

                // A, B의 거리
                float[] distance = new float[5];
                distance[1] = hongic.distanceTo(locationA);
                Log.d(TAG, "DistanceBetweenPointAandB: " + distance[1] * 1 / 1000 + "km");

            }
        });
        ////////////////////

        LatLng hongic = new LatLng(36.620722650921884, 127.28726137429476);
        mMap.addMarker(new MarkerOptions().position(hongic).title("Marker in 홍익"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(hongic));
    }
}