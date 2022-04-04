package cz.gyarab3e.rocnikovaprace3.jpa;

import org.hibernate.dialect.PostgreSQL10Dialect;

import java.sql.Types;


public class PostgreSQL95ArrayDialect extends PostgreSQL10Dialect {

    public PostgreSQL95ArrayDialect() {
        super();
        this.registerColumnType(Types.ARRAY, "array");
    }
}
