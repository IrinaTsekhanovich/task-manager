package webservice.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static webservice.model.Executor.execUpdate;

public class AddTask extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/view/createTask.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        Connection connection = (Connection) getServletContext().getAttribute("connection");
        final String name = req.getParameter("name");
        final String description = req.getParameter("description");
        final String status = req.getParameter("status");
        final String date = req.getParameter("date");

        String query;
        if (name!=null&&!name.equals("")){
            if (!date.equals("")) query = "insert into tasks (name, description, status, deadline) values ('"+
                    name+"','"+description+"','"+status+"','"+date+"')";
            else query = "insert into tasks (name, description, status) values ('"+
                    name+"','"+description+"','"+status+"')";
            try {
                int result = execUpdate(connection, query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        resp.sendRedirect( req.getContextPath() + "/tasks");
    }
}
