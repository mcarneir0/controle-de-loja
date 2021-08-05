# controle-de-loja
Mini projeto feito durante um curso de programação, incluindo Java e MySQL 

## Para compilar:
Altere as seguintes constantes de acordo com o seu banco de dados no arquivo ```src/conexao/FabricaDeConexoes.java```
```java
    private static final String URL = "jdbc:mysql://[IP_DO_SERVIDOR]:[PORTA]/[NOME_DO_BANCO_DE_DADOS]";
    private static final String USUARIO = "[SEU_USUARIO]";
    private static final String SENHA = "[SUA_SENHA]";
```

## Dependências
- Java JDK 1.8
- mysql-connector-java-8.0.25
