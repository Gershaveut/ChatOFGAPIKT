package com.gershaveut.coapikt

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

data class Message(var text: String, var messageType: MessageType? = MessageType.Message) {
	var argument: String? = null
	var color: Int? = null
	
	override fun toString(): String {
		return gson.toJson(this, type)
	}
	
	companion object {
		val gson: Gson = GsonBuilder().registerTypeAdapter(Message::class.java, MessageDeserializer()).create()
		val type: Type = object : TypeToken<Message>() {}.type
		
		fun parseMessage(text: String): Message {
			return try {
				return gson.fromJson(text, type)
			} catch (_: Exception) {
				Message(text)
			}
		}
	}
	
	class MessageDeserializer : JsonDeserializer<Message> {
		override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Message {
			val jsonObject = json.asJsonObject
			
			val messageDemo = Message("")
			
			val text = jsonObject.get("text").asString
			val messageType = jsonObject.get("messageType")?.asString ?: messageDemo.messageType.toString()
			val argument = jsonObject.get("argument")?.asString
			val color = jsonObject.get("color")?.asInt
			
			return Message(text, MessageType.valueOf(messageType)).apply {
				this.argument = argument; this.color = color
			}
		}
	}
}