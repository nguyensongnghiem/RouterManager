package model.imp;

import java.util.ArrayList;

import model.Router;

public class NokiaRouter extends Router{
    private String systemIp ;

    public NokiaRouter(String name, String ip, String vendor, String siteId, String provinceId) {
        super(name, ip, vendor, siteId, provinceId);
        routerBehavior = new NokiaBehavior();
    }   

    public NokiaRouter() {
    }
    
    public NokiaRouter(String name, String ip, String vendor, String siteId, String provinceId, String sysIp) {
        super(name, ip, vendor, siteId, provinceId);
        this.systemIp = sysIp;
    }

    public String getSystemIp() {
        return systemIp;
    }

    public void setSystemIp(String sysIp) {
        this.systemIp = sysIp;
    }

    @Override
    public ArrayList<String> getRunArea() {
        return routerBehavior.getRunArea(this.getIp());
    }
    
    
    
}
