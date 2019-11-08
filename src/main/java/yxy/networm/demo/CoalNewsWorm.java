package yxy.networm.demo;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.Date;

public class CoalNewsWorm {


    public void startGetCoalNews(String[] netUrls, PageProcessor coalNewsPageDao) {

        Spider spider1 = new Spider(coalNewsPageDao);
        spider1.addPipeline((new CoalNewsPipeline()));
        spider1.addUrl(netUrls);
        spider1.run();

//        System.out.println("==========end=========");
    }

    public static void main(String[] args) {

//        //抓取 http://www.sxcoal.com 中国煤炭资源网 的新闻
//        new CoalNewsWorm().startGetCoalNews(new String[]{"http://www.sxcoal.com/news/1/index"}
//                , new CoalNewsWormPageProcessor(
//                        new CoalNewsPageDao()
//                        .setRegex_url_list("http://www\\.sxcoal\\.com/news/*\\d*/index")
//                        .setRegex_url_path("http://www\\.sxcoal\\.com/news/\\d+/info")
//                        .setXpath_content_list("//div[@id='new_list']")
//                        .setXpath_content("//div[@id='news-detail']")
//                        .setXpath_title("//div[@class='news-detail']/h2/text()")
//                        .setXpath_date("//div[@id='news-detail']//div[@class='info']//span")
//                        .setRegex_date("\\d*-\\d+-\\d+ \\d+:\\d+:\\d+")
////                        .setSite(Site.me())
//                        )
//                );
//
//        System.out.println("===========================================================================");
//        System.out.println("===========================================================================");
//
//
        //抓取 http://www.coal.com.cn 煤炭网 的新闻
//        new CoalNewsWorm().startGetCoalNews(new String[]{"http://www.coal.com.cn/Coal_NewsList_1__.htm"}
//                , new CoalNewsWormPageProcessor(new CoalNewsPageDao().setRegex_url_list("http://www\\.coal\\.com\\.cn/Coal_NewsList_\\d+__.htm")
//                        .setRegex_url_path("http://www\\.coal\\.com\\.cn/News/\\d+.htm")
//                        .setXpath_content_list("//div[@class='news']")
//                        .setXpath_content("//div[@class='news']//div[@class='nr']")
//                        .setXpath_title("//div[@class='news']//div[@class='mx']/h1/text()")
//                        .setXpath_date("//div[@class='news']//div[@class='titsub']")
//                        .setRegex_date("\\d*/\\d+/\\d+ \\d+:\\d+:\\d+")
//                        .setSite(Site.me().setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36")
//                                .setTimeOut(100000).setSleepTime(100)
//                        )));

//
//        //抓取 http://www.ccoalnews.com 中国煤炭网 的新闻
//        new CoalNewsWorm().startGetCoalNews(new String[]{"http://www.ccoalnews.com/news/yaowen.html"}
//                , new CoalNewsWormPageProcessor(new CoalNewsPageDao().setRegex_url_list("http://www\\.ccoalnews\\.com/news/yaowen_*\\d*\\.html")
//                        .setRegex_url_path("http://www.ccoalnews.com/news/\\d+/\\d+/c\\d+.html")
//                        .setXpath_content_list("//div[@class='listPage-l fl'")
//                        .setXpath_content("//div[@class='text-article']")
//                        .setXpath_title("//div[@class='text-article']//h1/text()")
//                        .setXpath_date("//div[@class='text-article']//div[@class='article-details']//span[@class='date']")
//                        .setRegex_date("\\d*-\\d+-\\d+ \\d+:\\d+:\\d+")));
//
//
//        //http://www.gov.cn/xinwen/yaowen.htm 中国政府网 要闻列表
//        // http://www.gov.cn/xinwen/2019-11/07/content_5449606.htm
//        new CoalNewsWorm().startGetCoalNews(new String[]{"http://www.gov.cn/xinwen/yaowen.htm"}
//                , new CoalNewsWormPageProcessor(new CoalNewsPageDao().setRegex_url_list("http://www\\.gov\\.cn/xinwen/yaowen\\.htm")
//                        .setRegex_url_path("http://www\\.gov\\.cn/xinwen/\\d+-\\d+/\\d+/content_\\d+\\.htm")
//                        .setXpath_content_list("//div[@class='news_box']")
//                        .setXpath_content("//div[@class='content']")
//                        .setXpath_title("//div[@class='article oneColumn pub_border']//h1/text()")
//                        .setXpath_date("//div[@class='article oneColumn pub_border']//div[@class='pages-date']")
//                        .setRegex_date("\\d*-\\d+-\\d+ \\d+:\\d+")));

//        //http://news.people.com.cn/ 人民网
//        // http://world.people.com.cn/n1/2019/1107/c1002-31442452.html
         long time = new Date().getTime();
        new CoalNewsWorm().startGetCoalNews(new String[]{"http://news.people.com.cn/210801/211150/index.js" + "?_" + time}
                , new CoalNewsAjaxPageProcessor(new CoalNewsPageDao()
                        .setRegex_url_list("http://news.people.com.cn/210801/211150/index.js\\?_\\d+")
                        .setRegex_url_path("http://world\\.people\\.com\\.cn/n1/\\d+/\\d+/c\\d+-\\d+.html")
                        .setXpath_content_list("//div[@class='box news_list']")
                        .setXpath_content("//div[@class='box_con']") //
                        .setXpath_title("//div[@class='text_title']//h1/text()") //class="clearfix w1000_320 text_title"
                        .setXpath_date("//div[@class='text_title']//div[@class='fl']")
                        .setRegex_date("\\d*年\\d+月\\d+日\\d+:\\d+")));

    }
}
