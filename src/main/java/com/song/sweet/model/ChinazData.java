package com.song.sweet.model;

import java.io.Serializable;

public class ChinazData implements Serializable {

    private static final long serialVersionUID = 2040505409706492119L;

    private String StateCode;

    private String Reason;

    private ChinazResult Result;

    public String getStateCode() {
        return StateCode;
    }

    public void setStateCode(String stateCode) {
        StateCode = stateCode;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public ChinazResult getResult() {
        return Result;
    }

    public void setResult(ChinazResult result) {
        Result = result;
    }
}
