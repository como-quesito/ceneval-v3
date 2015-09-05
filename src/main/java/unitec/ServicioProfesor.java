package unitec;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by campitos on 2/09/15.
 */

@Configuration
public class ServicioProfesor {
    @Inject
    MongoTemplate mongoTemplate;

            public void agregarProfesor(Profesor profesor){
                if(!mongoTemplate.collectionExists(Profesor.class)){
                    mongoTemplate.createCollection(Profesor.class);
                }
                mongoTemplate.insert(profesor);
            }

    public void borrarColeccion(){
        mongoTemplate.dropCollection(Profesor.class);
    }
    public List<Profesor> obtenerTodos(){
        List<Profesor> Profesors=new ArrayList<>();
        Profesors=mongoTemplate.findAll(Profesor.class);
        return Profesors;
    }
    public Profesor obtenerPorPassword(String password){
    Profesor profesor= mongoTemplate.findOne(new Query(Criteria.where("password").is(password)), Profesor.class);
        return profesor;
    }

    public void borrarPorPassword(String password){
        mongoTemplate.remove(new Query(Criteria.where("password").is(password)), Profesor.class);
    }

    public void agregarReactivo(Reactivo reactivo, String password){
     Profesor p=   obtenerPorPassword(password);
      ArrayList<Reactivo> reactivos=  new ArrayList<>();
          if(p.getReactivos()!=null){
              reactivos=p.getReactivos();
          }

        reactivos.add(reactivo);
        Update update=new Update();
        update.set("reactivos",reactivos);
        mongoTemplate.updateFirst(new Query(Criteria.where("password").is(password)), update, Profesor.class);



    }

    public void borrarReactivo(Integer id, String password){
     Profesor p=obtenerPorPassword(password);
        ArrayList<Reactivo> reactivos=  new ArrayList<>();

        if(p.getReactivos()!=null){
            reactivos=p.getReactivos();

        }
        System.out.println("Tamaño antes:"+reactivos.size());
        reactivos.remove(0);
        //despues de remover actualizamos al profesor
        Update update=new Update();
        update.set("reactivos",reactivos);
        mongoTemplate.updateFirst(new Query(Criteria.where("password").is(password)), update, Profesor.class);
        System.out.println("Tamaño despues de todo:" + reactivos.size());
    }
}
