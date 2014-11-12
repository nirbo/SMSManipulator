package org.nirbo.smsmanipulator.model;

import com.j256.ormlite.field.DatabaseField;

public class Target extends ModelBase {
    @DatabaseField
    String contactName;

    @DatabaseField
    String contactNumber;

    @DatabaseField
    Boolean forwardActive;

    public Target() {

    }

    public Target(String contactName, String contactNumber, Boolean forwardActive) {
        this.contactName = contactName;
        this.contactNumber = contactNumber;
        this.forwardActive = forwardActive;
    }

    @Override
    public String toString() {
        return "Target{" +
                "contactName='" + contactName + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", forwardActive=" + forwardActive +
                '}';
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Boolean getForwardActive() {
        return forwardActive;
    }

    public void setForwardActive(Boolean forwardActive) {
        this.forwardActive = forwardActive;
    }
}
