package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import java.util.List;

/**
 * <p>
 * ${table.comment!} Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {
      /**
        * <p>Description: 搜索${table.comment!}</p>
        * @param ${entity?uncap_first} ${table.comment!}
        * @return ${table.comment!}全部
      */
      List<${entity}> selectGetAll(@Param("et") ${entity} ${entity?uncap_first});

      IPage<${entity}> queryPage(Page page, @Param("et") ${entity} ${entity?uncap_first});

    }
</#if>
