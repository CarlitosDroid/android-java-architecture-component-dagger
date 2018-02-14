package com.example.carlos.ac_android.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.VisibleForTesting;

import com.example.carlos.ac_android.repository.UserRepository;
import com.example.carlos.ac_android.repository.local.entity.User;
import com.example.carlos.ac_android.util.AbsentLiveData;
import com.example.carlos.ac_android.vo.Resource;

import java.util.Objects;

import javax.inject.Inject;

/**
 * Created by carlos on 1/18/18.
 */

public class UserViewModel extends ViewModel {

    private MutableLiveData<Integer> login = new MutableLiveData<>();
    private final LiveData<Resource<User>> user;

    @Inject
    public UserViewModel(UserRepository userRepository) {

        user = Transformations.switchMap(this.login, login -> {
            if(login == null){
                return AbsentLiveData.create();
            }else{
                return userRepository.loadUser(login);
            }
        });
    }

    @VisibleForTesting
    public void setLogin(int login){
        if(Objects.equals(this.login.getValue(), login)){
            return;
        }
        this.login.setValue(login);
    }

    @VisibleForTesting
    public LiveData<Resource<User>> getUser(){
        return user;
    }

}
