package com.intel.smartlockers;

public class GroupLockers {
    private int grouplockerId;
    private String grouplockerName;

    public int getGrouplockerId() {
        return grouplockerId;
    }

    public String getGrouplockerName() {
        return grouplockerName;
    }

    public void setGrouplockerId(int grouplockerId) {
        this.grouplockerId = grouplockerId;
    }

    public void setGrouplockerName(String grouplockerName) {
        this.grouplockerName = grouplockerName;
    }

    @Override
    public String toString() {
        return "GroupLockers{" +
                "grouplockerId=" + grouplockerId +
                ", grouplockerName='" + grouplockerName + '\'' +
                '}';
    }
}
