package com.example.carlos.ac_android.viewmodel;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.carlos.ac_android.repository.UserRepository;
import com.example.carlos.ac_android.repository.local.entity.User;
import com.example.carlos.ac_android.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.ArgumentCaptor;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;

/**
 * Created by carlosleonardocamilovargashuaman on 2/14/18.
 */

@SuppressWarnings("unchecked")
@RunWith(JUnit4.class)
public class UserViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private UserViewModel userViewModel;
    private UserRepository userRepository;

    @Before
    public void setup() {
        userRepository = mock(UserRepository.class);
        userViewModel = new UserViewModel(userRepository);
    }

    @Test
    public void verifyUserRepositoryIsNotNull() {
        assertThat(userViewModel.getUser(), notNullValue());
        verify(userRepository, never()).loadUser(anyInt());
        userViewModel.setLogin(1);
        verify(userRepository, never()).loadUser(anyInt());
    }

    @Test
    public void verifyLoadUserParameterIsCaptured() {
        ArgumentCaptor<Integer> captor = ArgumentCaptor.forClass(Integer.class);
        userViewModel.getUser().observeForever(mock(Observer.class));
        userViewModel.setLogin(1);
        verify(userRepository).loadUser(captor.capture());
        assertThat(captor.getValue(), is(1));
        reset(userRepository);
    }

    @Test
    public void sendResultToUI() {
        MutableLiveData<Resource<User>> foo = new MutableLiveData<>();
        when(userRepository.loadUser(1)).thenReturn(foo);
        Observer<Resource<User>> observer = mock(Observer.class);
        userViewModel.getUser().observeForever(observer);
        userViewModel.setLogin(1);
        verify(observer, never()).onChanged(any(Resource.class));
    }
}
