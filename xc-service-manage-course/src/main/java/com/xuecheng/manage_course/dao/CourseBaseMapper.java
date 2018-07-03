package com.xuecheng.manage_course.dao;

import com.github.pagehelper.Page;
import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.ext.CourseInfo;
import com.xuecheng.framework.domain.course.request.CourseListRequest;
import org.apache.ibatis.annotations.Mapper;
//声明此dao为一个mapper接口 也就是被mybatis框架定义的接口
@Mapper
public interface CourseBaseMapper {
    /**
     * 根据课程ID查询课程信息
     * @param id
     * @return
     */
    public CourseBase findCourseBaseById(String id);

    /**
     * 分页查询课程信息  根据条件查询
     * @return
     */
    public Page<CourseInfo> findCourseListPage(CourseListRequest courseListRequest);


}
