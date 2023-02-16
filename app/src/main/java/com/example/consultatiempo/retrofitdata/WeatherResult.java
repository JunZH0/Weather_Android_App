package com.example.consultatiempo.retrofitdata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherResult {
    @Expose
    @SerializedName("currently")
    private  Currently currently;

    @Expose
    @SerializedName("timezone")
    private  String timezone;



    public  Currently getCurrently() {
        return currently;
    }

    public  String getTimezone() {
        return timezone;
    }


}
