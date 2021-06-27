package com.example.dataapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vdx.designertoast.DesignerToast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView showCitiesView;
    EditText nameCity,idPostalCode;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    public void init(){
        nameCity = findViewById(R.id.idCity);
        idPostalCode = findViewById(R.id.idPostalCode);
        btnSave = findViewById(R.id.btnSave);
        showCitiesView = findViewById(R.id.showCities);

        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String txtNameCity = nameCity.getText().toString();
        int txtCodePostal = Integer.parseInt(idPostalCode.getText().toString());

        if(v.getId() == R.id.btnSave){

            if (isEmpty(nameCity)) {
                //DesignerToast.Error(this, "Error", "Nombre es requerido!",Gravity.CENTER, Toast.LENGTH_SHORT,DesignerToast.STYLE_DARK);
                nameCity.setError("Name city is required!");
            }

            if (isEmpty(idPostalCode)) {
                idPostalCode.setError("postal code is required!");
            }

            MyDbHelper db = new MyDbHelper(this);
            Area area = new Area();
            area.setId(txtCodePostal);
            area.setName(txtNameCity);

            // id, nombre, población, latitud, longitud
            db.insertArea(area, db.getWritableDatabase());

            ArrayList<Area> areas = db.selectArea(db.getWritableDatabase());

            for (Area areaSeleted: areas){
                System.out.println("El area "+ areaSeleted.getId() + " es igual a "+ areaSeleted.getName());
                String content = "";
                content += "Codigo postal: "+areaSeleted.getId()+ "\n";
                content += "Ciudad: "+areaSeleted.getName()+ "\n\n";
                showCitiesView.append(content);
            }

            DesignerToast.Success(this,"Register Success", Gravity.CENTER, Toast.LENGTH_SHORT);
            //Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    public void savedOtherWay(){
                /*

        area.setName("Medellin");
        area.setId(3);
        area.setName("Manizales");

        Set<String> set = new HashSet<>();
        set.add("Inglés");
        set.add("Programacion");
        set.add("Arquitectura");

        SharedPreferences sharedPref = getSharedPreferences("Materia", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("nombre", "Jhon arcila castaño");
        editor.putString("email", "Jhon.380172793@ucaldas.edu.co");
        editor.putStringSet("datos", set);
        editor.commit();

        sharedPref.getString("email","");
        sharedPref.getString("nombre","");
        ArrayList datos = (ArrayList) sharedPref.getStringSet("datos",null);*/
    }
}