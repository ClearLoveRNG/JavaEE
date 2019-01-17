package com.demo.dao;

import com.demo.pojo.Demo;

import java.util.List;

/**
 * Title:
 * Description:
 * Copyright: 2019 北京拓尔思信息技术股份有限公司 版权所有.保留所有权
 * Company:北京拓尔思信息技术股份有限公司(TRS)
 * Project: JavaEE
 * Author: jianghaotian
 * Create Time:2019/1/16 18:05
 */
public interface DemoDao {
    List<Demo> listDemo();
}
