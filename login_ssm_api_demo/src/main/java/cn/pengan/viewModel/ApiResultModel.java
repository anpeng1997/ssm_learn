package cn.pengan.viewModel;

import java.io.Serializable;

public class ApiResultModel implements Serializable {
    private Boolean status;
    private Object data;
    private String errorMsg;

    public  ApiResultModel(){ }

    public ApiResultModel(Boolean status, Object data, String errorMsg) {
        this.status = status;
        this.data = data;
        this.errorMsg = errorMsg;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
