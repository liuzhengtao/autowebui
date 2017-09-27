package com.yuminsoft.com.autoweb.common.util;

import java.sql.*;

/**
 * Created by 刘正涛 on 2017/9/27.
 */
public class SqlUtil {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet resultSet = null;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/seleniumui?useUnicode=true&characterEncoding=UTF-8";
    static final String USER = "root";
    static final String PASS = "root";
    public Connection getConnect(){
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public Statement getStatement() throws SQLException {
        stmt=conn.createStatement();
        return stmt;
    }

    public ResultSet getQuery(String sql){
        try {
            resultSet= stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public boolean excuet(String sql){
        boolean flag=false;
        try {
            flag= stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Statement getStmt() {
        return stmt;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public static String getPASS() {
        return PASS;
    }

    public static String getJdbcDriver() {
        return JDBC_DRIVER;
    }

    public static String getDbUrl() {
        return DB_URL;
    }

    public static String getUSER() {
        return USER;
    }
}
