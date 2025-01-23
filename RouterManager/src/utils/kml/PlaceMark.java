package utils.kml;

public class PlaceMark {
    private String name;
    private String desc;
    private Cordinate cord;

    public PlaceMark() {
    }

    public PlaceMark(String name, String desc, Cordinate cord) {
        this.name = name;
        this.desc = desc;
        this.cord = cord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Cordinate getCord() {
        return cord;
    }

    public void setCord(Cordinate cord) {
        this.cord = cord;
    }

    public String getPlaceMarkKml() {
        return "<Placemark>\n"
                + "<name>" + name + "</name>\n"
                + "<description>" + desc + "</description>\n"
                + "<Point>\n"
                + cord.getKmlCord() + "\n"
                + "</Point>\n"
                + "</Placemark>\n";
    }

}
