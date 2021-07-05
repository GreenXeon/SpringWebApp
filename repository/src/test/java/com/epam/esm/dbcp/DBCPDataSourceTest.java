package com.epam.esm.dbcp;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DBCPDataSourceTest {

    @Test
    void getConnection() {
        try {
            Connection testConnection = DBCPDataSource.getConnection();
            assertNotEquals(testConnection, null);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}