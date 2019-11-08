package yxy.networm.test;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import yxy.networm.demo.CoalNewsPipeline;
import yxy.networm.demo.PubConfig;

public class CoalPageProcessor implements PageProcessor {

    private static final String URL_LIST = "http://www\\.coal\\.com\\.cn/Coal_NewsList_\\d+__.htm";
    private static final String URL_POST = "http://www\\.coal\\.com\\.cn/News/\\d+.htm";

    private Site site = Site.me().setTimeOut(10000).setSleepTime(100);

    public void process(Page page) {


        if (page.getUrl().regex(URL_LIST).match()) {
            page.addTargetRequests(page.getHtml().xpath("//div[@class='news']").links().regex(URL_POST).all());
//            page.addTargetRequests(page.getHtml().regex(URL_LIST).all());

        }else {

            page.putField(PubConfig.KEY_TITLE, page.getHtml().xpath("//div[@class='news']//div[@class='mx']/h1").toString());
             page.putField(PubConfig.KEY_CONTENT, page.getHtml().xpath("//div[@class='news']").smartContent().toString());
            page.putField(PubConfig.KEY_DATE, page.getHtml().xpath("//div[@class='news']//div[@class='titsub']").regex("\\d*/\\d+/\\d+ \\d+:\\d+:\\d+").toString());

        }
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {



        String[] urls = {"http://www.coal.com.cn/Coal_NewsList_1__.htm", "http://www.coal.com.cn/Coal_NewsList_2__.htm", "http://www.coal.com.cn/Coal_NewsList_3__.htm"
                , "http://www.coal.com.cn/Coal_NewsList_4__.htm", "http://www.coal.com.cn/Coal_NewsList_5__.htm", "http://www.coal.com.cn/Coal_NewsList_6__.htm"};
        Spider spider1 = new Spider(new CoalPageProcessor());
        spider1.addPipeline((new CoalNewsPipeline()));
        spider1.addUrl(urls);
        spider1.thread(8);
        spider1.run();
    }
}
