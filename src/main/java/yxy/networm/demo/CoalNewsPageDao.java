package yxy.networm.demo;

import us.codecraft.webmagic.Site;

public class CoalNewsPageDao {

    /**
     * 网站的新闻列表url，
     * 一般列表都是分页的，可以设置正则表达式
     */
    private String regex_url_list = "";
    /**
     * 在列表的网页内容中提取 内容页的url的正则表达
     */
    private String regex_url_path = "";

    /**
     * 列表页中 新闻列表 所在的页面元素
     */
    private String xpath_content_list="";
    /**
     * 新闻页中 标题 所在的页面元素
     */
    private String xpath_title="";
    /**
     * 新闻页中 内容 所在的页面元素
     */
    private String xpath_content="";
    /**
     * 新闻页中 日期 所在的页面元素
     */
    private String xpath_date="";
    /**
     * 新闻页中 日期所在的页面元素内容中 符合日期的正则
     */
    private String regex_date="";

    private Site site = Site.me().setSleepTime(100);

    public Site getSite() {
        return site;
    }

    public CoalNewsPageDao setSite(Site site) {
        this.site = site;
        return this;
    }

    public String getRegex_url_list() {
        return regex_url_list;
    }

    public CoalNewsPageDao setRegex_url_list(String regex_url_list) {
        this.regex_url_list = regex_url_list;
        return this;
    }

    public String getRegex_url_path() {
        return regex_url_path;
    }

    public CoalNewsPageDao setRegex_url_path(String regex_url_path) {
        this.regex_url_path = regex_url_path;
        return this;
    }

    public String getXpath_content_list() {
        return xpath_content_list;
    }

    public CoalNewsPageDao setXpath_content_list(String xpath_content_list) {
        this.xpath_content_list = xpath_content_list;
        return this;
    }

    public String getXpath_title() {
        return xpath_title;
    }

    public CoalNewsPageDao setXpath_title(String xpath_title) {
        this.xpath_title = xpath_title;
        return this;
    }

    public String getXpath_content() {
        return xpath_content;
    }

    public CoalNewsPageDao setXpath_content(String xpath_content) {
        this.xpath_content = xpath_content;
        return this;
    }

    public String getXpath_date() {
        return xpath_date;
    }

    public CoalNewsPageDao setXpath_date(String xpath_date) {
        this.xpath_date = xpath_date;
        return this;
    }

    public String getRegex_date() {
        return regex_date;
    }

    public CoalNewsPageDao setRegex_date(String regex_date) {
        this.regex_date = regex_date;
        return this;
    }
}
