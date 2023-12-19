package model.imp;

import model.Router;

public class NokiaRouter extends Router{
    private String sysIp ;

    public NokiaRouter(String name, String ip, String vendor, String siteId) {
        super(name, ip, vendor, siteId);
    }

    public NokiaRouter() {
    }
    
    public NokiaRouter(String name, String ip, String vendor, String siteId, String sysIp) {
        super(name, ip, vendor, siteId);
        this.sysIp = sysIp;
    }

    public String getSysIp() {
        return sysIp;
    }

    public void setSysIp(String sysIp) {
        this.sysIp = sysIp;
    }
    
    
    
}
