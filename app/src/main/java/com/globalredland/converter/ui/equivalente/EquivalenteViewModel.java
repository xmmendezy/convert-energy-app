package com.globalredland.converter.ui.equivalente;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EquivalenteViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EquivalenteViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is equivalente fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
