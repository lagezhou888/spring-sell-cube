package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {
     /**
     * <p>Description: 搜索${table.comment!}</p>
     * @param ${entity?uncap_first} ${table.comment!}
     * @return ${table.comment!}查询全部
     */
     List<${entity}> selectGetAll(@Param("et") ${entity} ${entity?uncap_first});

     IPage<${entity}>  queryPage(Page page, @Param("et") ${entity} ${entity?uncap_first});

    }
</#if>
