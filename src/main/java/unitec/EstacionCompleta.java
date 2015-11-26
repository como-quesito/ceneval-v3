/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitec;

import org.springframework.data.annotation.Id;

/**
 *
 * @author campitos
 */
public class EstacionCompleta implements Comparable<EstacionCompleta> {
   @Id
   private String id;  
   private Integer hora;
   private String temperatura;
   private String humedad;
   private String dewPoint;
   private String windChill;
   private String presion;
   private String vientoIntensidad;
   private String lluviaDiaria;
   private String rainRate;
   private String uv;
   private String radiacion;

    public EstacionCompleta(Integer hora, String temperatura, String humedad, String dewPoint, String windChill, String presion, String vientoIntensidad, String lluviaDiaria, String rainRate
              ,String uv, String radiacion) {
        this.hora = hora;
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.dewPoint = dewPoint;
        this.windChill = windChill;
        this.presion = presion;
        this.vientoIntensidad = vientoIntensidad;
        this.lluviaDiaria = lluviaDiaria;
        this.rainRate = rainRate;
        this.uv=uv;
        this.radiacion=radiacion;
    }

    public EstacionCompleta() {
    }

    public String getUv() {
        return uv;
    }

    public void setUv(String uv) {
        this.uv = uv;
    }

    public String getRadiacion() {
        return radiacion;
    }

    public void setRadiacion(String radiacion) {
        this.radiacion = radiacion;
    }

    public EstacionCompleta(String id) {
        this.id = id;
    }

    public Integer getHora() {
        return hora;
    }

    public void setHora(Integer hora) {
        this.hora = hora;
    }

    public String getHumedad() {
        return humedad;
    }

    public void setHumedad(String humedad) {
        this.humedad = humedad;
    }

    public String getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(String dewPoint) {
        this.dewPoint = dewPoint;
    }

    public String getWindChill() {
        return windChill;
    }

    public void setWindChill(String windChill) {
        this.windChill = windChill;
    }

    public String getPresion() {
        return presion;
    }

    public void setPresion(String presion) {
        this.presion = presion;
    }

    public String getVientoIntensidad() {
        return vientoIntensidad;
    }

    public void setVientoIntensidad(String vientoIntensidad) {
        this.vientoIntensidad = vientoIntensidad;
    }

    public String getLluviaDiaria() {
        return lluviaDiaria;
    }

    public void setLluviaDiaria(String lluviaDiaria) {
        this.lluviaDiaria = lluviaDiaria;
    }

    public String getRainRate() {
        return rainRate;
    }

    public void setRainRate(String rainRate) {
        this.rainRate = rainRate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int compareTo(EstacionCompleta e) {
 return hora.compareTo(e.getHora());
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }
    

}
