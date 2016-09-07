
package io.fruitful.dong.dorsal.model.registerRespone;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class ResultRegister {

    @SerializedName("error")
    @Expose
    private Error error;
    @SerializedName("responseData")
    @Expose
    private ResponseData responseData;

    /**
     * 
     * @return
     *     The error
     */
    public Error getError() {
        return error;
    }

    /**
     * 
     * @param error
     *     The error
     */
    public void setError(Error error) {
        this.error = error;
    }

    /**
     * 
     * @return
     *     The responseData
     */
    public ResponseData getResponseData() {
        return responseData;
    }

    /**
     * 
     * @param responseData
     *     The responseData
     */
    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }

}
