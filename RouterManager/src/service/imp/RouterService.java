package service.imp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.jcraft.jsch.*;
import model.Router;
import repository.imp.RouterRepo;
import service.IRouterService;

public class RouterService implements IRouterService {
    private RouterRepo routerRepo = new RouterRepo();

    @Override
    public String add(Router router) {
        if (routerRepo.getRouter(router.getName()) == null) {
            routerRepo.add(router);
            return "Router added to database !";
        }
        return "Router already existed";
    }

    @Override
    public String delete(String name) {
        if (routerRepo.getRouter(name) != null) {
            routerRepo.delete(name);
            return "Router removed from database !";
        }
        return "Router not existed !";
    }

    @Override
    public String update(String name) {
        // TODO Auto-generated method stub
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
    public ArrayList<String> getArea(String name) {
        String hostname = routerRepo.getRouter(name).getIp();
        String username = "nghiem";
        String password = "nghiem123";
        int port = 22;
        String finalString = "";
        ArrayList<String> result = new ArrayList<>();
        // SSH connect
        if (isReachable(name)) {

            try {
                JSch jsch = new JSch();
                Session session = jsch.getSession(username, hostname, port);
                session.setPassword(password);
                session.setConfig("StrictHostKeyChecking", "no");
                session.connect();
                Channel channel = session.openChannel("shell");
                channel.connect();
                InputStream inputStream = channel.getInputStream();
                OutputStream outputStream = channel.getOutputStream();
                // send commands
                String command1 = "environment no more\n";
                String command2 = "admin display-config | match \"area 0.0\"\n";
                outputStream.write(command1.getBytes());
                outputStream.write(command2.getBytes());
                outputStream.write("logout\n".getBytes());
                outputStream.flush();
                // read inputStream
                byte[] buffer = new byte[1024];
                int bytesRead;
                while (true) {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        // System.out.println(bytesRead);
                        String output = new String(buffer, 0, bytesRead);
                        finalString = finalString + "\n" + output;
                    }
                    channel.disconnect();
                    session.disconnect();
                    if (channel.isClosed()) {
                        break;
                    }
                }

            } catch (JSchException | IOException e) {
                e.printStackTrace();
            }
            String reg = "\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b";
            Pattern pattern = Pattern.compile(reg);
            Matcher matcher = pattern.matcher(finalString);

            while (matcher.find()) {
                result.add(matcher.group());
                // System.out.println("Match: " + result);
            }
        }
        return result;
    }

    @Override
    public ArrayList<Router> getAll() {
        return routerRepo.getAll();
    }

    @Override
    public boolean isReachable(String name) {
        String hostname = routerRepo.getRouter(name).getIp();
        InetAddress inet;
        // Ping router check
        try {
            inet = InetAddress.getByName(hostname);
            return inet.isReachable(5000);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String getOspfArea(Router router) {

        // TODO Auto-generated method stub
        if (isReachable(router.getName())) {

        }
        throw new UnsupportedOperationException("Unimplemented method 'getOspfArea'");
    }

}
