package com.example.cryptolocker.ui.settings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SettingsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SettingsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Settings Will Be Available Soon...");
    }

    public LiveData<String> getText() {
        return mText;
    }
}