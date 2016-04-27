/*
 * Copyright (c) 2016 by David Hardy. Licensed under the Apache License, Version 2.0.
 */

package nl.endran.productbrowser;

import android.app.Application;
import android.os.Handler;

import nl.endran.productbrowser.injections.AppComponent;
import nl.endran.productbrowser.injections.AppModule;
import nl.endran.productbrowser.injections.DaggerAppComponent;

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this, new Handler())).build();
        appComponent.inject(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
