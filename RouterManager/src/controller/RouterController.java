package controller;

import java.util.ArrayList;

import model.Router;
import service.IRouterService;
import service.imp.RouterService;
import view.RouterView;

public class RouterController {
    private IRouterService service;
    private RouterView view;

    public RouterController() {
    }

    public RouterController(RouterService service, RouterView view) {
        this.service = service;
        this.view = view;
    }

    public void addRouter() {
        Router newRouter = view.inputRouter();
        String mess = service.add(newRouter);
        view.showMessage(mess);
    }

    public void deleteRouter() {
        String name = view.inputName();
        String mess = service.delete(name);
        view.showMessage(mess);
    }

    public void showRouter() {
        String name = view.inputName();
        Router router = service.getRouter(name);
        view.showRouter(router);
    }

    public void update(String name) {
    }
    public void updateAllOspfToDB() {
        service.updateAllOspfToDB();
    }
    public void updatePingStatustoDB(){
        service.updateAllPingStatustoDB();
    }
    public void startMain() {
        boolean exitFlag = false;
        do {
            int selected = view.getMenuSelect();
            switch (selected) {
                case 1:
                    // Show all router
                    ArrayList<Router> routers = service.getAll();
                    view.showAll(routers);
                    break;
                case 2:
                    // add router
                    System.out.println("----Add router---");
                    addRouter();
                    break;
                case 3:
                    // show router info by Name
                    System.out.println("show router by name");
                    showRouter();
                    break;
                case 4:
                    // delete router
                    System.out.println("--- Delete router ---");
                    deleteRouter();
                    break;

                case 5:
                    System.out.println("--- get running Ospf version ---");
                    String name = view.inputName();
                    System.out.println(service.getArea(name));
                    break;
                case 6:
                    System.out.println("--- Update All Ospf to DB---");
                    updateAllOspfToDB();
                    break;
                case 7:
                    System.out.println("--- Update Ping to DB---");
                    updatePingStatustoDB();
                    break;
                case 8:
                    System.out.println("--- xxxxx---");

                    break;
                case 10:
                    System.out.println("Exit !");
                    exitFlag = true;
                    break;
            }
        } while (!exitFlag);
    }

}
