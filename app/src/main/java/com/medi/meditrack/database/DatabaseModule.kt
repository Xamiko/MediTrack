package com.medi.meditrack.database

import android.app.Application
import androidx.room.Room
import com.medi.meditrack.database.local.Database
import com.medi.meditrack.database.local.MedicationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        app: Application
    ): Database {
        return Room.databaseBuilder(
            app,
            Database::class.java,
            "medication-db"
        ).build()
    }

    @Provides
    fun provideMedicationDao(db: Database): MedicationDao {
        return db.medicationDao()
    }
}
