package com.example.contadoryoutube;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.contadoryoutube.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    public int contador;
    TextView textoResultado;
    CheckBox negativos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textoResultado=(TextView)findViewById(R.id.contadorTexto);
        contador=0;
        EventoTeclado teclado = new EventoTeclado();
        EditText n_reseteo = (EditText)findViewById(R.id.n_reseteo);
        n_reseteo.setOnEditorActionListener(teclado);
        negativos = (CheckBox)findViewById(R.id.negativos);
        textoResultado.setText(""+contador);
    }

  /*  public void onSaveInstanceState(Bundle estado) {
        estado.putInt("cuenta",contador);
        super.onSaveInstanceState(estado);
    }

    public void onRestoreInstanceState(Bundle estado){
        super.onRestoreInstanceState(estado);
        contador=estado.getInt("cuenta");

        textoResultado.setText(""+contador);
    }
*/
    public void onPause(){

        super.onPause();
        SharedPreferences datos =PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor miEditor = datos.edit();

        miEditor.putInt("cuenta",contador);
        miEditor.apply();
    }

    public void onResume(){
        super.onResume();
        SharedPreferences datos =PreferenceManager.getDefaultSharedPreferences(this);

        contador = datos.getInt("cuenta",0);

        textoResultado.setText(""+contador);
    }

    class EventoTeclado implements TextView.OnEditorActionListener{

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId== EditorInfo.IME_ACTION_DONE){
                ResetearContador(null);
            }
            return false;
        }
    }
    public void Incrementar(View vista){
        if (contador>=0 && negativos.isChecked()){
            contador=0;
        }else{
            contador++;
        }
        textoResultado.setText(" "+contador);
    }
    public void Decrementar(View vista){
        contador--;
        if (contador>0 && negativos.isChecked()){
            contador=0;
        }
        textoResultado.setText(" "+contador);
    }


    public void ResetearContador(View vista){
        EditText numero_reset= (EditText) findViewById(R.id.n_reseteo);
        try {
            contador = Integer.parseInt(numero_reset.getText().toString());
        }catch(Exception e){
            contador=0;
        }
        numero_reset.setText("");
        textoResultado.setText(""+contador);

        InputMethodManager miTeclado=(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        miTeclado.hideSoftInputFromWindow(numero_reset.getWindowToken(),0);

    }




}