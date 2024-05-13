package com.gershaveut.coapikt

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.awt.Color

data class Message(var text: String, var messageType: MessageType = MessageType.Message) {
	var argument: String? = null
	var color: Color? = null
	
	override fun toString(): String {
		return Gson().toJson(this, type)
	}
	
	companion object {
		val type = object : TypeToken<Message>() {}.type
		
		fun parseMessage(text: String): Message {
			return try {
				return Gson().fromJson(text, type)
			} catch (_: Exception) {
				Message(text)
			}
		}
	}
}
