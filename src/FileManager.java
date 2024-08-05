import java.io.*;
import java.util.ArrayList;
import java.util.List;
/*@desc
@author Bings
@date 2024/7/31 22:01*/

public class FileManager {
    private static final String FILE_NAME = "users.dat";

    public static void saveUsers(List<User> users) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            users = (List<User>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("用户数据文件未找到，初始化新数据。");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("...系统初始化成功");
        return users;
    }
}

