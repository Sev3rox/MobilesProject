package com.example.smproject.ui.Photos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class PhotosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PhotosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is photos fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
    public static Bitmap getImage(String url) throws IOException {
        URL Url=new URL(url);
        Bitmap bmp = BitmapFactory.decodeStream(Url.openConnection().getInputStream());
        return bmp;
    }


}

