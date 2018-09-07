package by.vistar.comeco.store.dao;

import by.vistar.comeco.interfaces.DAO;
import by.vistar.comeco.store.db.DbConstants;
import by.vistar.comeco.store.entity.GoodInDoc;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static by.vistar.comeco.store.db.DbConstants.*;

public class DaoGoodInDoc extends DaoSupport implements DAO<Long, GoodInDoc> {

    private Map<String, PreparedStatement> mysqlPrepareStatement;

    private static volatile DaoGoodInDoc INSTANCE = null;

    private DaoGoodInDoc() {
    }

    public static DaoGoodInDoc getInstance() {
        DaoGoodInDoc daoGoodInDoc = INSTANCE;
        if (daoGoodInDoc == null) {
            synchronized (DaoGoodInDoc.class) {
                daoGoodInDoc = INSTANCE;
                if (daoGoodInDoc == null) {
                    INSTANCE = daoGoodInDoc = new DaoGoodInDoc();
                }
            }
        }
        return daoGoodInDoc;
    }

    public void initPrepareStatement(Connection connection) throws SQLException {
        super.setConnection(connection);
        if (mysqlPrepareStatement == null) {
            mysqlPrepareStatement = new HashMap<>();
            mysqlPrepareStatement.put("addGoodInDoc", connection.prepareStatement(MYSQL_ADD_GOOD_IN_DOC, Statement.RETURN_GENERATED_KEYS));
            mysqlPrepareStatement.put("getGoodInDoc", connection.prepareStatement(MYSQL_GET_GOOD_IN_DOC));
            mysqlPrepareStatement.put("editGoodInDoc", connection.prepareStatement(MYSQL_EDIT_GOOD_IN_DOC));
            mysqlPrepareStatement.put("dellGoodInDoc", connection.prepareStatement(MYSQL_DELL_GOOD_IN_DOC));
        }
    }

    @Override
    public GoodInDoc add(GoodInDoc goodInDoc) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("addGoodInDoc");
        pst.setString(1, goodInDoc.getNumberDoc());
        pst.setDate(2, (Date) goodInDoc.getDate());
        pst.setLong(3, goodInDoc.getPartnerId());
        pst.setString(4, goodInDoc.getInfo());
        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
            goodInDoc.setId(rs.getLong(1));
        }
        rs.close();
        return goodInDoc;
    }

    @Override
    public void dell(Long id) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("dellGoodInDoc");
        pst.setLong(1, id);
        pst.executeUpdate();
    }

    @Override
    public GoodInDoc edit(GoodInDoc goodInDoc) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("editGoodInDoc");
        pst.setString(1, goodInDoc.getNumberDoc());
        pst.setDate(2, (Date) goodInDoc.getDate());
        pst.setLong(3, goodInDoc.getPartnerId());
        pst.setString(4,goodInDoc.getInfo());
        pst.setLong(5,goodInDoc.getId());
        if (pst.executeUpdate() > 0) {
            return goodInDoc;
        } else {
            return null;
        }
    }

    @Override
    public GoodInDoc get(Long id) throws SQLException {
        GoodInDoc goodInDoc = null;
        PreparedStatement pst = mysqlPrepareStatement.get("getGoodInDoc");
        pst.setLong(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            goodInDoc = new GoodInDoc();
            goodInDoc.setId(rs.getLong("id"));
            goodInDoc.setNumberDoc(rs.getString("number_doc"));
            goodInDoc.setDate(rs.getDate("date"));
            goodInDoc.setPartnerId(rs.getLong("partner_id"));
            goodInDoc.setInfo(rs.getString("info"));
        }
        rs.close();
        return goodInDoc;
    }
}
