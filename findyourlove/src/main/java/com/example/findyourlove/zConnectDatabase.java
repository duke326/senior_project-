package com.example.findyourlove;

import com.microsoft.maps.Geopoint;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class zConnectDatabase {
    static Connection conn;
    public static void Connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("connect to database");
        conn = DriverManager.getConnection("jdbc:mysql://date.ihghotel.cn:3306/dating","dating","877152223Zzp!");
    }
    public static void addUser(int accid, String token, String email, String password) throws SQLException, ClassNotFoundException {
        if(conn==null)
            zConnectDatabase.Connect();
        PreparedStatement preparedStatement=conn.prepareStatement("INSERT INTO user(accid, token, email, password) VALUES(?,?,?,?)");
        preparedStatement.setInt(1,accid);
        preparedStatement.setString(2,token);
        preparedStatement.setString(3,email);
        preparedStatement.setString(4,password);
        preparedStatement.execute();
    }
    public static int getAccid(String token,String email,String password) throws SQLException, ClassNotFoundException {
        //two log in method. The first one is log in with token, returning accid. Email and password are null. The second one is log in with email and password, returning token and accid. Token should be null
        int accid=0;
        if(conn==null)
            zConnectDatabase.Connect();
        if(token!=null) {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT accid FROM user WHERE token=?");
            preparedStatement.setString(1, token);
            ResultSet resultSet=preparedStatement.executeQuery();

            while(resultSet.next()){
                accid=resultSet.getInt(1);
            }
        }
        else{
            PreparedStatement preparedStatement=conn.prepareStatement("SELECT token, accid FROM user WHERE email=? AND password=?");
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            ResultSet resultSet=preparedStatement.executeQuery();
            String token2=null;


            if(resultSet.getFetchSize()==0){
                System.out.println("ERROR: EMAIL OR PASSWORD");
            }
            else{
                while(resultSet.next()){
                    token=resultSet.getString(1);
                    accid=resultSet.getInt(2);

//Here! Store the token in the storage!!!!
                }
            }

        }
        return accid;
    }

    public static ResultSet getSurroundingUserLocation(Geopoint geopoint,double radius) throws SQLException, ClassNotFoundException {
        if(conn==null)
            zConnectDatabase.Connect();
        double longitude=geopoint.getPosition().getLongitude();
        double latitude=geopoint.getPosition().getLatitude();
        PreparedStatement preparedStatement=conn.prepareStatement("SELECT * FROM Location WHERE longitude - ? < ? and latitude - ? < ?");
        preparedStatement.setDouble(1,longitude);
        preparedStatement.setDouble(2,radius);
        preparedStatement.setDouble(3,latitude);
        preparedStatement.setDouble(4,radius);

        ResultSet resultSet=preparedStatement.executeQuery();


        return  resultSet;
    }
    public static String[] getUser(int accid) throws SQLException, ClassNotFoundException {
        if(conn==null)
            zConnectDatabase.Connect();
        PreparedStatement preparedStatement=conn.prepareStatement("SELECT name, gender FROM Test WHERE id = ?");
        preparedStatement.setInt(1,accid);
        String[] user=new String[2];
        ResultSet resultSet=preparedStatement.executeQuery();
        resultSet.next();
        user[0]=resultSet.getString(1);
        user[1]=resultSet.getString(2);

        return user;
    }
    public static void uploadLocation(Geopoint geopoint,int accid) throws SQLException, ClassNotFoundException {
        if(conn==null)
            zConnectDatabase.Connect();
        PreparedStatement preparedStatement=conn.prepareStatement("UPDATE Location SET latitude = ?, longitude = ? WHERE accid = ?");
        preparedStatement.setDouble(1,geopoint.getPosition().getLatitude());
        preparedStatement.setDouble(2,geopoint.getPosition().getLongitude());
        preparedStatement.setInt(3,accid);
        preparedStatement.execute();


    }
}
