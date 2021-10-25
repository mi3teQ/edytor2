package com.example.edytor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CheckBox;
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
    pokaz.findViewById(R.id.pokaz_obraz);
    ukryj.findViewById(R.id.zchowaj_obraz);
    rotacja.findViewById(R.id.rotacja);
    skala.findViewById(R.id.skala);
    obraz.findViewById(R.id.obraz);

    }
}