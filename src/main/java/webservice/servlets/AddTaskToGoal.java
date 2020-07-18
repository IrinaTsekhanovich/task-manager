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

public class AddTaskToGoal extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ArrayList<Task> tasks = new ArrayList<>();
        String goal_id = req.getParameter("id");
        Connection connection = (Connection) getServletContext().getAttribute("connection");
        String query = "select * from tasks\n" +
                "where tasks.goal_id is null";
        try {
            tasks = execQuery(connection, query, result -> {
                ArrayList<Task> tmp = new ArrayList<>();
                while (result.next()){
                    Task t = new Task(result.getInt("id"),result.getString("name"),
                            result.getString("description"),result.getBoolean("status"),
                            result.getDate("deadline"));
                    tmp.add(t);
                }
                return tmp;
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("goal_id", goal_id);
        req.setAttribute("tasks", tasks);
        req.getRequestDispatcher("/WEB-INF/view/addTaskToGoal.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        Connection connection = (Connection) getServletContext().getAttribute("connection");
        final String task = req.getParameter("task");
        final String id = req.getParameter("id");

        String query = "update tasks set goal_id = " + id + " where name = '" + task + "'";
        try {
            int result = execUpdate(connection, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        resp.sendRedirect( req.getContextPath() + "/select_goal?id="+id);
    }
}
