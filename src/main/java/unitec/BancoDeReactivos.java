package unitec;

import java.util.ArrayList;

/**
 * Created by campitos on 21/08/15.
 */
public class BancoDeReactivos {

    public static Reactivo obtenerReactivo()throws Exception{

        Reactivo reactivo=new Reactivo();
        reactivo.setPregunta("Se desea desarrollar un proyecto para un sistema en la plataforma JAVA empresarial con spring boot," +
                " el cual consta de varios módulos, para ello se asignan 3 desarrolladores: un modelador, " +
                " un programador y un diseñador, la siguiente es una herramienta adecuada para su elaboración:");
        ArrayList<Opcion> opcions=new ArrayList<>();
        Opcion opcion1=new Opcion();
        opcion1.setTitulo("Github");
        opcion1.setAcierto(true);
        Opcion opcion2=new Opcion();
              opcion2.setTitulo("Maven");
              opcion2.setAcierto(false);
        Opcion opcion3=new Opcion();
        opcion3.setAcierto(false);
        opcion3.setTitulo("Gradle");
        Opcion opcion4=new Opcion();
        opcion4.setTitulo("Ant");
        opcion4.setAcierto(false);
        opcions.add(opcion1);
        opcions.add(opcion2);
        opcions.add(opcion3);
        opcions.add(opcion4);
        reactivo.setOpciones(opcions);
        reactivo.setRetroalimentacion("La opción correcta es ésta, ya que Github es un sistema de control de " +
                "versiones y puede ser utilizado por equipos  y cada uno centrarse en una área específica del sistema" +
                " sin afectar o alterar el trabajo de los otros equipos");
       return  reactivo;
    }
}
