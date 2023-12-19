package repository.imp;

import java.util.ArrayList;

import model.Router;
import model.imp.NokiaRouter;
import repository.IRouterRepo;

public class RouterRepo implements IRouterRepo {

    private static ArrayList<Router> routers = new ArrayList<Router>();
    static {
        routers.add(new NokiaRouter("CSG-KHNT05", "10.250.92.49", "Nokia", "KHNT05", null));
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
