
package io.fruitful.dong.dorsal.model.loginRespone;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class ResponseData {

    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("account")
    @Expose
    private Account account;

    /**
     * 
     * @return
     *     The token
     */
    public String getToken() {
        return token;
    }

    /**
     * 
     * @param token
     *     The token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 
     * @return
     *     The account
     */
    public Account getAccount() {
        return account;
    }

    /**
     * 
     * @param account
     *     The account
     */
    public void setAccount(Account account) {
        this.account = account;
    }

}
