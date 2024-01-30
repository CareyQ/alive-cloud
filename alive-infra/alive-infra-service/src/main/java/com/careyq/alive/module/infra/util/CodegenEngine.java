package com.careyq.alive.module.infra.util;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.engine.velocity.VelocityEngine;
import com.careyq.alive.core.domain.BaseEntity;
import com.careyq.alive.core.domain.PageDTO;
import com.careyq.alive.core.domain.Result;
import com.careyq.alive.core.util.CollUtils;
import com.careyq.alive.module.infra.entity.CodegenColumn;
import com.careyq.alive.module.infra.entity.CodegenTable;
import com.careyq.alive.module.infra.enums.CodegenSceneEnum;
import com.careyq.alive.mybatis.core.mapper.BaseMapperX;
import com.careyq.alive.mybatis.core.service.IServiceX;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成引擎
 *
 * @author CareyQ
 */
@Component
public class CodegenEngine {

    @Value("${alive.codegen.base-package}")
    private String basePackage;

    /**
     * hutool 模板引擎
     */
    private final TemplateEngine templateEngine;
    /**
     * 全局通用变量映射
     */
    private static final Map<String, Object> GLOBAL_BINDING_MAP = new HashMap<>();

    /**
     * 后端模版配置
     * key: 模版路径
     * value: 生成文件路径
     */
    private static final Map<String, String> SERVER_TEMPLATES = MapUtil.<String, String>builder(new LinkedHashMap<>())
            .put(javaTemplatePath("dto/pageDTO"), javaModuleExtraFilePath("dto", "PageDTO"))
            .put(javaTemplatePath("dto/saveDTO"), javaModuleExtraFilePath("dto", "DTO"))
            .put(javaTemplatePath("vo/detailVO"), javaModuleExtraFilePath("vo", "VO"))
            .put(javaTemplatePath("vo/pageVO"), javaModuleExtraFilePath("vo", "PageVO"))
            .put(javaTemplatePath("entity/entity"), javaModuleFilePath("entity", ""))
            .put(javaTemplatePath("mapper/mapper"), javaModuleFilePath("mapper", "Mapper"))
            .put(javaTemplatePath("service/service"), javaModuleFilePath("service", "Service"))
            .put(javaTemplatePath("service/serviceImpl"), javaModuleFilePath("service", "ServiceImpl"))
            .put(javaTemplatePath("controller/controller"), javaModuleFilePath("controller", "Controller"))
            .build();

    /**
     * 前端模版配置
     * key: 模版路径
     * value: 生成文件路径
     */
    private static final Map<String, String> FRONT_TEMPLATES = MapUtil.<String, String>builder(new LinkedHashMap<>())
            .put(vueTemplatePath("views/index.vue"), vueFilePath("views/${table.moduleName}/${table.businessName}/index.vue"))
            .put(vueTemplatePath("views/form.vue"), vueFilePath("views/${table.moduleName}/${table.businessName}/${table.className}Form.vue"))
            .put(vueTemplatePath("api/api.ts"), vueFilePath("api/${table.moduleName}/${classNameVar}.ts"))
            .build();

    private static final Map<String, String> TEMPLATES = MapUtil.<String, String>builder(new LinkedHashMap<>())
            .putAll(SERVER_TEMPLATES)
            .putAll(FRONT_TEMPLATES)
            .build();

    public CodegenEngine() {
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setResourceMode(TemplateConfig.ResourceMode.CLASSPATH);
        this.templateEngine = new VelocityEngine(templateConfig);
    }

    @PostConstruct
    void initGlobalBindingMap() {
        GLOBAL_BINDING_MAP.put("basePackage", basePackage);
        GLOBAL_BINDING_MAP.put("ResultClassName", Result.class.getName());
        GLOBAL_BINDING_MAP.put("PageDtoClassName", PageDTO.class.getName());
        GLOBAL_BINDING_MAP.put("BaseEntityClassName", BaseEntity.class.getName());
        GLOBAL_BINDING_MAP.put("BaseMapperClassName", BaseMapperX.class.getName());
        GLOBAL_BINDING_MAP.put("BaseServiceClassName", IServiceX.class.getName());
    }

    /**
     * 生成代码
     *
     * @param table   表信息
     * @param columns 列信息
     * @return 生成的文件内容
     */
    public Map<String, String> execute(CodegenTable table, List<CodegenColumn> columns) {
        Map<String, Object> bindingMap = initBindingMap(table, columns);
        Map<String, String> res = new LinkedHashMap<>(TEMPLATES.size());
        TEMPLATES.forEach((vmPath, filePath) -> {
            filePath = formatFilePath(filePath, bindingMap);
            String content = templateEngine.getTemplate(vmPath).render(bindingMap);
            res.put(filePath, content);
        });
        return res;
    }

    /**
     * 初始化绑定变量
     *
     * @param table   表信息
     * @param columns 列信息
     * @return 绑定变量
     */
    private static Map<String, Object> initBindingMap(CodegenTable table, List<CodegenColumn> columns) {
        Map<String, Object> bindingMap = new HashMap<>(GLOBAL_BINDING_MAP);
        bindingMap.put("table", table);
        bindingMap.put("columns", columns);
        bindingMap.put("primaryColumn", CollUtils.findFirst(columns, CodegenColumn::getPrimaryKey));
        bindingMap.put("sceneEnum", CodegenSceneEnum.valueOf(table.getScene()));

        String simpleClassName = StrUtil.removePrefix(table.getClassName(), StrUtil.upperFirst(table.getModuleName()));
        bindingMap.put("simpleClassName", simpleClassName);
        // 将 DictType 转换成 dict_type
        bindingMap.put("simpleClassName_underlineCase", StrUtil.toUnderlineCase(simpleClassName));
        // 将 DictType 转换成 dictType，用于变量
        bindingMap.put("classNameVar", StrUtil.lowerFirst(simpleClassName));
        // 将 DictType 转换成 dict-type
        String simpleClassNameStrikeCase = StrUtil.toSymbolCase(simpleClassName, '-');
        bindingMap.put("simpleClassName_strikeCase", simpleClassNameStrikeCase);
        return bindingMap;
    }

    private String formatFilePath(String filePath, Map<String, Object> bindingMap) {
        filePath = StrUtil.replace(filePath, "${basePackage}",
                MapUtil.getStr(bindingMap, "basePackage").replaceAll("\\.", "/"));
        filePath = StrUtil.replace(filePath, "${classNameVar}", MapUtil.getStr(bindingMap, "classNameVar"));
        filePath = StrUtil.replace(filePath, "${simpleClassName}", MapUtil.getStr(bindingMap, "simpleClassName"));
        // sceneEnum 包含的字段
        CodegenSceneEnum sceneEnum = (CodegenSceneEnum) bindingMap.get("sceneEnum");
        filePath = StrUtil.replace(filePath, "${sceneEnum.prefixClass}", sceneEnum.getPrefixClass());
        filePath = StrUtil.replace(filePath, "${sceneEnum.basePackage}", sceneEnum.getBasePackage());
        // table 包含的字段
        CodegenTable table = (CodegenTable) bindingMap.get("table");
        filePath = StrUtil.replace(filePath, "${table.moduleName}", table.getModuleName());
        filePath = StrUtil.replace(filePath, "${table.businessName}", table.getBusinessName());
        filePath = StrUtil.replace(filePath, "${table.className}", table.getClassName());
        return filePath;
    }

    private static String javaTemplatePath(String path) {
        return "codegen/java/" + path + ".vm";
    }

    private static String javaModuleExtraFilePath(String packageName, String path) {
        return "alive-${table.moduleName}/alive-${table.moduleName}-service/" +
                "src/main/java/${basePackage}/module/${table.moduleName}/" + packageName + "/${sceneEnum.prefixClass}${table.className}" + path + ".java";
    }

    private static String javaModuleFilePath(String packageName, String path) {
        return "alive-${table.moduleName}/alive-${table.moduleName}-service/" +
                "src/main/java/${basePackage}/module/${table.moduleName}/" + packageName + "/${table.className}" + path + ".java";
    }

    private static String vueTemplatePath(String path) {
        return "codegen/vue/" + path + ".vm";
    }

    private static String vueFilePath(String path) {
        return "alive-ui-${sceneEnum.basePackage}/src/" + path;
    }
}
