package io.fruitful.dong.dorsal.model.registerRespone;

/**
 * Created by Ares on 9/5/2016.
 */
public class BodyRegister {

    private String name;
    private String password;
    private String email;
    private String mobile;
    private int platformType;
    private String platformVersion;
    private String deviceToken;
    private String deviceId;
    private String buildNumber;


    public BodyRegister(String name, String password, String mobile, String email, int platformType, String platformVersion, String deviceToken, String deviceId, String buildNumber) {
        this.name = name;
        this.password = password;
        this.mobile = mobile;
        this.email = email;
        this.platformType = platformType;
        this.platformVersion = platformVersion;
        this.deviceToken = deviceToken;
        this.deviceId =deviceId;
        this.buildNumber = buildNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
