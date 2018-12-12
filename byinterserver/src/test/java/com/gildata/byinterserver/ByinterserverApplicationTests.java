package com.gildata.byinterserver;

import com.gildata.byinterserver.domain.UsrBYZQSJYB;
import com.gildata.byinterserver.service.UsrBYZQSJYBService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ByinterserverApplicationTests {


	@Autowired
	@Qualifier("jypConfigJdbcTemplate")
	private JdbcTemplate jypConfigJdbcTemplate;

	@Autowired
	private UsrBYZQSJYBService usrBYZQSJYBService;


	@Test
	public void sjybm() {
		Map<String,String> data = new HashMap<>();
		List<Map<String,Object>> res =  jypConfigJdbcTemplate.queryForList("SELECT ZLMC,SJYBM FROM usrYBZXSJYB");
		for(Map<String,Object> map : res){
			data.put(map.get("ZLMC").toString(),map.get("SJYBM").toString());
		}
		System.out.println(data.size());
	}


	@Test
	public void dm() {
		Map<String,String> data = new HashMap<>();
		List<Map<String,Object>> res =  jypConfigJdbcTemplate.queryForList("SELECT DM,CLMS FROM usrZXCLB WHERE LB = 4");
		for(Map<String,Object> map : res){
			data.put(map.get("DM").toString(),map.get("CLMS").toString());
		}
		System.out.println(data.size());
	}



	@Test
	public void getAll() {
		Map<String,String> data = new HashMap<>();
		List<Map<String,Object>> res =  jypConfigJdbcTemplate.queryForList("select MTCC,SJYBM,LMLB,ZLMC,ZYLJ from usrYBZXSJYB");
		for(Map<String,Object> map : res){
//			Integer MTCC = map.get("MTCC") == null ? null : Integer.valueOf(map.get("MTCC").toString());
//			Integer SJYBM = map.get("SJYBM") == null ? null : Integer.valueOf(map.get("SJYBM").toString());
//			Integer LMLB =  map.get("LMLB") == null ? null : Integer.valueOf(map.get("LMLB").toString());
			String MTCC = map.get("MTCC") == null ? "" : map.get("MTCC").toString();
			String SJYBM = map.get("SJYBM") == null ? "" : map.get("SJYBM").toString();
			String LMLB = map.get("LMLB") == null ? "" : map.get("LMLB").toString();
			String ZLMC = map.get("ZLMC").toString();
			String ZYLJ = map.get("ZYLJ").toString();
			System.out.println(MTCC + "^^" + SJYBM + "^^" + LMLB + "^^" + ZLMC + "^^" + ZYLJ);
			data.put( ZLMC + "^^" + ZYLJ,MTCC + "^^" + SJYBM + "^^" + LMLB);
		}
		System.out.println(data.size());
	}


	@Test
	public void getData(){
		List<UsrBYZQSJYB> data = usrBYZQSJYBService.findByZYLJ("iofdfd");
		for(UsrBYZQSJYB usrBYZQSJYB : data){
			System.out.println(usrBYZQSJYB);
		}
	}

}
