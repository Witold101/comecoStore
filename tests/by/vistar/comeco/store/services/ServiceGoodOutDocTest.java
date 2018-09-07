package by.vistar.comeco.store.services;

import by.vistar.comeco.store.entity.GoodOutDoc;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class ServiceGoodOutDocTest {

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
        GoodOutDoc goodOutDoc = new GoodOutDoc();
        goodOutDoc.setNumberDoc(" 4567  YY ");
        goodOutDoc.setDate( new Date(118,7,23));
        goodOutDoc.setPartnerId(3L);
        goodOutDoc.setInfo(" Продажа по договору 8 от 54664  ");
        new ServiceGoodOutDoc(connection).add(goodOutDoc);
        GoodOutDoc goodOutDocTest = new ServiceGoodOutDoc(connection).get(goodOutDoc.getId());
        assertEquals(goodOutDoc.getInfo(),goodOutDocTest.getInfo());
        assertEquals(goodOutDoc.getNumberDoc(),goodOutDocTest.getNumberDoc());
        assertEquals(goodOutDoc.getDate(),goodOutDocTest.getDate());
        assertEquals(goodOutDoc.getPartnerId(),goodOutDocTest.getPartnerId());
        assertEquals(goodOutDoc.getId(),goodOutDocTest.getId());

    }

    @Test
    public void dell() {
        GoodOutDoc goodOutDoc = new GoodOutDoc();
        goodOutDoc.setNumberDoc(" 4567  YY ");
        goodOutDoc.setDate( new Date(118,7,23));
        goodOutDoc.setPartnerId(3L);
        goodOutDoc.setInfo(" Продажа по договору 8 от 54664  ");
        new ServiceGoodOutDoc(connection).add(goodOutDoc);
        new ServiceGoodOutDoc(connection).dell(goodOutDoc.getId());
        assertNull(new ServiceGoodOutDoc(connection).get(goodOutDoc.getId()));
    }

    @Test
    public void edit() {
        GoodOutDoc goodOutDoc = new GoodOutDoc();
        goodOutDoc.setNumberDoc(" 4567  YY ");
        goodOutDoc.setDate( new Date(118,7,23));
        goodOutDoc.setPartnerId(3L);
        goodOutDoc.setInfo(" Продажа по договору 8 от 54664  ");
        new ServiceGoodOutDoc(connection).add(goodOutDoc);
        goodOutDoc.setNumberDoc(" 4567  YY Test ");
        goodOutDoc.setDate( new Date(118,9,23));
        goodOutDoc.setPartnerId(4L);
        goodOutDoc.setInfo(" Продажа по договору 8 от 54664 Test  ");
        new ServiceGoodOutDoc(connection).edit(goodOutDoc);
        GoodOutDoc goodOutDocTest = new ServiceGoodOutDoc(connection).get(goodOutDoc.getId());
        assertEquals(goodOutDoc.getId(),goodOutDocTest.getId());
        assertEquals(goodOutDoc.getPartnerId(),goodOutDocTest.getPartnerId());
        assertEquals(goodOutDoc.getDate(),goodOutDocTest.getDate());
        assertEquals(goodOutDoc.getNumberDoc(),goodOutDocTest.getNumberDoc());
        assertEquals(goodOutDoc.getInfo(),goodOutDocTest.getInfo());
    }

    @Test
    public void get() {
        add();
    }
}