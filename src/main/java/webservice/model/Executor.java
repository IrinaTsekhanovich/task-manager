package webservice.model;

import java.sql.*;

public class Executor {

    public static <T> T execQuery(Connection connection, String query, ResultHandler<T> handler) throws SQLException {
        try (Statement st = connection.createStatement()) {
            st.executeQuery(query);
            ResultSet rs = st.getResultSet();
            T result = handler.handle(rs);
            rs.close();
            st.close();
            return result;
        }
        catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public static int execUpdate(Connection connection, String query) throws SQLException {
        try (Statement st = connection.createStatement()){
            st.executeUpdate(query);
            int result = st.getUpdateCount();
            st.close();
            return result;
        }catch (SQLException e){
            throw new SQLException(e);
        }
    }

    public static void execUpdate(Connection connection, String[] query) throws SQLException {
        try {
            connection.setAutoCommit(false);
            for (String q : query) {
                Statement st = connection.createStatement();
                st.executeUpdate(q);
                st.close();
            }
            connection.close();
        } catch (SQLException e) {
            try{
                connection.rollback();
            } catch (SQLException ignore) {}
        }

        try {
            connection.setAutoCommit(true);
        } catch (SQLException ignore) {}
    }
}