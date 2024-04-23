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
				val splitText = text.split(':')
				val messageType = MessageType.valueOf(splitText[0])
				
				Message(if (messageType.haveArguments) splitText[1] else text.substring(text.indexOf(':') + 1), messageType).apply {
					if (messageType.haveArguments && splitText.count() > 2)
						arguments = splitText[2]
				}
			} catch (_: Exception) {
				Message(text)
			}
		}
	}
}
