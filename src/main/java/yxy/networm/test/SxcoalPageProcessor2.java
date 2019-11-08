package yxy.networm.test;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import yxy.networm.demo.CoalNewsPipeline;
import yxy.networm.demo.PubConfig;

public class SxcoalPageProcessor2 implements PageProcessor {
    private static final String URL_LIST = "http://www\\.sxcoal\\.com/news/*\\d*/index";
    private static final String URL_POST = "http://www\\.sxcoal\\.com/news/\\d+/info";
    private Site site = Site.me().setSleepTime(100);

    public void process(Page page) {


        if (page.getUrl().regex(URL_LIST).match()) {
            page.addTargetRequests(page.getHtml().xpath("//div[@id='new_list']").links().regex(URL_POST).all());
//            page.addTargetRequests(page.getHtml().regex(URL_LIST).all());

        } else {
            page.putField(PubConfig.KEY_TITLE, page.getHtml().xpath("//div[@class='news-detail']/h2").toString());
            page.putField(PubConfig.KEY_CONTENT, page.getHtml().xpath("//div[@id='news-detail']").smartContent().toString());
            page.putField(PubConfig.KEY_DATE, page.getHtml().xpath("//div[@id='news-detail']//div[@class='info']//span").regex("\\d*-\\d+-\\d+ \\d+:\\d+:\\d+").toString());

        }

    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

        String[] urls = {"http://www.sxcoal.com/news/index", "http://www.sxcoal.com/news/2/index", "http://www.sxcoal.com/news/3/index"
                , "http://www.sxcoal.com/news/4/index", "http://www.sxcoal.com/news/5/index", "http://www.sxcoal.com/news/6/index"};
        Spider spider1 = new Spider(new SxcoalPageProcessor2());
        spider1.addPipeline((new CoalNewsPipeline()));
//        spider1.addUrl("http://www.sxcoal.com/news/index");
        spider1.addUrl(urls);
        spider1.thread(8);
        spider1.run();

    }
}
