package com.demo.model.formbean;


import java.util.Map;

public class TCFormBean {
	
	private  int beanflag=0;
	
	private  Map<Integer, String> mapBrand;
	private  Map<Integer, String> mapOs;
	private  Map<Integer, String> mapFunction;
	private  Map<Integer, String> mapTestMode;

	private  Map<Integer, String> mapLanguage;

	private static final TCFormBean instance = new TCFormBean();
	
	private TCFormBean(){}
	public static TCFormBean getInstance(){
		return instance;	
		
	}


	public Map<Integer, String> getMapTestMode() {
		return mapTestMode;
	}

	public void setMapTestMode(Map<Integer, String> mapTestMode) {
		this.mapTestMode = mapTestMode;
	}

	public Map<Integer, String> getMapBrand() {
		return mapBrand;
	}

	public void setMapBrand(Map<Integer, String> mapBrand) {
		this.mapBrand = mapBrand;
	}

	public Map<Integer, String> getMapOs() {
		return mapOs;
	}

	public void setMapOs(Map<Integer, String> mapOs) {
		this.mapOs = mapOs;
	}

	public Map<Integer, String> getMapFunction() {
		return mapFunction;
	}

	public void setMapFunction(Map<Integer, String> mapFunction) {
		this.mapFunction = mapFunction;
	}

	public Map<Integer, String> getMapLanguage() {
		return mapLanguage;
	}

	public void setMapLanguage(Map<Integer, String> mapLanguage) {
		this.mapLanguage = mapLanguage;
	}
}
