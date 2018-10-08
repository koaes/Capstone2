package com.example.windows7.myapplication;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.windows7.myapplication.data.AppDatabase;

public class MainViewModel extends AndroidViewModel{

    private LiveData<Integer> returnedNumber;

    public MainViewModel(@NonNull Application application) {
        super(application);
        AppDatabase mDb = AppDatabase.getInstance(this.getApplication());
        returnedNumber = mDb.statsDao().getTotal();

    }

    public LiveData<Integer> getReturnedNumber() {
        return returnedNumber;
    }
}
