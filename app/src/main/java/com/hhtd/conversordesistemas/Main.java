package com.hhtd.conversordesistemas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends AppCompatActivity {
    /*Definir variables*/
    public String TipoConversion="",Conversion="";
    public Button Binario, Decimal, Octal, Hexadecimal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Colocar el icono al costado del nombre
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        /*Cambiar el texto del titulo*/
        this.setTitle("Conversor de bases");
    }
    public void Binarios(View v){
        /*Definir datos y cambiar de activity*/
        Binario=(Button)findViewById(R.id.btn_Binario);
        TipoConversion=Binario.getText().toString();
        Conversion="Binario";
        CambiarVentana();
    }
    public void Decimales(View v){
        /*Definir datos y cambiar de activity*/
        Decimal=(Button)findViewById(R.id.btn_Decimal);
        TipoConversion=Decimal.getText().toString();
        Conversion="Decimal";
        CambiarVentana();
    }
    public void Octales(View v){
        /*Definir datos y cambiar de activity*/
        Octal=(Button)findViewById(R.id.btn_Octal);
        TipoConversion=Octal.getText().toString();
        Conversion="Octal";
        CambiarVentana();
    }
    public void Hexadecimales(View v){
        /*Definir datos y cambiar de activity*/
        Hexadecimal=(Button)findViewById(R.id.btn_Hexadecimal);
        TipoConversion=Hexadecimal.getText().toString();
        Conversion="Hexadecimal";
        CambiarVentana();
    }
    public void CambiarVentana(){
        /*Cabmiar de activity y enviar datos a ella*/
        Intent Ventana=new Intent(getApplicationContext(),Conversor.class);
        Ventana.putExtra("Tipo_Conversion",TipoConversion);
        Ventana.putExtra("Conver",Conversion);
        startActivity(Ventana);
    }
}
