package kml;

public class Cordinate {
    private float longtitude;
    private float latitude;
    public Cordinate(float longtitude, float latitude) {
        this.longtitude = longtitude;
        this.latitude = latitude;
    }
    public Cordinate() {
    }
    public float getLongtitude() {
        return longtitude;
    }
    public void setLongtitude(float longtitude) {
        this.longtitude = longtitude;
    }
    public float getLatitude() {
        return latitude;
    }
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }    
    public String getKmlCord() {
        return "  <coordinates>"+longtitude +","+ latitude+"</coordinates>";
    }

}
