package by.vistar.comeco.store.services;

import by.vistar.comeco.store.entity.GoodInDoc;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class ServiceGoodInDocTest {

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
        GoodInDoc goodInDocTest = new ServiceGoodInDoc(connection).get(goodInDoc.getId());
        assertEquals(goodInDoc.getInfo(),goodInDocTest.getInfo());
        assertEquals(goodInDoc.getNumberDoc(),goodInDocTest.getNumberDoc());
        assertEquals(goodInDoc.getDate(),goodInDocTest.getDate());
        assertEquals(goodInDoc.getPartnerId(),goodInDocTest.getPartnerId());
        assertEquals(goodInDoc.getId(),goodInDocTest.getId());

    }

    @Test
    public void dell() {
        GoodInDoc goodInDoc = new GoodInDoc();
        goodInDoc.setNumberDoc(" 4567  YY ");
        goodInDoc.setDate( new Date(118,7,23));
        goodInDoc.setPartnerId(3L);
        goodInDoc.setInfo(" Продажа по договору 8 от 54664  ");
        new ServiceGoodInDoc(connection).add(goodInDoc);
        new ServiceGoodInDoc(connection).dell(goodInDoc.getId());
        assertNull(new ServiceGoodInDoc(connection).get(goodInDoc.getId()));
    }

    @Test
    public void edit() {
        GoodInDoc goodInDoc = new GoodInDoc();
        goodInDoc.setNumberDoc(" 4567  YY ");
        goodInDoc.setDate( new Date(118,7,23));
        goodInDoc.setPartnerId(3L);
        goodInDoc.setInfo(" Продажа по договору 8 от 54664  ");
        new ServiceGoodInDoc(connection).add(goodInDoc);
        goodInDoc.setNumberDoc(" 4567  YY Test ");
        goodInDoc.setDate( new Date(118,9,23));
        goodInDoc.setPartnerId(4L);
        goodInDoc.setInfo(" Продажа по договору 8 от 54664 Test  ");
        new ServiceGoodInDoc(connection).edit(goodInDoc);
        GoodInDoc goodInDocTest = new ServiceGoodInDoc(connection).get(goodInDoc.getId());
        assertEquals(goodInDoc.getId(),goodInDocTest.getId());
        assertEquals(goodInDoc.getPartnerId(),goodInDocTest.getPartnerId());
        assertEquals(goodInDoc.getDate(),goodInDocTest.getDate());
        assertEquals(goodInDoc.getNumberDoc(),goodInDocTest.getNumberDoc());
        assertEquals(goodInDoc.getInfo(),goodInDocTest.getInfo());
    }

    @Test
    public void get() {
        add();
    }
}