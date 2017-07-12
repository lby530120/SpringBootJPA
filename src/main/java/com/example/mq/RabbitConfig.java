package com.example.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class RabbitConfig {
    public static final String QU_P2P_HELLO = "hello";
    public static final String EX_P2P_DIRECT = "ex_hello_direct";

    public static final String QU_STREAM_AS = "qu_stream_as";
    public static final String QU_STREAM_SS = "qu_stream_ss";
    public static final String EX_STREAM_TOPIC = "ex_stream_topic";

    public static final String EX_FANOUT_UPDCONFIG = "fanoutExchange";

    /**
     * directExchange
     * 其实就是p2p的模式，可以不配置directExchange,binding, 只配置queue就行了。
     * Sender往queue里发消息，Receiver监听这个queue并处理消息。
     */
    @Bean
    public Queue helloQueue() {
        return new Queue(QU_P2P_HELLO);
    }

   /* @Bean
    DirectExchange directExchange(){
        return new DirectExchange(EX_P2P_DIRECT, true, false);
    }

    //绑定
    @Bean
    public Binding bindingHello() {
        return BindingBuilder.bind(helloQueue()).to(directExchange()).with("key1");
    }*/


    /**topicExchange
     * 共声明了2个队列，分别是 qu_stream_as，qu_stream_ss，
     * 交换器类型为 ex_stream_topic,
     * 交换器与 qu_stream_as，qu_stream_ss 队列分别绑定。
     */

    //声明队列
    @Bean
    public Queue queue1() {
        return new Queue(QU_STREAM_AS, true); // true表示持久化该队列
    }

    @Bean
    public Queue queue2() {
        return new Queue(QU_STREAM_SS, true);
    }

    //声明交互器
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(EX_STREAM_TOPIC, true, false);
    }

    //绑定
    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(queue1()).to(topicExchange()).with("key.1");
    }

    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(queue2()).to(topicExchange()).with("key.#");
    }


}