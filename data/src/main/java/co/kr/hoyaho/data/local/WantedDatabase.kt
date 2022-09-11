package co.kr.hoyaho.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import co.kr.hoyaho.data.local.dao.SaveDao
import co.kr.hoyaho.data.local.entity.SaveEntity

@Database(entities = [SaveEntity::class], version = 1, exportSchema = false)
abstract class WantedDatabase : RoomDatabase() {
    abstract fun saveDao(): SaveDao
}
