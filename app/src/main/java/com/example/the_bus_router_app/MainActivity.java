package com.example.the_bus_router_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.the_bus_router_app.db.BuscarDados;
import com.example.the_bus_router_app.models.Usuario;


public class MainActivity extends AppCompatActivity {

    private ArrayList<Usuario>listaDados;
    EditText edtUsuario, edtSenha;
    Button btnLogin;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtSenha = findViewById(R.id.edtSenha);
        edtUsuario = findViewById(R.id.edtUsuario);
        btnLogin = findViewById(R.id.btnLogin);

        estaLogado();



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    verificarLogin();

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void estaLogado() {

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String loginR = sharedPref.getString("login", "");
        String senhaR = sharedPref.getString("login", "");

        if (!loginR.equals("") && !senhaR.equals("")) {
            Intent intent = new Intent(MainActivity.this, InicialActivity.class);
            startActivity(intent);
        }
    }

    private void verificarLogin() throws Exception {
        String loginD = edtUsuario.getText().toString();
        String senhaD = edtSenha.getText().toString();


        try {
            listaDados = new BuscarDados().execute(Config.link + "usuarios/").get();

            boolean loginSucedido = false;

            for (int i = 0; i < listaDados.size(); i++) {

                Usuario usuario = listaDados.get(i);

                if (loginD.equals(usuario.login) && senhaD.equals(usuario.senha)) {
                    loginSucedido = true;
                    break;
                }
            }

            if (loginSucedido) {
                Toast.makeText(this, "Login funcionou", Toast.LENGTH_SHORT).show();

                SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("login", loginD);
                editor.putString("senha", senhaD);
                editor.commit();

                Intent intent = new Intent(MainActivity.this, InicialActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Login falhou", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}