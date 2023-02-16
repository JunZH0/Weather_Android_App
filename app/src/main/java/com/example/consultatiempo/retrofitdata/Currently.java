package com.example.consultatiempo.retrofitdata;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Currently {

    @SerializedName("time")
    @Expose
    private Integer time;
    @SerializedName("temperature")
    @Expose
    private Double temperature;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("humidity")
    @Expose
    private Double humidity;
    @SerializedName("precipProbability")
    @Expose
    private Double precipProbability;

    public Integer getTime() {
        return time;
    }

    public Double getTemperature() {
        return ( (temperature - 32) * 5/9);
    }

    public String getSummary() {
        return summary;
    }

    public String getIcon() {
        return icon;
    }


    public Double getHumidity() {
        return humidity;
    }


    public Double getProbabilityPrecip() {
        return precipProbability;
    }





}
