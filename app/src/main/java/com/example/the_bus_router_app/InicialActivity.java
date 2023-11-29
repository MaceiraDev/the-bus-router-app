package com.example.the_bus_router_app;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.the_bus_router_app.db.BuscarDadosRota;
import com.example.the_bus_router_app.db.BuscarDadosTransportadora;
import com.example.the_bus_router_app.models.Rota;
import com.example.the_bus_router_app.models.Transportadora;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InicialActivity extends ListActivity {

    private ArrayList<Rota> listaRotas;
    private ArrayList<Transportadora> listaTransportadoras;
    Button btnDeslogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        btnDeslogar = findViewById(R.id.btnDeslogar);


        btnDeslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    fecharLogin();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        try {
            listaRotas = new BuscarDadosRota().execute(Config.link + "rotas/ ").get();
            listaTransportadoras = new BuscarDadosTransportadora().execute(Config.link + "transportadoras/ ").get();

            ListAdapter adapter = new SimpleAdapter(
                    this,
                    dadosToMap(listaRotas, listaTransportadoras),
                    R.layout.listview_modelo,
                    new String[] { "localPartida", "destino" },
                    new int[] { R.id.txtPartida, R.id.txtDestino }
            );


            setListAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private List<? extends Map<String,?>> dadosToMap(ArrayList<Rota> listaRotas, ArrayList<Transportadora> listaTransportadoras) {
        List<HashMap<String,String>> lista = new ArrayList<>();

        for (int i = 0; i < listaRotas.size(); i++) {
            HashMap<String, String> item = new HashMap<>();
            item.put("id", String.valueOf(listaRotas.get(i).id));
            item.put("transportadora", String.valueOf(listaRotas.get(i).transportadora));
            item.put("descricao", listaRotas.get(i).descricao);
            item.put("localPartida", listaRotas.get(i).localPartida);
            item.put("destino", listaRotas.get(i).destino);
            item.put("saida", listaRotas.get(i).saida);
            item.put("chegada", listaRotas.get(i).chegada);

            lista.add(item);
        }

        return lista;
    }


    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Rota rota = listaRotas.get(position);

        //        Transportadora trans = listaTransportadoras.get(Math.toIntExact(rota.transportadora));

        Intent telaDetalhesIntent = new Intent(InicialActivity.this, DetalhesActivity.class);
        telaDetalhesIntent.putExtra("id", rota.id);
        telaDetalhesIntent.putExtra("transportadora", " Transportadora não encontrada");

        try {
            for (int i = 0; i < listaTransportadoras.size(); i++) {
                String idT = String.valueOf(listaRotas.get(position).transportadora);
                String nome = listaTransportadoras.get(i).nome;

                if (idT.equals(String.valueOf(listaRotas.get(i).id))) {
                    telaDetalhesIntent.putExtra("transportadora", nome);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        telaDetalhesIntent.putExtra("descricao", rota.descricao);
        telaDetalhesIntent.putExtra("localPartida", rota.localPartida);
        telaDetalhesIntent.putExtra("destino", rota.destino);
        telaDetalhesIntent.putExtra("saida", rota.saida);
        telaDetalhesIntent.putExtra("chegada", rota.chegada);

        startActivity(telaDetalhesIntent);
    }


    private void fecharLogin() {
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("login", "");
        editor.putString("senha", "");
        editor.commit();

        Intent intent = new Intent(InicialActivity.this, MainActivity.class);
        startActivity(intent);
    }
}