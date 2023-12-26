package model.imp;

import java.util.ArrayList;

import model.Router;

public class JuniperRouter extends Router {

    public JuniperRouter(String name, String ip, String vendor, String siteId, String provinceId) {
        super(name, ip, vendor, siteId, provinceId);
        routerBehavior = new JuniperBehavior();
    }   

    public JuniperRouter() {
    }

    @Override
    public ArrayList<String> getRunArea() {
        return routerBehavior.getRunArea(this.getIp());
    }
    
    
    
}
