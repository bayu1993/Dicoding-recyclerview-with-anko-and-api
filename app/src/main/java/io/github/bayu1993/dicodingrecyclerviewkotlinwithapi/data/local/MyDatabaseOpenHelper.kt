package io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import io.github.bayu1993.dicodingrecyclerviewkotlinwithapi.data.local.model.ClubFavorite
import org.jetbrains.anko.db.*

/**
 * Created by Bayu teguh pamuji on 9/9/18.
 * email : bayuteguhpamuji@gmail.com.
 */

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteClub.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null
        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
                ClubFavorite.TABLE_FAVORITE, true,
                ClubFavorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                ClubFavorite.ID_CLUB to TEXT + UNIQUE,
                ClubFavorite.NAME_CLUB to TEXT,
                ClubFavorite.BADGE_CLUB to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(ClubFavorite.TABLE_FAVORITE, true)
    }

}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)