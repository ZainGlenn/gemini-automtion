package com.gemini.util.components.table.model;

import com.gemini.util.components.table.TableColumn;

public class GlobalSearchData extends TableData {

    @TableColumn(idx = 1)
    private String name;
    @TableColumn(idx = 2)
    private String dateOfBirth;
    @TableColumn(idx = 3)
    private String sex;
    @TableColumn(idx = 4)
    private String patientIdType;
    @TableColumn(idx = 5)
    private String patientId;
    @TableColumn(idx = 6)
    private String personalIdType;
    @TableColumn(idx = 7)
    private String personalId;

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getPatientIdType() {
        return patientIdType;
    }

    public String getPersonalIdType() {
        return personalIdType;
    }

    public String getPersonalId() {
        return personalId;
    }

    @Override
    public String toString() {
        return "GlobalSearchData{" +
                "name='" + name + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", sex='" + sex + '\'' +
                ", patientId='" + patientId + '\'' +
                ", personalIdType='" + personalIdType + '\'' +
                ", personalId='" + personalId + '\'' +
                "} " + super.toString();
    }
}
