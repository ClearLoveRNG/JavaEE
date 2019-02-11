package com.dubbo.consumer;

import com.dubbo.api.IDubboDemo;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Title:
 * Description:
 * Copyright: 2019 北京拓尔思信息技术股份有限公司 版权所有.保留所有权
 * Company:北京拓尔思信息技术股份有限公司(TRS)
 * Project: JavaEE
 * Author: jianghaotian
 * Create Time:2019/2/11 15:26
 */
public class DubboConsumer {
    public static void main(String[] args) {
        //测试常规服务
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-consumer.xml");
        context.start();
        System.out.println("consumer start");
        IDubboDemo dubboDemo = context.getBean(IDubboDemo.class);
        System.out.println(dubboDemo);
        System.out.println(dubboDemo.dubboDemo());
    }
}
