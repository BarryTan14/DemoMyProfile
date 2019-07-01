package sg.edu.rp.c346.demomyprofile;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etName,etGpa;
    Button btnSave;
    RadioGroup rgGender;
    String male = "Male";
    String female = "Female";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName=findViewById(R.id.editTextName);
        etGpa=findViewById(R.id.editTextGpa);
        btnSave=findViewById(R.id.buttonSave);
        rgGender=findViewById(R.id.radioGroupGender);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strName = etName.getText().toString();
                SharedPreferences namePref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor nameEditor = namePref.edit();
                nameEditor.putString("name",strName);
                nameEditor.commit();

                float dbeGpa = Float.parseFloat(etGpa.getText().toString());
                SharedPreferences gpaPref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor gpaEditor = gpaPref.edit();
                gpaEditor.putFloat("gpa",dbeGpa);
                gpaEditor.commit();

                int intRgrp = rgGender.getCheckedRadioButtonId();
                SharedPreferences intPref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor intEditor = intPref.edit();
                intEditor.putInt("ID",intRgrp);
                intEditor.commit();

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();



        SharedPreferences namePref = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences gpaPref = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences intRgrp = PreferenceManager.getDefaultSharedPreferences(this);

        int Id = intRgrp.getInt("ID",R.id.radioButtonGenderMale);

        rgGender.check(Id);

        if(Id==R.id.radioButtonGenderMale){
            Toast toast = Toast.makeText(MainActivity.this,namePref.getString("name","No Name")+"\n"+gpaPref.getFloat("gpa",12345678)+"\n"+male,Toast.LENGTH_LONG);
            toast.show();
        }
        else if(Id==R.id.radioButtonGenderFemale){
            Toast toast = Toast.makeText(MainActivity.this,namePref.getString("name","No Name")+"\n"+gpaPref.getFloat("gpa",12345678)+"\n"+female,Toast.LENGTH_LONG);
            toast.show();
        }



    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}
