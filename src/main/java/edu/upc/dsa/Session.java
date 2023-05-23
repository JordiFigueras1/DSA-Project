package edu.upc.dsa;

import edu.upc.dsa.models.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface Session<E> {
    void save(Object entity);
    void close();
    Object getByID(Class theClass, int ID) throws NoSuchMethodException;
    int getID (Object entity) throws SQLException;
    void update(Object object);
    void delete(Object object);
    List<Object> findAll(Class theClass) throws NoSuchMethodException;
    List<Object> findAll(Class theClass, HashMap params);
    List<Object> query(String query, Class theClass, HashMap params);
}
