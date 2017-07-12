package com.example.demo;

import com.example.Application;
import com.example.mq.Sender;
import com.example.util.AsyncTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Future;


@RunWith(SpringRunner.class)
@SpringBootTest(classes= Application.class)
public class ApplicationAsyncTests {

	@Autowired
	private AsyncTask task;

	@Test
	public void test() throws Exception {
		task.doTaskOne();
		task.doTaskTwo();
		task.doTaskThree();
	}

	@Test
	public void testCallback() throws Exception {
		long start = System.currentTimeMillis();
		Future<String> task1 = task.doCallbackTaskOne();
		Future<String> task2 = task.doCallbackTaskTwo();
		Future<String> task3 = task.doCallbackTaskThree();
		while(true) {
			if(task1.isDone() && task2.isDone() && task3.isDone()) {
				// 三个任务都调用完成，退出循环等待
				break;
			}
			Thread.sleep(1000);
		}
		long end = System.currentTimeMillis();
		System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
	}

}