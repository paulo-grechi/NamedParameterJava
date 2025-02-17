## NamedParameterJava
Vasculhando eu acabei achando uma classe que funciona em Spring para queries com parâmetro nomeado.  
Mas como não uso Spring no dia-a-dia precisei criar uma solução para a minha necessidade.  
[Eis o link para o package de Spring para parâmetros nomeados](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/core/namedparam/NamedParameterJdbcTemplate.html)

## Exemplo de Uso
```
import NamedParamStatement;


String sql = "SELECT * FROM tabela where campo = [$campo] and teste = [$teste]";
NamedParamStatement stmt = new NamedParamStatement(classeConexao, sql);
stmt.setString("campo", "campo 321");
stmt.setString("teste", "teste 123");
ResultSet result = stmt.executeQuery();
```

# README English Version
## NamedParameterJava
Searching around I stumbled upon a class that worked for SpringBoot for named parameter queries.  
However, since I don't use it on a daily basis I had to create a solution for my current needs.  
[Here's the link for the SpringBoot package](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/core/namedparam/NamedParameterJdbcTemplate.html)

## Usage
```
import NamedParamStatement;


String sql = "SELECT * FROM test_table where test_field = [$testField] and test_field_ii = [$testFieldII]";
NamedParamStatement stmt = new NamedParamStatement(dbConnectionClass, sql);
stmt.setString("testField", "field 321");
stmt.setString("testFieldII", "test 123");
ResultSet result = stmt.executeQuery();
```
