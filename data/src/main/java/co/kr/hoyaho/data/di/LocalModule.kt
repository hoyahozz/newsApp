package co.kr.hoyaho.data.di

import android.content.Context
import androidx.room.Room
import co.kr.hoyaho.data.local.WantedDatabase
import co.kr.hoyaho.data.local.dao.SaveDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LocalModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): WantedDatabase =
        Room.databaseBuilder(context, WantedDatabase::class.java, "wanted.db").build()

    @Singleton
    @Provides
    fun provideSaveDao(database: WantedDatabase): SaveDao = database.saveDao()
}
