package org.ssck.visual_displacement.Utils;

/**
 * 日期时间格式化工具类
 * 提供日期时间字符串格式转换功能
 */
public class DateTimeFormatUtil {

    /**
     * 私有构造方法，防止实例化
     */
    private DateTimeFormatUtil() {
        throw new UnsupportedOperationException("工具类不能实例化");
    }

    /**
     * 将格式为 "yyyy-MM-dd HH-mm-ss" 的字符串转换为中文格式 "yyyy年MM月dd日 HH点mm分ss秒"
     *
     * @param dateTimeStr 原始日期时间字符串，格式：2025-09-04 09-43-13
     * @return 格式化后的中文日期时间字符串，格式：2025年09月04日 09点43分13秒
     */
    public static String toChineseFormat(String dateTimeStr) {
        if (dateTimeStr == null || dateTimeStr.trim().isEmpty()) {
            return dateTimeStr;
        }

        try {
            // 使用字符串替换实现格式转换
            return dateTimeStr
                    .replaceFirst("(\\d{4})-(\\d{2})-(\\d{2})", "$1年$2月$3日")
                    .replaceFirst("(\\d{2})-(\\d{2})-(\\d{2})", "$1点$2分$3秒");
        } catch (Exception e) {
            // 如果转换失败，返回原始字符串
            return dateTimeStr;
        }
    }
}