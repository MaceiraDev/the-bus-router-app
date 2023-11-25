package com.example.the_bus_router_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class DetalhesActivity extends AppCompatActivity {

    TextView txtTransportadora;
    TextView txtDescricao;
    TextView txtLocalPartida;
    TextView txtLocalDestino;
    TextView txtSaida;
    TextView txtChegada;
    Button btnVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        btnVoltar = findViewById(R.id.btnVoltar);

        txtTransportadora = findViewById(R.id.txtTransportadora);
        txtDescricao = findViewById(R.id.txtDescricao);
        txtLocalPartida = findViewById(R.id.txtLocalPartida);
        txtLocalDestino = findViewById(R.id.txtLocalDestino);
        txtSaida = findViewById(R.id.txtSaida);
        txtChegada = findViewById(R.id.txtChegada);

        Bundle parametros = getIntent().getExtras();
        if (parametros != null) {
            String transportadora = parametros.getString("transportadora");
            String descricao = parametros.getString("descricao");
            String localPartida = parametros.getString("localPartida");
            String LocalDestino = parametros.getString("destino");
            String saida = parametros.getString("saida");
            String chegada = parametros.getString("chegada");

            txtTransportadora.setText(transportadora);
            txtDescricao.setText(descricao);
            txtLocalPartida.setText(localPartida);
            txtLocalDestino.setText(LocalDestino);
            txtSaida.setText(saida);
            txtChegada.setText(chegada);

        }

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(DetalhesActivity.this, InicialActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });


    }
}