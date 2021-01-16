package com.example.smproject.ui.Photos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PhotosListViewModel extends ViewModel{


        private MutableLiveData<String> mText;

        public PhotosListViewModel() {
            mText = new MutableLiveData<>();
            mText.setValue("This is photos fragment");
        }

        public LiveData<String> getText() {
            return mText;
        }



    }

