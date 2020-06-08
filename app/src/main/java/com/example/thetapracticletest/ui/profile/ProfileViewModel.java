package com.example.thetapracticletest.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.thetapracticletest.utils.AppConstants;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ProfileViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue(AppConstants.AuthUserKey);
    }

    public LiveData<String> getText() {
        return mText;
    }
}