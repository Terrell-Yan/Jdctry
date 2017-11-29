package com.example.seanm.jdctry.presenter;

import com.example.seanm.jdctry.fragment.Cart_F;

import dagger.Component;

/**
 * Created by SeanM on 2017/11/23.
 */
@PerFragment
@Component(dependencies = AppComponent.class,modules = HomePresenterModule.class)
public interface HomeFragmentComponent {
    void inject(Cart_F fragment);
}
