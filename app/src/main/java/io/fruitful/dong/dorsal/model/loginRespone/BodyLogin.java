package io.fruitful.dong.dorsal.model.loginRespone;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class BodyLogin {


    public BodyLogin(Integer platformType, String platformVersion, String deviceToken, String deviceId, String buildNumber) {
        this.platformType = platformType;
        this.platformVersion = platformVersion;
        this.deviceToken = deviceToken;
        this.deviceId = deviceId;
        this.buildNumber = buildNumber;
    }

    @SerializedName("platformType")
    @Expose
    private Integer platformType;
    @SerializedName("platformVersion")
    @Expose
    private String platformVersion;
    @SerializedName("deviceToken")
    @Expose
    private String deviceToken;
    @SerializedName("deviceId")
    @Expose
    private String deviceId;
    @SerializedName("buildNumber")
    @Expose
    private String buildNumber;

    /**
     *
     * @return
     * The platformType
     */
    public Integer getPlatformType() {
        return platformType;
    }

    /**
     *
     * @param platformType
     * The platformType
     */
    public void setPlatformType(Integer platformType) {
        this.platformType = platformType;
    }

    /**
     *
     * @return
     * The platformVersion
     */
    public String getPlatformVersion() {
        return platformVersion;
    }

    /**
     *
     * @param platformVersion
     * The platformVersion
     */
    public void setPlatformVersion(String platformVersion) {
        this.platformVersion = platformVersion;
    }

    /**
     *
     * @return
     * The deviceToken
     */
    public String getDeviceToken() {
        return deviceToken;
    }

    /**
     *
     * @param deviceToken
     * The deviceToken
     */
    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    /**
     *
     * @return
     * The deviceId
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     *
     * @param deviceId
     * The deviceId
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     *
     * @return
     * The buildNumber
     */
    public String getBuildNumber() {
        return buildNumber;
    }

    /**
     *
     * @param buildNumber
     * The buildNumber
     */
    public void setBuildNumber(String buildNumber) {
        this.buildNumber = buildNumber;
    }

}