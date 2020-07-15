package webservice.servlets;

import webservice.model.Goal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static webservice.model.Executor.execQuery;

public class GetGoals extends HttpServlet {
    private ArrayList<Goal> goals;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Connection connection = (Connection) getServletContext().getAttribute("connection");
        String query = "select distinct goals.id, goals.name from goals\n" +
                "where not exists (select * from subgoals_goals\n" +
                "where subgoals_goals.child=goals.id)\n" +
                "order by goals.id;\n";
        try {
            goals = execQuery(connection, query, result -> {
                ArrayList<Goal> tmp = new ArrayList<>();
                while (result.next()){
                    Goal t = new Goal(result.getInt("id"),result.getString("name"));
                    tmp.add(t);
                }
                return tmp;
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("goals", goals);
        req.getRequestDispatcher("/WEB-INF/view/goals.jsp").forward(req, resp);
    }
}
