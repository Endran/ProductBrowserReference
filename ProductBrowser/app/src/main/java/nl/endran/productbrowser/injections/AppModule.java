/*
 * Copyright (c) 2016 by David Hardy. Licensed under the Apache License, Version 2.0.
 */

package nl.endran.productbrowser.injections;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Handler;
import android.support.annotation.NonNull;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import nl.endran.productbrowser.example.ModelInteractor;

@Module
public class AppModule {

    public static final String MODEL_INTERACTOR_A = "MODEL_INTERACTOR_A";
    public static final String MODEL_INTERACTOR_B = "MODEL_INTERACTOR_B";

    @NonNull
    private final Context context;

    @NonNull
    private final Handler handler;

    public AppModule(@NonNull final Context context, @NonNull final Handler handler) {
        this.context = context.getApplicationContext();
        this.handler = handler;
    }

    @Singleton
    @Provides
    public Context provideContext() {
        return context;
    }

    @Singleton
    @Provides
    public Handler provideHandler() {
        return handler;
    }

    @Singleton
    @Provides
    public Resources provideResources(@NonNull final Context context) {
        return context.getResources();
    }

    @Singleton
    @Provides
    public SharedPreferences provideSharedPreferences(@NonNull final Context context) {
        return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    @Provides
    @Named(MODEL_INTERACTOR_A)
    public ModelInteractor provideModelInteractorA() {
        return new ModelInteractor("A");
    }

    @Provides
    @Named(MODEL_INTERACTOR_B)
    public ModelInteractor provideModelInteractorB() {
        return new ModelInteractor("B");
    }
}
