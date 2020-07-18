package webservice.servlets;

/** Deletes goal
 *
 * This servlet deletes one row with current goal's id from table goals in Database.*/

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static webservice.model.Executor.execUpdate;

public class DeleteGoal extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        Connection connection = (Connection) getServletContext().getAttribute("connection");
        final String id = req.getParameter("id");

        String[] query = { "delete from subgoals_goals where parent="+id+" or child="+id,
                "delete from goals where id="+id};
        try {
            execUpdate(connection, query);
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect(req.getContextPath() + "/goals");
    }
}
