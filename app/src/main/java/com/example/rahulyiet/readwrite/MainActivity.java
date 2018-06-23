package com.example.rahulyiet.readwrite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText name,password,email,city;
    Spinner spinner;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        name =findViewById(R.id.name);
        password=findViewById(R.id.password);
        email=findViewById(R.id.email);
        city=findViewById(R.id.city);
        spinner=findViewById(R.id.spinner);
        button=findViewById(R.id.button);


        final String[] State ={"Bihar","Jharkhand","Orissa"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,State);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

           button.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent =new Intent(MainActivity.this,Main2Activity.class);

                   String userName= name.getText().toString();
                   String userPassword = password.getText().toString();
                   String userEmail = email.getText().toString();
                   String userCity = city.getText().toString();
                   String spinnerData = spinner.getSelectedItem().toString();

                   if (TextUtils.isEmpty(name.getText())) {
                       name.setError("required");
                       return;
                   }
                   if (TextUtils.isEmpty(password.getText())) {
                       password.setError("required");
                       return;

                   }
                   if (TextUtils.isEmpty(email.getText())) {
                       email.setError("required");
                       return;
                   }
                   if (TextUtils.isEmpty(city.getText())) {
                       city.setError("required");
                       return;
                   }

                     //write data


                   String totalData = "Name :" + userName + "\n" + "Password :" + userPassword + "\n" + "Email :" + userEmail + "\n" + "State :" + spinnerData + "\n" + "City :" + userCity ;
                   FileOutputStream fos;

                   try {
                       fos = openFileOutput("userData", MODE_PRIVATE);
                       fos.write(totalData.getBytes());
                       fos.close();


                   } catch (FileNotFoundException e) {
                       e.printStackTrace();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }


                   startActivity(intent);
               }
           });

    }
}
