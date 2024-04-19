package com.gershaveut.coapikt

class Message(var text: String, var messageType: MessageType = MessageType.Message) {
	var arguments: String? = null
	
	override fun equals(other: Any?): Boolean {
		when (other) {
			is MessageType -> return messageType == other
			is String -> return text == other
		}
		
		return super.equals(other)
	}
	
	override fun toString(): String {
		return "$messageType:$text"
	}
	
	override fun hashCode(): Int {
		var result = text.hashCode()
		result = 31 * result + messageType.hashCode()
		return result
	}
	
	companion object {
		fun createMessageFromText(text: String): Message {
			return try {
				val messageType = MessageType.valueOf(text.split(':')[0])
				
				Message(if (messageType.haveArguments) text.substring(text.indexOf(':') + 1) else text.split(':', limit = 2)[1], messageType).apply {
					if (messageType.haveArguments)
						arguments = text.split(':')[2]
				}
			} catch (_: Exception) {
				Message(text)
			}
		}
	}
}
