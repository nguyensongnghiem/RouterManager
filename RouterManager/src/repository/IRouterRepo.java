package repository;

import java.util.ArrayList;

import model.router.Router;

public interface IRouterRepo {
    ArrayList<Router> getAll();
    void updateFileToDb();
    void addRouter(Router router);
    void deleteRouter(String name);
    void updateRouter(String name);  
    Router getRouter(String name);
    int getIndex(String name);
    void updateDbPing(String name, boolean pingStatus);
    String getDbArea(String name);
    void updateDbArea(String name, String Ospf);    
    void updateDbAreaPool(String provinceId, String ospfArea);
    ArrayList<String> getDbAreaPool(String provinceId);
}
