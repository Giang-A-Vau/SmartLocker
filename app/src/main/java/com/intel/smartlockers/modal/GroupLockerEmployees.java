package com.intel.smartlockers.modal;

public class GroupLockerEmployees {
    private int grouplockeremployeeId;
    private int groupemployeeId;
    private int lockerId;

    public int getGrouplockeremployeeId() {
        return grouplockeremployeeId;
    }

    public void setGrouplockeremployeeId(int grouplockeremployeeId) {
        this.grouplockeremployeeId = grouplockeremployeeId;
    }

    public int getGroupemployeeId() {
        return groupemployeeId;
    }

    public void setGroupemployeeId(int groupemployeeId) {
        this.groupemployeeId = groupemployeeId;
    }

    public int getLockerId() {
        return lockerId;
    }

    public void setLockerId(int lockerId) {
        this.lockerId = lockerId;
    }

    @Override
    public String toString() {
        return "GroupLockerEmployees{" +
                "grouplockeremployeeId=" + grouplockeremployeeId +
                ", groupemployeeId=" + groupemployeeId +
                ", lockerId=" + lockerId +
                '}';
    }
}
