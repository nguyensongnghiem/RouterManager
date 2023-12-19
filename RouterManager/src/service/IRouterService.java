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
    String getOsVersion(String name);    
}
