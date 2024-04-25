package com.bci.api.user;

import com.bci.api.user.config.ApplicationConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationConfig.class)
class UserApiApplicationTests {
	@Autowired
	ApplicationConfig applicationConfig;
	@Test
	void modelMapperTest() {
		ModelMapper mapper = applicationConfig.modelMapper();
		Assertions.assertThat(mapper).isInstanceOf(ModelMapper.class);
	}

}
