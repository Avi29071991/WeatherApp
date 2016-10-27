package com.example.l091735.weather_modified_app.model.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DailyData {

    @SerializedName("time")
    @Expose
    private long time;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("sunriseTime")
    @Expose
    private int sunriseTime;
    @SerializedName("sunsetTime")
    @Expose
    private int sunsetTime;
    @SerializedName("moonPhase")
    @Expose
    private double moonPhase;
    @SerializedName("precipIntensity")
    @Expose
    private double precipIntensity;
    @SerializedName("precipIntensityMax")
    @Expose
    private double precipIntensityMax;
    @SerializedName("precipIntensityMaxTime")
    @Expose
    private int precipIntensityMaxTime;
    @SerializedName("precipProbability")
    @Expose
    private double precipProbability;
    @SerializedName("precipType")
    @Expose
    private String precipType;
    @SerializedName("temperatureMin")
    @Expose
    private double temperatureMin;
    @SerializedName("temperatureMinTime")
    @Expose
    private int temperatureMinTime;
    @SerializedName("temperatureMax")
    @Expose
    private double temperatureMax;
    @SerializedName("temperatureMaxTime")
    @Expose
    private int temperatureMaxTime;
    @SerializedName("apparentTemperatureMin")
    @Expose
    private double apparentTemperatureMin;
    @SerializedName("apparentTemperatureMinTime")
    @Expose
    private int apparentTemperatureMinTime;
    @SerializedName("apparentTemperatureMax")
    @Expose
    private double apparentTemperatureMax;
    @SerializedName("apparentTemperatureMaxTime")
    @Expose
    private int apparentTemperatureMaxTime;
    @SerializedName("dewPoint")
    @Expose
    private double dewPoint;
    @SerializedName("humidity")
    @Expose
    private double humidity;
    @SerializedName("windSpeed")
    @Expose
    private double windSpeed;
    @SerializedName("windBearing")
    @Expose
    private int windBearing;
    @SerializedName("visibility")
    @Expose
    private double visibility;
    @SerializedName("cloudCover")
    @Expose
    private double cloudCover;
    @SerializedName("pressure")
    @Expose
    private double pressure;
    @SerializedName("ozone")
    @Expose
    private double ozone;

    /**
     * @return The time
     */
    public long getTime() {
        return time;
    }

    /**
     * @param time The time
     */
    public void setTime(long time) {
        this.time = time;
    }

    /**
     * @return The summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary The summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * @return The icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon The icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return The sunriseTime
     */
    public int getSunriseTime() {
        return sunriseTime;
    }

    /**
     * @param sunriseTime The sunriseTime
     */
    public void setSunriseTime(int sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    /**
     * @return The sunsetTime
     */
    public int getSunsetTime() {
        return sunsetTime;
    }

    /**
     * @param sunsetTime The sunsetTime
     */
    public void setSunsetTime(int sunsetTime) {
        this.sunsetTime = sunsetTime;
    }

    /**
     * @return The moonPhase
     */
    public double getMoonPhase() {
        return moonPhase;
    }

    /**
     * @param moonPhase The moonPhase
     */
    public void setMoonPhase(double moonPhase) {
        this.moonPhase = moonPhase;
    }

    /**
     * @return The precipIntensity
     */
    public double getPrecipIntensity() {
        return precipIntensity;
    }

    /**
     * @param precipIntensity The precipIntensity
     */
    public void setPrecipIntensity(double precipIntensity) {
        this.precipIntensity = precipIntensity;
    }

    /**
     * @return The precipIntensityMax
     */
    public double getPrecipIntensityMax() {
        return precipIntensityMax;
    }

    /**
     * @param precipIntensityMax The precipIntensityMax
     */
    public void setPrecipIntensityMax(double precipIntensityMax) {
        this.precipIntensityMax = precipIntensityMax;
    }

    /**
     * @return The precipIntensityMaxTime
     */
    public int getPrecipIntensityMaxTime() {
        return precipIntensityMaxTime;
    }

    /**
     * @param precipIntensityMaxTime The precipIntensityMaxTime
     */
    public void setPrecipIntensityMaxTime(int precipIntensityMaxTime) {
        this.precipIntensityMaxTime = precipIntensityMaxTime;
    }

    /**
     * @return The precipProbability
     */
    public double getPrecipProbability() {
        return precipProbability;
    }

    /**
     * @param precipProbability The precipProbability
     */
    public void setPrecipProbability(double precipProbability) {
        this.precipProbability = precipProbability;
    }

    /**
     * @return The precipType
     */
    public String getPrecipType() {
        return precipType;
    }

    /**
     * @param precipType The precipType
     */
    public void setPrecipType(String precipType) {
        this.precipType = precipType;
    }

    /**
     * @return The temperatureMin
     */
    public double getTemperatureMin() {
        return temperatureMin;
    }

    /**
     * @param temperatureMin The temperatureMin
     */
    public void setTemperatureMin(double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    /**
     * @return The temperatureMinTime
     */
    public int getTemperatureMinTime() {
        return temperatureMinTime;
    }

    /**
     * @param temperatureMinTime The temperatureMinTime
     */
    public void setTemperatureMinTime(int temperatureMinTime) {
        this.temperatureMinTime = temperatureMinTime;
    }

    /**
     * @return The temperatureMax
     */
    public double getTemperatureMax() {
        return temperatureMax;
    }

    /**
     * @param temperatureMax The temperatureMax
     */
    public void setTemperatureMax(double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    /**
     * @return The temperatureMaxTime
     */
    public int getTemperatureMaxTime() {
        return temperatureMaxTime;
    }

    /**
     * @param temperatureMaxTime The temperatureMaxTime
     */
    public void setTemperatureMaxTime(int temperatureMaxTime) {
        this.temperatureMaxTime = temperatureMaxTime;
    }

    /**
     * @return The apparentTemperatureMin
     */
    public double getApparentTemperatureMin() {
        return apparentTemperatureMin;
    }

    /**
     * @param apparentTemperatureMin The apparentTemperatureMin
     */
    public void setApparentTemperatureMin(double apparentTemperatureMin) {
        this.apparentTemperatureMin = apparentTemperatureMin;
    }

    /**
     * @return The apparentTemperatureMinTime
     */
    public int getApparentTemperatureMinTime() {
        return apparentTemperatureMinTime;
    }

    /**
     * @param apparentTemperatureMinTime The apparentTemperatureMinTime
     */
    public void setApparentTemperatureMinTime(int apparentTemperatureMinTime) {
        this.apparentTemperatureMinTime = apparentTemperatureMinTime;
    }

    /**
     * @return The apparentTemperatureMax
     */
    public double getApparentTemperatureMax() {
        return apparentTemperatureMax;
    }

    /**
     * @param apparentTemperatureMax The apparentTemperatureMax
     */
    public void setApparentTemperatureMax(double apparentTemperatureMax) {
        this.apparentTemperatureMax = apparentTemperatureMax;
    }

    /**
     * @return The apparentTemperatureMaxTime
     */
    public int getApparentTemperatureMaxTime() {
        return apparentTemperatureMaxTime;
    }

    /**
     * @param apparentTemperatureMaxTime The apparentTemperatureMaxTime
     */
    public void setApparentTemperatureMaxTime(int apparentTemperatureMaxTime) {
        this.apparentTemperatureMaxTime = apparentTemperatureMaxTime;
    }

    /**
     * @return The dewPoint
     */
    public double getDewPoint() {
        return dewPoint;
    }

    /**
     * @param dewPoint The dewPoint
     */
    public void setDewPoint(double dewPoint) {
        this.dewPoint = dewPoint;
    }

    /**
     * @return The humidity
     */
    public double getHumidity() {
        return humidity;
    }

    /**
     * @param humidity The humidity
     */
    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    /**
     * @return The windSpeed
     */
    public double getWindSpeed() {
        return windSpeed;
    }

    /**
     * @param windSpeed The windSpeed
     */
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    /**
     * @return The windBearing
     */
    public int getWindBearing() {
        return windBearing;
    }

    /**
     * @param windBearing The windBearing
     */
    public void setWindBearing(int windBearing) {
        this.windBearing = windBearing;
    }

    /**
     * @return The visibility
     */
    public double getVisibility() {
        return visibility;
    }

    /**
     * @param visibility The visibility
     */
    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }

    /**
     * @return The cloudCover
     */
    public double getCloudCover() {
        return cloudCover;
    }

    /**
     * @param cloudCover The cloudCover
     */
    public void setCloudCover(double cloudCover) {
        this.cloudCover = cloudCover;
    }

    /**
     * @return The pressure
     */
    public double getPressure() {
        return pressure;
    }

    /**
     * @param pressure The pressure
     */
    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    /**
     * @return The ozone
     */
    public double getOzone() {
        return ozone;
    }

    /**
     * @param ozone The ozone
     */
    public void setOzone(double ozone) {
        this.ozone = ozone;
    }
}
