package com.intel.smartlockers;

public class Lockers {
    private int id;
    private int lockerId;
    private int number;
    private String label;
    private int status; // status=0: normal, status = 1: register, status=2: use

    public Lockers() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLockerId() {
        return lockerId;
    }

    public void setLockerId(int lockerId) {
        this.lockerId = lockerId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Lockers{" +
                "id=" + id +
                ", lockerId=" + lockerId +
                ", number=" + number +
                ", label='" + label + '\'' +
                ", status=" + status +
                '}';
    }
}
