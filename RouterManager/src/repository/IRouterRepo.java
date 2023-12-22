package repository;

import java.util.ArrayList;

import model.Router;

public interface IRouterRepo {
    ArrayList<Router> getAll();
    void add(Router router);
    void delete(String name);
    void update(String name);  
    Router getRouter(String name);
    int getIndex(String name);
    void updatePing(String name, boolean pingStatus);
    void updateOspf(String name, String Ospf);
    
}
