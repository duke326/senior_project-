package com.example.findyourlove;


import android.app.Application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class user_db extends Application {
    static Connection conn;

    private static user_db _user = new user_db();
   // private static user_db _user;
    //private int id;
    private int id = 1;
    private String name;
    private String password;
    private Integer remember;
    private byte[] portrait;
    private String region;
    private String gender;
    private String birthday;

    public static user_db getInstance(){
        return _user;
    }

    private static Connection getConn(){

        Connection connection = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");// 动态加载类
            //String ip = "10.0.30.107";// 写成本机地址，不能写成localhost，同时手机和电脑连接的网络必须是同一个

            // 尝试建立到给定数据库URL的连接
            //connection = DriverManager.getConnection("jdbc:mysql://" + ip + ":3306/" + dbName, user, password);
            connection = DriverManager.getConnection("jdbc:mysql://date.ihghotel.cn:3306/dating","dating","877152223Zzp!");
            System.out.println("成功1");
        }catch (Exception e){
            e.printStackTrace();
        }

        return connection;
    }


    public String db_getInfo(){
        //String temp_name = null;
        new Thread(new Runnable() {
            @Override

            public void run() {
                try {
                    Connection conn = getConn();
                    //user_db.getConn();
                    if(conn!=null){
                        System.out.println("db_getName连接成功");
                        PreparedStatement preparedStatement = conn.prepareStatement("SELECT name FROM dating.Test WHERE id = ?;");
                        preparedStatement.setInt(1,id);
                        ResultSet resultSet = preparedStatement.executeQuery();
                        System.out.println("db_getName执行成功");
                        while (resultSet.next()) {
                            name = resultSet.getString(1);
                            }

                        preparedStatement = conn.prepareStatement("SELECT region FROM dating.Test WHERE id = ?;");
                        preparedStatement.setInt(1,id);
                        resultSet = preparedStatement.executeQuery();
                        System.out.println("db_getRegion执行成功");
                        while (resultSet.next()) {
                            region = resultSet.getString(1);
                        }

                        preparedStatement = conn.prepareStatement("SELECT birthday FROM dating.Test WHERE id = ?;");
                        preparedStatement.setInt(1,id);
                        resultSet = preparedStatement.executeQuery();
                        System.out.println("db_getBirthday执行成功");
                        while (resultSet.next()) {
                            birthday = resultSet.getString(1);
                        }

                        conn.close();
                        System.out.println("user_db.db_getInfo数据库连接关闭");
                    }
                    System.out.println("db_getName执行结果 name =" + name);
                    System.out.println("db_getRegion执行结果 region =" + region);
                    System.out.println("db_getBirth执行结果 birthday =" + birthday);
                 } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        }).start();
        return name;
    }

    public static String db_getName2(int id1){
        String s = null;
        Connection conn = getConn();
        try {
            if(conn!=null){
                System.out.println("db_getName2连接成功");
                PreparedStatement preparedStatement = conn.prepareStatement("SELECT name FROM dating.Test WHERE id = ?;");
                preparedStatement.setInt(1,id1);
                ResultSet resultSet = preparedStatement.executeQuery();
                System.out.println("db_getName2执行成功");
                while (resultSet.next()) {
                    s = resultSet.getString(1);
                }
                preparedStatement.close();
            }
            System.out.println("db_getName2执行结果 name =" + s);
            conn.close();
            System.out.println("user_db.db_getName2数据库连接关闭");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("db_getName2 s最终结果为"+ s);
        return s;
    }

    public static String db_getRegion(int id1){
        String s = null;
        Connection conn = getConn();
        try {
            if(conn!=null){
                //System.out.println("db_getName2连接成功");
                PreparedStatement preparedStatement = conn.prepareStatement("SELECT region FROM dating.Test WHERE id = ?;");
                preparedStatement.setInt(1,id1);
                ResultSet resultSet = preparedStatement.executeQuery();
                //System.out.println("db_getName2执行成功");
                while (resultSet.next()) {
                    s = resultSet.getString(1);
                }
                preparedStatement.close();
            }
            //System.out.println("db_getName2执行结果 name =" + s);
            conn.close();
            //System.out.println("user_db.db_getName2数据库连接关闭");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("db_getName2 s最终结果为"+ s);
        return s;
    }

    public static String db_getBirth(int id1){
        String s = null;
        Connection conn = getConn();
        try {
            if(conn!=null){
                System.out.println("db_getName2连接成功");
                PreparedStatement preparedStatement = conn.prepareStatement("SELECT birthday FROM dating.Test WHERE id = ?;");
                preparedStatement.setInt(1,id1);
                ResultSet resultSet = preparedStatement.executeQuery();
                System.out.println("db_getName2执行成功");
                while (resultSet.next()) {
                    s = resultSet.getString(1);
                }
                preparedStatement.close();
            }
            System.out.println("db_getName2执行结果 name =" + s);
            conn.close();
            System.out.println("user_db.db_getName2数据库连接关闭");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("db_getName2 s最终结果为"+ s);
        return s;
    }

    public static String db_getGender(int id1){
        String s = null;
        Connection conn = getConn();
        try {
            if(conn!=null){
                //System.out.println("db_getName2连接成功");
                PreparedStatement preparedStatement = conn.prepareStatement("SELECT gender FROM dating.Test WHERE id = ?;");
                preparedStatement.setInt(1,id1);
                ResultSet resultSet = preparedStatement.executeQuery();
                //System.out.println("db_getName2执行成功");
                while (resultSet.next()) {
                    s = resultSet.getString(1);
                }
                preparedStatement.close();
            }
            //System.out.println("db_getName2执行结果 name =" + s);
            conn.close();
            //System.out.println("user_db.db_getName2数据库连接关闭");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println("db_getName2 s最终结果为"+ s);
        return s;
    }


    public static ArrayList<String> db_init(int id1){
        ArrayList<String> info = new ArrayList<String>();
        Connection conn = getConn();
        try {
            if(conn!=null){
                System.out.println("db_init连接成功");
                PreparedStatement preparedStatement = conn.prepareStatement("SELECT name, birthday, region FROM dating.Test WHERE id = ?;");
                preparedStatement.setInt(1,id1);
                ResultSet resultSet = preparedStatement.executeQuery();
                System.out.println("db_getName2执行成功");
                info.add(resultSet.getString(1));
                info.add(resultSet.getString(2));
                info.add(resultSet.getString(3));
/*                while (resultSet.next()) {
                    s = resultSet.getString(1);
                }*/
                preparedStatement.close();
            }
            System.out.println("db_inint执行结果  =" + info.get(0) + info.get(1) + info.get(2));
            conn.close();
            System.out.println("user_db.db_init连接关闭");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("db_init s最终结果为"+ info);
        return info;
    }


}
