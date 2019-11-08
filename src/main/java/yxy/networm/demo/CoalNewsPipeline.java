package yxy.networm.demo;

import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class CoalNewsPipeline implements Pipeline {

    @Override
    public void process(ResultItems resultItems, Task task) {
        System.out.println("get page: " + resultItems.getRequest().getUrl());
        //遍历所有结果，输出到控制台，
        String title = resultItems.get(PubConfig.KEY_TITLE);
        String content = resultItems.get(PubConfig.KEY_CONTENT);
        String date = resultItems.get(PubConfig.KEY_DATE);


        if(StringUtils.isEmpty(title)){
            return;
        }
        if(StringUtils.isEmpty(content)){
            return;
        }


        boolean titleFilt = title.matches(PubConfig.FILTER_IN_REG);

        boolean contentFilt = content.matches(PubConfig.FILTER_IN_REG);

        if (!titleFilt && !contentFilt) {
            return;
        }

        if (content.matches(PubConfig.FILTER_OUT_REG)) {
            return;
        }


        System.out.println(PubConfig.KEY_TITLE + ":\t" + title);
        System.out.println(PubConfig.KEY_DATE + ":\t" + date);
        System.out.println(PubConfig.KEY_CONTENT + ":\t" + content);

    }
}
