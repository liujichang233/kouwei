package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by mrt on 2018/6/23.
 */
public interface CmsPageRepository extends MongoRepository<CmsPage,String> {

    //根据页面名称 站点ID 页面访问路径查询

    CmsPage findByPageNameAndSiteIdAndPageWebPath(String pageName,String siteId,String pageWebPath);


}
