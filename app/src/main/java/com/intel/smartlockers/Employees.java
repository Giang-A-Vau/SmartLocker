package com.intel.smartlockers;

public class Employees {
    private int employeeId;
    private int groupemployeeId;
    private int employeeCode;
    private String name;
    private int codeLogic;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getGroupemployeeId() {
        return groupemployeeId;
    }

    public void setGroupemployeeId(int groupemployeeId) {
        this.groupemployeeId = groupemployeeId;
    }

    public int getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(int employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCodeLogic() {
        return codeLogic;
    }

    public void setCodeLogic(int codeLogic) {
        this.codeLogic = codeLogic;
    }

    @Override
    public String toString() {
        return "Employees{" +
                "employeeId=" + employeeId +
                ", groupemployeeId=" + groupemployeeId +
                ", employeeCode=" + employeeCode +
                ", name='" + name + '\'' +
                ", codeLogic=" + codeLogic +
                '}';
    }
}
