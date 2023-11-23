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

        String apiUrl = "http://localhost:3000/usuarios";

        try {
            // Realiza a requisição GET
            String jsonResponse = HttpRequest.sendGetRequest(apiUrl);

            if (jsonResponse != null) {
                // Parse do JSON
                JSONObject jsonObject = new JSONObject(jsonResponse);
                JSONArray usersArray = jsonObject.getJSONArray("users");

                // Agora você pode iterar sobre os objetos do array
                for (int i = 0; i < usersArray.length(); i++) {
                    JSONObject userObject = usersArray.getJSONObject(i);

                    // Exemplo: Obtém o nome do usuário
                    String nome = userObject.getString("nome");

                    // Aqui você pode fazer o que quiser com os dados, como verificar login
                    // Comparar com os dados inseridos pelo usuário
                    if (usuario.equals(userObject.getString("login")) && senha.equals(userObject.getString("senha"))) {
                        // Login bem-sucedido
                        Toast.makeText(this, "Login bem-sucedido para: " + nome, Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                // Se chegou aqui, o login falhou
                Toast.makeText(this, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Erro ao obter dados da API", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            // Tratar exceções, se necessário
            e.printStackTrace();
            Toast.makeText(this, "Erro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}
//Toast.makeText(this, "Login falhou", Toast.LENGTH_SHORT).show();