package unitec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;

/**
 * Created by campitos on 8/09/15.
 */
@Service
@ComponentScan
public class ProfesorListas {
@Autowired
ServicioProfesor servicioProfesor;
    ArrayList<Profesor> profe=new ArrayList<>();
    public void llenar() {

        Profesor p1=new Profesor();
        p1.setLogin("campitos");
        p1.setAutoridad("Profesor");
        p1.setEmail("rapidclimate@gmail.com");
        p1.setPassword("33868");
        Profesor p2=new Profesor();
        p2.setLogin("angel");
        p2.setAutoridad("Administrador");
        p2.setPassword("director");

        Profesor p3=new Profesor();
        p3.setLogin("juan");
        p3.setAutoridad("Alumno");
        p3.setPassword("777");
        profe.add(p1);
        profe.add(p2);
        profe.add(p3);


    }



    public Profesor buscarPorLogin(String logito){
         llenar();

       //servicioProfesor.obtenerPorLogin(logito);
        Profesor p=new Profesor();
        System.out.println("Si entrooooooooooooooo mijo mijo:"+profe.size());

   for(Profesor pro:profe) {
       if (pro.getLogin().equals(logito)) {
           // profebuscado=p;

             System.out.println("Si entrooooooooooooooo en el if");
       p = new Profesor();
           p.setLogin(pro.getLogin());
           p.setPassword(pro.getPassword());
           p.setAutoridad(pro.getAutoridad());

           break;

       } else {
           p=null;
       }
   }

return p;
    }
}
