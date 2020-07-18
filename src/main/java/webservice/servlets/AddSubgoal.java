package webservice.servlets;

/** Adds subgoal
 *
 * This servlet inserts one new goal into table goals in Database
 * and insert one row parent-child into table subgoals_goals
 * where new goal is a child (subgoal) and current goal is a parent.*/

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static webservice.model.Executor.execUpdate;

public class AddSubgoal extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        final String id = req.getParameter("id");
        req.setAttribute("id", id);
        req.getRequestDispatcher("/WEB-INF/view/addSubgoal.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        Connection connection = (Connection) getServletContext().getAttribute("connection");
        final String name = req.getParameter("name");
        final String id = req.getParameter("id");

        if (name!=null&&!name.equals("")){
            String[] query = {"insert into goals (name) values ('" + name + "')",
            "insert into subgoals_goals (parent, child) values ("+id+ ", (select max(id) from goals))"};
            try {
                execUpdate(connection, query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        resp.sendRedirect( req.getContextPath() + "/select_goal?id="+id);
    }
}
