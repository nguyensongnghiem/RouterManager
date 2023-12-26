package model.imp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import model.IRouterBehavior;

public class NokiaBehavior implements IRouterBehavior {
    @Override
    public ArrayList<String> getRunArea(String ip) {
        String username = "nghiem";
        String password = "nghiem123";
        int port = 22;
        String finalString = "";
        ArrayList<String> areaList = new ArrayList<>();
        // SSH connect
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(username, ip, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            Channel channel = session.openChannel("shell");
            channel.connect();
            InputStream inputStream = channel.getInputStream();
            OutputStream outputStream = channel.getOutputStream();
            // send commands
            String command1 = "environment no more\n";
            String command2 = "show router ospf area | match 0.0\n";
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
        Scanner scanner = new Scanner(finalString);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!line.contains("0x0")) { // 0x0 mean no LSA count , the area is not working
                String reg = "\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\b";
                Pattern pattern = Pattern.compile(reg);
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    areaList.add(matcher.group());
                }
            }
        }
        scanner.close();

        return areaList;
    }

}
