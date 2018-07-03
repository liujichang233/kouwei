package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.response.CmsConfigResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Api(value="cms配置管理接口",description="cms配置管理接口，提供数据模型的管理、查询接口")
public interface CmsConfigControllerApi {

    final  String API_PRE = "/cms/config";

    @ApiOperation("根据ID查询数据模型信息")
    @GetMapping(API_PRE+"/getmodel/{id}")
    public CmsConfigResult getmodel(@PathVariable("id") String id);

}
