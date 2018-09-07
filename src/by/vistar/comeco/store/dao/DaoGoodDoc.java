package by.vistar.comeco.store.dao;

import by.vistar.comeco.interfaces.DAO;
import by.vistar.comeco.store.db.DbConstants;
import by.vistar.comeco.store.entity.GoodDoc;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static by.vistar.comeco.store.db.DbConstants.*;

public class DaoGoodDoc extends DaoSupport implements DAO<Long, GoodDoc> {

    private Map<String, PreparedStatement> mysqlPrepareStatement;

    private static volatile DaoGoodDoc INSTANCE = null;

    private DaoGoodDoc() {
    }

    public static DaoGoodDoc getInstance() {
        DaoGoodDoc daoGoodDoc = INSTANCE;
        if (daoGoodDoc == null) {
            synchronized (DaoGoodDoc.class) {
                daoGoodDoc = INSTANCE;
                if (daoGoodDoc == null) {
                    INSTANCE = daoGoodDoc = new DaoGoodDoc();
                }
            }
        }
        return daoGoodDoc;
    }

    public void initPrepareStatement(Connection connection) throws SQLException {
        super.setConnection(connection);
        if (mysqlPrepareStatement == null) {
            mysqlPrepareStatement = new HashMap<>();
            mysqlPrepareStatement.put("addGoodDoc", connection.prepareStatement(MYSQL_ADD_GOOD_DOC, Statement.RETURN_GENERATED_KEYS));
            mysqlPrepareStatement.put("getGoodDoc", connection.prepareStatement(MYSQL_GET_GOOD_DOC));
            mysqlPrepareStatement.put("editGoodDoc", connection.prepareStatement(MYSQL_EDIT_GOOD_DOC));
            mysqlPrepareStatement.put("dellGoodDoc", connection.prepareStatement(MYSQL_DELL_GOOD_DOC));
        }
    }

    @Override
    public GoodDoc add(GoodDoc goodDoc) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("addGoodDoc");
        pst.setLong(1, goodDoc.getDocId());
        pst.setLong(2, goodDoc.getGoodId());
        pst.setFloat(3, goodDoc.getQuantity());
        pst.setLong(4, goodDoc.getStoreId());
        pst.setInt(5, goodDoc.getTypeDoc());
        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
            goodDoc.setId(rs.getLong(1));
        }
        rs.close();
        return goodDoc;
    }

    @Override
    public void dell(Long id) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("dellGoodDoc");
        pst.setLong(1, id);
        pst.executeUpdate();
    }

    @Override
    public GoodDoc edit(GoodDoc goodDoc) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("editGoodDoc");
        pst.setLong(1, goodDoc.getDocId());
        pst.setLong(2, goodDoc.getGoodId());
        pst.setFloat(3, goodDoc.getQuantity());
        pst.setLong(4, goodDoc.getStoreId());
        pst.setInt(5, goodDoc.getTypeDoc());
        pst.setLong(6, goodDoc.getId());
        if (pst.executeUpdate() > 0) {
            return goodDoc;
        } else {
            return null;
        }
    }

    @Override
    public GoodDoc get(Long id) throws SQLException {
        GoodDoc goodDoc = null;
        PreparedStatement pst = mysqlPrepareStatement.get("getGoodDoc");
        pst.setLong(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            goodDoc = new GoodDoc();
            goodDoc.setDocId(rs.getLong("doc_id"));
            goodDoc.setGoodId(rs.getLong("good_id"));
            goodDoc.setQuantity(rs.getFloat("quantity"));
            goodDoc.setStoreId(rs.getLong("store_id"));
            goodDoc.setTypeDoc(rs.getInt("type_doc"));
            goodDoc.setId(rs.getLong("id"));
        }
        rs.close();
        return goodDoc;
    }
}
