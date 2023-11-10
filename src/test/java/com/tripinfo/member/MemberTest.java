package com.tripinfo.member;

import java.sql.SQLException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tripinfo.member.model.mapper.MemberMapper;

@SpringBootTest
public class MemberTest {
	
	@Autowired
	private MemberMapper mapper;
	
	@Test
	public void testIdCheck() throws SQLException {
		String userId = "ssafy";
		String result = mapper.idCheck(userId);
		Assertions.assertThat(result).isEqualTo(userId);
	}
}
