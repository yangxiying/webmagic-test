package yxy.networm.test;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import yxy.networm.demo.CoalNewsPipeline;
import yxy.networm.demo.PubConfig;

public class CcoalnewsPageProcessor implements PageProcessor {

    private static final String URL_LIST = "http://www\\.ccoalnews\\.com/news/yaowen_*\\d*\\.html";
    private static final String URL_POST = "http://www.ccoalnews.com/news/\\d+/\\d+/c\\d+.html";

    private Site site = Site.me().setTimeOut(10000).setSleepTime(100);

    public void process(Page page) {


        if (page.getUrl().regex(URL_LIST).match()) {
            page.addTargetRequests(page.getHtml().xpath("//div[@class='listPage-l fl']").links().regex(URL_POST).all());
//            page.addTargetRequests(page.getHtml().regex(URL_LIST).all());

        }else {

            page.putField(PubConfig.KEY_TITLE, page.getHtml().xpath("//div[@class='text-article']//h1").toString());
//        page.putField("content", page.getHtml().xpath("//div[@id='news-detail']//div[@class='content']"));
            page.putField(PubConfig.KEY_CONTENT, page.getHtml().xpath("//div[@class='text-article']").smartContent().toString());
            page.putField(PubConfig.KEY_DATE, page.getHtml().xpath("//div[@class='text-article']//div[@class='article-details']//span[@class='date']")
                    .regex("\\d*-\\d+-\\d+ \\d+:\\d+:\\d+").toString());

        }
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {


//        Spider spider1 = new Spider(new CoalPageProcessor());
//        spider1.addPipeline((new ConsolePipeline()));
//        spider1.addUrl("http://www.coal.com.cn/Coal_NewsList_1__.htm");
//        spider1.thread(1);
//        spider1.run();



        String[] urls = {"http://www.ccoalnews.com/news/yaowen.html", "http://www.ccoalnews.com/news/yaowen_2.html"};
        Spider spider1 = new Spider(new CcoalnewsPageProcessor());
        spider1.addPipeline((new CoalNewsPipeline()));
        spider1.addUrl(urls);
//        spider1.thread(8);
        spider1.run();
    }
}
