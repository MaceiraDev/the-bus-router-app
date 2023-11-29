package com.example.the_bus_router_app.db;

import android.os.AsyncTask;

import com.example.the_bus_router_app.models.Rota;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class BuscarDadosRota extends AsyncTask<String, Void, ArrayList<Rota>> {

    @Override
    protected ArrayList<Rota> doInBackground(String... strings) {
        ArrayList<Rota> listaRotas = new ArrayList<>();

        try {
            String link = strings[0];
            URL url = new URL(link);

            URLConnection connection = url.openConnection();
            InputStream stream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(stream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String dados = "";
            String linha;

            while ((linha = reader.readLine()) !=null){
                dados += linha;
            }

            //JSONObject json = new JSONObject(dados);
            JSONArray lista = new JSONArray(dados);
                                // json.getString("rota")

            for (int i = 0; i < lista.length(); i++){
                JSONObject item = (JSONObject)lista.get(i);

                Rota rota = new Rota();
                rota.transportadora = item.getLong("transportadora");
                rota.descricao = item.getString("descricao");
                rota.id = item.getLong("id");
                rota.localPartida = item.getString("localPartida");
                rota.destino = item.getString("destino");
                rota.saida = item.getString("saida");
                rota.chegada = item.getString("chegada");

                listaRotas.add(rota);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        return listaRotas;
    }
}
