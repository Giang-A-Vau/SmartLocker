package com.intel.smartlockers;

import java.util.Date;

public class Accesses {
    private int accessId;
    private int lockerId;
    private int employeeId;
    private Date accessTime;
    private int status;

    public int getAccessId() {
        return accessId;
    }

    public void setAccessId(int accessId) {
        this.accessId = accessId;
    }

    public int getLockerId() {
        return lockerId;
    }

    public void setLockerId(int lockerId) {
        this.lockerId = lockerId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Date getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(Date accessTime) {
        this.accessTime = accessTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Accesses{" +
                "accessId=" + accessId +
                ", lockerId=" + lockerId +
                ", employeeId=" + employeeId +
                ", accessTime=" + accessTime +
                ", status=" + status +
                '}';
    }
}
