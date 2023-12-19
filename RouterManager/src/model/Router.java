package model;

/**
 * Router
 */
public abstract class Router {
    private String name;
    private String ip;
    private String vendor;
    private String siteId;
    public Router(String name, String ip, String vendor, String siteId) {
        this.name = name;
        this.ip = ip;
        this.vendor = vendor;
        this.siteId = siteId;
    }
    public Router() {
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
    
    @Override
    public String toString() {
        return "Router [name=" + name + ", ip=" + ip + ", vendor=" + vendor + ", siteId=" + siteId + "]";
    }
    
}