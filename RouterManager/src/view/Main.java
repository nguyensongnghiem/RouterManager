package view;

import controller.RouterController;
import service.imp.RouterService;
/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        RouterService service = new RouterService();
        RouterView view = new RouterView();
        RouterController controller = new RouterController(service, view);
        controller.startMain();
    }
}