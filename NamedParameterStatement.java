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
            String subSql = sql;
            while ((pos = subSql.indexOf(":")) != -1) {
                // quando for encontrado um padrão de casting do SQL, quebra a string a partir dali e continua
                if (pos == subSql.indexOf("::")){
                    subSql = subSql.substring(pos + 2);
                    continue;
                }
                int end = subSql.indexOf(" ", pos);
                // caso não tenha um espaço entre o parâmetro nomeado e um possível casting, para não acabar sumindo com o casting
                //hint recomendo que dê-se um espaço após o parâmetro caso algum outro carácter venha depois, ou fazer a mesma tratativa para o carácter em específico
                end = end < subSql.indexOf("::", pos) ? end : subSql.indexOf("::", pos) > -1 ?  subSql.indexOf("::", pos) : end;
                if (end == -1) {
                    end = subSql.length();
                }
                fields.add(subSql.substring(pos + 1, end));
                if (subSql.length() == sql.length()){
                    sql = subSql.substring(0, pos) + "?" + subSql.substring(end);
                } else {
                    sql = sql.substring(0, sql.indexOf(subSql));
                    sql = sql + subSql.substring(0, pos) + "?" + subSql.substring(end);
                }
                subSql = subSql.substring(0, pos) + "?" + subSql.substring(end);
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
            prepStmt.setInt(index, value);
        }
    }

    public void setString(final String name, final String value) throws SQLException{
        for(int index : getIndex(name)){
            prepStmt.setString(index, value);
        }
    }

    public void setArray(final String name, final Array value) throws SQLException{
        for(int index : getIndex(name)){
            prepStmt.setArray(index, value);
        }
    }

    public void setBoolean(final String name, final Boolean value) throws SQLException{
        for(int index : getIndex(name)){
            prepStmt.setBoolean(index, value);
        }
    }

    public void setDouble(final String name, final Double value) throws SQLException{
        for(int index : getIndex(name)){
            prepStmt.setDouble(index, value);
        }
    }

    public void setFloat(final String name, final Float value) throws SQLException{
        for(int index : getIndex(name)){
            prepStmt.setFloat(index, value);
        }
    }

    public void setBlob (final String name, final Blob value) throws SQLException{
        for(int index : getIndex(name)){
            prepStmt.setBlob(index, value);
        }
    }

    public void setDate(final String name, final Date value) throws SQLException{
        for(int index : getIndex(name)){
            prepStmt.setDate(index, value);
        }
    }

    public void setTime(final String name, final Time value) throws SQLException{
        for(int index : getIndex(name)){
            prepStmt.setTime(index, value);
        }
    }

    public void setTimestamp(final String name, final Timestamp value) throws SQLException{
        for(int index : getIndex(name)){
            prepStmt.setTimestamp(index, value);
        }
    }

    private List<Integer> getIndex(final String name) {
        List<Integer> indexes = new ArrayList<>();
        int index = 1;
        for (String id: fields) {
            if(id.equals(name))
                indexes.add(index);
            index++;
        }
        return indexes;
    }
}
