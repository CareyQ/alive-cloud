package com.careyq.alive.captcha.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 模板
 *
 * @author CareyQ
 */
@Data
@AllArgsConstructor
public class Template {

    /**
     * 可移动的模板
     */
    private String active;

    /**
     * 固定的模板
     */
    private String fixed;
}
