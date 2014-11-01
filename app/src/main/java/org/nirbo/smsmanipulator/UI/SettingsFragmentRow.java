package org.nirbo.smsmanipulator.UI;

public class SettingsFragmentRow {
    protected String settingName;
    protected String settingDescription;

    public SettingsFragmentRow(String settingName, String settingDescription) {
        this.settingName = settingName;
        this.settingDescription = settingDescription;
    }

    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    public String getSettingDescription() {
        return settingDescription;
    }

    public void setSettingDescription(String settingDescription) {
        this.settingDescription = settingDescription;
    }
}
