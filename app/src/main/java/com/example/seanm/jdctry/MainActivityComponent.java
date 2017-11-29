package com.example.seanm.jdctry;

import dagger.Component;

/**
 * Created by SeanM on 2017/11/23.
 */
@Component
public interface MainActivityComponent {
    void inject(MainActivity activity);
}
