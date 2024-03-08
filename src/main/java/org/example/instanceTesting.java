package org.example;
import org.example.commons.DbConnection;
import org.example.commons.SingletonDBConnection;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class instanceTesting {
    @Test // Annotate the method as a test case
    public void testSingleton() throws SQLException {
        // Create two instances of the singleton class with the same db name
        System.out.println("First");
        Connection s1 = SingletonDBConnection.getInstance().getConnection();
        System.out.println("Second");
        Connection s2 = SingletonDBConnection.getInstance().getConnection();
        // Assert that they are the same object
        assertSame(s1, s2);
        System.out.println(s1+" - "+s2);
        // Assert that they have the same db name attribute
        //assertEquals(s1.db_name, s2.db_name);
    }

    @Test // Annotate the method as a test case
    public void testNormal() {
        // Create two instances of the normal class with the same db name
        System.out.println("Third");
        Connection n1 = DbConnection.getJdbcConnection();
        System.out.println("Forth");
        Connection n2 = DbConnection.getJdbcConnection();
        // Assert that they are not the same object
        assertNotSame(n1, n2);
        System.out.println(n1+" - "+n2);
        // Assert that they have the same db name attribute
        //assertEquals(n1.db_name, n2.db_name);
    }
}
