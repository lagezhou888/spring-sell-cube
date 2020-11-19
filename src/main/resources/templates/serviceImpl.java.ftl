package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    /**
    * <p>
    * Field ${table.mapperName}: 引用搜索Mapper
    * </p>
    */
    @Autowired
    private ${table.mapperName} ${entity?uncap_first}Mapper;

   /**
   * <p>Description: 搜索${table.comment!}</p>
   * @param ${entity?uncap_first} ${table.comment!}
   * @return ${table.comment!}全部
   */
   @Override
   public List<${entity}> selectGetAll(@Param("et") ${entity} ${entity?uncap_first}) {
        return ${entity?uncap_first}Mapper.selectGetAll(${entity?uncap_first});
   }

    @Override
    public IPage<${entity}> queryPage(Page page, @Param("et") ${entity} ${entity?uncap_first}) {
        return ${entity?uncap_first}Mapper.queryPage(page,${entity?uncap_first});
        }
    }
</#if>
