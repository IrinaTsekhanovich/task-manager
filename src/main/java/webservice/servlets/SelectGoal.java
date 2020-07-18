package webservice.servlets;

/** Gets current goal's information
 *
 * This servlet selects current goal from table goals in Database
 * and gives the information about subgoals and tasks.*/

import webservice.model.Goal;
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

public class SelectGoal extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ArrayList<Goal> subgoals = new ArrayList<Goal>();
        Goal goal = new Goal();
        ArrayList<Task> tasks = new ArrayList<Task>();

        Connection connection = (Connection) getServletContext().getAttribute("connection");
        final String goal_id = req.getParameter("id");
        String get_goal = "select * from goals where id = "+ goal_id;
        String get_subgoals = "select distinct goals.id, goals.name from goals\n"+
        "inner join subgoals_goals on goals.id=subgoals_goals.child\n"+
        "where subgoals_goals.parent = " + goal_id;
        String get_tasks = "select * from tasks\n" +
                "where tasks.goal_id=" + goal_id;
        try {
            goal = execQuery(connection, get_goal, result -> {
                Goal tmp = new Goal();
                if (result.next()){
                    tmp = new Goal(result.getInt("id"),result.getString("name"));
                }
                return tmp;
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            subgoals = execQuery(connection, get_subgoals, result -> {
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
        try {
            tasks = execQuery(connection, get_tasks, result -> {
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

        req.setAttribute("goal", goal);
        req.setAttribute("subgoals", subgoals);
        req.setAttribute("tasks", tasks);
        req.getRequestDispatcher("/WEB-INF/view/oneGoal.jsp").forward(req, resp);
    }
}