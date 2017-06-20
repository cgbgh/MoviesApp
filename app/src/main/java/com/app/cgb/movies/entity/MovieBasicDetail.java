package com.app.cgb.moviepreview.entity;

import java.util.List;

/**
 * Created by cgb on 2017/2/28.
 */
public class MovieBasicDetail {


    /**
     * code : 1
     * data : {"advertisement":{"advList":[{"advTag":"","endDate":1514649599,"isHorizontalScreen":false,"isOpenH5":false,"startDate":1451577600,"tag":"商城新奇特专题+商城美漫小团体","type":"203","typeName":"影片详情页banner2","url":"https://static4da.mtime.cn/feature/mobile/banner/2017/0228/xqtmm750210m.html"}],"count":1,"error":"","success":true},"basic":{"actors":[],"award":{},"commentSpecial":"","community":{},"director":{"directorId":897895,"img":"http://img31.mtime.cn/ph/2016/01/28/094446.79677444_1280X720X2.jpg","name":"弗兰克·德拉邦特","nameEn":"Frank Darabont"},"festivals":[],"hotRanking":-1,"img":"http://img31.mtime.cn/mt/2014/03/07/123549.37376649_1280X720X2.jpg","is3D":false,"isDMAX":false,"isEggHunt":false,"isFilter":false,"isIMAX":false,"isIMAX3D":false,"isTicket":false,"message":"该操作将清除您对该片的评分！是否确认？","mins":"142分钟","movieId":12231,"name":"肖申克的救赎","nameEn":"The Shawshank Redemption","overallRating":9.2,"personCount":126,"quizGame":{},"releaseArea":"美国","releaseDate":"19940923","showCinemaCount":-1,"showDay":-1,"showtimeCount":-1,"stageImg":{"count":114,"list":[{"imgId":359553,"imgUrl":"http://img31.mtime.cn/pi/2014/03/06/094947.29158501_1280X720X2.jpg"},{"imgId":347482,"imgUrl":"http://img31.mtime.cn/pi/2014/03/06/094947.15659323_1280X720X2.jpg"},{"imgId":401961,"imgUrl":"http://img31.mtime.cn/pi/2014/03/06/094947.13521527_1280X720X2.jpg"},{"imgId":401969,"imgUrl":"http://img31.mtime.cn/pi/2014/03/06/094947.23978212_1280X720X2.jpg"}]},"story":"银行家安迪因被陷害杀害妻子与她的情夫，被判两个终身监禁。入狱后，影片便以黑人狱友瑞德冷静的旁白来推进。监狱数十年如一日的改造会使原本自由的心灵习惯了牢笼的禁锢，而这也将是安迪的命运？","style":{"isLeadPage":0,"leadImg":"https://img2.mtime.cn/mg/.jpg","leadUrl":""},"totalNominateAward":0,"totalWinAward":0,"type":["犯罪","剧情"],"url":"https://movie.mtime.com/12231/","video":{"count":2,"hightUrl":"https://vfx.mtime.cn/Video/2014/03/06/mp4/140306102651231568.mp4","img":"http://img31.mtime.cn/mg/2014/03/06/101952.22948087_235X132X4.jpg","title":"肖申克的救赎 预告片","url":"https://vfx.mtime.cn/Video/2014/03/06/mp4/140306102651231568_480.mp4","videoId":48021}},"boxOffice":{},"live":{"count":0,"img":"","liveId":0,"playNumTag":"","playTag":"","status":3,"title":""},"related":{"goodsCount":0,"goodsList":[],"relateId":0,"relatedUrl":"https://m.mtime.cn/#!/commerce/list/","type":0}}
     * msg : 成功
     * showMsg :
     */

    private DataBean data;
    private String msg;

    public DataBean getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public static class DataBean{

        /**
         * advertisement : {"advList":[{"advTag":"","endDate":1514649599,"isHorizontalScreen":false,"isOpenH5":false,"startDate":1451577600,"tag":"商城新奇特专题+商城美漫小团体","type":"203","typeName":"影片详情页banner2","url":"https://static4da.mtime.cn/feature/mobile/banner/2017/0228/xqtmm750210m.html"}],"count":1,"error":"","success":true}
         * basic : {"actors":[],"award":{"awardList":[],"totalNominateAward":12,"totalWinAward":1},"commentSpecial":"","community":{},"director":{"directorId":897895,"img":"http://img31.mtime.cn/ph/2016/01/28/094446.79677444_1280X720X2.jpg","name":"弗兰克·德拉邦特","nameEn":"Frank Darabont"},"festivals":[{"festivalId":3,"img":"http://img31.mtime.cn/mg/2014/02/24/144331.32490714.jpg","nameCn":"奥斯卡金像奖","nameEn":"Academy AwardsBean","shortName":"奥斯卡"},{"festivalId":15,"img":"http://img31.mtime.cn/mg/2014/02/24/145735.69166081.jpg","nameCn":"美国金球奖","nameEn":"Golden Globes, USA","shortName":"金球奖"},{"festivalId":20,"img":"http://img31.mtime.cn/mg/2014/02/24/151023.22214126.jpg","nameCn":"日本电影学院奖","nameEn":"AwardsBean of the Japanese Academy","shortName":"日本学院奖"},{"festivalId":24,"img":"http://img31.mtime.cn/mg/2014/02/24/170521.92061793.jpg","nameCn":"土星奖","nameEn":"Saturn AwardsBean","shortName":"土星奖"}],"hotRanking":-1,"img":"http://img31.mtime.cn/mt/2014/03/07/123549.37376649_1280X720X2.jpg","is3D":false,"isDMAX":false,"isEggHunt":false,"isFilter":false,"isIMAX":false,"isIMAX3D":false,"isTicket":false,"message":"该操作将清除您对该片的评分！是否确认？","mins":"142分钟","movieId":12231,"name":"肖申克的救赎","nameEn":"The Shawshank Redemption","overallRating":9.2,"personCount":126,"quizGame":{},"releaseArea":"美国","releaseDate":"19940923","showCinemaCount":-1,"showDay":-1,"showtimeCount":-1,"stageImg":{"count":114,"list":[{"imgId":359553,"imgUrl":"http://img31.mtime.cn/pi/2014/03/06/094947.29158501_1280X720X2.jpg"},{"imgId":347482,"imgUrl":"http://img31.mtime.cn/pi/2014/03/06/094947.15659323_1280X720X2.jpg"},{"imgId":401961,"imgUrl":"http://img31.mtime.cn/pi/2014/03/06/094947.13521527_1280X720X2.jpg"},{"imgId":401969,"imgUrl":"http://img31.mtime.cn/pi/2014/03/06/094947.23978212_1280X720X2.jpg"}]},"story":"银行家安迪因被陷害杀害妻子与她的情夫，被判两个终身监禁。入狱后，影片便以黑人狱友瑞德冷静的旁白来推进。监狱数十年如一日的改造会使原本自由的心灵习惯了牢笼的禁锢，而这也将是安迪的命运？","style":{"isLeadPage":0,"leadImg":"https://img2.mtime.cn/mg/.jpg","leadUrl":""},"totalNominateAward":0,"totalWinAward":0,"type":["犯罪","剧情"],"url":"https://movie.mtime.com/12231/","video":{"count":2,"hightUrl":"https://vfx.mtime.cn/Video/2014/03/06/mp4/140306102651231568.mp4","img":"http://img31.mtime.cn/mg/2014/03/06/101952.22948087_235X132X4.jpg","title":"肖申克的救赎 预告片","url":"https://vfx.mtime.cn/Video/2014/03/06/mp4/140306102651231568_480.mp4","videoId":48021}}
         * boxOffice : {}
         * live : {"count":0,"img":"","liveId":0,"playNumTag":"","playTag":"","status":3,"title":""}
         * related : {"goodsCount":0,"goodsList":[],"relateId":0,"relatedUrl":"https://m.mtime.cn/#!/commerce/list/","type":0}
         */

        private BasicBean basic;
        private BoxOfficeBean boxOffice;
        private LiveBean live;

        public BasicBean getBasic() {
            return basic;
        }

        public BoxOfficeBean getBoxOffice() {
            return boxOffice;
        }

        public LiveBean getLive() {
            return live;
        }

        public static class BasicBean{

            /**
             * actors : [{"actorId":893026,"img":"http://img31.mtime.cn/ph/2014/10/16/090902.78583267_1280X720X2.jpg","name":"蒂姆·罗宾斯","nameEn":"Tim Robbins","roleImg":"http://img31.mtime.cn/mg/2014/03/06/095801.37640458.jpg","roleName":"安迪"},{"actorId":914747,"img":"http://img31.mtime.cn/ph/2014/03/14/152553.24862330_1280X720X2.jpg","name":"摩根·弗里曼","nameEn":"Morgan Freeman","roleImg":"http://img31.mtime.cn/mg/2014/03/06/100012.83373613.jpg","roleName":"瑞德"},{"actorId":926465,"img":"http://img31.mtime.cn/ph/2014/03/14/154053.49621373_1280X720X2.jpg","name":"鲍勃·冈顿","nameEn":"Bob Gunton","roleImg":"http://img31.mtime.cn/mg/2014/03/06/095646.98460287.jpg","roleName":"典狱长诺顿"},{"actorId":985553,"img":"http://img31.mtime.cn/ph/2014/03/14/154053.46837866_1280X720X2.jpg","name":"詹姆斯·惠特摩","nameEn":"James Whitmore","roleImg":"http://img31.mtime.cn/mg/2014/03/06/095605.37095220.jpg","roleName":"布鲁克斯"},{"actorId":914101,"img":"http://img31.mtime.cn/ph/2016/09/04/195829.17155789_1280X720X2.jpg","name":"吉尔·贝罗斯","nameEn":"Gil Bellows","roleImg":"http://img31.mtime.cn/mg/2014/03/06/100103.16916600.jpg","roleName":"汤米"},{"actorId":923631,"img":"http://img31.mtime.cn/ph/2016/06/01/102317.53025734_1280X720X2.jpg","name":"克兰西·布朗","nameEn":"Clancy Brown","roleImg":"http://img31.mtime.cn/mg/2014/03/06/100238.27497603.jpg","roleName":"赫德利"},{"actorId":980579,"img":"http://img31.mtime.cn/ph/2014/03/14/154055.16364112_1280X720X2.jpg","name":"马克·罗斯顿","nameEn":"Mark Rolston","roleImg":"http://img31.mtime.cn/mg/2014/03/06/095335.27207572.jpg","roleName":"鲍格斯"},{"actorId":927604,"img":"http://img31.mtime.cn/ph/2014/03/14/154055.36105401_1280X720X2.jpg","name":"祖德·塞克利拉","nameEn":"Jude Ciccolella","roleImg":"","roleName":"Guard Mert"},{"actorId":913477,"img":"http://img31.mtime.cn/ph/2016/08/31/132306.87833827_1280X720X2.jpg","name":"耐德·巴拉米","nameEn":"Ned Bellamy","roleImg":"","roleName":"Guard Youngblood"},{"actorId":985544,"img":"http://img31.mtime.cn/ph/2014/03/14/154055.60171327_1280X720X2.jpg","name":"杰弗瑞·德穆恩","nameEn":"Jeffrey DeMunn","roleImg":"","roleName":"1946 D.A."},{"actorId":989568,"img":"http://img31.mtime.cn/ph/2016/06/02/110028.47578651_1280X720X2.jpg","name":"","nameEn":"Larry Brandenburg","roleImg":"","roleName":"Skeet"},{"actorId":1013991,"img":"http://img31.mtime.cn/ph/1991/1013991/1013991_1280X720X2.jpg","name":"","nameEn":"Neil Giuntoli","roleImg":"","roleName":"Jigger"},{"actorId":965338,"img":"http://img31.mtime.cn/ph/1338/965338/965338_1280X720X2.jpg","name":"","nameEn":"Brian Libby","roleImg":"","roleName":"Floyd"},{"actorId":981250,"img":"http://img31.mtime.cn/ph/2014/03/14/154056.66087533_1280X720X2.jpg","name":"大卫·普罗瓦尔","nameEn":"David Proval","roleImg":"","roleName":"Snooze"},{"actorId":900770,"img":"http://img31.mtime.cn/ph/2016/05/16/115011.71106045_1280X720X2.jpg","name":"保罗·迈克格莱恩","nameEn":"Paul McCrane","roleImg":"","roleName":"Guard Trout"},{"actorId":1019549,"img":"http://img31.mtime.cn/ph/1549/1019549/1019549_1280X720X2.jpg","name":"","nameEn":"Renee Blaine","roleImg":"","roleName":"Andy Dufresne's Wife"},{"actorId":1019551,"img":"http://img31.mtime.cn/ph/1551/1019551/1019551_1280X720X2.jpg","name":"","nameEn":"Scott Mann","roleImg":"","roleName":"Glenn Quentin"},{"actorId":1008767,"img":"http://img31.mtime.cn/ph/767/1008767/1008767_1280X720X2.jpg","name":"","nameEn":"John Horton","roleImg":"","roleName":"1946 Judge"},{"actorId":993986,"img":"http://img31.mtime.cn/ph/1986/993986/993986_1280X720X2.jpg","name":"","nameEn":"Alfonso Freeman","roleImg":"","roleName":"Fresh Fish Con"},{"actorId":970393,"img":"http://img31.mtime.cn/ph/393/970393/970393_1280X720X2.jpg","name":"","nameEn":"V.J. Foster","roleImg":"","roleName":"Hungry Fish Con"}]
             * award : {"awardList":[{"festivalId":3,"nominateAwards":[{"awardName":"奥斯卡奖-最佳影片","festivalEventYear":"1995","persons":[{"nameCn":"","nameEn":"Niki Marvin","personId":1210720}],"sequenceNumber":67},{"awardName":"奥斯卡奖-最佳男主角","festivalEventYear":"1995","persons":[{"nameCn":"摩根·弗里曼","nameEn":"Morgan Freeman","personId":914747}],"sequenceNumber":67},{"awardName":"奥斯卡奖-最佳改编剧本","festivalEventYear":"1995","persons":[{"nameCn":"弗兰克·德拉邦特","nameEn":"Frank Darabont","personId":897895}],"sequenceNumber":67},{"awardName":"奥斯卡奖-最佳摄影","festivalEventYear":"1995","persons":[{"nameCn":"罗杰·狄金斯","nameEn":"Roger Deakins","personId":1162749}],"sequenceNumber":67},{"awardName":"奥斯卡奖-最佳音响","festivalEventYear":"1995","persons":[{"nameCn":"","nameEn":"Robert J. Litt","personId":1905448},{"nameCn":"","nameEn":"Elliot Tyson","personId":1905449},{"nameCn":"","nameEn":"Michael Herbick","personId":1905450},{"nameCn":"","nameEn":"Willie D. Burton","personId":1905315}],"sequenceNumber":67},{"awardName":"奥斯卡奖-最佳电影剪辑","festivalEventYear":"1995","persons":[{"nameCn":"","nameEn":"Richard Francis-Bruce","personId":1265229}],"sequenceNumber":67},{"awardName":"奥斯卡奖-最佳配乐","festivalEventYear":"1995","persons":[{"nameCn":"托马斯·纽曼","nameEn":"Thomas Newman","personId":1229462}],"sequenceNumber":67}],"nominateCount":7,"winAwards":[],"winCount":0},{"festivalId":15,"nominateAwards":[{"awardName":"电影类-剧情类最佳男主角","festivalEventYear":"1995","persons":[{"nameCn":"摩根·弗里曼","nameEn":"Morgan Freeman","personId":914747}],"sequenceNumber":52},{"awardName":"电影类-最佳编剧","festivalEventYear":"1995","persons":[{"nameCn":"弗兰克·德拉邦特","nameEn":"Frank Darabont","personId":897895}],"sequenceNumber":52}],"nominateCount":2,"winAwards":[],"winCount":0},{"festivalId":20,"nominateAwards":[],"nominateCount":0,"winAwards":[{"awardName":"最佳外语片","festivalEventYear":"1996","persons":[{"nameCn":"弗兰克·德拉邦特","nameEn":"Frank Darabont","personId":897895}],"sequenceNumber":19}],"winCount":1},{"festivalId":24,"nominateAwards":[{"awardName":"土星奖-最佳DVD/蓝光套装","festivalEventYear":"2016","persons":[],"sequenceNumber":42},{"awardName":"土星奖-最佳动作/冒险/惊悚电影","festivalEventYear":"1995","persons":[],"sequenceNumber":21},{"awardName":"土星奖-最佳编剧","festivalEventYear":"1995","persons":[{"nameCn":"弗兰克·德拉邦特","nameEn":"Frank Darabont","personId":897895}],"sequenceNumber":21}],"nominateCount":3,"winAwards":[],"winCount":0}],"totalNominateAward":12,"totalWinAward":1}
             * commentSpecial :
             * community : {}
             * director : {"directorId":897895,"img":"http://img31.mtime.cn/ph/2016/01/28/094446.79677444_1280X720X2.jpg","name":"弗兰克·德拉邦特","nameEn":"Frank Darabont"}
             * festivals : [{"festivalId":3,"img":"http://img31.mtime.cn/mg/2014/02/24/144331.32490714.jpg","nameCn":"奥斯卡金像奖","nameEn":"Academy AwardsBean","shortName":"奥斯卡"},{"festivalId":15,"img":"http://img31.mtime.cn/mg/2014/02/24/145735.69166081.jpg","nameCn":"美国金球奖","nameEn":"Golden Globes, USA","shortName":"金球奖"},{"festivalId":20,"img":"http://img31.mtime.cn/mg/2014/02/24/151023.22214126.jpg","nameCn":"日本电影学院奖","nameEn":"AwardsBean of the Japanese Academy","shortName":"日本学院奖"},{"festivalId":24,"img":"http://img31.mtime.cn/mg/2014/02/24/170521.92061793.jpg","nameCn":"土星奖","nameEn":"Saturn AwardsBean","shortName":"土星奖"}]
             * hotRanking : -1
             * img : http://img31.mtime.cn/mt/2014/03/07/123549.37376649_1280X720X2.jpg
             * is3D : false
             * isDMAX : false
             * isEggHunt : false
             * isFilter : false
             * isIMAX : false
             * isIMAX3D : false
             * isTicket : false
             * message : 该操作将清除您对该片的评分！是否确认？
             * mins : 142分钟
             * movieId : 12231
             * name : 肖申克的救赎
             * nameEn : The Shawshank Redemption
             * overallRating : 9.2
             * personCount : 126
             * quizGame : {}
             * releaseArea : 美国
             * releaseDate : 19940923
             * showCinemaCount : -1
             * showDay : -1
             * showtimeCount : -1
             * stageImg : {"count":114,"list":[{"imgId":359553,"imgUrl":"http://img31.mtime.cn/pi/2014/03/06/094947.29158501_1280X720X2.jpg"},{"imgId":347482,"imgUrl":"http://img31.mtime.cn/pi/2014/03/06/094947.15659323_1280X720X2.jpg"},{"imgId":401961,"imgUrl":"http://img31.mtime.cn/pi/2014/03/06/094947.13521527_1280X720X2.jpg"},{"imgId":401969,"imgUrl":"http://img31.mtime.cn/pi/2014/03/06/094947.23978212_1280X720X2.jpg"}]}
             * story : 银行家安迪因被陷害杀害妻子与她的情夫，被判两个终身监禁。入狱后，影片便以黑人狱友瑞德冷静的旁白来推进。监狱数十年如一日的改造会使原本自由的心灵习惯了牢笼的禁锢，而这也将是安迪的命运？
             * style : {"isLeadPage":0,"leadImg":"https://img2.mtime.cn/mg/.jpg","leadUrl":""}
             * totalNominateAward : 0
             * totalWinAward : 0
             * type : ["犯罪","剧情"]
             * url : https://movie.mtime.com/12231/
             * video : {"count":2,"hightUrl":"https://vfx.mtime.cn/Video/2014/03/06/mp4/140306102651231568.mp4","img":"http://img31.mtime.cn/mg/2014/03/06/101952.22948087_235X132X4.jpg","title":"肖申克的救赎 预告片","url":"https://vfx.mtime.cn/Video/2014/03/06/mp4/140306102651231568_480.mp4","videoId":48021}
             */

            private AwardBean award;
            private DirectorBean director;
            private String img;
            private String mins;
            private int movieId;
            private String name;
            private String nameEn;
            private double overallRating;
            private int personCount;
            private String releaseArea;
            private String releaseDate;
            private StageImgBean stageImg;
            private String story;
            private int totalNominateAward;
            private int totalWinAward;
            private String url;
            private VideoBean video;
            private java.util.List<ActorsBean> actors;
            private java.util.List<FestivalsBean> festivals;
            private java.util.List<String> type;

            public List<ActorsBean> getActors() {
                return actors;
            }

            public AwardBean getAward() {
                return award;
            }

            public DirectorBean getDirector() {
                return director;
            }

            public List<FestivalsBean> getFestivals() {
                return festivals;
            }

            public String getImg() {
                return img;
            }

            public String getMins() {
                return mins;
            }

            public int getMovieId() {
                return movieId;
            }

            public String getName() {
                return name;
            }

            public String getNameEn() {
                return nameEn;
            }

            public double getOverallRating() {
                return overallRating;
            }

            public int getPersonCount() {
                return personCount;
            }

            public String getReleaseArea() {
                return releaseArea;
            }

            public String getReleaseDate() {
                return releaseDate;
            }

            public StageImgBean getStageImg() {
                return stageImg;
            }

            public String getStory() {
                return story;
            }

            public int getTotalNominateAward() {
                return totalNominateAward;
            }

            public int getTotalWinAward() {
                return totalWinAward;
            }

            public List<String> getType() {
                return type;
            }

            public String getUrl() {
                return url;
            }

            public VideoBean getVideo() {
                return video;
            }

            public static class AwardBean extends AwardsBean {
                /**
                 * awardList : [{"festivalId":3,"nominateAwards":[{"awardName":"奥斯卡奖-最佳影片","festivalEventYear":"1995","persons":[{"nameCn":"","nameEn":"Niki Marvin","personId":1210720}],"sequenceNumber":67},{"awardName":"奥斯卡奖-最佳男主角","festivalEventYear":"1995","persons":[{"nameCn":"摩根·弗里曼","nameEn":"Morgan Freeman","personId":914747}],"sequenceNumber":67},{"awardName":"奥斯卡奖-最佳改编剧本","festivalEventYear":"1995","persons":[{"nameCn":"弗兰克·德拉邦特","nameEn":"Frank Darabont","personId":897895}],"sequenceNumber":67},{"awardName":"奥斯卡奖-最佳摄影","festivalEventYear":"1995","persons":[{"nameCn":"罗杰·狄金斯","nameEn":"Roger Deakins","personId":1162749}],"sequenceNumber":67},{"awardName":"奥斯卡奖-最佳音响","festivalEventYear":"1995","persons":[{"nameCn":"","nameEn":"Robert J. Litt","personId":1905448},{"nameCn":"","nameEn":"Elliot Tyson","personId":1905449},{"nameCn":"","nameEn":"Michael Herbick","personId":1905450},{"nameCn":"","nameEn":"Willie D. Burton","personId":1905315}],"sequenceNumber":67},{"awardName":"奥斯卡奖-最佳电影剪辑","festivalEventYear":"1995","persons":[{"nameCn":"","nameEn":"Richard Francis-Bruce","personId":1265229}],"sequenceNumber":67},{"awardName":"奥斯卡奖-最佳配乐","festivalEventYear":"1995","persons":[{"nameCn":"托马斯·纽曼","nameEn":"Thomas Newman","personId":1229462}],"sequenceNumber":67}],"nominateCount":7,"winAwards":[],"winCount":0},{"festivalId":15,"nominateAwards":[{"awardName":"电影类-剧情类最佳男主角","festivalEventYear":"1995","persons":[{"nameCn":"摩根·弗里曼","nameEn":"Morgan Freeman","personId":914747}],"sequenceNumber":52},{"awardName":"电影类-最佳编剧","festivalEventYear":"1995","persons":[{"nameCn":"弗兰克·德拉邦特","nameEn":"Frank Darabont","personId":897895}],"sequenceNumber":52}],"nominateCount":2,"winAwards":[],"winCount":0},{"festivalId":20,"nominateAwards":[],"nominateCount":0,"winAwards":[{"awardName":"最佳外语片","festivalEventYear":"1996","persons":[{"nameCn":"弗兰克·德拉邦特","nameEn":"Frank Darabont","personId":897895}],"sequenceNumber":19}],"winCount":1},{"festivalId":24,"nominateAwards":[{"awardName":"土星奖-最佳DVD/蓝光套装","festivalEventYear":"2016","persons":[],"sequenceNumber":42},{"awardName":"土星奖-最佳动作/冒险/惊悚电影","festivalEventYear":"1995","persons":[],"sequenceNumber":21},{"awardName":"土星奖-最佳编剧","festivalEventYear":"1995","persons":[{"nameCn":"弗兰克·德拉邦特","nameEn":"Frank Darabont","personId":897895}],"sequenceNumber":21}],"nominateCount":3,"winAwards":[],"winCount":0}]
                 * totalNominateAward : 12
                 * totalWinAward : 1
                 */

                private int totalNominateAward;
                private int totalWinAward;
                private java.util.List<AwardListBean> awardList;

                public int getTotalNominateAward() {
                    return totalNominateAward;
                }

                public void setTotalNominateAward(int totalNominateAward) {
                    this.totalNominateAward = totalNominateAward;
                }

                public int getTotalWinAward() {
                    return totalWinAward;
                }

                public void setTotalWinAward(int totalWinAward) {
                    this.totalWinAward = totalWinAward;
                }

                public List<AwardListBean> getAwardList() {
                    return awardList;
                }

                public void setAwardList(List<AwardListBean> awardList) {
                    this.awardList = awardList;
                }

                @Override
                public List<AwardListBean> getAwards() {
                    return awardList;
                }

                public static class AwardListBean extends Awards{
                    /**
                     * festivalId : 3
                     * nominateAwards : [{"awardName":"奥斯卡奖-最佳影片","festivalEventYear":"1995","persons":[{"nameCn":"","nameEn":"Niki Marvin","personId":1210720}],"sequenceNumber":67},{"awardName":"奥斯卡奖-最佳男主角","festivalEventYear":"1995","persons":[{"nameCn":"摩根·弗里曼","nameEn":"Morgan Freeman","personId":914747}],"sequenceNumber":67},{"awardName":"奥斯卡奖-最佳改编剧本","festivalEventYear":"1995","persons":[{"nameCn":"弗兰克·德拉邦特","nameEn":"Frank Darabont","personId":897895}],"sequenceNumber":67},{"awardName":"奥斯卡奖-最佳摄影","festivalEventYear":"1995","persons":[{"nameCn":"罗杰·狄金斯","nameEn":"Roger Deakins","personId":1162749}],"sequenceNumber":67},{"awardName":"奥斯卡奖-最佳音响","festivalEventYear":"1995","persons":[{"nameCn":"","nameEn":"Robert J. Litt","personId":1905448},{"nameCn":"","nameEn":"Elliot Tyson","personId":1905449},{"nameCn":"","nameEn":"Michael Herbick","personId":1905450},{"nameCn":"","nameEn":"Willie D. Burton","personId":1905315}],"sequenceNumber":67},{"awardName":"奥斯卡奖-最佳电影剪辑","festivalEventYear":"1995","persons":[{"nameCn":"","nameEn":"Richard Francis-Bruce","personId":1265229}],"sequenceNumber":67},{"awardName":"奥斯卡奖-最佳配乐","festivalEventYear":"1995","persons":[{"nameCn":"托马斯·纽曼","nameEn":"Thomas Newman","personId":1229462}],"sequenceNumber":67}]
                     * nominateCount : 7
                     * winAwards : []
                     * winCount : 0
                     */

                    private int festivalId;
                    private int nominateCount;
                    private int winCount;
                    private java.util.List<NominateAwardsBean> nominateAwards;
                    private java.util.List<WinAwardsBean> winAwards;

                    public int getFestivalId() {
                        return festivalId;
                    }

                    public void setFestivalId(int festivalId) {
                        this.festivalId = festivalId;
                    }

                    public int getNominateCount() {
                        return nominateCount;
                    }

                    public void setNominateCount(int nominateCount) {
                        this.nominateCount = nominateCount;
                    }

                    public int getWinCount() {
                        return winCount;
                    }

                    public void setWinCount(int winCount) {
                        this.winCount = winCount;
                    }

                    public List<NominateAwardsBean> getNominateAwards() {
                        return nominateAwards;
                    }

                    public void setNominateAwards(List<NominateAwardsBean> nominateAwards) {
                        this.nominateAwards = nominateAwards;
                    }

                    public List<WinAwardsBean> getWinAwards() {
                        return winAwards;
                    }

                    public void setWinAwards(List<WinAwardsBean> winAwards) {
                        this.winAwards = winAwards;
                    }

                    public static abstract class BaseAwardsBean{

                        public abstract String getAwardName();
                        public abstract String getFestivalEventYear();
                        public abstract int getSequenceNumber();
                        public abstract List<? extends BasePersonBean> getPersons();
                        public abstract void setIsTitle(boolean isTitle);
                        public abstract boolean isTitle();
                    }

                    public static abstract class BasePersonBean{
                        public abstract String getNameCn();
                        public abstract String getNameEn();
                        public abstract int getPersonId();
                    }

                    public static class NominateAwardsBean extends BaseAwards{
                        /**
                         * awardName : 奥斯卡奖-最佳影片
                         * festivalEventYear : 1995
                         * persons : [{"nameCn":"","nameEn":"Niki Marvin","personId":1210720}]
                         * sequenceNumber : 67
                         */

                        private String awardName;
                        private String festivalEventYear;
                        private int sequenceNumber;
                        private List<PersonsBean> persons;
                        private boolean isTitle;

                        public String getAwardName() {
                            return awardName;
                        }

                        public void setAwardName(String awardName) {
                            this.awardName = awardName;
                        }

                        public String getFestivalEventYear() {
                            return festivalEventYear;
                        }

                        public void setFestivalEventYear(String festivalEventYear) {
                            this.festivalEventYear = festivalEventYear;
                        }

                        public int getSequenceNumber() {
                            return sequenceNumber;
                        }

                        public void setSequenceNumber(int sequenceNumber) {
                            this.sequenceNumber = sequenceNumber;
                        }

                        public List<PersonsBean> getPersons() {
                            return persons;
                        }

                        @Override
                        public void setIsTitle(boolean isTitle) {
                            this.isTitle = isTitle;
                        }

                        @Override
                        public boolean isTitle() {
                            return isTitle;
                        }

                        public void setPersons(List<PersonsBean> persons) {
                            this.persons = persons;
                        }

                        public static class PersonsBean extends BasePerson{
                            /**
                             * nameCn :
                             * nameEn : Niki Marvin
                             * personId : 1210720
                             */

                            private String nameCn;
                            private String nameEn;
                            private int personId;

                            public String getNameCn() {
                                return nameCn;
                            }

                            public void setNameCn(String nameCn) {
                                this.nameCn = nameCn;
                            }

                            public String getNameEn() {
                                return nameEn;
                            }

                            public void setNameEn(String nameEn) {
                                this.nameEn = nameEn;
                            }

                            public int getPersonId() {
                                return personId;
                            }

                            public void setPersonId(int personId) {
                                this.personId = personId;
                            }
                        }
                    }

                    public static class WinAwardsBean extends BaseAwards{

                        /**
                         * awardName : 综艺系列节目 - 脱口秀/音乐秀-最佳综艺、音乐或喜剧特别节目
                         * festivalEventYear : 2005
                         * persons : [{"nameCn":"格伦·维斯","nameEn":"Glenn Weiss","personId":1408579},{"nameCn":"","nameEn":"Ricky Kirshner","personId":1919959}]
                         * sequenceNumber : 57
                         */

                        private String awardName;
                        private String festivalEventYear;
                        private int sequenceNumber;
                        private List<PersonsBean> persons;
                        private boolean isTitle ;

                        public String getAwardName() {
                            return awardName;
                        }

                        public void setAwardName(String awardName) {
                            this.awardName = awardName;
                        }

                        public String getFestivalEventYear() {
                            return festivalEventYear;
                        }

                        public void setFestivalEventYear(String festivalEventYear) {
                            this.festivalEventYear = festivalEventYear;
                        }

                        public int getSequenceNumber() {
                            return sequenceNumber;
                        }

                        public void setSequenceNumber(int sequenceNumber) {
                            this.sequenceNumber = sequenceNumber;
                        }

                        public List<PersonsBean> getPersons() {
                            return persons;
                        }

                        @Override
                        public void setIsTitle(boolean isTitle) {
                            this.isTitle = isTitle;
                        }

                        @Override
                        public boolean isTitle() {
                            return isTitle;
                        }

                        public void setPersons(List<PersonsBean> persons) {
                            this.persons = persons;
                        }

                        public static class PersonsBean extends BasePerson{
                            /**
                             * nameCn : 格伦·维斯
                             * nameEn : Glenn Weiss
                             * personId : 1408579
                             */

                            private String nameCn;
                            private String nameEn;
                            private int personId;

                            public String getNameCn() {
                                return nameCn;
                            }

                            public void setNameCn(String nameCn) {
                                this.nameCn = nameCn;
                            }

                            public String getNameEn() {
                                return nameEn;
                            }

                            public void setNameEn(String nameEn) {
                                this.nameEn = nameEn;
                            }

                            public int getPersonId() {
                                return personId;
                            }

                            public void setPersonId(int personId) {
                                this.personId = personId;
                            }
                        }
                    }
                }
            }

            public static class DirectorBean{

                /**
                 * directorId : 897895
                 * img : http://img31.mtime.cn/ph/2016/01/28/094446.79677444_1280X720X2.jpg
                 * name : 弗兰克·德拉邦特
                 * nameEn : Frank Darabont
                 */

                private int directorId;
                private String img;
                private String name;
                private String nameEn;

                public int getDirectorId() {
                    return directorId;
                }

                public void setDirectorId(int directorId) {
                    this.directorId = directorId;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getNameEn() {
                    return nameEn;
                }

                public void setNameEn(String nameEn) {
                    this.nameEn = nameEn;
                }
            }

            public static class StageImgBean{

                /**
                 * count : 114
                 * list : [{"imgId":359553,"imgUrl":"http://img31.mtime.cn/pi/2014/03/06/094947.29158501_1280X720X2.jpg"},{"imgId":347482,"imgUrl":"http://img31.mtime.cn/pi/2014/03/06/094947.15659323_1280X720X2.jpg"},{"imgId":401961,"imgUrl":"http://img31.mtime.cn/pi/2014/03/06/094947.13521527_1280X720X2.jpg"},{"imgId":401969,"imgUrl":"http://img31.mtime.cn/pi/2014/03/06/094947.23978212_1280X720X2.jpg"}]
                 */

                private int count;
                private List<ListBean> list;

                public int getCount() {
                    return count;
                }

                public void setCount(int count) {
                    this.count = count;
                }

                public List<ListBean> getList() {
                    return list;
                }

                public void setList(List<ListBean> list) {
                    this.list = list;
                }

                public static class ListBean {
                    /**
                     * imgId : 359553
                     * imgUrl : http://img31.mtime.cn/pi/2014/03/06/094947.29158501_1280X720X2.jpg
                     */

                    private int imgId;
                    private String imgUrl;

                    public int getImgId() {
                        return imgId;
                    }

                    public void setImgId(int imgId) {
                        this.imgId = imgId;
                    }

                    public String getImgUrl() {
                        return imgUrl;
                    }

                    public void setImgUrl(String imgUrl) {
                        this.imgUrl = imgUrl;
                    }
                }
            }

            public static class VideoBean{

                /**
                 * count : 2
                 * hightUrl : https://vfx.mtime.cn/Video/2014/03/06/mp4/140306102651231568.mp4
                 * img : http://img31.mtime.cn/mg/2014/03/06/101952.22948087_235X132X4.jpg
                 * title : 肖申克的救赎 预告片
                 * url : https://vfx.mtime.cn/Video/2014/03/06/mp4/140306102651231568_480.mp4
                 * videoId : 48021
                 */

                private int count;
                private String hightUrl;
                private String img;
                private String title;
                private String url;
                private int videoId;

                public int getCount() {
                    return count;
                }

                public void setCount(int count) {
                    this.count = count;
                }

                public String getHightUrl() {
                    return hightUrl;
                }

                public void setHightUrl(String hightUrl) {
                    this.hightUrl = hightUrl;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public int getVideoId() {
                    return videoId;
                }

                public void setVideoId(int videoId) {
                    this.videoId = videoId;
                }
            }

            public static class ActorsBean{

                /**
                 * actorId : 893026
                 * img : http://img31.mtime.cn/ph/2014/10/16/090902.78583267_1280X720X2.jpg
                 * name : 蒂姆·罗宾斯
                 * nameEn : Tim Robbins
                 * roleImg : http://img31.mtime.cn/mg/2014/03/06/095801.37640458.jpg
                 * roleName : 安迪
                 */

                private int actorId;
                private String img;
                private String name;
                private String nameEn;
                private String roleImg;
                private String roleName;

                public int getActorId() {
                    return actorId;
                }

                public void setActorId(int actorId) {
                    this.actorId = actorId;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getNameEn() {
                    return nameEn;
                }

                public void setNameEn(String nameEn) {
                    this.nameEn = nameEn;
                }

                public String getRoleImg() {
                    return roleImg;
                }

                public void setRoleImg(String roleImg) {
                    this.roleImg = roleImg;
                }

                public String getRoleName() {
                    return roleName;
                }

                public void setRoleName(String roleName) {
                    this.roleName = roleName;
                }
            }

            public static class FestivalsBean extends AwardsBean.BaseFestival{

                /**
                 * festivalId : 3
                 * img : http://img31.mtime.cn/mg/2014/02/24/144331.32490714.jpg
                 * nameCn : 奥斯卡金像奖
                 * nameEn : Academy AwardsBean
                 * shortName : 奥斯卡
                 */

                private int festivalId;
                private String img;
                private String nameCn;
                private String nameEn;
                private String shortName;

                public int getFestivalId() {
                    return festivalId;
                }

                public void setFestivalId(int festivalId) {
                    this.festivalId = festivalId;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                public String getNameCn() {
                    return nameCn;
                }

                public void setNameCn(String nameCn) {
                    this.nameCn = nameCn;
                }

                public String getNameEn() {
                    return nameEn;
                }

                public void setNameEn(String nameEn) {
                    this.nameEn = nameEn;
                }

                public String getShortName() {
                    return shortName;
                }

                public void setShortName(String shortName) {
                    this.shortName = shortName;
                }
            }

        }
        public static class BoxOfficeBean{


            /**
             * movieId : 200591
             * ranking : 2
             * todayBox : 367043488
             * todayBoxDes : 367.04
             * todayBoxDesUnit : 今日实时(万)
             * totalBox : 12972202593
             * totalBoxDes : 1.3
             * totalBoxUnit : 累计票房(亿)
             */

            private int movieId;
            private int ranking;
            private long todayBox;
            private String todayBoxDes;
            private String todayBoxDesUnit;
            private long totalBox;
            private String totalBoxDes;
            private String totalBoxUnit;

            public int getMovieId() {
                return movieId;
            }

            public void setMovieId(int movieId) {
                this.movieId = movieId;
            }

            public int getRanking() {
                return ranking;
            }

            public void setRanking(int ranking) {
                this.ranking = ranking;
            }

            public long getTodayBox() {
                return todayBox;
            }

            public void setTodayBox(int todayBox) {
                this.todayBox = todayBox;
            }

            public String getTodayBoxDes() {
                return todayBoxDes;
            }

            public void setTodayBoxDes(String todayBoxDes) {
                this.todayBoxDes = todayBoxDes;
            }

            public String getTodayBoxDesUnit() {
                return todayBoxDesUnit;
            }

            public void setTodayBoxDesUnit(String todayBoxDesUnit) {
                this.todayBoxDesUnit = todayBoxDesUnit;
            }

            public long getTotalBox() {
                return totalBox;
            }

            public void setTotalBox(long totalBox) {
                this.totalBox = totalBox;
            }

            public String getTotalBoxDes() {
                return totalBoxDes;
            }

            public void setTotalBoxDes(String totalBoxDes) {
                this.totalBoxDes = totalBoxDes;
            }

            public String getTotalBoxUnit() {
                return totalBoxUnit;
            }

            public void setTotalBoxUnit(String totalBoxUnit) {
                this.totalBoxUnit = totalBoxUnit;
            }
        }
        public static class LiveBean{

            /**
             * count : 1
             * img : http://img5.mtime.cn/mg/2017/02/20/120726.13046138.jpg
             * liveId : 275
             * playNumTag : 30.5万次播放
             * playTag :
             * status : 4
             * title : 《刺客信条》北京发布会
             */

            private int count;
            private String img;
            private int liveId;
            private String playNumTag;
            private String playTag;
            private int status;
            private String title;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getLiveId() {
                return liveId;
            }

            public void setLiveId(int liveId) {
                this.liveId = liveId;
            }

            public String getPlayNumTag() {
                return playNumTag;
            }

            public void setPlayNumTag(String playNumTag) {
                this.playNumTag = playNumTag;
            }

            public String getPlayTag() {
                return playTag;
            }

            public void setPlayTag(String playTag) {
                this.playTag = playTag;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
