package yxy.networm.test;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import yxy.networm.demo.CoalNewsPipeline;
import yxy.networm.demo.PubConfig;

public class SxcoalPageProcessor implements PageProcessor {
    private static final String URL_LIST = "http://www\\.sxcoal\\.com/news/*\\d*/index";
    private static final String URL_POST = "http://www\\.sxcoal\\.com/news/\\d+/info";
    private Site site = Site.me().setSleepTime(100);

    public void process(Page page) {


//        page.addTargetRequests(page.getHtml().xpath("//div[@id='new_list']").links().all());
//        page.addTargetRequests(page.getHtml().regex("http://www\\.sxcoal\\.com/news/\\d+/info").all());

        page.addTargetRequests(page.getHtml().xpath("//div[@id='new_list']").links().regex(URL_POST).all());

        page.putField(PubConfig.KEY_TITLE, page.getHtml().xpath("//div[@class='news-detail']/h2").toString());
//        page.putField("content", page.getHtml().xpath("//div[@id='news-detail']//div[@class='content']"));
        page.putField(PubConfig.KEY_CONTENT, page.getHtml().xpath("//div[@id='news-detail']").smartContent().toString());
        page.putField(PubConfig.KEY_DATE,page.getHtml().xpath("//div[@id='news-detail']//div[@class='info']//span").regex("\\d*-\\d+-\\d+ \\d+:\\d+:\\d+").toString());

    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
//        Spider spider = Spider.create(new SxcoalPageProcessor()).addUrl("http://www.sxcoal.com/news/index").addPipeline(new ConsolePipeline());
//        spider.setScheduler(new QueueScheduler()
//                .setDuplicateRemover(new BloomFilterDuplicateRemover(10000)) //10000000是估计的页面数量
//        );
//        spider.thread(8);
//        spider.run();
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        spider.stop();


        Spider spider1 = new Spider(new SxcoalPageProcessor());
        spider1.addPipeline((new CoalNewsPipeline()));
        spider1.addUrl("http://www.sxcoal.com/news/index");
        spider1.thread(8);
        spider1.run();

    }
}
