package co.kr.hoyaho.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.kr.hoyaho.data.local.entity.SaveEntity

@Dao
interface SaveDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSavedNews(saveEntity: SaveEntity)

    @Query("SELECT * FROM SaveEntity")
    suspend fun getSavedNews(): List<SaveEntity>
}
