package yxy.networm.demo;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class CoalNewsWormPageProcessor implements PageProcessor {


//    private Site site = Site.me().setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36")
//    .setTimeOut(5000).setSleepTime(100);

    /**
     * 爬取网站内容参数对象
     */
    private CoalNewsPageDao coalNewsPageDao;

    public CoalNewsWormPageProcessor(CoalNewsPageDao coalNewsPageDao) {
        this.coalNewsPageDao = coalNewsPageDao;
    }

    @Override
    public void process(Page page) {


        if (page.getUrl().regex(this.coalNewsPageDao.getRegex_url_list()).match()) {
            page.addTargetRequests(page.getHtml().xpath(this.coalNewsPageDao.getXpath_content_list()).links().regex(this.coalNewsPageDao.getRegex_url_path()).all());
//            page.addTargetRequests(page.getHtml().regex(this.coalNewsPageDao.getRegex_url_list()).all());

        } else {

            page.putField(PubConfig.KEY_TITLE, page.getHtml().xpath(this.coalNewsPageDao.getXpath_title()).toString());
//        page.putField("content", page.getHtml().xpath("//div[@id='news-detail']//div[@class='content']"));
            page.putField(PubConfig.KEY_CONTENT, page.getHtml().xpath(this.coalNewsPageDao.getXpath_content()).smartContent().toString());
            page.putField(PubConfig.KEY_DATE, page.getHtml().xpath(this.coalNewsPageDao.getXpath_date())
                    .regex(this.coalNewsPageDao.getRegex_date()).toString());

        }
    }

    @Override
    public Site getSite() {
        return this.coalNewsPageDao.getSite();
    }

    public static void main(String[] args) {


//        Spider spider1 = new Spider(new CoalPageProcessor());
//        spider1.addPipeline((new ConsolePipeline()));
//        spider1.addUrl("http://www.coal.com.cn/Coal_NewsList_1__.htm");
//        spider1.thread(1);
//        spider1.run();


//        String[] urls = {"http://www.ccoalnews.com/news/yaowen.html", "http://www.ccoalnews.com/news/yaowen_2.html"};
//        Spider spider1 = new Spider(new CoalNewsWormPageProcessor());
//        spider1.addPipeline((new ConsolePipeline()));
//        spider1.addUrl(urls);
////        spider1.thread(8);
//        spider1.run();
    }
}
