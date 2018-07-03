package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.*;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 * @create 2018-06-23 12:16
 **/
@Service
public class CmsPageService {
    @Autowired
    CmsPageRepository cmsPageRepository;

    //分页查询cmspage
    public QueryResponseResult findList(int page,int size,QueryPageRequest queryPageRequest){

        //首先需要思考  页面传入page 和 size 需要进行逻辑判断
        if(page<=0){
            page=1;//防止页面传来负值
        }
        //由于页面给用户访问  用户认为第一页page为1 但是mongodb接口中page从0开始
        page=page-1;
        if(size<=0){
            size=20;//默认页面显示条数为20
        }
        //开始进行分页查询操作，由于是分页查询 需要传入page对象  so开始构建page对象
        Pageable pageable = new PageRequest(page, size);
        //构建分页查询的查询条件 条件匹配器 默认是精确匹配
        ExampleMatcher example = ExampleMatcher.matching().withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        //条件值对象
        CmsPage cmsPage = new CmsPage();

        //添加查询条件  构建查询条件
        if(StringUtils.isNotEmpty(queryPageRequest.getSiteId())){
                cmsPage.setSiteId(queryPageRequest.getSiteId());
        }

        if(StringUtils.isNotEmpty(queryPageRequest.getPageAliase())){
            cmsPage.setPageAliase(queryPageRequest.getPageAliase());
        }

        //将查询条件构建
        Example<CmsPage> cmsPageExample = Example.of(cmsPage, example);

        //调用dao 执行查询所有方法
        Page<CmsPage> all = cmsPageRepository.findAll(cmsPageExample, pageable);
        //数据列表集合
        List<CmsPage> content = all.getContent();
        //总记录数
        long totalElements = all.getTotalElements();
        //定义返回结果对象
        QueryResult queryResult = new QueryResult();
        queryResult.setList(content);
        queryResult.setTotal(totalElements);
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return queryResponseResult;
    }

    //添加页面

    public CmsPageResult add(CmsPage cmsPage){
        //首先检验页面是否存在(可预知的异常信息处理)
        CmsPage cmsPage1 = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(),cmsPage.getSiteId(),cmsPage.getPageWebPath());
        if(cmsPage1!=null){
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_EXISTSNAME);
        }
            //防止出现意外，将主键ID 置为null
            cmsPage.setPageId(null);//添加页面主键由SpringData 自动生成
            CmsPage save = cmsPageRepository.save(cmsPage);
            //返回结果
            CmsPageResult cmsPageResult = new CmsPageResult(CommonCode.SUCCESS, save);
            return cmsPageResult;
    }

    //修改前的数据回显操作

    public CmsPageResult findById(String id){
        CmsPage one = cmsPageRepository.findOne(id);
        if(one!=null){
            //返回成功
            return new CmsPageResult(CommonCode.SUCCESS,one);
        }
        //返回失败
        return new CmsPageResult(CommonCode.FAIL,one);
    }

    //修改操作
    public  CmsPageResult update(String id,CmsPage cmsPage){
        //首先进行根据ID查询出之前实体 之后根据实体修改操作
        CmsPage one = cmsPageRepository.findOne(id);
        if(one!=null){
            //进行更新操作
        //更新模板id
            one.setTemplateId(cmsPage.getTemplateId());
        //更新所属站点
            one.setSiteId(cmsPage.getSiteId());
        //更新页面别名
            one.setPageAliase(cmsPage.getPageAliase());
        //更新页面名称
            one.setPageName(cmsPage.getPageName());
        //更新访问路径
            one.setPageWebPath(cmsPage.getPageWebPath());
        //更新物理路径
            one.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
        //执行更新
            CmsPage save = cmsPageRepository.save(one);
            return new CmsPageResult(CommonCode.SUCCESS,save);
        }
        return  new CmsPageResult(CommonCode.FAIL,null);
    }


    //删除操作
    public ResponseResult del(String id){
        CmsPage one = cmsPageRepository.findOne(id);
        if(one!=null){
            cmsPageRepository.delete(id);
            return  new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }





}
