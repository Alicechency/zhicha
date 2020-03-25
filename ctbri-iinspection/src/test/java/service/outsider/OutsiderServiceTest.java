package service.outsider;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ctbri.iinspection.service.MapService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-dao.xml" })
public class OutsiderServiceTest {

	@Resource
	MapService mapService;

	@Test
	public void testFindOutSider() {
		System.out.println("");
	}
}
