
package io.fruitful.dong.dorsal.model.listResportRespone;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class BodyListReport {

    @SerializedName("approved")
    @Expose
    private Boolean approved;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("timeRange")
    @Expose
    private Integer timeRange;
    @SerializedName("pageIndex")
    @Expose
    private Integer pageIndex;
    @SerializedName("pageSize")
    @Expose
    private Integer pageSize;

    public BodyListReport(Boolean approved, String country, Integer timeRange, Integer pageIndex, Integer pageSize) {
        this.approved = approved;
        this.country = country;
        this.timeRange = timeRange;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    /**
     * 
     * @return
     *     The approved
     */
    public Boolean getApproved() {
        return approved;
    }

    /**
     * 
     * @param approved
     *     The approved
     */
    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    /**
     * 
     * @return
     *     The country
     */
    public String getCountry() {
        return country;
    }

    /**
     * 
     * @param country
     *     The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 
     * @return
     *     The timeRange
     */
    public Integer getTimeRange() {
        return timeRange;
    }

    /**
     * 
     * @param timeRange
     *     The timeRange
     */
    public void setTimeRange(Integer timeRange) {
        this.timeRange = timeRange;
    }

    /**
     * 
     * @return
     *     The pageIndex
     */
    public Integer getPageIndex() {
        return pageIndex;
    }

    /**
     * 
     * @param pageIndex
     *     The pageIndex
     */
    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    /**
     * 
     * @return
     *     The pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * 
     * @param pageSize
     *     The pageSize
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
