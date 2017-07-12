package com.example.demo;

import com.example.Application;
import com.example.bean.JpaUser;
import com.example.dao.UserRepository;
import com.example.mq.RabbitConfig;
import com.example.mq.Sender;
import com.example.mq.TopicSender;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest(classes= Application.class)
public class ApplicationMQTests {

	@Autowired
	private Sender sender;

	@Autowired
	private TopicSender topicSender;

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Test
	public void hello() throws Exception {
		for(int i=0 ; i<100; i++) {
			sender.send("mytest_"+i +"_");
		}
		Thread.sleep(2000);//留点时间让receiver收完
	}

	@Test
	public void sendTest() throws Exception {
		for(int i=0 ; i<10; i++) {
			String msg = new Date().toString();
			topicSender.send(msg);
			Thread.sleep(100);
		}
	}

	@Test
	public void fanoutTest() throws Exception{
		for(int i=0 ; i<100000; i++) {
			String context = i +"_"+ new Date();
			System.out.println("---Sender need update configInfo : " + context);
			this.rabbitTemplate.convertAndSend(RabbitConfig.EX_FANOUT_UPDCONFIG,"updateConfig", context);
			//Thread.sleep(100);
		}
	}

}