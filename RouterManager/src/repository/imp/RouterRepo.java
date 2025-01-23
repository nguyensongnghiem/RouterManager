package repository.imp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.router.Router;
import model.router.imp.JuniperRouter;
import model.router.imp.NokiaRouter;
import repository.IRouterRepo;

public class RouterRepo implements IRouterRepo {
    private static String DB_URL = "jdbc:mysql://localhost:3306/routermanager";
    private static String USER_NAME = "root";
    private static String PASSWORD = "root";
    private Connection con;

    @Override
    public ArrayList<Router> getAll() {
        ArrayList<Router> routers = new ArrayList<Router>();
        try {
            con = getConnection();
            Statement st = con.createStatement();
            String queryString = "SELECT * FROM routers";
            ResultSet rs = st.executeQuery(queryString);
            while (rs.next()) {
                String name = rs.getString("name");
                String ip = rs.getString("ip");
                String vendor = rs.getString("vendor");
                String siteId = rs.getString("siteId");
                String provinceId = rs.getString("provinceId");
                routers.add(new NokiaRouter(name, ip, vendor, siteId, provinceId));
            }
            closeConnection();

        } catch (Exception e) {
            System.out.println(e);
        }

        return routers;
    }

    @Override
    public void addRouter(Router router) {  // Chưa triển khai
        ArrayList<Router> routers = new ArrayList<Router>();
        routers.add(router);
    }

    @Override
    public void deleteRouter(String name) {
        ArrayList<Router> routers = new ArrayList<Router>();

        for (int i = 0; i < routers.size(); i++) {
            if (routers.get(i).getName().equals(name)) {
                routers.remove(i);
            }
        }
    }

    @Override
    public void updateRouter(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public Router getRouter(String name) {
        Router router = null;
        try {
            con = getConnection();
            Statement statement = con.createStatement();
            String queryString = "SELECT * FROM routers WHERE name=" + "\"" + name + "\"";
            ResultSet rs = statement.executeQuery(queryString);
            if (rs.next()) {
                String ip = rs.getString("ip");
                String vendor = rs.getString("vendor");
                String siteId = rs.getString("siteId");
                String provinceId = rs.getString("provinceId");
                switch (vendor) {
                    case "Nokia":
                    router = new NokiaRouter(name, ip, vendor, siteId, provinceId);
                        
                        break;
                    case "Juniper":
                    router = new JuniperRouter(name, ip, vendor, siteId, provinceId);
                        
                        break;
                    default:
                        break;
                }
            }
            closeConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return router;
    }

    @Override
    public int getIndex(String name) {
        ArrayList<Router> routers = new ArrayList<Router>();

        for (int i = 0; i < routers.size(); i++) {
            if (routers.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void updateFileToDb() {
        try {
            con = getConnection();
            Statement st = con.createStatement();
            String line = "";
            BufferedReader br = new BufferedReader(
                    new FileReader("RouterManager\\src\\RouterData\\PY_CSG_20231223.csv"));
            br.readLine();
            while (((line = br.readLine()) != null)) {
                String[] routerString = line.split(",");
                String name = routerString[0];
                String ip = routerString[1];
                String vendor = routerString[2];
                String siteId = routerString[3];
                String queryString = "INSERT INTO routers (name, ip, vendor, siteId) VALUES (\"" + name + "\",\"" + ip
                        + "\",\""
                        + vendor + "\",\"" + siteId + "\")";
                st.executeUpdate(queryString);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        if (con == null || con.isClosed()) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
        }
        return con;
    }

    public void closeConnection() throws SQLException, ClassNotFoundException {
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }

    @Override
    public void updateDbPing(String name, boolean pingStatus) {
        try {
            int ping;
            if (pingStatus) {
                ping = 1;
            } else
                ping = 0;
            con = getConnection();
            Statement st = con.createStatement();
            String queryString = "UPDATE routers SET ping =" + ping + " WHERE name=\"" + name + "\"";
            st.executeUpdate(queryString);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDbArea(String name, String osfp) {
        try {
            con = getConnection();
            Statement st = con.createStatement();
            String queryString = "UPDATE routers SET ospf =\'" + osfp + "\' WHERE name=\'" + name + "\'";
            System.out.println("Updated " + osfp + "to router " + name + " in Database");
            st.executeUpdate(queryString);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDbArea(String name) {
        String ospf = null;
        try {
            con = getConnection();
            Statement st = con.createStatement();
            String queryString = "SELECT ospf FROM routers WHERE name=\'" + name + "\'";
            ResultSet rs = st.executeQuery(queryString);
            if (rs.next()) {

                ospf = rs.getString("ospf");
                closeConnection();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ospf;
    }

    @Override
    public void updateDbAreaPool(String provinceId, String ospfArea) {
        try {
            con = getConnection();
            Statement st = con.createStatement();
            String queryString = "INSERT INTO ospf_pool (provinceId, ospfArea) VALUES (\"" + provinceId + "\",\""
                    + ospfArea + "\")";
            System.out.println("Insert " + ospfArea + "to ospf pool of " + provinceId + " in Database");
            st.executeUpdate(queryString);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<String> getDbAreaPool(String provinceId) {
        ArrayList<String> ospfPool = new ArrayList<>();
        try {
            con = getConnection();
            Statement st = con.createStatement();
            String queryString = "SELECT ospfArea FROM ospf_pool WHERE provinceId=\'" + provinceId + "\'";
            ResultSet rs = st.executeQuery(queryString);
            while (rs.next()) {
                ospfPool.add(rs.getString("ospfArea"));
            }
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ospfPool;
    }
}
