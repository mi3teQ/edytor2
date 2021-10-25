package com.example.edytor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    CheckBox pokaz,ukryj;
    EditText rotacja, skala;
    ImageView obraz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    pokaz=(CheckBox) findViewById(R.id.pokaz_obraz);
    ukryj=(CheckBox) findViewById(R.id.zchowaj_obraz);
    rotacja=(EditText) findViewById(R.id.rotacja);
    skala=(EditText) findViewById(R.id.skala);
    obraz=(ImageView) findViewById(R.id.obraz);

    pokaz.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            if(isChecked==true)
            {
                obraz.setVisibility(View.VISIBLE);
            }
        }
    });
    ukryj.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            if(isChecked==true){
                obraz.setVisibility(View.INVISIBLE);
            }
            else{
                obraz.setVisibility(View.VISIBLE);
            }
        }
    });

    }
}