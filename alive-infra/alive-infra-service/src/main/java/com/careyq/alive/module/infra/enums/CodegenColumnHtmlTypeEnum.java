package com.careyq.alive.module.infra.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 代码生成器字段 HTML 对应类型
 *
 * @author CareyQ
 */
@Getter
@AllArgsConstructor
public enum CodegenColumnHtmlTypeEnum {

    /**
     * 文本框
     */
    INPUT("input"),
    /**
     * 文本域
     */
    TEXTAREA("textarea"),
    /**
     * 数值框
     */
    INPUT_NUMBER("inputNumber"),
    /**
     * 下拉框
     */
    SELECT("select"),
    /**
     * 单选框
     */
    RADIO("radio"),
    /**
     * 复选框
     */
    CHECKBOX("checkbox"),
    /**
     * 日期控件
     */
    DATETIME("datetime"),
    /**
     * 上传图片
     */
    IMAGE_UPLOAD("imageUpload"),
    /**
     * 上传文件
     */
    FILE_UPLOAD("fileUpload"),
    /**
     * 富文本控件
     */
    EDITOR("editor"),
    ;

    private final String type;
}
