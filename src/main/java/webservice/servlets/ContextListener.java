package webservice.servlets;

/** Starts before all other servlets.*/

import webservice.model.ConnectionPool;

import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.SQLException;


@WebListener
public class ContextListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent){
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        final ServletContext servletContext =
                servletContextEvent.getServletContext();
        servletContext.setAttribute("connection", connection);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //Close resource.
        final ServletContext servletContext =
                sce.getServletContext();
        try {
            Connection connection = (Connection) servletContext.getAttribute("connection");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}