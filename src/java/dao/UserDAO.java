/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;

/**
 *
 * @author hoang hung
 */
public class UserDAO {
    
    private final Connection conn;

    public UserDAO() {
        this.conn = DatabaseConnection.getConnection();
    }
}
