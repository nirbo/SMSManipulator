package org.nirbo.smsmanipulator.model;

import com.j256.ormlite.field.DatabaseField;

public class ModelBase {

    @DatabaseField(generatedId = true)
    Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
