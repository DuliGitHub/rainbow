package socketprogram;

import java.io.*;
import java.net.Socket;

/**
 * ━━━━━━如来保佑━━━━━━
 * ━━━━━━如来保佑━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　┻　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━━━永无BUG━━━━━━
 *
 * @author Ledev4
 * @date 2018/6/23 15:24 星期六
 */


public class Client {
    public static void main(String[] args) {
        try{
            Socket socket = new Socket("localhost",8080);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("server: " + in.readLine());
//            DataInputStream dis = new DataInputStream(socket.getInputStream());
//            String s = new String(dis.readUTF());
//            System.out.println(s);
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
