/*
 * Sonar, open source software quality management tool.
 * Copyright (C) 2008-2011 SonarSource
 * mailto:contact AT sonarsource DOT com
 *
 * Sonar is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * Sonar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Sonar; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.jpa.dialect;

import org.apache.commons.lang.StringUtils;
import org.hibernate.dialect.PostgreSQLDialect;

import java.sql.Types;

/**
 * @since 1.12
 */
public class PostgreSql implements Dialect {

  public static final String ID = "postgresql";

  public String getId() {
    return ID;
  }

  public String getActiveRecordDialectCode() {
    return "postgre";
  }

  public String getActiveRecordJdbcAdapter() {
    return "jdbc";
  }

  public Class<? extends org.hibernate.dialect.Dialect> getHibernateDialectClass() {
    return PostgreSQLWithDecimalDialect.class;
  }

  public boolean matchesJdbcURL(String jdbcConnectionURL) {
    return StringUtils.startsWithIgnoreCase(jdbcConnectionURL, "jdbc:postgresql:");
  }

  public static class PostgreSQLWithDecimalDialect extends PostgreSQLDialect {
    public PostgreSQLWithDecimalDialect() {
      super();
      registerColumnType(Types.DOUBLE, "numeric($p,$s)");
    }

    @Override
    public Class getNativeIdentifierGeneratorClass() {
      return PostgreSQLSequenceGenerator.class;
    }
  }

}
