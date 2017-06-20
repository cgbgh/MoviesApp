package com.app.cgb.moviepreview.entity;

import java.util.List;

public class EventList {

    /**
     * title : 该片你该了解的9件事
     * list : ["《金刚狼3：殊死一战》是休·杰克曼最后一次饰演金刚狼，这个角色自第一部《X战警》以来已过去17年。","英文片名Logan是金刚狼作为普通人的名字，第三部老年金刚狼的能力不断退化，会增加更多\u201c人类\u201d的特性，和女儿的亲情戏也是一大看点，片名暗示了电影的主题，也暗示了狼叔的最终归属。","影片借鉴了漫威漫画《暮狼寻乡》的一些剧情，不过休·杰克曼说他出演本片的灵感更多来自电影《不可饶恕》。","本集导演詹姆斯·曼高德也导演了《金刚狼2》。这其实也是休·杰克曼与其合作的第三部电影，他们还合作过《穿越时空爱上你》。","詹姆斯·曼高德拍摄本片时借鉴了经典西部片《原野奇侠》，在片中也有X教授在赌场酒店里观看了这部影片的场景。","本片中老年版X教授帕特里克·斯图尔特也迎来告别演出。","为了找到人气角色劳拉（X-23）的扮演者，剧组搜遍了欧洲、南美、北美、加拿大，最后在西班牙马德里找到了达芙妮·基恩。","本片主要拍摄地在美国新奥尔良和路易斯安那州，取景地包括NASA的密乔零件装配厂、斯莱德尔市、路易斯安那11号高速公路和知名的51号公路。","《金刚狼3》在北美的电影分级是R级。"]
     */

    private String title;
    private List<String> list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
