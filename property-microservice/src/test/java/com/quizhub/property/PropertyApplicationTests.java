package com.quizhub.property;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PropertyApplicationTests {

	@Test
	void contextLoads() {
		Object nothing = null;
		assertThat(nothing).isNull();
	}

}
