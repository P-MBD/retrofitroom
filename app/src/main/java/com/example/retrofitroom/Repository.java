package com.example.retrofitroom;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.Executors;

public class Repository {
    private ItemDao itemDao;
    private LiveData<List<Item>> allItems;

    public Repository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        itemDao = db.itemDao();
        allItems = db.itemDao().getAllItems();
    }

    public LiveData<List<Item>> getAllItems() {
        return allItems;
    }

    public void insert(List<Item> items) {
        Executors.newSingleThreadExecutor().execute(() -> itemDao.insert(items));
    }
}
