package by.vistar.comeco.store.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DaoSupport {

    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * Метод для установки ячеек в колонке в таблице в 0 кроме одной, которая будет 1
     */
    public void typeReset(String tableName, String collName) throws SQLException {
        String query = "SELECT * FROM " + tableName + " WHERE "+collName+"=1 limit 1;";
        PreparedStatement pst = connection.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            Long typeId = rs.getLong("id");
            String queryUpd = "UPDATE " + tableName + " SET "+collName+"=0 WHERE id=" + typeId + ";";
            pst = connection.prepareStatement(queryUpd);
            pst.execute();
        }
        rs.close();
    }
}
