package webservice.servlets;

/** Updates goal's name
 *
 * This servlet updates name of current goal in table goals in Database.*/

import webservice.model.Goal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static webservice.model.Executor.execQuery;
import static webservice.model.Executor.execUpdate;

public class UpdateGoal extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Connection connection = (Connection) getServletContext().getAttribute("connection");
        final String id = req.getParameter("id");

        String query = "select * from goals where goals.id="+id;
        Goal goal = new Goal();

        try {
            goal = execQuery(connection, query, result -> {
                Goal t = new Goal();
                if (result.next()){
                    t = new Goal(result.getInt("id"),result.getString("name"));
                }
                return t;
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("goal", goal);
        req.getRequestDispatcher("/WEB-INF/view/updateGoal.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        Connection connection = (Connection) getServletContext().getAttribute("connection");
        final String id = req.getParameter("id");
        final String name = req.getParameter("name");

        if (name!=null&&!name.equals("")) {
            String query = "update goals " +
                    "set name = '" + name + "'" +
                    " where id=" + id;

            try {
                int result = execUpdate(connection, query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        resp.sendRedirect( req.getContextPath() + "/goals");
    }
}
