package by.vistar.comeco.store.services;

import by.vistar.comeco.interfaces.DaoService;
import by.vistar.comeco.interfaces.ServiceSetup;
import by.vistar.comeco.store.dao.DaoGoodDoc;
import by.vistar.comeco.store.entity.GoodDoc;

import java.sql.Connection;
import java.sql.SQLException;

public class ServiceGoodDoc extends ServiceTablesInitDrop implements ServiceSetup<GoodDoc>, DaoService<Long,GoodDoc> {

    private DaoGoodDoc daoGoodDoc;
    Connection connection;

    public ServiceGoodDoc(Connection connection) {
        super(connection);
        this.connection = connection;
        daoGoodDoc = DaoGoodDoc.getInstance();
        try {
            this.daoGoodDoc.initPrepareStatement(connection);
        } catch (SQLException e) {
            System.out.println("Error initPrepareStatement");
            e.printStackTrace();
        }
    }

    @Override
    public GoodDoc add(GoodDoc goodDoc) {
        if (goodDoc != null) {
            startTransaction();
            try {
                daoGoodDoc.add(goodDoc);
            } catch (SQLException e) {
                System.out.println("Error add GOOD_DOC in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return goodDoc;
    }

    @Override
    public void dell(Long id) {
        if (id != null) {
            startTransaction();
            try {
                daoGoodDoc.dell(id);
            } catch (SQLException e) {
                System.out.println("Error dell GOOD_DOC from DB");
                e.printStackTrace();
            }
            commit();
        } else {
            System.out.println("Error id == null");
        }
    }

    @Override
    public GoodDoc edit(GoodDoc goodDoc) {
        if (goodDoc != null) {
            startTransaction();
            try {
                daoGoodDoc.edit(goodDoc);
            } catch (SQLException e) {
                System.out.println("Error edit GOOD_DOC in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return goodDoc;
    }

    @Override
    public GoodDoc get(Long id) {
        GoodDoc goodDoc = null;
        if (id != null) {
            startTransaction();
            try {
                goodDoc = daoGoodDoc.get(id);
            } catch (SQLException e) {
                System.out.println("Error get GOOD_DOC in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return goodDoc;
    }

    @Override
    public GoodDoc modificationLength(GoodDoc goodDoc) {
        return goodDoc;
    }
}
