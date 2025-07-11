package com.codingtu.cooltu.lib4j.fake;

import com.codingtu.cooltu.lib4j.tool.OtherTool;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Fake {

    /************************************************
     *
     *  随机类创建
     *
     ************************************************/
    private static final Random RANDOM = new Random();

    public static int nextInt(int bound) {
        return RANDOM.nextInt(bound);
    }

    public static int nextInt(int start, int end) {
        if (start == end)
            return start;
        if (start > end) {
            int temp = start;
            start = end;
            end = temp;
        }
        int i = RANDOM.nextInt(end - start);
        return start + i;
    }

    public static float nexFloat(int start, int end, int bits) {
        int base = 1;
        for (int i = 0; i < bits; i++) {
            base *= 10;
        }
        int i = nextInt(start * base, end * base);
        return i / (base * 1f);
    }

    public static String str(String... objs) {
        return objs[nextInt(objs.length)];
    }

    public static Object obj(Object... objs) {
        return objs[nextInt(objs.length)];
    }

    public static int integer(int... objs) {
        return objs[nextInt(objs.length)];
    }

    /************************************************
     *
     * 名字们
     *
     ************************************************/
    public static final String[] LAST_NAMES = {
            "澄邈", "德泽", "海超", "海阳", "海荣", "海逸", "海昌", "瀚钰", "瀚文", "涵亮", "涵煦", "明宇", "涵衍", "浩皛",
            "浩波", "浩博", "浩初", "浩宕", "浩歌", "浩广", "浩邈", "浩气", "浩思", "浩言", "鸿宝", "鸿波", "鸿博", "鸿才",
            "鸿畅", "鸿畴", "鸿达", "鸿德", "鸿飞", "鸿风", "鸿福", "鸿光", "鸿晖", "鸿朗", "鸿文", "鸿轩", "鸿煊", "鸿骞",
            "惜萍", "初之", "宛丝", "寄南", "小萍", "静珊", "千风", "天蓉", "雅青", "寄文", "涵菱", "香波", "青亦", "元菱",
            "翠彤", "春海", "惜珊", "向薇", "冬灵", "惜芹", "凌青", "谷芹", "雁桃", "映雁", "书兰", "盼香", "梅致", "寄风",
            "芳荷", "绮晴", "映之", "醉波", "幻莲", "晓昕", "傲柔", "寄容", "以珊", "紫雪", "芷容", "书琴", "美伊", "涵阳",
            "怀寒", "易云", "代秋", "惜梦", "宇涵", "谷槐", "怀莲", "英莲", "芷卉", "向彤", "新巧", "语海", "灵珊", "凝丹"
    };
    public static final String[] FIRST_NAMES = {
            "赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "蒋", "沈", "韩", "杨", "朱", "秦", "何", "吕", "张", "曹",
            "金", "魏", "陶", "姜", "苏", "范", "彭", "郎", "鲁", "韦", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳", "鲍",
            "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷", "罗", "毕", "郝", "邬", "安", "常", "乐", "于", "傅",
            "皮", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和", "穆", "尹", "姚", "汪", "祁", "毛", "禹", "宋"
    };

    private static final String[] NAMES_NET = new String[]{
            "旧景依旧", "浪推晚风", "屿南凉北", "南音少女", "冷面酷血", "傲视之巅", "灵魂摆渡", "三好骚年", "飞花似梦", "雨敲疏棂", "古月存风",
            "樱空之雪", "左哼哼", "独步杀戮", "十月男神", "似水往昔", "逍遥无痕", "谦谦世界", "孤影", "芈沂", "白鸥掠海", "初心未改", "盛夏光年",
            "沙漠之寂", "半暮未凉", "轻衣白衫", "森林牧歌", "花的舞步", "颜汐", "满城风絮", "鱼雁音书", "逆心者", "江城子", "無樂不歡", "惢之淚",
            "长风为骨", "萌叽", "浅陌初心", "罂花°雨", "南风与歌", "音梦", "竹琳听雨", "冷清清", "萌面侠", "暖南倾绿", "清风归客", "耀世天下",
            "雪舞倾城", "傲视狂杀", "梦之神", "巴斯光年", "清风无痕", "夜的海", "墨雨云烟", "书无诗章", "紫鸢斷情", "夏至微凉", "执手踏青",
            "撩心惯犯", "半透明色", "艺坛翘楚", "君永恒", "南笙孤风", "一川烟草", "品茗听雪", "操控可爱", "残花泽", "依旧安然", "千城墨白",
            "恋雪晨曦", "魔法唧唧", "寸心幽兰", "龙爷无敌", "荒岛亡心", "森林屿麓", "雨灵", "凉城独酒", "残影", "颠覆你的高傲", "枭雄在世",
            "笑傲苍生", "紫夜天狼", "遍地狼烟", "眼神秒杀一切", "一世霸气", "凮殘あ淩度", "萌面大叔", "乱我红尘路", "流光追不及", "皓月冷千山",
            "独爱流浪", "月下清风错", "随风飘远", "触碰岁月", "漫步あ雨中", "浮伤年华", "花生哥哥", "逍遥无痕", "秋ㄨ落尽", "蓝米小修", "杀戮之心",
            "依舊悲傷落", "一晚八连杀", "我叫小祸害", "光影小生", "龙傲天下", "独走孤獨/城", "夜、孤独", "傲剑笑九州", "兲陰冷随風", "几经风月",
            "寒闪图寒", "孤傲白狼", "过眼云烟", "南城旧时", "喪风", "赤魂杀戮", "无尽思念", "羡煞众生", "烟敛寒林", "十步杀一人", "逍遥云飞",
            "千里山河", "嗜血超神", "迊颩、榌舞", "老子今天帅极了", "星逝破晓", "奔跑的尘埃", "随性洒脱", "旧歌如梦", "那些曾經", "冷血刺客",
            "沽酒杯空影", "无限恐怖", "墨色的瞳暗", "冷情ゞ戒嗳"};

    public static String name() {
        return new StringBuilder().append(str(FIRST_NAMES)).append(str(LAST_NAMES)).toString();
    }

    public static String nameInNet() {
        return str(NAMES_NET);
    }

    public static List<String> names() {
        return names(20);
    }

    public static List<String> names(int size) {
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            names.add(name());
        }
        return names;
    }

    public static List<String> namesInNet() {
        return namesInNet(20);
    }

    public static List<String> namesInNet(int size) {
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            names.add(nameInNet());
        }
        return names;
    }

    /************************************************
     *
     * 图片们
     *
     ************************************************/

    private static final String[] IMAGE_URLS = new String[]{
            "https://hbimg.huabanimg.com/866a4585f910c6dc9154c963558a2966986fc4c81ac15-SzRU50_fw658",
            "https://hbimg.huabanimg.com/4bd51699eeec1e2d96c9bdea35caae6669d3f0e8595f7-RKbGpr_fw658",
            "https://hbimg.huabanimg.com/3171c50de3ed82253b8c35e43e6543ba35c0ad7e34ef5-fVvcao_fw658",
            "https://hbimg.huabanimg.com/10c9402e2a06ed8742828346476a450e3e28a8aed7a16-GHufmM_fw658",
            "https://hbimg.huabanimg.com/ffe53a7ff977d8aaf38e9575e0392d9ca1c0931de229-xyi8RM_fw658",
            "https://hbimg.huabanimg.com/0981c8cb85a74709dc0e2d6aba4afbfa9603e0066e63-dq1uEz_fw658",
            "https://hbimg.huabanimg.com/c967fde1d00913d8966c68e4ff686419e1c9db06123fe6-aOQTpj_fw658",
            "https://hbimg.huabanimg.com/5e3f7e19c58bb7b1097583b5f349927f5a66d163dea2c-BRgX09_fw658",
            "https://hbimg.huabanimg.com/7a790823b1a1990a87205cd1c905d856a5dd01f6419a3-CTJ6xx_fw658",
            "https://hbimg.huabanimg.com/78628db36eb16c8672bcf32fd62795bae04e17fc221f08-2F0tGU_fw658",
            "https://hbimg.huabanimg.com/17629f9d641acbc45e8c6ca4e00319dbcb35932c1b5aa-oPrUyq_fw658",
            "https://hbimg.huabanimg.com/f16302cd35224ec4486ced35a8f61fc5aa2fe1c1276cf-QTQSfc_fw658",
            "https://hbimg.huabanimg.com/d697e0b91865bd0b689f653a8c159571bf495397198ce-vgyERc_fw658",
            "https://hbimg.huabanimg.com/a416f0ad535c0756168e9e88efc1e23efb4b7a87156da-evfSzC_fw658",
            "https://hbimg.huabanimg.com/176cffc8518547eef9a57b148429602f7fdeada942eb3-f4eXnG_fw658",
            "https://hbimg.huabanimg.com/8846087dfe324a4bd2cdfa83c31bee401806080d94da-UCmam1_fw658",
            "https://hbimg.huabanimg.com/86c813efc778370ceeb76ba66e59f0e424ca1eee2e4ca-NCNiRo_fw658",
            "https://hbimg.huabanimg.com/44abc9699f2d0bb20a7ea8f588996ffba31965b71b35b-RPdpyt_fw658",
            "https://hbimg.huabanimg.com/06a92960f283c90a1d2cabdbad4b85cd993dab3a59543-5C5V70_fw658",
            "https://hbimg.huabanimg.com/748f7a0c6ccd92419f622b3d7bbdc706918d66dd1efc6-8GRhXt_fw658",
            "https://hbimg.huabanimg.com/1d82ddd40771b0e2104e73813b590aef24b2bea910122e-gxuI8g_fw658",
            "https://hbimg.huabanimg.com/a51218a12b9e90f67a41e019eb340ceb7d418e442ee64-JMyotk_fw658",
            "https://hbimg.huabanimg.com/fc224fc0b01eec4897957cb192ce6fa18471fd243f372-qQSr7Z_fw658",
            "https://hbimg.huabanimg.com/ba7d622236b3a1b070cd1163fea0eb53017ab53113112-dobPMb_fw658",
            "https://hbimg.huabanimg.com/e80071e054fa4e6132da86ff48c9a22b84db4785129be-6PysQ0_fw658",
            "https://hbimg.huabanimg.com/b44d685f2ecd7a1506956d5e6e7896dcfbaaae5e8173d-qEDaMz_fw658",
            "https://hbimg.huabanimg.com/99b6994100f24963bb520bc5eaab02e58b21b988ed2c-TlO3XD_fw658",
            "https://hbimg.huabanimg.com/3bc2a65bb7d19c672e45f5df4374468f64c4633b8b37-e2bcXD_fw658",
            "https://hbimg.huabanimg.com/8dab1530f87a8f2c438660a64ce3fa23a7cd1d26236c4-JpvewD_fw658",
            "https://hbimg.huabanimg.com/cc6cb54ab74123d5247518ff57bd36e0436705c7a0e9c-95N4nR_fw658"
    };

    public static String image() {
        return str(IMAGE_URLS);
    }

    public static List<String> images() {
        return images(20);
    }

    public static List<String> images(int size) {
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            names.add(image());
        }
        return names;
    }

    /************************************************
     *
     * 颜色们
     *
     ************************************************/

    private static final String[] COLOR_NUM = new String[]{
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};


    public static String colorStr() {
        StringBuffer sb = new StringBuffer();
        sb.append("#");
        for (int i = 0; i < 6; i++) {
            sb.append(str(COLOR_NUM));
        }
        return sb.toString();
    }

    public static int colorInt() {
        Color color = new Color(nextInt(256), nextInt(256), nextInt(256));
        return color.getRGB();
    }

    /************************************************
     *
     * 价格们
     *
     ************************************************/
    public static double price() {
        return price(3000);
    }

    public static double price(int max) {
        return ((double) nextInt(max * 100) / 100d);
    }


    /************************************************
     *
     * 真假
     *
     ************************************************/

    public static boolean bool() {
        return nextInt(2) == 0;
    }

//    /************************************************
//     *
//     * 坐标
//     *
//     ************************************************/
//    public static LocLatLng latlng() {
//        LocLatLng ll = new LocLatLng();
//        ll.lat = ((double) Fake.nextInt(39834905, 40003424) / 1000000d);
//        ll.lng = ((double) Fake.nextInt(251831, 474304) / 1000000d) + 116d;
//        return ll;
//    }

    /************************************************
     *
     * 其他
     *
     ************************************************/
    public static int distence() {
        return nextInt(1000000);
    }

    public static List<String> avatar(int size) {
        return images(size);
    }

    public static long time() {
        return System.currentTimeMillis() + (bool() ? 1 : -1) * nextInt(8640000) * 1000;
    }

    /***********************************
     *
     * 集合们
     *
     ***********************************/
    public static Map<Integer, String> map(int count) {
        HashMap<Integer, String> map = new HashMap<>();
        for (int i = 0; i < count; i++) {
            map.put(i, "num" + i);
        }
        return map;
    }

    public static int[] ints(int count) {
        int[] ints = new int[count];
        for (int i = 0; i < count; i++) {
            ints[i] = i;
        }
        return ints;
    }

    /**************************************************
     *
     * 获取T列表
     *
     **************************************************/
    public static <E> E getT(Inject<E> tt) {
        E t = tt.newT();
        tt.inject(t);
        return t;
    }

    public static <E> List<E> getTs(int size, Inject<E> tt) {
        ArrayList<E> ts = new ArrayList<E>();
        for (int i = 0; i < size; i++) {
            ts.add(getT(tt));
        }
        return ts;
    }

    public static abstract class Inject<E> {
        E newT() {
            try {
                return ((Class<E>) OtherTool.getParameterizedType(this, 0)).newInstance();
            } catch (Exception e) {
                return null;
            }
        }

        public abstract void inject(E t);
    }

}
