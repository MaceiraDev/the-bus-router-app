package br.com.unialfaumuarama.the_bus_router_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import br.com.unialfaumuarama.the_bus_router_app.models.Usuario;


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

    private void verificarLogin() throws Exception {
        String usuario = edtUsuario.getText().toString();
        String senha = edtSenha.getText().toString();



    }
}