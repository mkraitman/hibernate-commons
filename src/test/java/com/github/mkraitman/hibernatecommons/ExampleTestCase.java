package com.github.mkraitman.hibernatecommons;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.mkraitman.hibernatecommons.business.ExampleBusinessService;

@SpringBootTest(classes = MainClass.class)
@RunWith(SpringRunner.class)
public class ExampleTestCase {

	private ExampleBusinessService exampleBusinessService;

	@Autowired
	public void setExampleBusinessService(ExampleBusinessService exampleBusinessService) {
		this.exampleBusinessService = exampleBusinessService;
	}

	@Test
	@Transactional
	public void doSomething() {
//		exampleBusinessService.todo();
	}

}
