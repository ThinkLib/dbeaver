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

package org.jkiss.dbeaver.model.runtime;

import org.jkiss.utils.CommonUtils;

/**
 * DBRShellCommand
 */
public class DBRShellCommand
{

    private String command;
    private boolean enabled;
    private boolean showProcessPanel = true;
    private boolean waitProcessFinish;
    private boolean terminateAtDisconnect = true;
    private String workingDirectory;

    public DBRShellCommand(String command)
    {
        this.command = command;
    }

    public DBRShellCommand(DBRShellCommand command)
    {
        this.command = command.command;
        this.enabled = command.enabled;
        this.showProcessPanel = command.showProcessPanel;
        this.waitProcessFinish = command.waitProcessFinish;
        this.terminateAtDisconnect = command.terminateAtDisconnect;
        this.workingDirectory = command.workingDirectory;
    }

    public String getCommand()
    {
        return command;
    }

    public void setCommand(String command)
    {
        this.command = command;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    public boolean isShowProcessPanel()
    {
        return showProcessPanel;
    }

    public void setShowProcessPanel(boolean showProcessPanel)
    {
        this.showProcessPanel = showProcessPanel;
    }

    public boolean isWaitProcessFinish()
    {
        return waitProcessFinish;
    }

    public void setWaitProcessFinish(boolean waitProcessFinish)
    {
        this.waitProcessFinish = waitProcessFinish;
    }

    public boolean isTerminateAtDisconnect()
    {
        return terminateAtDisconnect;
    }

    public void setTerminateAtDisconnect(boolean terminateAtDisconnect)
    {
        this.terminateAtDisconnect = terminateAtDisconnect;
    }

    public String getWorkingDirectory()
    {
        return workingDirectory;
    }

    public void setWorkingDirectory(String workingDirectory)
    {
        this.workingDirectory = workingDirectory;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DBRShellCommand)) {
            return false;
        }
        DBRShellCommand source = (DBRShellCommand)obj;
        return
            CommonUtils.equalObjects(this.command, source.command) &&
            this.enabled == source.enabled &&
            this.showProcessPanel == source.showProcessPanel &&
            this.waitProcessFinish == source.waitProcessFinish &&
            this.terminateAtDisconnect == source.terminateAtDisconnect &&
            CommonUtils.equalObjects(this.workingDirectory, source.workingDirectory);
    }
}
