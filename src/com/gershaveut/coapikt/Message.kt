package com.gershaveut.coapikt

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

data class Message(var text: String, var messageType: MessageType? = MessageType.Message) : JsonDeserializer<Message> {
	var argument: String? = null
	var color: Array<Int> = arrayOf(0, 0, 0)
	
	override fun toString(): String {
		return gson.toJson(this, type)
	}
	
	companion object {
		val gson = GsonBuilder().registerTypeAdapter(Message::class.java, this).create()
		val type: Type = object : TypeToken<Message>() {}.type
		
		fun parseMessage(text: String): Message {
			return try {
				return gson.fromJson(text, type)
			} catch (_: Exception) {
				Message(text)
			}
		}
	}
	
	override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Message {
		val jsonObject = json.asJsonObject
		
		val text = jsonObject.get("text").asString
		val messageType = jsonObject.get("messageType")?.asString ?: MessageType.Message.toString()
		val argument = jsonObject.get("argument")?.asString
		val color = jsonObject.get("color")?.asJsonArray?.map { it.asInt }?.toTypedArray() ?: arrayOf(0, 0, 0)
		
		return Message(text, MessageType.valueOf(messageType)).apply { this.argument = argument; this.color = color }
	}
}