package sql;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Row {

    public Map<Object, Class> row;
    public static Map<String, Class> TYPE;

    static {
        TYPE = new HashMap<String, Class>();
        TYPE.put("INTEGER", Integer.class);
        TYPE.put("TINYINT", Byte.class);
        TYPE.put("SMALLINT", Short.class);
        TYPE.put("BIGINT", Long.class);
        TYPE.put("REAL", Float.class);
        TYPE.put("FLOAT", Double.class);
        TYPE.put("DOUBLE", Double.class);
        TYPE.put("DECIMAL", BigDecimal.class);
        TYPE.put("NUMERIC", BigDecimal.class);
        TYPE.put("BOOLEAN", Boolean.class);
        TYPE.put("CHAR", String.class);
        TYPE.put("VARCHAR", String.class);
        TYPE.put("LONGVARCHAR", String.class);
        TYPE.put("DATE", Date.class);
        TYPE.put("TIME", Time.class);
        TYPE.put("TIMESTAMP", Timestamp.class);
    }
    
    private Row() {
        this.row = new HashMap<Object, Class>();
    }
    
    public <T> void add(T data) {
        row.put(data, data.getClass());
    }

    public void add(Object data, String sqlType) {
        Class castType = Row.TYPE.get(sqlType);
        add(castType.cast(data));
    }

    public static void formTable(ResultSet rs, ArrayList<Row> table) {
        if (rs == null) {
            return;
        }

        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            int numOfCols = rsmd.getColumnCount();

            while (rs.next()) {
                Row tempRow = new Row();

                for (int i = 1; i <= numOfCols; i++) {
                    tempRow.add(rs.getObject(i), rsmd.getColumnTypeName(i));
                }

                table.add(tempRow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}