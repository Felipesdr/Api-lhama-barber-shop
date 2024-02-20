package com.lhamacorp.apibarbershop.model.ENUMs;

public enum ScheduleStatus {

    PENDING("pending"),
    CONFIRMED("confirmed"),
    EXECUTING("executing"),
    FINISHED("finished"),
    CANCELED("canceled");

    private String status;

    ScheduleStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }
}
