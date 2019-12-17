package com.intel.smartlockers;

public class GroupEmployees {
    private int groupemployeeId;
    private String groupemployeeName;

    public int getGroupemployeeId() {
        return groupemployeeId;
    }

    public void setGroupemployeeId(int groupemployeeId) {
        this.groupemployeeId = groupemployeeId;
    }

    public String getGroupemployeeName() {
        return groupemployeeName;
    }

    public void setGroupemployeeName(String groupemployeeName) {
        this.groupemployeeName = groupemployeeName;
    }

    @Override
    public String toString() {
        return "GroupEmployees{" +
                "groupemployeeId=" + groupemployeeId +
                ", groupemployeeName='" + groupemployeeName + '\'' +
                '}';
    }
}
