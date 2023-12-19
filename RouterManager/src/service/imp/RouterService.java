package service.imp;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
    public String getOsVersion(String name) {
        String hostname = routerRepo.getRouter(name).getIp();
        String username = "nghiem";
        String password = "nghiem123";
        int port = 22;
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(username, hostname, port);
            session.setPassword(password);

            // Avoid asking for key confirmation
            session.setConfig("StrictHostKeyChecking", "no");

            // Connect to the remote device
            session.connect();

            // Create a channel for executing commands
            ChannelExec channel = (ChannelExec) session.openChannel("exec");

            // Provide the command you want to execute
            channel.setCommand("show version");

            // Connect the channel
            channel.connect();

            // Read the command output
            InputStream in = channel.getInputStream();
            byte[] buffer = new byte[1024];
            StringBuilder result = new StringBuilder();
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                result.append(new String(buffer, 0, bytesRead));
            }

            // Print the command output

            System.out.println(result.toString());
            // Disconnect the channel and session
            channel.disconnect();
            session.disconnect();
            return result.toString();
        } catch (JSchException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Router> getAll() {
        return routerRepo.getAll();
    }

}
