package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.Router;
import model.imp.NokiaRouter;

public class RouterView {
    private Scanner scanner= new Scanner(System.in);
    public void showAll(ArrayList<Router> routers) {
        System.out.println("-- List of all ---");
        for (Router router : routers) {
            System.out.println(router);
        }
        System.out.println("-- End of list ---");
    }
    public void showRouter(Router router) {
        System.out.println("--- Router info ---");
        System.out.println(router);
        System.out.println("--- End of info ---");
    }
    public void showOsVersion(String info) {
        System.out.println();
    }
    public int getMenuSelect() {
            System.out.println("ROUTER MANAGER ");
            System.out.println("1. Display all routers ");
            System.out.println("2. Add new router ");
            System.out.println("3. Show info by name");
            System.out.println("4. Delete router");
            System.out.println("5. Show running ospf");
            System.out.println("6. Update All ospf to DB");
            System.out.println("7. Update ping status to DB");
            System.out.println("8. xxxx");
            System.out.println("10. Exit");
            System.out.print("Select a task: ");
            int selected = Integer.parseInt(scanner.nextLine());
            return selected;
    }
    public String inputName() {
        System.out.print("Enter router name: ");
        String name = scanner.nextLine();
        return name;
    }
    public Router inputRouter() {
        System.out.print("Enter router name: ");
        String name = scanner.nextLine();
        System.out.print("Enter router ip: ");
        String ip = scanner.nextLine();
        System.out.print("Enter SideID: ");
        String siteId = scanner.nextLine();
        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();
        return new NokiaRouter(name, ip, vendor, siteId);
    }
    public void showMessage(String mess) {
        System.out.println(mess);
        
    }
    
}
