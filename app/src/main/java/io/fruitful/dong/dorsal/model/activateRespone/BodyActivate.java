package io.fruitful.dong.dorsal.model.activateRespone;

/**
 * Created by Ares on 9/5/2016.
 */
public class BodyActivate {

    /**
     * accountId : 1000000221
     * activationCode : 9999
     * platformType : 1
     * platformVersion : 1.0
     * deviceToken : PUSH NOTIFICATION DEVICE TOKEN
     * deviceId :
     * buildNumber : 20160126
     */

    private int accountId;
    private String activationCode;
    private int platformType;
    private String platformVersion;
    private String deviceToken;
    private String deviceId;
    private String buildNumber;


    public BodyActivate(int accountId, String activationCode, int platformType, String platformVersion, String deviceToken, String deviceId, String buildNumber) {
        this.accountId = accountId;
        this.activationCode = activationCode;
        this.platformType = platformType;
        this.platformVersion = platformVersion;
        this.deviceToken = deviceToken;
        this.deviceId = deviceId;
        this.buildNumber = buildNumber;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public int getPlatformType() {
        return platformType;
    }

    public void setPlatformType(int platformType) {
        this.platformType = platformType;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public void setPlatformVersion(String platformVersion) {
        this.platformVersion = platformVersion;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(String buildNumber) {
        this.buildNumber = buildNumber;
    }
}
