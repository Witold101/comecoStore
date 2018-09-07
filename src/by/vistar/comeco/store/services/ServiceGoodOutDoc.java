package by.vistar.comeco.store.services;

import by.vistar.comeco.interfaces.DaoService;
import by.vistar.comeco.interfaces.ServiceSetup;
import by.vistar.comeco.store.dao.DaoGoodInDoc;
import by.vistar.comeco.store.dao.DaoGoodOutDoc;
import by.vistar.comeco.store.db.DbConstants;
import by.vistar.comeco.store.entity.GoodInDoc;
import by.vistar.comeco.store.entity.GoodOutDoc;

import java.sql.Connection;
import java.sql.SQLException;

public class ServiceGoodOutDoc extends ServiceTablesInitDrop implements ServiceSetup<GoodOutDoc>, DaoService<Long, GoodOutDoc> {

    private DaoGoodOutDoc daoGoodOutDoc;
    Connection connection;

    public ServiceGoodOutDoc(Connection connection) {
        super(connection);
        this.connection = connection;
        daoGoodOutDoc = DaoGoodOutDoc.getInstance();
        try {
            this.daoGoodOutDoc.initPrepareStatement(connection);
        } catch (SQLException e) {
            System.out.println("Error initPrepareStatement");
            e.printStackTrace();
        }
    }

    @Override
    public GoodOutDoc add(GoodOutDoc goodOutDoc) {
        if (goodOutDoc != null) {
            modificationLength(goodOutDoc);
            startTransaction();
            try {
                daoGoodOutDoc.add(goodOutDoc);
            } catch (SQLException e) {
                System.out.println("Error add GOOD_OUT_DOC in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return goodOutDoc;
    }

    @Override
    public void dell(Long id) {
        if (id != null) {
            startTransaction();
            try {
                daoGoodOutDoc.dell(id);
            } catch (SQLException e) {
                System.out.println("Error dell GOOD_OUT_DOC from DB");
                e.printStackTrace();
            }
            commit();
        } else {
            System.out.println("Error id == null");
        }
    }

    @Override
    public GoodOutDoc edit(GoodOutDoc goodOutDoc) {
        if (goodOutDoc != null) {
            modificationLength(goodOutDoc);
            startTransaction();
            try {
                daoGoodOutDoc.edit(goodOutDoc);
            } catch (SQLException e) {
                System.out.println("Error edit GOOD_OUT_DOC in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return goodOutDoc;
    }

    @Override
    public GoodOutDoc get(Long id) {
        GoodOutDoc goodOutDoc = null;
        if (id != null) {
            startTransaction();
            try {
                goodOutDoc =daoGoodOutDoc.get(id);
            } catch (SQLException e) {
                System.out.println("Error get GOOD_OUT_DOC in DB.");
                e.printStackTrace();
            }
            commit();
        }
        return goodOutDoc;
    }

    @Override
    public GoodOutDoc modificationLength(GoodOutDoc goodOutDoc) {
        if (goodOutDoc != null) {
            if (goodOutDoc.getNumberDoc().trim().length() > DbConstants.MAX_NUMBER_DOC) {
                goodOutDoc.setNumberDoc(goodOutDoc.getNumberDoc().trim().substring(0, DbConstants.MAX_NAME));
            } else {
                goodOutDoc.setNumberDoc(goodOutDoc.getNumberDoc().trim());
            }
            if (goodOutDoc.getInfo().trim().length() > DbConstants.MAX_INFO_TEXT) {
                goodOutDoc.setInfo(goodOutDoc.getInfo().trim().substring(0, DbConstants.MAX_INFO_TEXT));
            } else {
                goodOutDoc.setInfo(goodOutDoc.getInfo().trim());
            }
        }
        return goodOutDoc;
    }
}
