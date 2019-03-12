package edu.cecar.controladores;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExtraerDatoCVLACUnitTest {
    private List<Investigador> investigadores;
    private String urls[] = {"http://scienti.colciencias.gov.co:8081/cvlac/visualizador/generarCurriculoCv.do?cod_rh=0001376707",
    "http://scienti.colciencias.gov.co:8081/cvlac/visualizador/generarCurriculoCv.do?cod_rh=0000787132"};
    @Before
    public  void setUpClass() {
        investigadores = new ArrayList<>();
        //investigadores.add(new Investigador())
    }
    @Rule
    public GestionTestExtraerDatosCVLAC gestionTestExtraerDatosCVLAC = new GestionTestExtraerDatosCVLAC();



    @Test
    public void testDatosCVLAC() {
      /*
         Investigador investigador1 = ExtraerDatoCVLAC.getDatos("http://scienti.colciencias.gov.co:8081/cvlac/visualizador/generarCurriculoCv.do?cod_rh=0001376707");
        Investigador investigador2 = ExtraerDatoCVLAC.getDatos("http://scienti.colciencias.gov.co:8081/cvlac/visualizador/generarCurriculoCv.do?cod_rh=0000787132");

        //Se definen los datos esperados
        String nombreInvestigador1 = "Guillermo Carlos Hernández Hernández";
        String nombreInvestigador2 = "Namuel Francisco Solórzano Peralta";

        // Se comprueba o testea el valor esperado con el obtenido
        assertEquals(nombreInvestigador1, investigador1.getNombres());
        assertEquals(nombreInvestigador2, investigador2.getNombres());

       */

     
    }

    @Test
    public void testDatosCvlac() {
        // Se obtienen los datos del investigador
        Investigador investigador = ExtraerDatoCVLAC.getDatos("http://scienti.colciencias.gov.co:8081/cvlac/visualizador/generarCurriculoCv.do?cod_rh=0000402478");
        //Se definen los datos esperados
        String nombreInvestigador = "Luty Del Carmen Gomezcaceres Peréz";
        // Se comprueba o testea el valor esperado con el obtenido
        assertEquals(nombreInvestigador, investigador.getNombres());
    }
}
