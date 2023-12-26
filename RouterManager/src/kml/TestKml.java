package kml;

import java.util.ArrayList;

public class TestKml {
public static void main(String[] args) {
    Cordinate cord1 = new Cordinate(108.24f, 13.124f);
    Cordinate cord2 = new Cordinate(108.24f, 13.124f);

    String name1 = "KHNT01";
    String desc1 = "Site test";
    String name2 = "KHNT15";
    String desc2 = "Site test";
    PlaceMark p1 = new PlaceMark(name1, desc1, cord1);
    PlaceMark p2 = new PlaceMark(name2, desc2, cord2);
    ArrayList<PlaceMark> list = new ArrayList<>();
    list.add(p1);
    list.add(p2);
    Kml kml = new Kml(list);
    System.out.println(kml.getKml());
    kml.exportFile();
}
}
