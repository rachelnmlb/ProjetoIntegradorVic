package com.rachel.projetointegrador.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rachel.projetointegrador.data.dao.FavoriteMovieDao
import com.rachel.projetointegrador.data.model.entity.FavoriteMovie
import com.rachel.projetointegrador.data.model.entity.Genre

@Database(entities = [FavoriteMovie::class, Genre::class], version = 2)
abstract class FavoriteMovieDatabase : RoomDatabase() {

    abstract fun favoriteMovieDao() : FavoriteMovieDao

    companion object {

        private lateinit var INSTANCE: FavoriteMovieDatabase

        fun getDatabase(context: Context): FavoriteMovieDatabase {

            synchronized(FavoriteMovieDatabase::class) {

                if (!::INSTANCE.isInitialized) {

                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FavoriteMovieDatabase::class.java,
                        "favorite-movie-database"
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
            }

            return INSTANCE
        }
    }
}