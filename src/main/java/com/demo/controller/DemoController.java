package com.demo.controller;

import com.demo.pojo.Demo;
import com.demo.service.DemoService;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Title:
 * Description:
 * Copyright: 2019 北京拓尔思信息技术股份有限公司 版权所有.保留所有权
 * Company:北京拓尔思信息技术股份有限公司(TRS)
 * Project: JavaEE
 * Author: jianghaotian
 * Create Time:2019/1/16 18:09
 */
@Controller
public class DemoController {
    @Autowired
    DemoService demoService;

    @RequestMapping("/listDemo")
    public ModelAndView listDemo(){
        ModelAndView mav = new ModelAndView();
        List<Demo> cs= demoService.listDemo();

        // 放入转发参数
        mav.addObject("cs", cs);
        // 放入jsp路径
        mav.setViewName("demo");
        return mav;
    }

    @RequestMapping("/uploadImage")
    public String uploadImage(HttpServletRequest request,MultipartFile file, Model model) throws IOException {
        String filePath = request.getServletContext().getRealPath("/image/");
        String fileName = RandomUtils.nextInt()+"."+file.getContentType().substring(file.getContentType().indexOf("/")+1);
        File imageFile = new File(filePath+fileName);
        imageFile.getParentFile().mkdirs();
        file.transferTo(imageFile);
        model.addAttribute("src",fileName);
        return "uploadImage";
    }

    @RequestMapping("/upload")
    public String upload() throws IOException {
        return "uploadImage";
    }
}
