package com.rachel.projetointegrador.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rachel.projetointegrador.data.dao.FavoriteMovieDao
import com.rachel.projetointegrador.data.model.FavoriteMovie

@Database(entities = [FavoriteMovie::class], version = 1)
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
                        .allowMainThreadQueries()
                        .build()
                }
            }

            return INSTANCE
        }
    }
}