package com.xuecheng.manage_cms.web.controller;

import com.xuecheng.api.cms.CmsPageControllerApi;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.service.CmsPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 * @version 1.0
 * @create 2018-06-23 12:18
 **/
@RestController
public class CmsPageController implements CmsPageControllerApi {
    @Autowired
    CmsPageService cmsPageService;
    @Override
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size, QueryPageRequest queryPageRequest) {
        return cmsPageService.findList(page,size,queryPageRequest);
    }

    @Override
    public CmsPageResult add(@RequestBody CmsPage cmsPage) {
        return cmsPageService.add(cmsPage);
    }

    @Override
    public CmsPageResult findById(@PathVariable("id") String id) {
        return cmsPageService.findById(id);
    }

    @Override
    public CmsPageResult edit(@PathVariable("id") String id,@RequestBody  CmsPage cmsPage) {
        return cmsPageService.update(id,cmsPage);
    }

    @Override
    public ResponseResult del(@PathVariable("id") String id) {
        return cmsPageService.del(id);
    }
}
