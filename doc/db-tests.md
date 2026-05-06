Configurar SQLite para tests en Spring Boot
==========================================

Resumen
- Objetivo: utilizar SQLite como base de datos ligera para pruebas de integración JPA/Hibernate.
- Ventaja: rápida y de archivo local (evita dependencias externas).
- Recomendación: usar un archivo temporal (`target/test.sqlite`) en vez de memoria si hay varias conexiones.

1) Dependencia Maven (añadir en `pom.xml`, scope `test`)

```xml
<dependency>
  <groupId>org.xerial</groupId>
  <artifactId>sqlite-jdbc</artifactId>
  <version>3.40.1.3</version>
  <scope>test</scope>
</dependency>
```

2) Dialecto Hibernate mínimo (colocar en `src/test/java/...` dentro de un paquete de tests)

```java
package es.ediae.master.programacion.gestionusuario.testsqlite;

import java.sql.Types;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.identity.IdentityColumnSupportImpl;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StringType;

public class SQLiteDialect extends Dialect {
    public SQLiteDialect() {
        registerColumnType(Types.VARCHAR, "text");
        registerColumnType(Types.INTEGER, "integer");
        registerColumnType(Types.BIGINT, "integer");
        registerColumnType(Types.BOOLEAN, "integer");
        registerColumnType(Types.BLOB, "blob");
        registerFunction("lower", new StandardSQLFunction("lower", new StringType()));
    }

    @Override
    public IdentityColumnSupportImpl getIdentityColumnSupport() {
        return new IdentityColumnSupportImpl();
    }

    @Override
    public boolean supportsIdentityColumns() {
        return true;
    }

    @Override
    public String getIdentityColumnString() {
        return "integer";
    }

    @Override
    public String getIdentitySelectString() {
        return "select last_insert_rowid()";
    }
}
```

3) `application-test.properties` (colocar en `src/test/resources/`)

```
spring.datasource.url=jdbc:sqlite:target/test.sqlite
spring.datasource.driver-class-name=org.sqlite.JDBC
spring.datasource.username=
spring.datasource.password=
spring.jpa.database-platform=es.ediae.master.programacion.gestionusuario.testsqlite.SQLiteDialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.flyway.enabled=true
```

4) Nota sobre `@AutoConfigureTestDatabase`
- Si tus tests usan `@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)`, la configuración anterior debe estar disponible para el contexto de test (por ejemplo `application-test.properties`).

Consejos y trampas comunes
- SQLite in-memory con varias conexiones puede fallar; usar archivo en `target/` es más fiable.
- Flyway funciona bien si activas `spring.flyway.enabled=true` y tienes tus migraciones en `db/migration`.
- Si prefieres simplicidad, H2 en modo archivo o memoria es más sencillo de configurar, pero no replica exactamente el comportamiento de SQLite.
- Para ejecutar los tests con el profile de test: `mvn test -Dspring.profiles.active=test` o asegúrate de que el runner cargue `application-test.properties`.

Fin del documento.
