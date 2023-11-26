package com.example.the_bus_router_app.db;

import android.os.AsyncTask;

import com.example.the_bus_router_app.models.Rota;
import com.example.the_bus_router_app.models.Transportadora;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class BuscarDadosTransportadora extends AsyncTask<String, Void, ArrayList<Transportadora>> {
    @Override
    protected ArrayList<Transportadora> doInBackground(String... strings) {
        ArrayList<Transportadora> listaTransportadoras = new ArrayList<>();

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

                Transportadora transportadora = new Transportadora();
                transportadora.id = item.getLong("id");
                transportadora.nome = item.getString("nome");
                transportadora.endereco = item.getString("endereco");
                transportadora.telefone = item.getString("telefone");
                transportadora.email = item.getString("email");
                transportadora.sitio = item.getString("sitio");

                listaTransportadoras.add(transportadora);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        return listaTransportadoras;
    }
}
