package com.demo.model.formbean;

import java.util.Map;

/**
 * Created by Admin on 2016/9/8.
 */
public class IssueFormBean {
    private Map<Integer,String> mapPhase;
    private Map<Integer, String> mapOs;
    private Map<Integer,String> mapTestsite;
    private static final IssueFormBean instance = new IssueFormBean();
    private IssueFormBean(){}
    public static IssueFormBean getInstance(){
        return instance;

    }

    public Map<Integer, String> getMapTestsite() {
        return mapTestsite;
    }

    public void setMapTestsite(Map<Integer, String> mapTestsite) {
        this.mapTestsite = mapTestsite;
    }

    public Map<Integer, String> getMapPhase() {
        return mapPhase;
    }

    public void setMapPhase(Map<Integer, String> mapPhase) {
        this.mapPhase = mapPhase;
    }

    public Map<Integer, String> getMapOs() {
        return mapOs;
    }

    public void setMapOs(Map<Integer, String> mapOs) {
        this.mapOs = mapOs;
    }


}
