package me.ijachok.librarykotlin

import androidx.datastore.core.Serializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object BooksSerializer :Serializer<Supplier>{
    override val defaultValue: Supplier
        get() = Supplier

    override suspend fun readFrom(input: InputStream): Supplier {
       return try {
            Json.decodeFromString(
                deserializer = Supplier.serializer(),
                string = input.readBytes().decodeToString()
            )
        }catch (e: SerializationException){
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: Supplier, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = Supplier.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}