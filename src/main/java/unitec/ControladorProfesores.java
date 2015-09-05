package unitec;



import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.gridfs.GridFSDBFile;
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
    String guardar(@RequestBody String json) throws Exception {
        String mensaje="nada :'(";




        Map<String, String> map = getMap(json);

        String nombre=map.get("nombre");
        String paterno=map.get("paterno");
        String  password=map.get("password");

        Profesor p=new Profesor();
        p.setNombre(nombre);
        p.setPaterno(paterno);
        p.setPassword(password);

       servicioProfesor.agregarProfesor(p);
        mensaje="Ha llegado el profesor y se guardo con nombre "+nombre+" , paterno "+paterno+" ,password "+password;

        System.out.println(mensaje);

        return json;
    }


    /*
    Post REACTIVO

     */


    @RequestMapping(value = "/pregunta", method = RequestMethod.GET, headers = {"Accept=text/html"})
    @ResponseBody
    String guardarReactivo() throws Exception {
        String mensaje="nada :'(";

           Reactivo reactivo=new Reactivo();
        Opcion o1=new Opcion();
        o1.setTitulo("primera de dos");
        o1.setAcierto(false);
        Opcion o2=new Opcion();
        o2.setTitulo("segunda de dos");
        o2.setAcierto(true);
        ArrayList<Opcion> opciones=new ArrayList<>();
        opciones.add(o1);
        opciones.add(o2);
        reactivo.setOpciones(opciones);
        reactivo.setPregunta("Esta es la segunda pregunta");
        reactivo.setRetroalimentacion("NO hay rtro");
        reactivo.setTema("Uno");
        reactivo.setSubtema("Uno b");
        servicioProfesor.agregarReactivo(reactivo,"33868");

        return "Se agrego el reactivo";
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
    @RequestMapping(value="/cargar-mongo1", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file)throws Exception{
        String nombre=file.getOriginalFilename();
        long tamano= file.getSize();
        String fileName = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        if (file.getSize() > 0) {
            inputStream = file.getInputStream();


            String contenido=  file.getContentType();
            int punto=nombre.indexOf(".");
            String nombreSolo=nombre.substring(0, punto);
            //   System.out.println("nombre de archivo:"+fileName);
            // gridFsTemplate.store(inputStream,nombre);
            gridFsTemplate.store(inputStream,nombre,file.getContentType());

        }
        System.out.println("El nombre de archivaldo es:" + nombre + " el tamaño del archivo esta:" + tamano);

        return  "guardado con exito";

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
