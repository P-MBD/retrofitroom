package com.example.retrofitroom;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<Item> items);

    @Query("SELECT * FROM items")
    LiveData<List<Item>> getAllItems(); // Change the return type to LiveData
}
