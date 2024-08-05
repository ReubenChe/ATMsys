import java.util.*;
/*@desc
@author Bings
@date 2024/7/31 21:17*/

public class ATMSystem {
    private static List<User> users;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        users = FileManager.loadUsers();
        if (users.isEmpty()) {
            initializeUsers();
        }
        showMainMenu();
        FileManager.saveUsers(users);
    }

    private static void initializeUsers() {
        users = new ArrayList<>();
        users.add(new User("111111", "111", 12000));
        users.add(new User("222223", "222", 24000));
        users.add(new User("333333", "333", 18500));
        users.add(new User("444444", "444", 15000));
        users.add(new User("555555", "555", 900));
        users.add(new User("666666", "666", 6300));
        users.add(new User("777777", "777", 7200));
        users.add(new User("888888", "888", 18100));
        users.add(new User("999999", "999", 9100));
        users.add(new User("100000", "100", 20000));

    }

    private static void showMainMenu() {
        while (true) {
            System.out.println("++++++++++++++++++++++++++");
            System.out.println("  >欢迎使用简易 ATM 机系统<");
            System.out.println("++++++++++++++++++++++++++");
            System.out.println(">1. 用户登录");
            System.out.println(">2. 管理员登录");
            System.out.println(">0. 退出系统");
            System.out.print("请选择操作：");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    userLogin();
                    break;
                case 2:
                    adminLogin();
                    break;
                case 0:
                    System.out.println("感谢使用，再见！");
                    System.out.println("++++++++++++++++++++++++++");
                    return;
                default:
                    System.out.println("无效选择，请重新输入。");
            }
        }
    }

    private static void userLogin() {
        System.out.print("请输入卡号：");
        String account = sc.next();
        System.out.print("请输入密码：");
        String password = sc.next();

        User user = findUser(account);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("登录成功！");
            showUserMenu(user);
        } else {
            System.out.println("卡号或密码错误，请重新输入。");
        }
    }

    private static User findUser(String account) {
        for (User user : users) {
            if (user.getAccount().equals(account)) {
                return user;
            }

        }
        return null;
    }

    private static void showUserMenu(User user) {
        while (true) {
            System.out.println("++++++++++++++++++++++++++");
            System.out.println("欢迎用户：[" + user.getAccount() + "]");
            System.out.println(">1. 修改密码");
            System.out.println(">2. 显示余额");
            System.out.println(">3. 存款");
            System.out.println(">4. 取款");
            System.out.println(">5. 转账");
            System.out.println(">0. 退出登录");
            System.out.print("请选择操作：");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    changePassword(user);
                    break;
                case 2:
                    showMoney(user);
                    break;
                case 3:
                    deposit(user);
                    break;
                case 4:
                    withdraw(user);
                    break;
                case 5:
                    transfer(user);
                    break;
                case 0:
                    System.out.println("退出登录成功！");
                    return;
                default:
                    System.out.println("无效选择，请重新输入。");
            }
        }
    }

    private static void changePassword(User user) {
        System.out.print("请输入当前密码：");
        String currentPassword = sc.next();

        if (!user.getPassword().equals(currentPassword)) {
            System.out.println("当前密码错误，请重新输入。");
            return;
        }

        System.out.print("请输入新密码：");
        String newPassword = sc.next();

        System.out.print("请再次输入新密码：");
        String confirmPassword = sc.next();

        if (!newPassword.equals(confirmPassword)) {
            System.out.println("两次输入的新密码不一致，请重新输入。");
            return;
        }

        if (newPassword.equals(currentPassword)) {
            System.out.println("新密码不能与当前密码相同，请重新输入。");
            return;
        }

        user.setPassword(newPassword);
        System.out.println("密码修改成功！");
    }

    private static void showMoney(User user) {
        System.out.println("你的余额为：" + user.getBalance());
    }


    private static void deposit(User user) {                //存款
        System.out.print("请输入存款金额：");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("存款金额必须为正数，请重新输入。");
            return;
        }

        user.setBalance(user.getBalance() + amount);
        System.out.println("存款成功！当前余额为：" + user.getBalance());
    }


    private static void withdraw(User user) {               //取款
        System.out.print("请输入取款金额：");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("取款金额必须为正数，请重新输入。");
            return;
        }

        if (amount > user.getBalance()) {
            System.out.println("余额不足，取款失败。");
            return;
        }

        user.setBalance(user.getBalance() - amount);
        System.out.println("取款成功！当前余额为：" + user.getBalance());
    }

    private static void transfer(User user) {               //转账
        System.out.print("请输入转账卡号：");
        String targetAccount = sc.next();
        System.out.print("请输入转账金额：");
        double amount = sc.nextDouble();

        if (amount <= 0) {
            System.out.println("转账金额必须为正数，请重新输入。");
            return;
        }

        User targetUser = findUser(targetAccount);
        if (targetUser == null) {
            System.out.println("目标卡号不存在，请重新输入。");
            return;
        }

        if (amount > user.getBalance()) {
            System.out.println("余额不足，转账失败。");
            return;
        }

        user.setBalance(user.getBalance() - amount);
        targetUser.setBalance(targetUser.getBalance() + amount);
        System.out.println("转账成功！当前余额为：" + user.getBalance());
    }

    private static void adminLogin() {
        System.out.print("请输入管理员账号：");
        String adminAccount = sc.next();
        System.out.print("请输入管理员密码：");
        String adminPassword = sc.next();

        if ("superadmin".equals(adminAccount) && "123".equals(adminPassword)) {
            System.out.println("管理员登录成功！");
            showAdminMenu();
        } else {
            System.out.println("管理员账号或密码错误，请重新输入。");
        }
    }

    private static void showAdminMenu() {
        while (true) {
            System.out.println("欢迎，管理员");
            System.out.println(">1. 查询所有账户信息");
            System.out.println(">2. 查询存储金额最高和最低的账户信息");
            System.out.println(">3. 查询账户总数和平均存储金额");
            System.out.println(">0. 退出登录");
            System.out.print("请选择操作：");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    searchAllAccounts();
                    break;
                case 2:
                    searchMaxMin();
                    break;
                case 3:
                    searchTotalAndAvg();
                    break;
                case 0:
                    System.out.println("退出登录成功！");
                    return;
                default:
                    System.out.println("无效选择，请重新输入。");
            }

        }
    }

    public static void searchAllAccounts() {
        users.stream()
                .sorted(Comparator.comparingDouble(User::getBalance).reversed())
                .forEach(user -> System.out.println("卡号：" + user.getAccount() + "，余额：" + user.getBalance()));
    }

    public static void searchMaxMin() {
        User maxBalanceUser = users.stream().max(Comparator.comparingDouble(User::getBalance)).orElse(null);
        User minBalanceUser = users.stream().min(Comparator.comparingDouble(User::getBalance)).orElse(null);

        if (maxBalanceUser != null && minBalanceUser != null) {
            System.out.println("存储金额最高的账户：卡号：" + maxBalanceUser.getAccount() + "，余额：" + maxBalanceUser.getBalance());
            System.out.println("存储金额最低的账户：卡号：" + minBalanceUser.getAccount() + "，余额：" + minBalanceUser.getBalance());
        } else {
            System.out.println("没有账户信息。");
        }
    }

    public static void searchTotalAndAvg() {
        int totalAccounts = users.size();
        OptionalDouble averageBalance = users.stream().mapToDouble(User::getBalance).average();

        System.out.println("账户总数：" + totalAccounts);
        averageBalance.ifPresent(avg -> System.out.println("平均存储金额：" + avg));
    }
}

