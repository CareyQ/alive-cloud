package com.careyq.alive.module.product.util;

import cn.hutool.core.util.StrUtil;

/**
 * SN 条码工具栏
 *
 * @author CareyQ
 */
public class SnUtils {

    /**
     * SN 条码长度
     */
    public static final int SN_CODE_LENGTH = 13;

    /**
     * 生成 sn 码
     *
     * @param brandId    品牌 id
     * @param categoryId 分类 id
     * @param productId  商品 id
     * @param index      索引
     * @return sn
     */
    public static String generateSnCode(Long brandId, Long categoryId, Long productId, int index) {
        String snCode = "69" + snCodePart(brandId.toString()) + snCodePart(index + categoryId.toString() + productId);
        return snCode + generateCheckDigit(snCode);
    }

    /**
     * sn 码片段
     *
     * @param source 原值
     * @return 片段
     */
    public static String snCodePart(String source) {
        return StrUtil.padPre(source, 5, "0");
    }

    /**
     * 生成校验位
     *
     * @param snCode sn 码
     * @return 校验位
     */
    public static char generateCheckDigit(String snCode) {
        if (snCode.length() >= SN_CODE_LENGTH) {
            snCode = StrUtil.sub(snCode, 0, SN_CODE_LENGTH);
        }

        int oddSum = 0;
        int evenSum = 0;
        for (int i = 0; i < snCode.length(); i++) {
            if (i % 2 == 0) {
                oddSum += Character.getNumericValue(snCode.charAt(i));
            } else {
                evenSum += Character.getNumericValue(snCode.charAt(i));
            }
        }

        int total = oddSum + (evenSum * 3);
        return (char) (10 - (total % 10));
    }
}
