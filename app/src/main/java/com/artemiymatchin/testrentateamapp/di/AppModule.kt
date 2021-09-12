package com.artemiymatchin.testrentateamapp.di

import android.app.Application
import androidx.room.Room
import com.artemiymatchin.testrentateamapp.data.UsersDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDatabase(
        app: Application,
        callback: UsersDatabase.Callback
    ) = Room.databaseBuilder(app, UsersDatabase::class.java, "task_database")
        .fallbackToDestructiveMigration()
        .addCallback(callback)
        .build()

    @Provides
    fun provideTaskDAO(db: UsersDatabase) = db.userDao()

}