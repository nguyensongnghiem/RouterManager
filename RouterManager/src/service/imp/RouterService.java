package service.imp;

import java.lang.reflect.Type;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashSet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.router.Router;
import repository.IRouterRepo;
import repository.imp.RouterRepo;
import service.IRouterService;

public class RouterService implements IRouterService {
    private IRouterRepo routerRepo = new RouterRepo();

    @Override
    public String addRouter(Router router) {
        if (routerRepo.getRouter(router.getName()) == null) {
            routerRepo.addRouter(router);
            return "Router added to database !";
        }
        return "Router already existed";
    }

    @Override
    public String delete(String name) {
        if (routerRepo.getRouter(name) != null) {
            routerRepo.deleteRouter(name);
            return "Router removed from database !";
        }
        return "Router not existed !";
    }

    @Override
    public String update(String name) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Router getRouter(String name) {
        return routerRepo.getRouter(name);
    }

    @Override
    public int getIndex(String name) {
        return routerRepo.getIndex(name);
    }

    @Override
    public ArrayList<String> getRunArea(String name) {
        // String hostname = routerRepo.getRouter(name).getIp();
        Router router = getRouter(name);
        System.out.println(router);
        ArrayList<String> areaList = router.getRunArea();
        return areaList;
    }

    @Override
    public ArrayList<Router> getAll() {
        return routerRepo.getAll();
    }

    @Override
    public boolean isReachable(String name) {
        Router router = routerRepo.getRouter(name);
        return router.isReachable();        
    }

    @Override
    public void updateAllOspfToDB() {
        ArrayList<Router> routers = routerRepo.getAll();
        for (Router router : routers) {
            String name = router.getName();
            String ospfDB = routerRepo.getDbArea(name);
            if (ospfDB == null || ospfDB == "[]") {
                ArrayList<String> areaList = getRunArea(name);
                Gson gson = new Gson();
                String areaToJson = gson.toJson(areaList);
                routerRepo.updateDbArea(name, areaToJson);
            }
        }
    }

    @Override
    public void updateAllPingStatustoDB() {
        ArrayList<Router> routers = routerRepo.getAll();
        for (Router router : routers) {
            boolean pingStatus = router.isReachable();
            routerRepo.updateDbPing(router.getName(), pingStatus);
        }
    }

    @Override
    public void updateFileToDb() {
        routerRepo.updateFileToDb();
    }

    @Override
    public HashSet<String> createDbAreaPool(String provinceId) {
        ArrayList<Router> routers = routerRepo.getAll();
        HashSet<String> ospfSet = new HashSet<>();
        for (Router router : routers) {
            String name = router.getName();
            String routerProvince = router.getProvinceId();
            if (routerProvince.equals(provinceId)) {
                String ospfDB = routerRepo.getDbArea(name);
                Gson gson = new Gson();
                Type collectionType = new TypeToken<ArrayList<String>>() {
                }.getType();
                ArrayList<String> list = gson.fromJson(ospfDB, collectionType);
                for (String ospf : list) {
                    ospfSet.add(ospf);
                }
            }
        }
        System.out.println(ospfSet.toString());
        for (String ospfArea : ospfSet) {
            routerRepo.updateDbAreaPool(provinceId, ospfArea);
        }
        return ospfSet;
    }

    @Override
    public ArrayList<String> getOspfPool(String provinceId) {
        return routerRepo.getDbAreaPool(provinceId);
    }

    @Override
    public String getOspf(String name) {
        return routerRepo.getDbArea(name);
    }
}
