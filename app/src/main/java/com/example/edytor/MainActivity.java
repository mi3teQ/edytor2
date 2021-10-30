package com.example.edytor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.graphics.Matrix;


public class MainActivity extends AppCompatActivity {

    CheckBox pokaz,ukryj;
    EditText rotacja, skala;
    ImageView obraz;
    ImageButton prawo, lewo;
    int l=0;
    int images[]={R.drawable.kot,R.drawable.kot1,R.drawable.kot2,R.drawable.kot3};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    pokaz=(CheckBox) findViewById(R.id.pokaz_obraz);
    ukryj=(CheckBox) findViewById(R.id.zchowaj_obraz);
    rotacja=(EditText) findViewById(R.id.rotacja);
    skala=(EditText) findViewById(R.id.skala);
    obraz=(ImageView) findViewById(R.id.obraz);
    prawo=(ImageButton) findViewById(R.id.prawo);
    lewo=(ImageButton) findViewById(R.id.lewo);


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

    rotacja.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String value= rotacja.getText().toString();
            int finalValue=Integer.parseInt(value);
            obraz.setRotation(finalValue);
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
            String value= skala.getText().toString();
            int finalValue=Integer.parseInt(value);
            obraz.getLayoutParams().height = finalValue;
            obraz.getLayoutParams().width = finalValue;
            obraz.requestLayout();

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
                if(l==4)
                    l=0;
            }
        });
        lewo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                l--;
                if(l==-1)
                    l=3;
                obraz.setImageResource(images[l]);
            }
        });
    }
}