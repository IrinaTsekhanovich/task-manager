package webservice.servlets;

/** Adds goal
 *
 * This servlet inserts one goal into table goals in Database.*/

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static webservice.model.Executor.execUpdate;

public class AddGoal extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/view/addGoal.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        Connection connection = (Connection) getServletContext().getAttribute("connection");
        final String name = req.getParameter("name");


        if (name!=null&&!name.equals("")){
            String query = "insert into goals (name) values ('" + name + "')";
            try {
                int result = execUpdate(connection, query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        resp.sendRedirect( req.getContextPath() + "/goals");
    }
}
