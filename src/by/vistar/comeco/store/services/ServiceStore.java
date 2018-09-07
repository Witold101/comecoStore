package by.vistar.comeco.store.services;

import by.vistar.comeco.interfaces.DaoService;
import by.vistar.comeco.interfaces.ServiceSetup;
import by.vistar.comeco.store.dao.DaoStore;
import by.vistar.comeco.store.db.DbConstants;
import by.vistar.comeco.store.entity.Store;

import java.sql.Connection;
import java.sql.SQLException;

public class ServiceStore extends ServiceTablesInitDrop implements ServiceSetup<Store>, DaoService<Long,Store> {


    private DaoStore daoStore;
    Connection connection;

    public ServiceStore(Connection connection) {
        super(connection);
        this.connection = connection;
        daoStore = DaoStore.getInstance();
        try {
            this.daoStore.initPrepareStatement(connection);
        } catch (SQLException e) {
            System.out.println("Error initPrepareStatement");
            e.printStackTrace();
        }
    }

    @Override
    public Store add(Store store) {
        if (store != null) {
            modificationLength(store);
            startTransaction();
            try {
                daoStore.add(store);
            } catch (SQLException e) {
                System.out.println("Error add ACCOUNT in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return store;
    }

    @Override
    public void dell(Long id) {
        if (id != null) {
            startTransaction();
            try {
                daoStore.dell(id);
            } catch (SQLException e) {
                System.out.println("Error dell STORE from DB");
                e.printStackTrace();
            }
            commit();
        } else {
            System.out.println("Error id == null");
        }
    }

    @Override
    public Store edit(Store store) {
        if (store != null) {
            modificationLength(store);
            startTransaction();
            try {
                daoStore.edit(store);
            } catch (SQLException e) {
                System.out.println("Error edit STORE in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return store;
    }

    @Override
    public Store get(Long id) {
        Store store = null;
        if (id != null) {
            startTransaction();
            try {
                store =daoStore.get(id);
            } catch (SQLException e) {
                System.out.println("Error get STORE in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return store;
    }

    @Override
    public Store modificationLength(Store store) {
        if (store != null) {
            if (store.getName().trim().length() > DbConstants.MAX_NAME) {
                store.setName(store.getName().trim().substring(0, DbConstants.MAX_NAME));
            } else {
                store.setName(store.getName().trim());
            }
            if (store.getInfo().trim().length() > DbConstants.MAX_INFO_TEXT) {
                store.setInfo(store.getInfo().trim().substring(0, DbConstants.MAX_INFO_TEXT));
            } else {
                store.setInfo(store.getInfo().trim());
            }
        }
        return store;
    }
}
