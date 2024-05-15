package com.gershaveut.coapikt;

import org.junit.Test;

class MessageTest {
	@Test
	fun parseMessageTest() {
		val message = Message.parseMessage("{\"text\":\"Test\", \"color\":\"5675\"}")
		
		println(message)
	}
}
