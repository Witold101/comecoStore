package by.vistar.comeco.store.services;

import by.vistar.comeco.store.entity.GoodDoc;
import by.vistar.comeco.store.entity.GoodInDoc;
import by.vistar.comeco.store.entity.Store;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class ServiceGoodDocTest {

    static Connection connection;

    @AfterClass
    public static void connectionClose(){
        new ServiceTablesInitDrop(connection).dropTable();
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @BeforeClass
    public static void initTest() {
        connection = ServiceMainTest.getConnection();
        new ServiceTablesInitDrop(connection).initTable();
    }

    @Test
    public void add() {
        GoodInDoc goodInDoc = new GoodInDoc();
        goodInDoc.setNumberDoc(" 4567  YY ");
        goodInDoc.setDate( new Date(118,7,23));
        goodInDoc.setPartnerId(3L);
        goodInDoc.setInfo(" Продажа по договору 8 от 54664  ");
        new ServiceGoodInDoc(connection).add(goodInDoc);

        Store store = new Store();
        store.setName("      Store один ");
        store.setInfo("      Store один INFO ");
        store.setByDefault(true);
        new ServiceStore(connection).add(store);

        GoodDoc goodDoc = new GoodDoc();
        goodDoc.setDocId(goodInDoc.getId());
        goodDoc.setGoodId(45L);
        goodDoc.setQuantity(134.234f);
        goodDoc.setStoreId(store.getId());
        // TypeDoc - это тип документа (0- это расходный, 1- приходный)
        goodDoc.setTypeDoc(1);
        new ServiceGoodDoc(connection).add(goodDoc);
        GoodDoc goodDocTest = new ServiceGoodDoc(connection).get(goodDoc.getId());
        assertEquals(goodDoc.getDocId(),goodDocTest.getDocId());
        assertEquals(goodDoc.getGoodId(),goodDocTest.getGoodId());
        assertEquals(goodDoc.getStoreId(),goodDocTest.getStoreId());
        assertEquals(goodDoc.getId(),goodDocTest.getId());
        assertEquals(goodDoc.getQuantity(),goodDocTest.getQuantity());
        assertEquals(goodDoc.getTypeDoc(),goodDocTest.getTypeDoc());

    }

    @Test
    public void dell() {
        GoodInDoc goodInDoc = new GoodInDoc();
        goodInDoc.setNumberDoc(" 4567  YY ");
        goodInDoc.setDate( new Date(118,7,23));
        goodInDoc.setPartnerId(3L);
        goodInDoc.setInfo(" Продажа по договору 8 от 54664  ");
        new ServiceGoodInDoc(connection).add(goodInDoc);

        Store store = new Store();
        store.setName("      Store один ");
        store.setInfo("      Store один INFO ");
        store.setByDefault(true);
        new ServiceStore(connection).add(store);

        GoodDoc goodDoc = new GoodDoc();
        goodDoc.setDocId(goodInDoc.getId());
        goodDoc.setGoodId(45L);
        goodDoc.setQuantity(134.234f);
        goodDoc.setStoreId(store.getId());
        // TypeDoc - это тип документа (0- это расходный, 1- приходный)
        goodDoc.setTypeDoc(1);
        new ServiceGoodDoc(connection).add(goodDoc);
        assertEquals(new ServiceGoodDoc(connection).get(goodDoc.getId()).getId(),goodDoc.getId());
        new ServiceGoodDoc(connection).dell(goodDoc.getId());
        assertNull(new ServiceGoodDoc(connection).get(goodDoc.getId()));
    }

    @Test
    public void edit() {
        GoodInDoc goodInDoc = new GoodInDoc();
        goodInDoc.setNumberDoc(" 4567  YY ");
        goodInDoc.setDate( new Date(118,7,23));
        goodInDoc.setPartnerId(3L);
        goodInDoc.setInfo(" Продажа по договору 8 от 54664  ");
        new ServiceGoodInDoc(connection).add(goodInDoc);

        Store store = new Store();
        store.setName("      Store один ");
        store.setInfo("      Store один INFO ");
        store.setByDefault(true);
        new ServiceStore(connection).add(store);

        GoodDoc goodDoc = new GoodDoc();
        goodDoc.setDocId(goodInDoc.getId());
        goodDoc.setGoodId(45L);
        goodDoc.setQuantity(134.234f);
        goodDoc.setStoreId(store.getId());
        // TypeDoc - это тип документа (0- это расходный, 1- приходный)
        goodDoc.setTypeDoc(1);
        new ServiceGoodDoc(connection).add(goodDoc);

        goodDoc.setDocId(goodInDoc.getId());
        goodDoc.setGoodId(55L);
        goodDoc.setQuantity(13.00f);
        goodDoc.setStoreId(store.getId());
        // TypeDoc - это тип документа (0- это расходный, 1- приходный)
        goodDoc.setTypeDoc(1);
        GoodDoc goodDocTest = new ServiceGoodDoc(connection).edit(goodDoc);

        assertEquals(goodDoc.getDocId(),goodDocTest.getDocId());
        assertEquals(goodDoc.getGoodId(),goodDocTest.getGoodId());
        assertEquals(goodDoc.getStoreId(),goodDocTest.getStoreId());
        assertEquals(goodDoc.getId(),goodDocTest.getId());
        assertEquals(goodDoc.getQuantity(),goodDocTest.getQuantity());
        assertEquals(goodDoc.getTypeDoc(),goodDocTest.getTypeDoc());

    }

    @Test
    public void get() {
        add();
    }
}