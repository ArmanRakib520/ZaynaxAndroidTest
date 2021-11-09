package com.zaynax.test.viewmodel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zaynax.test.Network.callback.ActivationShow;
import com.zaynax.test.Network.callback.RequestCompleteListener;
import com.zaynax.test.model.ActivationList;
import com.zaynax.test.model.Item;

import java.util.List;

public class ActivatedViewModel extends ViewModel {
    public MutableLiveData<Boolean> progressLiveData = new MutableLiveData<>();
    public MutableLiveData<List<Item>> activationLiveData = new MutableLiveData<>();

    public void getActivationList(Context context, String page, ActivationShow model) {
        progressLiveData.postValue(true);
        model.getActivation(context, page,new RequestCompleteListener() {
            @Override
            public void onRequestSuccess(Object obj) {
                ActivationList data = (ActivationList) obj;
                if (data != null )
                activationLiveData.postValue(data.getData().getItems());
                progressLiveData.postValue(false);
            }
            @Override
            public void onRequestFailed(String errorMsg) {
                progressLiveData.postValue(false);
                progressLiveData.postValue(null);
            }
        });


    }
}
