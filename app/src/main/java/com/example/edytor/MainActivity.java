package com.example.edytor;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    CheckBox ukryj;
    EditText rotacja, skala;
    ImageView obraz;
    ImageButton prawo, lewo, wyczysc, zdjecie;
    int l=0;
    int images[]={R.drawable.kot,R.drawable.kot1,R.drawable.kot2,R.drawable.kot3};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ukryj = (CheckBox) findViewById(R.id.zchowaj_obraz);
        rotacja = (EditText) findViewById(R.id.rotacja);
        skala = (EditText) findViewById(R.id.skala);
        obraz = (ImageView) findViewById(R.id.obraz);
        prawo = (ImageButton) findViewById(R.id.prawo);
        lewo = (ImageButton) findViewById(R.id.lewo);
        zdjecie = (ImageButton) findViewById(R.id.zdjecie);
        wyczysc = (ImageButton) findViewById(R.id.wyczysc);


        ukryj.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked == true) {
                    obraz.setVisibility(View.INVISIBLE);
                } else {
                    obraz.setVisibility(View.VISIBLE);
                }
            }
        });

        rotacja.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String value = rotacja.getText().toString();
                if(value != null && !value.isEmpty()) {

                    int finalValue = Integer.parseInt(value);
                    obraz.setRotation(finalValue);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        skala.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String value = skala.getText().toString();

                if(value != null && !value.isEmpty()) {
                    int finalValue = Integer.parseInt(value);
                    obraz.setScaleX(finalValue / 100F);
                    obraz.setScaleY(finalValue / 100F);
                    obraz.requestLayout();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        prawo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obraz.setImageResource(images[l]);
                l++;
                if (l == 4)
                    l = 0;
            }
        });
        lewo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                l--;
                if (l == -1)
                    l = 3;
                obraz.setImageResource(images[l]);
            }
        });

        wyczysc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obraz.setImageDrawable(null);
            }
        });

        zdjecie.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                } else {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });


    }

        @Override
        public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions,
        @NonNull int[] grantResults)
        {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (requestCode == MY_CAMERA_PERMISSION_CODE) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                } else {
                    Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
                }
            }
        }


        @Override
        protected void onActivityResult ( int requestCode, int resultCode, Intent data)
        {
            if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                obraz.setImageBitmap(photo);
            }
        }

}