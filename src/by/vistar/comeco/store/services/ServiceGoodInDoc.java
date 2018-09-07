package by.vistar.comeco.store.services;

import by.vistar.comeco.interfaces.DaoService;
import by.vistar.comeco.interfaces.ServiceSetup;
import by.vistar.comeco.store.dao.DaoGoodInDoc;
import by.vistar.comeco.store.db.DbConstants;
import by.vistar.comeco.store.entity.GoodInDoc;

import java.sql.Connection;
import java.sql.SQLException;

public class ServiceGoodInDoc extends ServiceTablesInitDrop implements ServiceSetup<GoodInDoc>, DaoService<Long, GoodInDoc> {

    private DaoGoodInDoc daoGoodInDoc;
    Connection connection;

    public ServiceGoodInDoc(Connection connection) {
        super(connection);
        this.connection = connection;
        daoGoodInDoc = DaoGoodInDoc.getInstance();
        try {
            this.daoGoodInDoc.initPrepareStatement(connection);
        } catch (SQLException e) {
            System.out.println("Error initPrepareStatement");
            e.printStackTrace();
        }
    }

    @Override
    public GoodInDoc add(GoodInDoc goodInDoc) {
        if (goodInDoc != null) {
            modificationLength(goodInDoc);
            startTransaction();
            try {
                daoGoodInDoc.add(goodInDoc);
            } catch (SQLException e) {
                System.out.println("Error add GOOD_IN_DOC in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return goodInDoc;
    }

    @Override
    public void dell(Long id) {
        if (id != null) {
            startTransaction();
            try {
                daoGoodInDoc.dell(id);
            } catch (SQLException e) {
                System.out.println("Error dell GOOD_IN_DOC from DB");
                e.printStackTrace();
            }
            commit();
        } else {
            System.out.println("Error id == null");
        }
    }

    @Override
    public GoodInDoc edit(GoodInDoc goodInDoc) {
        if (goodInDoc != null) {
            modificationLength(goodInDoc);
            startTransaction();
            try {
                daoGoodInDoc.edit(goodInDoc);
            } catch (SQLException e) {
                System.out.println("Error edit GOOD_IN_DOC in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return goodInDoc;
    }

    @Override
    public GoodInDoc get(Long id) {
        GoodInDoc goodInDoc = null;
        if (id != null) {
            startTransaction();
            try {
                goodInDoc =daoGoodInDoc.get(id);
            } catch (SQLException e) {
                System.out.println("Error get GOOD_IN_DOC in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return goodInDoc;
    }

    @Override
    public GoodInDoc modificationLength(GoodInDoc goodInDoc) {
        if (goodInDoc != null) {
            if (goodInDoc.getNumberDoc().trim().length() > DbConstants.MAX_NUMBER_DOC) {
                goodInDoc.setNumberDoc(goodInDoc.getNumberDoc().trim().substring(0, DbConstants.MAX_NAME));
            } else {
                goodInDoc.setNumberDoc(goodInDoc.getNumberDoc().trim());
            }
            if (goodInDoc.getInfo().trim().length() > DbConstants.MAX_INFO_TEXT) {
                goodInDoc.setInfo(goodInDoc.getInfo().trim().substring(0, DbConstants.MAX_INFO_TEXT));
            } else {
                goodInDoc.setInfo(goodInDoc.getInfo().trim());
            }
        }
        return goodInDoc;
    }
}
