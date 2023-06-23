package edu.upc.dsa;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface Session<E> {
    void save(Object entity);
    void close();
    Object getByID(Class theClass, int ID) throws NoSuchMethodException;
    int getID (Object entity) throws SQLException;
    boolean update(Object object) throws SQLException;
    void delete(Object object);
    void createInventory(Class myClass) throws NoSuchMethodException;
    List<Object> findAll(Class theClass) throws NoSuchMethodException;

    List<Object> findAll(Object object, List<String> args);
}
