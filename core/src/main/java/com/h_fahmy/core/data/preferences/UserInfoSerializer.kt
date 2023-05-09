package com.h_fahmy.core.data.preferences

import android.util.Log
import androidx.datastore.core.Serializer
import com.h_fahmy.core.domain.model.UserInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object UserInfoSerializer : Serializer<UserInfo> {
    override val defaultValue: UserInfo
        get() = UserInfo()

    override suspend fun readFrom(input: InputStream): UserInfo {
        return try {
            Json.decodeFromString(
                deserializer = UserInfo.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            Log.e(null, null, e)
            defaultValue
        }
    }

    override suspend fun writeTo(t: UserInfo, output: OutputStream) {
        withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(
                    serializer = UserInfo.serializer(),
                    value = t
                ).encodeToByteArray()
            )
        }
    }
}