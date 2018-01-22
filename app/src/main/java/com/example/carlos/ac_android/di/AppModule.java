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

package com.example.carlos.ac_android.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.carlos.ac_android.repository.local.AppDatabase;
import com.example.carlos.ac_android.repository.local.dao.UserDao;
import com.example.carlos.ac_android.repository.remote.api.WebServiceApi;
import com.example.carlos.ac_android.util.LiveDataCallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
class AppModule {
    @Singleton
    @Provides
    WebServiceApi provideGithubService() {

        HttpLoggingInterceptor li = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

        return new Retrofit.Builder()
                .baseUrl("http://demo.wp-api.org/wp-json/wp/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .client(new OkHttpClient.Builder()
                        .addInterceptor(li)
                        .build())
                .build()
                .create(WebServiceApi.class);
    }

    @Singleton
    @Provides
    AppDatabase provideDb(Application app) {
        return Room.databaseBuilder(app, AppDatabase.class, "github.db").build();
    }

    @Singleton
    @Provides
    UserDao provideUserDao(AppDatabase db) {
        return db.userDao();
    }

}
