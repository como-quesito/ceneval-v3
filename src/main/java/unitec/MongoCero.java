package unitec;

import com.mongodb.*;

import java.net.UnknownHostException;

/**
 * Created by campitos on 9/09/15.
 */
public class MongoCero {

    public Profesor buscarPorLogin(String login){
Profesor p=new Profesor();
        MongoClient mongoClient = null;
        try {
            mongoClient = new MongoClient(new MongoClientURI("mongodb://campitos:raton@ds035563.mongolab.com:35563/unitec"));

            DB database = mongoClient.getDB("unitec");
            DBCollection collection = database.getCollection("profesor");
            DBObject query = new BasicDBObject("login", login);

        DBObject objeto = collection.findOne(query);
       String pas= (String) objeto.get("password");
        String logino=(String)objeto.get("login");
        System.out.println("Hasta aqui todo va bien:"+pas);
        if(pas!=null){
            p.setLogin(logino);
            p.setPassword(pas);
        }
        else{
            p=null;
        }
        }catch(Exception e){
System.out.println("algo malo:"+e.getMessage());
        }
           return p;

    }
}
