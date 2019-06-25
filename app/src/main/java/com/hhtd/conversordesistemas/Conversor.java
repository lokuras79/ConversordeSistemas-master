package com.hhtd.conversordesistemas;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Conversor extends AppCompatActivity {
    public String TipoConversion="",Conversion;
    EditText Num;
    TextView Base1_Total, Base2_Total, Base3_Total;
    TextView Base_Inicial, Base1, Base2, Base3;
    public int Numero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversor);
        //Colocar el icono al costado del nombre
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        /*Definir textbox*/
        Num=(EditText)findViewById(R.id.txt_Numero);
        /*Hacer que las letras introducidas sean mayusculas*/
        InputFilter Mayuscula=new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                try {
                    Character Letra=source.charAt(0);
                    if(Character.isLetter(Letra) || Character.isDigit(Letra)) {
                        return ""+Character.toUpperCase(Letra);
                    }
                    else{
                        return "";
                    }
                }
                catch (Exception e){
                    return null;
                }
            }
        };
        /*Activar filtro*/
        Num.setFilters(new InputFilter[]{Mayuscula});
        /*Definir labels*/
        Base_Inicial=(TextView)findViewById(R.id.lbl_Base_Inicial);
        Base1=(TextView)findViewById(R.id.lbl_Base1);
        Base2=(TextView)findViewById(R.id.lbl_Base2);
        Base3=(TextView)findViewById(R.id.lbl_Base3);
        /*Asignar los valores recibidos de la otra activity*/
        TipoConversion = getIntent().getStringExtra("Tipo_Conversion");
        Conversion = getIntent().getStringExtra("Conver");
        /*Cambiar el titulo segun los datos recibidos de la otra activity*/
        this.setTitle(TipoConversion);
        /*Segun los datos recibidos de la otra activity cambiar el texto de los labels*/
        switch (Conversion){
            case "Binario":
                /*Definir el input type*/
                Num.setInputType(InputType.TYPE_CLASS_NUMBER);
                /*Validar textbox*/
                Num.setKeyListener(DigitsKeyListener.getInstance("01"));
                Base_Inicial.setText("Binario:");
                Base1.setText("Decimal:");
                Base2.setText("Hexadecimal:");
                Base3.setText("Octal:");
                break;
            case "Decimal":
                /*Definir el input type*/
                Num.setInputType(InputType.TYPE_CLASS_NUMBER);
                /*Validar textbox*/
                Num.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
                /*Cambiar texto de los labels*/
                Base_Inicial.setText("Decimal:");
                Base1.setText("Binario:");
                Base2.setText("Hexadecimal:");
                Base3.setText("Octal:");
                break;
            case "Hexadecimal":
                /*Definir el input type*/
                Num.setInputType(InputType.TYPE_CLASS_TEXT);
                /*Validar textbox*/
                InputFilter Hexa=new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                        for(int i=start;i<end;i++){
                            String Validar=String.valueOf(source.charAt(i));
                            Pattern Pat=Pattern.compile("[ABCDEFabcdef1234567890]");
                            Matcher Match=Pat.matcher(Validar);
                            boolean Valido=Match.matches();
                            if(!Valido){
                                return "";
                            }
                        }
                        return null;
                    }
                };
                /*Actival filtro*/
                Num.setFilters(new InputFilter[]{Hexa});
                /*Cambiar texto de los labels*/
                Base_Inicial.setText("Hexadecimal:");
                Base1.setText("Binario:");
                Base2.setText("Decimal:");
                Base3.setText("Octal:");
                break;
            case "Octal":
                /*Definir el input type*/
                Num.setInputType(InputType.TYPE_CLASS_NUMBER);
                /*Validar textbox*/
                Num.setKeyListener(DigitsKeyListener.getInstance("01234567"));
                /*Cambiar texto de los labels*/
                Base_Inicial.setText("Octal:");
                Base1.setText("Binario:");
                Base2.setText("Decimal:");
                Base3.setText("Hexadecimal:");
                break;
        }
    }
    public void Calcular(View v){
        try{
            Base1_Total=(TextView)findViewById(R.id.lbl_Base1_Total);
            Base2_Total=(TextView)findViewById(R.id.lbl_Base2_Total);
            Base3_Total=(TextView)findViewById(R.id.lbl_Base3_Total);
            if(Num.getText().toString().equals("")){
               Num.setText("0");
            }
            else{
                /*Definir variables*/
                Numero=Integer.parseInt(Num.getText().toString());
                String Binario, Decimal, Hexadecimal, Octal;
                /*Segun los datos recibidos de la otra activity cambiar el tipo de operacion para la conversion*/
                switch (Conversion){
                    case "Binario":
                        Decimal=String.valueOf(Binario_Decimal(Num.getText().toString().toUpperCase()));
                        Base1_Total.setText(Decimal);
                        Hexadecimal=String.valueOf(Binario_Hexadecimal(Num.getText().toString().toUpperCase()));
                        Base2_Total.setText(Hexadecimal);
                        Octal=String.valueOf(Binario_Octal(Num.getText().toString().toUpperCase()));
                        Base3_Total.setText(Octal);
                        break;
                    case "Decimal":
                        Binario=String.valueOf(Decimal_Binario(Num.getText().toString().toUpperCase()));
                        Base1_Total.setText(Binario);
                        Hexadecimal=String.valueOf(Decimal_Hexadecimal(Num.getText().toString().toUpperCase()));
                        Base2_Total.setText(Hexadecimal);
                        Octal=String.valueOf(Decimal_Octal(Num.getText().toString().toUpperCase()));
                        Base3_Total.setText(Octal);
                        break;
                    case "Octal":
                        Binario=String.valueOf(Octal_Binario(Num.getText().toString().toUpperCase()));
                        Base1_Total.setText(Binario);
                        Decimal=String.valueOf(Octal_Decimal(Num.getText().toString().toUpperCase()));
                        Base2_Total.setText(Decimal);
                        Hexadecimal=String.valueOf(Octal_Hexadeciaml(Num.getText().toString().toUpperCase()));
                        Base3_Total.setText(Hexadecimal);
                        break;
                    case "Hexadecimal":
                        Binario=String.valueOf(Hexadecimal_Binario(Num.getText().toString().toUpperCase()));
                        Base1_Total.setText(Binario);
                        Decimal=String.valueOf(Hexadecimal_Decimal(Num.getText().toString().toUpperCase()));
                        Base2_Total.setText(Decimal);
                        Octal=String.valueOf(Hexadecimal_Octal(Num.getText().toString().toUpperCase()));
                        Base3_Total.setText(Octal);
                        break;
                }
            }
        }
        catch (Exception e){
            /*En caso de error mostrar un mensaje*/
            Toast.makeText(getApplicationContext(),"Error algo paso: \n"+e,Toast.LENGTH_SHORT).show();
        }
    }
    /*Binario al resto de sistemas*/
    public int Binario_Decimal(String Valor)
    {
        /*Conversion binario a decimal*/
        int Decimal=Integer.parseInt(Valor,2);
        return Decimal;
    }
    public String Binario_Octal(String Valor)
    {
        /*Conversion binario a octal*/
        int Decimal=Integer.parseInt(Valor,2);
        Valor=Integer.toOctalString(Decimal);
        return Valor;
    }
    public String Binario_Hexadecimal(String Valor)
    {
        /*Conversion binario a hexadecimal*/
        int Decimal=Integer.parseInt(Valor,2);
        Valor=Integer.toHexString(Decimal);
        return Valor;
    }
    /*Decimal al resto de sistemas*/
    public String Decimal_Binario(String Valor)
    {
        /*Conversion decimal a binario*/
        int Decimal=Integer.parseInt(Valor);
        Valor=Integer.toBinaryString(Decimal);
        return Valor;
    }

    public String Decimal_Hexadecimal(String Valor)
    {
        /*Conversion decimal a hexadecimal*/
        int Decimal=Integer.parseInt(Valor);
        Valor=Integer.toHexString(Decimal);
        return Valor;
    }

    public String Decimal_Octal(String Valor)
    {
        /*Conversion decimal a octal*/
        int Decimal=Integer.parseInt(Valor);
        Valor=Integer.toOctalString(Decimal);
        return Valor;
    }
    /*Hexadecimal al resto de sistemas*/
    public int Hexadecimal_Decimal(String Valor)
    {
        int Decimal=Integer.parseInt(Valor,16);
        return Decimal;
    }

    public String Hexadecimal_Binario(String Valor)
    {
        int Decimal=Integer.parseInt(Valor,16);
        Valor=Integer.toBinaryString(Decimal);
        return Valor;
    }

    public String Hexadecimal_Octal(String Valor)
    {
        int Decimal=Integer.parseInt(Valor,16);
        Valor=Integer.toOctalString(Decimal);
        return Valor;
    }
    /*Octal al resto de sistemas*/
    public int Octal_Decimal(String Valor)
    {
        /*Conversion octal a decimal*/
        int Decimal=Integer.parseInt(Valor,8);
        return Decimal;
    }

    public String Octal_Binario(String Valor)
    {
        /*Conversion octal a binario*/
        int Decimal=Integer.parseInt(Valor,8);
        Valor=Integer.toBinaryString(Decimal);
        return Valor;
    }

    public String Octal_Hexadeciaml(String Valor)
    {
        /*Conversion octal a hexadeciaml*/
        int Decimal=Integer.parseInt(Valor,8);
        Valor=Integer.toHexString(Decimal);
        return Valor;
    }
    public void Atras(View v){
        CambiarVentana();
    }
    public void CambiarVentana(){
        /*Abir la activity anterior*/
        Intent Ventana=new Intent(getApplicationContext(),Main.class);
        startActivity(Ventana);
    }
}
