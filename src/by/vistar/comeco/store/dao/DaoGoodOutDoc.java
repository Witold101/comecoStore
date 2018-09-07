package by.vistar.comeco.store.dao;

import by.vistar.comeco.interfaces.DAO;
import by.vistar.comeco.store.entity.GoodInDoc;
import by.vistar.comeco.store.entity.GoodOutDoc;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static by.vistar.comeco.store.db.DbConstants.*;

public class DaoGoodOutDoc extends DaoSupport implements DAO<Long, GoodOutDoc> {

    private Map<String, PreparedStatement> mysqlPrepareStatement;

    private static volatile DaoGoodOutDoc INSTANCE = null;

    private DaoGoodOutDoc() {
    }

    public static DaoGoodOutDoc getInstance() {
        DaoGoodOutDoc daoGoodOutDoc = INSTANCE;
        if (daoGoodOutDoc == null) {
            synchronized (DaoGoodOutDoc.class) {
                daoGoodOutDoc = INSTANCE;
                if (daoGoodOutDoc == null) {
                    INSTANCE = daoGoodOutDoc = new DaoGoodOutDoc();
                }
            }
        }
        return daoGoodOutDoc;
    }

    public void initPrepareStatement(Connection connection) throws SQLException {
        super.setConnection(connection);
        if (mysqlPrepareStatement == null) {
            mysqlPrepareStatement = new HashMap<>();
            mysqlPrepareStatement.put("addGoodOutDoc", connection.prepareStatement(MYSQL_ADD_GOOD_OUT_DOC, Statement.RETURN_GENERATED_KEYS));
            mysqlPrepareStatement.put("getGoodOutDoc", connection.prepareStatement(MYSQL_GET_GOOD_OUT_DOC));
            mysqlPrepareStatement.put("editGoodOutDoc", connection.prepareStatement(MYSQL_EDIT_GOOD_OUT_DOC));
            mysqlPrepareStatement.put("dellGoodOutDoc", connection.prepareStatement(MYSQL_DELL_GOOD_OUT_DOC));
        }
    }

    @Override
    public GoodOutDoc add(GoodOutDoc goodOutDoc) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("addGoodOutDoc");
        pst.setString(1, goodOutDoc.getNumberDoc());
        pst.setDate(2, (Date) goodOutDoc.getDate());
        pst.setLong(3, goodOutDoc.getPartnerId());
        pst.setString(4, goodOutDoc.getInfo());
        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
            goodOutDoc.setId(rs.getLong(1));
        }
        rs.close();
        return goodOutDoc;
    }

    @Override
    public void dell(Long id) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("dellGoodOutDoc");
        pst.setLong(1, id);
        pst.executeUpdate();
    }

    @Override
    public GoodOutDoc edit(GoodOutDoc goodOutDoc) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("editGoodOutDoc");
        pst.setString(1, goodOutDoc.getNumberDoc());
        pst.setDate(2, (Date) goodOutDoc.getDate());
        pst.setLong(3, goodOutDoc.getPartnerId());
        pst.setString(4,goodOutDoc.getInfo());
        pst.setLong(5,goodOutDoc.getId());
        if (pst.executeUpdate() > 0) {
            return goodOutDoc;
        } else {
            return null;
        }
    }

    @Override
    public GoodOutDoc get(Long id) throws SQLException {
        GoodOutDoc goodOutDoc = null;
        PreparedStatement pst = mysqlPrepareStatement.get("getGoodOutDoc");
        pst.setLong(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            goodOutDoc = new GoodOutDoc();
            goodOutDoc.setId(rs.getLong("id"));
            goodOutDoc.setNumberDoc(rs.getString("number_doc"));
            goodOutDoc.setDate(rs.getDate("date"));
            goodOutDoc.setPartnerId(rs.getLong("partner_id"));
            goodOutDoc.setInfo(rs.getString("info"));
        }
        rs.close();
        return goodOutDoc;
    }
}
