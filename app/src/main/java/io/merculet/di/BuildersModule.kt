package io.merculet.di

import io.merculet.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {
//    @ActivityScope
//    @ContributesAndroidInjector()
//    internal abstract fun bindSplashModule(): SplashActivity

    @io.merculet.core.di.ActivityScope
    @ContributesAndroidInjector()
    internal abstract fun bindMainModule(): MainActivity
}
