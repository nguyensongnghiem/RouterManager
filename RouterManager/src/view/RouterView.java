package view;

import java.util.ArrayList;
import java.util.Scanner;

import model.router.Router;
import model.router.imp.NokiaRouter;

public class RouterView {
    private Scanner scanner = new Scanner(System.in);

    public void showAll(ArrayList<Router> routers) {
        System.out.println("---------------- List of all -------------------");
        for (Router router : routers) {
            System.out.println(router);
        }
        System.out.println("---------------- End of list -------------------");
    }

    public void showRouter(Router router) {
        System.out.println("---------------- Router info -------------------");
        System.out.println(router);
        System.out.println("---------------- End of info -------------------");
    }

    public void showOsVersion(String info) {
        System.out.println();
    }

    public int getMenuSelect() {
        System.out.println("ROUTER MANAGER ");
        System.out.println("1. Display all routers from DB");
        System.out.println("2. Add new router to DB ");
        System.out.println("3. Show info by router name");
        System.out.println("4. Delete router from DB");
        System.out.println("5. Show running ospf by Router name ");
        System.out.println("6. Update All running ospf to DB -- ### CAREFUL ### ");
        System.out.println("7. Update ping status to DB");
        System.out.println("8. Import CSV file to DB ### CAREFUL ###");
        System.out.println("9. Update Ospf Pool in DB base on ospf in DB ## By Province ID ## ");
        System.out.println("10. Show all Ring ## By Province ID ##");
        System.out.println("15. Exit");
        System.out.print("Select a task: ");
        int selected = Integer.parseInt(scanner.nextLine());
        return selected;
    }

    public String inputName() {
        System.out.print("Enter router name: ");
        String name = scanner.nextLine();
        return name;
    }
    public String inputProviceId() {
        System.out.print("Enter Province Id: ");
        String provinceId = scanner.nextLine();
        return provinceId;
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
        System.out.print("Enter province ID: ");
        String provinceId = scanner.nextLine();
        return new NokiaRouter(name, ip, vendor, siteId, provinceId);
    }

    public void showMessage(String mess) {
        System.out.println(mess);

    }

}
