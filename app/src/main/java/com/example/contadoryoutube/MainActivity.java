package com.example.contadoryoutube;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.contadoryoutube.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    public int contador;
    TextView textoResultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textoResultado=(TextView)findViewById(R.id.contadorTexto);
        contador=0;
    }

    public void Incrementar(View vista){
        contador++;
        textoResultado.setText(" "+contador);
    }
    public void Decrementar(View vista){
        contador--;
        if (contador<0){
            CheckBox negativos=(CheckBox)findViewById(R.id.negativos);
            if (!negativos.isChecked()){
                contador=0;
            }
        }
        textoResultado.setText(" "+contador);
    }
    public void ResetearContador(View vista){
        EditText texto= (EditText) findViewById(R.id.n_reseteo);
        try {
            contador = Integer.parseInt(texto.getText().toString());
        }catch(Exception e){
            contador=0;
        }
        texto.setText("");
        textoResultado.setText(""+contador);

    }




}