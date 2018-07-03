package com.xuecheng.manage_course;

import com.xuecheng.framework.domain.course.ext.CategoryNode;
import com.xuecheng.framework.domain.course.ext.CourseInfo;
import com.xuecheng.framework.domain.course.request.CourseListRequest;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.manage_course.dao.CategoryMapper;
import com.xuecheng.manage_course.dao.CourseBaseMapper;
import com.xuecheng.manage_course.dao.CourseBaseRepository;
import com.xuecheng.manage_course.service.CourseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestMapper {

    @Autowired
    CourseBaseRepository courseBaseRepository;
    @Autowired
    CourseBaseMapper courseBaseMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    CourseService courseService;


    @Test
    public void testFindNode(){
        CategoryNode categoryNode = categoryMapper.selectList();
        System.out.println(categoryNode);
    }

    @Test
    public void testService(){

        CategoryNode categoryList = courseService.findCategoryList();

        System.out.println(categoryList);
        CourseListRequest courseListRequest = new CourseListRequest();
        QueryResponseResult<CourseInfo> courseList = courseService.findCourseList(1, 5, courseListRequest);
        System.out.println(courseList);

    }


}
