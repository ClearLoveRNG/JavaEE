package simpledemo1;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Title:简单队列 消费者
 * Description:
 * Copyright: 2019 北京拓尔思信息技术股份有限公司 版权所有.保留所有权
 * Company:北京拓尔思信息技术股份有限公司(TRS)
 * Project: JavaEE
 * Author: jianghaotian
 * Create Time:2019/3/11 16:23
 */
public class Recv {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        /*这里怎么打开连接和信道，以及声明用于接收消息的队列，这些步骤与发送端基本上是一样的*/
        //创建工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        //创建连接
        Connection connection = factory.newConnection();
        //创建通道
        final Channel channel = connection.createChannel();

        /*确保这里的队列是存在的*/
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //定义消费者
        //DefaultConsumer类实现了Consumer接口，通过传入一个频道，
        //告诉服务器我们需要那个频道的消息，如果频道中有消息，就会执行回调函数handleDelivery
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("收到消息:" + message);
                try {
                    //如果出现异常，则下面的自动回复方法(basicConsume)的第二个参数应该设为false，这样保证消息不会在队列中删除
                }catch (Exception e){
                    //强制关闭通道并等待关闭操作完成。在关闭操作中遇到的任何异常都会被静默丢弃。
                    channel.abort();
                }finally {
                    System.out.println("出现异常");
                    //因为现在basicConsume的第二个参数设为false了，所以没有自动答复，所以就要手动调方法(basicAck)进行答复
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            }
        };
        //自动回复队列应答 -- RabbitMQ中的消息确认机制
        //告诉队列，我收到消息了，你可以把消息发送给别人了
        //autoAck是否自动回复，如果为true的话，每次生产者只要发送信息就会从内存中删除，那么如果消费者程序异常退出，那么就无法获取数据，我们当然是不希望出现这样的情况，所以才去手动回复，
        //每当消费者收到并处理信息然后在通知生成者。最后从队列中删除这条信息。如果消费者异常退出，如果还有其他消费者，那么就会把队列中的消息发送给其他消费者，如果没有，等消费者启动时候再次发送。
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
