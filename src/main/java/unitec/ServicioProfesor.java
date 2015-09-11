package unitec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by campitos on 2/09/15.
 */

@Component
public  class ServicioProfesor {

    @Autowired
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
    /*
    EXAMENSITOOOOO
     */
    public List<Profesor> obtenerTodos(){
        List<Profesor> Profesors=new ArrayList<>();
        Profesors=mongoTemplate.findAll(Profesor.class);
        return Profesors;
    }

    public Profesor obtenerPorPassword(String password){
    Profesor profesor= mongoTemplate.findOne(new Query(Criteria.where("password").is(password)), Profesor.class);
        return profesor;
    }

    public Profesor obtenerPorLogin(String login){
        Profesor profesor= mongoTemplate.findOne(new Query(Criteria.where("login").is(login)), Profesor.class);
        System.out.println("CANSADO"+profesor.getAutoridad());
        return profesor;
    }

    public void borrarPorPassword(String password){
        mongoTemplate.remove(new Query(Criteria.where("password").is(password)), Profesor.class);
    }

    public void agregarReactivo(Profesor profe){
     Profesor p=   obtenerPorPassword(profe.getPassword());
      ArrayList<Reactivo> reactivos=  new ArrayList<>();
          if(p.getReactivos()!=null){
              reactivos=p.getReactivos();
          }

        reactivos.add(profe.getReactivos().get(0));
        Update update=new Update();
        update.set("reactivos",reactivos);
        mongoTemplate.updateFirst(new Query(Criteria.where("password").is(profe.getPassword())), update, Profesor.class);



    }

    public ArrayList<Reactivo> buscarTodosReactivos()throws Exception{
        ArrayList<Reactivo>reactivos=new ArrayList<>();
        Query cueri=new Query(Criteria.where("reactivos").exists(true));
List<Profesor> profesores=mongoTemplate.find(cueri, Profesor.class);
     System.out.println("proofesores con reactivos:" + profesores.size());
        for(Profesor profe:profesores){
            for(Reactivo reactivo:profe.getReactivos()){
                if(reactivo!=null){
                    reactivos.add(reactivo);
                }
            }
        }

        System.out.println("Reactivos encontrados:" + reactivos.size());
        return reactivos;
    }

    public ArrayList<Reactivo> buscarReactivosporTema(String tema)throws Exception{
        ArrayList<Reactivo>reactivos=new ArrayList<>();
        Query cueri=new Query(Criteria.where("reactivos").exists(true));
        List<Profesor> profesores=mongoTemplate.find(cueri, Profesor.class);
        System.out.println("proofesores con reactivos:"+profesores.size());
        for(Profesor profe:profesores){
            for(Reactivo reactivo:profe.getReactivos()){
                if(reactivo!=null&& reactivo.getTema().equals(tema)){
                    reactivos.add(reactivo);
                }
            }
        }

        System.out.println("Reactivos encontrados:"+reactivos.size());
        return reactivos;
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
