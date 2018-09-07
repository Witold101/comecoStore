package by.vistar.comeco.store.services;

import by.vistar.comeco.store.entity.Store;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class ServiceStoreTest {

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
        Store store = new Store();
        store.setName("      Store один ");
        store.setInfo("      Store один INFO ");
        store.setByDefault(true);
        new ServiceStore(connection).add(store);
        Store storeTest = new ServiceStore(connection).get(store.getId());
        assertEquals(store.getInfo(),storeTest.getInfo());
        assertEquals(store.getName(),storeTest.getName());
        assertEquals(store.getByDefault(),storeTest.getByDefault());
        assertEquals(store.getId(),storeTest.getId());
    }

    @Test
    public void dell() {
        Store store = new Store();
        store.setName("      Store один ");
        store.setInfo("      Store один INFO ");
        store.setByDefault(true);
        new ServiceStore(connection).add(store);
        new ServiceStore(connection).dell(store.getId());
        assertNull(new ServiceStore(connection).get(store.getId()));

    }

    @Test
    public void edit() {
        Store store = new Store();
        store.setName("      Store один ");
        store.setInfo("      Store один INFO ");
        store.setByDefault(true);
        new ServiceStore(connection).add(store);
        Store storeTest = new Store();
        storeTest.setName("      Store один Test ");
        storeTest.setInfo("      Store один INFO Test ");
        storeTest.setId(store.getId());
        storeTest.setByDefault(false);
        store = new ServiceStore(connection).edit(storeTest);
        assertEquals(storeTest.getInfo(),store.getInfo());
        assertEquals(storeTest.getName(),store.getName());
        assertEquals(storeTest.getByDefault(),store.getByDefault());
        assertEquals(store.getId(),storeTest.getId());
    }

    @Test
    public void get() {
        add();
    }
}