package unitec;


import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.inject.Inject;
import java.util.Calendar;
import java.util.TimeZone;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author campitos
 */
@Configuration
@EnableAsync
@EnableScheduling
public class TareaProgramadaEstacion {

    //@Inject ServicioEstacion estacion;
    @Inject ServicioEstacionCompleta estacionCompleta;

@Scheduled(fixedDelay=12000)
public void checarDatos(){
    System.out.println("Cargando datos");
    try{
    //checamos la informacion de la temperatura (temperatura que viene en la estacion solita
    int indice1=ServicioLeerEstacion.servicioLeerEStacion()[0].indexOf("data\">");
    int indice2=ServicioLeerEstacion.servicioLeerEStacion()[0].indexOf("C");
    String temperatura=ServicioLeerEstacion.servicioLeerEStacion()[0].substring(indice1+6, indice2-1);

   //  ===============>>>>>>>>>>>>>>>><  LA CLASE Estacion COMPLETA
    //checamos la temepratura que viene en la temperatura completa
     int indice1Temperatura=ServicioLeerEstacion.servicioLeerEStacion()[0].indexOf("data\">");
    int indice2Temperatura=ServicioLeerEstacion.servicioLeerEStacion()[0].indexOf("C");
    String temperatura2=ServicioLeerEstacion.servicioLeerEStacion()[0].substring(indice1Temperatura+6, indice2Temperatura-1);

    //Checamos windchill
    int indice1WindChill=ServicioLeerEstacionCompleta.servicioLeerWindChill()[0].indexOf("data\">");
    int indice2WindChill=ServicioLeerEstacionCompleta.servicioLeerWindChill()[0].indexOf("C");
    String windChill=ServicioLeerEstacionCompleta.servicioLeerWindChill()[0].substring(indice1WindChill+6, indice2WindChill);



    //checamos la informacion de la humedad
    int indice1Humedades=ServicioLeerEstacionCompleta.servicioLeerHumedades()[0].indexOf("data\">");
    int indice2Humedades=ServicioLeerEstacionCompleta.servicioLeerHumedades()[0].indexOf("C");
    String rocio=ServicioLeerEstacionCompleta.servicioLeerHumedades()[0].substring(indice1Humedades+6, indice2Humedades);

    //Checamos intensidad del viento
     int indice1IntensidadViento=ServicioLeerEstacionCompleta.servicioLeerIntensidadViento()[0].indexOf("data\">");
    int indice2IntensidadViento=ServicioLeerEstacionCompleta.servicioLeerIntensidadViento()[0].indexOf("</td>");
    String intensidadViento=ServicioLeerEstacionCompleta.servicioLeerIntensidadViento()[0].substring(indice1IntensidadViento+6, indice2IntensidadViento);
    //Checamos si es calm
    if(intensidadViento.equalsIgnoreCase("Calm"))intensidadViento="0";


    //Checamos rain rate

        int indice1RainRate=ServicioLeerEstacionCompleta.servicioLeerLluvia()[0].indexOf("data\">");
    int indice2RainRate=ServicioLeerEstacionCompleta.servicioLeerLluvia()[0].indexOf("mm/Hour</td>");
    String rainRate=ServicioLeerEstacionCompleta.servicioLeerLluvia()[0].substring(indice1RainRate+6, indice2RainRate);


    //checamos rain
           int indice1Rain=ServicioLeerEstacionCompleta.servicioLeerLluvia()[1].indexOf("data\">");
    int indice2Rain=ServicioLeerEstacionCompleta.servicioLeerLluvia()[1].indexOf("mm</td>");
    String rain=ServicioLeerEstacionCompleta.servicioLeerLluvia()[1].substring(indice1Rain+6, indice2Rain);


    //Checamos presion
         int indice1Presion=ServicioLeerEstacionCompleta.servicioLeerPresion()[0].indexOf("data\">");
    int indice2Presion=ServicioLeerEstacionCompleta.servicioLeerPresion()[0].indexOf("mb</td>");
    String presion=ServicioLeerEstacionCompleta.servicioLeerPresion()[0].substring(indice1Presion+6, indice2Presion);

    //Leer humedad
             int indice1Humedad=ServicioLeerEstacionCompleta.servicioLeerHumedad()[0].indexOf("data\">");
            int indice2Humedad=ServicioLeerEstacionCompleta.servicioLeerHumedad()[0].indexOf("</td>");
    String humedad=ServicioLeerEstacionCompleta.servicioLeerHumedad()[0].substring(indice1Humedad+6, indice2Humedad);
    System.out.println("Se ve");
    //Leer UV
    int indice1Uv=ServicioLeerEstacionCompleta.servicioLeerUv()[0].indexOf("data\">");
    int        indice2Uv=ServicioLeerEstacionCompleta.servicioLeerUv()[0].indexOf("</td>");
    String uv=ServicioLeerEstacionCompleta.servicioLeerUv()[0].substring(indice1Uv+6, indice2Uv-5);


    // System.out.println(""+uv);
   // Leer Radiacion
     int indice1Radiacion=ServicioLeerEstacionCompleta.servicioLeerRadiacion()[0].indexOf("data\">");
           int indice2Radiacion=ServicioLeerEstacionCompleta.servicioLeerRadiacion()[0].indexOf("<span class=\"threequarter\">");
    String radiacion=ServicioLeerEstacionCompleta.servicioLeerRadiacion()[0].substring(indice1Radiacion+6, indice2Radiacion-3);



  TimeZone timeZone1 = TimeZone.getTimeZone("America/Mexico_City");
       // out.println(    timeZone1.getDisplayName());

            Calendar cal=Calendar.getInstance(timeZone1);
   //   System.out.println("Temperatura actual es:"+temperatura);
  //    System.out.println("P. rocio actual:"+rocio);
   //   System.out.println("Intesidad viento:"+intensidadViento);
    //  System.out.println("Rain rate:"+rainRate);
    //  System.out.println("LLuvia diaria:"+rain);
    //  System.out.println("La presion en este momento es:"+presion);
    //  System.out.println("La humedad es:"+humedad);
    //  System.out.println("El wind chill es:"+windChill);
    int hora=cal.get(Calendar.HOUR_OF_DAY);



            if(estacionCompleta.estacionHora(hora)==null){
              estacionCompleta.agregarEstacion(new EstacionCompleta(hora,temperatura, humedad,rocio,windChill, presion,intensidadViento,rain,rainRate, uv, radiacion));
             }else{
            estacionCompleta.actualizarEstacion(new EstacionCompleta(hora,temperatura, humedad,rocio,windChill, presion,intensidadViento,rain,rainRate, uv, radiacion));
            }

            //Lo mismo pero para la estacion completa, no solo para la temperatura


               // System.out.println("Temperatura actual:"+ServicioLeerEstacion.servicioLeerEStacion()[0]);
                 System.out.println("Temperatura actual:"+temperatura);
                 System.out.println("El wind chill es:"+windChill);
                  System.out.println("P. rocio actual:"+rocio);
                  System.out.println("Intesidad viento:"+intensidadViento);
                  System.out.println("La humedad es:"+humedad);
                  System.out.println("La presion en este momento es:"+presion);
                  System.out.println("Rain rate:"+rainRate);
                  System.out.println("LLuvia diaria:"+rain);
                  System.out.println("UV es:"+uv);
                  System.out.println("Radiacion es:"+radiacion);


    }catch(Exception e){
        System.out.println("Eso paso<<<<<<<<<<<<<<<<<<<<"+e.getMessage());
      }

    }



}
