package org.perscholas.sdbank;

import org.junit.jupiter.api.Test;
import org.perscholas.sdbank.dao.CustomersRepoI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class SdbankApplicationTests {

	@Autowired
	private CustomersRepoI customersRepoI;

	@Test
	void contextLoads() {

		assertThat(customersRepoI.findById(1).get().getFirstName()).isEqualTo("Test");
	}

}
