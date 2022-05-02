package com.plcoding.calorytracker.di



import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.plcoding.core.data.preferences.DefaultPreferences
import com.plcoding.core.domain.preferences.Preferences
import com.plcoding.core.domain.use_case.FilterOutDigits
import com.plcoding.on_boarding_domain.use_cases.ValidateNutrients
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//module is a central place that we can store objects and classes needed in the app
@Module
@InstallIn(SingletonComponent::class) //app lifecycle
object AppModule {
    //dependency that can be used
    @Provides
    @Singleton
    fun providePreferences( // provides the defined default preferences impl through the interface
        sharedPreferences: SharedPreferences
    ): Preferences {
        return DefaultPreferences(sharedPreferences)
    }

    @Provides
    @Singleton //provides the preferences with the shared preferences
    fun provideSharedPreferences(
        app: Application
    ): SharedPreferences {
        return app.getSharedPreferences("shared_pref", MODE_PRIVATE)
    }

    @Provides
    @Singleton //provides the use case to filter digits
    fun provideFilterOutDigitsUseCase(): FilterOutDigits{
        return FilterOutDigits()
    }
}