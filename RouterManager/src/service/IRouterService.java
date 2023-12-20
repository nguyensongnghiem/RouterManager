package service;

import java.util.ArrayList;

import model.Router;

public interface IRouterService {
    ArrayList<Router> getAll();
    String add(Router router);
    String delete(String name);
    String update(String name);  
    Router getRouter(String name);
    int getIndex(String name);
    ArrayList<String> getArea(String name);    
    boolean isReachable(String name);
    String getOspfArea(Router router);
}
