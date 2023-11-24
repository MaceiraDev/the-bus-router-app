package br.com.unialfaumuarama.the_bus_router_app.db;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import br.com.unialfaumuarama.the_bus_router_app.models.Usuario;

public class BuscarDados extends AsyncTask<String, Void, ArrayList<Usuario>> {

    protected ArrayList<Usuario> doInBackground(String... strings) {
        ArrayList<Usuario> listaDados = new ArrayList<>();

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

            JSONObject json = new JSONObject(dados);

            JSONArray lista = new JSONArray(json.getString("results"));

            for (int i = 0; i < lista.length(); i++){
                JSONObject item = (JSONObject)lista.get(i);

                Usuario usuario = new Usuario();
                usuario.login = item.getString("login");
                usuario.senha = item.getString("senha");

                listaDados.add(usuario);
            }
        }
        catch (Exception ex){

        }
        return listaDados;
    }

}