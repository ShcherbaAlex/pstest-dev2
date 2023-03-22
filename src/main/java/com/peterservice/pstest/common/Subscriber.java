package com.peterservice.pstest.common;

/**
 * Created by Andrey.Shilov on 17.11.2015.
 */
public class Subscriber {
    private String msisdn;
    private String ratePlan;

    public Subscriber(){}

    public String getMsisdn(){
        return this.msisdn;
    }

    public void setMsisdn(String msisdn){
        this.msisdn = msisdn;
    }

    public String getRatePlan(){
        return this.ratePlan;
    }

    public void setRatePlan(String ratePlan){
        this.ratePlan = ratePlan;
    }
}
