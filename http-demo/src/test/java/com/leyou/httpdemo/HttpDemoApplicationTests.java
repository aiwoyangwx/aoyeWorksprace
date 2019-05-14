package com.leyou.httpdemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leyou.httpdemo.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HttpDemoApplication.class)
public class HttpDemoApplicationTests {

	/*private ObjectMapper mapper = new ObjectMapper();
	@Test
	public void testJson() throws Exception{
		User user = new User();
		user.setId(8L);
		user.setAge(21);
		user.setName("柳岩");
		user.setUserName("liuyan");
		// 序列化
		String json = mapper.writeValueAsString(user);
		System.out.println("json = " + json);

		// 反序列化，接收两个参数：json数据，反序列化的目标类字节码
		User result = mapper.readValue(json, User.class);
		System.out.println("result = " + result);
	}*/

	// json处理工具
	/*private ObjectMapper mapper = new ObjectMapper();
	@Test
	public void testJson() throws Exception {
		User user = new User();
		user.setId(8L);
		user.setAge(21);
		user.setName("柳岩");
		user.setUserName("liuyan");

		// 序列化,得到对象集合的json字符串
		String json = mapper.writeValueAsString(Arrays.asList(user,user));

		// 反序列化，接收两个参数：json数据，反序列化的目标类字节码
		List<User> users = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, User.class));
		for (User u : users) {
			System.out.println("u = " + u);
		}
	}*/

	// json处理工具
	private ObjectMapper mapper = new ObjectMapper();
	@Test
	public void testJson() throws Exception {
		User user = new User();
		user.setId(8L);
		user.setAge(21);
		user.setName("柳岩");
		user.setUserName("liuyan");
		// 序列化,得到对象集合的json字符串
		String json = mapper.writeValueAsString(Arrays.asList(user, user));
		// 反序列化，接收两个参数：json数据，反序列化的目标类字节码
		List<User> users = mapper.readValue(json, new TypeReference<List<User>>(){});
		for (User u : users) {
			System.out.println("u = " + u);
		}
	}








	@Autowired
	private RestTemplate restTemplate;
	@Test
	public void httpGet() {
		User user = this.restTemplate.getForObject("http://localhost/hello", User.class);
		System.out.println(user);
	}
}
