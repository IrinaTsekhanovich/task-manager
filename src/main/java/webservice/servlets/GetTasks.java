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

public class GetTasks extends HttpServlet{
    private ArrayList<Task> tasks;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Connection connection = (Connection) getServletContext().getAttribute("connection");
        String query = "select * from tasks\n" +
                "order by id;";
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

        req.setAttribute("tasks", tasks);
        req.getRequestDispatcher("/WEB-INF/view/tasks.jsp").forward(req, resp);
    }
}
