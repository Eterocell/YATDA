package com.eterocell.yatda.core.database.util

import androidx.room.TypeConverter
import kotlin.uuid.Uuid

class UuidConverter {
    @TypeConverter
    fun fromUuid(uuid: Uuid): ByteArray = uuid.toByteArray()

    @TypeConverter
    fun toUuid(bytes: ByteArray): Uuid = Uuid.fromByteArray(bytes)
}
