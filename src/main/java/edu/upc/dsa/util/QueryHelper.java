package edu.upc.dsa.util;

import edu.upc.dsa.models.Inventory;
import edu.upc.dsa.models.Level;

import java.lang.reflect.Constructor;
import java.util.List;

public class QueryHelper {

    public static String createQueryINSERT(Object entity) {

        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(entity.getClass().getSimpleName().toLowerCase()).append(" ");
        sb.append("(");

        String [] fields = ObjectHelper.getFields(entity);
        sb.append("ID");
        for (String field: fields) {
            if (field == "id") {
            } else {
                sb.append(", ").append(field);
            }
        }

        sb.append(") VALUES (?");

        for (String field: fields) {
            if (field == "id") {
            } else {
                sb.append(", ?");
            }
        }

        sb.append(")");

        return sb.toString();
    }

    public static String createQuerySELECTALL(Class theClass) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(theClass.getSimpleName().toLowerCase());

        return sb.toString();
    }
    public static String createQuerySELECTbyID(Object entity) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(entity.getClass().getSimpleName().toLowerCase());
        sb.append(" WHERE ID = ?");

        return sb.toString();
    }

    public static String createQuerySELECTID(Object entity) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT ID FROM ").append(entity.getClass().getSimpleName().toLowerCase());
        sb.append(" WHERE ");

        String [] fields = ObjectHelper.getFields(entity);
        sb.append(fields[0]).append("=?");
        return sb.toString();
    }

    public static String createQuerySELECT(Class theClass, List<String> args) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(theClass.getSimpleName().toLowerCase());
        sb.append(" WHERE ");

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

            String[] fields = ObjectHelper.getFields(entity);
            int n = args.size();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < fields.length; j++) {
                    if (fields[j].equals(args.get(i))) {
                        sb.append(fields[j]).append("=?");
                        if ((i + 1) != n) {
                            sb.append(" AND ");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String createQueryUPDATE(Object entity) {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(entity.getClass().getSimpleName().toLowerCase());
        sb.append(" SET ");

        String [] fields = ObjectHelper.getFields(entity);
        int n = fields.length;
        int begin = 0;
        if (entity.getClass().equals(Level.class)) {
            begin = 1;
        }

        for (int i = begin; i < n-1; i++ ) {
            sb.append(fields[i]).append("=?, ");
        }
        sb.append(fields[n-1]).append("=?");
        sb.append(" WHERE ID = ?");

        return sb.toString();
    }

    public static String createQueryDELETE(Object entity) {
        StringBuffer sb = new StringBuffer();
        sb.append("DELETE FROM ").append(entity.getClass().getSimpleName().toLowerCase());
        sb.append(" WHERE ID=? ");

        return sb.toString();
    }
}
