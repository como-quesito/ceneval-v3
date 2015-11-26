/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitec;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

/**
 *
 * @author campitos
 */
@Component
public class ServicioEstacionCompleta {

    @Inject private MongoTemplate mongoTemplate;

 public void agregarEstacion(EstacionCompleta estacion) {
	        if (!mongoTemplate.collectionExists(EstacionCompleta.class)) {
	            mongoTemplate.createCollection(EstacionCompleta.class);
	        }

	        mongoTemplate.insert(estacion);
	    }


	 public void actualizarEstacion(EstacionCompleta estacion){

			         mongoTemplate.updateFirst(query(where("hora").is(estacion.getHora())), update("temperatura", estacion.getTemperatura()), EstacionCompleta.class);
				 mongoTemplate.updateFirst(query(where("hora").is(estacion.getHora())), update("dewPoint", estacion.getDewPoint()), EstacionCompleta.class);
                                 mongoTemplate.updateFirst(query(where("hora").is(estacion.getHora())), update("humedad", estacion.getHumedad()), EstacionCompleta.class);
                                 mongoTemplate.updateFirst(query(where("hora").is(estacion.getHora())), update("windChill", estacion.getWindChill()), EstacionCompleta.class);
                                 mongoTemplate.updateFirst(query(where("hora").is(estacion.getHora())), update("presion", estacion.getPresion()), EstacionCompleta.class);
                                 mongoTemplate.updateFirst(query(where("hora").is(estacion.getHora())), update("vientoIntensidad", estacion.getVientoIntensidad()), EstacionCompleta.class);
                                 mongoTemplate.updateFirst(query(where("hora").is(estacion.getHora())), update("lluviaDiaria", estacion.getLluviaDiaria()), EstacionCompleta.class);
                                 mongoTemplate.updateFirst(query(where("hora").is(estacion.getHora())), update("rainRate", estacion.getRainRate()), EstacionCompleta.class);
                                 mongoTemplate.updateFirst(query(where("hora").is(estacion.getHora())), update("uv", estacion.getUv()), EstacionCompleta.class);
                                 mongoTemplate.updateFirst(query(where("hora").is(estacion.getHora())), update("radiacion", estacion.getRadiacion()), EstacionCompleta.class);

			 }

	 public List<EstacionCompleta> todosEstacion(){
		 List<EstacionCompleta>  estacion = mongoTemplate.findAll(EstacionCompleta.class);

		 return estacion;
	 }

         public EstacionCompleta estacionId(String id){
         EstacionCompleta e=    mongoTemplate.findById(id, EstacionCompleta.class);

         return e;
         }


         public EstacionCompleta estacionHora(int hora){
             // query to search user
	Query cueri = new Query(Criteria.where("hora").is(hora));
         EstacionCompleta e=    mongoTemplate.findOne(cueri, EstacionCompleta.class);

         return e;
         }
         public String borrarEstacion(String id){

             mongoTemplate.remove(new EstacionCompleta(id));

             return "producto borrado";
         }


}
