/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.carlos.ac_android.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;


import com.example.carlos.ac_android.util.AppExecutors;
import com.example.carlos.ac_android.repository.local.dao.UserDao;
import com.example.carlos.ac_android.repository.local.entity.User;
import com.example.carlos.ac_android.repository.remote.api.ApiResponse;
import com.example.carlos.ac_android.repository.remote.api.WebServiceApi;
import com.example.carlos.ac_android.repository.remote.model.UserResponse;
import com.example.carlos.ac_android.vo.Resource;


import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Repository that handles User objects.
 */
@Singleton
public class UserRepository {
    private final UserDao userDao;
    private final WebServiceApi githubService;
    private final AppExecutors appExecutors;

    @Inject
    UserRepository(AppExecutors appExecutors, UserDao userDao, WebServiceApi githubService) {
        this.userDao = userDao;
        this.githubService = githubService;
        this.appExecutors = appExecutors;
    }

    public LiveData<Resource<User>> loadUser(final Integer login) {
        return new NetworkBoundResource<User, UserResponse>(appExecutors) {

            @Override
            protected void saveCallResult(@NonNull UserResponse userResponse) {
                User user = new User(userResponse.getId(), userResponse.getName());
                Log.e("saveCallResult"," saveCallResult " + user.getId());
                Log.e("saveCallResult"," saveCallResult " + user.getName());
                userDao.insert(user);
            }

            @Override
            protected boolean shouldFetch(@Nullable User data) {
                return data == null;
            }

            @NonNull
            @Override
            protected LiveData<User> loadFromDb() {
                return userDao.load(login);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<UserResponse>> createCall() {
                return githubService.getUser2(login);
            }
        }.asLiveData();
    }
}
