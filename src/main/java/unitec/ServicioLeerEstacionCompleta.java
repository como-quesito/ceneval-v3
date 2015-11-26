/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitec;

import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author campitos
 */
@Repository
public class ServicioLeerEstacionCompleta {
  public static  String[] servicioLeerTemperaturas(){
       String mensaje="nada";
       boolean encontrada=false;
           int lineas=0;
           int valorLinea=0;
          String temperaturas[]=new String[5];


       String datos="nada";
       try{

            URL url = new URL("http://www.weatherlink.com/user/sierraguadalupe/index.php?view=summary&headers=0");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                                    con.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
        datos=inputLine;
        lineas++;
        if(encontrada && valorLinea<5){
            temperaturas[valorLinea]=inputLine;
            valorLinea++;
        }
        if(inputLine.contains("Outside Temp")){
            encontrada=true;

        }

        }
        int indice=datos.indexOf("#");
      //  mensaje=datos.substring(0, indice);

        }catch(Exception e){

        }

     return temperaturas;
    }

   public static  String[] servicioLeerHumedades(){
       String mensaje="nada";
       boolean encontrada=false;
           int lineas=0;
           int valorLinea=0;
          String humedades[]=new String[5];

       String datos="nada";
       try{

            URL url = new URL("http://www.weatherlink.com/user/sierraguadalupe/index.php?view=summary&headers=0");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                                    con.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
        datos=inputLine;
        lineas++;
        if(encontrada && valorLinea<5){
            humedades[valorLinea]=inputLine;
            valorLinea++;
        }
        if(inputLine.contains("Dew Point")){
            encontrada=true;

        }

        }
        int indice=datos.indexOf("#");
      //  mensaje=datos.substring(0, indice);

        }catch(Exception e){

        }

     return humedades;
    }


   public static  String[] servicioLeerIntensidadViento(){
       String mensaje="nada";
       boolean encontrada=false;
           int lineas=0;
           int valorLinea=0;
          String intensidadViento[]=new String[5];

       String datos="nada";
       try{

            URL url = new URL("http://www.weatherlink.com/user/sierraguadalupe/index.php?view=summary&headers=0");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                                    con.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
        datos=inputLine;
        lineas++;
        if(encontrada && valorLinea<5){
            intensidadViento[valorLinea]=inputLine;
            valorLinea++;
        }
        if(inputLine.contains("Wind Speed")){
            encontrada=true;

        }

        }
        int indice=datos.indexOf("#");
      //  mensaje=datos.substring(0, indice);

        }catch(Exception e){

        }

     return intensidadViento;

   }


   public static  String[] servicioLeerLluvia(){
       String mensaje="nada";
       boolean encontrada=false;
           int lineas=0;
           int valorLinea=0;
          String lluvia[]=new String[5];

       String datos="nada";
       try{

            URL url = new URL("http://www.weatherlink.com/user/sierraguadalupe/index.php?view=summary&headers=0");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                                    con.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
        datos=inputLine;
        lineas++;
        if(encontrada && valorLinea<5){
            lluvia[valorLinea]=inputLine;
            valorLinea++;
        }
        if(inputLine.contains("class=\"summary_data\">Rain")){
            encontrada=true;

        }

        }
        int indice=datos.indexOf("#");
      //  mensaje=datos.substring(0, indice);

        }catch(Exception e){

        }

     return lluvia;

   }


   public static  String[] servicioLeerPresion(){
       String mensaje="nada";
       boolean encontrada=false;
           int lineas=0;
           int valorLinea=0;
          String presion[]=new String[5];

       String datos="nada";
       try{

            URL url = new URL("http://www.weatherlink.com/user/sierraguadalupe/index.php?view=summary&headers=0");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                                    con.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
        datos=inputLine;
        lineas++;
        if(encontrada && valorLinea<5){
            presion[valorLinea]=inputLine;
            valorLinea++;
        }
        if(inputLine.contains("\"summary_data\">Barometer</td>")){
            encontrada=true;

        }

        }
        int indice=datos.indexOf("#");
      //  mensaje=datos.substring(0, indice);

        }catch(Exception e){

        }

     return presion;

   }

   public static  String[] servicioLeerHumedad(){
       String mensaje="nada";
       boolean encontrada=false;
           int lineas=0;
           int valorLinea=0;
          String humedad[]=new String[5];

       String datos="nada";
       try{

            URL url = new URL("http://www.weatherlink.com/user/sierraguadalupe/index.php?view=summary&headers=0");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                                    con.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
        datos=inputLine;
        lineas++;
        if(encontrada && valorLinea<5){
            humedad[valorLinea]=inputLine;
            valorLinea++;
        }
        if(inputLine.contains("class=\"summary_data\">Outside Humidity</td>")){
            encontrada=true;

        }

        }
        int indice=datos.indexOf("#");
      //  mensaje=datos.substring(0, indice);

        }catch(Exception e){

        }

     return humedad;

   }

   public static  String[] servicioLeerWindChill(){
       String mensaje="nada";
       boolean encontrada=false;
           int lineas=0;
           int valorLinea=0;
          String windChill[]=new String[5];

       String datos="nada";
       try{

            URL url = new URL("http://www.weatherlink.com/user/sierraguadalupe/index.php?view=summary&headers=0");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                                    con.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
        datos=inputLine;
        lineas++;
        if(encontrada && valorLinea<5){
            windChill[valorLinea]=inputLine;
            valorLinea++;
        }
        if(inputLine.contains("class=\"summary_data\">Wind Chill</td>")){
            encontrada=true;

        }

        }
        int indice=datos.indexOf("#");
      //  mensaje=datos.substring(0, indice);

        }catch(Exception e){
             System.out.println("<<<<<<<<<<<<<<<<<<<<ESTO MALO PASO:"+e.getMessage());
        }

     return windChill;

   }
    public static  String[] servicioLeerUv(){
       String mensaje="nada";
       boolean encontrada=false;
           int lineas=0;
           int valorLinea=0;
          String windChill[]=new String[5];

       String datos="nada";
       try{

            URL url = new URL("http://www.weatherlink.com/user/sierraguadalupe/index.php?view=summary&headers=0");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                                    con.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
        datos=inputLine;
        lineas++;
        if(encontrada && valorLinea<5){
            windChill[valorLinea]=inputLine;
            valorLinea++;
        }
        if(inputLine.contains("class=\"summary_data\">UV Radiation</td>")){
            encontrada=true;

        }

        }
        int indice=datos.indexOf("#");
      //  mensaje=datos.substring(0, indice);

        }catch(Exception e){
            System.out.println("<<<<<<<<<<<<<<<<<<<<ESTO MALO PASO:"+e.getMessage());
        }

     return windChill;

   }

    public static  String[] servicioLeerRadiacion(){
       String mensaje="nada";
       boolean encontrada=false;
           int lineas=0;
           int valorLinea=0;
          String windChill[]=new String[5];

       String datos="nada";
       try{

            URL url = new URL("http://www.weatherlink.com/user/sierraguadalupe/index.php?view=summary&headers=0");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                                    con.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
        datos=inputLine;
        lineas++;
        if(encontrada && valorLinea<5){
            windChill[valorLinea]=inputLine;
            valorLinea++;
        }
        if(inputLine.contains("class=\"summary_data\">Solar Radiation</td>")){
            encontrada=true;

        }

        }
        int indice=datos.indexOf("#");
      //  mensaje=datos.substring(0, indice);

        }catch(Exception e){

        }

     return windChill;

   }




}
