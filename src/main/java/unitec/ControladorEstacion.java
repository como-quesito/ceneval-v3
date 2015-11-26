/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitec;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author campitos
 */
@Controller
@RequestMapping("/estacion")
public class ControladorEstacion {
    
  
    @Inject private ServicioEstacionCompleta servicioEstacionCompleta;

    @RequestMapping("/hola")
    public String estacioncita(){
        return "estacion";
    }
    @CrossOrigin
  @RequestMapping(value="/temperatura", method=RequestMethod.GET, headers={"Accept=Application/json;charset=UTF-8"})  
  public @ResponseBody String temperaturas()throws Exception {
        List<EstacionCompleta> estacionCompleta=new ArrayList<EstacionCompleta>();
            estacionCompleta=     servicioEstacionCompleta.todosEstacion();
            
       ObjectMapper mapper=new ObjectMapper();
       EstacionCompare estacionCompare=new EstacionCompare();
       Collections.sort(estacionCompleta, estacionCompare);
       System.out.println(estacionCompleta);
      
       System.out.println("se ha activado el GET TODOS");
       return mapper.writeValueAsString(estacionCompleta);
  }
    
}

class EstacionCompare implements Comparator<EstacionCompleta>{

    public int compare(EstacionCompleta o1, EstacionCompleta o2) {
     return o1.getHora().compareTo(o2.getHora());
    }
    
}