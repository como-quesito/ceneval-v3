package unitec;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;

/**
 * Created by campitos on 21/08/15.
 */
public class Reactivo {

    String tema;
    String subtema;
    String pregunta;
    String retroalimentacion;
    ArrayList<Opcion> opciones;

    public ArrayList<Opcion> getOpciones() {
        return opciones;
    }

   
    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getSubtema() {
        return subtema;
    }

    public void setSubtema(String subtema) {
        this.subtema = subtema;
    }



    public void setOpciones(ArrayList<Opcion> opciones) {
        this.opciones = opciones;
    }

    public String getRetroalimentacion() {
        return retroalimentacion;
    }

    public void setRetroalimentacion(String retroalimentacion) {
        this.retroalimentacion = retroalimentacion;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public Reactivo() {
    }
}
