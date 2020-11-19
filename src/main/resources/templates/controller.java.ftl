package ${package.Controller};


import ${package.Entity}.${entity};
import ${package.ServiceImpl}.${table.serviceImplName};
import ${package.Mapper}.${table.mapperName};
import com.cgz.util.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import javax.servlet.http.HttpServletRequest;
import io.swagger.annotations.Api;

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
@Api(value = "<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>", description = "${table.comment!}接口")
 <#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>



    /**
        * <p>
        * Description: ${table.comment!}服务
        * </p>
    */
     @Autowired
     private ${table.serviceImplName} ${table.serviceImplName?uncap_first};




        /**
        * <p>
        * Description: ${table.comment!}分页查询
        * </p>
        */
      @ApiOperation(value = "分页查询${table.comment!}接口", notes = "分页${table.comment!}接口")
      @GetMapping(value = "/listPage")
      public Result queryPageList( ${entity} ${entity?uncap_first},
                @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                HttpServletRequest req) {
                Result result = new Result();
                Page<${entity}> page = new Page<${entity}>(pageNo, pageSize);
                            //自定义getAll接口
                IPage<${entity} > pageList=this.${table.serviceImplName?uncap_first}.queryPage(page,${entity?uncap_first});
                 return result.successOk(pageList);
     }






    /**
    *
    * <p>
    * Description: 添加${table.comment!}
    * </p>
    *
    * @param ${entity?uncap_first} 要添加的${table.comment!}，必须包含id
    * @return Result 添加是否成功
    */
     @ApiOperation(value = "新增${table.comment!}接口", notes = "新增${table.comment!}接口")
     @PostMapping()
     public Result insert(@RequestBody ${entity} ${entity?uncap_first}) {
         Result result = new Result();
         Boolean isOk = this.${table.serviceImplName?uncap_first}.save(${entity?uncap_first});
         if (!isOk) {
              return result.successOk(isOk);
         } else {
              return result.successOk(isOk);
         }
     }


    /**
    *
    * <p>
    * Description: 修改${table.comment!}
    * </p>
    *
    * @param ${entity?uncap_first} 要修改的${table.comment!}，必须包含id
    * @return Result 修改是否成功
    */
    @ApiOperation(value = "修改${table.comment!}接口", notes = "修改${table.comment!}接口")
    @PutMapping()
    public Result update(@RequestBody ${entity} ${entity?uncap_first}) {
        Result result = new Result();
        Boolean isOk = this.${table.serviceImplName?uncap_first}.updateById(${entity?uncap_first});
        if (!isOk) {
             return result.successOk(isOk);
        } else {
             return result.successOk(isOk);
        }
    }


    /**
    *
    * <p>
    * Description: 删除${table.comment!}
    * </p>
    *
    * @param id 根据id删除${table.comment!}
    * @return Result 是否删除成功
    */
    @ApiOperation(value = "删除${table.comment!}接口", notes = "删除${table.comment!}接口")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Integer id) {
         Result result = new Result();
         Boolean isOk = this.${table.serviceImplName?uncap_first}.removeById(id);
        if (!isOk) {
             return result.successOk(isOk);
        } else {
              return result.successOk(isOk);
        }
    }

    /**
    *
    * <p>
    * Description: 批量删除${table.comment!}
    * </p>
    *
    * @param id 根据ids删除${table.comment!}
    * @return Result 是否删除成功
    */
    @ApiOperation(value = "批量删除${table.comment!}接口", notes = "批量删除${table.comment!}接口")
    @DeleteMapping("/batch")
    public Result deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
            Result result = new Result();
            if (ids == null || "".equals(ids.trim())) {
                result.successOk("id不能为空");
                return result;
            } else {
                 Boolean isOk = this.${table.serviceImplName?uncap_first}.removeByIds(Arrays.asList(ids.split(",")));
                  if (!isOk) {
                     return result.successOk(isOk);
                  } else {
                      return result.successOk(isOk);
            }
         }
    }

    }
</#if>
