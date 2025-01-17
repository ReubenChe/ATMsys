# 简易ATM机系统

简易ATM机系统是一个基于命令行界面的模拟ATM机系统，提供用户登录、管理员登录、查询余额、存款、取款、转账等功能。

## 功能介绍

### 1. 用户功能
- **用户登录**：用户可以使用卡号和密码登录系统。
- **修改密码**：用户可以修改自己的密码。
- **显示余额**：用户可以查询自己的账户余额。
- **存款**：用户可以向自己的账户存款。
- **取款**：用户可以从自己的账户取款。
- **转账**：用户可以向其他用户的账户转账。
- **退出登录**：用户可以退出登录。

### 2. 管理员功能
- **管理员登录**：管理员可以使用管理员账号和密码登录系统。
- **查询所有账户信息**：管理员可以查询所有用户的账户信息。
- **查询存储金额最高和最低的账户信息**：管理员可以查询存储金额最高和最低的账户信息。
- **查询账户总数和平均存储金额**：管理员可以查询账户总数和平均存储金额。
- **退出登录**：管理员可以退出登录。管理员卡号：superadmin    密码：123456

## 使用说明

1. 克隆项目到本地：
    ```bash
    git clone https://github.com/yourusername/atm-system.git
    ```

2. 进入项目目录并编译：
    ```bash
    cd atm-system
    javac -d bin src/*.java
    ```

3. 运行主程序：
    ```bash
    java -cp bin ATMSystem
    ```

## 示例

### 用户登录
```plaintext
++++++++++++++++++++++++++
  >欢迎使用简易 ATM 机系统<
++++++++++++++++++++++++++
>1. 用户登录
>2. 管理员登录
>0. 退出系统
请选择操作：1
请输入卡号：111111
请输入密码：111
登录成功！
++++++++++++++++++++++++++
欢迎用户：[111111]
>1. 修改密码
>2. 显示余额
>3. 存款
>4. 取款
>5. 转账
>0. 退出登录
请选择操作：
