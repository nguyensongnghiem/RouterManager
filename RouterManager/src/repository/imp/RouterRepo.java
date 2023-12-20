package repository.imp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import model.Router;
import model.imp.NokiaRouter;
import repository.IRouterRepo;

public class RouterRepo implements IRouterRepo {

    private static ArrayList<Router> routers = new ArrayList<Router>();
    static {
       routers.add(new NokiaRouter("test", "10.250.88.68", "Nokia", "KHNT05", null));
        // String line="";
        // try {
        //     BufferedReader br = new BufferedReader(new FileReader("RouterManager\\src\\Metro5G.csv"));
        //     while (((line = br.readLine())!= null)) {
        //         String[] routerString = line.split(",");
        //         String name = routerString[0];
        //         String ip = routerString[1];

        //         String vendor = routerString[2];
        //         String siteId = routerString[3];
        //         routers.add(new NokiaRouter(name,ip,vendor,siteId));
        //     }
        // } catch (Exception e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }

    }

    @Override
    public ArrayList<Router> getAll() {
        return routers;
    }

    @Override
    public void add(Router router) {
        routers.add(router);
    }

    @Override
    public void delete(String name) {
        for (int i = 0; i < routers.size(); i++) {
            if (routers.get(i).getName().equals(name)) {
                routers.remove(i);
            } 
        }
    }

    @Override
    public void update(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Router getRouter(String name) {

        for (int i = 0; i < routers.size(); i++) {
            if (routers.get(i).getName().equals(name)) {
                return routers.get(i);
            } 
        }
        return null;
    }

    @Override
    public int getIndex(String name) {
        for (int i = 0; i < routers.size(); i++) {
            if (routers.get(i).getName().equals(name)) {
                return i;
            } 
        }
        return -1;
    }

}
