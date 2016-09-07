package io.fruitful.dong.dorsal.model;

/**
 * Created by Ares on 9/5/2016.
 */
public class ErrorRespone {


    /**
     * code : 1003
     * message : Email or password is incorrect.
     */

    private ErrorBean error;
    /**
     * error : {"code":1003,"message":"Email or password is incorrect."}
     * responseData : null
     */

    private Object responseData;




    public ErrorBean getError() {
        return error;
    }

    public void setError(ErrorBean error) {
        this.error = error;
    }

    public Object getResponseData() {
        return responseData;
    }

    public void setResponseData(Object responseData) {
        this.responseData = responseData;
    }

    public static class ErrorBean {
        private int code;
        private String message;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
