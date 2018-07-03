package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mrt on 2018/6/23.
 */
public interface CmsPageControllerApi {
    final String API_PRE = "/cms/page";
    //此注解是为了修改接口工程中页面查询接口，添加swagger注解
    @GetMapping(API_PRE+"/list/{page}/{size}")
    @ApiOperation("分页查询页面列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value="页码",required = true,paramType = "path",dataType = "integer"),
            @ApiImplicitParam(name="size",value = "每页记录数",required = true,paramType = "path",dataType = "integer")
    })
    //分页查询页面
    public QueryResponseResult findList(@PathVariable("page") int page,@PathVariable("size") int size,QueryPageRequest queryPageRequest);

    //添加页面
    @PostMapping(API_PRE+"/add")
    @ApiOperation("添加页面")
    public CmsPageResult add(@RequestBody CmsPage cmsPage);

    //修改之前的回显操作
    @ApiOperation("通过id查询页面")
    @GetMapping(API_PRE+"/get/{id}")
    public CmsPageResult findById(@PathVariable("id") String id);

    //修改操作
    @ApiOperation("修改页面")
    @PutMapping(API_PRE+"/edit/{id}")//这里使用put方法，http方法中put表示更新
    public CmsPageResult edit(@PathVariable("id") String id ,@RequestBody CmsPage cmsPage);

    //删除操作


    @ApiOperation("删除页面")
    @DeleteMapping(API_PRE+"/del/{id}")
    public ResponseResult del(@PathVariable("id") String id);


}
