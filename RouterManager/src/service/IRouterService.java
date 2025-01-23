package service;

import java.util.ArrayList;
import java.util.HashSet;

import model.router.Router;

public interface IRouterService {
    ArrayList<Router> getAll();
    // handle with DB
    String addRouter(Router router);
    String delete(String name);
    String update(String name);  
    Router getRouter(String name);
    String getOspf(String name);
    void updateFileToDb();
    void updateAllPingStatustoDB();
    void updateAllOspfToDB();
    ArrayList<String> getOspfPool(String provinceId);
    HashSet<String> createDbAreaPool(String provinceId);
    int getIndex(String name);
    //handle with router connect
    ArrayList<String> getRunArea(String name);    
    boolean isReachable(String name);    
    
    
}
