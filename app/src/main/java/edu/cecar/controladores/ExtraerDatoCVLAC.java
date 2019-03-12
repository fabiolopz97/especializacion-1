package edu.cecar.controladores;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExtraerDatoCVLAC {
    private ExtraerDatoCVLAC() {

    }

    public static Investigador  getDatos(String url) {

        Investigador investigador = null;
         List<LineaInvestigacion> lineas = new ArrayList<>();

        try {

            //Se obtiene el documento HTML
            Document documentoHTML = Jsoup.connect(url).get();

            Element tablas = documentoHTML.select("table").get(1); //Se obtiene la segunda tabla
            Element tablasLineaOcho = documentoHTML.select("table").get(6); //Se obtiene la octava tabla
            Elements filasTabla = tablas.select("tr"); // Se obtienen las filas de la tabla
            Elements filasTablaOcho = tablasLineaOcho.select("tr"); // Se obtienen las filas de la tablaLineaOcho

            int  filaNombre = 0;
            int filaNacionalidad = 2;
            int filaSexo = 3;

            if(filasTabla.size() > 4) {
                filaNombre = 2;
                filaNacionalidad = 4;
                filaSexo = 5;
            }

            //Verificacion
            //filasTablaOcho.get(0).select("td").get(1).text();
            Elements listas = tablasLineaOcho.select("li");
            if (listas.isEmpty()){
                //mostrara error
            }else{
                for (Element lista : listas) {
                    Log.i("dato lista",lista.text());
                    List<TextNode> nodos = lista.textNodes();
                     boolean isActivo = nodos.get(1).text().equalsIgnoreCase("Si");
                    lineas.add(new LineaInvestigacion(nodos.get(0).text(),isActivo));
                }

            }

            //Se obtienen las columnas para cada atributo del invstigador
            String nombre = filasTabla.get(filaNombre).select("td").get(1).text();
            String nacionalidad = filasTabla.get(filaNacionalidad).select("td").get(1).text();
            String sexo = filasTabla.get(filaSexo).select("td").get(1).text();

            //Se crea el objeto investigador
            investigador = new Investigador(nombre, nacionalidad,sexo,true);
            investigador.setLineas(lineas);
        } catch (IOException e) {

            e.printStackTrace();

        }

        return investigador;

    }
}
