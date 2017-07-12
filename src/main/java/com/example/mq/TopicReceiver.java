package com.example.mq;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class TopicReceiver {

  @RabbitListener(queues = RabbitConfig.QU_STREAM_AS)
  public String processMessage1(String msg) {
    System.out.println(Thread.currentThread().getName() +
            " 接收到来自"+RabbitConfig.QU_STREAM_AS+"队列的消息：" + msg);
    return msg.toUpperCase();
  }
 
  @RabbitListener(queues = RabbitConfig.QU_STREAM_SS)
  public void processMessage2(String msg) {
    System.out.println(Thread.currentThread().getName() +
            " 接收到来自"+RabbitConfig.QU_STREAM_SS+"队列的消息：" + msg);
  }

  /**fanoutExchange
   * 发送消息到所有队列，场景用于更新配置文件，后台更新配置后发送到fanoutExchange，然后发送到所有订阅的queue,
   * 所以tomcat启动时创建一个匿名队列绑定到fanoutExchange. 启动多个tomcat测试成功。
   */

  @RabbitListener(bindings = @QueueBinding(
          value = @Queue,
          exchange = @Exchange(value = RabbitConfig.EX_FANOUT_UPDCONFIG, type = ExchangeTypes.FANOUT, durable="true", ignoreDeclarationExceptions = "true"),
          key = "updateConfig"))
  public void processMessage3(String msg) {
    System.out.println(Thread.currentThread().getName() +
            " 接收到来自fanout update config队列的消息：" + msg);
  }
}