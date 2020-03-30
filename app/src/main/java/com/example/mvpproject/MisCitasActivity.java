package com.example.mvpproject;

import androidx.appcompat.app.AppCompatActivity;
import cz.msebera.android.httpclient.Header;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import org.json.JSONArray;
import com.loopj.android.http.AsyncHttpResponseHandler;
import org.json.JSONException;
import android.view.View;
import android.widget.Toast;

import org.json.JSONObject;

import com.example.mvpproject.Resources.ConnectionApi;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;

public class MisCitasActivity extends AppCompatActivity {

    ConnectionApi api = new ConnectionApi();
    private TableLayout tableLayout;
    private AsyncHttpClient client = new AsyncHttpClient();
    private String[] header={"id  ","     Nombre      ","   fecha   ","      Doctor   ",""};
    private ArrayList<String[]> rows = new ArrayList<>();
    private TableRow tableRow;
    private TextView txtCell;
    private int indexR;
    private int indexC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_citas);
        tableLayout = findViewById(R.id.tabala);
        getCitas();
    }

    private void getCitas() {
        client.get(getApplicationContext(), "http://192.168.43.135:3333" + "/cita/3", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray responseBody) {
                //logger.log(Level.INFO,"------------"+statusCode);
                try {
                    for(int i = 0; i< responseBody.length();i++){
                        JSONObject dato = responseBody.getJSONObject(i);
                        //logger.log(Level.SEVERE,"fghjk");
                        rows.add(new String[]{dato.getString("id"),dato.getString("nombre"),dato.getString("dia")+"-"+dato.getString("mes")+"-"+dato.getString("año"),"Dr. Susano"});
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                createHeader();
                createDataTable();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable error, JSONArray responseBody) {
                //logger.log(Level.INFO,"------------"+statusCode);
                switch (statusCode){
                    case 0:
                        Toast.makeText(getApplicationContext(),"Tiempo de espera terminado",Toast.LENGTH_LONG).show();
                    case 404:
                        Toast.makeText(getApplicationContext(),"Error de envio de datos o datos incorrectos",Toast.LENGTH_LONG).show();
                        break;
                    case 400:
                        Toast.makeText(getApplicationContext(),"Ha surgido un error interno",Toast.LENGTH_LONG).show();
                        break;
                    case 500:
                        Toast.makeText(getApplicationContext(),"Problemas de comunicacion",Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
    }

    private void newRow(){
        tableRow = new TableRow(getApplicationContext());
    }

    private void newCell(){
        txtCell = new TextView(getApplicationContext());
        txtCell.setGravity(Gravity.CENTER);
        txtCell.setTextSize(15);
    }

    private void createHeader(){
        indexC = 0;
        newRow();
        while (indexC < header.length){
            newCell();
            txtCell.setText(header[indexC++]);
            tableRow.addView(txtCell, newTableRowParams());
        }
        tableLayout.addView(tableRow);
    }

    private void createDataTable(){
        String info;
        for(indexR=1;indexR<rows.size();indexR++){
            newRow();
            for(indexC=0;indexC<header.length;indexC++){
                if(indexC+1 == header.length){
                    final int id = Integer.parseInt(rows.get(indexR-1)[0]);
                    newCell();
                    Button x = new Button(getApplicationContext());
                    x.setId(id);
                    x.setText("Cancelar");
                    x.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            client.delete(getApplicationContext(), "http://192.168.43.135:3333/cita/"+id , new AsyncHttpResponseHandler() {
                                @Override
                                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                    System.out.println(".......eliminado");
                                    finish();
                                    startActivity(getIntent());
                                }

                                @Override
                                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                                }
                            });
                        }
                    });
                    tableRow.addView(x,newTableRowParams());
                    newCell();
                    String[] columns= rows.get(indexR-1);
                    info=(indexC<columns.length)?columns[indexC]:"";
                    txtCell.setText(info);
                    tableRow.addView(txtCell, newTableRowParams());
                }else{
                    newCell();
                    String[] columns= rows.get(indexR-1);
                    info=(indexC<columns.length)?columns[indexC]:"";
                    txtCell.setText(info);
                    tableRow.addView(txtCell, newTableRowParams());
                }
            }

            tableLayout.addView(tableRow);
        }
    }

    private TableRow.LayoutParams newTableRowParams(){
        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.setMargins(1,1,1,1);
        params.weight=1;
        return params;
    }
}
