// as Classes de conexão usadas
import BaseDatabaseContext;
import DatabaseContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NamedParamStatement {

    private final PreparedStatement prepStmt;
    private final List<String> fields = new ArrayList<>();

    public NamedParamStatement(final BaseDatabaseContext conn, String sql) throws SQLException {
        try {
            int pos;
            // enquanto achar o padrão [$ busca parâmetros
            while ((pos = sql.indexOf("[$")) != -1) {
                int end = sql.indexOf("]", pos);
                // adiciona o nome do parâmetro criado na lista
                fields.add(sql.substring(pos + 2, end));
                // substitui o parâmetro pelo parâmetro do PreparedStatement
                sql = sql.substring(0, pos) + "?" + sql.substring(end + 1);
            }
            prepStmt = conn.getPreparedStatement(sql);
        } catch (Exception e){
            throw new SQLException();
        }
    }

    public void close() throws SQLException {
        prepStmt.close();
    }
    public PreparedStatement getPreparedStatement() {
        return prepStmt;
    }
    public ResultSet executeQuery() throws SQLException {
        return prepStmt.executeQuery();
    }

    public int executeUpdate() throws SQLException{
        return prepStmt.executeUpdate();
    }

    public void setInt(final String name, final int value) throws SQLException {
        for(int index : getIndex(name)) {
            prepStmt.setInt(index + 1, value);
        }
    }

    public void setString(final String name, final String value) throws SQLException{
        for(int index : getIndex(name)){
            prepStmt.setString(index + 1, value);
        }
    }

    public void setArray(final String name, final Array value) throws SQLException{
        for(int index : getIndex(name)){
            prepStmt.setArray(index + 1, value);
        }
    }

    public void setBoolean(final String name, final Boolean value) throws SQLException{
        for(int index : getIndex(name)){
            prepStmt.setBoolean(index + 1, value);
        }
    }

    public void setDouble(final String name, final Double value) throws SQLException{
        for(int index : getIndex(name)){
            prepStmt.setDouble(index + 1, value);
        }
    }

    public void setFloat(final String name, final Float value) throws SQLException{
        for(int index : getIndex(name)){
            prepStmt.setFloat(index + 1, value);
        }
    }

    public void setBlob (final String name, final Blob value) throws SQLException{
        for(int index : getIndex(name)){
            prepStmt.setBlob(index + 1, value);
        }
    }

    public void setDate(final String name, final Date value) throws SQLException{
        for(int index : getIndex(name)){
            prepStmt.setDate(index + 1, value);
        }
    }

    public void setTime(final String name, final Time value) throws SQLException{
        for(int index : getIndex(name)){
            prepStmt.setTime(index + 1, value);
        }
    }

    public void setTimestamp(final String name, final Timestamp value) throws SQLException{
        for(int index : getIndex(name)){
            prepStmt.setTimestamp(index + 1, value);
        }
    }

    private List<Integer> getIndex(final String name) {
        List<Integer> indexes = IntStream.range(0, fields.size())
                .filter(i -> name.equals(fields.get(i)))
                .boxed()
                .collect(Collectors.toList());
        return indexes;
    }

    public static <T> java.sql.Array createAray(List<T> list, BaseDatabaseContext dbContext, String type) throws Exception {
        T[] data = (T[]) list.toArray(new Object[list.size()]);
        Array array = dbContext.createArray(type, data);
        return array;
    }
}
