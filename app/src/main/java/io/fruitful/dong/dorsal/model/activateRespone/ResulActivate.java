
package io.fruitful.dong.dorsal.model.activateRespone;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class ResulActivate {

    @SerializedName("error")
    @Expose
    private Object error;
    @SerializedName("responseData")
    @Expose
    private Boolean responseData;

    /**
     * @return The error
     */
    public Object getError() {
        return error;
    }

    /**
     * @param error The error
     */
    public void setError(Object error) {
        this.error = error;
    }

    /**
     * @return The responseData
     */
    public Boolean getResponseData() {
        return responseData;
    }

    /**
     * @param responseData The responseData
     */
    public void setResponseData(Boolean responseData) {
        this.responseData = responseData;
    }

}