package yxy.networm.demo;

public interface PubConfig {

    /**
     * 从页面解析的内容对应的key值
     */

    /**
     * 标题
     */
    String KEY_TITLE="title";
    /**
     * 内容
     */
    String KEY_CONTENT="content";
    /**
     * 发表日期
     */
    String KEY_DATE="date";


    /**
     * 抽取内容中包含指定关键词的 煤炭 或 煤电
     */
    String FILTER_IN_REG =".*(煤炭|煤电).*";

    /**
     * 去除内容中包含指定关键词的
     */
    String FILTER_OUT_REG =".*(登录后查看).*";
}
