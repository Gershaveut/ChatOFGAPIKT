package com.gershaveut.coapikt

enum class MessageType(val haveArguments: Boolean) {
	Message(false),
	Join(false),
	Leave(false),
	Kick(true),
	Error(false)
}