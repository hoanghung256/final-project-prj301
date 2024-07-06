/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.OrderDetail;
import model.Product;

/**
 *
 * @author ASUS
 */
public class TestDB {
    public static void main(String[] args) {
        OrderDAO dao = new OrderDAO();
        List<OrderDetail> list = dao.getOrderDetailsNotYetDelivered();
        
        System.out.println(list);
    }
}
