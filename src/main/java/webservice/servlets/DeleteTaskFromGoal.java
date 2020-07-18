package webservice.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static webservice.model.Executor.execUpdate;

public class DeleteTaskFromGoal extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        Connection connection = (Connection) getServletContext().getAttribute("connection");
        final String task_id = req.getParameter("task_id");
        final String goal_id = req.getParameter("goal_id");

        String query = "update tasks set goal_id=null where id = " + task_id;
        try {
            int result = execUpdate(connection, query);
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect(req.getContextPath() + "/select_goal?id="+goal_id);
    }
}
