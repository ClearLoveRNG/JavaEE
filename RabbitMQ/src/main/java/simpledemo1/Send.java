package simpledemo1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Title:简单队列 发送者
 * Description:
 * Copyright: 2019 北京拓尔思信息技术股份有限公司 版权所有.保留所有权
 * Company:北京拓尔思信息技术股份有限公司(TRS)
 * Project: JavaEE
 * Author: jianghaotian
 * Create Time:2019/3/11 16:23
 */
/**
 * 简单队列：采用发送信息到队列然后队列把信息在发送到消费者
 */


/**
 * ConnectionFactory、Connection、Channel都是RabbitMQ对外提供的API中最基本的对象。
 * <p>
 * 　　Connection就是建立一个TCP连接，生产者和消费者的都是通过TCP的连接到RabbitMQ Server中的,是RabbitMQ的socket链接，它封装了socket协议相关部分逻辑
 * <p>
 * ConnectionFactory为Connection的制造工厂。
 * <p>
 * 　　Channel是我们与RabbitMQ打交道的最重要的一个接口，我们大部分的业务操作是在Channel这个接口中完成的，包括定义Queue、定义Exchange、绑定Queue与Exchange、发布消息等。
 * 是一个虚拟连接，建立在上面TCP连接的基础上，数据流动都是通过Channel来进行的。为什么不是直接建立在TCP的基础上进行数据流动呢？
 * 如果建立在TCP的基础上进行数据流动，建立和关闭TCP连接有代价。频繁的建立关闭TCP连接对于系统的性能有很大的影响，而且TCP的连接数也有限制，这也限制了系统处理高并发的能力。但是，在TCP连接中建立Channel是没有上述代价的。
 */
public class Send {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        /*
        连接可以抽象为socket连接，为我们维护协议版本信息和协议证书等。这里我们连接
        上了本机的消息服务器实体（localhost）。如果我们想连接其它主机上的RabbitMQ服务，
        只需要修改一下主机名或是IP就可以了
        */
        //创建链接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        //创建新连接
        Connection connection = factory.newConnection();
        //创建一个通道
        Channel channel = connection.createChannel();

        /*接下创建channel（信道），这是绝大多数API都能用到的。为了发送消息，你必须要声明一个消息消息队列，然后向该队列里推送消息*/
        //queueDeclare第一个参数表示队列名称、第二个参数为是否持久化（true表示是，队列将在服务器重启时生存）、第三个参数为是否是独占队列（创建者可以使用的私有队列，断开后自动删除）、第四个参数为当所有消费者客户端连接断开时是否自动删除队列、第五个参数为队列的其他参数
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "hello world!";
        //发送消息到队列中
        //basicPublish第一个参数为交换机名称、第二个参数为队列映射的路由key、第三个参数为消息的其他属性、第四个参数为发送信息的主体
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println("发送信息:" + message);

        channel.close();
        connection.close();

    }

}
