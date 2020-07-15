package webservice.servlets;

import webservice.model.Task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static webservice.model.Executor.execQuery;
import static webservice.model.Executor.execUpdate;

public class UpdateTask extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Connection connection = (Connection) getServletContext().getAttribute("connection");
        final String id = req.getParameter("id");
        String query = "select * from tasks where tasks.id="+id+";";
        Task task = new Task();
        try {
            task = execQuery(connection, query, result -> {
                Task t = new Task();
                if (result.next()){
                    t = new Task(result.getInt("id"),result.getString("name"),
                            result.getString("description"),result.getBoolean("status"),
                            result.getDate("deadline"));
                }
                return t;
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("task", task);
        req.getRequestDispatcher("/WEB-INF/view/createTask.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        Connection connection = (Connection) getServletContext().getAttribute("connection");
        final String id = req.getParameter("id");
        final String name = req.getParameter("name");
        final String description = req.getParameter("description");
        final String status = req.getParameter("status");
        final String date = req.getParameter("date");

        String query = "update tasks "+
                "set name = "+ name +
                ", description = " + description +
                ", status = " + status +
                ", deadline = " + date +
                " where tasks.id=" + id;

//                    (date.equals("") ?"set date ="+date: "set date=null")

        try {
            int result = execUpdate(connection, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        resp.sendRedirect( req.getContextPath() + "/tasks");
    }


}
