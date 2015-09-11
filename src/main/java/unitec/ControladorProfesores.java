package unitec;



import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.gridfs.GridFSDBFile;
import org.joda.time.DateTime;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by campitos on 21/08/15.
 */
@RestController
@RequestMapping("/")
public class ControladorProfesores {

    @Inject
    ServicioProfesor servicioProfesor;

    @Inject
    GridFsTemplate gridFsTemplate;


    @CrossOrigin
    @RequestMapping(value = "/profesor", method = RequestMethod.GET, headers = {"Accept=application/json"})
    @ResponseBody
    ArrayList<Profesor> obtenerProfesor() throws Exception {


        ArrayList<Profesor> profesores = new ArrayList<>();
       profesores= (ArrayList<Profesor>) servicioProfesor.obtenerTodos();
        return profesores;
    }
/*
POST GUARDAR PROFESOR
 */
    @RequestMapping(value = "/profesor", method = RequestMethod.POST, headers = {"Accept=application/json"})
    @ResponseBody
    String guardarProfesor(@RequestBody String json) throws Exception {
        String mensaje="nada :'(";




        Map<String, String> map = getMap(json);

        String nombre=map.get("nombre");
        String paterno=map.get("paterno");
        String  password=map.get("password");
        String login=map.get("login");
        String autoridad=map.get("autoridad");

        Profesor p=new Profesor();

        p.setNombre(nombre);
        p.setPaterno(paterno);
        p.setPassword(password);
        p.setLogin(login);
        p.setAutoridad(autoridad);

       servicioProfesor.agregarProfesor(p);
        mensaje="Ha llegado el profesor y se guardo con nombre "+nombre+" , paterno "+paterno+" ,password "+password+" login:"+login+" autoridad:"+autoridad;

        System.out.println(mensaje);

        return json;
    }


    /*
    Post REACTIVO

     */


    @RequestMapping(value = "/reactivo", method = RequestMethod.POST, headers = {"Accept=application/json"})
    @ResponseBody
    String guardarReactivo(@RequestBody String json) throws Exception {
        String mensaje="nada :'(";


        ObjectMapper mapper = new ObjectMapper();
        Profesor p = mapper.readValue(json, Profesor.class);
      String password=  p.getPassword();
      String pregunta=   p.getReactivos().get(0).getPregunta();
      boolean acierto1=  p.getReactivos().get(0).getOpciones().get(0).isAcierto();
       String titulo1 =  p.getReactivos().get(0).getOpciones().get(0).getTitulo();
        String tema=p.getReactivos().get(0).getTema();
        String urlimagen=p.getReactivos().get(0).getUrlimagen();

//Guardamos el Profeso con su reactivo, no olvide desompentar el DE ABAJOOOO!!
        servicioProfesor.agregarReactivo(p);
        mensaje="Ha llegado el json y se guardo con password: "+password+" y la pregunta es:"+pregunta +" el primer acierto es:"+acierto1+" urlima:"+urlimagen+" su primer titulo:"+titulo1+" con tema:"+tema;

        System.out.println(mensaje);

        return json;
    }

    @RequestMapping(value = "/borrar-pregunta/{indice}/{clave}", method = RequestMethod.GET, headers = {"Accept=text/html"})
    @ResponseBody
    String borrarPregunta(@PathVariable Integer indice, @PathVariable String clave) throws Exception {
        String mensaje = "nada :'(";
          servicioProfesor.borrarReactivo(indice,clave);
          mensaje="reactivo borrado";

        return mensaje;
    }

/*
GUARDAR IMAGEN ELN MONGODB
 */
    @RequestMapping(value="/cargar-mongo1", method=RequestMethod.POST, headers={"Accept=text/html"})
    public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file)throws Exception{
        String nombre=file.getOriginalFilename();
        String prefijo="";
        DateTime date=new DateTime();
      int dia=  date.getDayOfYear();
      int segundo=  date.getSecondOfDay();
        long tamano= file.getSize();
        String fileName = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        if (file.getSize() > 0) {
            inputStream = file.getInputStream();


            String contenido=  file.getContentType();
            int punto=nombre.indexOf(".");
            String nombreSolo=nombre.substring(0, punto);
            prefijo="dia"+dia+"segundo"+segundo;
            //   System.out.println("nombre de archivo:"+fileName);
            //Guardamos imagen, si es que hay
            gridFsTemplate.store(inputStream,prefijo+nombre,file.getContentType());


        }
        System.out.println("El nombre de archivaldo es:" + nombre + " el tamaño del archivo esta:" + tamano + " se guardo como: " + prefijo + nombre);

        return  prefijo+nombre;

    }

    /*
    Para leer la imagen DE MONGODB
    */
    @RequestMapping(value="/leer-imagen/{nombre:.+}", method=RequestMethod.GET)
    public @ResponseBody byte[] culera2(HttpServletResponse response, @PathVariable String nombre)throws IOException {
        GridFSDBFile filesito=gridFsTemplate.findOne(new Query(Criteria.where("filename").is(nombre)));
        File imageFile=new File(nombre);
        filesito.writeTo(imageFile);
        byte[] bytes= FileCopyUtils.copyToByteArray(imageFile);
        System.out.println("Recobrando correctamente con este metodo del todo nuevo");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + imageFile.getName() + "\"");
        response.setContentLength(bytes.length);
        response.setContentType("image/png");
        return bytes;
    }

/*
EXAMENSITOOOOO
 */
    @RequestMapping(value="/examen", method=RequestMethod.GET, headers={"Accept=application/json"})
    @ResponseBody String examinar()throws Exception{
        String json="";
        ArrayList<Reactivo> reactivos=new ArrayList<>();
        reactivos= servicioProfesor.buscarTodosReactivos();

        ObjectMapper objectMapper=new ObjectMapper();

        return objectMapper.writeValueAsString(reactivos);
    }

    /*

    EXAMENSITO POR TEMAAAAA!!!!!
     */
    @RequestMapping(value="/examen/{tema}", method=RequestMethod.GET, headers={"Accept=application/json"})
    @ResponseBody String examinarPorTema(@PathVariable String tema)throws Exception{
        String json="";
        ArrayList<Reactivo> reactivos=new ArrayList<>();
        reactivos= servicioProfesor.buscarReactivosporTema(tema);

        ObjectMapper objectMapper=new ObjectMapper();

        return objectMapper.writeValueAsString(reactivos);
    }

/*
METODO AUXILIAR
 */
    private Map<String, String> getMap(String json) throws java.io.IOException {
        Map<String,String> map = new HashMap<String,String>();
        ObjectMapper mapper = new ObjectMapper();
        //convert JSON string to Map
        map = mapper.readValue(json,
                new TypeReference<HashMap<String,String>>(){});
        return map;
    }
}
