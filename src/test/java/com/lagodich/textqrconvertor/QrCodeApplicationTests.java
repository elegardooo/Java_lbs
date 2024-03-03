package com.lagodich.textqrconvertor;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class QrCodeApplicationTests {

	@Test
	void contextLoads() {
		int result = 1 + 2;
		assertEquals(3, result, "1 + 2 should equal 3");
	}

}
