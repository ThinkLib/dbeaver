/*
 * DBeaver - Universal Database Manager
 * Copyright (C) 2010-2017 Serge Rider (serge@jkiss.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jkiss.dbeaver.ext.postgresql.model;

import org.jkiss.code.NotNull;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.ext.postgresql.PostgreUtils;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCUtils;
import org.jkiss.dbeaver.model.meta.Property;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * PostgreForeignServer
 */
public class PostgreForeignServer extends PostgreInformation {

    private long oid;
    private String name;
    private String type;
    private String version;
    private String[] options;
    private long ownerId;
    private long dataWrapperId;

    public PostgreForeignServer(PostgreDatabase database, ResultSet dbResult)
        throws SQLException
    {
        super(database);
        this.loadInfo(dbResult);
    }

    private void loadInfo(ResultSet dbResult)
        throws SQLException
    {
        this.oid = JDBCUtils.safeGetLong(dbResult, "oid");
        this.name = JDBCUtils.safeGetString(dbResult, "srvname");
        this.ownerId = JDBCUtils.safeGetLong(dbResult, "srvowner");
        this.dataWrapperId = JDBCUtils.safeGetLong(dbResult, "srvfdw");
        this.type = JDBCUtils.safeGetString(dbResult, "srvtype");
        this.version = JDBCUtils.safeGetString(dbResult, "srvversion");
        this.options = JDBCUtils.safeGetArray(dbResult, "srvoptions");
    }

    @NotNull
    @Override
    @Property(viewable = true, order = 2)
    public String getName()
    {
        return name;
    }

    @Property(viewable = true, order = 3)
    public String getType() {
        return type;
    }

    @Property(viewable = true, order = 4)
    public String getVersion() {
        return version;
    }

    @Property(viewable = true, order = 5)
    public String[] getOptions() {
        return options;
    }

    @Override
    public long getObjectId() {
        return oid;
    }

    @Property(viewable = false, order = 8)
    public PostgreRole getOwner(DBRProgressMonitor monitor) throws DBException {
        return PostgreUtils.getObjectById(monitor, getDatabase().roleCache, getDatabase(), ownerId);
    }

    @Property(viewable = true, order = 10)
    public PostgreForeignDataWrapper getForeignDataWrapper(DBRProgressMonitor monitor) throws DBException {
        return PostgreUtils.getObjectById(monitor, getDatabase().foreignDataWrapperCache, getDatabase(), dataWrapperId);
    }

}
