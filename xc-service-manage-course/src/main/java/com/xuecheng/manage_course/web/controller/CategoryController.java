package com.xuecheng.manage_course.web.controller;

import com.xuecheng.api.cms.CategoryControllerApi;
import com.xuecheng.framework.domain.course.ext.CategoryNode;
import com.xuecheng.manage_course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @version 1.0
 * @create 2018-06-30 11:51
 **/
@RestController
public class CategoryController implements CategoryControllerApi {

    @Autowired
    CourseService service;

    @Override
    public CategoryNode findList() {
        return service.findCategoryList();
    }
}
