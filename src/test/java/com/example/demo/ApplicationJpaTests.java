package com.example.demo;

import com.example.Application;
import com.example.bean.JpaUser;
import com.example.dao.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes= Application.class)
public class ApplicationJpaTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CacheManager cacheManager;



	@Test
	//@Transactional
	@Rollback
	public void test() throws Exception {
		// 测试findAll, 查询所有记录
		//Assert.assertEquals(1031015, userRepository.findAll().size());

		// 测试findByName, 查询姓名为FFF的User
		Assert.assertEquals("0201", userRepository.findByUserId(2l).getUserKind());

		// 测试findUser, 查询姓名为FFF的User
		Assert.assertEquals(1, userRepository.findUser(20L).getUserStatus().intValue());


		// 测试删除姓名为AAA的User
		//userRepository.delete(userRepository.findByName("AAA"));

		// 测试findAll, 查询所有记录, 验证上面的删除是否成功
		//Assert.assertEquals(9, userRepository.findAll().size());

	}


	@Test
	public void testCache() throws Exception {
		long userId = 2l;

		cacheManager.getCache("appUsers");

		System.out.println("First query-->");
		JpaUser u1 = userRepository.findByUserId(userId);
		System.out.println("First result:" + u1!=null ? u1.getUserKind():"null");

		//update data and cache
		System.out.println("update jpaUser and cache-->");
		u1.setUserKind("0201");
		userRepository.save(u1);

		System.out.println("Second query-->");
		JpaUser u2 = userRepository.findByUserId(userId);
		System.out.println("Second result:" + u2!=null ? u2.getUserKind():"null");
		//delete data and cache
		//System.out.println("delete jpaUser-->");
		//userRepository.delete(u2);

		System.out.println("Third query-->");
		JpaUser u3 = userRepository.findByUserId(userId);
		System.out.println("Third result:" + u3 !=null ? u3.getUserKind():"null");

	}
 
}