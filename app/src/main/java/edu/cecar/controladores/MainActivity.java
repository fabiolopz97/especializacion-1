package edu.cecar.controladores;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import edu.cecar.adapters.MyAdapterLineaInvestigacion;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText teNombres;
    private TextInputEditText teNacionalidad;
    private TextInputEditText teSexo;
    private TextInputEditText teCategoria;
    private List<LineaInvestigacion> lineaInvestigacions;
    private RecyclerView mRecyclerView;
    private MyAdapterLineaInvestigacion mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    String url = "http://scienti.colciencias.gov.co:8081/cvlac/visualizador/generarCurriculoCv.do?cod_rh=0001007017";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Se referencias los elementos graficos
        teNombres = findViewById(R.id.teNombres);
        teNacionalidad = findViewById(R.id.teNacionalidad);
        teSexo = findViewById(R.id.teSexo);
        teCategoria = findViewById(R.id.teCategorizado);

        lineaInvestigacions = getAll();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewListLineaInvestigacion);
        mLayoutManager = new LinearLayoutManager(MainActivity.this);
        mAdapter = new MyAdapterLineaInvestigacion(lineaInvestigacions, R.layout.list_linea_investigacion, new MyAdapterLineaInvestigacion.OnItemClickListener() {
            @Override
            public void onItemClick(LineaInvestigacion lineaInvestigacion, int position) {

            }
        });
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);


        Button btObtenerDatosCVLac = findViewById(R.id.btObtenerDatos);
        btObtenerDatosCVLac.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                extraerDatosCVLAC();

            }
        });
    }

    public void extraerDatosCVLAC() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                Investigador investigador = ExtraerDatoCVLAC.getDatos(url);
                adicionarDatosCasillasTexto(investigador);

            }

        }).start();

    }

    public void adicionarDatosCasillasTexto(final Investigador investigador) {

        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                teNombres.setText(investigador.getNombres());
                teNacionalidad.setText(investigador.getNacionalidad());
                teSexo.setText(investigador.getSexo());
                teCategoria.setText(investigador.isCategorizado() ? "Si" : "No");

            }
        });

    }

    private List<LineaInvestigacion> getAll () {
        return new ArrayList<LineaInvestigacion>(){{
            add(new LineaInvestigacion("Nombre", true));
            add(new LineaInvestigacion("Nombre", true));
            add(new LineaInvestigacion("Nombre", true));
            add(new LineaInvestigacion("Nombre", true));
            add(new LineaInvestigacion("Nombre", true));
            add(new LineaInvestigacion("Nombre", true));
            add(new LineaInvestigacion("Nombre", true));
            add(new LineaInvestigacion("Nombre", true));
        }};
    }

}
