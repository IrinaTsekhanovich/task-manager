package webservice.servlets;

/** Deletes task
 *
 * This servlet deletes one row with current task's id from table tasks in Database.*/

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static webservice.model.Executor.execUpdate;

public class DeleteTask extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        Connection connection = (Connection) getServletContext().getAttribute("connection");
        final String id = req.getParameter("id");

        String query = "delete from tasks where tasks.id="+id+";";
        try {
            int result = execUpdate(connection, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        resp.sendRedirect( req.getContextPath() + "/tasks");
    }
}
