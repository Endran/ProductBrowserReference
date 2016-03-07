/*
 * Copyright (c) 2016 by David Hardy. Licensed under the Apache License, Version 2.0.
 */

package nl.endran.productbrowser.injections;

import javax.inject.Singleton;

import dagger.Component;
import nl.endran.productbrowser.App;
import nl.endran.productbrowser.interactors.ScreenFlowController;
import nl.endran.productbrowser.fragments.OverviewFragmentPresenter;
import nl.endran.productbrowser.fragments.OverviewFragmentView;
import nl.endran.productbrowser.fragments.DetailFragmentPresenter;
import nl.endran.productbrowser.fragments.DetailFragmentView;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(App app);

    ScreenFlowController getScreenFlowController();

    DetailFragmentView getDetailFragmentView();

    DetailFragmentPresenter.Factory getDetailFragmentPresenterFactory();

    OverviewFragmentView getOverviewFragmentView();

    OverviewFragmentPresenter getOverviewFragmentPresenter();
}
