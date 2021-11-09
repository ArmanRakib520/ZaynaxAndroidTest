package com.zaynax.test.Network.callback;

public interface RequestCompleteListener {
    void onRequestSuccess(Object obj);
    void onRequestFailed(String errorMsg);
}
