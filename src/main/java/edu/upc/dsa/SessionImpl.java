package edu.upc.dsa;


import edu.upc.dsa.models.Inventory;
import edu.upc.dsa.util.ObjectHelper;
import edu.upc.dsa.util.QueryHelper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.*;

public class SessionImpl implements Session {
    private final Connection conn;

    public SessionImpl(Connection conn) {
        this.conn = conn;
    }

    public void save(Object entity) {

        String insertQuery = QueryHelper.createQueryINSERT(entity);
        int i;
        PreparedStatement pstm = null;

        try {
            pstm = conn.prepareStatement(insertQuery);
            if (entity.getClass().equals(Inventory.class)) {
                i = 1;
            } else {
                pstm.setObject(1, 0);
                i = 2;
            }

            for (String field: ObjectHelper.getFields(entity)) {
                pstm.setObject(i++, ObjectHelper.getter(entity, field));
            }
            pstm.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {

    }

    public Object getByID(Class theClass, int ID) throws NoSuchMethodException {

        PreparedStatement pstm = null;

        try {
            Constructor[] ctors = theClass.getDeclaredConstructors();
            Constructor ctor = null;
            for (int i = 0; i < ctors.length; i++) {
                ctor = ctors[i];
                if (ctor.getGenericParameterTypes().length == 0)
                    break;
            }

            ctor.setAccessible(true);
            Object entity = (Object) ctor.newInstance();

            String selectQuery = QueryHelper.createQuerySELECTbyID(entity);
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, ID);

            ResultSet result = pstm.executeQuery();

            if (result.next()) {
                for (Field field : theClass.getDeclaredFields()) {

                    Class param = field.getType();
                    String type = param.getSimpleName();

                    char[] arr = type.toCharArray();
                    arr[0] = Character.toUpperCase(arr[0]);
                    String newType = new String(arr);
                    String method = "get" + newType;

                    Method mth = result.getClass().getMethod(method, String.class);
                    Object value = mth.invoke(result, field.getName());
                    ObjectHelper.setter(entity, field.getName(), value);
                }
                return entity;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getID(Object entity) throws SQLException {

        int id = 0;
        PreparedStatement pstm = null;

        try {
            String selectQuery = QueryHelper.createQuerySELECTID(entity);
            pstm = conn.prepareStatement(selectQuery);

            String [] fields = ObjectHelper.getFields(entity);

            pstm.setObject(1, ObjectHelper.getter(entity, fields[0]));

            ResultSet result = pstm.executeQuery();

            if (result.next()) {
                id = result.getInt("id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean update(Object object) throws SQLException {

        PreparedStatement pstm = null;
        int id = this.getID(object);
        if (id == 0) {
            return false;
        }

        String updateQuery = QueryHelper.createQueryUPDATE(object);

        try {
            pstm = conn.prepareStatement(updateQuery);
            String[] fields = ObjectHelper.getFields(object);
            int n = fields.length;
            int i = 1;

            for (String field: fields) {
                pstm.setObject(i++, ObjectHelper.getter(object, field));
            }
            pstm.setObject(n+1, id);
            pstm.executeQuery();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void delete(Object object) {

        PreparedStatement pstm = null;

        try {
            int id = this.getID(object);
            System.out.println(id);
            String selectQuery = QueryHelper.createQueryDELETE(object);
            pstm = conn.prepareStatement(selectQuery);
            pstm.setObject(1, id);

            pstm.executeQuery();

        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createInventory(Class myClass) throws NoSuchMethodException {

        PreparedStatement pstm = null;

        List<Object> objects = this.findAll(myClass);
        System.out.println(objects);
        int n = objects.size();
        System.out.println(n);
        String createQuery;

        try {
            String[] fields = ObjectHelper.getFields(objects.get(0));


            StringBuffer sb = new StringBuffer();
            sb.append("CREATE TABLE inventory");
            sb.append(" (idUser INT, ");

            for (int i = 0; i<n; i++) {
                String value = (String) ObjectHelper.getter(objects.get(i), fields[0]);
                sb.append("`" + value + "` INT DEFAULT 0, ");
            }
            sb.append("PRIMARY KEY (idUser)) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
            createQuery =  sb.toString();
            System.out.println(createQuery);

            pstm = conn.prepareStatement(createQuery);
            pstm.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Object> findAll(Class theClass) throws NoSuchMethodException {

        List<Object> objects = new ArrayList<>();
        PreparedStatement pstm = null;

        try {
            Constructor[] ctors = theClass.getDeclaredConstructors();
            Constructor ctor = null;
            for (int i = 0; i < ctors.length; i++) {
                ctor = ctors[i];
                if (ctor.getGenericParameterTypes().length == 0)
                    break;
            }

            ctor.setAccessible(true);

            String selectQuery = QueryHelper.createQuerySELECTALL(theClass);
            pstm = conn.prepareStatement(selectQuery);
            ResultSet result = pstm.executeQuery();

            while (result.next()) {
                Object entity = (Object) ctor.newInstance();
                for (Field field : theClass.getDeclaredFields()) {

                    Class param = field.getType();
                    String type = param.getSimpleName();

                    char[] arr = type.toCharArray();
                    arr[0] = Character.toUpperCase(arr[0]);
                    String newType = new String(arr);
                    String method = "get" + newType;

                    Method mth = result.getClass().getMethod(method, String.class);
                    Object value = mth.invoke(result, field.getName());
                    ObjectHelper.setter(entity, field.getName(), value);
                }
                objects.add(entity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return objects;
    }

    public List<Object> findAll(Class theClass, HashMap params) {
        return null;
    }
    public List<Object> query(String query, Class theClass, HashMap params) {
        return null;
    }
}

