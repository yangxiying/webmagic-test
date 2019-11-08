package yxy.networm.demo;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CoalNewsAjaxPageProcessor implements PageProcessor {


    private CoalNewsPageDao coalNewsPageDao;

    public CoalNewsAjaxPageProcessor(CoalNewsPageDao coalNewsPageDao) {
        this.coalNewsPageDao = coalNewsPageDao;
    }

    @Override
    public void process(Page page) {
        if (page.getUrl().regex(this.coalNewsPageDao.getRegex_url_list()).match()) {

            List<String> lst = new JsonPathSelector("$.items[*]").selectList(page.getRawText());
            if (CollectionUtils.isNotEmpty(lst)) {
                for (String item : lst) {
                    String url = new JsonPathSelector("$.url").select(item);
                    String date = new JsonPathSelector("$.date").select(item); //2019-11-01 05:41:02
                    try {
                        Date publishDate = DateUtils.parseDate(date, "yyyy-MM-dd HH:mm:ss");
                        //只取当天的
                        if (DateUtils.truncatedCompareTo(DateUtils.addDays(new Date(), 0), publishDate, Calendar.DAY_OF_MONTH) == 0) {
                            page.addTargetRequest(url);
                        }
                    } catch (ParseException e) {

                    }
                }
            }


        } else {
//            page.putField("title", new JsonPathSelector("$.data.title").select(page.getRawText()));
//            page.putField("content", new JsonPathSelector("$.data.content").select(page.getRawText()));

            page.putField(PubConfig.KEY_TITLE, page.getHtml().xpath(this.coalNewsPageDao.getXpath_title()).toString());
//        page.putField("content", page.getHtml().xpath("//div[@id='news-detail']//div[@class='content']"));
            page.putField(PubConfig.KEY_CONTENT, page.getHtml().xpath(this.coalNewsPageDao.getXpath_content()).smartContent().toString());
            page.putField(PubConfig.KEY_DATE, page.getHtml().xpath(this.coalNewsPageDao.getXpath_date())
                    .regex(this.coalNewsPageDao.getRegex_date()).toString());

        }

        System.out.println("");
    }

    @Override
    public Site getSite() {
        return this.coalNewsPageDao.getSite();
    }

    public static void main(String[] args) {
        long time = new Date().getTime();
        Spider.create(new CoalNewsAjaxPageProcessor(new CoalNewsPageDao()
                .setRegex_url_list("http://news.people.com.cn/210801/211150/index.js\\?_\\d+")
                .setRegex_url_path("http://world\\.people\\.com\\.cn/n1/\\d+/\\d+/c\\d+-\\d+.html")
                .setXpath_content_list("//div[@class='box news_list']")
                .setXpath_content("//div[@class='box_con']") //
                .setXpath_title("//div[@class='text_title']//h1") //class="clearfix w1000_320 text_title"
                .setXpath_date("//div[@class='text_title']//div[@class='fl']")
                .setRegex_date("\\d*年\\d+月\\d+日\\d+:\\d+")))
                .addUrl("http://news.people.com.cn/210801/211150/index.js" + "?_" + time)
                .addPipeline(new CoalNewsPipeline())
                .run();
    }
}
