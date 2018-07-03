package com.xuecheng.manage_course.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xuecheng.framework.domain.course.ext.CategoryNode;
import com.xuecheng.framework.domain.course.ext.CourseInfo;
import com.xuecheng.framework.domain.course.request.CourseListRequest;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage_course.dao.CategoryMapper;
import com.xuecheng.manage_course.dao.CourseBaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    CourseBaseMapper courseBaseMapper;

    @Autowired
    CategoryMapper categoryMapper;

    //查询课程列表
    public QueryResponseResult<CourseInfo>findCourseList(int page, int size, CourseListRequest courseListRequest){
        if(page<0){
            page = 1;
        }
        if(size<0){
            size = 10;
        }
        //调用dao
        //设置分页参数
        PageHelper.startPage(page,size);
        Page<CourseInfo> courseListPage = courseBaseMapper.findCourseListPage(courseListRequest);
        //列表
        List<CourseInfo> result = courseListPage.getResult();
        //总数
        long total = courseListPage.getTotal();
        QueryResult queryResult = new QueryResult();
        queryResult.setList(result);
        queryResult.setTotal(total);
        return new QueryResponseResult(CommonCode.SUCCESS,queryResult);
    }

    //分类查询
    public CategoryNode findCategoryList(){
        CategoryNode categoryNode = categoryMapper.selectList();
        return categoryNode;
    }
}
