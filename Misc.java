import BaseDatabaseContext;

// um arquivo qualquer com funções e métodos de utilidade diversas
public class Misc {
     // caso seja necessário converter uma Collection do tipo List para java.sql.Array
     public static <T> java.sql.Array createArray(List<T> list, BaseDatabaseContext dbContext, String type) throws Exception {
       T[] data = (T[]) list.toArray(new Object[list.size()]);
       Array array = dbContext.createArray(type, data);
       return array;
   }
  
}
