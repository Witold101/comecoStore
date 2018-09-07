package by.vistar.comeco.store.dao;

import by.vistar.comeco.interfaces.DAO;
import by.vistar.comeco.store.db.DbConstants;
import by.vistar.comeco.store.entity.Store;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static by.vistar.comeco.store.db.DbConstants.*;

public class DaoStore extends DaoSupport implements DAO<Long, Store> {

    private Map<String, PreparedStatement> mysqlPrepareStatement;

    private static volatile DaoStore INSTANCE = null;

    private DaoStore() {
    }

    public static DaoStore getInstance() {
        DaoStore daoStore = INSTANCE;
        if (daoStore == null) {
            synchronized (DaoStore.class) {
                daoStore = INSTANCE;
                if (daoStore == null) {
                    INSTANCE = daoStore = new DaoStore();
                }
            }
        }
        return daoStore;
    }

    public void initPrepareStatement(Connection connection) throws SQLException {
        super.setConnection(connection);
        if (mysqlPrepareStatement == null) {
            mysqlPrepareStatement = new HashMap<>();
            mysqlPrepareStatement.put("addStore", connection.prepareStatement(MYSQL_ADD_STORE, Statement.RETURN_GENERATED_KEYS));
            mysqlPrepareStatement.put("getStore", connection.prepareStatement(MYSQL_GET_STORE));
            mysqlPrepareStatement.put("editStore", connection.prepareStatement(MYSQL_EDIT_STORE));
            mysqlPrepareStatement.put("dellStore", connection.prepareStatement(MYSQL_DELL_STORE));
        }
    }

    @Override
    public Store add(Store store) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("addStore");
        pst.setString(1, store.getName());
        pst.setString(2, store.getInfo());
        if (store.getByDefault()){
            typeReset(DbConstants.TABLE_NAME_STORE,"by_default");
        }
        pst.setBoolean(3, store.getByDefault());
        pst.executeUpdate();
        ResultSet rs = pst.getGeneratedKeys();
        if (rs.next()) {
            store.setId(rs.getLong(1));
        }
        rs.close();
        return store;
    }

    @Override
    public void dell(Long id) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("dellStore");
        pst.setLong(1, id);
        pst.executeUpdate();
    }

    @Override
    public Store edit(Store store) throws SQLException {
        PreparedStatement pst = mysqlPrepareStatement.get("editStore");
        pst.setString(1, store.getName());
        pst.setString(2, store.getInfo());
        if (store.getByDefault()){
            typeReset(DbConstants.TABLE_NAME_STORE,"by_default");
        }
        pst.setBoolean(3, store.getByDefault());
        pst.setLong(4,store.getId());
        if (pst.executeUpdate() > 0) {
            return store;
        } else {
            return null;
        }
    }

    @Override
    public Store get(Long id) throws SQLException {
        Store store = null;
        PreparedStatement pst = mysqlPrepareStatement.get("getStore");
        pst.setLong(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            store = new Store();
            store.setId(rs.getLong("id"));
            store.setName(rs.getString("name"));
            store.setInfo(rs.getString("info"));
            store.setByDefault(rs.getBoolean("by_default"));
        }
        rs.close();
        return store;
    }
}
