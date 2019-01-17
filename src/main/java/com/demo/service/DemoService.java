package com.demo.service;

import com.demo.dao.DemoDao;
import com.demo.pojo.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Title:
 * Description:
 * Copyright: 2019 北京拓尔思信息技术股份有限公司 版权所有.保留所有权
 * Company:北京拓尔思信息技术股份有限公司(TRS)
 * Project: JavaEE
 * Author: jianghaotian
 * Create Time:2019/1/16 18:08
 */
@Service
public class DemoService {

    @Autowired
    DemoDao demoDao;

    public List<Demo> listDemo(){
        return demoDao.listDemo();
    }
}
