
package io.fruitful.dong.dorsal.model.listResportRespone;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class ResultList {

    @SerializedName("error")
    @Expose
    private Object error;
    @SerializedName("responseData")
    @Expose
    private List<ResponseDatum> responseData = new ArrayList<ResponseDatum>();

    /**
     * 
     * @return
     *     The error
     */
    public Object getError() {
        return error;
    }

    /**
     * 
     * @param error
     *     The error
     */
    public void setError(Object error) {
        this.error = error;
    }

    /**
     * 
     * @return
     *     The responseData
     */
    public List<ResponseDatum> getResponseData() {
        return responseData;
    }

    /**
     * 
     * @param responseData
     *     The responseData
     */
    public void setResponseData(List<ResponseDatum> responseData) {
        this.responseData = responseData;
    }

}
