/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import dao.DatabaseConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hoang hung
 */
@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Logger.getLogger(ContextListener.class.getName()).log(Level.INFO, "Server started!");
        DatabaseConnection.createConnection();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Logger.getLogger(ContextListener.class.getName()).log(Level.INFO, "Server stopped!");
        DatabaseConnection.closeConnection();
    }
}
