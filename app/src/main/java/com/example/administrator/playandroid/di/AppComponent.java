package com.example.administrator.playandroid.di;

import android.app.Application;

import com.example.administrator.playandroid.App;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Administrator on 2019/6/28.
 * <p>Copyright 2019 Success101.</p>
 */
@Singleton
@Component(modules ={ AndroidSupportInjectionModule.class,ActivityBindingModule.class,FragmentBindModule.class,AppModule.class})
public interface AppComponent extends AndroidInjector<App> {
    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
