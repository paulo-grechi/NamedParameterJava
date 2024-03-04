# NamedParameterJava
Vasculhando eu acabei achando uma classe que funciona em Spring para queries com parâmetro nomeado.  
Mas como não uso Spring no dia-a-dia precisei criar uma solução para a minha necessidade.  
[Eis o link para o package de Spring para parâmetros nomeados](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/core/namedparam/NamedParameterJdbcTemplate.html)

# Exemplo de Uso
```
import NamedParamStatement;


String sql = "SELECT * FROM tabela where campo = :campo and teste = :teste";
NamedParamStatement stmt = new NamedParamStatement(dbContext, sql);
stmt.setString("campo", "campo 321");
stmt.setString("teste", "teste 123");
ResultSet result = stmt.executeQuery();
```
