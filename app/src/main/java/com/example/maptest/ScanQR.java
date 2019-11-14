package com.example.maptest;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class ScanQR extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);

        new IntentIntegrator(this).initiateScan();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        String [] barcodedata = new String[3];
        barcodedata[0] = "";

        SharedPreferences setting;

        setting = getSharedPreferences("data",0);

        TextView pos;
        setContentView(R.layout.activity_scan_qr);
        pos = findViewById(R.id.mTextMessage);
        for(int i = 0; i < 3; i++) {
            if (result != null) {
                if (result.getContents() == null) {
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                    // todo
                } else {
                    Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                    // todo
                    barcodedata[i] = result.getContents();
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }

            SharedPreferences.Editor editor;
            editor = setting.edit();
            editor.putString ("pos"+i,barcodedata[i]);
            editor.commit();

        }

        pos.setText(setting.getString("pos"+0, "defValue"));
        pos.setText(setting.getString("pos"+1, "defValue"));
        pos.setText(setting.getString("pos"+2, "defValue"));
    }

}