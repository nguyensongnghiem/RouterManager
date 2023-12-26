package model;

import java.util.ArrayList;

/**
 * Router
 */
public abstract class Router {
    protected IRouterBehavior routerBehavior;
    private String name;
    private String ip;
    private String vendor;
    private String siteId;
    private String provinceId;    
    public Router(String name, String ip, String vendor, String siteId, String provinceId) {
        this.name = name;
        this.ip = ip;
        this.vendor = vendor;
        this.siteId = siteId;
        this.provinceId = provinceId;        
    }
    public Router() {
    }
    public IRouterBehavior getRouterBehavior() {
        return routerBehavior;
    }
    public void setRouterBehavior(IRouterBehavior routerBehavior) {
        this.routerBehavior = routerBehavior;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getVendor() {
        return vendor;
    }
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
    public String getSiteId() {
        return siteId;
    }
    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }
    public String getProvinceId() {
        return provinceId;
    }
    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }
    public ArrayList<String> getRunArea() {
        return routerBehavior.getRunArea(ip);
    };
    @Override
    public String toString() {
        return "Router [name=" + name + ", ip=" + ip + ", vendor=" + vendor + ", siteId=" + siteId + ", provinceId=" + provinceId +"]";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {              
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()){
            return false;
        }
        Router anotherRouter = (Router) obj;
        if (this.getName()==anotherRouter.getName() && this.getIp()==anotherRouter.getIp()) {
            return true;
        }
        else return false;
    }
    
    
}