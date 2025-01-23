package utils.kml;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Kml {
    private ArrayList<PlaceMark> list;
    public Kml() {
    }
    public Kml(ArrayList<PlaceMark> list) {
        this.list = list;
    }
    public String getKml() {
        String result = "";
        String placeMarks = "";
        String begin = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + //
                "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n" +
                "<Document>\n";
        String end = " </Document>\n</kml>\n";
        for (PlaceMark placeMark : list) {
            placeMarks += placeMark.getPlaceMarkKml();
        }
        result = begin + placeMarks + end;   
        return result;
    }
    public void exportFile() {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter("RouterManager/test.kml");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(getKml());
            bufferedWriter.flush();;
            System.out.println("Exported kml !");      
            bufferedWriter.close();  
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
