package by.vistar.comeco.store.dao;

import by.vistar.comeco.store.db.DbConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoFirstInit {
    public static boolean firstInit = false;

    public static void initTables(Connection connection) throws SQLException {
        if (!firstInit) {
            PreparedStatement pst = connection.prepareStatement(DbConstants.MYSQL_NEW_TABLE_STORE);
            pst.execute();
            pst = connection.prepareStatement(DbConstants.MYSQL_NEW_TABLE_GOOD_IN_DOC);
            pst.execute();
            pst = connection.prepareStatement(DbConstants.MYSQL_NEW_TABLE_GOOD_OUT_DOC);
            pst.execute();
            pst = connection.prepareStatement(DbConstants.MYSQL_NEW_TABLE_GOOD_DOC);
            pst.execute();

            firstInit = true;
        }
    }

    public static void dropTables(Connection connection) throws SQLException {
        String sql1 = "DROP TABLE `" + DbConstants.TABLE_NAME_STORE + "`;";
        String sql2 = "DROP TABLE `" + DbConstants.TABLE_NAME_GOOD_OUT_DOC + "`;";
        String sql3 = "DROP TABLE `" + DbConstants.TABLE_NAME_GOOD_IN_DOC + "`;";
        String sql4 = "DROP TABLE `" + DbConstants.TABLE_NAME_GOOD_DOC + "`;";

        PreparedStatement pst = connection.prepareStatement(sql4);
        pst.execute();
        pst = connection.prepareStatement(sql3);
        pst.execute();
        pst = connection.prepareStatement(sql2);
        pst.execute();
        pst = connection.prepareStatement(sql1);
        pst.execute();
        firstInit = false;
    }
}
