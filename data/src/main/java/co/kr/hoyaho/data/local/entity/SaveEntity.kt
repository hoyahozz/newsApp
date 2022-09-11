package co.kr.hoyaho.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import co.kr.hoyaho.domain.model.News

@Entity
data class SaveEntity(
    @PrimaryKey @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "imgUrl") val imgUrl: String,
    @ColumnInfo(name = "elapsed") val elapsed: String,
    @ColumnInfo(name = "content") val content: String,
)

fun SaveEntity.toNews(): News {
    return News(
        title = this.title,
        author = this.author,
        imgUrl = this.imgUrl,
        elapsed = this.elapsed,
        content = this.content,
    )
}

fun News.toSaveEntity(): SaveEntity {
    return SaveEntity(
        title = this.title,
        author = this.author,
        imgUrl = this.imgUrl,
        elapsed = this.elapsed,
        content = this.content,
    )
}
