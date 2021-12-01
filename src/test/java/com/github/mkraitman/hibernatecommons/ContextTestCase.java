package com.github.mkraitman.hibernatecommons;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = MainClass.class)
@RunWith(SpringRunner.class)
public class ContextTestCase {

	@Test
	public void run() {
		assertTrue(true);
	}
}
