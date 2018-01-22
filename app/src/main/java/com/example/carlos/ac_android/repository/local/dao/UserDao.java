package com.example.carlos.ac_android.repository.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.carlos.ac_android.repository.local.entity.User;

/**
 * Created by Carlos Leonardo Camilo Vargas Huamán on 1/21/18.
 */

@Dao
public interface UserDao {

    @Query("SELECT * FROM user WHERE id = :userId")
    LiveData<User> load(int userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

}
