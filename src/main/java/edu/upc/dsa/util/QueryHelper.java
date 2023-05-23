package edu.upc.dsa.util;

import edu.upc.dsa.models.User;
import edu.upc.dsa.models.VOCredentials;

public class QueryHelper {

    public static String createQueryINSERT(Object entity) {

        StringBuffer sb = new StringBuffer("INSERT INTO ");
        sb.append(entity.getClass().getSimpleName()).append(" ");
        sb.append("(");

        String [] fields = ObjectHelper.getFields(entity);

        sb.append("ID");
        for (String field: fields) {
            sb.append(", ").append(field);
        }

        sb.append(") VALUES (?");

        for (String field: fields) {
            sb.append(", ?");
        }

        sb.append(")");

        return sb.toString();
    }

    public static String createQuerySELECTALL(Class theClass) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(theClass.getSimpleName());

        return sb.toString();
    }
    public static String createQuerySELECTbyID(Object entity) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM ").append(entity.getClass().getSimpleName());
        sb.append(" WHERE ID = ?");

        return sb.toString();
    }

    public static String createQuerySELECTID(Object entity) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT ID FROM ").append(entity.getClass().getSimpleName());
        sb.append(" WHERE ");

        String [] fields = ObjectHelper.getFields(entity);
        sb.append(fields[0]).append("=?");
        return sb.toString();
    }

    public static String createQueryUPDATE(Object entity) {
        StringBuffer sb = new StringBuffer();
        sb.append("UPDATE ").append(entity.getClass().getSimpleName());
        sb.append(" SET ");

        String [] fields = ObjectHelper.getFields(entity);
        int n = fields.length;

        for (String field : fields) {
            if (field.equals(fields[n-1])) {
                sb.append(field).append("=?");
            } else {
                sb.append(field).append("=?, ");
            }
        }
        sb.append(" WHERE ID = ?");

        return sb.toString();
    }
}
