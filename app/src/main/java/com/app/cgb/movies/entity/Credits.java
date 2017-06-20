package com.app.cgb.moviepreview.entity;

import java.util.List;

public class Credits {


    private List<TypesBean> types;

    public List<TypesBean> getTypes() {
        return types;
    }

    public void setTypes(List<TypesBean> types) {
        this.types = types;
    }

    public static class TypesBean {
        /**
         * typeName : 演员
         * typeNameEn : Actor
         * persons : [{"id":1098276,"name":"克里斯·帕拉特","nameEn":"Chris Pratt","image":"http://img31.mtime.cn/ph/2014/10/17/170007.63597396_1280X720X2.jpg","personate":"星爵","personateCn":"星爵","personateEn":"Peter Quill /              Star-Lord","roleCover":"http://img5.mtime.cn/mg/2017/04/14/161406.23550100_120X120X4.jpg"},{"id":913516,"name":"佐伊·索尔达娜","nameEn":"Zoe Saldana","image":"http://img31.mtime.cn/ph/2014/03/14/153218.69564396_1280X720X2.jpg","personate":"卡魔拉","personateCn":"卡魔拉","personateEn":"Gamora","roleCover":"http://img5.mtime.cn/mg/2017/04/14/161423.29045331_120X120X4.jpg"},{"id":917063,"name":"戴夫·巴蒂斯塔","nameEn":"Dave Bautista","image":"http://img31.mtime.cn/ph/2014/03/19/170357.43056139_1280X720X2.jpg","personate":"德拉克斯","personateCn":"德拉克斯","personateEn":"Drax","roleCover":"http://img5.mtime.cn/mg/2017/04/14/161441.71806468_120X120X4.jpg"},{"id":913695,"name":"布莱德利·库珀","nameEn":"Bradley Cooper","image":"http://img31.mtime.cn/ph/2014/03/16/161323.55642433_1280X720X2.jpg","personate":"火箭浣熊（配音）","personateCn":"火箭浣熊（配音）","personateEn":"Rocket       (voice)","roleCover":"http://img5.mtime.cn/mg/2017/04/14/161555.97421057_120X120X4.jpg"},{"id":913378,"name":"范·迪塞尔","nameEn":"Vin Diesel","image":"http://img31.mtime.cn/ph/2014/09/01/170748.64755972_1280X720X2.jpg","personate":"格鲁特宝宝（配音）","personateCn":"格鲁特宝宝（配音）","personateEn":"Baby Groot       (voice)","roleCover":"http://img5.mtime.cn/mg/2017/04/14/161450.67649222_120X120X4.jpg"},{"id":928469,"name":"迈克尔·鲁克","nameEn":"Michael Rooker","image":"http://img31.mtime.cn/ph/2014/02/22/201150.73881890_1280X720X2.jpg","personate":"勇度","personateCn":"勇度","personateEn":"Yondu","roleCover":"http://img5.mtime.cn/mg/2017/04/14/161500.73904708_120X120X4.jpg"},{"id":901235,"name":"库尔特·拉塞尔","nameEn":"Kurt Russell","image":"http://img31.mtime.cn/ph/2015/04/27/162743.33568954_1280X720X2.jpg","personate":"伊戈","personateCn":"伊戈","personateEn":"Ego","roleCover":"http://img5.mtime.cn/mg/2017/04/14/161943.89973303_120X120X4.jpg"},{"id":1426693,"name":"凯伦·吉兰","nameEn":"Karen Gillan","image":"http://img31.mtime.cn/ph/2014/04/29/151443.48684426_1280X720X2.jpg","personate":"星云涅布拉","personateCn":"星云涅布拉","personateEn":"Nebula","roleCover":"http://img5.mtime.cn/mg/2017/04/14/161711.71685511_120X120X4.jpg"},{"id":1494016,"name":"庞·克莱门捷夫","nameEn":"Pom Klementieff","image":"http://img31.mtime.cn/ph/2015/10/29/105909.62492592_1280X720X2.jpg","personate":"曼提斯","personateCn":"曼提斯","personateEn":"Mantis","roleCover":"http://img5.mtime.cn/mg/2017/04/14/161934.90698121_120X120X4.jpg"},{"id":1860786,"name":"伊丽莎白·德比齐","nameEn":"Elizabeth Debicki","image":"http://img31.mtime.cn/ph/2014/03/14/153559.77544956_1280X720X2.jpg","personate":"阿耶莎","personateCn":"阿耶莎","personateEn":"Ayesha","roleCover":"http://img5.mtime.cn/mg/2017/04/14/161801.84235744_120X120X4.jpg"},{"id":1476646,"name":"克里斯·沙利文","nameEn":"Chris Sullivan","image":"http://img31.mtime.cn/ph/2014/12/02/141953.11621742_1280X720X2.jpg","personate":"杀冒脸","personateCn":"杀冒脸","personateEn":"Taserface"},{"id":987578,"name":"西恩·古恩","nameEn":"Sean Gunn","image":"http://img31.mtime.cn/ph/2016/08/29/111255.84034310_1280X720X2.jpg","personate":"克拉格林","personateCn":"克拉格林","personateEn":"Kraglin /              On-Set Rocket"},{"id":897254,"name":"西尔维斯特·史泰龙","nameEn":"Sylvester Stallone","image":"http://img31.mtime.cn/ph/2014/03/16/124411.15571286_1280X720X2.jpg","personate":"斯塔卡","personateCn":"斯塔卡","personateEn":"Stakar Ogord"},{"id":914292,"name":"汤米·弗拉纳根","nameEn":"Tommy Flanagan","image":"http://img31.mtime.cn/ph/2015/10/28/103249.82997420_1280X720X2.jpg","personate":"图立克","personateCn":"图立克","personateEn":"Tullk"},{"id":1517504,"name":"劳拉·哈德克","nameEn":"Laura Haddock","image":"http://img5.mtime.cn/ph/2017/02/06/144615.92450623_1280X720X2.jpg","personate":"梅瑞蒂斯·奎尔","personateCn":"梅瑞蒂斯·奎尔","personateEn":"Meredith Quill"},{"id":2046835,"name":"瓦耶特·奥莱夫","nameEn":"Wyatt Oleff","image":"http://img31.mtime.cn/ph/2014/10/14/153330.24927552_1280X720X2.jpg","personate":"Young Peter Quill","personateCn":"","personateEn":"Young Peter Quill"},{"id":1023348,"name":"","nameEn":"Aaron Schwartz","image":"http://img31.mtime.cn/ph/2016/08/29/112317.70650692_1280X720X2.jpg","personate":"Young Ego Facial Reference","personateCn":"","personateEn":"Young Ego Facial Reference"},{"id":2089902,"name":"","nameEn":"Elizabeth Ludlow","image":"http://img31.mtime.cn/ph/2016/08/29/112519.76760950_1280X720X2.jpg","personate":"Easik Mother","personateCn":"","personateEn":"Easik Mother"},{"id":1291985,"name":"阿拉米斯·奈特","nameEn":"Aramis Knight","image":"http://img31.mtime.cn/ph/2015/05/18/141854.43662997_1280X720X2.jpg","personate":"Boy on Star-Lord's Ship       (uncredited)","personateCn":"","personateEn":"Boy on Star-Lord's Ship       (uncredited)"},{"id":2153272,"name":"","nameEn":"Hilty Bowen","image":"http://img31.mtime.cn/ph/2016/08/29/112739.29416725_1280X720X2.jpg","personate":"Sovereign Pilot","personateCn":"","personateEn":"Sovereign Pilot"},{"id":2219104,"name":"","nameEn":"Cheyanna Lavon Zubas","image":"http://img31.mtime.cn/ph/2016/08/29/112827.46370323_1280X720X2.jpg","personate":"Robot Courtesan       (uncredited)","personateCn":"","personateEn":"Robot Courtesan       (uncredited)"},{"id":1019755,"name":"","nameEn":"Terrence Rosemore","image":"http://img31.mtime.cn/ph/2016/06/02/162945.80194130_1280X720X2.jpg","personate":"Narblik","personateCn":"","personateEn":"Narblik"},{"id":2216149,"name":"","nameEn":"Damita Jane Howard","image":"http://img31.mtime.cn/ph/149/2216149/2216149_1280X720X2.jpg","personate":"Grandpa Quill's Friend","personateCn":"","personateEn":"Grandpa Quill's Friend"},{"id":2219105,"name":"","nameEn":"Don Johnson","image":"http://img31.mtime.cn/ph/1105/2219105/2219105_1280X720X2.jpg","personate":"Ravager       (uncredited)","personateCn":"","personateEn":"Ravager       (uncredited)"},{"id":2083641,"name":"","nameEn":"Jason Speer","image":"http://img31.mtime.cn/ph/2016/03/14/171523.55519740_1280X720X2.jpg","personate":"Weak Ravager       (uncredited)","personateCn":"","personateEn":"Weak Ravager       (uncredited)"},{"id":2096103,"name":"","nameEn":"Donny Carrington","image":"http://img31.mtime.cn/ph/103/2096103/2096103_1280X720X2.jpg","personate":"Kree Monk       (uncredited)","personateCn":"","personateEn":"Kree Monk       (uncredited)"},{"id":2172541,"name":"","nameEn":"Brian Clackley","image":"http://img5.mtime.cn/ph/2017/05/04/155911.52190073_1280X720X2.jpg","personate":"Alien       (uncredited)","personateCn":"","personateEn":"Alien       (uncredited)"},{"id":2096105,"name":"","nameEn":"Nea Dune","image":"http://img31.mtime.cn/ph/2016/05/10/164331.98811034_1280X720X2.jpg","personate":"Lovebot       (uncredited)","personateCn":"","personateEn":"Lovebot       (uncredited)"},{"id":2110972,"name":"","nameEn":"Tahseen Ghauri","image":"http://img31.mtime.cn/ph/972/2110972/2110972_1280X720X2.jpg","personate":"Pedestrian Runner       (uncredited)","personateCn":"","personateEn":"Pedestrian Runner       (uncredited)"},{"id":2112665,"name":"","nameEn":"Alphonso A'Qen-Aten Jackson","image":"http://img31.mtime.cn/ph/2014/12/26/111540.78396867_1280X720X2.jpg","personate":"Ravager       (uncredited)","personateCn":"","personateEn":"Ravager       (uncredited)"},{"id":2219106,"name":"","nameEn":"My'Chyl Purr","image":"http://img31.mtime.cn/ph/1106/2219106/2219106_1280X720X2.jpg","personate":"Krylorian       (uncredited)","personateCn":"","personateEn":"Krylorian       (uncredited)"},{"id":897567,"name":"斯坦·李","nameEn":"Stan Lee","image":"http://img31.mtime.cn/ph/2014/03/14/152325.59284310_1280X720X2.jpg","personate":"Astronaut       (uncredited)","personateCn":"","personateEn":"Astronaut       (uncredited)"},{"id":913954,"name":"","nameEn":"Evan Jones","image":"http://img31.mtime.cn/ph/1954/913954/913954_1280X720X2.jpg","personate":"Retch","personateCn":"","personateEn":"Retch"},{"id":2117231,"name":"","nameEn":"Kelly Richardson","image":"http://img31.mtime.cn/ph/1231/2117231/2117231_1280X720X2.jpg","personate":"Daisy Kiu       (uncredited)","personateCn":"","personateEn":"Daisy Kiu       (uncredited)"},{"id":913650,"name":"罗达·格里菲斯","nameEn":"Rhoda Griffis","image":"http://img31.mtime.cn/ph/2016/08/30/145137.12543440_1280X720X2.jpg","personate":"Sneeper Madame","personateCn":"","personateEn":"Sneeper Madame"},{"id":1227895,"name":"史蒂夫·艾吉","nameEn":"Steve Agee","image":"http://img31.mtime.cn/ph/1895/1227895/1227895_1280X720X2.jpg","personate":"Gef","personateCn":"","personateEn":"Gef"},{"id":2108740,"name":"","nameEn":"Anthony J Sacco","image":"http://img31.mtime.cn/ph/2016/03/14/170814.16253927_1280X720X2.jpg","personate":"Man on the Street       (uncredited)","personateCn":"","personateEn":"Man on the Street       (uncredited)"},{"id":951787,"name":"","nameEn":"Sebastian Siegel","image":"http://img31.mtime.cn/ph/1787/951787/951787_1280X720X2.jpg","personate":"Guard of the Sovereign Nation       (uncredited)","personateCn":"","personateEn":"Guard of the Sovereign Nation       (uncredited)"},{"id":918875,"name":"","nameEn":"Joe Fria","image":"http://img31.mtime.cn/ph/875/918875/918875_1280X720X2.jpg","personate":"Oblo","personateCn":"","personateEn":"Oblo"},{"id":918879,"name":"","nameEn":"Stephen Blackehart","image":"http://img31.mtime.cn/ph/2014/10/17/104952.24723026_1280X720X2.jpg","personate":"Brahl","personateCn":"","personateEn":"Brahl"},{"id":2245820,"name":"","nameEn":"Hannah Gottesman","image":"http://img31.mtime.cn/ph/1820/2245820/2245820_1280X720X2.jpg","personate":"Sovereign Chambermaid","personateCn":"","personateEn":"Sovereign Chambermaid"},{"id":1562247,"name":"","nameEn":"Jason Williams","image":"http://img31.mtime.cn/ph/247/1562247/1562247_1280X720X2.jpg","personate":"Huge Guard of the Sovereign Nation       (uncredited)","personateCn":"","personateEn":"Huge Guard of the Sovereign Nation       (uncredited)"},{"id":2189435,"name":"","nameEn":"Donald K. Overstreet","image":"http://img31.mtime.cn/ph/1435/2189435/2189435_1280X720X2.jpg","personate":"Aakon Ravager       (uncredited)","personateCn":"","personateEn":"Aakon Ravager       (uncredited)"},{"id":2154526,"name":"","nameEn":"Josh Tipis","image":"http://img31.mtime.cn/ph/526/2154526/2154526_1280X720X2.jpg","personate":"Humanoid Ravager       (uncredited)","personateCn":"","personateEn":"Humanoid Ravager       (uncredited)"},{"id":913097,"name":"迈克尔·罗森巴姆","nameEn":"Michael Rosenbaum","image":"http://img31.mtime.cn/ph/2014/02/22/195117.60118047_1280X720X2.jpg","personate":"Martinex","personateCn":"","personateEn":"Martinex"},{"id":910371,"name":"本·布劳德","nameEn":"Ben Browder","image":"http://img31.mtime.cn/ph/2014/02/22/194722.97688133_1280X720X2.jpg","personate":"Sovereign Admiral","personateCn":"","personateEn":"Sovereign Admiral"},{"id":1898842,"name":"","nameEn":"Alex Klein","image":"http://img31.mtime.cn/ph/842/1898842/1898842_1280X720X2.jpg","personate":"Zylak","personateCn":"","personateEn":"Zylak"},{"id":1570459,"name":"卢克·库克","nameEn":"Luke Cook","image":"http://img31.mtime.cn/ph/2014/02/22/220834.91392077_1280X720X2.jpg","personate":"Zylak's Frenemy","personateCn":"","personateEn":"Zylak's Frenemy"},{"id":2268962,"name":"","nameEn":"Blondy Baruti","image":"http://img31.mtime.cn/ph/962/2268962/2268962_1280X720X2.jpg","personate":"Huhtar","personateCn":"","personateEn":"Huhtar"},{"id":1161361,"name":"","nameEn":"Richard Christy","image":"http://img31.mtime.cn/ph/1361/1161361/1161361_1280X720X2.jpg","personate":"Down There!","personateCn":"","personateEn":"Down There!"},{"id":905856,"name":"罗布·赞比","nameEn":"Rob Zombie","image":"http://img31.mtime.cn/ph/2014/10/17/114620.78323466_1280X720X2.jpg","personate":"Unseen Ravager","personateCn":"","personateEn":"Unseen Ravager"},{"id":2172975,"name":"","nameEn":"Sierra Love","image":"http://img31.mtime.cn/ph/975/2172975/2172975_1280X720X2.jpg","personate":"Robot Courtesan","personateCn":"","personateEn":"Robot Courtesan"},{"id":2012173,"name":"","nameEn":"Kendra Staub","image":"http://img31.mtime.cn/ph/173/2012173/2012173_1280X720X2.jpg","personate":"Robot Courtesan       (as Kendra Staub)","personateCn":"","personateEn":"Robot Courtesan       (as Kendra Staub)"},{"id":2009388,"name":"","nameEn":"Milynn Sarley","image":"http://img31.mtime.cn/ph/1388/2009388/2009388_1280X720X2.jpg","personate":"Robot Courtesan","personateCn":"","personateEn":"Robot Courtesan"},{"id":2180082,"name":"","nameEn":"Mac Wells","image":"http://img31.mtime.cn/ph/82/2180082/2180082_1280X720X2.jpg","personate":"Officer Fitzgibbon","personateCn":"","personateEn":"Officer Fitzgibbon"},{"id":2268963,"name":"","nameEn":"Jim Gunn Sr.","image":"http://img31.mtime.cn/ph/963/2268963/2268963_1280X720X2.jpg","personate":"Weird Old Man","personateCn":"","personateEn":"Weird Old Man"},{"id":2268964,"name":"","nameEn":"Leota Gunn","image":"http://img31.mtime.cn/ph/964/2268964/2268964_1280X720X2.jpg","personate":"Weird Old Man's Mistress","personateCn":"","personateEn":"Weird Old Man's Mistress"},{"id":950479,"name":"格雷格·亨利","nameEn":"Gregg Henry","image":"http://img31.mtime.cn/ph/2014/02/22/202858.63682685_1280X720X2.jpg","personate":"Grandpa Quill","personateCn":"","personateEn":"Grandpa Quill"},{"id":937544,"name":"麦莉·赛勒斯","nameEn":"Miley Cyrus","image":"http://img31.mtime.cn/ph/2014/03/14/154023.69220624_1280X720X2.jpg","personate":"Mainframe       (voice) (uncredited)","personateCn":"","personateEn":"Mainframe       (voice) (uncredited)"},{"id":2271825,"name":"","nameEn":"Jimmy Urine","image":"http://img31.mtime.cn/ph/1825/2271825/2271825_1280X720X2.jpg","personate":"Halfnut","personateCn":"","personateEn":"Halfnut"},{"id":930360,"name":"赛斯·格林","nameEn":"Seth Green","image":"http://img31.mtime.cn/ph/2014/02/22/201406.19311536_1280X720X2.jpg","personate":"Howard the Duck       (voice)","personateCn":"","personateEn":"Howard the Duck       (voice)"},{"id":911754,"name":"文·瑞姆斯","nameEn":"Ving Rhames","image":"http://img31.mtime.cn/ph/2014/04/30/111024.18600765_1280X720X2.jpg","personate":"Charlie-27","personateCn":"","personateEn":"Charlie-27"},{"id":912937,"name":"杨紫琼","nameEn":"Michelle Yeoh","image":"http://img31.mtime.cn/ph/2015/08/06/091417.71090541_1280X720X2.jpg","personate":"Aleta Ogord","personateCn":"","personateEn":"Aleta Ogord"},{"id":2043710,"name":"","nameEn":"Fred Galle","image":"http://img31.mtime.cn/ph/2014/12/26/111103.40099121_1280X720X2.jpg","personate":"Sovereign Leader       (uncredited)","personateCn":"","personateEn":"Sovereign Leader       (uncredited)"},{"id":925777,"name":"杰夫·高布伦","nameEn":"Jeff Goldblum","image":"http://img31.mtime.cn/ph/2016/06/08/091335.51168616_1280X720X2.jpg","personate":"Grandmaster       (uncredited)","personateCn":"","personateEn":"Grandmaster       (uncredited)"},{"id":923633,"name":"大卫·哈塞尔霍夫","nameEn":"David Hasselhoff","image":"http://img31.mtime.cn/ph/2014/02/22/200536.62656713_1280X720X2.jpg","personate":"Zardu Hasslefrau       (uncredited)","personateCn":"","personateEn":"Zardu Hasslefrau       (uncredited)"},{"id":1904753,"name":"吉列尔莫·罗德里格斯","nameEn":"Guillermo Rodriguez","image":"http://img5.mtime.cn/ph/2017/02/27/115759.12952150_1280X720X2.jpg","personate":"Cop #2       (uncredited)","personateCn":"","personateEn":"Cop #2       (uncredited)"},{"id":2271826,"name":"","nameEn":"Stephen Vining","image":"http://img31.mtime.cn/ph/1826/2271826/2271826_1280X720X2.jpg","personate":"Ravager #7       (uncredited)","personateCn":"","personateEn":"Ravager #7       (uncredited)"}]
         */

        private String typeName;
        private String typeNameEn;
        private List<PersonsBean> persons;

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getTypeNameEn() {
            return typeNameEn;
        }

        public void setTypeNameEn(String typeNameEn) {
            this.typeNameEn = typeNameEn;
        }

        public List<PersonsBean> getPersons() {
            return persons;
        }

        public void setPersons(List<PersonsBean> persons) {
            this.persons = persons;
        }

        public static class PersonsBean {
            /**
             * id : 1098276
             * name : 克里斯·帕拉特
             * nameEn : Chris Pratt
             * image : http://img31.mtime.cn/ph/2014/10/17/170007.63597396_1280X720X2.jpg
             * personate : 星爵
             * personateCn : 星爵
             * personateEn : Peter Quill /              Star-Lord
             * roleCover : http://img5.mtime.cn/mg/2017/04/14/161406.23550100_120X120X4.jpg
             */

            private int id;
            private String name;
            private String nameEn;
            private String image;
            private String personate;
            private String personateCn;
            private String personateEn;
            private String roleCover;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
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

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getPersonate() {
                return personate;
            }

            public void setPersonate(String personate) {
                this.personate = personate;
            }

            public String getPersonateCn() {
                return personateCn;
            }

            public void setPersonateCn(String personateCn) {
                this.personateCn = personateCn;
            }

            public String getPersonateEn() {
                return personateEn;
            }

            public void setPersonateEn(String personateEn) {
                this.personateEn = personateEn;
            }

            public String getRoleCover() {
                return roleCover;
            }

            public void setRoleCover(String roleCover) {
                this.roleCover = roleCover;
            }
        }
    }
}
