package com.example.the_bus_router_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.the_bus_router_app.db.HttpRequest;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

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

        if (!usuario.equals("") && !senha.equals("")) {
            String url = "http://localhost:3000/users";
            String response = HttpRequest.sendGetRequest(url);

            JSONObject jsonResponse = new JSONObject(response);
            JSONArray users = jsonResponse.getJSONObject("usuarios").getJSONArray("users");

            String apiUsuario = userObject.getString("login")

        }else {
            Toast.makeText(this, "Login falhou", Toast.LENGTH_SHORT).show();
        }
    }
}