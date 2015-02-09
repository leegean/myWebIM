(function() {
    function g(b) {
        return a[b] || a[b]
    }
    function f(b, c) {
        a[b] = c()
    }
    (function() {
        function a() {
            this.subscribers = {}
        }
        a.prototype = {publish: function(a, b) {
                this.subscribers[a] = this.subscribers[a] || [];
                for (var c = 0; c < this.subscribers[a].length; c++) {
                    var d = this.subscribers[a][c];
                    if (typeof d === "function") {
                        var e = [];
                        for (var f = 1, g = arguments.length; f < g; f++) {
                            e.push(arguments[f])
                        }
                        d.apply(this, e)
                    }
                }
            },subscribe: function(a, b, c) {
                this.subscribers[a] = this.subscribers[a] || [];
                this.subscribers[a].push(function() {
                    if (typeof b === "function") {
                        b.apply(c, arguments)
                    }
                })
            }};
        window.__PubSub__ = window.__PubSub__ || new a
    })();
    var a = {}, b = window, c = document, d = true, e = false;
    var h = ["talk", "live"];
    var i = "http://js.t.sinajs.cn/t6/webim_prime/js/";
    var j = "http://img.t.sinajs.cn/t4/appstyle/V6_webim/css/pages/";
    var k = {js: i + "webim.js",css: j + "wbim.css"};
    var l = {js: i + "webim_g.js",css: j + "wbim.css"};
    var m = "http://js.t.sinajs.cn/t5/lang/jsmess/mo/";
    var n = "http://img.t.sinajs.cn/t4/appstyle/V6_webim/css/lang/l_en.css";
    var o = {"zh-tw": m + "zh-tw.js",en: m + "en-us.js"};
    var p = {box: "http://weibo.com/messages/",user: "http://weibo.com/message/history?uid="};
    var q = {io: "/socket.io/socket.io.js",jquery: "/client/js/jquery.min.js",index: "/client/js/index.js"};
    var r = ["uid", "msg", "time", "mid", "card", "media_type"], s = ["uid", "msg", "time", "type", "resource", "att_ids", "mid", "card", "media_type"], t = ["uid", "msg", "time", "type", "mid", "card", "media_type"], u = ["offline", "online", "away", "busy", "hide"], v = ["uid", "nick", "status", "group", "avatar", "level", "sex", "resource", "member", "intimacy"], w = ["uid", "nick", "status", "avatar", "isFriend", "level", "sex", "resource", "member", "intimacy"], x = ["uid", "nick", "status", "avatar", "sex", "resource"], y = ["uid", "nick", "status", "avatar", "level", "isFriend", "sex", "resource", "group"];
    var z = b.JSON || function() {
        function l(a, b) {
            var c, d, e, f, i = g, m, n = b[a];
            if (n && typeof n === "object" && typeof n.toJSON === "function") {
                n = n.toJSON(a)
            }
            if (typeof j === "function") {
                n = j.call(b, a, n)
            }
            switch (typeof n) {
                case "string":
                    return k(n);
                case "number":
                    return isFinite(n) ? String(n) : "null";
                case "boolean":
                case "null":
                    return String(n);
                case "object":
                    if (!n) {
                        return "null"
                    }
                    g += h;
                    m = [];
                    if (Object.prototype.toString.apply(n) === "[object Array]") {
                        f = n.length;
                        for (c = 0; c < f; c += 1) {
                            m[c] = l(c, n) || "null"
                        }
                        e = m.length === 0 ? "[]" : g ? "[\n" + g + m.join(",\n" + g) + "\n" + i + "]" : "[" + m.join(",") + "]";
                        g = i;
                        return e
                    }
                    if (j && typeof j === "object") {
                        f = j.length;
                        for (c = 0; c < f; c += 1) {
                            if (typeof j[c] === "string") {
                                d = j[c];
                                e = l(d, n);
                                if (e) {
                                    m.push(k(d) + (g ? ": " : ":") + e)
                                }
                            }
                        }
                    } else {
                        for (d in n) {
                            if (Object.prototype.hasOwnProperty.call(n, d)) {
                                e = l(d, n);
                                if (e) {
                                    m.push(k(d) + (g ? ": " : ":") + e)
                                }
                            }
                        }
                    }
                    e = m.length === 0 ? "{}" : g ? "{\n" + g + m.join(",\n" + g) + "\n" + i + "}" : "{" + m.join(",") + "}";
                    g = i;
                    return e
            }
        }
        function k(a) {
            f.lastIndex = 0;
            return f.test(a) ? '"' + a.replace(f, function(a) {
                var b = i[a];
                return typeof b === "string" ? b : "\\u" + ("0000" + a.charCodeAt(0).toString(16)).slice(-4)
            }) + '"' : '"' + a + '"'
        }
        function d(a) {
            return a < 10 ? "0" + a : a
        }
        var a = {}, c = "eval";
        if (typeof Date.prototype.toJSON !== "function") {
            Date.prototype.toJSON = function() {
                return isFinite(this.valueOf()) ? this.getUTCFullYear() + "-" + d(this.getUTCMonth() + 1) + "-" + d(this.getUTCDate()) + "T" + d(this.getUTCHours()) + ":" + d(this.getUTCMinutes()) + ":" + d(this.getUTCSeconds()) + "Z" : null
            };
            String.prototype.toJSON = Number.prototype.toJSON = Boolean.prototype.toJSON = function() {
                return this.valueOf()
            }
        }
        var e, f, g, h, i, j;
        f = /[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g;
        i = {"\b": "\\b","\t": "\\t","\n": "\\n","\f": "\\f","\r": "\\r",'"': '\\"',"\\": "\\\\"};
        a.stringify = function(a, b, c) {
            var d;
            g = "";
            h = "";
            if (typeof c === "number") {
                for (d = 0; d < c; d += 1) {
                    h += " "
                }
            } else if (typeof c === "string") {
                h = c
            }
            j = b;
            if (b && typeof b !== "function" && (typeof b !== "object" || typeof b.length !== "number")) {
                throw new Error("JSON.stringify")
            }
            return l("", {"": a})
        };
        e = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g;
        a.parse = function(a, d) {
            function g(a, b) {
                var c, e, f = a[b];
                if (f && typeof f === "object") {
                    for (c in f) {
                        if (Object.prototype.hasOwnProperty.call(f, c)) {
                            e = g(f, c);
                            if (e !== undefined) {
                                f[c] = e
                            } else {
                                delete f[c]
                            }
                        }
                    }
                }
                return d.call(a, b, f)
            }
            var f;
            a = String(a);
            e.lastIndex = 0;
            if (e.test(a)) {
                a = a.replace(e, function(a) {
                    return "\\u" + ("0000" + a.charCodeAt(0).toString(16)).slice(-4)
                })
            }
            if (/^[\],:{}\s]*$/.test(a.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, "@").replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, "]").replace(/(?:^|:|,)(?:\s*\[)+/g, ""))) {
                f = b[c]("(" + a + ")");
                return typeof d === "function" ? g({"": f}, "") : f
            }
            throw new SyntaxError("JSON.parse")
        };
        return a
    }();
    f("rule.Card", function() {
        return {domain: {1001: {webpage: ["1001", "社区管理中心卷宗", "webpage", "object.summary", "", "卷宗详情", "fl_objjumpurl"]},1005: {article: ["1005", "36Kr文章", "article", "object.summary", "", "文章详情", "fl_objjumpurl"]},1007: {video: ["1007", "优酷网", "video", "object.summary", "", "播放", "feed_list_third_rend"]},1008: {video: ["1008", "音悦台", "video", "object.summary", "", "播放", "feed_list_third_rend"]},1009: {webpage: ["1009", "马蜂窝", "webpage", "object.summary", "", "文章详情", "fl_objjumpurl"]},1017: {article: ["1017", "新浪博客", "article", "object.summary", "", "文章详情", "fl_objjumpurl"],webpage: ["1017", "门户新闻1017", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},1020: {webpage: ["1020", "门户新闻1020", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},1022: {adFeedEvent: ["1022", "活动广告", "adFeedEvent", "object.summary", "", "马上看看", "fl_objjumpurl"],adFeedVideo: ["1022", "广告视频", "adFeedVideo", "object.summary", "", "马上看看", "fl_objjumpurl"],adMessage: ["1022", "广告消息", "adMessage", "object.summary", "", "马上看看", "fl_objjumpurl"],app: ["1022", "应用Page", "app", "object.summary", "object.author.display_name", "点击下载", "fl_objjumpurl"],article: ["1022", "长文", "article", "object.summary", "", "马上阅读", "feed_list_third_rend"],audio: ["1022", "音乐page", "audio", "object.summary", "object.ext_summary", "", "fl_objjumpurl"],bill: ["1022", "微博转账", "bill", "转账金额：|object.price", "object.summary", "", "fl_objjumpurl"],book: ["1022", "读书Page", "book", "object.summary", "object.author.display_name", "", ""],broadcast: ["1022", "电台Page", "broadcast", "简介：|object.summary", "", "马上收听", "fl_objjumpurl"],campaign: ["1022", "微博活动", "campaign", "dynamic.remain_time", "参与人数：|dynamic.participants", "马上参与", "fl_objjumpurl"],car: ["1022", "汽车page", "car", "object.summary", "object.koubei", "车型详情", "fl_objjumpurl"],cardlist: ["1022", "Card列表", "cardlist", "object.summary", "", "", ""],collection: ["1022", "yetanothercollection", "collection", "object.summary", "", "查看更多", "feed_list_third_rend"],concert: ["1022", "演唱会", "concert", "object.summary", "", "演出详情", "fl_objjumpurl"],coupon: ["1022", "团购Page", "coupon", "粉丝价：|object.sell_price", "", "去购买", "fl_objjumpurl"],event: ["1022", "微博益起来", "event", "object.first_desc", "object.second_desc", "马上领取", "fl_objjumpurl"],foodRaider: ["1022", "美食推", "foodRaider", "object.summary", "", "查看攻略", "fl_objjumpurl"],game: ["1022", "游戏page", "game", "object.summary", "", "游戏详情", "fl_objjumpurl"],gameArticle: ["1022", "游戏文章", "gameArticle", "object.summary", "", "马上阅读", "fl_objjumpurl"],movie: ["1022", "电影Page", "movie", "导演：|object.director.0.display_name", "object.summary", "影片详情", "fl_objjumpurl"],place: ["1022", "地理位置", "place", "object.address.street_address", "", "地点详情", "fl_objjumpurl"],product: ["1022", "微博电商", "product", "object.subtitle", "粉丝价：|object.sell_price", "去购买", "fl_objjumpurl"],topic: ["1022", "微话题", "topic", "object.summary", "", "话题详情", "fl_objjumpurl"],vote: ["1022", "投票", "vote", "object.summary", "", "马上投票", "feed_list_third_rend"]},1023: {webpage: ["1023", "微博之夜投票", "webpage", "object.summary", "", "马上投票", "fl_objjumpurl"]},1029: {comic: ["1029", "微漫画", "comic", "object.summary", "", "立即观看", "fl_objjumpurl"]},1030: {video: ["1030", "酷6网", "video", "object.summary", "", "播放", "feed_list_third_rend"]},1031: {video: ["1031", "搜狐视频", "video", "object.summary", "", "播放", "feed_list_third_rend"]},1032: {article: ["1032", "媒体平台特型", "article", "object.summary", "", "文章详情", "fl_objjumpurl"],collection: ["1032", "粉服文章", "collection", "object.summary", "", "查看更多", "feed_list_third_rend"]},1036: {audio: ["1036", "微盘音频", "audio", "object.author.display_name", "object.create_at", "试听", "feed_list_third_rend"],file: ["1036", "微盘文件", "file", "object.author.display_name", "object.create_at", "去下载", "fl_objjumpurl"],image: ["1036", "微盘图片", "image", "object.author.display_name", "object.create_at", "图片预览", "fl_objjumpurl"],video: ["1036", "微盘视频", "video", "object.author.display_name", "object.create_at", "视频详情", "fl_objjumpurl"]},1037: {voice: ["1037", "啪啪", "voice", "object.summary", "", "去看看", "fl_objjumpurl"]},1042: {event: ["1042", "微公益-个人求助", "event", "object.first_desc", "object.second_desc", "查看详情", "feed_list_third_rend"],group: ["1042", "公开分组", "group", "object.custom_data.members_list", "", "查看分组", "fl_objjumpurl"],recruit: ["1042", "微人脉", "recruit", "object.first_desc", "object.second_desc", "职位详情", "fl_objjumpurl"],webpage: ["1042", "微公益2", "webpage", "object.first_desc", "object.second_desc", "查看详情", "fl_objjumpurl"]},1005001: {article: ["1005001", "36Kr文章", "article", "object.summary", "", "文章详情", "fl_objjumpurl"]},1008001: {video: ["1008001", "音悦台", "video", "object.summary", "", "播放", "feed_list_third_rend"]},1022003: {webpage: ["1022003", "新浪竞猜", "webpage", "object.custom_data.creator", "object.custom_data.create_time", "马上竞猜", "fl_objjumpurl"]},1037001: {voice: ["1037001", "啪啪", "voice", "object.summary", "", "去看看", "fl_objjumpurl"]},1038001: {game: ["1038001", "微博踢球", "game", "object.summary", "", "马上玩", "feed_list_third_rend"],tvShow: ["1038001", "新浪直播室", "tvShow", "object.summary", "", "一键观赛", "feed_list_third_rend"],webpage: ["1038001", "门户新闻001", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},1038003: {tvShow: ["1038003", "新浪直播室", "tvShow", "object.summary", "", "一键观赛", "feed_list_third_rend"],video: ["1038003", "新浪视频", "video", "object.summary", "", "播放", "feed_list_third_rend"]},1038005: {webpage: ["1038005", "门户新闻005", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},1038006: {article: ["1038006", "新浪旅游游记", "article", "object.summary", "", "游记详情", "fl_objjumpurl"],webpage: ["1038006", "新浪旅游攻略", "webpage", "object.summary", "", "查看攻略", "fl_objjumpurl"]},1038007: {webpage: ["1038007", "门户新闻007", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},1038009: {webpage: ["1038009", "门户新闻009", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},1038014: {webpage: ["1038014", "门户新闻014", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},1038017: {webpage: ["1038017", "门户新闻017", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},1038018: {webpage: ["1038018", "门户新闻018", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},1038019: {webpage: ["1038019", "门户新闻019", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},1038020: {webpage: ["1038020", "门户新闻020", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},1038025: {webpage: ["1038025", "门户新闻025", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},1038030: {webpage: ["1038030", "门户新闻030", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},1038031: {webpage: ["1038031", "门户新闻031", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},1038032: {webpage: ["1038032", "门户新闻032", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},1038036: {audio: ["1038036", "微博音乐人", "audio", "object.summary", "演唱：|object.author.display_name", "", "fl_objjumpurl"]},1038037: {video: ["1038037", "新浪看游戏", "video", "object.summary", "", "播放", "feed_list_third_rend"]},1038039: {webpage: ["1038039", "新浪游戏商城", "webpage", "object.summary", "object.custom_data.price", "马上看看", "fl_objjumpurl"]},1038040: {webpage: ["1038040", "新浪游戏积分商城", "webpage", "object.summary", "object.custom_data.price", "马上看看", "fl_objjumpurl"]},1038041: {audio: ["1038041", "华东音乐", "audio", "object.summary", "", "试听", "feed_list_third_rend"],video: ["1038041", "华东视频", "video", "object.summary", "", "播放", "feed_list_third_rend"],webpage: ["1038041", "集客", "webpage", "object.summary", "", "查看详情", "fl_objjumpurl"]},1038081: {event: ["1038081", "世界杯竞猜", "event", "object.summary", "", "参与竞猜", "fl_objjumpurl"],video: ["1038081", "新浪世界杯视频", "video", "object.summary", "", "播放", "feed_list_third_rend"]},1042005: {appItem: ["1042005", "平台应用Card", "appItem", "object.summary", "", "应用详情", "fl_objjumpurl"]},1042014: {recruit: ["1042014", "微人脉", "recruit", "object.first_desc", "object.second_desc", "职位详情", "fl_objjumpurl"]},1042017: {event: ["1042017", "转发求助", "event", "object.first_desc", "object.second_desc", "查看详情", "fl_objjumpurl"],webpage: ["1042017", "微公益2", "webpage", "object.first_desc", "object.second_desc", "查看详情", "fl_objjumpurl"]},1042019: {event: ["1042019", "带着微博去旅行", "event", "object.custom_data.tag", "object.custom_data.link_pc", "抽奖", "fl_objjumpurl"]},1042022: {webpage: ["1042022", "新浪竞猜", "webpage", "object.custom_data.creator", "object.custom_data.create_time", "马上竞猜", "fl_objjumpurl"]},1042024: {event: ["1042024", "微博之夜", "event", "object.custom_data.topic_name", "object.custom_data.tag", "马上投票", "feed_list_third_rend"]},1042025: {bill: ["1042025", "微博转账", "bill", "转账金额：|object.price", "object.summary", "", "fl_objjumpurl"]},1042038: {audio: ["1042038", "新浪乐库", "audio", "object.summary", "演唱：|object.author.display_name", "", "fl_objjumpurl"]},1042042: {webpage: ["1042042", "点评团", "webpage", "object.summary", "", "查看详情", "fl_objjumpurl"]},1042044: {webpage: ["1042044", "自媒体", "webpage", "object.summary", "", "查看详情", "fl_objjumpurl"]},2000009: {audio: ["2000009", "网易云音乐", "audio", "object.author.display_name", "", "试听", "feed_list_third_rend"],video: ["2000009", "网易视频", "video", "object.summary", "", "播放", "feed_list_third_rend"],webpage: ["2000009", "网易新闻", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},2000062: {article: ["2000062", "财新网", "article", "object.summary", "", "文章详情", "fl_objjumpurl"]},2000108: {webpage: ["2000108", "好愿网", "webpage", "object.author.display_name", "", "查看详情", "fl_objjumpurl"]},2000127: {video: ["2000127", "凤凰网", "video", "object.summary", "", "播放", "feed_list_third_rend"]},2000206: {webpage: ["2000206", "搜狐新闻客户端", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},2000241: {article: ["2000241", "财经网", "article", "object.summary", "", "文章详情", "fl_objjumpurl"]},2001137: {webpage: ["2001137", "新东方留学", "webpage", "object.summary", "", "文章详情", "fl_objjumpurl"]},2002846: {webpage: ["2002846", "小度i耳目", "webpage", "object.summary", "", "马上看看", "fl_jumpurl"]},2002957: {video: ["2002957", "新浪拍客", "video", "object.summary", "", "播放", "feed_list_third_rend"]},2003022: {webpage: ["2003022", "小度i耳目", "webpage", "object.summary", "", "马上看看", "fl_jumpurl"]},2003156: {video: ["2003156", "微录客主域", "video", "object.summary", "", "播放", "feed_list_third_rend"]},2003205: {video: ["2003205", "56通用", "video", "object.summary", "", "播放", "feed_list_third_rend"]},2003234: {video: ["2003234", "乐视", "video", "object.summary", "", "播放", "feed_list_third_rend"]},2003338: {video: ["2003338", "腾讯视频", "video", "object.summary", "", "播放", "feed_list_third_rend"],webpage: ["2003338", "微信文章腾讯主域", "webpage", "object.summary", "", "文章详情", "fl_objjumpurl"]},2003385: {webpage: ["2003385", "赶集网", "webpage", "object.summary", "", "查看详情", "fl_objjumpurl"]},2003420: {video: ["2003420", "小影视频", "video", "object.summary", "", "播放", "feed_list_third_rend"]},2003475: {video: ["2003475", "第一视频", "video", "object.summary", "", "播放", "feed_list_third_rend"]},2003491: {recruit: ["2003491", "大街网", "recruit", "object.first_desc", "object.second_desc", "职位详情", "fl_objjumpurl"]},2003498: {webpage: ["2003498", "雪球主站", "webpage", "object.summary", "", "文章详情", "fl_objjumpurl"]},2003557: {app: ["2003557", "豌豆荚应用", "app", "object.summary", "", "应用详情", "fl_objjumpurl"],article: ["2003557", "豌豆荚文章", "article", "object.summary", "", "文章详情", "fl_objjumpurl"]},2003633: {video: ["2003633", "迅雷看看", "video", "object.summary", "", "播放", "feed_list_third_rend"]},2003824: {video: ["2003824", "玩拍", "video", "object.summary", "", "播放", "feed_list_third_rend"]},2003855: {audio: ["2003855", "京东数字音乐", "audio", "object.author.display_name", "object.summary", "试听", "feed_list_third_rend"]},2004091: {video: ["2004091", "土豆", "video", "object.summary", "", "播放", "feed_list_third_rend"]},2004266: {video: ["2004266", "爆米花视频", "video", "object.summary", "", "播放", "feed_list_third_rend"]},2004363: {video: ["2004363", "华尔街日报", "video", "object.summary", "", "播放", "feed_list_third_rend"]},2005600: {webpage: ["2005600", "果壳网", "webpage", "object.summary", "", "文章详情", "fl_objjumpurl"]},2006934: {video: ["2006934", "爱拍猎游", "video", "object.summary", "", "播放", "feed_list_third_rend"]},2006935: {video: ["2006935", "风云直播", "video", "object.summary", "", "播放", "feed_list_third_rend"]},2007632: {audio: ["2007632", "看见音乐", "audio", "object.author.display_name", "", "试听", "feed_list_third_rend"]},2008047: {webpage: ["2008047", "知乎1", "webpage", "object.summary", "", "查看详情", "fl_objjumpurl"]},2008661: {webpage: ["2008661", "今日头条", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},2008794: {audio: ["2008794", "虾米音乐", "audio", "object.author.display_name", "", "试听", "feed_list_third_rend"]},2008804: {video: ["2008804", "唱吧视频", "video", "object.display_name", "", "播放", "feed_list_third_rend"]},2009065: {webpage: ["2009065", "58同城", "webpage", "object.summary", "", "查看详情", "fl_objjumpurl"]},2009488: {article: ["2009488", "喜结网", "article", "object.author.display_name", "", "文章详情", "fl_objjumpurl"]},2009551: {webpage: ["2009551", "图钉图片", "webpage", "object.summary", "", "图片详情", "fl_objjumpurl"]},2009734: {video: ["2009734", "图钉视频", "video", "object.summary", "", "播放", "feed_list_third_rend"]},2009873: {audio: ["2009873", "咪咕音乐", "audio", "object.author.display_name", "", "试听", "feed_list_third_rend"]},2009875: {video: ["2009875", "暴风影音", "video", "object.summary", "", "播放", "feed_list_third_rend"]},2010015: {video: ["2010015", "拍酷", "video", "object.author.display_name", "", "播放", "feed_list_third_rend"]},2010222: {article: ["2010222", "游戏葡萄", "article", "object.author.display_name", "object.summary", "文章详情", "fl_objjumpurl"]},2010772: {webpage: ["2010772", "知乎2", "webpage", "object.summary", "", "查看详情", "fl_objjumpurl"]},2010809: {audio: ["2010809", "荔枝fm", "audio", "object.summary", "", "收听", "feed_list_third_rend"]},2010835: {webpage: ["2010835", "搜房网", "webpage", "object.summary", "", "文章详情", "fl_objjumpurl"]},2010861: {audio: ["2010861", "天天动听", "audio", "object.author.display_name", "", "试听", "feed_list_third_rend"]},2012100: {webpage: ["2012100", "NextCar", "webpage", "object.summary", "发布时间:|object. create_at", "文章详情", "fl_objjumpurl"]},2012186: {webpage: ["2012186", "52活宝网", "webpage", "object.summary", "", "文章详情", "fl_objjumpurl"]},2012199: {article: ["2012199", "中舞网", "article", "object.author.display_name", "object.summary", "文章详情", "fl_objjumpurl"],video: ["2012199", "中舞网视频", "video", "object.summary", "object.author.display_name", "视频详情", "fl_objjumpurl"]},2012231: {webpage: ["2012231", "婚礼时光", "webpage", "object.summary", "", "查看详情", "fl_objjumpurl"]},2012449: {audio: ["2012449", "蜻蜓FM", "audio", "object.summary", "", "播放", "feed_list_third_rend"]},2012571: {video: ["2012571", "人民网", "video", "object.custom_data.source", "", "播放", "feed_list_third_rend"]},2012616: {webpage: ["2012616", "金融界", "webpage", "object.summary", "", "文章详情", "fl_objjumpurl"]},2012676: {video: ["2012676", "芒果TV", "video", "object.summary", "", "播放", "feed_list_third_rend"]},2013350: {audio: ["2013350", "看见音乐", "audio", "object.author.display_name", "", "试听", "feed_list_third_rend"]},2013614: {video: ["2013614", "啊噜哈", "video", "object.summary", "", "播放", "feed_list_third_rend"]},2014131: {video: ["2014131", "美拍", "video", "object.summary", "", "播放", "feed_list_third_rend"]},2014325: {product: ["2014325", "乐影客", "product", "object.summary", "", "查看详情", "fl_objjumpurl"],webpage: ["2014325", "乐影客", "webpage", "object.summary", "", "马上购买", "fl_objjumpurl"]},2014563: {audio: ["2014563", "多米1", "audio", "object.author.display_name", "", "试听", "feed_list_third_rend"]},2014565: {audio: ["2014565", "多米2", "audio", "object.author.display_name", "", "试听", "feed_list_third_rend"]},2014763: {product: ["2014763", "微卖", "product", "object.summary", "object.custom_data.summary", "立即购买", "fl_objjumpurl"]},2014765: {product: ["2014765", "尽在电商", "product", "object.summary", "", "立即购买", "fl_objjumpurl"]},2015199: {product: ["2015199", "乐童音乐", "product", "object.summary", "", "", "fl_objjumpurl"]},2015216: {article: ["2015216", "一点资讯", "article", "object.summary", "", "文章详情", "fl_objjumpurl"]},2015217: {article: ["2015217", "校园文章", "article", "object.summary", "", "文章详情", "fl_objjumpurl"]},2016141: {video: ["2016141", "微视", "video", "object.summary", "", "播放", "feed_list_third_rend"]},2016339: {video: ["2016339", "哔哩哔哩", "video", "object.summary", "", "播放", "feed_list_third_rend"]},2016361: {game: ["2016361", "微博踢球游戏", "game", "object.summary", "", "马上玩", "feed_list_third_rend"]},2016475: {video: ["2016475", "哔哩哔哩", "video", "object.summary", "", "播放", "feed_list_third_rend"]},2016757: {webpage: ["2016757", "林兴测试专用", "webpage", "object.summary", "", "马上看看", "fl_objjumpurl"]},2016781: {video: ["2016781", "acfun", "video", "UP主：|object.author.display_name", "", "播放", "feed_list_third_rend"]},2017037: {video: ["2017037", "趣拍", "video", "object.summary", "", "播放", "feed_list_third_rend"]},2017105: {video: ["2017105", "魔方英语", "video", "object.summary", "", "播放", "feed_list_third_rend"]},2000009014: {audio: ["2000009014", "网易云音乐", "audio", "object.author.display_name", "", "试听", "feed_list_third_rend"]},2000009016: {webpage: ["2000009016", "网易新闻", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},2000108001: {webpage: ["2000108001", "好愿网", "webpage", "object.author.display_name", "", "查看详情", "fl_objjumpurl"]},2001137008: {webpage: ["2001137008", "新东方留学", "webpage", "object.summary", "", "文章详情", "fl_objjumpurl"]},2002846001: {webpage: ["2002846001", "新浪新闻001", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},2002846002: {webpage: ["2002846002", "新浪新闻002", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},2002846005: {webpage: ["2002846005", "新浪新闻005", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},2002846006: {webpage: ["2002846006", "新浪新闻06", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},2002846007: {webpage: ["2002846007", "新浪新闻007", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},2002846008: {webpage: ["2002846008", "新浪新闻008", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},2002846009: {webpage: ["2002846009", "新浪新闻009", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},2002846011: {webpage: ["2002846011", "新浪新闻011", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},2002846012: {webpage: ["2002846012", "新浪新闻012", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},2002846015: {webpage: ["2002846015", "新浪新闻015", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},2002846018: {webpage: ["2002846018", "新门户新闻018", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},2002846019: {webpage: ["2002846019", "新门户新闻019", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},2002846020: {webpage: ["2002846020", "新门户新闻020", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},2002846021: {webpage: ["2002846021", "新增门户新闻021", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},2002846022: {webpage: ["2002846022", "新浪新闻022", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},2002846023: {webpage: ["2002846023", "新浪新闻023", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},2002846026: {webpage: ["2002846026", "新浪新闻026", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},2002846032: {webpage: ["2002846032", "新浪新闻032", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},2002846035: {webpage: ["2002846035", "新浪新闻035", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},2002846036: {webpage: ["2002846036", "新浪新闻036", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},2002846037: {webpage: ["2002846037", "新浪新闻037", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},2002846038: {webpage: ["2002846038", "新浪新闻038", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},2002846039: {webpage: ["2002846039", "新浪新闻039", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},2002846040: {webpage: ["2002846040", "新门户新闻", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},2002957002: {video: ["2002957002", "新浪拍客", "video", "object.summary", "", "播放", "feed_list_third_rend"]},2003205001: {video: ["2003205001", "56通用", "video", "object.summary", "", "播放视频", "feed_list_third_rend"]},2003205002: {video: ["2003205002", "56z", "video", "object.summary", "", "播放视频", "feed_list_third_rend"]},2003205003: {video: ["2003205003", "563g", "video", "object.summary", "", "播放视频", "feed_list_third_rend"]},2003205004: {video: ["2003205004", "56m", "video", "object.summary", "", "播放", "feed_list_third_rend"]},2003234001: {video: ["2003234001", "乐视", "video", "object.summary", "", "播放", "feed_list_third_rend"]},2003338004: {webpage: ["2003338004", "微信文章", "webpage", "object.summary", "", "文章详情", "fl_objjumpurl"]},2003475012: {video: ["2003475012", "第一视频", "video", "object.summary", "", "播放", "feed_list_third_rend"]},2003491001: {recruit: ["2003491001", "大街网", "recruit", "object.first_desc", "object.second_desc", "职位详情", "fl_objjumpurl"]},2003498001: {webpage: ["2003498001", "雪球001", "webpage", "object.summary", "", "新闻详情", "fl_objjumpurl"]},2003633003: {video: ["2003633003", "迅雷看看", "video", "object.summary", "", "播放", "feed_list_third_rend"]},2003824002: {video: ["2003824002", "玩拍2", "video", "object.summary", "", "播放", "feed_list_third_rend"]},2003855002: {audio: ["2003855002", "京东数字音乐", "audio", "object.author.display_name", "object.summary", "试听", "feed_list_third_rend"]},2004266001: {video: ["2004266001", "爆米花视频", "video", "object.summary", "", "播放", "feed_list_third_rend"]},2006934001: {video: ["2006934001", "爱拍猎游", "video", "object.summary", "", "播放", "feed_list_third_rend"]},2006935001: {video: ["2006935001", "风云直播", "video", "object.summary", "", "播放", "feed_list_third_rend"]},2007632001: {audio: ["2007632001", "看见音乐", "audio", "object.author.display_name", "", "试听", "feed_list_third_rend"]},2010835001: {webpage: ["2010835001", "租房帮", "webpage", "object.summary", "", "查看详情", "fl_objjumpurl"]}},biz: {100127: ["100127", "微公益", "event", "object.summary", "", "参与捐助", "fl_objjumpurl"],100404: ["100404", "应用page", "app", "object.summary", "object.author.display_name", "点击下载", "fl_objjumpurl"],230331: ["230331", "砍价团", "product", "object.summary", "", "去砍价", "fl_objjumpurl"],230348: ["230348", "阿桃充值", "product", "object.summary", "object.custom_data.summary", "购买", "fl_jumpurl"],230362: ["230362", "粉丝俱乐部", "event", "object.summary", "", "去送花", "thrid_rend_iframe"],230365: ["230365", "优物库", "product", "object.summary", "object.custom_data.summary", "购买", "fl_jumpurl"],230370: ["230370", "粉丝俱乐部榜单", "event", "object.summary", "", "看榜单", "fl_objjumpurl"],230371: ["230371", "粉丝俱乐部首页", "event", "object.summary", "", "去查看", "fl_objjumpurl"],230378: ["230378", "微博头条", "article", "object.summary", "", "文章详情", "fl_objjumpurl"]}}
    });
    f("rule.CardTemp", function() {
        return [, "<!-- Card模板，请使用STK的easyTemplate构建 -->", "<#et Card display>", '<#if (display.type == "common")>', '<#if (display.style == "article")>', '<div class="WB_feed_spec S_bg2 S_line1 clearfix" data-im="card.article.show" data-im-param="id=${display.id}&url=${display.enUrl}" data-im-card-id="${display.id}">', '<#elseif (display.style == "adFeedVideo")>', '<div class="WB_feed_spec S_bg2 S_line1 clearfix" data-im="card.video.show" data-im-param="url=${display.enShort}&id=${display.id}" data-im-card-id="${display.id}" style="height:152px">', "<#else>", '<div class="WB_feed_spec S_bg2 S_line1 clearfix" data-im="card.show" data-im-param="url=${display.enUrl}" data-im-card-id="${display.id}" style="${display.style == "adFeedEvent" ? "height:auto;background-color:white" : ""}">', "</#if>", '<div class="spec_box clearfix">', '<div class="WB_feed_spec_pic S_bg1" style="${display.style == "adFeedVideo" ? "width:268px;height:152px" : ""}${display.style == "adFeedEvent" ? "width:268px;height:120px" : ""}">', '<a href="${display.style == "article" ? "javascript:void(0);" : display.url}" target="_blank" >', '<img src="${display.pic}" title="${display.name}" alt="${display.name}" style="${display.style == "adFeedVideo" ? "width:268px" : ""}${display.style == "adFeedEvent" ? "width:268px;height:120px" : ""}">', "</a>", '<#if (display.style == "audio")>', '<span class="W_icon icon_playmusic" data-im="card.audio.btn" data-im-param="id=${display.id}&class1=W_icon|icon_playmusic&class2=W_icon|icon_stopmusic"></span>', '<#elseif (display.style == "video" || display.style == "adFeedVideo")>', '<span class="WBIM_icon_playvideo" data-im="card.video.show" data-im-param="url=${display.enShort}&id=${display.id}"></span>', "</#if>", "</div>", '<#if (display.style != "adFeedVideo")>', '<div class="WB_feed_spec_info SW_fun" style="${display.style == "adFeedEvent" ? "width:auto;height:auto;padding:5px" : ""}">', '<div class="WB_feed_spec_cont">', '<h4 class="WB_feed_spec_tit W_autocut">', '<a class="S_func1 W_autocut W_autocut" href="${display.style == "article" ? "javascript:void(0);" : display.url}" target="_blank">${(display.style == "taobao" || display.style == "tmall") ? "宝贝详情" : display.name}</a>', "</h4>", '<#if (display.style == "taobao" || display.style == "tmall")>', '<div class="mesCard_row W_autocut">', '<span class="S_txt2">宝贝：<a href="javascript:void(0);">${display.name}</a></span>', "</div>", '<div class="mesCard_row W_autocut">', '<span class="S_txt2">分类：${display.category}</span>', "</div>", "<#elseif (display.multi)>", '<div class="mesCard_row W_autocut">', '<span class="S_txt2" data-im-type="card_desc1">${display.desc1}</span>', "</div>", '<div class="mesCard_row W_autocut">', '<span class="S_txt2" data-im-type="card_desc2">${display.desc2}</span>', "</div>", "<#else>", '<div class="WB_feed_spec_brieftxt">', '<span class="S_txt2" data-im-type="card_desc">${display.desc}</span>', "</div>", "</#if>", '<#if (display.style != "adFeedEvent")>', '<div class="WB_feed_spec_clearfix clearfix S_txt3">', '<span class="W_fr">', '<#if (display.style == "taobao" || display.style == "tmall")>', '<a class="W_btn_a btn_22px" href="${display.url}" target="_blank">', "去购买", "</a>", "<#elseif (display.button)>", '<#if (display.style == "video" || display.style == "adFeedVideo")>', '<a class="W_btn_a btn_22px" href="${display.url}" target="_blank" data-im-type="card_btn"  data-im="card.video.show" data-im-param="url=${display.enShort}&id=${display.id}">', "${display.button}", "</a>", '<#elseif (display.style == "article")>', '<a class="W_btn_a btn_22px" href="${display.url}" data-im-type="card_btn" target="_blank"  data-im="card.article.show" data-im-param="id=${display.id}&url=${display.enUrl}">', "${display.button}", "</a>", "<#else>", '<a class="W_btn_a btn_22px" href="${display.url}" target="_blank" data-im-type="card_btn">', "${display.button}", "</a>", "</#if>", "</#if>", "&nbsp;", '<a <#if (display.hidelike == "hide")>style="display:none;"</#if> class="W_btn_b btn_22px" href="javascript:void(0);" data-im="card.like" data-id="${display.id}" data-im-param="id=${display.id}&type=${display.style}&liked=${display.liked}&class1=W_icon|icon_praised_bc&class2=W_icon|icon_praised_b">', '<i class="${display.liked ? "W_icon|icon_praised_bc" : "W_icon|icon_praised_b"}"></i>', "<i>${display.like_count}<i>", "</a>", "</span>", "</div>", "</#if>", "</div>", "</div>", "</#if>", "</div>", "</div>", '<#elseif (display.type == "news")>', '<#if (display.style == "simple" || display.style == "vote")>', '<a href="${display.url}" target="_blank">', '<img src="${display.pic}" title="${display.name}" alt="${display.name}" style="${display.style == "vote" ? "width:100%" : ""}"/>', "</a>", '<#elseif (display.style == "collection")>', '<div class="WB_feed_spec S_bg2 S_line1 clearfix">', '<div class="spec_box clearfix">', '<div class="mesCard_pic S_bg1">', '<a href="${display.style == "article" ? "javascript:void(0);" : display.url}" target="_blank" >', '<img src="${display.pic}" title="${display.name}" alt="${display.name}">', "</a>", "</div>", '<div class="mesCard_info">', '<div class="WB_feed_spec_cont">', "<#list display.items as item>", '<div class="mesCard_row W_autocut">', '<span class="S_link1 W_dot">█</span>', '<span class="S_txt2">', '<a href="${item.url}" target="_blank">${item.name}</a>', "</span>", "</div>", "</#list>", "</div>", "</div>", "</div>", "</div>", "</#if>", '<#elseif (display.type == "collection")>', '<div class="widgetDiv">', '<div class="PRF_media_card">', '<ul class="PRF_card_list S_bg4">', '<li class="PRF_card_item S_line2 clearfix PRF_card_item_first" data-im="card.', '" data-im-param="id=${display.id}&url=${display.enUrl}">', '<div class="PRF_card_img">', '<a href="${display.url}" target="_blank" data-im="card.article.show" data-im-param="id=${display.id}&url=${display.enUrl}" >', '<img src="${display.pic}" alt="${display.name}">', "</a>", "</div>", '<div class="PRF_card_txt">', '<span class="PRF_tbg"></span>', '<h4 class="PRF_t">', '<a href="${display.url}" target="_blank" data-im="card.article.show" data-im-param="id=${display.id}&url=${display.enUrl}" >', "<span>${display.name}</span>", "</a>", "</h4>", "</div>", "</li>", "<#list display.items as item>", '<li class="PRF_card_item S_line2 clearfix" data-im="card.article.show" data-im-param="id=${item.id}&url=${item.enUrl}">', '<div class="PRF_card_img">', '<img width="50" src="${item.pic}" alt="${item.name}" />', "</div>", '<div class="PRF_card_txt">', '<span class="PRF_tbg"></span>', '<h4 class="PRF_t">', '<a href="${item.url}" target="_blank" class="cl_title"  data-im="card.article.show" data-im-param="id=${item.id}&url=${item.enUrl}">${item.name}</a>', "</h4>", "</div>", "</li>", "</#list>", "</ul>", "</div>", "</div>", '<#elseif (display.type == "im_invite")>', '<div class="WB_feed_spec S_bg2 S_line1 clearfix">', '<div class="spec_box clearfix">', '<div class="mesCard_pic S_bg1">', '<a href="javascript:void(0);">', '<img src="${display.pic}" title="${display.style == "attention" ? "关注邀请" : "游戏邀请"}" alt="${display.style == "attention" ? "关注邀请" : "游戏邀请"}">', "</a>", "</div>", '<div class="mesCard_info">', '<div class="WB_feed_spec_cont">', '<h4 class="WB_feed_spec_tit W_autocut">', '<a href="javascript:void(0);" class="S_func1 W_autocut">${display.style == "attention" ? "关注邀请" : "游戏邀请"}</a>', "</h4>", '<#if (display.style == "attention")>', '<div class="WB_feed_spec_brieftxt">', '<a href="http://weibo.com/u/${display.uid}" target="_blank">${display.nick}</a>', '<span class="S_txt2">邀请您关注他。</span>', "</div>", '<#elseif (display.style == "game")>', '<div class="WB_feed_spec_brieftxt">', '<a href="http://weibo.com/u/${display.uid}" target="_blank">${display.nick}</a>', '<span class="S_txt2">在</span>', '<a href="${display.invite_url}">${display.app_name}</a>', '<span class="S_txt2">中给您发送了一个游戏邀请。</span>', "</div>", "</#if>", '<div class="WB_feed_spec_clearfix clearfix S_txt3">', '<span class="W_fr">', '<#if (display.style == "attention")>', "<#if (display.status == 0)>", '<a class="WBIM_btn_b" data-im="invite.response" data-im-param="id=${display.mid}&class1=WBIM_btn_b&class2=W_btn_b btn_22px">', '<i class="W_ico12 wbim_icon_addone"></i>', '<i class="wbim_vline">|</i>', '<i class="wbim_icon_addicon">+</i>', "<i>关注</i>", "</a>", '<a class="W_btn_a btn_22px" style="display:none">', '<i class="W_ico12 wbim_icon_addtwo"></i>', "<i>互相关注</i>", "</a>", "<#else>", '<a class="W_btn_a btn_22px">', '<i class="W_ico12 wbim_icon_addtwo"></i>', "<i>互相关注</i>", "</a>", "</#if>", '<#elseif (display.style == "game")>', '<a href="${display.invite_url}" target="_blank" class="WBIM_btn_b">', "<span>接收邀请</span>", "</a>", "</#if>", "</span>", "</div>", "</div>", "</div>", "</div>", "</div>", '<#elseif (display.type == "priMessage")>', '<div class="mesCard_transaction S_bg2 S_line1 clearfix">', '<p class="card_dia_line"></p>', '<div class="card_transaction_bd">', '<div class="card_hd clearfix">', '<div class="hd_left">', '<h4 class="title S_func1 W_f14 W_autocut">${display.display_name}</h4>', '<p class="data S_txt2">${display.create_at}</p>', "</div>", '<div class="hd_right">', '<span class="small_logo"></span>', '<!--<img src="${display.headimgurl}" title="${display.display_name}" alt=""/>-->', "</div>", "</div>", '<div class="card_cont S_line2">', '<p class="cont_page  S_txt1">${display.content}</p>', "</div>", '<div class="card_more">', '<a href="${display.target_url}">详情<em class="W_f14">»</em></a>', "</div>", "</div>", "</div>", '<#elseif (display.type == "webpage")>', '<div class="WB_feed_spec S_bg2 S_line1 clearfix" data-im="redBagFly.click" action-data="url=${display.target_url}" data-im-card-id="${display.id}">', '<div class="PCD_event_red2014">', '<div class="wrap clearfix">', '<div class="W_fl face">', '<img src="${display.ownerUrl}" alt="${display.ownerName}" class="W_face_radius">', '<i title="" class="W_icon  ${display.iconClassName}" style="display:${display.verified}"></i>', "</div>", '<div class="con">', '<p class="W_f14 W_autocut"><span>@${display.ownerName}</span> 的红包</p>', '<p class="W_f14"><span class="W_f18">${display.Desc_Sum}</span> 元</p>', "<p>${display.People_Count}</p>", "</div>", "</div>", "</div>", "</div>", "</#if>", "</#et>"].join("")
    });
    f("util.type", function() {
        function f(a) {
            var b = [];
            for (var c in a) {
                b.push(c)
            }
            return b
        }
        function c(c, d) {
            var e = b[c === null || c !== c ? c : Object.prototype.toString.call(c)] || c && c.nodeName || "#";
            if (c === void 0) {
                e = "Undefined"
            } else if (e.charAt(0) === "#") {
                if (c == c.document && c.document != c) {
                    e = "Window"
                } else if (c.nodeType === 9) {
                    e = "Document"
                } else if (c.callee) {
                    e = "Arguments"
                } else if (isFinite(c.length) && c.item) {
                    e = "NodeList"
                } else {
                    e = a.toString.call(c).slice(8, -1)
                }
            }
            if (d) {
                return d === e
            }
            return e
        }
        var a = Object.prototype;
        var b = {"[object HTMLDocument]": "Document","[object HTMLCollection]": "NodeList","[object StaticNodeList]": "NodeList","[object IXMLDOMNodeList]": "NodeList","[object DOMWindow]": "Window","[object global]": "Window","null": "Null",NaN: "NaN","undefined": "Undefined"};
        "Boolean,Number,String,Function,Array,Date,RegExp,Window,Document,Arguments,NodeList,Null,Undefined".replace(/\w+/ig, function(a) {
            b["[object " + a + "]"] = a
        });
        return {is: function(a, b) {
                return c(a, b)
            },isArray: function(a) {
                return c(a, "Array")
            },isString: function(a) {
                return c(a, "String")
            },isFunction: function(a) {
                return c(a, "Function")
            },isPlainObject: function(b) {
                if (!c(b, "Object") || b.nodeType || c(b, "Window")) {
                    return e
                }
                try {
                    if (b.constructor && !a.hasOwnProperty.call(b.constructor.prototype, "isPrototypeOf")) {
                        return e
                    }
                } catch (f) {
                    return e
                }
                return d
            },isEmptyObject: function(a) {
                if (c(a, "Object") && !f(a).length) {
                    return d
                }
                return e
            }}
    });
    f("util.browser", function() {
        var a = navigator.userAgent.toLowerCase();
        return {IE: /msie/.test(a),OPERA: /opera/.test(a),MOZ: /gecko/.test(a) && !/(compatible|webkit)/.test(a),IE5: /msie 5/.test(a),IE55: /msie 5\.5/.test(a),IE6: /msie 6/.test(a),IE7: /msie 7/.test(a),IE8: /msie 8/.test(a),IE9: /msie 9/.test(a),IE10: /msie 10/.test(a),SAFARI: !/chrome\/([\d.]*)/.test(a) && /\/([\da-f.]*) safari/.test(a),CHROME: /chrome\/([\d.]*)/.test(a),IPAD: /\(ipad/i.test(a),IPHONE: /\(iphone/i.test(a),ITOUCH: /\(itouch/i.test(a),MOBILE: /mobile/i.test(a),LIE: /msie [5678]/i.test(a)}
    });
    f("util.array", function() {
        var a = {forEach: function(a, b, c) {
                var d, e;
                if (c) {
                    for (d = a.length - 1; d >= 0; d--) {
                        b.call(a, a[d], d)
                    }
                } else {
                    for (d = 0, e = a.length; d < e; d++) {
                        b.call(a, a[d], d)
                    }
                }
            },indexOf: function(a, b) {
                for (var c = 0, d = a.length; c < d; c++) {
                    if (a[c] === b) {
                        return c
                    }
                }
                return -1
            },unique: function(b) {
                var c = [];
                for (var d = 0, e = b.length; d < e; d++) {
                    if (a.indexOf(b, b[d]) == d) {
                        c.push(b[d])
                    }
                }
                return c
            },union: function(b, c) {
                var d = [];
                a.forEach(b, function(b) {
                    if (a.indexOf(c, b) > -1) {
                        d.push(b)
                    }
                });
                return d
            },remove: function(b, c) {
                return b.splice(a.indexOf(b, c), 1)
            },associate: function(a, b) {
                var c = {};
                for (var d = 0; d < b.length; d++) {
                    c[b[d]] = a[d]
                }
                return c
            }};
        return a
    });
    f("util.base62", function() {
        function b(a) {
            var b = a.split("");
            var c = a;
            if (b[0] == "1" && b[7] == "0") {
                c = a.substr(0, 7) + a.substr(8, a.length)
            }
            return c
        }
        function a(a) {
            var b = 62;
            var c = 0;
            var d = 0;
            var e;
            var f = a.length;
            for (e = 0; e < f; e++) {
                c = a.charCodeAt(e);
                if (c > 96) {
                    c -= 87
                } else if (c > 64) {
                    c -= 29
                } else {
                    c -= 48
                }
                d += c * Math.pow(b, f - 1 - e)
            }
            return d
        }
        "use strict";
        return {decode: function(c) {
                var d = c.split("");
                var e = d.length;
                var f = "";
                var g = [];
                var h = [];
                var i;
                while (e--) {
                    g.unshift(d[e]);
                    if (g.length === 4) {
                        h.unshift(a(g.join("")));
                        g = []
                    }
                }
                if (g.length) {
                    h.unshift(a(g.join("")))
                }
                i = h.join("");
                i = b(i);
                return i
            }}
    });
    f("util.object", function() {
        var a = g("util.type").isArray, b = g("util.array").forEach;
        return {forEach: function(a, b) {
                for (var c in a) {
                    b.call(a, c, a[c])
                }
            },clone: function(a) {
                var b = {};
                for (var c in a) {
                    b[c] = a[c]
                }
                return b
            },assign: function(a, b) {
                for (var c in b) {
                    a[c] = b[c] === void 0 ? a[c] : b[c]
                }
                return a
            },extend: function(a, b) {
                var c = {};
                for (var d in a) {
                    c[d] = b[d] === void 0 ? a[d] : b[d]
                }
                return c
            },toQuery: function(c, d, e, f) {
                function i(a) {
                    b(c[a], function(b) {
                        g.push(a + e + h(b))
                    })
                }
                var g = [], h = d ? function(a) {
                    return encodeURIComponent(a)
                } : function(a) {
                    return a
                };
                e = e || "=";
                f = f || "&";
                for (var j in c) {
                    if (j == "$Null") {
                        g.push(c[j])
                    } else if (a(c[j])) {
                        i(j)
                    } else {
                        g.push(j + e + h(c[j]))
                    }
                }
                return g.join(f)
            }}
    });
    f("util.string", function() {
        var a = g("util.array"), b = a.associate, c = a.forEach, d = g("util.object").toQuery;
        var e = {trim: function(a) {
                var b = "\\s\\x09\\x0a\\x0b\\x0c\\x0d\\x20\\xA0\\x85\\u1680\\u180E\\u2000-\\u200A\\u2028\\u2029\\u202F\\u205F\\u3000\\uFEFF";
                return a.replace(RegExp("^[" + b + "]+|[" + b + "]+$", "g"), "")
            },parseQuery: function(a, b) {
                var d = {}, f = b ? function(a) {
                    return decodeURIComponent(a)
                } : function(a) {
                    return a
                };
                c(e.trim(a).split("&"), function(a) {
                    var b = a.split("="), c = b[0], e = b[1];
                    if (b.length < 2) {
                        e = c;
                        c = "$Null"
                    }
                    e = f(e);
                    d[c] = d[c] ? [].concat(d[c]).concat(e) : e
                });
                return d
            },parseURL: function(a, c) {
                var f = /(\w+):\/\/\/?([^\:|\/]+)(\:\d*)?(.*\/)([^#|\?|\n]+)?(\?[^#]*)?(#.*)?/i, g = b(f.exec(a) || [], ["url", "protocol", "hostname", "port", "path", "name", "query", "hash"]);
                g.query = g.query ? e.parseQuery(g.query.substring(1), c) : {};
                g.hash = g.hash ? e.parseQuery(g.hash.substring(1), c) : {};
                g.getQuery = function(a) {
                    return g.query[a]
                };
                g.getHash = function(a) {
                    return g.hash[a]
                };
                g.setQuery = function(a, b) {
                    if (b == void 0) {
                        delete g.query[a]
                    } else {
                        g.query[a] = b
                    }
                    return g
                };
                g.setHash = function(a, b) {
                    if (b == void 0) {
                        delete g.hash[a]
                    } else {
                        g.hash[a] = b
                    }
                    return g
                };
                g.toUrl = function(a) {
                    var b = g.protocol + "://", c = d(g.query, a), e = d(g.hash, a);
                    if (g.protocol.toLowerCase() === "file") {
                        b += "/"
                    }
                    return b + g.hostname + (g.port ? ":" + g.port : "") + g.path + (g.name || "") + (c ? "?" + c : "") + (e ? "#" + e : "")
                };
                return g
            }};
        return e
    });
    f("util.delay", function() {
        return function(a, c) {
            return b.setTimeout(a, c || 0)
        }
    });
    f("util.cookie", function() {
        var a = g("util.object");
        var b = {set: function(b, d, e) {
                var f = [], g, h, i = a.extend({expire: null,path: "/",domain: null,secure: null,encode: true}, e);
                if (i.encode === true) {
                    d = escape(d)
                }
                f.push(b + "=" + d);
                if (i.path !== null) {
                    f.push("path=" + i.path)
                }
                if (i.domain !== null) {
                    f.push("domain=" + i.domain)
                }
                if (i.secure !== null) {
                    f.push(i.secure)
                }
                if (i.expire !== null) {
                    g = new Date;
                    h = g.getTime() + i.expire * 36e5;
                    g.setTime(h);
                    f.push("expires=" + g.toGMTString())
                }
                c.cookie = f.join(";")
            },get: function(a) {
                a = a.replace(/([\.\[\]\$])/g, "\\$1");
                var b = new RegExp(a + "=([^;]*)?;", "i"), c = document.cookie + ";", d = c.match(b);
                if (d) {
                    return d[1] || ""
                } else {
                    return ""
                }
            },remove: function(a, c) {
                c = c || {};
                c.expire = -10;
                b.set(a, "", c)
            }};
        return b
    });
    f("util.ready", function() {
        var a = g("util.array"), d = g("util.type"), e = g("util.delay");
        var f = {isReady: false,listeners: [],bindListener: function(a) {
                if (d.isFunction(a)) {
                    if (f.isReady) {
                        e(a)
                    } else {
                        f.listeners.push(a)
                    }
                    return true
                }
                return false
            },ready: function() {
                if (f.isReady) {
                    return
                }
                f.isReady = true;
                a.forEach(f.listeners, function(a) {
                    a()
                })
            }}, h = function() {
            if (f.isReady) {
                return
            }
            try {
                c.documentElement.doScroll("left")
            } catch (a) {
                e(h);
                return
            }
            f.ready()
        }, i;
        if (c.readyState === "complete") {
            e(f.ready)
        }
        if (c.addEventListener) {
            i = function() {
                c.removeEventListener("DOMContentLoaded", i, false);
                f.ready()
            };
            c.addEventListener("DOMContentLoaded", i, false);
            b.addEventListener("load", f.ready, false)
        } else if (b.attachEvent) {
            i = function() {
                if (c.readyState === "complete") {
                    c.detachEvent("onreadystatechange", i);
                    f.ready()
                }
            };
            c.attachEvent("onreadystatechange", i);
            b.attachEvent("onload", f.ready);
            var j = false;
            try {
                j = b.frameElement === null
            } catch (k) {
            }
            if (c.documentElement.doScroll && j) {
                h()
            }
        }
        return function(a) {
            return f.bindListener(a)
        }
    });
    f("util.webSocket", function() {
        return b.WebSocket || b.MozWebSocket
    });
    f("util.swf", function() {
        var a = function() {
            function X(a) {
                var c = /[\\\"<>\.;]/;
                var d = c.exec(a) !== null;
                return d && typeof encodeURIComponent != b ? encodeURIComponent(a) : a
            }
            function W(a, b) {
                if (!z) {
                    return
                }
                var c = b ? "visible" : "hidden";
                if (v && R(a)) {
                    R(a).style.visibility = c
                } else {
                    V("#" + a, "visibility:" + c)
                }
            }
            function V(a, d, e, f) {
                if (A.ie && A.mac) {
                    return
                }
                var g = k.getElementsByTagName("head")[0];
                if (!g) {
                    return
                }
                var h = e && typeof e == "string" ? e : "screen";
                if (f) {
                    x = null;
                    y = null
                }
                if (!x || y != h) {
                    var i = S("style");
                    i.setAttribute("type", "text/css");
                    i.setAttribute("media", h);
                    x = g.appendChild(i);
                    if (A.ie && A.win && typeof k.styleSheets != b && k.styleSheets.length > 0) {
                        x = k.styleSheets[k.styleSheets.length - 1]
                    }
                    y = h
                }
                if (A.ie && A.win) {
                    if (x && typeof x.addRule == c) {
                        x.addRule(a, d)
                    }
                } else {
                    if (x && typeof k.createTextNode != b) {
                        x.appendChild(k.createTextNode(a + " {" + d + "}"))
                    }
                }
            }
            function U(a) {
                var b = A.pv, c = a.split(".");
                c[0] = parseInt(c[0], 10);
                c[1] = parseInt(c[1], 10) || 0;
                c[2] = parseInt(c[2], 10) || 0;
                return b[0] > c[0] || b[0] == c[0] && b[1] > c[1] || b[0] == c[0] && b[1] == c[1] && b[2] >= c[2] ? true : false
            }
            function T(a, b, c) {
                a.attachEvent(b, c);
                q[q.length] = [a, b, c]
            }
            function S(a) {
                return k.createElement(a)
            }
            function R(a) {
                var b = null;
                try {
                    b = k.getElementById(a)
                } catch (c) {
                }
                return b
            }
            function Q(a) {
                var b = R(a);
                if (b) {
                    for (var c in b) {
                        if (typeof b[c] == "function") {
                            b[c] = null
                        }
                    }
                    b.parentNode.removeChild(b)
                }
            }
            function P(a) {
                var b = R(a);
                if (b && b.nodeName == "OBJECT") {
                    if (A.ie && A.win) {
                        b.style.display = "none";
                        (function() {
                            if (b.readyState == 4) {
                                Q(a)
                            } else {
                                setTimeout(arguments.callee, 10)
                            }
                        })()
                    } else {
                        b.parentNode.removeChild(b)
                    }
                }
            }
            function O(a, b, c) {
                var d = S("param");
                d.setAttribute("name", b);
                d.setAttribute("value", c);
                a.appendChild(d)
            }
            function N(a, d, e) {
                var g, h = R(e);
                if (A.wk && A.wk < 312) {
                    return g
                }
                if (h) {
                    if (typeof a.id == b) {
                        a.id = e
                    }
                    if (A.ie && A.win) {
                        var i = "";
                        for (var j in a) {
                            if (a[j] != Object.prototype[j]) {
                                if (j.toLowerCase() == "data") {
                                    d.movie = a[j]
                                } else if (j.toLowerCase() == "styleclass") {
                                    i += ' class="' + a[j] + '"'
                                } else if (j.toLowerCase() != "classid") {
                                    i += " " + j + '="' + a[j] + '"'
                                }
                            }
                        }
                        var k = "";
                        for (var l in d) {
                            if (d[l] != Object.prototype[l]) {
                                k += '<param name="' + l + '" value="' + d[l] + '" />'
                            }
                        }
                        h.outerHTML = '<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"' + i + ">" + k + "</object>";
                        p[p.length] = a.id;
                        g = R(a.id)
                    } else {
                        var m = S(c);
                        m.setAttribute("type", f);
                        for (var n in a) {
                            if (a[n] != Object.prototype[n]) {
                                if (n.toLowerCase() == "styleclass") {
                                    m.setAttribute("class", a[n])
                                } else if (n.toLowerCase() != "classid") {
                                    m.setAttribute(n, a[n])
                                }
                            }
                        }
                        for (var o in d) {
                            if (d[o] != Object.prototype[o] && o.toLowerCase() != "movie") {
                                O(m, o, d[o])
                            }
                        }
                        h.parentNode.replaceChild(m, h);
                        g = m
                    }
                }
                return g
            }
            function M(a) {
                var b = S("div");
                if (A.win && A.ie) {
                    b.innerHTML = a.innerHTML
                } else {
                    var d = a.getElementsByTagName(c)[0];
                    if (d) {
                        var e = d.childNodes;
                        if (e) {
                            var f = e.length;
                            for (var g = 0; g < f; g++) {
                                if (!(e[g].nodeType == 1 && e[g].nodeName == "PARAM") && e[g].nodeType != 8) {
                                    b.appendChild(e[g].cloneNode(true))
                                }
                            }
                        }
                    }
                }
                return b
            }
            function L(a) {
                if (A.ie && A.win && a.readyState != 4) {
                    var b = S("div");
                    a.parentNode.insertBefore(b, a);
                    b.parentNode.replaceChild(M(a), b);
                    a.style.display = "none";
                    (function() {
                        if (a.readyState == 4) {
                            a.parentNode.removeChild(a)
                        } else {
                            setTimeout(arguments.callee, 10)
                        }
                    })()
                } else {
                    a.parentNode.replaceChild(M(a), a)
                }
            }
            function K(a, c, d, e) {
                w = true;
                t = e || null;
                u = {success: false,id: d};
                var f = R(d);
                if (f) {
                    if (f.nodeName == "OBJECT") {
                        r = M(f);
                        s = null
                    } else {
                        r = f;
                        s = d
                    }
                    a.id = h;
                    if (typeof a.width == b || !/%$/.test(a.width) && parseInt(a.width, 10) < 310) {
                        a.width = "310"
                    }
                    if (typeof a.height == b || !/%$/.test(a.height) && parseInt(a.height, 10) < 137) {
                        a.height = "137"
                    }
                    k.title = k.title.slice(0, 47) + " - Flash Player Installation";
                    var g = A.ie && A.win ? "ActiveX" : "PlugIn", i = "MMredirectURL=" + encodeURI(window.location).toString().replace(/&/g, "%26") + "&MMplayerType=" + g + "&MMdoctitle=" + k.title;
                    if (typeof c.flashvars != b) {
                        c.flashvars += "&" + i
                    } else {
                        c.flashvars = i
                    }
                    if (A.ie && A.win && f.readyState != 4) {
                        var j = S("div");
                        d += "SWFObjectNew";
                        j.setAttribute("id", d);
                        f.parentNode.insertBefore(j, f);
                        f.style.display = "none";
                        (function() {
                            if (f.readyState == 4) {
                                f.parentNode.removeChild(f)
                            } else {
                                setTimeout(arguments.callee, 10)
                            }
                        })()
                    }
                    N(a, c, d)
                }
            }
            function J() {
                return !w && U("6.0.65") && (A.win || A.mac) && !(A.wk && A.wk < 312)
            }
            function I(a) {
                var d = null;
                var e = R(a);
                if (e && e.nodeName == "OBJECT") {
                    if (typeof e.SetVariable != b) {
                        d = e
                    } else {
                        var f = e.getElementsByTagName(c)[0];
                        if (f) {
                            d = f
                        }
                    }
                }
                return d
            }
            function H() {
                var a = o.length;
                if (a > 0) {
                    for (var c = 0; c < a; c++) {
                        var d = o[c].id;
                        var e = o[c].callbackFn;
                        var f = {success: false,id: d};
                        if (A.pv[0] > 0) {
                            var g = R(d);
                            if (g) {
                                if (U(o[c].swfVersion) && !(A.wk && A.wk < 312)) {
                                    W(d, true);
                                    if (e) {
                                        f.success = true;
                                        f.ref = I(d);
                                        e(f)
                                    }
                                } else if (o[c].expressInstall && J()) {
                                    var h = {};
                                    h.data = o[c].expressInstall;
                                    h.width = g.getAttribute("width") || "0";
                                    h.height = g.getAttribute("height") || "0";
                                    if (g.getAttribute("class")) {
                                        h.styleclass = g.getAttribute("class")
                                    }
                                    if (g.getAttribute("align")) {
                                        h.align = g.getAttribute("align")
                                    }
                                    var i = {};
                                    var j = g.getElementsByTagName("param");
                                    var k = j.length;
                                    for (var l = 0; l < k; l++) {
                                        if (j[l].getAttribute("name").toLowerCase() != "movie") {
                                            i[j[l].getAttribute("name")] = j[l].getAttribute("value")
                                        }
                                    }
                                    K(h, i, d, e)
                                } else {
                                    L(g);
                                    if (e) {
                                        e(f)
                                    }
                                }
                            }
                        } else {
                            W(d, true);
                            if (e) {
                                var m = I(d);
                                if (m && typeof m.SetVariable != b) {
                                    f.success = true;
                                    f.ref = m
                                }
                                e(f)
                            }
                        }
                    }
                }
            }
            function G() {
                var a = k.getElementsByTagName("body")[0];
                var d = S(c);
                d.setAttribute("type", f);
                var e = a.appendChild(d);
                if (e) {
                    var g = 0;
                    (function() {
                        if (typeof e.GetVariable != b) {
                            var c = e.GetVariable("$version");
                            if (c) {
                                c = c.split(" ")[1].split(",");
                                A.pv = [parseInt(c[0], 10), parseInt(c[1], 10), parseInt(c[2], 10)]
                            }
                        } else if (g < 10) {
                            g++;
                            setTimeout(arguments.callee, 10);
                            return
                        }
                        a.removeChild(d);
                        e = null;
                        H()
                    })()
                } else {
                    H()
                }
            }
            function F() {
                if (m) {
                    G()
                } else {
                    H()
                }
            }
            function E(a) {
                if (typeof j.addEventListener != b) {
                    j.addEventListener("load", a, false)
                } else if (typeof k.addEventListener != b) {
                    k.addEventListener("load", a, false)
                } else if (typeof j.attachEvent != b) {
                    T(j, "onload", a)
                } else if (typeof j.onload == "function") {
                    var c = j.onload;
                    j.onload = function() {
                        c();
                        a()
                    }
                } else {
                    j.onload = a
                }
            }
            function D(a) {
                if (v) {
                    a()
                } else {
                    n[n.length] = a
                }
            }
            function C() {
                if (v) {
                    return
                }
                try {
                    var a = k.getElementsByTagName("body")[0].appendChild(S("span"));
                    a.parentNode.removeChild(a)
                } catch (b) {
                    return
                }
                v = true;
                var c = n.length;
                for (var d = 0; d < c; d++) {
                    n[d]()
                }
            }
            var b = "undefined", c = "object", d = "Shockwave Flash", e = "ShockwaveFlash.ShockwaveFlash", f = "application/x-shockwave-flash", h = "SWFObjectExprInst", i = "onreadystatechange", j = window, k = document, l = navigator, m = false, n = [F], o = [], p = [], q = [], r, s, t, u, v = false, w = false, x, y, z = true, A = function() {
                var a = typeof k.getElementById != b && typeof k.getElementsByTagName != b && typeof k.createElement != b, h = l.userAgent.toLowerCase(), i = l.platform.toLowerCase(), n = i ? /win/.test(i) : /win/.test(h), o = i ? /mac/.test(i) : /mac/.test(h), p = /webkit/.test(h) ? parseFloat(h.replace(/^.*webkit\/(\d+(\.\d+)?).*$/, "$1")) : false, q = g("util.browser").IE, r = [0, 0, 0], s = null;
                if (typeof l.plugins != b && typeof l.plugins[d] == c) {
                    s = l.plugins[d].description;
                    if (s && !(typeof l.mimeTypes != b && l.mimeTypes[f] && !l.mimeTypes[f].enabledPlugin)) {
                        m = true;
                        q = false;
                        s = s.replace(/^.*\s+(\S+\s+\S+$)/, "$1");
                        r[0] = parseInt(s.replace(/^(.*)\..*$/, "$1"), 10);
                        r[1] = parseInt(s.replace(/^.*\.(.*)\s.*$/, "$1"), 10);
                        r[2] = /[a-zA-Z]/.test(s) ? parseInt(s.replace(/^.*[a-zA-Z]+(.*)$/, "$1"), 10) : 0
                    }
                } else if (typeof j.ActiveXObject != b) {
                    try {
                        var t = new ActiveXObject(e);
                        if (t) {
                            s = t.GetVariable("$version");
                            if (s) {
                                q = true;
                                s = s.split(" ")[1].split(",");
                                r = [parseInt(s[0], 10), parseInt(s[1], 10), parseInt(s[2], 10)]
                            }
                        }
                    } catch (u) {
                    }
                }
                return {w3: a,pv: r,wk: p,ie: q,win: n,mac: o}
            }(), B = function() {
                if (!A.w3) {
                    return
                }
                if (typeof k.readyState != b && k.readyState == "complete" || typeof k.readyState == b && (k.getElementsByTagName("body")[0] || k.body)) {
                    C()
                }
                if (!v) {
                    if (typeof k.addEventListener != b) {
                        k.addEventListener("DOMContentLoaded", C, false)
                    }
                    if (A.ie && A.win) {
                        k.attachEvent(i, function() {
                            if (k.readyState == "complete") {
                                k.detachEvent(i, arguments.callee);
                                C()
                            }
                        });
                        if (j == top) {
                            (function() {
                                if (v) {
                                    return
                                }
                                try {
                                    k.documentElement.doScroll("left")
                                } catch (a) {
                                    setTimeout(arguments.callee, 0);
                                    return
                                }
                                C()
                            })()
                        }
                    }
                    if (A.wk) {
                        (function() {
                            if (v) {
                                return
                            }
                            if (!/loaded|complete/.test(k.readyState)) {
                                setTimeout(arguments.callee, 0);
                                return
                            }
                            C()
                        })()
                    }
                    E(C)
                }
            }();
            var Y = function() {
                if (A.ie && A.win) {
                    window.attachEvent("onunload", function() {
                        var b = q.length;
                        for (var c = 0; c < b; c++) {
                            q[c][0].detachEvent(q[c][1], q[c][2])
                        }
                        var d = p.length;
                        for (var e = 0; e < d; e++) {
                            P(p[e])
                        }
                        for (var f in A) {
                            A[f] = null
                        }
                        A = null;
                        for (var g in a) {
                            a[g] = null
                        }
                        a = null
                    })
                }
            }();
            return {registerObject: function(a, b, c, d) {
                    if (A.w3 && a && b) {
                        var e = {};
                        e.id = a;
                        e.swfVersion = b;
                        e.expressInstall = c;
                        e.callbackFn = d;
                        o[o.length] = e;
                        W(a, false)
                    } else if (d) {
                        d({success: false,id: a})
                    }
                },getObjectById: function(a) {
                    if (A.w3) {
                        return I(a)
                    }
                },embedSWF: function(a, d, e, f, g, h, i, j, k, l) {
                    var m = {success: false,id: d};
                    if (A.w3 && !(A.wk && A.wk < 312) && a && d && e && f && g) {
                        W(d, false);
                        D(function() {
                            e += "";
                            f += "";
                            var n = {};
                            if (k && typeof k === c) {
                                for (var o in k) {
                                    n[o] = k[o]
                                }
                            }
                            n.data = a;
                            n.width = e;
                            n.height = f;
                            var p = {};
                            if (j && typeof j === c) {
                                for (var q in j) {
                                    p[q] = j[q]
                                }
                            }
                            if (i && typeof i === c) {
                                for (var r in i) {
                                    if (typeof p.flashvars != b) {
                                        p.flashvars += "&" + r + "=" + i[r]
                                    } else {
                                        p.flashvars = r + "=" + i[r]
                                    }
                                }
                            }
                            if (U(g)) {
                                var s = N(n, p, d);
                                if (n.id == d) {
                                    W(d, true)
                                }
                                m.success = true;
                                m.ref = s
                            } else if (h && J()) {
                                n.data = h;
                                K(n, p, d, l);
                                return
                            } else {
                                W(d, true)
                            }
                            if (l) {
                                l(m)
                            }
                        })
                    } else if (l) {
                        l(m)
                    }
                },switchOffAutoHideShow: function() {
                    z = false
                },ua: A,getFlashPlayerVersion: function() {
                    return {major: A.pv[0],minor: A.pv[1],release: A.pv[2]}
                },hasFlashPlayerVersion: U,createSWF: function(a, b, c) {
                    if (A.w3) {
                        return N(a, b, c)
                    } else {
                        return undefined
                    }
                },showExpressInstall: function(a, b, c, d) {
                    if (A.w3 && J()) {
                        K(a, b, c, d)
                    }
                },removeSWF: function(a) {
                    if (A.w3) {
                        P(a)
                    }
                },createCSS: function(a, b, c, d) {
                    if (A.w3) {
                        V(a, b, c, d)
                    }
                },addDomLoadEvent: D,addLoadEvent: E,getQueryParamValue: function(a) {
                    var b = k.location.search || k.location.hash;
                    if (b) {
                        if (/\?/.test(b)) {
                            b = b.split("?")[1]
                        }
                        if (a === null) {
                            return X(b)
                        }
                        var c = b.split("&");
                        for (var d = 0; d < c.length; d++) {
                            if (c[d].substring(0, c[d].indexOf("=")) == a) {
                                return X(c[d].substring(c[d].indexOf("=") + 1))
                            }
                        }
                    }
                    return ""
                },expressInstallCallback: function() {
                    if (w) {
                        var a = R(h);
                        if (a && r) {
                            a.parentNode.replaceChild(r, a);
                            if (s) {
                                W(s, true);
                                if (A.ie && A.win) {
                                    r.style.display = "block"
                                }
                            }
                            if (t) {
                                t(u)
                            }
                        }
                        w = false
                    }
                }}
        }();
        return a
    });
    f("util.bip", function() {
        return function(a, b) {
            a += "";
            b += "";
            if (a.length > b.length) {
                return d
            } else if (a.length < b.length) {
                return e
            } else {
                return a > b
            }
        }
    });
    (function() {
        var a = function() {
            var a = {};
            var b = "theia";
            a[b] = {IE: /msie/i.test(navigator.userAgent),E: function(a) {
                    if (typeof a === "string") {
                        return document.getElementById(a)
                    } else {
                        return a
                    }
                },C: function(a) {
                    var b;
                    a = a.toUpperCase();
                    if (a == "TEXT") {
                        b = document.createTextNode("")
                    } else if (a == "BUFFER") {
                        b = document.createDocumentFragment()
                    } else {
                        b = document.createElement(a)
                    }
                    return b
                }};
            var c = a[b];
            c.register = function(c, d, e) {
                if (!e || typeof e != "string") {
                    e = b
                }
                if (!a[e]) {
                    a[e] = {}
                }
                var f = a[e];
                var g = c.split(".");
                var h = f;
                var i = null;
                while (i = g.shift()) {
                    if (g.length) {
                        if (h[i] === undefined) {
                            h[i] = {}
                        }
                        h = h[i]
                    } else {
                        if (h[i] === undefined) {
                            try {
                                if (e && e !== b) {
                                    if (c === "core.util.listener") {
                                        h[i] = a[b].core.util.listener;
                                        return true
                                    }
                                    if (c === "core.util.connect") {
                                        h[i] = a[b].core.util.connect;
                                        return true
                                    }
                                }
                                h[i] = d(f);
                                return true
                            } catch (j) {
                            }
                        }
                    }
                }
                return false
            };
            c.unRegister = function(c, d) {
                if (!d || typeof d != "string") {
                    d = b
                }
                var e = a[d];
                var f = c.split(".");
                var g = e;
                var h = null;
                while (h = f.shift()) {
                    if (f.length) {
                        if (g[h] === undefined) {
                            return false
                        }
                        g = g[h]
                    } else {
                        if (g[h] !== undefined) {
                            delete g[h];
                            return true
                        }
                    }
                }
                return false
            };
            c.regShort = function(a, b) {
                if (c[a] !== undefined) {
                    throw "[" + a + "] : short : has been register"
                }
                c[a] = b
            };
            c.shortRegister = function(c, d, e) {
                if (!e || typeof e != "string") {
                    e = b
                }
                var f = a[e];
                var g = c.split(".");
                if (!d) {
                    return false
                }
                if (f[d]) {
                    return false
                }
                var h = f;
                var i = null;
                while (i = g.shift()) {
                    if (g.length) {
                        if (h[i] === undefined) {
                            return false
                        }
                        h = h[i]
                    } else {
                        if (h[i] !== undefined) {
                            if (f[d]) {
                                return false
                            }
                            f[d] = h[i];
                            return true
                        }
                    }
                }
                return false
            };
            c.getPKG = function(c) {
                if (!c || typeof c != "string") {
                    c = b
                }
                return a[c]
            };
            return c
        }();
        a.register("core.arr.isArray", function(a) {
            return function(a) {
                return Object.prototype.toString.call(a) === "[object Array]"
            }
        });
        a.register("core.func.getType", function(a) {
            return function(a) {
                var b;
                return ((b = typeof a) == "object" ? a === null && "null" || Object.prototype.toString.call(a).slice(8, -1) : b).toLowerCase()
            }
        });
        a.register("core.evt.custEvent", function(a) {
            var b = "__custEventKey__", c = 1, d = {}, e = function(a, c) {
                var e = typeof a == "number" ? a : a[b];
                return e && d[e] && {obj: typeof c == "string" ? d[e][c] : d[e],key: e}
            };
            var f = {};
            var g = function(a, b, c, d, f) {
                if (a && typeof b == "string" && c) {
                    var g = e(a, b);
                    if (!g || !g.obj) {
                        throw "custEvent (" + b + ") is undefined !"
                    }
                    g.obj.push({fn: c,data: d,once: f});
                    return g.key
                }
            };
            var h = function(b, c, d, f) {
                var g = true;
                var h = function() {
                    g = false
                };
                if (b && typeof c == "string") {
                    var i = e(b, c), j;
                    if (i && (j = i.obj)) {
                        d = typeof d != "undefined" && [].concat(d) || [];
                        for (var k = j.length - 1; k > -1 && j[k]; k--) {
                            var l = j[k].fn;
                            var m = j[k].once;
                            if (l && l.apply) {
                                try {
                                    l.apply(b, [{obj: b,type: c,data: j[k].data,preventDefault: h}].concat(d));
                                    if (m) {
                                        j.splice(k, 1)
                                    }
                                } catch (n) {
                                }
                            }
                        }
                        if (g && a.core.func.getType(f) === "function") {
                            f()
                        }
                        return i.key
                    }
                }
            };
            var i = {define: function(a, e) {
                    if (a && e) {
                        var f = typeof a == "number" ? a : a[b] || (a[b] = c++), g = d[f] || (d[f] = {});
                        e = [].concat(e);
                        for (var h = 0; h < e.length; h++) {
                            g[e[h]] || (g[e[h]] = [])
                        }
                        return f
                    }
                },undefine: function(a, c) {
                    if (a) {
                        var e = typeof a == "number" ? a : a[b];
                        if (e && d[e]) {
                            if (c) {
                                c = [].concat(c);
                                for (var f = 0; f < c.length; f++) {
                                    if (c[f] in d[e])
                                        delete d[e][c[f]]
                                }
                            } else {
                                delete d[e]
                            }
                        }
                    }
                },add: function(a, b, c, d) {
                    return g(a, b, c, d, false)
                },once: function(a, b, c, d) {
                    return g(a, b, c, d, true)
                },remove: function(b, c, d) {
                    if (b) {
                        var f = e(b, c), g, h;
                        if (f && (g = f.obj)) {
                            if (a.core.arr.isArray(g)) {
                                if (d) {
                                    var i = 0;
                                    while (g[i]) {
                                        if (g[i].fn === d) {
                                            break
                                        }
                                        i++
                                    }
                                    g.splice(i, 1)
                                } else {
                                    g.splice(0, g.length)
                                }
                            } else {
                                for (var i in g) {
                                    g[i] = []
                                }
                            }
                            return f.key
                        }
                    }
                },fire: function(a, b, c, d) {
                    return h(a, b, c, d)
                },hook: function(a, e, g) {
                    if (!a || !e || !g) {
                        return
                    }
                    var j = [], k = a[b], l = k && d[k], m, n = e[b] || (e[b] = c++), o;
                    if (l) {
                        o = f[k + "_" + n] || (f[k + "_" + n] = {});
                        var p = function(a) {
                            var b = true;
                            h(e, o[a.type].type, Array.prototype.slice.apply(arguments, [1, arguments.length]), function() {
                                b = false
                            });
                            b && a.preventDefault()
                        };
                        for (var q in g) {
                            var r = g[q];
                            if (!o[q]) {
                                if (m = l[q]) {
                                    m.push({fn: p,data: undefined});
                                    o[q] = {fn: p,type: r};
                                    j.push(r)
                                }
                            }
                        }
                        i.define(e, j)
                    }
                },unhook: function(a, c, d) {
                    if (!a || !c || !d) {
                        return
                    }
                    var e = a[b], g = c[b], h = f[e + "_" + g];
                    if (h) {
                        for (var j in d) {
                            var k = d[j];
                            if (h[j]) {
                                i.remove(a, j, h[j].fn)
                            }
                        }
                    }
                },destroy: function() {
                    d = {};
                    c = 1;
                    f = {}
                }};
            return i
        });
        (function() {
            var b = {foreach: "core.arr.foreach",addEvent: "core.evt.addEvent",custEvent: "core.evt.custEvent",removeEvent: "core.evt.removeEvent"};
            for (var c in b) {
                a.shortRegister(b[c], c, "theia")
            }
        })();
        a.register("core.evt.addEvent", function(a) {
            return function(b, c, d) {
                b = a.E(b);
                if (b === null) {
                    return false
                }
                if (typeof d !== "function") {
                    return false
                }
                if (b.addEventListener) {
                    b.addEventListener(c, d, false)
                } else if (b.attachEvent) {
                    b.attachEvent("on" + c, d)
                } else {
                    b["on" + c] = d
                }
                return true
            }
        });
        a.register("core.evt.removeEvent", function(a) {
            return function(b, c, d) {
                b = a.E(b);
                if (b === null) {
                    return false
                }
                if (typeof d !== "function") {
                    return false
                }
                if (b.removeEventListener) {
                    b.removeEventListener(c, d, false)
                } else if (b.detachEvent) {
                    b.detachEvent("on" + c, d)
                }
                b["on" + c] = null;
                return true
            }
        });
        a.register("core.arr.foreach", function(a) {
            var b = function(a, b) {
                var c = [];
                for (var d = 0, e = a.length; d < e; d += 1) {
                    var f = b(a[d], d);
                    if (f === false) {
                        break
                    } else if (f !== null) {
                        c[d] = f
                    }
                }
                return c
            };
            var c = function(a, b) {
                var c = {};
                for (var d in a) {
                    var e = b(a[d], d);
                    if (e === false) {
                        break
                    } else if (e !== null) {
                        c[d] = e
                    }
                }
                return c
            };
            return function(d, e) {
                if (a.core.arr.isArray(d) || d.length && d[0] !== undefined) {
                    return b(d, e)
                } else if (typeof d === "object") {
                    return c(d, e)
                }
                return null
            }
        });
        if (!window.WBAudio) {
            (function(a) {
                var b = {};
                var c = function(a) {
                    return b[a]
                };
                var d = function(e) {
                    if (!b[e]) {
                        var f = {exports: {}};
                        try {
                            a[e].call(f.exports, f, f.exports, d, c)
                        } catch (g) {
                        }
                        b[e] = f.exports
                    }
                    return b[e]
                };
                return d("a")
            })({a: function(a, b, c, d) {
                    function g() {
                        var a = "http://js.t.sinajs.cn/t5/pack/static/flash/audio.swf", b = "__audioFlashInstance__" + p++, c = document.body.appendChild(h.C("div")), d = {playerInstance: "window.__audioFlashInstances['" + b + "']"}, f = {allowscriptaccess: "always",wmode: "transparent"};
                        c.style.cssText = ";position:absolute;width:1px;height:1px;top:-10px;left:-10px;", c.innerHTML = '<div id="' + b + '"></div>', i.embedSWF(a + "?ts=" + ((new Date).getTime() + Math.random()), b, "1", "1", "9.0.0", null, d, f), o[b] = this, this._audio = e(b), this._id = b, this._currentTime = 0, this._vol = 0, this._seekable = !1, this._playing = !1, this._muted = !1, this._duration = 0, this._loadpst = 0, this._isready = !1, this._readyfns = [], this._src = ""
                    }
                    function f(a) {
                        this._audio = h.C("audio"), this._audio.autoplay = !1, this._audio.preload = "auto", this._audio.autobuffer = !0, this._vol = 0, this._currentTime = 0, this._duration = 0, this._loadpst = 0, this._seekable = !1, this._playing = !1, this._muted = !1, this._timer = null, this._isready = !0, this._readyfns = [], this._src = "", this._bindEvents()
                    }
                    function e(a) {
                        return navigator.appName.indexOf("Microsoft") != -1 ? window[a] : document[a]
                    }
                    var h = c("A"), i = c("b"), j = c("B"), k = c("c"), l = c("C"), m = h.core.evt.addEvent, n = h.core.evt.removeEvent, o = window.__audioFlashInstances = {}, p = 0, q = ["progress", "loadstart", "load", "loadedmetadata", "play", "pause", "ended", "error", "timeupdate", "seeking", "seeked", "ready"];
                    j(f.prototype, l, {isready: function() {
                            return this._isready
                        },ready: function(a) {
                            return this.isready() === !0 ? a && a() : a && this._readyfns.push(a), this
                        },src: function() {
                            return this._src
                        },load: function(a) {
                            return this._reset(), this._audio.setAttribute("src", this._src = a), this._audio.load(), this
                        },play: function() {
                            return this._audio.play(), this
                        },pause: function() {
                            return this._audio.pause(), this
                        },playing: function() {
                            return this._playing
                        },volume: function(a) {
                            return arguments.length > 0 ? this._setVolume(a) : this._getVolume()
                        },mute: function(a) {
                            return arguments.length > 0 ? this._setMute(a) : this._getMute()
                        },currentTime: function(a) {
                            return arguments.length > 0 ? this._setCurrentTime(a) : this._getCurrentTime()
                        },duration: function() {
                            return this._duration
                        },buffered: function() {
                            return this._loadpst
                        },_getVolume: function() {
                            return this._vol
                        },_setVolume: function(a) {
                            return this._vol = Math.max(0, Math.min(1, a)), this._muted === !1 && (this._audio.volume = this._vol), this
                        },_getMute: function() {
                            return this._muted
                        },_setMute: function(a) {
                            return a = !!a, this._muted = a, this._audio.volume = a ? 0 : this._vol, this.trigger("mute", a), this
                        },_getCurrentTime: function() {
                            return this._currentTime
                        },_setCurrentTime: function(a) {
                            return this._audio.currentTime = this._currentTime = a, this.trigger("timeupdate", this._currentTime, this._duration), this
                        },_onLoadStart: function() {
                            this.trigger("loadstart")
                        },_onLoad: function() {
                            if (this._seekable = !!(this._audio.seekable && this._audio.seekable.length > 0))
                                this._timer = setInterval(k(this, this._onProgress), 250);
                            this.trigger("load")
                        },_onLoadedMetadata: function() {
                            this.trigger("loadedmetadata")
                        },_onPlay: function() {
                            this._playing = !0, this.trigger("play")
                        },_onPause: function() {
                            this._playing = !1, this.trigger("pause")
                        },_onEnded: function() {
                            this._playing = !1, this.trigger("ended")
                        },_onError: function(a) {
                            this._playing = !1, this.trigger("error", [a])
                        },_onTimeUpdate: function() {
                            this._audio.buffered !== null && this._audio.buffered.length && this.playing && (this._currentTime = this._audio.currentTime, this._duration = this._audio.duration === Infinity ? null : this._audio.duration, this.trigger("timeupdate", [this._currentTime, this._duration]))
                        },_onProgress: function() {
                            this._audio.buffered !== null && this._audio.buffered.length && this.trigger("progress", [this._loadpst = parseInt(this._audio.buffered.end(this._audio.buffered.length - 1) / this._audio.duration * 100, 10)])
                        },_onSeeking: function() {
                            this.trigger("seeking")
                        },_onSeeked: function() {
                            this.trigger("seeked")
                        },_clearLoadProgress: function() {
                            return clearInterval(this._timer), this
                        },_reset: function() {
                            return this._clearLoadProgress(), this._seekable = !1, this._playing = !1, this._duration = 0, this._currentTime = 0, this._loadpst = 0, this
                        },_bindEvents: function() {
                            this._onLoadStartBinded = k(this, this._onLoadStart), this._onLoadBinded = k(this, this._onLoad), this._onLoadedMetadataBinded = k(this, this._onLoadedMetadata), this._onPlayBinded = k(this, this._onPlay), this._onPauseBinded = k(this, this._onPause), this._onEndedBinded = k(this, this._onEnded), this._onErrorBinded = k(this, this._onError), this._onTimeUpdateBinded = k(this, this._onTimeUpdate), this._onSeekingBinded = k(this, this._onSeeking), this._onSeekedBinded = k(this, this._onSeeked), m(this._audio, "loadstart", this._onLoadStartBinded), m(this._audio, "canplay", this._onLoadBinded), m(this._audio, "loadedmetadata", this._onLoadedMetadataBinded), m(this._audio, "play", this._onPlayBinded), m(this._audio, "pause", this._onPauseBinded), m(this._audio, "ended", this._onEndedBinded), m(this._audio, "error", this._onErrorBinded), m(this._audio, "timeupdate", this._onTimeUpdateBinded), m(this._audio, "seeking", this._onSeekingBinded), m(this._audio, "seeked", this._onSeekedBinded)
                        },_unbindEvents: function() {
                            n(this._audio, "loadstart", this._onLoadStartBinded), n(this._audio, "canplay", this._onLoadBinded), n(this._audio, "loadedmetadata", this._onLoadedMetadataBinded), n(this._audio, "play", this._onPlayBinded), n(this._audio, "pause", this._onPauseBinded), n(this._audio, "ended", this._onEndedBinded), n(this._audio, "error", this._onErrorBinded), n(this._audio, "timeupdate", this._onTimeUpdateBinded), n(this._audio, "seeking", this._onSeekingBinded), n(this._audio, "seeked", this._onSeekedBinded)
                        }}), j(g.prototype, l, {isready: function() {
                            return this._isready
                        },ready: function(a) {
                            return this.isready() === !0 ? a && a() : a && this._readyfns.push(a), this
                        },src: function() {
                            return this._src
                        },load: function(a) {
                            this._reset(), this._audio.load(this._src = a)
                        },play: function() {
                            return this._audio.pplay(), this
                        },pause: function() {
                            return this._audio.ppause(), this
                        },playing: function() {
                            return this._playing
                        },volume: function(a) {
                            return arguments.length > 0 ? this._setVolume(a) : this._getVolume()
                        },mute: function(a) {
                            return arguments.length > 0 ? this._setMute(a) : this._getMute()
                        },currentTime: function(a) {
                            return arguments.length > 0 ? this._setCurrentTime(a) : this._getCurrentTime()
                        },duration: function() {
                            return this._duration
                        },buffered: function() {
                            return this._loadpst
                        },_getVolume: function() {
                            return this._vol
                        },_setVolume: function(a) {
                            return this._vol = Math.max(0, Math.min(1, a)), this._muted === !1 && this._audio.setVolume(this._vol), this
                        },_getCurrentTime: function() {
                            return this._currentTime
                        },_setCurrentTime: function(a) {
                            return this._audio.seekTo(this._currentTime = a), this
                        },_getMute: function() {
                            return this._muted
                        },_setMute: function(a) {
                            return a = !!a, this._muted = a, this._audio.setVolume(a ? 0 : this._vol), this.trigger("mute", a), this
                        },_reset: function() {
                            return this._seekable = !1, this._playing = !1, this._duration = 0, this._currentTime = 0, this._loadpst = 0, this
                        },eiReady: function() {
                            this._audio = e(this._id), this._isready = !0, this.load(""), h.foreach(this._readyfns, k(this, function(a) {
                                a && a()
                            }))
                        },eiLoadStart: function() {
                            this.trigger("loadstart")
                        },eiLoadedMetadata: function() {
                            this.trigger("loadedmetadata")
                        },eiCanPlay: function() {
                            this.trigger("load")
                        },eiTimeUpdate: function(a, b, c) {
                            this._currentTime = a, this._duration = b, this._seekable = c, this.trigger("timeupdate", [a, c ? b : 0])
                        },eiProgress: function(a) {
                            this.trigger("progress", [this._loadpst = a])
                        },eiLoadError: function(a) {
                            this._playing = !1, this.trigger("error", [a])
                        },eiPlay: function() {
                            this._playing = !0, this.trigger("play")
                        },eiPause: function() {
                            this._playing = !1, this.trigger("pause")
                        },eiEnded: function() {
                            this._playing = !1, this.trigger("ended")
                        },eiSeeking: function() {
                            this.trigger("seeking")
                        },eiSeeked: function() {
                            this.trigger("seeked")
                        }}), window.WBAudio = a.exports = function(a) {
                        return new ({HTML5: f,FLASH: g}[a.toUpperCase()])
                    }
                },A: function(b, c, d, e) {
                    b.exports = e("") || a || window.$WBIM || {}
                },b: function(a, b, c, d) {
                    var e = function() {
                        function w(a) {
                            var b = /[\\\"<>\.;]/, c = b.exec(a) != null;
                            return c && typeof encodeURIComponent != x ? encodeURIComponent(a) : a
                        }
                        function v(a, b) {
                            if (!U)
                                return;
                            var c = b ? "visible" : "hidden";
                            Q && q(a) ? q(a).style.visibility = c : u("#" + a, "visibility:" + c)
                        }
                        function u(a, b, c, d) {
                            if (V.ie && V.mac)
                                return;
                            var e = F.getElementsByTagName("head")[0];
                            if (!e)
                                return;
                            var f = c && typeof c == "string" ? c : "screen";
                            d && (S = null, T = null);
                            if (!S || T != f) {
                                var g = r("style");
                                g.setAttribute("type", "text/css"), g.setAttribute("media", f), S = e.appendChild(g), V.ie && V.win && typeof F.styleSheets != x && F.styleSheets.length > 0 && (S = F.styleSheets[F.styleSheets.length - 1]), T = f
                            }
                            V.ie && V.win ? S && typeof S.addRule == y && S.addRule(a, b) : S && typeof F.createTextNode != x && S.appendChild(F.createTextNode(a + " {" + b + "}"))
                        }
                        function t(a) {
                            var b = V.pv, c = a.split(".");
                            return c[0] = parseInt(c[0], 10), c[1] = parseInt(c[1], 10) || 0, c[2] = parseInt(c[2], 10) || 0, b[0] > c[0] || b[0] == c[0] && b[1] > c[1] || b[0] == c[0] && b[1] == c[1] && b[2] >= c[2] ? !0 : !1
                        }
                        function s(a, b, c) {
                            a.attachEvent(b, c), L[L.length] = [a, b, c]
                        }
                        function r(a) {
                            return F.createElement(a)
                        }
                        function q(a) {
                            var b = null;
                            try {
                                b = F.getElementById(a)
                            } catch (c) {
                            }
                            return b
                        }
                        function p(a) {
                            var b = q(a);
                            if (b) {
                                for (var c in b)
                                    typeof b[c] == "function" && (b[c] = null);
                                b.parentNode.removeChild(b)
                            }
                        }
                        function o(a) {
                            var b = q(a);
                            b && b.nodeName == "OBJECT" && (V.ie && V.win ? (b.style.display = "none", function() {
                                b.readyState == 4 ? p(a) : setTimeout(arguments.callee, 10)
                            }()) : b.parentNode.removeChild(b))
                        }
                        function n(a, b, c) {
                            var d = r("param");
                            d.setAttribute("name", b), d.setAttribute("value", c), a.appendChild(d)
                        }
                        function m(a, b, c) {
                            var d, e = q(c);
                            if (V.wk && V.wk < 312)
                                return d;
                            if (e) {
                                typeof a.id == x && (a.id = c);
                                if (V.ie && V.win) {
                                    var f = "";
                                    for (var g in a)
                                        a[g] != Object.prototype[g] && (g.toLowerCase() == "data" ? b.movie = a[g] : g.toLowerCase() == "styleclass" ? f += ' class="' + a[g] + '"' : g.toLowerCase() != "classid" && (f += " " + g + '="' + a[g] + '"'));
                                    var h = "";
                                    for (var i in b)
                                        b[i] != Object.prototype[i] && (h += '<param name="' + i + '" value="' + b[i] + '" />');
                                    e.outerHTML = '<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"' + f + ">" + h + "</object>", K[K.length] = a.id, d = q(a.id)
                                } else {
                                    var j = r(y);
                                    j.setAttribute("type", B);
                                    for (var k in a)
                                        a[k] != Object.prototype[k] && (k.toLowerCase() == "styleclass" ? j.setAttribute("class", a[k]) : k.toLowerCase() != "classid" && j.setAttribute(k, a[k]));
                                    for (var l in b)
                                        b[l] != Object.prototype[l] && l.toLowerCase() != "movie" && n(j, l, b[l]);
                                    e.parentNode.replaceChild(j, e), d = j
                                }
                            }
                            return d
                        }
                        function l(a) {
                            var b = r("div");
                            if (V.win && V.ie)
                                b.innerHTML = a.innerHTML;
                            else {
                                var c = a.getElementsByTagName(y)[0];
                                if (c) {
                                    var d = c.childNodes;
                                    if (d) {
                                        var e = d.length;
                                        for (var f = 0; f < e; f++)
                                            (d[f].nodeType != 1 || d[f].nodeName != "PARAM") && d[f].nodeType != 8 && b.appendChild(d[f].cloneNode(!0))
                                    }
                                }
                            }
                            return b
                        }
                        function k(a) {
                            if (V.ie && V.win && a.readyState != 4) {
                                var b = r("div");
                                a.parentNode.insertBefore(b, a), b.parentNode.replaceChild(l(a), b), a.style.display = "none", function() {
                                    a.readyState == 4 ? a.parentNode.removeChild(a) : setTimeout(arguments.callee, 10)
                                }()
                            } else
                                a.parentNode.replaceChild(l(a), a)
                        }
                        function j(a, b, c, d) {
                            R = !0, O = d || null, P = {success: !1,id: c};
                            var e = q(c);
                            if (e) {
                                e.nodeName == "OBJECT" ? (M = l(e), N = null) : (M = e, N = c), a.id = C;
                                if (typeof a.width == x || !/%$/.test(a.width) && parseInt(a.width, 10) < 310)
                                    a.width = "310";
                                if (typeof a.height == x || !/%$/.test(a.height) && parseInt(a.height, 10) < 137)
                                    a.height = "137";
                                F.title = F.title.slice(0, 47) + " - Flash Player Installation";
                                var f = V.ie && V.win ? "ActiveX" : "PlugIn", g = "MMredirectURL=" + E.location.toString().replace(/&/g, "%26") + "&MMplayerType=" + f + "&MMdoctitle=" + F.title;
                                typeof b.flashvars != x ? b.flashvars += "&" + g : b.flashvars = g;
                                if (V.ie && V.win && e.readyState != 4) {
                                    var h = r("div");
                                    c += "SWFObjectNew", h.setAttribute("id", c), e.parentNode.insertBefore(h, e), e.style.display = "none", function() {
                                        e.readyState == 4 ? e.parentNode.removeChild(e) : setTimeout(arguments.callee, 10)
                                    }()
                                }
                                m(a, b, c)
                            }
                        }
                        function i() {
                            return !R && t("6.0.65") && (V.win || V.mac) && !(V.wk && V.wk < 312)
                        }
                        function h(a) {
                            var b = null, c = q(a);
                            if (c && c.nodeName == "OBJECT")
                                if (typeof c.SetVariable != x)
                                    b = c;
                                else {
                                    var d = c.getElementsByTagName(y)[0];
                                    d && (b = d)
                                }
                            return b
                        }
                        function g() {
                            var a = J.length;
                            if (a > 0)
                                for (var b = 0; b < a; b++) {
                                    var c = J[b].id, d = J[b].callbackFn, e = {success: !1,id: c};
                                    if (V.pv[0] > 0) {
                                        var f = q(c);
                                        if (f)
                                            if (t(J[b].swfVersion) && !(V.wk && V.wk < 312))
                                                v(c, !0), d && (e.success = !0, e.ref = h(c), d(e));
                                            else if (J[b].expressInstall && i()) {
                                                var g = {};
                                                g.data = J[b].expressInstall, g.width = f.getAttribute("width") || "0", g.height = f.getAttribute("height") || "0", f.getAttribute("class") && (g.styleclass = f.getAttribute("class")), f.getAttribute("align") && (g.align = f.getAttribute("align"));
                                                var l = {}, m = f.getElementsByTagName("param"), n = m.length;
                                                for (var o = 0; o < n; o++)
                                                    m[o].getAttribute("name").toLowerCase() != "movie" && (l[m[o].getAttribute("name")] = m[o].getAttribute("value"));
                                                j(g, l, c, d)
                                            } else
                                                k(f), d && d(e)
                                    } else {
                                        v(c, !0);
                                        if (d) {
                                            var p = h(c);
                                            p && typeof p.SetVariable != x && (e.success = !0, e.ref = p), d(e)
                                        }
                                    }
                                }
                        }
                        function f() {
                            var a = F.getElementsByTagName("body")[0], b = r(y);
                            b.setAttribute("type", B);
                            var c = a.appendChild(b);
                            if (c) {
                                var d = 0;
                                (function() {
                                    if (typeof c.GetVariable != x) {
                                        var e = c.GetVariable("$version");
                                        e && (e = e.split(" ")[1].split(","), V.pv = [parseInt(e[0], 10), parseInt(e[1], 10), parseInt(e[2], 10)])
                                    } else if (d < 10) {
                                        d++, setTimeout(arguments.callee, 10);
                                        return
                                    }
                                    a.removeChild(b), c = null, g()
                                })()
                            } else
                                g()
                        }
                        function d() {
                            H ? f() : g()
                        }
                        function c(a) {
                            if (typeof E.addEventListener != x)
                                E.addEventListener("load", a, !1);
                            else if (typeof F.addEventListener != x)
                                F.addEventListener("load", a, !1);
                            else if (typeof E.attachEvent != x)
                                s(E, "onload", a);
                            else if (typeof E.onload == "function") {
                                var b = E.onload;
                                E.onload = function() {
                                    b(), a()
                                }
                            } else
                                E.onload = a
                        }
                        function b(a) {
                            Q ? a() : I[I.length] = a
                        }
                        function a() {
                            if (Q)
                                return;
                            try {
                                var a = F.getElementsByTagName("body")[0].appendChild(r("span"));
                                a.parentNode.removeChild(a)
                            } catch (b) {
                                return
                            }
                            Q = !0;
                            var c = I.length;
                            for (var d = 0; d < c; d++)
                                I[d]()
                        }
                        var x = "undefined", y = "object", z = "Shockwave Flash", A = "ShockwaveFlash.ShockwaveFlash", B = "application/x-shockwave-flash", C = "SWFObjectExprInst", D = "onreadystatechange", E = window, F = document, G = navigator, H = !1, I = [d], J = [], K = [], L = [], M, N, O, P, Q = !1, R = !1, S, T, U = !0, V = function() {
                            var a = typeof F.getElementById != x && typeof F.getElementsByTagName != x && typeof F.createElement != x, b = G.userAgent.toLowerCase(), c = G.platform.toLowerCase(), d = c ? /win/.test(c) : /win/.test(b), e = c ? /mac/.test(c) : /mac/.test(b), f = /webkit/.test(b) ? parseFloat(b.replace(/^.*webkit\/(\d+(\.\d+)?).*$/, "$1")) : !1, g = !1, h = [0, 0, 0], i = null;
                            if (typeof G.plugins != x && typeof G.plugins[z] == y)
                                i = G.plugins[z].description, i && (typeof G.mimeTypes == x || !G.mimeTypes[B] || !!G.mimeTypes[B].enabledPlugin) && (H = !0, g = !1, i = i.replace(/^.*\s+(\S+\s+\S+$)/, "$1"), h[0] = parseInt(i.replace(/^(.*)\..*$/, "$1"), 10), h[1] = parseInt(i.replace(/^.*\.(.*)\s.*$/, "$1"), 10), h[2] = /[a-zA-Z]/.test(i) ? parseInt(i.replace(/^.*[a-zA-Z]+(.*)$/, "$1"), 10) : 0);
                            else if (typeof E.ActiveXObject != x)
                                try {
                                    var j = new ActiveXObject(A);
                                    j && (i = j.GetVariable("$version"), i && (g = !0, i = i.split(" ")[1].split(","), h = [parseInt(i[0], 10), parseInt(i[1], 10), parseInt(i[2], 10)]))
                                } catch (k) {
                                }
                            return {w3: a,pv: h,wk: f,ie: g,win: d,mac: e}
                        }(), W = function() {
                            if (!V.w3)
                                return;
                            (typeof F.readyState != x && F.readyState == "complete" || typeof F.readyState == x && (F.getElementsByTagName("body")[0] || F.body)) && a(), Q || (typeof F.addEventListener != x && F.addEventListener("DOMContentLoaded", a, !1), V.ie && V.win && (F.attachEvent(D, function() {
                                F.readyState == "complete" && (F.detachEvent(D, arguments.callee), a())
                            }), E == top && function() {
                                if (Q)
                                    return;
                                try {
                                    F.documentElement.doScroll("left")
                                } catch (b) {
                                    setTimeout(arguments.callee, 0);
                                    return
                                }
                                a()
                            }()), V.wk && function() {
                                if (Q)
                                    return;
                                if (!/loaded|complete/.test(F.readyState)) {
                                    setTimeout(arguments.callee, 0);
                                    return
                                }
                                a()
                            }(), c(a))
                        }(), X = function() {
                            V.ie && V.win && window.attachEvent("onunload", function() {
                                var a = L.length;
                                for (var b = 0; b < a; b++)
                                    L[b][0].detachEvent(L[b][1], L[b][2]);
                                var c = K.length;
                                for (var d = 0; d < c; d++)
                                    o(K[d]);
                                for (var f in V)
                                    V[f] = null;
                                V = null;
                                for (var g in e)
                                    e[g] = null;
                                e = null
                            })
                        }();
                        return {registerObject: function(a, b, c, d) {
                                if (V.w3 && a && b) {
                                    var e = {};
                                    e.id = a, e.swfVersion = b, e.expressInstall = c, e.callbackFn = d, J[J.length] = e, v(a, !1)
                                } else
                                    d && d({success: !1,id: a})
                            },getObjectById: function(a) {
                                if (V.w3)
                                    return h(a)
                            },embedSWF: function(a, c, d, e, f, g, h, k, l, n) {
                                var o = {success: !1,id: c};
                                V.w3 && !(V.wk && V.wk < 312) && a && c && d && e && f ? (v(c, !1), b(function() {
                                    d += "", e += "";
                                    var b = {};
                                    if (l && typeof l === y)
                                        for (var p in l)
                                            b[p] = l[p];
                                    b.data = a, b.width = d, b.height = e;
                                    var q = {};
                                    if (k && typeof k === y)
                                        for (var r in k)
                                            q[r] = k[r];
                                    if (h && typeof h === y)
                                        for (var s in h)
                                            typeof q.flashvars != x ? q.flashvars += "&" + s + "=" + h[s] : q.flashvars = s + "=" + h[s];
                                    if (t(f)) {
                                        var u = m(b, q, c);
                                        b.id == c && v(c, !0), o.success = !0, o.ref = u
                                    } else {
                                        if (g && i()) {
                                            b.data = g, j(b, q, c, n);
                                            return
                                        }
                                        v(c, !0)
                                    }
                                    n && n(o)
                                })) : n && n(o)
                            },switchOffAutoHideShow: function() {
                                U = !1
                            },ua: V,getFlashPlayerVersion: function() {
                                return {major: V.pv[0],minor: V.pv[1],release: V.pv[2]}
                            },hasFlashPlayerVersion: t,createSWF: function(a, b, c) {
                                return V.w3 ? m(a, b, c) : undefined
                            },showExpressInstall: function(a, b, c, d) {
                                V.w3 && i() && j(a, b, c, d)
                            },removeSWF: function(a) {
                                V.w3 && o(a)
                            },createCSS: function(a, b, c, d) {
                                V.w3 && u(a, b, c, d)
                            },addDomLoadEvent: b,addLoadEvent: c,getQueryParamValue: function(a) {
                                var b = F.location.search || F.location.hash;
                                if (b) {
                                    /\?/.test(b) && (b = b.split("?")[1]);
                                    if (a == null)
                                        return w(b);
                                    var c = b.split("&");
                                    for (var d = 0; d < c.length; d++)
                                        if (c[d].substring(0, c[d].indexOf("=")) == a)
                                            return w(c[d].substring(c[d].indexOf("=") + 1))
                                }
                                return ""
                            },expressInstallCallback: function() {
                                if (R) {
                                    var a = q(C);
                                    a && M && (a.parentNode.replaceChild(M, a), N && (v(N, !0), V.ie && V.win && (M.style.display = "block")), O && O(P)), R = !1
                                }
                            }}
                    }();
                    a.exports = e
                },B: function(a, b, c, d) {
                    function e(a) {
                        var b = arguments.length, c = 1, d;
                        while (c < b) {
                            d = arguments[c];
                            for (var e in d)
                                d.hasOwnProperty(e) && (a[e] = d[e]);
                            c++
                        }
                        return a
                    }
                    a.exports = e
                },c: function(a, b, c, d) {
                    function e(a, b) {
                        return function() {
                            return b.apply(a, arguments)
                        }
                    }
                    a.exports = e
                },C: function(a, b, c, d) {
                    function e(a) {
                        g(a, function() {
                            i in a || (a[i] = {})
                        })()
                    }
                    var f = c("A"), g = c("c"), h = f.custEvent, i = "__EVT__";
                    a.exports = {on: function(a, b) {
                            return e(this), h.define(this[i], a), h.add(this[i], a, b), this
                        },once: function(a, b) {
                            return e(this), h.define(this[i], a), h.once(this[i], a, b), this
                        },off: function(a, b) {
                            return e(this), h.remove(this[i], a, b), this
                        },trigger: function(a, b) {
                            return e(this), h.fire(this[i], a, b), this
                        }}
                }})
        }
    })();
    f("util.addEvent", function() {
        var a = g("util.type");
        return function(b, c, d) {
            if (b && a.isFunction(d)) {
                if (b.addEventListener) {
                    b.addEventListener(c, d, false)
                } else if (b.attachEvent) {
                    b.attachEvent("on" + c, d)
                } else {
                    b["on" + c] = d
                }
            }
        }
    });
    f("util.easyTemplate", function() {
        var a = Function;
        var b = function(c, d) {
            if (!c) {
                return ""
            }
            if (c !== b.template) {
                b.template = c;
                b.aStatement = b.parsing(b.separate(c))
            }
            var e = b.aStatement;
            var f = function(a) {
                if (a) {
                    d = a
                }
                return arguments.callee
            };
            f.toString = function() {
                return (new a(e[0], e[1]))(d)
            };
            return f
        };
        b.separate = function(a) {
            var b = /\\'/g;
            var c = a.replace(/(<(\/?)#(.*?(?:\(.*?\))*)>)|(')|([\r\n\t])|(\$\{([^\}]*?)\})/g, function(a, c, d, e, f, g, h, i) {
                if (c) {
                    return "{|}" + (d ? "-" : "+") + e + "{|}"
                }
                if (f) {
                    return "\\'"
                }
                if (g) {
                    return ""
                }
                if (h) {
                    return "'+(" + i.replace(b, "'") + ")+'"
                }
            });
            return c
        };
        b.parsing = function(a) {
            var b, c, d, e, f, g, h, i = ["var aRet = [];"];
            h = a.split(/\{\|\}/);
            var j = /\s/;
            while (h.length) {
                d = h.shift();
                if (!d) {
                    continue
                }
                f = d.charAt(0);
                if (f !== "+" && f !== "-") {
                    d = "'" + d + "'";
                    i.push("aRet.push(" + d + ");");
                    continue
                }
                e = d.split(j);
                switch (e[0]) {
                    case "+et":
                        b = e[1];
                        c = e[2];
                        i.push('aRet.push("<!--' + b + ' start-->");');
                        break;
                    case "-et":
                        i.push('aRet.push("<!--' + b + ' end-->");');
                        break;
                    case "+if":
                        e.splice(0, 1);
                        i.push("if" + e.join(" ") + "{");
                        break;
                    case "+elseif":
                        e.splice(0, 1);
                        i.push("}else if" + e.join(" ") + "{");
                        break;
                    case "-if":
                        i.push("}");
                        break;
                    case "+else":
                        i.push("}else{");
                        break;
                    case "+list":
                        i.push("if(" + e[1] + ".constructor === Array){with({i:0,l:" + e[1] + ".length," + e[3] + "_index:0," + e[3] + ":null}){for(i=l;i--;){" + e[3] + "_index=(l-i-1);" + e[3] + "=" + e[1] + "[" + e[3] + "_index];");
                        break;
                    case "-list":
                        i.push("}}}");
                        break;
                    default:
                        break
                }
            }
            i.push('return aRet.join("");');
            return [c, i.join("")]
        };
        return b
    });
    f("util.MD5", function() {
        return function(a) {
            function q() {
                function a(a, b, c, d) {
                    var f = w;
                    w = v;
                    v = u;
                    u = p(u, e(p(t, p(a, p(b, c))), d));
                    t = f
                }
                var b = n.length;
                n.push(128);
                var c = n.length % 64;
                if (c > 56) {
                    for (var k = 0; k < 64 - c; k++) {
                        n.push(0)
                    }
                    c = n.length % 64
                }
                for (var m = 0; m < 56 - c; m++) {
                    n.push(0)
                }
                n = n.concat(d(b * 8));
                var o = 1732584193;
                var q = 4023233417;
                var r = 2562383102;
                var s = 271733878;
                var t = 0, u = 0, v = 0, w = 0;
                for (var x = 0; x < n.length / 64; x++) {
                    t = o;
                    u = q;
                    v = r;
                    w = s;
                    var y = x * 64;
                    a(f(u, v, w), 3614090360, j(n, y), 7);
                    a(f(u, v, w), 3905402710, j(n, y + 4), 12);
                    a(f(u, v, w), 606105819, j(n, y + 8), 17);
                    a(f(u, v, w), 3250441966, j(n, y + 12), 22);
                    a(f(u, v, w), 4118548399, j(n, y + 16), 7);
                    a(f(u, v, w), 1200080426, j(n, y + 20), 12);
                    a(f(u, v, w), 2821735955, j(n, y + 24), 17);
                    a(f(u, v, w), 4249261313, j(n, y + 28), 22);
                    a(f(u, v, w), 1770035416, j(n, y + 32), 7);
                    a(f(u, v, w), 2336552879, j(n, y + 36), 12);
                    a(f(u, v, w), 4294925233, j(n, y + 40), 17);
                    a(f(u, v, w), 2304563134, j(n, y + 44), 22);
                    a(f(u, v, w), 1804603682, j(n, y + 48), 7);
                    a(f(u, v, w), 4254626195, j(n, y + 52), 12);
                    a(f(u, v, w), 2792965006, j(n, y + 56), 17);
                    a(f(u, v, w), 1236535329, j(n, y + 60), 22);
                    a(g(u, v, w), 4129170786, j(n, y + 4), 5);
                    a(g(u, v, w), 3225465664, j(n, y + 24), 9);
                    a(g(u, v, w), 643717713, j(n, y + 44), 14);
                    a(g(u, v, w), 3921069994, j(n, y), 20);
                    a(g(u, v, w), 3593408605, j(n, y + 20), 5);
                    a(g(u, v, w), 38016083, j(n, y + 40), 9);
                    a(g(u, v, w), 3634488961, j(n, y + 60), 14);
                    a(g(u, v, w), 3889429448, j(n, y + 16), 20);
                    a(g(u, v, w), 568446438, j(n, y + 36), 5);
                    a(g(u, v, w), 3275163606, j(n, y + 56), 9);
                    a(g(u, v, w), 4107603335, j(n, y + 12), 14);
                    a(g(u, v, w), 1163531501, j(n, y + 32), 20);
                    a(g(u, v, w), 2850285829, j(n, y + 52), 5);
                    a(g(u, v, w), 4243563512, j(n, y + 8), 9);
                    a(g(u, v, w), 1735328473, j(n, y + 28), 14);
                    a(g(u, v, w), 2368359562, j(n, y + 48), 20);
                    a(h(u, v, w), 4294588738, j(n, y + 20), 4);
                    a(h(u, v, w), 2272392833, j(n, y + 32), 11);
                    a(h(u, v, w), 1839030562, j(n, y + 44), 16);
                    a(h(u, v, w), 4259657740, j(n, y + 56), 23);
                    a(h(u, v, w), 2763975236, j(n, y + 4), 4);
                    a(h(u, v, w), 1272893353, j(n, y + 16), 11);
                    a(h(u, v, w), 4139469664, j(n, y + 28), 16);
                    a(h(u, v, w), 3200236656, j(n, y + 40), 23);
                    a(h(u, v, w), 681279174, j(n, y + 52), 4);
                    a(h(u, v, w), 3936430074, j(n, y), 11);
                    a(h(u, v, w), 3572445317, j(n, y + 12), 16);
                    a(h(u, v, w), 76029189, j(n, y + 24), 23);
                    a(h(u, v, w), 3654602809, j(n, y + 36), 4);
                    a(h(u, v, w), 3873151461, j(n, y + 48), 11);
                    a(h(u, v, w), 530742520, j(n, y + 60), 16);
                    a(h(u, v, w), 3299628645, j(n, y + 8), 23);
                    a(i(u, v, w), 4096336452, j(n, y), 6);
                    a(i(u, v, w), 1126891415, j(n, y + 28), 10);
                    a(i(u, v, w), 2878612391, j(n, y + 56), 15);
                    a(i(u, v, w), 4237533241, j(n, y + 20), 21);
                    a(i(u, v, w), 1700485571, j(n, y + 48), 6);
                    a(i(u, v, w), 2399980690, j(n, y + 12), 10);
                    a(i(u, v, w), 4293915773, j(n, y + 40), 15);
                    a(i(u, v, w), 2240044497, j(n, y + 4), 21);
                    a(i(u, v, w), 1873313359, j(n, y + 32), 6);
                    a(i(u, v, w), 4264355552, j(n, y + 60), 10);
                    a(i(u, v, w), 2734768916, j(n, y + 24), 15);
                    a(i(u, v, w), 1309151649, j(n, y + 52), 21);
                    a(i(u, v, w), 4149444226, j(n, y + 16), 6);
                    a(i(u, v, w), 3174756917, j(n, y + 44), 10);
                    a(i(u, v, w), 718787259, j(n, y + 8), 15);
                    a(i(u, v, w), 3951481745, j(n, y + 36), 21);
                    o = p(o, t);
                    q = p(q, u);
                    r = p(r, v);
                    s = p(s, w)
                }
                return l(s, r, q, o)
            }
            function p(a, b) {
                return 4294967295 & a + b
            }
            function m(a) {
                var b = new Array(a.length);
                for (var c = 0; c < a.length; c++) {
                    b[c] = a[c]
                }
                return b
            }
            function l(a, c, d, e) {
                var f = "";
                var g = 0;
                var h = 0;
                for (var i = 3; i >= 0; i--) {
                    h = arguments[i];
                    g = h & 255;
                    h = h >>> 8;
                    g = g << 8;
                    g = g | h & 255;
                    h = h >>> 8;
                    g = g << 8;
                    g = g | h & 255;
                    h = h >>> 8;
                    g = g << 8;
                    g = g | h;
                    f = f + b(g)
                }
                return f
            }
            function k(a) {
                var b = [];
                for (var c = 0; c < a.length; c++)
                    if (a.charCodeAt(c) <= 127) {
                        b.push(a.charCodeAt(c))
                    } else {
                        var d = encodeURIComponent(a.charAt(c)).substr(1).split("%");
                        for (var e = 0; e < d.length; e++) {
                            b.push(parseInt(d[e], 16))
                        }
                    }
                return b
            }
            function j(a, b) {
                return a[b + 3] << 24 | a[b + 2] << 16 | a[b + 1] << 8 | a[b]
            }
            function i(a, b, c) {
                return b ^ (a | ~c)
            }
            function h(a, b, c) {
                return a ^ b ^ c
            }
            function g(a, b, c) {
                return c & a | ~c & b
            }
            function f(a, b, c) {
                return a & b | ~a & c
            }
            function e(a, b) {
                return a << b & 4294967295 | a >>> 32 - b
            }
            function d(a) {
                var b = [];
                for (var c = 0; c < 8; c++) {
                    b.push(a & 255);
                    a = a >>> 8
                }
                return b
            }
            function c(a) {
                var b = [];
                for (var c = 0; c < a.length; c++) {
                    b = b.concat(k(a[c]))
                }
                return b
            }
            function b(a) {
                var b = (a >>> 0).toString(16);
                return "00000000".substr(0, 8 - b.length) + b
            }
            var n = null;
            var o = null;
            if (typeof a == "string") {
                n = k(a)
            } else if (a.constructor == Array) {
                if (a.length === 0) {
                    n = a
                } else if (typeof a[0] == "string") {
                    n = c(a)
                } else if (typeof a[0] == "number") {
                    n = a
                } else {
                    o = typeof a[0]
                }
            } else if (typeof ArrayBuffer != "undefined") {
                if (a instanceof ArrayBuffer) {
                    n = m(new Uint8Array(a))
                } else if (a instanceof Uint8Array || a instanceof Int8Array) {
                    n = m(a)
                } else if (a instanceof Uint32Array || a instanceof Int32Array || a instanceof Uint16Array || a instanceof Int16Array || a instanceof Float32Array || a instanceof Float64Array) {
                    n = m(new Uint8Array(a.buffer))
                } else {
                    o = typeof a
                }
            } else {
                o = typeof a
            }
            if (o) {
            }
            return q()
        }
    });
    f("util.sizzle", function(a) {
        function p(a, b, c, d, e, f) {
            for (var h = 0, i = d.length; h < i; h++) {
                var j = d[h];
                if (j) {
                    j = j[a];
                    var k = false;
                    while (j) {
                        if (j.sizcache === c) {
                            k = d[j.sizset];
                            break
                        }
                        if (j.nodeType === 1) {
                            if (!f) {
                                j.sizcache = c;
                                j.sizset = h
                            }
                            if (typeof b !== "string") {
                                if (j === b) {
                                    k = true;
                                    break
                                }
                            } else if (g.filter(b, [j]).length > 0) {
                                k = j;
                                break
                            }
                        }
                        j = j[a]
                    }
                    d[h] = k
                }
            }
        }
        function o(a, b, c, d, e, f) {
            for (var g = 0, h = d.length; g < h; g++) {
                var i = d[g];
                if (i) {
                    i = i[a];
                    var j = false;
                    while (i) {
                        if (i.sizcache === c) {
                            j = d[i.sizset];
                            break
                        }
                        if (i.nodeType === 1 && !f) {
                            i.sizcache = c;
                            i.sizset = g
                        }
                        if (i.nodeName.toLowerCase() === b) {
                            j = i;
                            break
                        }
                        i = i[a]
                    }
                    d[g] = j
                }
            }
        }
        var b = /((?:\((?:\([^()]+\)|[^()]+)+\)|\[(?:\[[^\[\]]*\]|['"][^'"]*['"]|[^\[\]'"]+)+\]|\\.|[^ >+~,(\[\\]+)+|[>+~])(\s*,\s*)?((?:.|\r|\n)*)/g, c = 0, d = Object.prototype.toString, e = false, f = true;
        [0, 0].sort(function() {
            f = false;
            return 0
        });
        var g = function(a, c, e, f) {
            e = e || [];
            c = c || document;
            var j = c;
            if (c.nodeType !== 1 && c.nodeType !== 9) {
                return []
            }
            if (!a || typeof a !== "string") {
                return e
            }
            var k = [], m, n, o, p, r = true, s = g.isXML(c), t = a, u, v, w, x;
            do {
                b.exec("");
                m = b.exec(t);
                if (m) {
                    t = m[3];
                    k.push(m[1]);
                    if (m[2]) {
                        p = m[3];
                        break
                    }
                }
            } while (m);
            if (k.length > 1 && i.exec(a)) {
                if (k.length === 2 && h.relative[k[0]]) {
                    n = q(k[0] + k[1], c)
                } else {
                    n = h.relative[k[0]] ? [c] : g(k.shift(), c);
                    while (k.length) {
                        a = k.shift();
                        if (h.relative[a]) {
                            a += k.shift()
                        }
                        n = q(a, n)
                    }
                }
            } else {
                if (!f && k.length > 1 && c.nodeType === 9 && !s && h.match.ID.test(k[0]) && !h.match.ID.test(k[k.length - 1])) {
                    u = g.find(k.shift(), c, s);
                    c = u.expr ? g.filter(u.expr, u.set)[0] : u.set[0]
                }
                if (c) {
                    u = f ? {expr: k.pop(),set: l(f)} : g.find(k.pop(), k.length === 1 && (k[0] === "~" || k[0] === "+") && c.parentNode ? c.parentNode : c, s);
                    n = u.expr ? g.filter(u.expr, u.set) : u.set;
                    if (k.length > 0) {
                        o = l(n)
                    } else {
                        r = false
                    }
                    while (k.length) {
                        v = k.pop();
                        w = v;
                        if (!h.relative[v]) {
                            v = ""
                        } else {
                            w = k.pop()
                        }
                        if (w == null) {
                            w = c
                        }
                        h.relative[v](o, w, s)
                    }
                } else {
                    o = k = []
                }
            }
            if (!o) {
                o = n
            }
            if (!o) {
                g.error(v || a)
            }
            if (d.call(o) === "[object Array]") {
                if (!r) {
                    e.push.apply(e, o)
                } else if (c && c.nodeType === 1) {
                    for (x = 0; o[x] != null; x++) {
                        if (o[x] && (o[x] === true || o[x].nodeType === 1 && g.contains(c, o[x]))) {
                            e.push(n[x])
                        }
                    }
                } else {
                    for (x = 0; o[x] != null; x++) {
                        if (o[x] && o[x].nodeType === 1) {
                            e.push(n[x])
                        }
                    }
                }
            } else {
                l(o, e)
            }
            if (p) {
                g(p, j, e, f);
                g.uniqueSort(e)
            }
            return e
        };
        g.uniqueSort = function(a) {
            if (n) {
                e = f;
                a.sort(n);
                if (e) {
                    for (var b = 1; b < a.length; b++) {
                        if (a[b] === a[b - 1]) {
                            a.splice(b--, 1)
                        }
                    }
                }
            }
            return a
        };
        g.matches = function(a, b) {
            return g(a, null, null, b)
        };
        g.find = function(a, b, c) {
            var d;
            if (!a) {
                return []
            }
            for (var e = 0, f = h.order.length; e < f; e++) {
                var g = h.order[e], i;
                if (i = h.leftMatch[g].exec(a)) {
                    var j = i[1];
                    i.splice(1, 1);
                    if (j.substr(j.length - 1) !== "\\") {
                        i[1] = (i[1] || "").replace(/\\/g, "");
                        d = h.find[g](i, b, c);
                        if (d != null) {
                            a = a.replace(h.match[g], "");
                            break
                        }
                    }
                }
            }
            if (!d) {
                d = b.getElementsByTagName("*")
            }
            return {set: d,expr: a}
        };
        g.filter = function(a, b, c, d) {
            var e = a, f = [], i = b, j, k, l = b && b[0] && g.isXML(b[0]);
            while (a && b.length) {
                for (var m in h.filter) {
                    if ((j = h.leftMatch[m].exec(a)) != null && j[2]) {
                        var n = h.filter[m], o, p, q = j[1];
                        k = false;
                        j.splice(1, 1);
                        if (q.substr(q.length - 1) === "\\") {
                            continue
                        }
                        if (i === f) {
                            f = []
                        }
                        if (h.preFilter[m]) {
                            j = h.preFilter[m](j, i, c, f, d, l);
                            if (!j) {
                                k = o = true
                            } else if (j === true) {
                                continue
                            }
                        }
                        if (j) {
                            for (var r = 0; (p = i[r]) != null; r++) {
                                if (p) {
                                    o = n(p, j, r, i);
                                    var s = d ^ !!o;
                                    if (c && o != null) {
                                        if (s) {
                                            k = true
                                        } else {
                                            i[r] = false
                                        }
                                    } else if (s) {
                                        f.push(p);
                                        k = true
                                    }
                                }
                            }
                        }
                        if (o !== undefined) {
                            if (!c) {
                                i = f
                            }
                            a = a.replace(h.match[m], "");
                            if (!k) {
                                return []
                            }
                            break
                        }
                    }
                }
                if (a === e) {
                    if (k == null) {
                        g.error(a)
                    } else {
                        break
                    }
                }
                e = a
            }
            return i
        };
        g.error = function(a) {
            throw "Syntax error, unrecognized expression: " + a
        };
        var h = {order: ["ID", "NAME", "TAG"],match: {ID: /#((?:[\w\u00c0-\uFFFF\-]|\\.)+)/,CLASS: /\.((?:[\w\u00c0-\uFFFF\-]|\\.)+)/,NAME: /\[name=['"]*((?:[\w\u00c0-\uFFFF\-]|\\.)+)['"]*\]/,ATTR: /\[\s*((?:[\w\u00c0-\uFFFF\-]|\\.)+)\s*(?:(\S?=)\s*(['"]*)(.*?)\3|)\s*\]/,TAG: /^((?:[\w\u00c0-\uFFFF\*\-]|\\.)+)/,CHILD: /:(only|nth|last|first)-child(?:\((even|odd|[\dn+\-]*)\))?/,POS: /:(nth|eq|gt|lt|first|last|even|odd)(?:\((\d*)\))?(?=[^\-]|$)/,PSEUDO: /:((?:[\w\u00c0-\uFFFF\-]|\\.)+)(?:\((['"]?)((?:\([^\)]+\)|[^\(\)]*)+)\2\))?/},leftMatch: {},attrMap: {"class": "className","for": "htmlFor"},attrHandle: {href: function(a) {
                    return a.getAttribute("href")
                }},relative: {"+": function(a, b) {
                    var c = typeof b === "string", d = c && !/\W/.test(b), e = c && !d;
                    if (d) {
                        b = b.toLowerCase()
                    }
                    for (var f = 0, h = a.length, i; f < h; f++) {
                        if (i = a[f]) {
                            while ((i = i.previousSibling) && i.nodeType !== 1) {
                            }
                            a[f] = e || i && i.nodeName.toLowerCase() === b ? i || false : i === b
                        }
                    }
                    if (e) {
                        g.filter(b, a, true)
                    }
                },">": function(a, b) {
                    var c = typeof b === "string", d, e = 0, f = a.length;
                    if (c && !/\W/.test(b)) {
                        b = b.toLowerCase();
                        for (; e < f; e++) {
                            d = a[e];
                            if (d) {
                                var h = d.parentNode;
                                a[e] = h.nodeName.toLowerCase() === b ? h : false
                            }
                        }
                    } else {
                        for (; e < f; e++) {
                            d = a[e];
                            if (d) {
                                a[e] = c ? d.parentNode : d.parentNode === b
                            }
                        }
                        if (c) {
                            g.filter(b, a, true)
                        }
                    }
                },"": function(a, b, d) {
                    var e = c++, f = p, g;
                    if (typeof b === "string" && !/\W/.test(b)) {
                        b = b.toLowerCase();
                        g = b;
                        f = o
                    }
                    f("parentNode", b, e, a, g, d)
                },"~": function(a, b, d) {
                    var e = c++, f = p, g;
                    if (typeof b === "string" && !/\W/.test(b)) {
                        b = b.toLowerCase();
                        g = b;
                        f = o
                    }
                    f("previousSibling", b, e, a, g, d)
                }},find: {ID: function(a, b, c) {
                    if (typeof b.getElementById !== "undefined" && !c) {
                        var d = b.getElementById(a[1]);
                        return d ? [d] : []
                    }
                },NAME: function(a, b) {
                    if (typeof b.getElementsByName !== "undefined") {
                        var c = [], d = b.getElementsByName(a[1]);
                        for (var e = 0, f = d.length; e < f; e++) {
                            if (d[e].getAttribute("name") === a[1]) {
                                c.push(d[e])
                            }
                        }
                        return c.length === 0 ? null : c
                    }
                },TAG: function(a, b) {
                    return b.getElementsByTagName(a[1])
                }},preFilter: {CLASS: function(a, b, c, d, e, f) {
                    a = " " + a[1].replace(/\\/g, "") + " ";
                    if (f) {
                        return a
                    }
                    for (var g = 0, h; (h = b[g]) != null; g++) {
                        if (h) {
                            if (e ^ (h.className && (" " + h.className + " ").replace(/[\t\n]/g, " ").indexOf(a) >= 0)) {
                                if (!c) {
                                    d.push(h)
                                }
                            } else if (c) {
                                b[g] = false
                            }
                        }
                    }
                    return false
                },ID: function(a) {
                    return a[1].replace(/\\/g, "")
                },TAG: function(a, b) {
                    return a[1].toLowerCase()
                },CHILD: function(a) {
                    if (a[1] === "nth") {
                        var b = /(-?)(\d*)n((?:\+|-)?\d*)/.exec(a[2] === "even" && "2n" || a[2] === "odd" && "2n+1" || !/\D/.test(a[2]) && "0n+" + a[2] || a[2]);
                        a[2] = b[1] + (b[2] || 1) - 0;
                        a[3] = b[3] - 0
                    }
                    a[0] = c++;
                    return a
                },ATTR: function(a, b, c, d, e, f) {
                    var g = a[1].replace(/\\/g, "");
                    if (!f && h.attrMap[g]) {
                        a[1] = h.attrMap[g]
                    }
                    if (a[2] === "~=") {
                        a[4] = " " + a[4] + " "
                    }
                    return a
                },PSEUDO: function(a, c, d, e, f) {
                    if (a[1] === "not") {
                        if ((b.exec(a[3]) || "").length > 1 || /^\w/.test(a[3])) {
                            a[3] = g(a[3], null, null, c)
                        } else {
                            var i = g.filter(a[3], c, d, true ^ f);
                            if (!d) {
                                e.push.apply(e, i)
                            }
                            return false
                        }
                    } else if (h.match.POS.test(a[0]) || h.match.CHILD.test(a[0])) {
                        return true
                    }
                    return a
                },POS: function(a) {
                    a.unshift(true);
                    return a
                }},filters: {enabled: function(a) {
                    return a.disabled === false && a.type !== "hidden"
                },disabled: function(a) {
                    return a.disabled === true
                },checked: function(a) {
                    return a.checked === true
                },selected: function(a) {
                    a.parentNode.selectedIndex;
                    return a.selected === true
                },parent: function(a) {
                    return !!a.firstChild
                },empty: function(a) {
                    return !a.firstChild
                },has: function(a, b, c) {
                    return !!g(c[3], a).length
                },header: function(a) {
                    return /h\d/i.test(a.nodeName)
                },text: function(a) {
                    return "text" === a.type
                },radio: function(a) {
                    return "radio" === a.type
                },checkbox: function(a) {
                    return "checkbox" === a.type
                },file: function(a) {
                    return "file" === a.type
                },password: function(a) {
                    return "password" === a.type
                },submit: function(a) {
                    return "submit" === a.type
                },image: function(a) {
                    return "image" === a.type
                },reset: function(a) {
                    return "reset" === a.type
                },button: function(a) {
                    return "button" === a.type || a.nodeName.toLowerCase() === "button"
                },input: function(a) {
                    return /input|select|textarea|button/i.test(a.nodeName)
                }},setFilters: {first: function(a, b) {
                    return b === 0
                },last: function(a, b, c, d) {
                    return b === d.length - 1
                },even: function(a, b) {
                    return b % 2 === 0
                },odd: function(a, b) {
                    return b % 2 === 1
                },lt: function(a, b, c) {
                    return b < c[3] - 0
                },gt: function(a, b, c) {
                    return b > c[3] - 0
                },nth: function(a, b, c) {
                    return c[3] - 0 === b
                },eq: function(a, b, c) {
                    return c[3] - 0 === b
                }},filter: {PSEUDO: function(a, b, c, d) {
                    var e = b[1], f = h.filters[e];
                    if (f) {
                        return f(a, c, b, d)
                    } else if (e === "contains") {
                        return (a.textContent || a.innerText || g.getText([a]) || "").indexOf(b[3]) >= 0
                    } else if (e === "not") {
                        var i = b[3];
                        for (var j = 0, k = i.length; j < k; j++) {
                            if (i[j] === a) {
                                return false
                            }
                        }
                        return true
                    } else {
                        g.error("Syntax error, unrecognized expression: " + e)
                    }
                },CHILD: function(a, b) {
                    var c = b[1], d = a;
                    switch (c) {
                        case "only":
                        case "first":
                            while (d = d.previousSibling) {
                                if (d.nodeType === 1) {
                                    return false
                                }
                            }
                            if (c === "first") {
                                return true
                            }
                            d = a;
                        case "last":
                            while (d = d.nextSibling) {
                                if (d.nodeType === 1) {
                                    return false
                                }
                            }
                            return true;
                        case "nth":
                            var e = b[2], f = b[3];
                            if (e === 1 && f === 0) {
                                return true
                            }
                            var g = b[0], h = a.parentNode;
                            if (h && (h.sizcache !== g || !a.nodeIndex)) {
                                var i = 0;
                                for (d = h.firstChild; d; d = d.nextSibling) {
                                    if (d.nodeType === 1) {
                                        d.nodeIndex = ++i
                                    }
                                }
                                h.sizcache = g
                            }
                            var j = a.nodeIndex - f;
                            if (e === 0) {
                                return j === 0
                            } else {
                                return j % e === 0 && j / e >= 0
                            }
                    }
                },ID: function(a, b) {
                    return a.nodeType === 1 && a.getAttribute("id") === b
                },TAG: function(a, b) {
                    return b === "*" && a.nodeType === 1 || a.nodeName.toLowerCase() === b
                },CLASS: function(a, b) {
                    return (" " + (a.className || a.getAttribute("class")) + " ").indexOf(b) > -1
                },ATTR: function(a, b) {
                    var c = b[1], d = h.attrHandle[c] ? h.attrHandle[c](a) : a[c] != null ? a[c] : a.getAttribute(c), e = d + "", f = b[2], g = b[4];
                    return d == null ? f === "!=" : f === "=" ? e === g : f === "*=" ? e.indexOf(g) >= 0 : f === "~=" ? (" " + e + " ").indexOf(g) >= 0 : !g ? e && d !== false : f === "!=" ? e !== g : f === "^=" ? e.indexOf(g) === 0 : f === "$=" ? e.substr(e.length - g.length) === g : f === "|=" ? e === g || e.substr(0, g.length + 1) === g + "-" : false
                },POS: function(a, b, c, d) {
                    var e = b[2], f = h.setFilters[e];
                    if (f) {
                        return f(a, c, b, d)
                    }
                }}};
        g.selectors = h;
        var i = h.match.POS, j = function(a, b) {
            return "\\" + (b - 0 + 1)
        };
        for (var k in h.match) {
            h.match[k] = new RegExp(h.match[k].source + /(?![^\[]*\])(?![^\(]*\))/.source);
            h.leftMatch[k] = new RegExp(/(^(?:.|\r|\n)*?)/.source + h.match[k].source.replace(/\\(\d+)/g, j))
        }
        var l = function(a, b) {
            a = Array.prototype.slice.call(a, 0);
            if (b) {
                b.push.apply(b, a);
                return b
            }
            return a
        };
        try {
            Array.prototype.slice.call(document.documentElement.childNodes, 0)[0].nodeType
        } catch (m) {
            l = function(a, b) {
                var c = b || [], e = 0;
                if (d.call(a) === "[object Array]") {
                    Array.prototype.push.apply(c, a)
                } else {
                    if (typeof a.length === "number") {
                        for (var f = a.length; e < f; e++) {
                            c.push(a[e])
                        }
                    } else {
                        for (; a[e]; e++) {
                            c.push(a[e])
                        }
                    }
                }
                return c
            }
        }
        var n;
        if (document.documentElement.compareDocumentPosition) {
            n = function(a, b) {
                if (!a.compareDocumentPosition || !b.compareDocumentPosition) {
                    if (a == b) {
                        e = true
                    }
                    return a.compareDocumentPosition ? -1 : 1
                }
                var c = a.compareDocumentPosition(b) & 4 ? -1 : a === b ? 0 : 1;
                if (c === 0) {
                    e = true
                }
                return c
            }
        } else if ("sourceIndex" in document.documentElement) {
            n = function(a, b) {
                if (!a.sourceIndex || !b.sourceIndex) {
                    if (a == b) {
                        e = true
                    }
                    return a.sourceIndex ? -1 : 1
                }
                var c = a.sourceIndex - b.sourceIndex;
                if (c === 0) {
                    e = true
                }
                return c
            }
        } else if (document.createRange) {
            n = function(a, b) {
                if (!a.ownerDocument || !b.ownerDocument) {
                    if (a == b) {
                        e = true
                    }
                    return a.ownerDocument ? -1 : 1
                }
                var c = a.ownerDocument.createRange(), d = b.ownerDocument.createRange();
                c.setStart(a, 0);
                c.setEnd(a, 0);
                d.setStart(b, 0);
                d.setEnd(b, 0);
                var f = c.compareBoundaryPoints(Range.START_TO_END, d);
                if (f === 0) {
                    e = true
                }
                return f
            }
        }
        g.getText = function(a) {
            var b = "", c;
            for (var d = 0; a[d]; d++) {
                c = a[d];
                if (c.nodeType === 3 || c.nodeType === 4) {
                    b += c.nodeValue
                } else if (c.nodeType !== 8) {
                    b += g.getText(c.childNodes)
                }
            }
            return b
        };
        (function() {
            var a = document.createElement("div"), b = "script" + (new Date).getTime();
            a.innerHTML = "<a name='" + b + "'/>";
            var c = document.documentElement;
            c.insertBefore(a, c.firstChild);
            if (document.getElementById(b)) {
                h.find.ID = function(a, b, c) {
                    if (typeof b.getElementById !== "undefined" && !c) {
                        var d = b.getElementById(a[1]);
                        return d ? d.id === a[1] || typeof d.getAttributeNode !== "undefined" && d.getAttributeNode("id").nodeValue === a[1] ? [d] : undefined : []
                    }
                };
                h.filter.ID = function(a, b) {
                    var c = typeof a.getAttributeNode !== "undefined" && a.getAttributeNode("id");
                    return a.nodeType === 1 && c && c.nodeValue === b
                }
            }
            c.removeChild(a);
            c = a = null
        })();
        (function() {
            var a = document.createElement("div");
            a.appendChild(document.createComment(""));
            if (a.getElementsByTagName("*").length > 0) {
                h.find.TAG = function(a, b) {
                    var c = b.getElementsByTagName(a[1]);
                    if (a[1] === "*") {
                        var d = [];
                        for (var e = 0; c[e]; e++) {
                            if (c[e].nodeType === 1) {
                                d.push(c[e])
                            }
                        }
                        c = d
                    }
                    return c
                }
            }
            a.innerHTML = "<a href='#'></a>";
            if (a.firstChild && typeof a.firstChild.getAttribute !== "undefined" && a.firstChild.getAttribute("href") !== "#") {
                h.attrHandle.href = function(a) {
                    return a.getAttribute("href", 2)
                }
            }
            a = null
        })();
        if (document.querySelectorAll) {
            (function() {
                var a = g, b = document.createElement("div");
                b.innerHTML = "<p class='TEST'></p>";
                if (b.querySelectorAll && b.querySelectorAll(".TEST").length === 0) {
                    return
                }
                g = function(b, c, d, e) {
                    c = c || document;
                    if (!e && c.nodeType === 9 && !g.isXML(c)) {
                        try {
                            return l(c.querySelectorAll(b), d)
                        } catch (f) {
                        }
                    }
                    return a(b, c, d, e)
                };
                for (var c in a) {
                    g[c] = a[c]
                }
                b = null
            })()
        }
        (function() {
            var a = document.createElement("div");
            a.innerHTML = "<div class='test e'></div><div class='test'></div>";
            if (!a.getElementsByClassName || a.getElementsByClassName("e").length === 0) {
                return
            }
            a.lastChild.className = "e";
            if (a.getElementsByClassName("e").length === 1) {
                return
            }
            h.order.splice(1, 0, "CLASS");
            h.find.CLASS = function(a, b, c) {
                if (typeof b.getElementsByClassName !== "undefined" && !c) {
                    return b.getElementsByClassName(a[1])
                }
            };
            a = null
        })();
        g.contains = document.compareDocumentPosition ? function(a, b) {
            return !!(a.compareDocumentPosition(b) & 16)
        } : function(a, b) {
            return a !== b && (a.contains ? a.contains(b) : true)
        };
        g.isXML = function(a) {
            var b = (a ? a.ownerDocument || a : 0).documentElement;
            return b ? b.nodeName !== "HTML" : false
        };
        var q = function(a, b) {
            var c = [], d = "", e, f = b.nodeType ? [b] : b;
            while (e = h.match.PSEUDO.exec(a)) {
                d += e[0];
                a = a.replace(h.match.PSEUDO, "")
            }
            a = h.relative[a] ? a + "*" : a;
            for (var i = 0, j = f.length; i < j; i++) {
                g(a, f[i], c)
            }
            return g.filter(d, c)
        };
        return g
    });
    f("util.date", function() {
        return function(a) {
            if ((a + "").length < 13) {
                a = a * Math.pow(10, 13 - (a + "").length)
            }
            var b = (new Date).getTime(), c = new Date(parseInt(a)), d, e, f, g, h, i, j = [(j = c.getHours()) < 10 ? "0" : "", j].join(""), k = [(k = c.getMinutes()) < 10 ? "0" : "", k].join(""), l, m;
            ct = b;
            b = new Date(a);
            d = b.getFullYear();
            e = b.getMonth();
            l = b.getDate();
            f = c.getFullYear();
            g = c.getMonth() + 1;
            i = c.getDate();
            if (a - (new Date(d, 0, 1)).getTime() > -1) {
                return [[g + "月", i + "日"].join(""), [j, k].join(":")].join(" ")
            }
            return [[f + "年", g + "月", i + "日"].join(""), [j, k].join(":")].join(" ")
        }
    });
    f("async.deferred", function() {
        var a = function(a) {
            return [].slice.call(a)
        }, b = g("util.type").isFunction, c = g("util.array").indexOf, d = g("util.array").forEach, e = g("util.delay");
        return function() {
            var f = "pending";
            var g;
            var h = function() {
                var a = {resolve: [],inject: [],notify: []};
                return {add: function(b, c) {
                        a[b].push(c)
                    },remove: function(b, d) {
                        a[b].splice(c(a[b], d), 1)
                    },fire: function(b, c) {
                        d(a[b], function(a) {
                            (function(a) {
                                if (b == "notify") {
                                    a.apply(null, c)
                                } else {
                                    e(function() {
                                        a.apply(null, c)
                                    })
                                }
                            })(a)
                        })
                    }}
            }();
            var i = function(a, c, d) {
                if (b(a)) {
                    if (f == "resolved") {
                        e(function() {
                            a.apply(null, g)
                        })
                    } else if (f == "pending") {
                        h.add("resolve", a)
                    }
                }
                if (b(c)) {
                    if (f == "injected") {
                        e(function() {
                            c.apply(null, g)
                        })
                    } else if (f == "pending") {
                        h.add("inject", c)
                    }
                }
                if (b(d)) {
                    h.add("notify", d)
                }
            };
            var j = {};
            j.all = function(a) {
                i(a, a);
                return j
            };
            j.done = function(a) {
                i(a);
                return j
            };
            j.fail = function(a) {
                i(null, a);
                return j
            };
            j.progress = function(a) {
                i(null, null, a);
                return j
            };
            j.unProgress = function(a) {
                h.remove("notify", a)
            };
            j.then = function(a, b, c) {
                i(a, b, c);
                return j
            };
            j.resolve = function() {
                if (f == "pending") {
                    f = "resolved";
                    g = a(arguments);
                    h.fire("resolve", g)
                }
                return j
            };
            j.inject = function() {
                if (f == "pending") {
                    f = "injected";
                    g = a(arguments);
                    h.fire("inject", g)
                }
                return j
            };
            j.notify = function() {
                h.fire("notify", a(arguments));
                return j
            };
            j.state = function() {
                return f
            };
            j.outer = function(a) {
                return {all: function(b) {
                        j.all(b);
                        a(j);
                        return j
                    },done: function(b) {
                        j.done(b);
                        a(j);
                        return j
                    },fail: function(b) {
                        j.fail(b);
                        a(j);
                        return j
                    },progress: function(b) {
                        j.progress(b);
                        a(j);
                        return j
                    },unProgress: function(a) {
                        j.unProgress(a);
                        return j
                    },then: function(b, c, d) {
                        j.then(b, c, d);
                        a(j);
                        return j
                    }}
            };
            return j
        }
    });
    f("async.delay", function() {
        var a = g("async.deferred"), b = g("util.type");
        return function(c, d, e, f) {
            var g = new a;
            if (d === void 0 && b.is(c, "Number")) {
                d = c;
                c = function() {
                    return d
                }
            }
            e = e ? [].concat(e) : [];
            f = f || null;
            d = d || 0;
            func = b.isFunction(c) ? c : function() {
                return c
            };
            return g.outer(function() {
                setTimeout(function() {
                    g.resolve(func.apply(f, e))
                }, d)
            })
        }
    });
    f("async.loader", function() {
        function i(a, b, c) {
            var e;
            f.insertBefore(a, f.firstChild);
            if (c) {
                e = setTimeout(function() {
                    a.onreadystatechange = null;
                    a.onload = null;
                    a.onerror = null;
                    h(a);
                    a = null;
                    b.inject({error: true,reson: "timeout"})
                }, c)
            }
            if (d) {
                a.onreadystatechange = function() {
                    if (a.readyState.toLowerCase() == "loaded" || a.readyState.toLowerCase() == "complete") {
                        if (e) {
                            clearTimeout(e)
                        }
                        a.onreadystatechange = null;
                        a.onerror = null;
                        b.resolve()
                    }
                }
            } else {
                a.onload = function() {
                    if (e) {
                        clearTimeout(e)
                    }
                    a.onload = null;
                    a.onerror = null;
                    b.resolve()
                }
            }
            a.onerror = function() {
                if (e) {
                    clearTimeout(e)
                }
                h(a);
                a.onload = null;
                a.onerror = null;
                b.inject()
            }
        }
        var a = g("async.deferred"), b = g("util.browser"), d = b.IE, e = b.IE6 || b.IE7, f = c.getElementsByTagName("head")[0];
        var h = function() {
            return e ? function() {
                var a;
                return function(b) {
                    if (!a) {
                        a = c.createElement("div")
                    }
                    a.append(b);
                    a.innerHTML = "";
                    b = null
                }
            }() : function(a) {
                if (a && a.parentNode) {
                    a.parentNode.removeChild(a);
                    a = null
                }
            }
        }();
        var j = {js: function(b, d) {
                var e = new a, f = d.charset || "UTF-8", g = parseInt(d.timeout) || 30 * 1e3, h = c.createElement("script");
                h.type = "text/javascript";
                h.charset = f;
                return e.outer(function() {
                    e.notify("element", h);
                    i(h, e, g);
                    h.src = b
                })
            },css: function(b, d) {
                var e = new a, f = d.charset || "UTF-8", g = parseInt(d.timeout) || 30 * 1e3, h = c.createElement("link");
                h.type = "text/css";
                h.charset = f;
                h.rel = "stylesheet";
                return e.outer(function() {
                    i(h, e, g);
                    h.href = b
                })
            },img: function(b, c) {
                var d = new a, e = new Image, f = parseInt(c.timeout) || 10 * 1e3, g = null;
                e.onload = function() {
                    d.resolve(e)
                };
                e.onerror = function() {
                    if (g) {
                        clearTimeout(g)
                    }
                    d.inject({error: true,reson: "load"})
                };
                if (f) {
                    g = setTimeout(function() {
                        e.onerror = function() {
                            d.inject({error: true,reson: "timeout"})
                        }
                    }, f)
                }
                return d.outer(function() {
                    e.src = b
                })
            }};
        return function(a, b, c) {
            return j[a] && j[a](b, c || {})
        }
    });
    f("async.jsonp", function() {
        var a = g("async.deferred"), d = g("util.string").parseURL, f = g("util.object").assign, h = g("async.loader"), i = g("util.browser"), j = g("util.delay"), k = i.IE6 || i.IE7, l = 0;
        var m = function() {
            return k && e ? function() {
                var a;
                return function(b) {
                    if (!a) {
                        a = c.createElement("div")
                    }
                    a.appendChild(b);
                    a.innerHTML = ""
                }
            }() : function(a) {
                if (a && a.parentNode) {
                    a.parentNode.removeChild(a)
                }
            }
        }();
        return function(c, e, g) {
            var i = new a, k = g && g.timeout || 10 * 1e3, n = d(c), o, p;
            n.query = f(n.query, e || {});
            n.query.callback = n.query.callback || "IM_" + +(new Date) + l++;
            n.query.__rnd = +(new Date);
            b[n.query.callback] = function(a) {
                if (p) {
                    clearTimeout(p)
                }
                i.resolve(a);
                m(o)
            };
            p = j(function() {
                i.inject({reson: "timeout"});
                m(o)
            }, k);
            return i.outer(function() {
                h("js", n.toUrl(), g || {}).progress(function(a, b) {
                    if (a == "element") {
                        o = b
                    }
                })
            })
        }
    });
    f("async.parallel", function() {
        var a = g("async.deferred"), b = g("util.array").forEach, c = g("util.array").associate, d = g("util.type").isFunction;
        return function(e, f) {
            var g = new a, h = e.slice(0), i = [], j = 0, k = function() {
                if (j == h.length) {
                    if (f && f.length) {
                        i = c(i, f)
                    }
                    g.resolve.call(null, i)
                }
            }, l = function() {
                b(h, function(a, b) {
                    if (a && d(a.then)) {
                        i[b] = void 0;
                        a.all(function(a) {
                            i[b] = a;
                            j++;
                            k()
                        })
                    } else {
                        i[b] = a;
                        j++
                    }
                });
                k()
            };
            return g.outer(function() {
                setTimeout(l, 0)
            })
        }
    });
    f("async.queue", function() {
        var a = g("async.deferred"), b = g("util.array").associate, c = g("util.type").isFunction;
        return function(d, e, f) {
            var g = new a, h = f ? d : d.slice(0), i = [], j = -1, k = function(a) {
                k = e && e.length ? function(a) {
                    return e[a]
                } : function(a) {
                    return a
                };
                return k(a)
            }, l = function() {
                j++;
                var a = h.shift();
                if (a && c(a.then)) {
                    a.all(function(a) {
                        g.notify(k(j), a, d);
                        i[j] = a;
                        l()
                    })
                } else if (a) {
                    if (c(a)) {
                        var f = a(i[j - 1], i);
                        if (f && c(f.then)) {
                            f.all(function(a) {
                                g.notify(k(j), a, d);
                                i[j] = a;
                                l()
                            })
                        } else {
                            g.notify(k(j), f, d);
                            i[j] = f;
                            l()
                        }
                    } else {
                        g.notify(k(j), a, d);
                        i[j] = a;
                        l()
                    }
                } else {
                    if (e && e.length) {
                        i = b(i, e)
                    }
                    g.resolve.call(null, i)
                }
            };
            return g.outer(function() {
                setTimeout(l, 0)
            })
        }
    });
    f("notice.Message", function() {
        var a = g("util.type"), b = g("util.array");
        return {type: "message",fix: function(c) {
                var d = c.items[0], e = b.associate(d, d.length <= 6 ? t : s);
                e.card = e.card && !a.isEmptyObject(e.card) ? e.card : c.ext && c.ext[0];
                e.dmIsRemind = c.dm_isRemind;
                return e
            }}
    });
    f("notice.Unreader", function() {
        var a = g("util.object");
        return {type: "unread",fix: function(b) {
                var c = {items: {},groupItems: {},total: 0,lastMid: b.lastmid,dmIsRemind: b.dm_isRemind};
                a.forEach(b.items, function(a, b) {
                    if (a == "total") {
                        c.total = b
                    } else {
                        c.items[a] = b
                    }
                });
                return c
            }}
    });
    f("notice.ChatStates", function() {
        return {type: "chatStatus",fix: function(a) {
                return {status: a.type == "chatStates" ? "chat" : "pause",uid: a.items[0] + ""}
            }}
    });
    f("notice.Dmread", function() {
        return {type: "clear",fix: function(a) {
                return {uid: a.uid}
            }}
    });
    f("notice.GroupChat", function() {
        var a = {320: "createGroup",321: "groupMessage",322: "joinGroup",323: "exitGroup",324: "kickGroup",325: "changeTitle",327: "adminRoleChange",328: "belongChange",329: "delBelong",331: "delMessage",332: "clearMessageUnread",333: "userSetting",334: "delNotice",335: "groupInfoChange",336: "clearNoticeUnread",421: "newJoinNotice",421: "acceptJoinNotice",422: "belongNotice"};
        return {type: "groupChat",fix: function(b) {
                var c = a[b["sub_type"]];
                return {data: b.info,type: c,fixedType: c}
            }}
    });
    f("notice.Synchroniz", function() {
        return {type: "synchroniz",fix: function(a) {
                var b = z.parse(a.syncData), c = a.seq;
                if (/fixed-/.test(c)) {
                    var d = c.split("-");
                    b.fixedType = d[1]
                }
                if (/ui\./.test(b.type)) {
                    switch (b.type) {
                        case "ui.user.click":
                            b = {fixedType: "chatSync",type: b.data.uid ? "addUser" : "addGroup",uid: b.data.uid + "",gid: b.data.gid + ""};
                            break;
                        case "ui.group.click":
                            b = {fixedType: "chatSync",type: "addGroup",data: b.data.uid + ""};
                            break;
                        case "ui.closeChatUser":
                            b = {fixedType: "chatSync",type: "removeUser",data: b.data.uid + ""};
                            break;
                        case "ui.closeChatGroup":
                            b = {fixedType: "chatSync",type: "removeGroup",data: b.data.gid + ""};
                            break;
                        case "ui.miniChatWindow":
                            b = {fixedType: "chatSync",type: "min"};
                            break;
                        case "ui.realCloseChatWindow":
                            b = {fixedType: "chatSync",type: "close"};
                            break;
                        case "ui.changeSendMethod":
                            b = {fixedType: "chatSync",type: "send",data: b.data.status == "Ctrl" ? "C" : "E"};
                            break
                    }
                }
                return b
            }}
    });
    f("notice.Presence", function() {
        return {type: "presence",fix: function(a) {
                return {uid: a.items[0] + "",status: u[a.items[1]]}
            }}
    });
    f("notice.Broadcast", function() {
        return {type: "news",fix: function(a) {
                return a
            }}
    });
    f("notice.Roster", function() {
        return {type: "user",fix: function(a) {
                var b = {};
                switch (a.type) {
                    case "rosterremove":
                        b.act = "remove";
                        b.uid = a.uid + "";
                        break;
                    case "rosterupdate":
                        b.act = "update";
                        b.user = {uid: a.uid + "",nick: a.nick,avatar: a.avatar,status: u[a.status],resource: a.resource,sex: a.sex,group: a.group};
                        break
                }
                return b
            }}
    });
    f("notice.Recent", function() {
        return {type: "recent",fix: function(a) {
                var b = {};
                switch (a.type) {
                    case "rosterrecentremove":
                        b.act = "remove";
                        b.uid = a.uid + "";
                        break
                }
                return b
            }}
    });
    f("notice.SendSync", function() {
        var a = g("util.type"), b = g("util.array");
        return {type: "send",fix: function(c) {
                var d = [];
                c.ext = c.ext || [];
                b.forEach(c.items, function(e, f) {
                    var g = b.associate(e, e.length <= 6 ? r : s);
                    g.long = !!g.long;
                    g.card = g.card && !a.isEmptyObject(g.card) ? g.card : c.ext[f];
                    d.push(g)
                });
                return d
            }}
    });
    f("notice", function() {
        return {msg: g("notice.Message"),unreader: g("notice.Unreader"),chatStates: g("notice.ChatStates"),pauseChat: g("notice.ChatStates"),dmread: g("notice.Dmread"),synchroniz: g("notice.Synchroniz"),presence: g("notice.Presence"),broadcast: g("notice.Broadcast"),rosterupdate: g("notice.Roster"),rosterremove: g("notice.Roster"),rosterrecentremove: g("notice.Recent"),msgsync: g("notice.SendSync"),groupchat: g("notice.GroupChat")}
    });
    f("protocol.Message", function() {
        return {autoResend: false,fix: function(a, b, c, d) {
                var e = {cmd: "msg",uid: a,msg: b || ""};
                if (c && c.length == 2) {
                    e.x = {att_ids: c,media_type: "1"}
                }
                if (d) {
                    e.check_retcode = d
                }
                return e
            },receive: function(a) {
                return {success: a.ret === 0,errorCode: a.ret}
            }}
    });
    f("protocol.Vcard", function() {
        var a = g("util.array");
        return {autoResend: false,fix: function(a) {
                return {cmd: "vcard",uid: a}
            },receive: function(b) {
                b = a.associate(b.items, y);
                b.uid += "";
                return b
            }}
    });
    f("protocol.Roster", function() {
        var a = g("util.array");
        return {autoResend: false,fix: function() {
                return {cmd: "roster",limit: 2e3}
            },receive: function(b) {
                var c = {groups: [],items: [],recents: []};
                a.forEach(b.groups, function(a) {
                    c.groups.push(a)
                });
                a.forEach(b.items, function(b) {
                    var d = a.associate(b, v);
                    d.uid = d.uid + "";
                    d.status = u[d.status];
                    c.items.push(d)
                });
                a.forEach(b.recents, function(b) {
                    var d = a.associate(b, w);
                    d.uid = d.uid + "";
                    d.status = u[d.status];
                    c.recents.push(d)
                });
                return c
            }}
    });
    f("protocol.Recents", function() {
        var a = g("util.array");
        return {autoResend: false,fix: function() {
                return {cmd: "recents"}
            },receive: function(b) {
                var c = [];
                a.forEach(b.recents, function(b) {
                    var d = a.associate(b, w);
                    d.uid = d.uid + "";
                    d.status = u[d.status];
                    c.push(d)
                });
                return c
            }}
    });
    f("protocol.QueryPresence", function() {
        return {autoResend: true,fix: function() {
                return {cmd: "querypresence"}
            },receive: function(a) {
                var b = u[a.items[1]];
                return {status: b}
            }}
    });
    f("protocol.ChatMsgHistory", function() {
        var a = g("util.type"), b = g("util.array");
        return {autoResend: true,fix: function(a, b, c) {
                var d = {cmd: "chat_msg_history",uids: a,count: (b || 10) + ""};
                if (c) {
                    d.page = parseInt(c) || 1
                }
                return d
            },receive: function(c) {
                var d = [];
                c.ext = c.ext || [];
                b.forEach(c.items, function(e, f) {
                    var g = b.associate(e, e.length <= 6 ? r : s);
                    g.uid += "";
                    g.card = g.card && !a.isEmptyObject(g.card) ? g.card : c.ext[f];
                    d.push(g)
                });
                return d
            }}
    });
    f("protocol.Presence", function() {
        var a = g("util.array").indexOf;
        return {autoResend: true,fix: function(b) {
                var c = a(u, b);
                return {cmd: "presence",status: c > -1 ? c : 1}
            },receive: function(a) {
                return {success: a.ret === 0,errorCode: a.ret}
            }}
    });
    f("protocol.Revmsg", function() {
        return {autoResend: true,fix: function(a) {
                var b = {cmd: "revmsg",uid: a};
                return b
            },receive: function(a) {
                return {success: a.ret === 0,errorCode: a.ret}
            }}
    });
    f("protocol.Synchroniz", function() {
        return {autoResend: true,fix: function(a, b) {
                var c = {cmd: "synchroniz",syncData: z.stringify(a),seq: b || ""};
                return c
            },receive: function(a) {
                return "ret" in a ? {success: a.ret === 0,errorCode: a.ret} : {success: a.rev === 0,errorCode: a.rev}
            }}
    });
    f("protocol.ChatStates", function() {
        var a = {chat: "c",pause: "p"};
        return {autoResend: true,fix: function(b, c) {
                var d = {cmd: "chatStates",uid: b,states: a[c] || "p"};
                return d
            },receive: function(a) {
                return {success: a.ret === 0,errorCode: a.ret}
            }}
    });
    f("protocol.ChatSync", function() {
        return {autoResend: true,fix: function(a, b) {
                var c = {cmd: "synchroniz",syncData: z.stringify({type: a,data: b || ""}),seq: "fixed-chatSync"};
                return c
            },receive: function(a) {
                return {success: a.ret === 0,errorCode: a.ret}
            }}
    });
    f("protocol.Blockscreate", function() {
        return {autoResend: false,fix: function(a) {
                return {cmd: "blockscreate",uid: a}
            },receive: function(a) {
                return {success: a.ret === 0,errorCode: a.ret}
            }}
    });
    f("protocol.Restrict", function() {
        var a = g("util.array");
        var b = ["create", "delete", "query"];
        return {autoResend: true,fix: function(c, d) {
                var e = a.indexOf(b, d) > -1 ? d : "query";
                return {cmd: "restrict",uid: c,act: e,seq: e}
            },receive: function(a) {
                var b = {};
                switch (a.seq) {
                    case "query":
                        b.restricted = !!a.restricted;
                        break;
                    case "create":
                    case "delete":
                        b.success = a.ret === 0;
                        b.errorCode = a.ret;
                        break
                }
                return b
            }}
    });
    f("protocol.RosterSearch", function() {
        var a = g("util.array");
        return {autoResend: false,fix: function(a, b) {
                var c = {cmd: "roster_search",key: a,limit: b || 2e3};
                return c
            },receive: function(b) {
                var c = [];
                a.forEach(b.items, function(b) {
                    var d = a.associate(b, x);
                    d.uid += "";
                    d.status = u[d.status];
                    c.push(d)
                });
                return c
            }}
    });
    f("protocol.Usersetting", function() {
        var a = {};
        return {autoResend: false,fix: function(b) {
                var c = this.subcmd, d = {cmd: "usersetting",subcmd: c,seq: c};
                if (c == "set") {
                    b = b || {};
                    for (var e in b) {
                        if (b[e] === void 0) {
                            delete a[e]
                        } else {
                            a[e] = b[e]
                        }
                    }
                    d.data = z.stringify(a)
                }
                return d
            },receive: function(b) {
                var c = {};
                if (b.seq == "get") {
                    try {
                        c = z.parse(b.data);
                        a = c || {}
                    } catch (d) {
                        a = {}
                    }
                } else if (b.seq == "set") {
                    c = {success: b.result == "0",errorCode: b.result}
                }
                return c
            }}
    });
    f("protocol.Statuses", function() {
        return {autoResend: false,fix: function(a, b) {
                return {cmd: "statuses_update",content: a,visible: parseInt(b) || 0}
            },receive: function(a) {
                return {success: a.ret === 0,errorCode: a.ret}
            }}
    });
    f("protocol.Comment", function() {
        return {autoResend: false,fix: function(a, b) {
                return {cmd: "create_comment",id: a,comment: b}
            },receive: function(a) {
                return {success: a.ret === 0,errorCode: a.ret}
            }}
    });
    f("protocol.Like", function() {
        return {autoResend: false,fix: function(a, b, c) {
                return {cmd: "likeupdate",id: a,type: b,act: c}
            },receive: function(a) {
                return {success: a.ret === 0,errorCode: a.ret}
            }}
    });
    f("protocol.Interact", function() {
        return {autoResend: false,fix: function(a) {
                return {cmd: "friends_interact",count: parseInt(a) || 5}
            },receive: function(a) {
                var b = a.items, c = [];
                for (var d = 0, e = b.length; d < e; d++) {
                    c.push({uid: b[d][0],nick: b[d][1]})
                }
                return c
            }}
    });
    f("protocol.InviteResponse", function() {
        return {autoResend: false,fix: function(a) {
                return {cmd: "invite_response",inviteid: a,response: 1}
            },receive: function(a) {
                return {success: a.ret === 0,errorCode: a.ret}
            }}
    });
    f("protocol.TestNotice", function() {
        return {autoResend: true,fix: function(a) {
                return {cmd: "test_notice",data: a}
            },receive: function(a) {
                return a
            }}
    });
    f("protocol", function() {
        return {msg: g("protocol.Message"),vcard: g("protocol.Vcard"),roster: g("protocol.Roster"),recents: g("protocol.Recents"),querypresence: g("protocol.QueryPresence"),chatMsgHistory: g("protocol.ChatMsgHistory"),presence: g("protocol.Presence"),revmsg: g("protocol.Revmsg"),synchroniz: g("protocol.Synchroniz"),chatStates: g("protocol.ChatStates"),chatSync: g("protocol.ChatSync"),blockscreate: g("protocol.Blockscreate"),restrict: g("protocol.Restrict"),rosterSearch: g("protocol.RosterSearch"),usersetting: g("protocol.Usersetting"),statusesUpdate: g("protocol.Statuses"),createComment: g("protocol.Comment"),likeupdate: g("protocol.Like"),friendsInteract: g("protocol.Interact"),inviteResponse: g("protocol.InviteResponse"),testNotice: g("protocol.TestNotice")}
    });
    f("base.Error", function() {
        return {SUCCESS: 0,NAS_FAIL: 1,HANDSHAKE_FAIL: 2,CONNECT_FAIL: 3,PUBLISH_FAIL: 4,PACKET_FAIL: 5,SOCKET_FAIL: 6,RESPONSE_TIMEOUT: 11,HEARTBEAT_TIMEOUT: 12}
    });
    f("base.Gray", function() {
        var a = g("util.array").indexOf;
        return function() {
            var b = location.href.match(/\/\/(\w*)\./), c = b && b[1] || "home";
            return ~a(h, c) ? "on" : "off"
        }()
    });
    f("base.Config", function() {
        var a = {im_space: "$WBIM",im_root: "WB_webim",im_class: "WB_webim",im_type: "all",im_gray: "off",im_source: "209678993",im_version: +(new Date),im_cache: false,im_lang: "",im_test: e,im_test_server: "127.0.0.1",im_test_port: "6789",cometd_level: "info",auto_connect: 0 * 1e3,upload_swf_path: "http://service.weibo.com/staticjs/tools/upload.swf",storage_swf_path: "http://js.t.sinajs.cn/t6/webim/swf/sync_storage.swf",audio_swf_path: "http://js.t.sinajs.cn/t6/webim/swf/audio.swf"};
        var c = g("util.type"), d = g("util.object"), f = g("util.string"), h = g("util.cookie"), i = d.extend(d.extend(a, b.$IM_CONFIG || {}), f.parseQuery(location.search.substring(1)));
        i.uid = function() {
            var a = unescape(h.get("SUP")), b = a.match(/uid=([0-9]+)&/);
            if (a && b && b[1]) {
                return b[1]
            }
        }();
        i.im_gray = i.im_gray == "on" ? "on" : g("base.Gray") || "off";
        i.im_lang = i.im_lang || b.$CONFIG && b.$CONFIG.lang || "zh-cn";
        i.im_test = !!parseInt(i.im_test);
        b.$IM_CONFIG = i;
        return function(a) {
            return i[a]
        }
    });
    f("cometd.Transport", function() {
        var a = g("util.type"), c = g("util.delay");
        var d = function() {
            var d;
            var e;
            this.registered = function(a, b) {
                d = a;
                e = b
            };
            this.unregistered = function() {
                d = null;
                e = null
            };
            this._debug = function() {
                if (e && e._debug) {
                    e._debug.apply(e, arguments)
                }
            };
            this._mixin = function() {
                return e._mixin.apply(e, arguments)
            };
            this.getConfiguration = function() {
                return e.getConfiguration()
            };
            this.getAdvice = function() {
                return e.getAdvice()
            };
            this.setTimeout = function(a, b) {
                return c(a, b)
            };
            this.clearTimeout = function(a) {
                b.clearTimeout(a)
            };
            this.convertToMessages = function(b) {
                if (a.isString(b)) {
                    try {
                        return z.parse(b)
                    } catch (c) {
                        this._debug("Could not convert to JSON the following string", '"' + b + '"');
                        throw c
                    }
                }
                if (a.isArray(b)) {
                    return b
                }
                if (b === undefined || b === null) {
                    return []
                }
                if (a.isObject(b)) {
                    return [b]
                }
                throw "Conversion Error " + b + ", typeof " + typeof b
            };
            this.accept = function(a, b, c) {
                throw "Abstract"
            };
            this.getType = function() {
                return d
            };
            this.send = function(a, b) {
                throw "Abstract"
            };
            this.reset = function() {
                this._debug("Transport", d, "reset")
            };
            this.abort = function() {
                this._debug("Transport", d, "aborted")
            };
            this.toString = function() {
                return this.getType()
            }
        };
        d.derive = function(a) {
            function b() {
            }
            b.prototype = a;
            return new b
        };
        return d
    });
    f("cometd.RequestTransport", function() {
        var a = g("cometd.Transport"), b = g("util.array");
        return function() {
            function n(a) {
                if (f !== null) {
                    throw "Concurrent metaConnect requests not allowed, request id=" + f.id + " not yet completed"
                }
                var b = ++e;
                this._debug("Transport", this.getType(), "metaConnect send, request", b, "envelope", a);
                var c = {id: b,metaConnect: true};
                j.call(this, a, c);
                f = c
            }
            function m(a, c) {
                var d = b.indexOf(g, a);
                if (d >= 0) {
                    g.splice(d, 1)
                }
                if (h.length > 0) {
                    var e = h.shift();
                    var f = e[0];
                    var j = e[1];
                    this._debug("Transport dequeued request", j.id);
                    if (c) {
                        if (this.getConfiguration().autoBatch) {
                            i.call(this, f)
                        }
                        k.call(this, f);
                        this._debug("Transport completed request", a.id, f)
                    } else {
                        var l = this;
                        this.setTimeout(function() {
                            l.complete(j, false, j.metaConnect);
                            f.onFailure(j.xhr, f.messages, "error", "Previous request failed")
                        }, 0)
                    }
                }
            }
            function l(a) {
                var b = a.id;
                this._debug("Transport", this.getType(), "metaConnect complete, request", b);
                if (f !== null && f.id !== b) {
                    throw "Longpoll request mismatch, completing request " + b
                }
                f = null
            }
            function k(a) {
                var b = ++e;
                var c = {id: b,metaConnect: false};
                if (g.length < this.getConfiguration().maxConnections - 1) {
                    g.push(c);
                    j.call(this, a, c)
                } else {
                    this._debug("Transport", this.getType(), "queueing request", b, "envelope", a);
                    h.push([a, c])
                }
            }
            function j(a, b) {
                this.transportSend(a, b);
                b.expired = false;
                if (!a.sync) {
                    var c = this.getConfiguration().maxNetworkDelay;
                    var d = c;
                    if (b.metaConnect === true) {
                        d += this.getAdvice().timeout
                    }
                    this._debug("Transport", this.getType(), "waiting at most", d, "ms for the response, maxNetworkDelay", c);
                    var e = this;
                    b.timeout = this.setTimeout(function() {
                        b.expired = true;
                        if (b.xhr) {
                            b.xhr.abort()
                        }
                        var c = "Request " + b.id + " of transport " + e.getType() + " exceeded " + d + " ms max network delay";
                        e._debug(c);
                        e.complete(b, false, b.metaConnect);
                        a.onFailure(b.xhr, a.messages, "timeout", c)
                    }, d)
                }
            }
            function i(a) {
                while (h.length > 0) {
                    var b = h[0];
                    var c = b[0];
                    var d = b[1];
                    if (c.url === a.url && c.sync === a.sync) {
                        h.shift();
                        a.messages = a.messages.concat(c.messages);
                        this._debug("Coalesced", c.messages.length, "messages from request", d.id);
                        continue
                    }
                    break
                }
            }
            var c = new a;
            var d = a.derive(c);
            var e = 0;
            var f = null;
            var g = [];
            var h = [];
            d.complete = function(a, b, c) {
                if (c) {
                    l.call(this, a)
                } else {
                    m.call(this, a, b)
                }
            };
            d.transportSend = function(a, b) {
                throw "Abstract"
            };
            d.transportSuccess = function(a, b, c) {
                if (!b.expired) {
                    this.clearTimeout(b.timeout);
                    this.complete(b, true, b.metaConnect);
                    if (c && c.length > 0) {
                        a.onSuccess(c)
                    } else {
                        a.onFailure(b.xhr, a.messages, "Empty HTTP response")
                    }
                }
            };
            d.transportFailure = function(a, b, c, d) {
                if (!b.expired) {
                    this.clearTimeout(b.timeout);
                    this.complete(b, false, b.metaConnect);
                    a.onFailure(b.xhr, a.messages, c, d)
                }
            };
            d.send = function(a, b) {
                if (b) {
                    n.call(this, a)
                } else {
                    k.call(this, a)
                }
            };
            d.abort = function() {
                c.abort();
                for (var a = 0; a < g.length; ++a) {
                    var b = g[a];
                    this._debug("Aborting request", b);
                    if (b.xhr) {
                        b.xhr.abort()
                    }
                }
                if (f) {
                    this._debug("Aborting metaConnect request", f);
                    if (f.xhr) {
                        f.xhr.abort()
                    }
                }
                this.reset()
            };
            d.reset = function() {
                c.reset();
                f = null;
                g = [];
                h = []
            };
            return d
        }
    });
    f("cometd.TransportRegistry", function() {
        return function() {
            var a = [];
            var b = {};
            this.getTransportTypes = function() {
                return a.slice(0)
            };
            this.findTransportTypes = function(c, d, e) {
                var f = [];
                for (var g = 0; g < a.length; ++g) {
                    var h = a[g];
                    if (b[h].accept(c, d, e) === true) {
                        f.push(h)
                    }
                }
                return f
            };
            this.negotiateTransport = function(c, d, e, f) {
                for (var g = 0; g < a.length; ++g) {
                    var h = a[g];
                    for (var i = 0; i < c.length; ++i) {
                        if (h === c[i]) {
                            var j = b[h];
                            if (j.accept(d, e, f) === true) {
                                return j
                            }
                        }
                    }
                }
                return null
            };
            this.add = function(c, d, e) {
                var f = false;
                for (var g = 0; g < a.length; ++g) {
                    if (a[g] === c) {
                        f = true;
                        break
                    }
                }
                if (!f) {
                    if (typeof e !== "number") {
                        a.push(c)
                    } else {
                        a.splice(e, 0, c)
                    }
                    b[c] = d
                }
                return !f
            };
            this.find = function(c) {
                for (var d = 0; d < a.length; ++d) {
                    if (a[d] === c) {
                        return b[c]
                    }
                }
                return null
            };
            this.remove = function(c) {
                for (var d = 0; d < a.length; ++d) {
                    if (a[d] === c) {
                        a.splice(d, 1);
                        var e = b[c];
                        delete b[c];
                        return e
                    }
                }
                return null
            };
            this.clear = function() {
                a = [];
                b = {}
            };
            this.reset = function() {
                for (var c = 0; c < a.length; ++c) {
                    b[a[c]].reset()
                }
            }
        }
    });
    f("cometd.Cometd", function() {
        var a = g("cometd.TransportRegistry"), b = g("util.delay"), c = g("util.type");
        return function(d) {
            function by(a) {
                var b = p[a[0]];
                if (b) {
                    delete b[a[1]];
                    e._debug("Removed listener", a)
                }
            }
            function bx(a, b, c, d) {
                var f = bw(b, c);
                e._debug("Adding listener on", a, "with scope", f.scope, "and callback", f.method);
                var g = {channel: a,scope: f.scope,callback: f.method,listener: d};
                var h = p[a];
                if (!h) {
                    h = [];
                    p[a] = h
                }
                var i = h.push(g) - 1;
                g.id = i;
                g.handle = [a, i];
                e._debug("Added listener", g, "for channel", a, "having id =", i);
                return g.handle
            }
            function bw(a, b) {
                var c = {scope: a,method: b};
                if (A(a)) {
                    c.scope = undefined;
                    c.method = a
                } else {
                    if (z(b)) {
                        if (!a) {
                            throw "Invalid scope " + a
                        }
                        c.method = a[b];
                        if (!A(c.method)) {
                            throw "Invalid callback " + b + " for scope " + a
                        }
                    } else if (!A(b)) {
                        throw "Invalid callback " + b
                    }
                }
                return c
            }
            function bv(a) {
                var b = p[a];
                if (b) {
                    for (var c = 0; c < b.length; ++c) {
                        if (b[c]) {
                            return true
                        }
                    }
                }
                return false
            }
            function bu(a) {
                a = I(a);
                if (a === undefined || a === null) {
                    return
                }
                Z(a.advice);
                var b = a.channel;
                switch (b) {
                    case "/meta/handshake":
                        bc(a);
                        break;
                    case "/meta/connect":
                        bf(a);
                        break;
                    case "/meta/disconnect":
                        bi(a);
                        break;
                    case "/meta/subscribe":
                        bl(a);
                        break;
                    case "/meta/unsubscribe":
                        bo(a);
                        break;
                    default:
                        bs(a);
                        break
                }
            }
            function bt(a, b) {
                br({successful: false,failure: true,channel: b.channel,request: b,xhr: a,advice: {reconnect: "none",interval: 0}})
            }
            function bs(a) {
                if (a.successful === undefined) {
                    if (a.data) {
                        L(a.channel, a)
                    } else {
                        e._debug("Unknown message", a)
                    }
                } else {
                    if (a.successful) {
                        bq(a);
                        L("/meta/publish", a)
                    } else {
                        br(a)
                    }
                }
            }
            function br(a) {
                bq(a);
                L("/meta/publish", a);
                L("/meta/unsuccessful", a)
            }
            function bq(a) {
                var b = v[a.id];
                if (A(b)) {
                    delete v[a.id];
                    b.call(e, a)
                }
            }
            function bp(a, b) {
                bn({successful: false,failure: true,channel: "/meta/unsubscribe",request: b,xhr: a,advice: {reconnect: "none",interval: 0}})
            }
            function bo(a) {
                if (a.successful) {
                    L("/meta/unsubscribe", a)
                } else {
                    bn(a)
                }
            }
            function bn(a) {
                L("/meta/unsubscribe", a);
                L("/meta/unsuccessful", a)
            }
            function bm(a, b) {
                bk({successful: false,failure: true,channel: "/meta/subscribe",request: b,xhr: a,advice: {reconnect: "none",interval: 0}})
            }
            function bl(a) {
                if (a.successful) {
                    L("/meta/subscribe", a)
                } else {
                    bk(a)
                }
            }
            function bk(a) {
                L("/meta/subscribe", a);
                L("/meta/unsuccessful", a)
            }
            function bj(a, b) {
                bh({successful: false,failure: true,channel: "/meta/disconnect",request: b,xhr: a,advice: {reconnect: "none",interval: 0}})
            }
            function bi(a) {
                if (a.successful) {
                    $(false);
                    L("/meta/disconnect", a)
                } else {
                    bh(a)
                }
            }
            function bh(a) {
                $(true);
                L("/meta/disconnect", a);
                L("/meta/unsuccessful", a)
            }
            function bg(a, b) {
                x = false;
                be({successful: false,failure: true,channel: "/meta/connect",request: b,xhr: a,advice: {reconnect: "retry",interval: q}})
            }
            function bf(a) {
                x = a.successful;
                if (x) {
                    L("/meta/connect", a);
                    var b = F() ? "none" : t.reconnect;
                    switch (b) {
                        case "retry":
                            S();
                            Y();
                            break;
                        case "none":
                            $(false);
                            break;
                        default:
                            throw "Unrecognized advice action " + b
                    }
                } else {
                    be(a)
                }
            }
            function be(a) {
                L("/meta/connect", a);
                L("/meta/unsuccessful", a);
                var b = F() ? "none" : t.reconnect;
                switch (b) {
                    case "retry":
                        Y();
                        T();
                        break;
                    case "handshake":
                        h.reset();
                        S();
                        ba();
                        break;
                    case "none":
                        $(false);
                        break;
                    default:
                        throw "Unrecognized advice action" + b
                }
            }
            function bd(a, b) {
                bb({successful: false,failure: true,channel: "/meta/handshake",request: b,xhr: a,advice: {reconnect: "retry",interval: q}})
            }
            function bc(a) {
                if (a.successful) {
                    l = a.clientId;
                    var b = h.negotiateTransport(a.supportedConnectionTypes, a.version, g, y.url);
                    if (b === null) {
                        throw "Could not negotiate transport with server; client " + h.findTransportTypes(a.version, g, y.url) + ", server " + a.supportedConnectionTypes
                    } else if (i !== b) {
                        e._debug("Transport", i, "->", b);
                        i = b
                    }
                    o = false;
                    V();
                    a.reestablish = w;
                    w = true;
                    L("/meta/handshake", a);
                    var c = F() ? "none" : t.reconnect;
                    switch (c) {
                        case "retry":
                            S();
                            Y();
                            break;
                        case "none":
                            $(false);
                            break;
                        default:
                            throw "Unrecognized advice action " + c
                    }
                } else {
                    bb(a)
                }
            }
            function bb(a) {
                L("/meta/handshake", a);
                L("/meta/unsuccessful", a);
                var b = !F() && t.reconnect !== "none";
                if (b) {
                    T();
                    ba()
                } else {
                    $(false)
                }
            }
            function ba() {
                E("handshaking");
                o = true;
                N(function() {
                    _(u)
                })
            }
            function _(a) {
                l = null;
                D();
                if (F()) {
                    h.reset();
                    Z(y.advice)
                } else {
                    Z(e._mixin(false, t, {reconnect: "retry"}))
                }
                m = 0;
                o = true;
                u = a;
                var b = "1.0";
                var c = h.findTransportTypes(b, g, y.url);
                var d = {version: b,minimumVersion: "0.9",channel: "/meta/handshake",supportedConnectionTypes: c,advice: {timeout: t.timeout,interval: t.interval}};
                var f = e._mixin(false, {}, u, d);
                i = h.negotiateTransport(c, b, g, y.url);
                e._debug("Initial transport is", i.getType());
                E("handshaking");
                e._debug("Handshake sent", f);
                Q(false, [f], false, "handshake")
            }
            function $(a) {
                M();
                if (a) {
                    i.abort()
                }
                l = null;
                E("disconnected");
                m = 0;
                S();
                if (n.length > 0) {
                    P.call(e, undefined, n, "error", "Disconnected");
                    n = []
                }
            }
            function Z(a) {
                if (a) {
                    t = e._mixin(false, {}, y.advice, a);
                    e._debug("New advice", t)
                }
            }
            function Y() {
                E("connecting");
                N(function() {
                    X()
                })
            }
            function X() {
                if (!F()) {
                    var a = {channel: "/meta/connect",connectionType: i.getType()};
                    if (!x) {
                        a.advice = {timeout: 0}
                    }
                    E("connecting");
                    e._debug("Connect sent", a);
                    Q(false, [a], true, "connect");
                    E("connected")
                }
            }
            function W() {
                --m;
                if (m < 0) {
                    throw "Calls to startBatch() and endBatch() are not paired"
                }
                if (m === 0 && !F() && !o) {
                    V()
                }
            }
            function V() {
                var a = n;
                n = [];
                if (a.length > 0) {
                    Q(false, a, false)
                }
            }
            function U() {
                ++m
            }
            function T() {
                if (q < y.maxBackoff) {
                    q += y.backoffIncrement
                }
            }
            function S() {
                q = 0
            }
            function R(a) {
                if (m > 0 || o === true) {
                    n.push(a)
                } else {
                    Q(false, [a], false)
                }
            }
            function Q(a, b, c, d) {
                for (var f = 0; f < b.length; ++f) {
                    var g = b[f];
                    g.id = "" + G();
                    if (l) {
                        g.clientId = l
                    }
                    var h;
                    if (A(g._callback)) {
                        h = g._callback;
                        delete g._callback
                    }
                    g = J(g);
                    if (g !== undefined && g !== null) {
                        b[f] = g;
                        if (h)
                            v[g.id] = h
                    } else {
                        b.splice(f--, 1)
                    }
                }
                if (b.length === 0) {
                    return
                }
                var j = y.url;
                if (y.appendMessageTypeToURL) {
                    if (!j.match(/\/$/)) {
                        j = j + "/"
                    }
                    if (d) {
                        j = j + d
                    }
                }
                var k = {url: j,sync: a,messages: b,onSuccess: function(a) {
                        try {
                            O.call(e, a)
                        } catch (b) {
                            e._debug("Exception during handling of messages", b)
                        }
                    },onFailure: function(a, b, c, d) {
                        try {
                            P.call(e, a, b, c, d)
                        } catch (f) {
                            e._debug("Exception during handling of failure", f)
                        }
                    }};
                e._debug("Send", k);
                i.send(k, c)
            }
            function N(a) {
                M();
                var c = t.interval + q;
                e._debug("Function scheduled in", c, "ms, interval =", t.interval, "backoff =", q, a);
                r = b(a, c)
            }
            function M() {
                if (r !== null) {
                    window.clearTimeout(r)
                }
                r = null
            }
            function L(a, b) {
                K(a, b);
                var c = a.split("/");
                var d = c.length - 1;
                for (var e = d; e > 0; --e) {
                    var f = c.slice(0, e).join("/") + "/*";
                    if (e === d) {
                        K(f, b)
                    }
                    f += "*";
                    K(f, b)
                }
            }
            function K(a, b) {
                var c = p[a];
                if (c && c.length > 0) {
                    for (var d = 0; d < c.length; ++d) {
                        var f = c[d];
                        if (f) {
                            try {
                                f.callback.call(f.scope, b)
                            } catch (g) {
                                e._debug("Exception during notification", f, b, g);
                                var h = e.onListenerException;
                                if (A(h)) {
                                    e._debug("Invoking listener exception callback", f, g);
                                    try {
                                        h.call(e, g, f.handle, f.listener, b)
                                    } catch (i) {
                                        e._info("Exception during execution of listener callback", f, i)
                                    }
                                }
                            }
                        }
                    }
                }
            }
            function J(a) {
                for (var b = 0; b < s.length; ++b) {
                    if (a === undefined || a === null) {
                        break
                    }
                    var c = s[b];
                    var d = c.extension.outgoing;
                    if (A(d)) {
                        var e = H(c.extension, d, c.name, a, true);
                        a = e === undefined ? a : e
                    }
                }
                return a
            }
            function I(a) {
                for (var b = 0; b < s.length; ++b) {
                    if (a === undefined || a === null) {
                        break
                    }
                    var c = y.reverseIncomingExtensions ? s.length - 1 - b : b;
                    var d = s[c];
                    var e = d.extension.incoming;
                    if (A(e)) {
                        var f = H(d.extension, e, d.name, a, false);
                        a = f === undefined ? a : f
                    }
                }
                return a
            }
            function H(a, b, c, d, f) {
                try {
                    return b.call(a, d)
                } catch (g) {
                    e._debug("Exception during execution of extension", c, g);
                    var h = e.onExtensionException;
                    if (A(h)) {
                        e._debug("Invoking extension exception callback", c, g);
                        try {
                            h.call(e, g, c, f, d)
                        } catch (i) {
                            e._info("Exception during execution of exception callback in extension", c, i)
                        }
                    }
                    return d
                }
            }
            function G() {
                return ++k
            }
            function F() {
                return j === "disconnecting" || j === "disconnected"
            }
            function E(a) {
                if (j !== a) {
                    e._debug("Status", j, "->", a);
                    j = a
                }
            }
            function D() {
                for (var a in p) {
                    var b = p[a];
                    for (var c = 0; c < b.length; ++c) {
                        var d = b[c];
                        if (d && !d.listener) {
                            delete b[c];
                            e._debug("Removed subscription", d, "for channel", a)
                        }
                    }
                }
            }
            function C(a) {
                e._debug("Configuring cometd object with", a);
                if (z(a)) {
                    a = {url: a}
                }
                if (!a) {
                    a = {}
                }
                y = e._mixin(false, y, a);
                if (!y.url) {
                    throw "Missing required configuration parameter 'url' specifying the Bayeux server URL"
                }
                var b = /(^https?:\/\/)?(((\[[^\]]+\])|([^:\/\?#]+))(:(\d+))?)?([^\?#]*)(.*)?/.exec(y.url);
                var c = b[2];
                var d = b[8];
                var f = b[9];
                g = e._isCrossDomain(c);
                if (y.appendMessageTypeToURL) {
                    if (f !== undefined && f.length > 0) {
                        e._info("Appending message type to URI " + d + f + " is not supported, disabling 'appendMessageTypeToURL' configuration");
                        y.appendMessageTypeToURL = false
                    } else {
                        var h = d.split("/");
                        var i = h.length - 1;
                        if (d.match(/\/$/)) {
                            i -= 1
                        }
                        if (h[i].indexOf(".") >= 0) {
                            e._info("Appending message type to URI " + d + " is not supported, disabling 'appendMessageTypeToURL' configuration");
                            y.appendMessageTypeToURL = false
                        }
                    }
                }
            }
            function B(a, b) {
                if (window.console) {
                    var c = window.console[a];
                    if (A(c)) {
                        c.apply(window.console, b)
                    }
                }
            }
            function A(a) {
                if (a === undefined || a === null) {
                    return false
                }
                return typeof a === "function"
            }
            function z(a) {
                return c.isString(a)
            }
            var e = this;
            var f = d || "default";
            var g = false;
            var h = new a;
            var i;
            var j = "disconnected";
            var k = 0;
            var l = null;
            var m = 0;
            var n = [];
            var o = false;
            var p = {};
            var q = 0;
            var r = null;
            var s = [];
            var t = {};
            var u;
            var v = {};
            var w = false;
            var x = false;
            var y = {connectTimeout: 8e3,maxConnections: 2,backoffIncrement: 1e3,maxBackoff: 18e4,logLevel: "info",reverseIncomingExtensions: true,maxNetworkDelay: 8e3,requestHeaders: {},appendMessageTypeToURL: true,autoBatch: false,advice: {timeout: 6e4,interval: 0,reconnect: "retry"}};
            this._mixin = function(a, b, c) {
                var d = b || {};
                for (var e = 2; e < arguments.length; ++e) {
                    var f = arguments[e];
                    if (f === undefined || f === null) {
                        continue
                    }
                    for (var g in f) {
                        var h = f[g];
                        var i = d[g];
                        if (h === b) {
                            continue
                        }
                        if (h === undefined) {
                            continue
                        }
                        if (a && typeof h === "object" && h !== null) {
                            if (h instanceof Array) {
                                d[g] = this._mixin(a, i instanceof Array ? i : [], h)
                            } else {
                                var j = typeof i === "object" && !(i instanceof Array) ? i : {};
                                d[g] = this._mixin(a, j, h)
                            }
                        } else {
                            d[g] = h
                        }
                    }
                }
                return d
            };
            this._warn = function() {
                B("warn", arguments)
            };
            this._info = function() {
                if (y.logLevel !== "warn") {
                    B("info", arguments)
                }
            };
            this._debug = function() {
                if (y.logLevel === "debug") {
                    B("debug", arguments)
                }
            };
            this._isCrossDomain = function(a) {
                return a && a !== window.location.host
            };
            var O;
            var P;
            this.send = R;
            this.receive = bu;
            O = function(a) {
                e._debug("Received", a);
                for (var b = 0; b < a.length; ++b) {
                    var c = a[b];
                    bu(c)
                }
            };
            P = function(a, b, c, d) {
                e._debug("handleFailure", a, b, c, d);
                for (var f = 0; f < b.length; ++f) {
                    var g = b[f];
                    var h = g.channel;
                    switch (h) {
                        case "/meta/handshake":
                            bd(a, g);
                            break;
                        case "/meta/connect":
                            bg(a, g);
                            break;
                        case "/meta/disconnect":
                            bj(a, g);
                            break;
                        case "/meta/subscribe":
                            bm(a, g);
                            break;
                        case "/meta/unsubscribe":
                            bp(a, g);
                            break;
                        default:
                            bt(a, g);
                            break
                    }
                }
            };
            this.registerTransport = function(a, b, c) {
                var d = h.add(a, b, c);
                if (d) {
                    this._debug("Registered transport", a);
                    if (A(b.registered)) {
                        b.registered(a, this)
                    }
                }
                return d
            };
            this.getTransportTypes = function() {
                return h.getTransportTypes()
            };
            this.unregisterTransport = function(a) {
                var b = h.remove(a);
                if (b !== null) {
                    this._debug("Unregistered transport", a);
                    if (A(b.unregistered)) {
                        b.unregistered()
                    }
                }
                return b
            };
            this.unregisterTransports = function() {
                h.clear()
            };
            this.findTransport = function(a) {
                return h.find(a)
            };
            this.configure = function(a) {
                C.call(this, a)
            };
            this.init = function(a, b) {
                this.configure(a);
                this.handshake(b)
            };
            this.handshake = function(a) {
                E("disconnected");
                w = false;
                _(a)
            };
            this.disconnect = function(a, b) {
                L("/meta/reconnect", {});
                if (F()) {
                    return
                }
                if (b === undefined) {
                    if (typeof a !== "boolean") {
                        b = a;
                        a = false
                    }
                }
                var c = {channel: "/meta/disconnect"};
                var d = this._mixin(false, {}, b, c);
                E("disconnecting");
                Q(a === true, [d], false, "disconnect")
            };
            this.startBatch = function() {
                U()
            };
            this.endBatch = function() {
                W()
            };
            this.batch = function(a, b) {
                var c = bw(a, b);
                this.startBatch();
                try {
                    c.method.call(c.scope);
                    this.endBatch()
                } catch (d) {
                    this._debug("Exception during execution of batch", d);
                    this.endBatch();
                    throw d
                }
            };
            this.addListener = function(a, b, c) {
                if (arguments.length < 2) {
                    throw "Illegal arguments number: required 2, got " + arguments.length
                }
                if (!z(a)) {
                    throw "Illegal argument type: channel must be a string"
                }
                return bx(a, b, c, true)
            };
            this.removeListener = function(a) {
                if (!c.isArray(a)) {
                    throw "Invalid argument: expected subscription, not " + a
                }
                by(a)
            };
            this.clearListeners = function() {
                p = {}
            };
            this.subscribe = function(a, b, c, d) {
                if (arguments.length < 2) {
                    throw "Illegal arguments number: required 2, got " + arguments.length
                }
                if (!z(a)) {
                    throw "Illegal argument type: channel must be a string"
                }
                if (F()) {
                    throw "Illegal state: already disconnected"
                }
                if (A(b)) {
                    d = c;
                    c = b;
                    b = undefined
                }
                var e = !bv(a);
                var f = bx(a, b, c, false);
                if (e) {
                    var g = {channel: "/meta/subscribe",subscription: a};
                    var h = this._mixin(false, {}, d, g);
                    R(h)
                }
                return f
            };
            this.unsubscribe = function(a, b) {
                if (arguments.length < 1) {
                    throw "Illegal arguments number: required 1, got " + arguments.length
                }
                if (F()) {
                    throw "Illegal state: already disconnected"
                }
                this.removeListener(a);
                var c = a[0];
                if (!bv(c)) {
                    var d = {channel: "/meta/unsubscribe",subscription: c};
                    var e = this._mixin(false, {}, b, d);
                    R(e)
                }
            };
            this.clearSubscriptions = function() {
                D()
            };
            this.publish = function(a, b, c, d) {
                if (arguments.length < 1) {
                    throw "Illegal arguments number: required 1, got " + arguments.length
                }
                if (!z(a)) {
                    throw "Illegal argument type: channel must be a string"
                }
                if (F()) {
                    throw "Illegal state: already disconnected"
                }
                if (A(b)) {
                    d = b;
                    b = c = {}
                } else if (A(c)) {
                    d = c;
                    c = {}
                }
                var e = {channel: a,data: b,_callback: d};
                var f = this._mixin(false, {}, c, e);
                R(f);
                return f.id
            };
            this.getStatus = function() {
                return j
            };
            this.isDisconnected = F;
            this.setBackoffIncrement = function(a) {
                y.backoffIncrement = a
            };
            this.getBackoffIncrement = function() {
                return y.backoffIncrement
            };
            this.getBackoffPeriod = function() {
                return q
            };
            this.setLogLevel = function(a) {
                y.logLevel = a
            };
            this.registerExtension = function(a, b) {
                if (arguments.length < 2) {
                    throw "Illegal arguments number: required 2, got " + arguments.length
                }
                if (!z(a)) {
                    throw "Illegal argument type: extension name must be a string"
                }
                var c = false;
                for (var d = 0; d < s.length; ++d) {
                    var e = s[d];
                    if (e.name === a) {
                        c = true;
                        break
                    }
                }
                if (!c) {
                    s.push({name: a,extension: b});
                    this._debug("Registered extension", a);
                    if (A(b.registered)) {
                        b.registered(a, this)
                    }
                    return true
                } else {
                    this._info("Could not register extension with name", a, "since another extension with the same name already exists");
                    return false
                }
            };
            this.unregisterExtension = function(a) {
                if (!z(a)) {
                    throw "Illegal argument type: extension name must be a string"
                }
                var b = false;
                for (var c = 0; c < s.length; ++c) {
                    var d = s[c];
                    if (d.name === a) {
                        s.splice(c, 1);
                        b = true;
                        this._debug("Unregistered extension", a);
                        var e = d.extension;
                        if (A(e.unregistered)) {
                            e.unregistered()
                        }
                        break
                    }
                }
                return b
            };
            this.getExtension = function(a) {
                for (var b = 0; b < s.length; ++b) {
                    var c = s[b];
                    if (c.name === a) {
                        return c.extension
                    }
                }
                return null
            };
            this.getName = function() {
                return f
            };
            this.getClientId = function() {
                return l
            };
            this.getURL = function() {
                return y.url
            };
            this.getTransport = function() {
                return i
            };
            this.getConfiguration = function() {
                return this._mixin(true, {}, y)
            };
            this.getAdvice = function() {
                return this._mixin(true, {}, t)
            }
        }
    });
    f("cometd.ext.AckExtension", function() {
        return function() {
            function e(b, c) {
                a._debug(b, c)
            }
            var a;
            var b = false;
            var c = -1;
            var d = {};
            d.registered = function(b, c) {
                a = c;
                e("AckExtension: executing registration callback")
            };
            d.unregistered = function() {
                e("AckExtension: executing unregistration callback");
                a = null
            };
            d.incoming = function(a) {
                var d = a.channel;
                if (d == "/meta/handshake") {
                    b = a.ext && a.ext.ack;
                    e("AckExtension: server supports acks", b)
                } else if (b && d == "/meta/connect" && a.successful) {
                    var f = a.ext;
                    if (f && typeof f.ack === "number") {
                        c = f.ack;
                        e("AckExtension: server sent ack id", c)
                    }
                }
                return a
            };
            d.outgoing = function(d) {
                var f = d.channel;
                if (f == "/meta/handshake") {
                    if (!d.ext) {
                        d.ext = {}
                    }
                    d.ext.ack = a && a.ackEnabled !== false;
                    c = -1
                } else if (b && f == "/meta/connect") {
                    if (!d.ext) {
                        d.ext = {}
                    }
                    d.ext.ack = c;
                    e("AckExtension: client sending ack id", c)
                }
                return d
            };
            return d
        }
    });
    f("cometd.ext.ReloadExtension", function() {
        var a = g("util.cookie");
        return function(b) {
            function m(a) {
                if (a) {
                    if (typeof a.cookieMaxAge === "number") {
                        h = a.cookieMaxAge
                    }
                    if (typeof a.cookieName === "string") {
                        f = a.cookieName
                    }
                    if (typeof a.cookiePath === "string") {
                        g = a.cookiePath
                    }
                }
            }
            function l(a) {
                return e.url == a.url
            }
            function k(b) {
                if (e && e.handshakeResponse !== null) {
                    m(b);
                    e.cookiePath = g;
                    var c = z.stringify(e);
                    d("Reload extension saving cookie value", c);
                    a.set(f, c, {"max-age": h,path: g,expires: new Date((new Date).getTime() + h * 1e3)})
                }
            }
            var c;
            var d;
            var e = null;
            var f = "org.cometd.reload";
            var g = "/";
            var h = 5;
            var i = false;
            var j = {};
            j.configure = m;
            j.registered = function(a, b) {
                c = b;
                c.reload = k;
                d = c._debug
            };
            j.unregistered = function() {
                delete c.reload;
                c = null
            };
            j.outgoing = function(b) {
                var g = b.channel;
                if (g == "/meta/handshake") {
                    e = {};
                    e.url = c.getURL();
                    var h = a.get(f);
                    d("Reload extension found cookie value", h);
                    if (h) {
                        try {
                            var j = z.parse(h);
                            a.set(f, "", {"max-age": -1,path: j.cookiePath,expires: -1});
                            if (j.handshakeResponse && l(j)) {
                                d("Reload extension restoring state", j);
                                setTimeout(function() {
                                    d("Reload extension replaying handshake response", j.handshakeResponse);
                                    e.handshakeResponse = j.handshakeResponse;
                                    e.transportType = j.transportType;
                                    e.reloading = true;
                                    var a = c._mixin(true, {}, e.handshakeResponse, {ext: {reload: true}});
                                    a.supportedConnectionTypes = [e.transportType];
                                    c.receive(a);
                                    d("Reload extension replayed handshake response", a)
                                }, 0);
                                if (!i) {
                                    i = true;
                                    c.startBatch()
                                }
                                return null
                            } else {
                                d("Reload extension could not restore state", j)
                            }
                        } catch (k) {
                            d("Reload extension error while trying to restore cookie", k)
                        }
                    }
                } else if (g == "/meta/connect") {
                    if (!e.transportType) {
                        e.transportType = b.connectionType;
                        d("Reload extension tracked transport type", e.transportType)
                    }
                }
                return b
            };
            j.incoming = function(a) {
                if (a.successful) {
                    switch (a.channel) {
                        case "/meta/handshake":
                            if (!e.handshakeResponse) {
                                e.handshakeResponse = a;
                                d("Reload extension tracked handshake response", a)
                            }
                            break;
                        case "/meta/disconnect":
                            e = null;
                            break;
                        case "/meta/connect":
                            if (i) {
                                c.endBatch();
                                i = false
                            }
                            break;
                        default:
                            break
                    }
                }
                return a
            };
            m(b);
            return j
        }
    });
    f("cometd.ext.TimeStampExtension", function() {
        return function() {
            var a = {};
            a.outgoing = function(a) {
                a.timestamp = (new Date).toUTCString();
                return a
            };
            return a
        }
    });
    f("cometd.ext.TimeSyncExtension", function() {
        var a = g("util.delay");
        return function(b) {
            function j(a, b) {
                c._debug(a, b)
            }
            var c;
            var d = b && b.maxSamples || 10;
            var e = [];
            var f = [];
            var g = 0;
            var h = 0;
            var i = {};
            i.registered = function(a, b) {
                c = b;
                j("TimeSyncExtension: executing registration callback")
            };
            i.unregistered = function() {
                j("TimeSyncExtension: executing unregistration callback");
                c = null;
                e = [];
                f = []
            };
            i.incoming = function(a) {
                var b = a.channel;
                if (b && b.indexOf("/meta/") === 0) {
                    if (a.ext && a.ext.timesync) {
                        var c = a.ext.timesync;
                        j("TimeSyncExtension: server sent timesync", c);
                        var i = (new Date).getTime();
                        var k = (i - c.tc - c.p) / 2;
                        var l = c.ts - c.tc - k;
                        e.push(k);
                        f.push(l);
                        if (f.length > d) {
                            f.shift();
                            e.shift()
                        }
                        var m = f.length;
                        var n = 0;
                        var o = 0;
                        for (var p = 0; p < m; ++p) {
                            n += e[p];
                            o += f[p]
                        }
                        g = parseInt((n / m).toFixed());
                        h = parseInt((o / m).toFixed());
                        j("TimeSyncExtension: network lag", g, "ms, time offset with server", h, "ms", g, h)
                    }
                }
                return a
            };
            i.outgoing = function(a) {
                var b = a.channel;
                if (b && b.indexOf("/meta/") === 0) {
                    if (!a.ext) {
                        a.ext = {}
                    }
                    a.ext.timesync = {tc: (new Date).getTime(),l: g,o: h};
                    j("TimeSyncExtension: client sending timesync", z.stringify(a.ext.timesync))
                }
                return a
            };
            i.getTimeOffset = function() {
                return h
            };
            i.getTimeOffsetSamples = function() {
                return f
            };
            i.getNetworkLag = function() {
                return g
            };
            i.getServerTime = function() {
                return (new Date).getTime() + h
            };
            i.getServerDate = function() {
                return new Date(this.getServerTime())
            };
            i.setTimeout = function(b, c) {
                var d = c instanceof Date ? c.getTime() : 0 + c;
                var e = d - h;
                var f = e - (new Date).getTime();
                if (f <= 0) {
                    f = 1
                }
                return a(b, f)
            };
            return i
        }
    });
    f("transport.CallbackPolling", function() {
        function l() {
            function t(a, b) {
                var c = e++, d = g + c, h, i, j, n, o;
                if (!b) {
                    f = f || q();
                    h = f.contentWindow.document
                }
                if (h) {
                    n = k + l
                } else {
                    h = document;
                    n = l
                }
                o = p(a, n + d);
                j = h.createElement("script");
                j.type = "text/javascript";
                j.src = o;
                j.charset = "utf-8";
                m[d] = s(a.onSuccess, j, d, i);
                h.getElementsByTagName("head")[0].appendChild(j)
            }
            function s(a, b, c, d) {
                return function(e) {
                    var f = z.parse(z.stringify(e));
                    a(f);
                    r(b);
                    delete m[c];
                    if (d) {
                        clearTimeout(d)
                    }
                }
            }
            function q() {
                var a = "cometd_imsdk_" + h, b = c.getElementById(a);
                if (!b && d && d.connection) {
                    b = d.connection.getElementById(a)
                }
                return b
            }
            function p(a, b) {
                var c = a.url;
                if (c.indexOf("jsonp=?") == -1) {
                    c += "?jsonp=" + b
                } else {
                    c = c.replace("jsonp=?", "jsonp=" + b)
                }
                if (a.body) {
                    c += "&message=" + encodeURIComponent(a.body)
                }
                c += "&" + new Date;
                return c
            }
            function o() {
                var a = "cometd_imsdk_" + ++h;
                if (i && !j) {
                    d.connection = new ActiveXObject("htmlfile");
                    d.connection.open();
                    d.connection.write("<html>");
                    d.connection.write("</html>");
                    d.connection.close();
                    d.iframediv = d.connection.createElement("div");
                    d.connection.body.appendChild(d.iframediv);
                    d.connection.parentWindow.lc = d;
                    d.connection.parentWindow.webimCB = m;
                    d.iframediv.innerHTML = '<iframe id="' + a + '" name="' + a + '"></iframe>'
                } else if (navigator.appVersion.indexOf("KHTML") != -1) {
                    d.connection = c.createElement("iframe");
                    d.connection.setAttribute("id", a);
                    d.connection.style.cssText = "position:absolute;left:-100px;top:-100px;height:1px;width:1px;visibility:hidden;";
                    n.appendChild(d.connection)
                } else {
                    d.connection = c.createElement("iframe");
                    d.connection.setAttribute("id", a);
                    d.connection.style.cssText = "position:absolute;left:-100px;top:-100px;height:1px;width:1px;visibility:hidden;display:none;";
                    d.iframediv = c.createElement("iframe");
                    d.connection.appendChild(d.iframediv);
                    n.appendChild(d.connection)
                }
            }
            var d = {}, e = 0, f, g = "_callback", k = "parent.", l = "webimCB.", m = b.webimCB = {}, n = c.getElementById(a("im_root")) || c.body;
            var r = function(a) {
                if (a && a.parentNode) {
                    a.parentNode.removeChild(a)
                }
            };
            o();
            return {request: t}
        }
        var a = g("base.Config"), d = g("util.browser"), e = g("cometd.Transport"), f = g("cometd.RequestTransport");
        var h = 0, i = d.IE, j = d.IE9, k = d.IE6 || d.IE7;
        return function() {
            var a = new f, b = l(), c = e.derive(a), d = 2e3;
            c.accept = function(a, b, c) {
                return true
            };
            c.jsonpSend = function(a, c) {
                b.request(a, c)
            };
            c.transportSend = function(a, b) {
                var c = this;
                var e = 0;
                var f = a.messages.length;
                var g = [];
                var h = function(a, d, e) {
                    c.setTimeout(function() {
                        c.transportFailure(a, b, "error", e)
                    }, 0)
                };
                while (f > 0) {
                    var i = z.stringify(a.messages.slice(e, e + f));
                    var j = a.url.length + encodeURI(i).length;
                    if (j > d) {
                        if (f === 1) {
                            var k = "Bayeux message too big (" + j + " bytes, max is " + d + ") " + "for transport " + c.getType();
                            h(a, reques, k);
                            return
                        }
                        --f;
                        continue
                    }
                    g.push(f);
                    e += f;
                    f = a.messages.length - e
                }
                var l = a;
                if (g.length > 1) {
                    var m = 0;
                    var n = g[0];
                    c._debug("Transport", c.getType(), "split", a.messages.length, "messages into", g.join(" + "));
                    l = c._mixin(false, {}, a);
                    l.messages = a.messages.slice(m, n);
                    l.onSuccess = a.onSuccess;
                    l.onFailure = a.onFailure;
                    for (var o = 1; o < g.length; ++o) {
                        var p = c._mixin(false, {}, a);
                        m = n;
                        n += g[o];
                        p.messages = a.messages.slice(m, n);
                        p.onSuccess = a.onSuccess;
                        p.onFailure = a.onFailure;
                        c.send(p, b.metaConnect)
                    }
                }
                c._debug("Transport", c.getType(), "sending request", b.id, "envelope", l);
                try {
                    var q = true;
                    c.jsonpSend({transport: c,url: l.url,sync: l.sync,headers: c.getConfiguration().requestHeaders,body: z.stringify(l.messages),onSuccess: function(a) {
                            var d = false;
                            try {
                                var e = c.convertToMessages(a);
                                if (e.length === 0) {
                                    c.transportFailure(l, b, "no response")
                                } else {
                                    d = true;
                                    c.transportSuccess(l, b, e)
                                }
                            } catch (f) {
                                c._debug(f);
                                if (!d) {
                                    c.transportFailure(l, b, "bad response", f)
                                }
                            }
                        },onError: function(a, d) {
                            if (q) {
                                c.setTimeout(function() {
                                    c.transportFailure(l, b, a, d)
                                }, 0)
                            } else {
                                c.transportFailure(l, b, a, d)
                            }
                        }});
                    q = false
                } catch (r) {
                    c.setTimeout(function() {
                        c.transportFailure(l, b, "error", r)
                    }, 0)
                }
            };
            return c
        }
    });
    f("transport.WebSocket", function() {
        var a = g("cometd.Transport"), b = g("util.webSocket"), c = g("util.array");
        return function(d) {
            function r(a, b) {
                try {
                    if (l === null) {
                        p.call(this)
                    } else if (m) {
                        q.call(this, a, b)
                    }
                } catch (c) {
                    var d = l;
                    this.setTimeout(function() {
                        a.onFailure(d, a.messages, "error", c)
                    }, 0)
                }
            }
            function q(a, b) {
                var c = z.stringify(a.messages);
                l.send(c);
                this._debug("Transport", this.getType(), "sent", a, "metaConnect =", b);
                var d = this.getConfiguration().maxNetworkDelay;
                var e = d;
                if (b) {
                    e += this.getAdvice().timeout;
                    n = true
                }
                var f = [];
                var g = this.setTimeout(function() {
                    if (m) {
                        m.close(1e3, "Timeout")
                    }
                }, e);
                for (var h = 0; h < a.messages.length; ++h) {
                    var i = a.messages[h];
                    if (i.id) {
                        f.push(i.id);
                        var j = this;
                        var m = l;
                        k[i.id] = g
                    }
                }
                this._debug("Transport", this.getType(), "waiting at most", e, "ms for messages", f, "maxNetworkDelay", d, ", timeouts:", k)
            }
            function p() {
                var a = g.getURL().replace(/^http/, "ws");
                this._debug("Transport", this.getType(), "connecting to URL", a);
                var c = this;
                var e = null;
                var f = g.getConfiguration().connectTimeout;
                if (f > 0) {
                    e = this.setTimeout(function() {
                        e = null;
                        if (!m) {
                            c._debug("Transport", c.getType(), "timed out while connecting to URL", a, ":", f, "ms");
                            c.onClose(1002, "Connect Timeout")
                        }
                    }, f)
                }
                var h = null;
                var i = d.getConfig("webSocket_connect_timeout");
                var j = new b(a);
                var k = function() {
                    c._debug("WebSocket opened", j);
                    if (e) {
                        c.clearTimeout(e);
                        e = null
                    }
                    if (i > 0) {
                        h = c.setTimeout(function() {
                            h = null;
                            c._debug("Transport", c.getType(), "timed out while getting message", "Message Timeout", ":", i, "ms");
                            c.onClose(1002, "Message Timeout")
                        }, i)
                    }
                    if (j !== l) {
                        c._debug("Ignoring open event, WebSocket", l);
                        return
                    }
                    c.onOpen()
                };
                var n = function(a) {
                    var b = a ? a.code : 1e3;
                    var d = a ? a.reason : undefined;
                    c._debug("WebSocket closed", b, "/", d, j);
                    if (e) {
                        c.clearTimeout(e);
                        e = null
                    }
                    if (j !== l) {
                        c._debug("Ignoring close event, WebSocket", l);
                        return
                    }
                    c.onClose(b, d)
                };
                var o = function(a) {
                    c._debug("WebSocket message", a, j);
                    if (h) {
                        clearTimeout(h);
                        h = null
                    }
                    if (j !== l) {
                        c._debug("Ignoring message event, WebSocket", l);
                        return
                    }
                    c.onMessage(a)
                };
                j.onopen = k;
                j.onclose = n;
                j.onerror = function() {
                    n({code: 1002})
                };
                j.onmessage = o;
                l = j;
                this._debug("Transport", this.getType(), "configured callbacks on", j)
            }
            var e = new a;
            var f = a.derive(e);
            var g;
            var h = true;
            var i = false;
            var j = {};
            var k = {};
            var l = null;
            var m = false;
            var n = false;
            var o;
            f.onOpen = function() {
                this._debug("Transport", this.getType(), "opened", l);
                m = true;
                i = true;
                this._debug("Sending pending messages", j);
                for (var a in j) {
                    var b = j[a];
                    var c = b[0];
                    var d = b[1];
                    o = c.onSuccess;
                    q.call(this, c, d)
                }
            };
            f.onMessage = function(a) {
                this._debug("Transport", this.getType(), "received websocket message", a, l);
                var b = false;
                var d = this.convertToMessages(a.data);
                var e = [];
                for (var f = 0; f < d.length; ++f) {
                    var g = d[f];
                    if (/^\/meta\//.test(g.channel) || g.data === undefined) {
                        if (g.id) {
                            e.push(g.id);
                            var h = k[g.id];
                            if (h) {
                                this.clearTimeout(h);
                                delete k[g.id];
                                this._debug("Transport", this.getType(), "removed timeout for message", g.id, ", timeouts", k)
                            }
                        }
                    }
                    if ("/meta/connect" === g.channel) {
                        n = false
                    }
                    if ("/meta/disconnect" === g.channel && !n) {
                        b = true
                    }
                }
                var i = false;
                for (var m = 0; m < e.length; ++m) {
                    var p = e[m];
                    for (var q in j) {
                        var r = q.split(",");
                        var s = c.indexOf(p, r);
                        if (s >= 0) {
                            i = true;
                            r.splice(s, 1);
                            var t = j[q][0];
                            var u = j[q][1];
                            delete j[q];
                            if (r.length > 0) {
                                j[r.join(",")] = [t, u]
                            }
                            break
                        }
                    }
                }
                if (i) {
                    this._debug("Transport", this.getType(), "removed envelope, envelopes", j)
                }
                o.call(this, d);
                if (b) {
                    l.close(1e3, "Disconnect")
                }
            };
            f.onClose = function(a, b) {
                this._debug("Transport", this.getType(), "closed", a, b, l);
                h = i;
                for (var c in k) {
                    this.clearTimeout(k[c])
                }
                k = {};
                for (var d in j) {
                    var e = j[d][0];
                    var f = j[d][1];
                    if (f) {
                        n = false
                    }
                    e.onFailure(l, e.messages, "closed " + a + "/" + b)
                }
                j = {};
                if (l !== null && m) {
                    l.close(1e3, "Close")
                }
                m = false;
                l = null
            };
            f.registered = function(a, b) {
                e.registered(a, b);
                g = b
            };
            f.accept = function(a, c, e) {
                return h && !!b && d.getConfig("webSocket")
            };
            f.send = function(a, b) {
                this._debug("Transport", this.getType(), "sending", a, "metaConnect =", b);
                var c = [];
                for (var d = 0; d < a.messages.length; ++d) {
                    var e = a.messages[d];
                    if (e.id) {
                        c.push(e.id)
                    }
                }
                j[c.join(",")] = [a, b];
                this._debug("Transport", this.getType(), "stored envelope, envelopes", j);
                r.call(this, a, b)
            };
            f.abort = function() {
                e.abort();
                if (l !== null) {
                    try {
                        l.close(1001)
                    } catch (a) {
                        this._debug(a)
                    }
                }
                this.reset()
            };
            f.reset = function() {
                e.reset();
                if (l !== null && m) {
                    l.close(1e3, "Reset")
                }
                h = true;
                i = false;
                k = {};
                j = {};
                l = null;
                m = false;
                o = null
            };
            return f
        }
    });
    f("connect.Connect", function() {
        function w(g) {
            function bf() {
                if (G) {
                    clearTimeout(G)
                }
                G = r(function() {
                    V("disconnected", n.HEARTBEAT_TIMEOUT)
                }, o.heart_beat_timeout)
            }
            function be() {
                return C
            }
            function bd(a) {
                return w.publish("/im/req", a)
            }
            function bc(a) {
                s.forEach(F, function(b) {
                    b(a)
                })
            }
            function bb(a) {
                s.forEach(E, function(b) {
                    b(a)
                })
            }
            function ba(a, b) {
                s.forEach(D, function(c) {
                    c(a, b)
                })
            }
            function _(a) {
                if (q.isFunction(a)) {
                    F.push(a)
                }
            }
            function $(a) {
                if (q.isFunction(a)) {
                    E.push(a)
                }
            }
            function Z(a) {
                if (q.isFunction(a)) {
                    D.push(a)
                }
            }
            function Y() {
                if (C !== "connecting")
                    M()
            }
            function X() {
                if (H <= o.reconnect_time) {
                    var a = b * Math.random();
                    r(function() {
                        N()
                    }, a)
                } else {
                    V("shutdown")
                }
                H += 1
            }
            function W() {
                w.clearListeners();
                w.clearSubscriptions();
                w.disconnect()
            }
            function V(a, b) {
                if (C != a) {
                    if (C != "connecting" || a != "disconnected") {
                        ba(a, b)
                    }
                    C = a;
                    switch (a) {
                        case "connected":
                            break;
                        case "connecting":
                            break;
                        case "disconnected":
                            W();
                            X();
                            break;
                        case "shutdown":
                            W();
                            break
                    }
                }
            }
            function U() {
                return y.timesync.getServerTime()
            }
            function T(a) {
                return o[a]
            }
            function S() {
                if (!I) {
                    var a = unescape(p.get("SUP")), b = a.match(/uid=([0-9]+)&/);
                    if (a && b && b[1]) {
                        I = b[1]
                    }
                }
                return I
            }
            function R(a) {
                var b = a.data && a.data.test || e;
                if (a.id && !b) {
                    bb(a)
                } else {
                    bc(a)
                }
            }
            function Q() {
                x = w.subscribe(A, function(a) {
                    R(a)
                })
            }
            function P() {
                w.addListener("/meta/handshake", function(a) {
                    if (a.successful) {
                        Q()
                    } else {
                        if (a.xhr && o.webSocket) {
                            w.unregisterTransport("websocket")
                        } else {
                            V("disconnected", n.HANDSHAKE_FAIL)
                        }
                    }
                });
                w.addListener("/meta/connect", function(a) {
                    if (!a.successful) {
                        V("disconnected", n.CONNECT_FAIL)
                    } else {
                        H = 0;
                        V("connected");
                        bf()
                    }
                });
                w.addListener("/meta/publish", function(a) {
                    if ((a.failure === true || a.successful === false) && C !== "connecting") {
                        V("disconnected", n.PUBLISH_FAIL)
                    }
                })
            }
            function O(b) {
                var c = S();
                if (c) {
                    m(a, {uid: c || "",returntype: "json",v: "1.1"}, {timeout: 30 * 1e3}).done(function(a) {
                        b(a)
                    }).fail(function(a) {
                        b(a, true)
                    })
                }
            }
            function N() {
                w.init({url: z + "im",logLevel: o.log_level});
                P()
            }
            function M() {
                B = d;
                O(function(a, b) {
                    if (!b && a && a.server && a.channel) {
                        z = a.server;
                        A = a.channel;
                        N()
                    } else {
                        V("disconnected", n.NAS_FAIL)
                    }
                })
            }
            function L() {
                if (o.webSocket) {
                    w.registerTransport("websocket", h(_self))
                }
                w.registerTransport("callback-polling", f(_self))
            }
            function K() {
                t.forEach(y, function(a, b) {
                    w.registerExtension(a, b)
                })
            }
            function J() {
                y.ack = i();
                y.reload = j();
                y.timestamp = k();
                y.timesync = l();
                w = new c;
                K();
                L()
            }
            var o = t.extend(v, g || {}), w = null, x = null, y = {}, z = null, A = null, B = e, C = "unconnect", D = [], E = [], F = [], G = null, H = 0, I = "";
            _self = {};
            _self = {start: function() {
                    if (!B) {
                        u(M)
                    }
                },send: bd,onMessage: $,onNotice: _,onStatusChange: Z,setStatus: V,getConfig: T,getUid: S,getServerTime: U,getStatus: be,reConnectNow: Y};
            J();
            return _self
        }
        var a = "http://nas.im.api.weibo.com/im/webim.jsp";
        var b = 30 * 1e3;
        var c = g("cometd.Cometd"), f = g("transport.CallbackPolling"), h = g("transport.WebSocket"), i = g("cometd.ext.AckExtension"), j = g("cometd.ext.ReloadExtension"), k = g("cometd.ext.TimeStampExtension"), l = g("cometd.ext.TimeSyncExtension"), m = g("async.jsonp"), n = g("base.Error"), o = g("base.Config"), p = g("util.cookie"), q = g("util.type"), r = g("util.delay"), s = g("util.array"), t = g("util.object"), u = g("util.ready");
        var v = {webSocket: false,log_level: o("cometd_level") || "info",heart_beat_timeout: 3.5 * 60 * 1e3,packet_timeout: 8 * 1e3,webSocket_connect_timeout: 1e3,reconnect_time: 5};
        return w
    });
    f("connect.Sender", function() {
        var a = g("async.deferred"), b = g("util.object").forEach, c = g("util.array").forEach, d = g("util.delay"), e = g("base.Error");
        return function() {
            function p(a, b) {
                return o(a, b)
            }
            function o(b, c) {
                return (new a).outer(function(a) {
                    if (f && f.getStatus() == "connected") {
                        var i = g[f.send(b)] = {param: b,deferred: a,autoResend: c,timeout: d(function() {
                                f.setStatus("disconnected", e.RESPONSE_TIMEOUT)
                            }, f.getConfig("packet_timeout"))};
                        j.notify(i)
                    } else if (c) {
                        h.push({param: b,deferred: a,autoResend: c});
                        d(function() {
                            a.notify({type: "disconnected"})
                        })
                    } else {
                        a.inject({error: true,errCode: "disconnected",message: "Not Connected."})
                    }
                })
            }
            function n(a) {
                if (!f && a) {
                    f = a;
                    f.onMessage(l);
                    f.onNotice(k);
                    f.onStatusChange(m)
                }
            }
            function m(a) {
                if (a == "disconnected") {
                    b(g, function(a, b) {
                        clearTimeout(b.timeout);
                        if (b.autoResend) {
                            h.push(b);
                            b.deferred.notify({type: "disconnected"})
                        } else {
                            b.deferred.inject({error: true,errCode: "disconnected",message: "Not Connected."})
                        }
                    });
                    g = {}
                } else if (a == "connected") {
                    c(h, function(a) {
                        a.timeout = d(function() {
                            f.setStatus("disconnected", e.RESPONSE_TIMEOUT)
                        }, f.getConfig("packet_timeout"));
                        g[f.send(a.param)] = a
                    });
                    h.length = 0
                }
            }
            function l(a) {
                var b = g[a.id], c = a.data || {}, d = b.param.cmd, e = c.type || a.type;
                if (b) {
                    if (e == d) {
                        clearTimeout(b.timeout);
                        i.notify(b, c);
                        b.deferred.resolve(c);
                        if (e != "msg") {
                            delete g[a.id]
                        }
                    } else {
                        b.deferred.notify({type: "receipt",errorCode: c.errCode,success: c.ret === 0});
                        if (b.param.cmd == "test_notice") {
                            clearTimeout(b.timeout);
                            b.deferred.resolve(c)
                        }
                    }
                }
            }
            function k(a) {
                var b = a.data;
                if (b.type == "msg") {
                    d(function() {
                        var a = g[b.reqid];
                        if (a) {
                            a.deferred.notify({success: b.ret === 0,ret: b.ret,errCode: b.errCode,type: "send",mid: b.dmid,error: b.errMsg});
                            delete g[b.reqid]
                        }
                    }, 300)
                }
            }
            var f = null, g = {}, h = [], i = new a, j = new a;
            p.on = function(a) {
                i.progress(a)
            };
            p.beforeSend = function(a) {
                j.progress(a)
            };
            p.inject = function(a) {
                n(a)
            };
            return p
        }()
    });
    f("connect.Notifier", function() {
        function f(a) {
            return a.replace(/_\w/g, function(a) {
                return a[1].toUpperCase()
            })
        }
        var a = g("async.deferred"), b = g("notice"), c = g("util.array"), d = g("util.type"), e = g("util.delay");
        return function() {
            function i(a) {
                var g = a.data, i = f(g.type), j = b[i], k = j.type;
                if (j) {
                    try {
                        var l = j.fix(g);
                        if (l.fixedType) {
                            k = l.fixedType;
                            delete l.fixedType
                        }
                        if (d.isArray(l)) {
                            c.forEach(l, function(a) {
                                (function(a) {
                                    e(function() {
                                        h.notify(k, a)
                                    })
                                })(a)
                            })
                        } else {
                            e(function() {
                                h.notify(k, l)
                            })
                        }
                    } catch (m) {
                    }
                }
            }
            var g = null, h = new a;
            var j = {on: function(a) {
                    h.progress(a)
                },push: function(a, b) {
                    h.notify(a, b)
                },inject: function(a) {
                    if (!g && a) {
                        g = a;
                        g.onNotice(i)
                    }
                },unbind: function(a) {
                    h.unProgress(a)
                }};
            return j
        }()
    });
    f("connect.Unreader", function() {
        var a = g("async.deferred"), b = g("connect.Notifier"), c = g("util.object").forEach, d = g("util.delay"), e = g("util.bip"), f = g("util.type");
        var h = null;
        var i = null;
        return function() {
            function q() {
                return g.dmIsRemind === 0
            }
            function p() {
                var a = {dmIsRemind: g.dmIsRemind}, b = {}, d = {};
                var e = 0;
                if (g.dmIsRemind != 1) {
                    c(g.items, function(a, c) {
                        b[a] = parseInt(c) || 0;
                        e += parseInt(c) || 0
                    });
                    c(g.groupItems, function(a, b) {
                        d[a] = parseInt(b) || 0;
                        e += parseInt(b) || 0
                    })
                }
                a.items = b;
                a.groupItems = d;
                a.total = e;
                return a
            }
            function o(a, b) {
                var f = g.items, h = g.groupItems, o, r;
                switch (a) {
                    case "message":
                        if (b.mid) {
                            o = b.uid;
                            g.total += 1;
                            if (!f[o]) {
                                f[o] = 0
                            }
                            f[o] += 1;
                            j = b.mid;
                            if (b.dmIsRemind !== undefined) {
                                g.dmIsRemind = b.dmIsRemind
                            }
                        }
                        break;
                    case "groupMessage":
                        var s = b.data;
                        if (s.id && i != s.from_uid && !s.sub_type) {
                            if (s.push == 1) {
                                r = s.gid;
                                g.total += 1;
                                if (!h[r]) {
                                    h[r] = 0
                                }
                                h[r] += 1
                            }
                            k = b.id
                        }
                        break;
                    case "unread":
                        n = true;
                        g.total = Math.max(0, b.total) + g.total;
                        c(b.items, function(a, b) {
                            if (parseInt(b)) {
                                f[a] = b
                            }
                        });
                        g.dmIsRemind = b.dmIsRemind;
                        j = b.lastMid;
                        break;
                    case "clear":
                        o = b.uid;
                        if (parseInt(o) === 0) {
                            c(f, function(a) {
                                f[a] = 0
                            });
                            g.total = 0
                        } else if (f[o]) {
                            g.total -= parseInt(f[o]) || 0;
                            g.total = Math.max(0, g.total);
                            f[o] = 0
                        }
                        break;
                    case "clearMessageUnread":
                        r = b.data.gid;
                        if (!r) {
                            if (h) {
                                c(h, function(a) {
                                    h[a] = 0
                                })
                            }
                            if (f) {
                                c(f, function(a) {
                                    f[a] = 0
                                })
                            }
                            g.total = 0
                        } else if (r && h[r]) {
                            g.total -= parseInt(h[r]) || 0;
                            g.total = Math.max(0, g.total);
                            h[r] = 0
                        }
                        break;
                    default:
                        return
                }
                clearTimeout(m);
                m = d(function() {
                    if (q()) {
                        e.notify(p())
                    }
                }, l)
            }
            var e = new a, g = {total: 0,items: {},groupItems: {},dmIsRemind: 0}, j = 0, k = 0, l = 500, m;
            var n = false;
            b.on(o);
            return {on: function(a) {
                    if (f.isFunction(a)) {
                        if (n && q()) {
                            d(function() {
                                a(p())
                            })
                        }
                        e.progress(a)
                    }
                },unbind: function(a) {
                    e.unProgress(a)
                },inject: function(a) {
                    if (!h && a) {
                        h = a;
                        i = h.getUid()
                    }
                },get: function() {
                    return p()
                },set: function(a) {
                    g.groupItems = a;
                    c(a, function(a, b) {
                        g.total += b
                    })
                },clearGroup: function(a) {
                    var b = g.groupItems[a] || 0;
                    g.groupItems[a] = 0;
                    g.total -= b;
                    e.notify(p())
                }}
        }()
    });
    f("connect.Newser", function() {
        var a = "http://wbapp.mobile.sina.cn/interface/f/ttt/v3/pc_win.php?posid=pos51dbbc713bb90", b = g("async.deferred"), c = g("connect.Notifier"), d = g("util.delay"), e = g("async.jsonp"), f = g("util.type");
        return function() {
            function j(a, b) {
                if (a == "news") {
                    h = b;
                    g.notify(b)
                }
            }
            function i(b, c) {
                e(a, {uid: b}).done(function(a) {
                    var b = c.getItem("last_news_id"), d;
                    if (!a || b == a.id) {
                        return
                    }
                    d = {bigTitle: a.title,title: a.content,url: a.link,adId: a.id,clickUrl: a.click_url,t: a.t,check_time: a.check_time};
                    h = d;
                    g.notify(d)
                })
            }
            var g = new b, h;
            c.on(j);
            return {on: function(a) {
                    if (f.isFunction(a)) {
                        if (h) {
                            d(function() {
                                a(h)
                            })
                        }
                        g.progress(a)
                    }
                },unbind: function(a) {
                    g.unProgress(a)
                },start: function(a, b) {
                    i(a, b)
                }}
        }()
    });
    f("connect.Messager", function() {
        var a = g("async.deferred"), b = g("connect.Notifier"), c = g("util.bip");
        return function() {
            function g(a, b) {
                switch (a) {
                    case "message":
                        if (b.mid) {
                            d.notify("rev", b);
                            e = b.mid
                        }
                        break;
                    case "send":
                        if (c(b.mid, f)) {
                            d.notify("send", b);
                            f = b.mid
                        }
                        break;
                    default:
                        return
                }
            }
            var d = new a, e = 0, f = 0;
            b.on(g);
            return {on: function(a) {
                    d.progress(a)
                },unbind: function(a) {
                    d.unProgress(a)
                }}
        }()
    });
    f("connect.GroupMessager", function() {
        var a = g("async.deferred"), b = g("connect.Notifier"), c = g("util.bip");
        return function() {
            function g(a, b) {
                var g = b.data;
                switch (a) {
                    case "groupMessage":
                        if (g.sub_type) {
                            b.type = "warn";
                            d.notify("notice", b)
                        } else if (g.id) {
                            d.notify("rev", g);
                            e = g.id
                        }
                        break;
                    case "groupSend":
                        if (c(g.id, f)) {
                            d.notify("send", g);
                            f = g.id
                        }
                        break;
                    case "exitGroup":
                        d.notify("notice", b);
                        break;
                    case "createGroup":
                        d.notify("notice", b);
                        break;
                    case "joinGroup":
                        d.notify("notice", b);
                        break;
                    case "kickGroup":
                        d.notify("notice", b);
                        break;
                    case "changeTitle":
                        d.notify("notice", b);
                        break;
                    case "adminRoleChange":
                        d.notify("notice", b);
                        break;
                    case "delNotice":
                        d.notify("notice", b);
                        break;
                    case "groupInfoChange":
                        d.notify("notice", b);
                        break;
                    case "clearNoticeUnread":
                        d.notify("notice", b);
                        break;
                    case "acceptJoinNotice":
                        d.notify("notice", b);
                        break;
                    case "newJoinNotice":
                        d.notify("notice", b);
                        break;
                    default:
                        return
                }
            }
            var d = new a, e = 0, f = 0;
            b.on(g);
            return {on: function(a) {
                    d.progress(a)
                },unbind: function(a) {
                    d.unProgress(a)
                }}
        }()
    });
    f("memory.Storage", function() {
        function v(b) {
            var c = new a;
            if (p) {
                u(b).then(function(a) {
                    c.resolve(a)
                }, function(a) {
                    c.resolve(r)
                })
            } else if (o) {
                t(b).then(function(a) {
                    c.resolve(a)
                }, function(a) {
                    if (p) {
                        u(b).then(function(a) {
                            c.resolve(a)
                        }, function(a) {
                            c.resolve(r)
                        })
                    } else {
                        c.resolve(r)
                    }
                })
            } else {
                c.resolve(r)
            }
            return {done: c.done}
        }
        function u(b) {
            var c = new a, f = b + "-", g = function() {
                var a = {type: "local",getItem: function(a, b) {
                        if (s[a] == void 0) {
                            try {
                                var c = z.parse(localStorage.getItem(f + a)) || null;
                                s[a] = c
                            } catch (d) {
                                s[a] = null
                            }
                        }
                        return b == void 0 ? s[a] : s[a] === null ? b : s[a]
                    },setItem: function(b, c) {
                        if (c != void 0) {
                            s[b] = c;
                            try {
                                return localStorage.setItem(f + b, z.stringify(c))
                            } catch (d) {
                                return e
                            }
                        } else {
                            return a.clear(b)
                        }
                    },clear: function(a) {
                        s[a] = void 0;
                        try {
                            localStorage.clear(a);
                            return d
                        } catch (b) {
                            return e
                        }
                    }};
                return a
            };
            j(function() {
                c.resolve(g())
            });
            return c
        }
        function t(d) {
            var g = new a, k = function() {
                var a = c.getElementById(l), b = {type: "flash",getItem: function(b, c) {
                        if (s[b] == void 0) {
                            try {
                                var d = z.parse(decodeURIComponent(a.getSo(b))) || null;
                                s[b] = d
                            } catch (e) {
                                s[b] = null
                            }
                        }
                        return c == void 0 ? s[b] : s[b] === null ? c : s[b]
                    },setItem: function(c, d) {
                        if (d != void 0) {
                            s[c] = d;
                            try {
                                return a.setSo(c, z.stringify(d))
                            } catch (f) {
                                return e
                            }
                        } else {
                            return b.clear(c)
                        }
                    },clear: function(b) {
                        s[b] = void 0;
                        try {
                            return a.clear(b)
                        } catch (c) {
                            return e
                        }
                    }};
                return b
            }, o = j(function() {
                g.inject("TIMEOUT")
            }, n);
            b[h]._storageEvent = {ready: function() {
                    clearTimeout(o);
                    g.resolve(k())
                },error: function(a) {
                    clearTimeout(o);
                    g.inject(a)
                }};
            try {
                q(l, c.getElementById(f("im_root")));
                i.embedSWF(m, l, "1px", "1px", "0", "", {uid: d,ready: h + "._storageEvent.ready",error: h + "._storageEvent.error"}, {allowScriptAccess: "always",wmode: "transparent"}, {})
            } catch (p) {
            }
            return g
        }
        var a = g("async.deferred"), f = g("base.Config"), h = f("im_space"), i = g("util.swf"), j = g("util.delay"), k = g("util.browser").IE, l = "webim_swf_box", m = f("storage_swf_path") + "?_version=" + f("im_version"), n = 1e3, o = function() {
            var a = false, b;
            if (k) {
                try {
                    b = new ActiveXObject("ShockwaveFlash.ShockwaveFlash");
                    a = b && b.GetVariable("$version") ? true : false
                } catch (c) {
                    a = false
                }
            } else {
                b = navigator.plugins["Shockwave Flash"];
                a = b && b.description ? true : false
            }
            return a
        }(), p = !!b.localStorage, q = function(a, b) {
            b = b || c.body;
            var d = c.createElement("div");
            d.id = a;
            d.style.cssText = "position:absolute;position:fixed;bottom:2px;right:2px;z-index:9999;width:1px;height:1px";
            b.appendChild(d)
        }, r = {type: "empty",getItem: function(a, b) {
                return b == void 0 ? null : b
            },setItem: function() {
                return e
            },clear: function() {
                return e
            }}, s = {};
        return v
    });
    f("plugin.audio", function() {
        var a = g("async.deferred"), b = g("util.ready"), c;
        b(function() {
            var a = !!document.createElement("video").canPlayType;
            if (a) {
                c = WBAudio("HTML5")
            } else {
                c = WBAudio("FLASH")
            }
        });
        return function(b) {
            return (new a).outer(function(a) {
                c.load(b);
                c.play();
                a.resolve(c)
            })
        }
    });
    f("plugin.sync", function() {
        var a = g("connect.Sender"), b = g("connect.Notifier"), c = g("util.object").clone;
        a.on(function(c) {
            param = c.param;
            switch (param.cmd) {
                case "msg":
                    c.deferred.progress(function(b) {
                        if (b.type == "send" && b.success) {
                            a({cmd: "synchroniz",syncData: z.stringify({uid: param.uid,msg: param.msg,"long": false,mid: b.mid,time: +(new Date),att_ids: param.x && param.x.att_ids}),seq: "fixed-send"}).done()
                        }
                    });
                    break;
                case "revmsg":
                    b.push("clear", {uid: param.uid});
                    a({cmd: "synchroniz",syncData: z.stringify({uid: param.uid}),seq: "fixed-clear"}).done();
                    break
            }
        })
    });
    f("plugin.upload", function() {
        function s(b) {
            return (new a).outer(function(c) {
                var d = new a, e = null;
                r(b).done(function(a) {
                    e = a;
                    c.resolve(function(a) {
                        return {setStyle: function(b, c) {
                                if (a && a.style) {
                                    a.style[b] = c
                                }
                            },setStyles: function(b) {
                                if (a && a.style) {
                                    for (var c in b) {
                                        a.style[c] = b[c]
                                    }
                                }
                            },show: function() {
                                if (a && a.style) {
                                    a.style.display = ""
                                }
                            },hide: function() {
                                if (a && a.style) {
                                    a.style.display = "none"
                                }
                            },setUid: function(b) {
                                if (a && a.resetUploadAPI) {
                                    a.resetUploadAPI(m + "&tuid=" + b)
                                }
                            },setGid: function(b) {
                                if (a && a.resetUploadAPI) {
                                    a.resetUploadAPI(m + "&gid=" + b)
                                }
                            },upload: function(b) {
                                if (a && a.screenCapture) {
                                    a.screenCapture(b)
                                }
                            },onNotice: function(a) {
                                if (d) {
                                    d.progress(a)
                                }
                            }}
                    }(a))
                }).fail(function() {
                    c.inject()
                }).progress(function(a, b) {
                    if (d) {
                        d.notify(a, b)
                    }
                })
            })
        }
        var a = g("async.deferred"), d = g("base.Config"), e = d("im_space"), f = g("util.swf"), h = g("util.delay"), i = g("util.type"), j = g("util.browser").IE, k = "webim_upload_box", l = d("upload_swf_path") + "?_version=" + d("im_version"), m = "http://upload.api.weibo.com/2/mss/upload.json?source=" + d("im_source"), n = 5e3, o = ["img", "file"], p = function() {
            var a = false, b;
            if (j) {
                try {
                    b = new ActiveXObject("ShockwaveFlash.ShockwaveFlash");
                    a = b && b.GetVariable("$version") ? true : false
                } catch (c) {
                    a = false
                }
            } else {
                b = navigator.plugins["Shockwave Flash"];
                a = b && b.description ? true : false
            }
            return a
        }(), q = function(a, b) {
            b = b || c.body;
            var d = c.createElement("div");
            d.id = a;
            d.style.cssText = "position:absolute;position:fixed;bottom:2px;right:2px;z-index:9999;width:1px;height:1px";
            b.appendChild(d)
        }, r = function(g) {
            var i = new a, j = function() {
                return c.getElementById(k)
            }, p = h(function() {
                i.inject("TIMEOUT")
            }, n);
            b[e]._uploadEvent = {init: function(a) {
                    i.resolve(j())
                },success: function(a) {
                    if (a.data) {
                        i.notify("success", a)
                    }
                },error: function(a) {
                    i.notify("error", a)
                },start: function(a) {
                    i.notify("start", a)
                },showTip: function(a) {
                    i.notify("show", o[a || 0])
                },hideTip: function(a) {
                    i.notify("hide", o[a || 0])
                }};
            try {
                q(k, g || c.getElementById(d("im_root")));
                var r = f.embedSWF(l, k, "44px", "16px", "0", "", {swfid: k,maxSumSize: 50,maxFileSize: 50,maxFileNum: 100,multiSelect: 0,uploadAPI: encodeURIComponent(m),initFun: e + "._uploadEvent.init",sucFun: e + "._uploadEvent.success",errFun: e + "._uploadEvent.error",beginFun: e + "._uploadEvent.start",showTipFun: e + "._uploadEvent.showTip",hiddenTipFun: e + "._uploadEvent.hideTip",areaInfo: "0-16|12-16",fExt: "*.jpg;*.gif;*.jpeg;*.png|*",fExtDec: "选择图片|选择文件"}, {menu: "false",scale: "noScale",allowFullscreen: "true",allowScriptAccess: "always",bgcolor: "",wmode: "transparent",data: "upload.swf"}, {})
            } catch (s) {
            }
            return i
        };
        return p ? function(a) {
            return s(a)
        } : null
    });
    f("plugin.tip", function() {
        var a = b.__PubSub__;
        return function(b) {
            if (a) {
                b.onConnect(function() {
                    a.publish("webim.connected")
                });
                b.onUnreadChange(function(b) {
                    a.publish("webim.showMessage", b.total)
                });
                a.subscribe("webim.messageTipClicked", function(a) {
                    b.publish("IM:tipClick", a || {})
                })
            }
        }
    });
    f("plugin.apiCache", function() {
        var a = {};
        var b = g("async.deferred"), c = g("connect.Sender"), d = g("connect.Notifier"), e = g("util.array");
        c.on(function(b) {
            if (b.param.cmd == "recents") {
                b.deferred.done(function(b) {
                    e.forEach(b.recents, function(b) {
                        var c = e.associate(b, w);
                        if (c.nick) {
                            c.uid = c.uid + "";
                            c.status = u[c.status];
                            a[c.uid] = c
                        }
                    })
                })
            } else if (b.param.cmd == "roster") {
                b.deferred.done(function(b) {
                    e.forEach(b.items, function(b) {
                        var c = e.associate(b, v);
                        if (c.nick) {
                            c.uid = c.uid + "";
                            c.status = u[c.status];
                            a[c.uid] = c
                        }
                    })
                })
            }
        });
        return function(c, d) {
            var f = c.getUser;
            if (d && d.recent) {
                e.forEach(d.recent, function(b) {
                    if (b.nick) {
                        a[b.uid] = b
                    }
                })
            }
            c.getUser = function(c) {
                return (new b).outer(function(b) {
                    if (a[c]) {
                        b.resolve(a[c])
                    } else {
                        f(c).done(function(d) {
                            a[c] = d;
                            b.resolve(d)
                        }).fail(b.inject)
                    }
                })
            };
            return c
        }
    });
    f("plugin.apiQueue", function() {
        var a = g("async.deferred"), b = g("util.array");
        var c = function() {
            function c() {
                if (a) {
                    if (b.length > 0) {
                        a = e;
                        var f = b.shift(), g = f.deferred, h = f.outer;
                        g.all(function() {
                            a = d;
                            c()
                        }).done(function(a) {
                            h.resolve(a)
                        }).fail(h.inject).progress(h.notify)
                    }
                }
            }
            var a = d, b = [];
            return function(a, d) {
                if (a.all) {
                    b.push({deferred: a,outer: d})
                }
                c()
            }
        }();
        var f = ["sendMessage", "getUser", "getRoster", "getRecent", "getPresence", "getHistory", "setPresence", "clearUnread", "sync", "setStatus", "chatSync", "blockUser", "restrict", "search", "getSetting", "setSetting", "publish", "comment", "like", "interact", "inviteResponse", "testNotice"];
        return function(d) {
            var e = {};
            b.forEach(f, function(b) {
                e[b] = d[b];
                d[b] = function() {
                    var d = [].slice.call(arguments);
                    return (new a).outer(function(a) {
                        var f = e[b].apply({}, d);
                        c(f, a)
                    })
                }
            });
            return d
        }
    });
    f("open.Public", function() {
        function j(d, f, g) {
            var j = new b, k = {timeout: a}, l = g ? h.replace("api.", g + ".api.") : h;
            f = f || {};
            f.source = i;
            if (e.is(f.timeout) == "Number") {
                k.timeout = f.timeout;
                delete k.timeout
            }
            return j.outer(function() {
                c(l + d + ".json", f, k).done(function(a) {
                    var b = a.code ? a.data : a;
                    j.resolve(b)
                }).fail(j.inject)
            })
        }
        var a = 5 * 1e3;
        var b = g("async.deferred"), c = g("async.jsonp"), d = g("async.queue"), e = g("util.type"), f = g("util.object"), h = "http://api.weibo.com/2/", i = g("base.Config")("im_source");
        j.list = function(a, c, g, h) {
            return (new b).outer(function(b) {
                var i = [], k = {};
                d([j(a, g, h)], null, true).done(function() {
                    k[c] = i;
                    b.resolve(k)
                }).progress(function(d, k, l) {
                    if (k[c] && e.isArray(k[c])) {
                        i = i.concat(k[c]);
                        if (i.length < k.total_number) {
                            l.push(j(a, f.assign(g || {}, {cursor: k.next_cursor}), h))
                        }
                    } else {
                        b.inject()
                    }
                })
            })
        };
        return j
    });
    f("open.Private", function() {
        var a = g("async.deferred"), b = g("connect.Sender");
        return function(a, c) {
            c = c || {};
            return b({cmd: "api_proxy",api: a,params: c}, true)
        }
    });
    f("control.Root", function() {
        function i(d, f) {
            function k() {
                h.top = j()
            }
            var g, h = d.style, i = c.compatMode && c.compatMode === "CSS1Compat";
            h.position = "absolute";
            var j = i ? function() {
                var a = c.documentElement;
                return a.scrollTop + (a.clientHeight - d.clientHeight) - f + "px"
            } : function() {
                var a = c.body;
                return a.scrollTop + (a.clientHeight - d.clientHeight) - f + "px"
            };
            k();
            e(b, "scroll", function() {
                if (g) {
                    clearTimeout(g)
                }
                h.display = "none";
                g = a(function() {
                    k();
                    h.display = "";
                    g = null
                }, 300)
            });
            e(b, "resize", k)
        }
        var a = g("util.delay"), d = g("base.Config"), e = g("util.addEvent"), f = g("util.browser").IE6, h = null;
        return {create: function() {
                if (!h) {
                    h = c.createElement("div");
                    h.id = d("im_root");
                    if (d("im_lang") == "en") {
                        h.className = "WB_webim webim_L_en"
                    } else {
                        h.className = "WB_webim"
                    }
                    h.style.cssText = "position:fixed;bottom:0px;right:0px;z-index:1024";
                    if (f) {
                        i(h, 0)
                    }
                    c.body.appendChild(h)
                }
                return h
            },show: function() {
                if (h) {
                    h.style.display = ""
                }
            },hide: function() {
                if (h) {
                    h.style.display = "none"
                }
            }}
    });
    f("control.Component", function() {
        var a = g("base.Config"), c = g("async.loader"), d = g("async.queue"), f = g("async.parallel"), h = g("open.Public"), i = e, j = i || a("im_gray") == "on", m = j ? l : k, n = b && b.$CONFIG || {}, p = a("im_lang"), r = a("im_test");
        var s = "http://js.t.sinajs.cn/open/analytics/js/suda.js?version=";
        return {load: function() {
                var e = a("im_cache") ? a("im_version") : window.$IM_CONFIG && window.$IM_CONFIG.im_version ? window.$IM_CONFIG.im_version : +(new Date);
                f([n.$webim === void 0 ? h("account/get_privacy") : {webim: n.$webim}], ["config"]).done(function(a) {
                    var b = !!a.config.webim;
                    if (b) {
                        var g = [];
                        if (!window.SUDA) {
                            g.push(c("js", s + e))
                        } else if (!SUDA.uaTrack) {
                            SUDA.uaTrack = function() {
                            }
                        }
                        if (p == "en") {
                        }
                        if (o[p]) {
                            g.push(c("js", o[p]))
                        }
                        d([f(g), c("js", m.js + "?v=" + e)]).done()
                    }
                });
                if (r) {
                    var g = "http://" + a("im_test_server") + ":" + a("im_test_port");
                    b.TRIAL_CONFIG = {server: a("im_test_server"),port: a("im_test_port")};
                    d([c("js", g + q.io), c("js", g + q.jquery), c("js", g + q.index)]).done()
                }
            }}
    });
    f("control.Protocol", function() {
        function n(a, c) {
            var d = new b, e = i.isString(a) ? {cmd: a} : a, g = f[e.cmd];
            if (g) {
                var j = g.fix.apply(e, c);
                h(j, g.autoResend).done(function(a) {
                    try {
                        d.resolve(g.receive(a))
                    } catch (b) {
                    }
                }).fail(d.inject).progress(d.notify)
            }
            return d
        }
        var a = {sendMessage: "msg",getUser: "vcard",getRoster: "roster",getRecent: "recents",getPresence: "querypresence",getHistory: "chatMsgHistory",setPresence: "presence",clearUnread: "revmsg",sync: "synchroniz",setStatus: "chatStates",chatSync: "chatSync",blockUser: "blockscreate",restrict: "restrict",search: "rosterSearch",getSetting: {cmd: "usersetting",subcmd: "get"},setSetting: {cmd: "usersetting",subcmd: "set"},publish: "statusesUpdate",comment: "createComment",like: "likeupdate",interact: "friendsInteract",inviteResponse: "inviteResponse",testNotice: "testNotice"};
        var b = g("async.deferred"), c = g("async.queue"), e = g("open.Public"), f = g("protocol"), h = g("connect.Sender"), i = g("util.type"), j = g("util.object").forEach, k = function(a) {
            return [].slice.call(a)
        }, l = {};
        var m = {};
        j(a, function(a, b) {
            l[a] = function() {
                return n(b, k(arguments))
            }
        });
        l.getUser = function(a) {
            return (new b).outer(function(b) {
                if (m[a]) {
                    b.resolve(m[a])
                } else
                    e("users/show", {uid: a}).done(function(c) {
                        var c = {uid: c.idstr,nick: c.remark || c.remark_name || c.screen_name,sex: c.gender == "m" ? 1 : 0,status: c.online_status ? "online" : "offline",avatar: c.profile_image_url && c.profile_image_url.split("/")[5],member: c.verified_type};
                        b.resolve(c);
                        m[a] = c
                    }).fail(b.inject)
            })
        };
        var o = l.getHistory;
        l.getHistory = function() {
            var a = [].slice.call(arguments);
            return (new b).outer(function(b) {
                c([o.apply(null, a)], ["once", "twice"], d).done(function(a) {
                    if (a.twice) {
                        b.resolve(a.twice)
                    } else {
                        b.resolve(a.once)
                    }
                }).progress(function(b, c, d) {
                    if (b == "once" && !c.length && c.length < 1) {
                        d.push(o.apply(null, a))
                    }
                })
            })
        };
        return l
    });
    f("control.Register", function() {
        return function(a) {
            var b = a || {}, f = {};
            b.C = function(a) {
                var b;
                a = a.toUpperCase();
                if (a == "TEXT") {
                    b = c.createTextNode("")
                } else if (a == "BUFFER") {
                    b = c.createDocumentFragment()
                } else {
                    b = c.createElement(a)
                }
                return b
            };
            b.E = function(a) {
                if (typeof a === "string") {
                    return document.getElementById(a)
                } else {
                    return a
                }
            };
            return {register: function(a, c) {
                    var g = a.split("."), h = b, i = g.shift();
                    while (i) {
                        if (g.length) {
                            if (h[i] === void 0) {
                                h[i] = {}
                            }
                            h = h[i]
                        } else {
                            if (h[i] === void 0) {
                                h[i] = c(b);
                                if (f[a]) {
                                    b[f[a]] = h[i]
                                }
                                return d
                            }
                        }
                        i = g.shift()
                    }
                    return e
                },shortOption: function(a) {
                    for (var b in a) {
                        f[a[b]] = b
                    }
                }}
        }
    });
    f("control.Setting", function() {
        function s(g) {
            var s = new m, t = g.getItem(b), u = g.getItem(c), v = {activeType: g.getItem(h) || "user",status: g.getItem(k) || "max",activeUid: g.getItem(i) || "",activeGid: g.getItem(j) || "",ulist: a(g.getItem(d) || []),glist: a(g.getItem(f) || []),send: g.getItem(l) || "E"}, w = [], x = function(a) {
                var b = -1;
                n.forEach(w, function(c, d) {
                    if (c.uid == a) {
                        b = d
                    }
                });
                if (b > -1) {
                    return w.splice(b, 1)[0]
                } else {
                    return e
                }
            }, y = function() {
                g.setItem(c, w);
                s.notify({type: "recent",data: w})
            }, A = function() {
                g.setItem(k, v.status);
                g.setItem(d, v.ulist);
                g.setItem(f, v.glist);
                g.setItem(i, v.activeUid || "");
                g.setItem(j, v.activeGid || "");
                g.setItem(l, v.send);
                g.setItem(h, v.activeType);
                s.notify({type: "chat",data: v})
            };
            p.beforeSend(function(a) {
                var b = a.param;
                switch (b.cmd) {
                    case "setting":
                        if (b.subcmd == "set") {
                            p({cmd: "synchroniz",syncData: z.stringify({act: "refresh",data: z.parse(b.data)}),seq: "fixed-setting"}).done();
                            q.push("setting", {act: "refresh",data: z.parse(b.data)})
                        }
                        break;
                    case "msg":
                        q.push("recent", {act: "add",uid: b.uid + ""});
                        break;
                    case "synchroniz":
                        var c, d;
                        if (b.seq == "fixed-chatSync") {
                            var e = z.parse(b.syncData);
                            switch (e.type) {
                                case "add":
                                    c = e.data.uid;
                                    d = e.data.gid;
                                    if (c) {
                                        v.activeUid = c + "";
                                        if (!~n.indexOf(v.ulist, c)) {
                                            v.ulist.push(c)
                                        }
                                        v.activeType = "user"
                                    } else if (d) {
                                        v.activeGid = d + "";
                                        if (!~n.indexOf(v.glist, d)) {
                                            v.glist.push(d)
                                        }
                                        v.activeType = "group"
                                    }
                                    v.status = "open";
                                    break;
                                case "removeUser":
                                    c = e.data;
                                    n.remove(v.ulist, c);
                                    if (v.activeUid == c) {
                                        v.activeUid = v.ulist[0] || null
                                    }
                                    break;
                                case "removeGroup":
                                    d = e.data;
                                    n.remove(v.glist, d);
                                    if (v.activeGid == d) {
                                        v.activeGid = null
                                    }
                                    break;
                                case "min":
                                    v.status = "min";
                                    break;
                                case "close":
                                    v.ulist = [];
                                    v.glist = [];
                                    v.activeUid = null;
                                    v.activeGid = null;
                                    break;
                                case "clear":
                                    v.ulist = [];
                                    v.glist = [];
                                    v.activeUid = null;
                                    v.activeGid = null;
                                    break;
                                case "max":
                                    v.status = "max";
                                    break;
                                case "send":
                                    v.send = e.data || "E";
                                    break
                            }
                            A()
                        }
                        break
                }
            });
            q.on(function(a, c) {
                switch (a) {
                    case "send":
                    case "message":
                        q.push("recent", {act: "add",uid: c.uid + ""});
                        break;
                    case "setting":
                        if (c.act == "refresh") {
                            g.setItem(b, c.data);
                            s.notify({type: "setting",data: c.data})
                        }
                        break;
                    case "recent":
                        switch (c.act) {
                            case "add":
                                o([x(c.uid) || r.getUser(c.uid)], ["user"]).done(function(a) {
                                    if (a.user) {
                                        w.unshift(a.user);
                                        y()
                                    }
                                });
                                break;
                            case "remove":
                                x(c.uid);
                                y();
                                break;
                            case "refresh":
                                w = c.list;
                                y()
                        }
                        break;
                    case "chatSync":
                        var d, e;
                        switch (c.type) {
                            case "add":
                                d = c.data.uid;
                                e = c.data.gid;
                                if (d) {
                                    v.activeUid = d + "";
                                    if (!~n.indexOf(v.ulist, d)) {
                                        v.ulist.push(d)
                                    }
                                    v.activeType = "user"
                                } else if (e) {
                                    v.activeGid = e + "";
                                    if (!~n.indexOf(v.glist, e)) {
                                        v.glist.push(e)
                                    }
                                    v.activeType = "group"
                                }
                                break;
                            case "removeUser":
                                d = c.data;
                                n.remove(v.ulist, d);
                                if (v.activeUid == d) {
                                    v.activeUid = v.ulist[0] || null
                                }
                                break;
                            case "removeGroup":
                                e = c.data;
                                n.remove(v.glist, e);
                                if (v.activeGid == e) {
                                    v.activeGid = v.glist[0] || null
                                }
                                break;
                            case "min":
                                v.status = "min";
                                break;
                            case "max":
                                v.status = "max";
                                break;
                            case "close":
                                v.ulist = [];
                                v.glist = [];
                                v.activeUid = null;
                                v.activeGid = null;
                                break;
                            case "clear":
                                v.ulist = [];
                                v.glist = [];
                                v.activeUid = null;
                                v.activeGid = null;
                                break;
                            case "send":
                                v.send = c.data || "E";
                                break
                        }
                        A();
                        break
                }
            });
            return s.outer(function() {
                if (t && u) {
                    curRcent = u;
                    s.resolve({setting: t,recent: u,chat: v})
                } else {
                    s.resolve({setting: {},recent: [],chat: v})
                }
            })
        }
        var a = g("util.array").unique;
        var b = "remote-setting", c = "remote-recent", d = "local-chat-user", f = "local-chat-group", h = "local-chat-type", i = "local-active-user", j = "local-active-group", k = "local-cStatus", l = "local-send";
        var m = g("async.deferred"), n = g("util.array"), o = g("async.parallel"), p = g("connect.Sender"), q = g("connect.Notifier"), r = g("control.Protocol");
        return s
    });
    f("control.Watch", function() {
        var a = g("async.deferred"), b = g("util.type"), c = g("util.array"), d = g("util.object");
        return function() {
            var c = new a, e = {};
            return {watch: function(a, d) {
                    if (b.isFunction(d)) {
                        if (a in e) {
                            d(e[a])
                        }
                        c.progress(function(b, c, e) {
                            if (b == a) {
                                d(c, e)
                            }
                        })
                    }
                },publish: function(a, b, d) {
                    e[a] = b;
                    c.notify(a, b, d)
                },setAll: function(a) {
                    d.forEach(a, function(a, b) {
                        e[a] = b
                    })
                }}
        }()
    });
    f("rule.CardStorage", function() {
        function i(a, b) {
            for (var c = 0, d = a.length; c < d; c++) {
                if (a[c] === b) {
                    return c
                }
            }
            return -1
        }
        function h(a) {
            a = a || {};
            return a.annotations && a.annotations[0] && (a.type == "36" || a.type == "39" && a.annotations[0].object || a.type == "34" || a.type == "16" || a.type == "7")
        }
        var a = "im-card-", b = /http:\/\/t\.cn\/([\w\d]*)/g, c = 300, d = window.localStorage || {getItem: function() {
                return null
            },setItem: function() {
            },removeItem: function() {
            }}, f = [];
        try {
            f = z.parse(d.getItem(a + "list") || "[]")
        } catch (g) {
        }
        return {get: function(b) {
                for (var c = 0, g = b.length; c < g; c++) {
                    var j = b[c];
                    if (i(f, j) == -1) {
                        return e
                    } else {
                        var k = {};
                        try {
                            k = z.parse(d.getItem(a + j) || "{}")
                        } catch (l) {
                        }
                        if (h(k)) {
                            return k
                        }
                    }
                }
                return e
            },set: function(b, e) {
                if (f.length > c) {
                    var g = f.shift();
                    d.removeItem(a + g)
                }
                f.push(b);
                d.setItem(a + "list", z.stringify(f));
                d.setItem(a + b, z.stringify(e))
            }}
    });
    f("rule.CardAnalysis", function() {
        function S(b, f) {
            var g = new a, i = b[0] || {}, j = [], k = {}, l = {}, m, n;
            if (!f && i.url_short) {
                if ((i.type == "36" || i.type == "39") && i.annotations[0].object && i.annotations[0].object_type === "webpage") {
                } else {
                    u.set(i.url_short, i)
                }
            }
            if (i.annotations && i.annotations[0]) {
                if ((i.type == "36" || i.type == "39") && i.annotations[0].object) {
                    n = i.annotations[0];
                    var o = R(n);
                    m = n.object_domain_id.substring(0, 7);
                    k = o.content;
                    k.url = i.url_long;
                    k.enUrl = encodeURIComponent(k.url);
                    k.id = n.object_id;
                    k.short = i.url_short;
                    k.enShort = encodeURIComponent(k.short);
                    k.style = n.object_type;
                    switch (n.object_type) {
                        case "audio":
                            i.extra = h(E, {object_id: n.object_id,is_encoded: 1});
                            break;
                        case "article":
                            i.extra = c("entity/show", {object_id: n.object_id,with_object: 1,is_encoded: 1});
                            break;
                        case "collection":
                            if (n.object && n.object.items) {
                                var p = [];
                                x(n.object.items, function(a, b) {
                                    p.push(c("entity/show", {object_id: a.id,with_object: 1,is_encoded: 1}));
                                    if (b > 0) {
                                        j.push({id: a.id,name: a.display_name,pic: a.pic,url: a.url,enUrl: encodeURIComponent(a.url)})
                                    } else {
                                        k.id = a.id
                                    }
                                });
                                i.extra = p
                            }
                            break;
                        case "priMessage":
                            var q = n.object;
                            if (q) {
                                var s = q.content_data;
                                var v = q.content_template.replace(/{{(\w+).(\w+)}}/g, function(a, b) {
                                    return '<span style="color :${display.data.' + b + '.color}"> ${display.data.' + b + ".value} </span>"
                                }).replace(/\n/g, "<br />");
                                v = "  <#et tname display> " + v + "</#et>";
                                k = {type: "priMessage",headimgurl: q.headimgurl,data: q.content_data,content_template: q.content_template,create_at: w(q.create_at),display_name: q.display_name,target_url: q.target_url,note: q.note};
                                var y = r(v)(k);
                                k.content = y
                            }
                            break;
                        case "webpage":
                            var q = n.object;
                            if (q) {
                                var z = q.Custom_data;
                                var A = z && z.Owner;
                                if (q.id.indexOf("230698") > -1) {
                                    k.desc = q.summary;
                                    k.hidelike = "hide"
                                }
                                if (A) {
                                    k = {type: "webpage",target_url: encodeURIComponent(q.url),display_name: q.display_name,ownerUrl: A.profile_image_url,ownerName: A.name,Desc_Sum: parseInt(z.Desc_Sum),People_Count: z.People_Count,verified: A.verified};
                                    if (A.verified_type >= 1 && A.verified_type <= 7) {
                                        k.iconClassName = "icon_pf_approve_co"
                                    } else if (A.verified_type == 0) {
                                        k.iconClassName = "icon_pf_approve"
                                    }
                                }
                            }
                            break
                    }
                    if (n.object_domain_id == "1032" && n.object_type == "collection") {
                        k.type = "collection";
                        k.pic = n.object.full_image && n.object.full_image.url;
                        k.items = j
                    }
                    if (m == "2002449" || m == "2002174") {
                        k.style = m == "2002449" ? "taobao" : "tmall";
                        k.category = n.object.custom_data && n.object.custom_data.category_name || ""
                    }
                    k.pic = k.pic || function() {
                        var a;
                        switch (k.style) {
                            case "article":
                                a = H;
                                break;
                            case "vote":
                                a = I;
                                break;
                            case "topic":
                                a = J;
                                break;
                            default:
                                a = G
                        }
                        return a
                    }();
                    k.liked = e;
                    k.like_count = 0;
                    for (var B in k) {
                        if (typeof k[B] === "string") {
                            B != "html" && (k[B] = Q(k[B]))
                        }
                    }
                    l = {card: n,rule: o.rule,extra: i.extra || null,"short": i.url_short,"long": i.url_long,type: n.object_type,show: n.show_status.charAt(0) == "1",domain_id: n.object_domain_id,biz_id: (n.containerid || "").substring(0, 6),display: k,name: k.name || k.display_name,html: N(t(k).toString())};
                    g.resolve(l);
                    L(n.object_id);
                    if (f) {
                        var C = o.rule.desc1, D = o.rule.desc2;
                        if (C && C.indexOf("dynamic") > -1 || D && D.indexOf("dynamic") > -1 || n.actions && n.actions[0]) {
                            M(i.url_short)
                        }
                    }
                } else if (i.type == "34" || i.type == "16") {
                    n = i.annotations && i.annotations[0];
                    if (n) {
                        k = {type: "news",style: i.type == "34" ? "simple" : "vote",name: Q(n.title),url: i.url_short,pic: n.img_prev || H};
                        g.resolve({card: n,rule: null,extra: null,"short": i.url_short,"long": i.url_long,type: "news",show: d,domain_id: "",biz_id: "",display: k,name: k.name,html: N(t(k).toString())})
                    } else {
                        g.resolve({})
                    }
                } else if (i.type == "7") {
                    n = i.annotations && i.annotations[0];
                    if (n) {
                        var F = [];
                        x(n.news, function(a) {
                            F.push({name: a.t,url: a.url})
                        });
                        k = {type: "news",style: "collection",name: Q(n.ptitle),url: i.url_short,pic: n.sp || H,purl: n.purl,items: F};
                        g.resolve({card: n,rule: null,extra: null,"short": i.url_short,"long": i.url_long,type: "news",show: d,domain_id: "",biz_id: "",display: k,name: Q(k.name),html: N(t(k).toString())})
                    } else {
                        g.resolve({})
                    }
                } else {
                    g.resolve({})
                }
            } else {
                g.resolve({})
            }
            return g
        }
        function R(a) {
            var b = {type: "common"}, c = (a.containerid || "").substring(0, 6), d = a.object_domain_id, e = a.object_type, f = P(c, d, e), g = O(f.desc1 || "", a).replace(/\|/g, ""), h = O(f.desc2 || "", a).replace(/\|/g, ""), i = !!g && !!h;
            g = Q(g);
            h = Q(h);
            b.pic = a.object.img_url || a.object.image && a.object.image.url;
            b.name = a.object.display_name;
            b.button = f.button || "";
            b.multi = i;
            if (i) {
                b.desc1 = g;
                b.desc2 = h
            } else {
                b.desc = g || h
            }
            b.action = f.actionType;
            if (a.actions && a.actions[0]) {
                b.button = a.actions[0].name
            }
            return {content: b,rule: f}
        }
        function Q(a, b) {
            if (typeof a !== "string") {
                return a
            }
            if (b) {
                a = a.replace(/\&/g, "&amp;")
            }
            return a.replace(/"/g, "&quot;").replace(/\</g, "&lt;").replace(/\>/g, "&gt;").replace(/\'/g, "&#39;").replace(/(\u0020|\u000B|\u2028|\u2029|\f)/g, "&#32;")
        }
        function P(a, b, c) {
            if (k.biz[a]) {
                return m.associate(k.biz[a], C)
            } else if (k.domain[b] && k.domain[b][c]) {
                return m.associate(k.domain[b][c], B)
            } else {
                var d = "";
                if (b && b.charAt(0) == "1") {
                    d = b.substring(0, 4)
                } else if (b && b.charAt(0) == "2") {
                    d = b.substring(0, 7)
                }
                if (k.domain[d] && k.domain[d][c]) {
                    return m.associate(k.domain[d][c], B)
                }
            }
            return {}
        }
        function O(a, b) {
            return a.replace(z, function(a) {
                return (new D("try{ return this." + a.replace(/\.(\d+)/g, function(a, b) {
                    return "[" + b + "]"
                }) + ' || "";}catch(e){return "";}')).apply(b)
            })
        }
        function N(a) {
            return a.replace(/<!--[^(-->)]*-->/g, "")
        }
        var a = g("async.deferred"), b = g("base.Config"), c = g("open.Public"), f = g("control.Protocol"), h = g("async.jsonp"), i = g("async.queue"), j = g("async.parallel"), k = g("rule.Card"), l = g("util.object"), m = g("util.array"), n = g("util.string"), o = g("util.sizzle"), p = g("util.type"), q = g("util.MD5"), r = g("util.easyTemplate"), s = g("rule.CardTemp"), t = r(s), u = g("rule.CardStorage"), v = g("util.delay"), w = g("util.date"), x = m.forEach;
        var y = /http:\/\/t\.cn\/([\w\d]*)/g, z = /(\w+(\.[\w|\d]+)*)/g, A = /#(\S+)#/g, B = ["domain_id", "name", "object_type", "desc1", "desc2", "button", "actionType"], C = ["biz_id", "name", "object_type", "desc1", "desc2", "button", "actionType"], D = Function, E = "http://ting.weibo.com/page/appclient/getsonginfojsonp", F = "http://weibo.com/aj/card/show", G = "http://img.t.sinajs.cn/t4/appstyle/V5_webim/images/card_default.gif", H = "http://img.t.sinajs.cn/t5/style/images/face/face_card_longwb.png", I = "http://img.t.sinajs.cn/t5/style/images/face/bg_face_card.png", J = "http://img.t.sinajs.cn/t5/style/images/topic_PCD/topicavatar.jpg", K = "http://img.t.sinajs.cn/t4/appstyle/V5_webim/images/card_atten.jpg";
        var L = function() {
            function k(a) {
                l.forEach(a, function(a, b) {
                    var c = o(e + " a[data-im=card.like][data-id=" + a + "]");
                    x(c, function(a) {
                        var c = a.getElementsByTagName("i")[0], d = a.getElementsByTagName("i")[1], e = n.parseQuery(a.getAttribute("data-im-param") || "");
                        i(c, b.liked ? e.class1 : e.class2);
                        d.innerHTML = b.count || 0;
                        d.style.display = b.count === 0 ? "none" : "";
                        e.liked = b.liked ? "true" : "false";
                        a.setAttribute("data-im-param", l.toQuery(e))
                    })
                })
            }
            function i(a, b) {
                a.className = b.replace(/\|/g, " ")
            }
            function h() {
                var a = f.join(",");
                f.length = 0;
                j([c("likes/exists", {uid: b("uid"),object_ids: a}), c("likes/counts", {object_ids: a})], ["exists", "counts"]).done(function(a) {
                    var b = a.exists, c = a.counts;
                    if (b.result && b.result.length && c.objects && c.objects.length) {
                        var d = {};
                        x(b.result, function(a) {
                            d[a.object_id] = {liked: a.liked,count: 0}
                        });
                        x(c.objects, function(a) {
                            if (d[a.object_id]) {
                                d[a.object_id].count = a.like_counts
                            }
                        });
                        k(d)
                    }
                })
            }
            var a = 20, d = 500, e = "." + b("im_class") + " ";
            var f = [], g = null;
            return function(b) {
                f.push(b);
                if (g) {
                    clearTimeout(g)
                }
                if (f.length < a) {
                    g = v(h, d)
                } else {
                    h()
                }
            }
        }();
        var M = function() {
            function i() {
                var a = e.join(",");
                e.length = 0;
                h(F, {short_url: a,_t: 3}).done(function(a) {
                    x(a, g)
                })
            }
            function g(a) {
                var b = a.annotations && a.annotations[0];
                if (b && b.object) {
                    var c = b.object_id, e = R(b).content;
                    if (e.multi) {
                        var f = o(d + " [data-im-card-id=" + c + "] [data-im-type=card_desc1]"), g = o(d + " [data-im-card-id=" + c + "] [data-im-type=card_desc2]");
                        x(f, function(a) {
                            a.innerHTML = e.desc1
                        });
                        x(g, function(a) {
                            a.innerHTML = e.desc2
                        })
                    } else {
                        var h = o(d + " [data-im-card-id=" + c + "] [data-im-type=card_desc]");
                        x(h, function(a) {
                            a.innerHTML = e.desc
                        })
                    }
                    var i = o(d + " [data-im-card-id=" + c + "] [data-im-type=card_btn]");
                    x(i, function(a) {
                        a.innerHTML = e.button
                    });
                    u.set(a.url_short, a)
                }
            }
            var a = 20, c = 500, d = "." + b("im_class") + " ";
            var e = [], f = null;
            return function(b) {
                e.push(b);
                if (f) {
                    clearTimeout(f)
                }
                if (e.length < a) {
                    f = v(i, c)
                } else {
                    i()
                }
            }
        }();
        var T = {parseShortUrl: function(b) {
                var c = new a, d = (b || "").match(y);
                return c.outer(function() {
                    function a() {
                        h(F, {short_url: d.join(","),_t: 3}).done(function(a) {
                            if (p.isArray(a)) {
                                var b = [];
                                for (var d = 0; d < a.length; d++) {
                                    for (var e in a[d]) {
                                        a[d][e] && b.push(a[d][e])
                                    }
                                }
                                var f = [{}];
                                x(b, function(a) {
                                    f.push(function(a) {
                                        return function(b) {
                                            if (p.isEmptyObject(b)) {
                                                return S([a])
                                            } else {
                                                return b
                                            }
                                        }
                                    }(a))
                                });
                                i(f).done(function(a) {
                                    if (p.isArray(a)) {
                                        c.resolve(a[a.length - 1])
                                    } else {
                                        c.resolve({})
                                    }
                                }).fail(function() {
                                    c.resolve({})
                                })
                            } else {
                                c.resolve({})
                            }
                        }).fail(function() {
                            c.resolve({})
                        })
                    }
                    if (d && d.length) {
                        var b = u.get(d);
                        if (b) {
                            S([b], true).done(function(b) {
                                if (p.isEmptyObject(b)) {
                                    a()
                                } else {
                                    c.resolve(b)
                                }
                            })
                        } else {
                            a()
                        }
                    } else {
                        c.resolve({})
                    }
                })
            },parseExt: function(b, c, e) {
                var g = new a;
                if (b) {
                    if (b.card_type == "invite") {
                        if (b.invite_type == "attention") {
                            return g.outer(function(a) {
                                f.getUser(c).done(function(c) {
                                    if (c.uid) {
                                        var f = {}, g = {type: "im_invite",style: "attention",uid: c.uid,nick: c.nick,status: b.invite_state,pic: b.app_logo || K,mid: e};
                                        f.type = "im_invite";
                                        f.display = g;
                                        f.show = d;
                                        f.html = N(t(g).toString());
                                        a.resolve(f)
                                    } else {
                                        a.resolve({})
                                    }
                                }).fail(function() {
                                    a.resolve({})
                                })
                            })
                        } else if (b.invite_type == "game") {
                            return g.outer(function(a) {
                                f.getUser(c).done(function(c) {
                                    if (c.uid) {
                                        var e = {}, f = {type: "im_invite",style: "game",uid: c.uid,nick: c.nick,app_name: b.app_name,invite_url: b.invite_url};
                                        e.type = "im_invite";
                                        e.display = f;
                                        e.show = d;
                                        e.html = N(t(f).toString());
                                        a.resolve(e)
                                    } else {
                                        a.resolve({})
                                    }
                                }).fail(function() {
                                    a.resolve({})
                                })
                            })
                        } else {
                            return {}
                        }
                    } else if (b.card_type == "weibo") {
                        return {}
                    } else {
                        return {}
                    }
                } else {
                    return {}
                }
            },parseTopic: function(b) {
                var d = (b || "").match(A);
                return (new a).outer(function(a) {
                    if (d && d.length) {
                        var b = d[0], e;
                        b = b.substring(1, b.length - 1);
                        e = "1022:100808" + q(b);
                        c("object/show", {object_id: e}).done(function(c) {
                            var d = "http://huati.weibo.com/k/" + encodeURIComponent(b);
                            S([{annotations: [c],description: "",last_modified: "",title: "",type: "36",url_long: d,url_short: d}]).done(a.resolve)
                        }).fail(function() {
                            a.resolve({})
                        })
                    } else {
                        a.resolve({})
                    }
                })
            }};
        return T
    });
    f("open.Process", function() {
        var a = g("async.deferred"), b = g("base.Config"), c = g("connect.Sender"), e = g("open.Private"), f = g("open.Public"), h = g("async.jsonp"), i = g("async.queue"), j = g("async.parallel"), k = g("plugin.audio"), l = g("util.array"), m = g("util.type"), n = g("rule.CardAnalysis"), o = l.forEach;
        var p = g("util.base62");
        var q = /\\?(\[([^\[\]]+)\])/g, r = "http://ting.weibo.com/page/appclient/getsonginfojsonp";
        var s = /^http:\/\/weibo\.com\/p\/100000([0-9A-Za-z\.,_\+\-]{1,})/, t = /^http:\/\/weibo\.com\/(\d+)\/([a-zA-Z0-9]+)/, u = /^http:\/\/weibo\.com\/(?:u\/)?([a-zA-Z0-9]+)/, v = /^http:\/\/weibo\.com\/([a-zA-Z0-9]+)\/profile/;
        var w = function() {
            return (new a).outer(function(a) {
                var b = window.localStorage;
                if (b && b.getItem("wbim_phiz") && z.parse(b.getItem("wbim_phiz")).version === "2.1.1") {
                    var c = z.parse(b.getItem("wbim_phiz"));
                    a.resolve(c.maps, c.view)
                } else
                    f("emotions").done(function(c) {
                        var d = {}, e = [], f = {};
                        o(c, function(a) {
                            var b = a.category || (a.hot ? "hot" : "default");
                            d[a.phrase] = a.url;
                            if (f[b] == void 0) {
                                f[b] = e.length;
                                e[f[b]] = {category: b,items: []}
                            }
                            e[f[b]].items.push(a)
                        });
                        var g = {maps: d,view: e,version: "2.1.1"};
                        b.setItem("wbim_phiz", z.stringify(g));
                        a.resolve(d, e)
                    }).fail(function() {
                        a.resolve({}, {})
                    })
            })
        }();
        var x = function() {
            function c(c) {
                return (new a).outer(function(a) {
                    f.list("friendships/groups/members", "users", {list_id: c}).done(function(c) {
                        var d = [], e = [], g = [], h = [];
                        if (c.users) {
                            o(c.users, function(a) {
                                e.push(a.idstr);
                                d.push({uid: a.idstr,nick: a.remark || a.remark_name || a.screen_name,sex: a.gender == "m" ? 1 : 0,status: a.online_status ? "online" : "offline",avatar: a.profile_image_url && a.profile_image_url.split("/")[5],member: -1})
                            })
                        }
                        while (e.length > b) {
                            g.push(e.splice(0, b).join(","))
                        }
                        g.push(e.join(","));
                        o(g, function(a) {
                            h.push(f("friendships/friends/remark_batch", {uids: a}))
                        });
                        i(h).done(function(b) {
                            var c = {};
                            o(b, function(a) {
                                o(a, function(a) {
                                    if (a.remark && a.id) {
                                        c[a.id] = a.remark
                                    }
                                })
                            });
                            o(d, function(a) {
                                if (c[a.uid]) {
                                    a.nick = c[a.uid]
                                }
                            });
                            a.resolve(d)
                        }).fail(a.inject)
                    }).fail(a.inject)
                })
            }
            var b = 40;
            return function(a) {
                var b = [];
                if (a.lists) {
                    o(a.lists, function(a) {
                        b.push({id: a.idstr,name: a.name,count: a.member_count || 0,users: c(a.idstr)})
                    })
                }
                b.push({id: "0",name: "未分组",count: -1,users: c("0")});
                return b
            }
        }();
        var y = {loadAudio: function(b) {
                return (new a).outer(function(a) {
                    h(r, {object_id: b}).done(function(b) {
                        if (b && b.result === 0 && b.result_data && b.result_data.mp3_url) {
                            k(b.result_data.mp3_url).done(a.resolve)
                        }
                    })
                })
            },getFile: function(b) {
                return (new a).outer(function(a) {
                    f("mss/meta_query", {fid: b}, "upload").done(function(c) {
                        if (c) {
                            c.id = b
                        }
                        a.resolve(c)
                    }).fail(a.inject)
                })
            },getMenu: function(b) {
                var c = new a;
                return c.outer(function() {
                    f("messages/menu/show", {uid: b}, "m").done(c.resolve).fail(c.inject)
                })
            },getFullText: function(b) {
                var c = new a;
                return c.outer(function() {
                    f("direct_messages/show_batch", {is_encoded: 1,is_complete: 1,dmids: b}).done(c.resolve).fail(c.inject)
                })
            },getFace: function() {
                return (new a).outer(function(a) {
                    w.done(function(b, c) {
                        a.resolve(c)
                    })
                })
            },parseFace: function(b) {
                return (new a).outer(function(a) {
                    var c = (b || "").match(q);
                    if (c && c.length > 0) {
                        w.done(function(c, d) {
                            var e = {};
                            b = b.replace(q, function(a, b, d) {
                                if (c[b]) {
                                    e[b] = c[b]
                                }
                                return a
                            });
                            a.resolve(e)
                        })
                    } else {
                        a.resolve({})
                    }
                })
            },parseProfile: function(b) {
                return (new a).outer(function(a) {
                    var c = (b || "").match(u);
                    if (!c) {
                        c = (b || "").match(v)
                    }
                    if (c && c.length) {
                        var d = c[1];
                        if (isNaN(d)) {
                            f("users/domain_show", {domain: d,is_encoded: 1}).done(function(b) {
                                if (b) {
                                    var d = b;
                                    var e = {type: "profile",avatar: d.avatar_large || d.avatar_hd,display_name: d.name || d.screen_name || "",summary: d.verified_reason || d.description || "",short_url: c[0]};
                                    a.resolve(e)
                                } else {
                                    a.resolve({})
                                }
                            }).fail(function() {
                                a.resolve({})
                            })
                        } else {
                            f("users/show_batch", {uids: d,is_encoded: 1}).done(function(b) {
                                if (b.users && b.users[0]) {
                                    var d = b.users[0];
                                    var e = {type: "profile",avatar: d.avatar_large || d.avatar_hd,display_name: d.name || d.screen_name || "",summary: d.verified_reason || d.description || "",short_url: c[0]};
                                    a.resolve(e)
                                } else {
                                    a.resolve({})
                                }
                            }).fail(function() {
                                a.resolve({})
                            })
                        }
                    } else {
                        a.resolve({})
                    }
                })
            },parseWeibo: function(b) {
                return (new a).outer(function(a) {
                    var c = (b || "").match(s);
                    if (!c) {
                        c = (b || "").match(t)
                    }
                    if (c && c.length) {
                        var d = c[2];
                        var e = {ids: p.decode(d),is_encoded: 1};
                        f("statuses/show_batch", e).done(function(b) {
                            if (b.statuses && b.statuses[0]) {
                                var d = b.statuses[0];
                                var e = d.user && (d.user.name || d.user.screen_name) || "";
                                var f = {type: "weibo",img_url: d.bmiddle_pic || d.original_pic || d.user && d.user.avatar_large,display_name: "转发&nbsp;" + e + "&nbsp;的微博",user_name: e,summary: d.text || "",short_url: c[0],object_type: "status",object_id: d.idstr};
                                if (!f.display_name && !f.summary) {
                                    a.resolve({})
                                } else {
                                    a.resolve(f)
                                }
                            } else {
                                a.resolve({})
                            }
                        }).fail(function() {
                            a.resolve({})
                        })
                    } else {
                        a.resolve({})
                    }
                })
            },analysisMessage: function(b) {
                return (new a).outer(function(a) {
                    if (m.isString(b)) {
                        b = {msg: b}
                    }
                    j([i([n.parseShortUrl(b.msg)], ["url", "ext", "topic"], d).progress(function(a, c, d) {
                            if (a == "url" && m.isEmptyObject(c)) {
                                d.push(n.parseExt(b.card, b.uid, b.mid))
                            } else if (a == "ext" && m.isEmptyObject(c)) {
                                d.push(n.parseTopic(b.msg))
                            }
                        }), y.parseProfile(b.msg), y.parseWeibo(b.msg), y.parseFace(b.msg)], ["card", "profile", "weibo", "face"]).done(function(b) {
                        b.card = m.isEmptyObject(b.card.url) ? m.isEmptyObject(b.card.ext) ? b.card.topic : b.card.ext : b.card.url;
                        a.resolve(b)
                    }).fail(a.inject)
                })
            },pushEvent: function(a, c, d) {
                return e("messages_event_create", {receiver_id: d,sender_id: b("uid"),type: a,key: c})
            },getGroups: function() {
                return (new a).outer(function(a) {
                    f("friendships/groups").done(function(b) {
                        a.resolve(x(b))
                    }).fail(a.inject)
                })
            }};
        return y
    });
    f("open.GroupChat", function() {
        var a = g("async.deferred");
        var b = 5 * 1e3, c = g("async.jsonp"), d = g("util.type"), e = "http://api.weibo.com/", f = g("base.Config")("im_source");
        var h = g("util.array").forEach;
        var i = g("connect.Unreader");
        var j = g("open.Public");
        var k = function(g, h) {
            var i = new a, j = {timeout: b}, h = h || {};
            h.source = f;
            if (d.is(h.timeout) == "Number") {
                j.timeout = h.timeout;
                delete j.timeout
            }
            return i.outer(function() {
                c(e + g + ".json", h, j).done(function(a) {
                    var b = a.code ? a.data : a;
                    i.resolve(b)
                }).fail(i.inject)
            })
        };
        var l = {getGroup: function(b, c, d, e) {
                var f = new a;
                return f.outer(function() {
                    k("groupchat/query", {id: b,query_member: c || 0,query_member_count: d || 0,group_ts: e || 0}).done(f.resolve).fail(f.inject)
                })
            },createGroup: function(b, c, d, e, f) {
                var g = new a;
                return g.outer(function() {
                    k("create", {members: b.join(","),name: c || "",max_member: d || 100,validate_type: e || 0,check_friend: f || 1}).done(g.resolve).fail(g.inject)
                })
            },getMultiGroup: function(b, c, d) {
                var e = new a;
                return e.outer(function() {
                    k("groupchat/query_multi", {id: b.join(","),query_member: c || 0,addsession_groups: d || 0}).done(e.resolve).fail(e.inject)
                })
            },getJoinGroups: function(b, c, d) {
                var e = new a;
                return e.outer(function() {
                    k("groupchat/query_join_groups", {member: b || 0,query_member_count: c || 0,addsession_only: d || 0,is_pc: 1}).done(e.resolve).fail(e.inject)
                })
            },getMembers: function(b, c) {
                var d = new a;
                return d.outer(function() {
                    k("groupchat/query_members", {id: b || 0,count: c || 0}).done(d.resolve).fail(d.inject)
                })
            },isMembers: function(b) {
                var c = new a;
                return c.outer(function() {
                    k("groupchat/is_members", {gids: b.join(",")}).done(c.resolve).fail(c.inject)
                })
            },getUnread: function() {
                var b = new a;
                return b.outer(function() {
                    k("groupchat/query_unread", {query_push: 1,is_pc: 1}).done(function(a) {
                        var c = {};
                        var d = a.groups;
                        if (d) {
                            h(d, function(a, b) {
                                if (a.push == 1) {
                                    c[a.id] = a["unread"]
                                }
                            });
                            i.set(c);
                            b.resolve(a)
                        }
                    }).fail(b.inject)
                })
            },getUnreadTotal: function() {
                var b = new a;
                return b.outer(function() {
                    k("groupchat/query_unread_total", {}).done(b.resolve).fail(b.inject)
                })
            },getMessages: function(b, c, d, e) {
                var f = new a;
                var g = {id: b || 0,count: c || 10,is_encoded: 1,convert_emoji: 1,is_pc: 1};
                if (d) {
                    g.max_mid = d
                }
                return f.outer(function() {
                    k("groupchat/query_messages", g).done(f.resolve).fail(f.inject)
                })
            },getUserSettings: function(b) {
                var c = new a;
                return c.outer(function() {
                    k("groupchat/query_user_settings", {id: b || 0,is_pc: 1}).done(c.resolve).fail(c.inject)
                })
            },getExitFlags: function(b) {
                var c = new a;
                return c.outer(function() {
                    k("groupchat/query_exit_flags", {gids: b || 0}).done(c.resolve).fail(c.inject)
                })
            },getAffiliatedGroup: function(b) {
                var c = new a;
                return c.outer(function() {
                    k("groupchat/query_affiliated_group", {page_id: b || 0}).done(c.resolve).fail(c.inject)
                })
            },sendMessage: function(b, c, d) {
                var e = new a;
                return e.outer(function() {
                    k("groupchat/send_message", {id: b || 0,content: c || "",fids: d || []}).done(function(a) {
                        if (a.result) {
                            e.resolve(a)
                        } else {
                            e.inject(a)
                        }
                    }).progress(function(a) {
                        e.notify(a)
                    }).fail(e.inject)
                })
            },clearUnread: function(b) {
                var c = new a;
                return c.outer(function() {
                    k("groupchat/clear_unread", {id: b || 0}).done(function(a) {
                        c.resolve(a);
                        i.clearGroup(b)
                    }).fail(c.inject)
                })
            },exitGroup: function(b) {
                var c = new a;
                return c.outer(function() {
                    k("groupchat/exit", {id: b || 0}).done(function(a) {
                        c.resolve(a)
                    }).fail(c.inject)
                })
            },getGroupSetting: function(b) {
                var c = new a;
                return c.outer(function() {
                    k("groupchat/query_user_settings", {id: b || 0,is_pc: 1}).done(function(a) {
                        c.resolve(a)
                    }).fail(c.inject)
                })
            },getNoticeSetting: function(b) {
                var c = new a;
                return c.outer(function() {
                    j("notice_center/discussion_group/push_settings", {dgid: b || 0,call_from: "pc"}).done(function(a) {
                        c.resolve(a)
                    }).fail(c.inject)
                })
            },setNoticeSetting: function(b, c) {
                var d = new a;
                return d.outer(function() {
                    j("notice_center/discussion_group/set_push_settings", {dgid: b || 0,call_from: "pc","switch": c}).done(function(a) {
                        d.resolve(a)
                    }).fail(d.inject)
                })
            },updateGroupSetting: function(b, c, d, e) {
                var f = new a;
                var g = {id: b || 0};
                if (c !== "") {
                    g.push = c
                }
                if (d !== undefined) {
                    g.addsession = d
                }
                if (e !== undefined) {
                    g.filterfeed = e
                }
                return f.outer(function() {
                    k("groupchat/update_user_settings", g).done(function(a) {
                        f.resolve(a)
                    }).fail(f.inject)
                })
            },deleteGroupChat: function(b, c) {
                var d = new a;
                var e = {id: b || 0};
                if (c !== undefined) {
                    e.mids = c.join(",")
                }
                return d.outer(function() {
                    k("groupchat/delete_message", e).done(function(a) {
                        d.resolve(a)
                    }).fail(d.inject)
                })
            },queryMessage: function(b, c) {
                var d = new a;
                return d.outer(function() {
                    k("groupchat/query_message", {id: b,mid: c,convert_emoji: 1}).done(d.resolve).fail(d.inject)
                })
            }};
        return l
    });
    f("control.Center", function() {
        var a = g("async.deferred"), b = g("control.Root"), c = g("control.Component"), f = g("base.Config"), h = g("control.Protocol"), i = g("control.Setting"), j = g("control.Watch"), k = g("control.Register"), l = g("connect.Connect"), m = g("connect.Sender"), n = g("connect.Notifier"), o = g("connect.Unreader"), p = g("connect.Newser"), q = g("connect.Messager"), r = g("connect.GroupMessager"), s = g("open.GroupChat"), t = g("memory.Storage"), u = g("plugin.apiCache"), v = g("plugin.apiQueue"), w = g("plugin.upload"), x = g("plugin.tip"), y = g("util.object"), z = g("util.delay"), A = g("util.ready");
        var B = function() {
            var x = null, B = new a, C = new a, D = new a, E = e, F = e, G = h, H = {}, I = {}, J = {}, K = {isReady: e,status: "disConnected",register: function() {
                },shortOption: function() {
                }}, L = {};
            if (v) {
                G = v(G)
            }
            K.onStatusChange = function(a) {
                D.progress(a)
            };
            K.onStatusChange.unbind = function(a) {
                D.unProgress(a)
            };
            K.onConnect = function(a, b) {
                C.done(function() {
                    a()
                });
                if (b) {
                    K.startConnect()
                }
            };
            K.setStatus = function(a) {
                x.setStatus(a)
            };
            K.reConnectNow = function() {
                x.setStatus("disconnected");
                x.reConnectNow()
            };
            K.getStatus = function() {
                return x.getStatus()
            };
            K.onNotice = function(a) {
                n.on(a)
            };
            K.onNotice.unbind = function(a) {
                n.unbind(a)
            };
            K.onNews = function(a) {
                p.on(a)
            };
            K.onNews.unbind = function(a) {
                p.unbind(a)
            };
            K.onMessage = function(a) {
                q.on(a)
            };
            K.onMessage.unbind = function(a) {
                q.unbind(a)
            };
            K.onGroupMessage = function(a) {
                r.on(a)
            };
            K.onGroupMessage.unbind = function(a) {
                r.unbind(a)
            };
            K.startConnect = function() {
                if (!E && x) {
                    x.start()
                } else {
                    E = d
                }
            };
            K.onUnreadChange = function(a) {
                o.on(a)
            };
            K.onUnreadChange.unbind = function(a) {
                o.unbind(a)
            };
            H.Deferred = g("async.deferred");
            H.queue = g("async.queue");
            H.parallel = g("async.parallel");
            H.jsonp = g("async.jsonp");
            H.delay = g("async.delay");
            H.loader = g("async.loader");
            K.tool = H;
            if (G) {
                K.anyWhere = function(a) {
                    C.done(a)
                };
                G.public = g("open.Public");
                G.process = I = g("open.Process");
                G.group = g("open.GroupChat");
                G.getUnread = o.get;
                K.api = G;
                K.public = G.public;
                K.process = G.process;
                K.group = G.group;
                K.getUnread = o.get
            }
            if (j) {
                K.watch = function(a, b) {
                    return j.watch(a, function() {
                        var a = [].slice.call(arguments);
                        if (b && b.apply) {
                            try {
                                b.apply(null, a)
                            } catch (c) {
                            }
                        }
                    })
                };
                K.publish = j.publish
            }
            K.onReady = function(a) {
                B.done(a)
            };
            K.getTime = function() {
                return (new Date).getTime()
            };
            if (k) {
                L = y.assign({}, K);
                var M = k(L);
                K.register = M.register;
                K.shortOption = M.shortOption
            }
            A(function() {
                K.isReady = d;
                L.root = K.root = b.create();
                x = new l;
                m.inject(x);
                n.inject(x);
                o.inject(x);
                x.onStatusChange(function(b, c) {
                    if (!F && b == "connected") {
                        F = d;
                        L.getTime = K.getTime = function() {
                            return x.getServerTime()
                        };
                        B.done(function(a, b) {
                            if (!b) {
                                C.resolve(G, H, I)
                            }
                        })
                    }
                    if (b == "disconnected") {
                        F = e;
                        if (C.state() == "injected") {
                            C = new a
                        }
                    }
                    L.status = K.status = b;
                    D.notify(b, c)
                });
                if (x.getUid()) {
                    t(x.getUid()).done(function(a) {
                        L.storage = H.storage = a;
                        p.start(x.getUid(), a);
                        if (i) {
                            i(a).done(function(a) {
                                if (u) {
                                    G = u(G, a)
                                }
                                L.initData = K.initData = a;
                                B.resolve(K, false);
                                C.done(function() {
                                    h.getSetting().done(function(a) {
                                        n.push("setting", {act: "refresh",data: a})
                                    });
                                    h.getRecent().done(function(a) {
                                        n.push("recent", {act: "refresh",list: a})
                                    })
                                });
                                c.load()
                            }).progress(function(a) {
                                if (K.initData) {
                                    if (a.type == "recent") {
                                        L.initData.recent = K.initData.recent = a.data
                                    } else if (a.type == "setting") {
                                        L.initData.setting = K.initData.setting = a.data
                                    } else if (a.type == "chat") {
                                        K.initData.chat = K.initData.chat = a.data
                                    }
                                }
                            })
                        } else {
                            B.resolve(K, false)
                        }
                        if (E) {
                            x.start()
                        } else if (f("auto_connect") !== e) {
                            z(K.startConnect, parseInt(f("auto_connect")) || 0)
                        }
                    });
                    if (w) {
                        H.upload = w
                    }
                } else {
                    B.resolve({message: "Domain Error OR No Login!"}, true)
                }
                s.getUnread().done()
            });
            K._onSend = m.on;
            return K
        }();
        if (x) {
            x(B)
        }
        return B
    });
    var A = g("base.Config")("im_space"), B = g("control.Center"), C = g("control.Watch");
    if (b[A] && b[A]._inject) {
        b[A]._inject(B);
        if (C) {
            C.setAll(b[A]._cache)
        }
    }
    b[A] = B;
    b.$WBIM = b.$WBIM || {};
    if (!b.$WBIM.register) {
        b.$WBIM.register = B.register
    }
    (function() {
        var a = b.__PubSub__;
        var f = g("control.Watch"), h = g("connect.Unreader");
        g("util.ready")(function() {
            g("util.addEvent")(c.body, "click", function(a) {
                var b = a.target || a.srcElement || c;
                if (b && b.getAttribute) {
                    if (b.nodeType == 3) {
                        b = b.parentNode
                    }
                    var d, e, h = b, i = b.getAttribute && b.getAttribute("action-type"), j = b.getAttribute && b.getAttribute("data-im");
                    while (b && !j && !(i && /webim\./.test(i)) && (b.tagName.toUpperCase() != "A" || /javascript/.test(b.href)) && b != c.body) {
                        b = b.parentNode;
                        if (b && b.getAttribute) {
                            i = b.getAttribute("action-type");
                            j = b.getAttribute("data-im")
                        }
                    }
                    if (j || i && i !== "webim.conversation" && /webim\./.test(i)) {
                        d = j || i.split(".")[1];
                        if (d) {
                            e = b.getAttribute("data-im-param") || b.getAttribute("action-data") || "";
                            e = e ? g("util.string").parseQuery(e) : {};
                            f.publish("IM:" + d, e, {target: b,item: h});
                            if (a.preventDefault) {
                                a.preventDefault()
                            }
                            if (a.stopPropagation) {
                                a.stopPropagation()
                            }
                            a.returnValue = false;
                            a.cancelBubble = true
                        }
                    }
                }
            })
        });
        f.watch("IM:class", function(a) {
            var b = g("control.Root").create();
            b.className += " " + a
        });
        f.watch("IM:connect", function() {
            g("control.Center").startConnect()
        });
        var i = e, j = d;
        f.watch("IM:display", function(b) {
            j = b != "hide";
            if (i) {
                a.publish("webim.display", {show: j})
            }
        });
        f.watch("IM:uiReady", function(b) {
            i = !!b;
            a.publish("webim.display", {show: j})
        });
        f.watch("IM:tipClick", function(a) {
            if (!i || !j) {
                location.href = p.box
            } else {
                f.publish("IM:conversation", a)
            }
        });
        window.STK = window.STK || {};
        STK.webim = STK.webim || {};
        STK.webim.ui = STK.webim.ui || {};
        STK.webim.ui.uiEvent = STK.webim.ui.uiEvent || {};
        a.subscribe("webim.display", function(a) {
            if (a && a.show) {
                STK.webim.ui.uiEvent.fire = function(a, b) {
                    if (a == "ui.user.click") {
                        f.publish("IM:conversation", b || {})
                    }
                }
            } else {
                STK.webim.ui.uiEvent.fire = null
            }
        })
    })();
    f("rule.CardEvent", function() {
        function k(a, b) {
            a.className = b.replace(/\|/g, " ")
        }
        var a = {video: d,article: d};
        var c = g("control.Center"), e = g("control.Watch"), f = g("open.Process"), h = g("util.object");
        var i = 0, j = {};
        e.watch("IM:card.audio.btn", function(a, b) {
            if (b && b.target) {
                var c = b.target, d = c.getAttribute("data-im-audio-id");
                if (d && j[d]) {
                    j[d].pause();
                    k(c, a.class1);
                    delete j[d]
                } else {
                    c.setAttribute("data-im-audio-id", ++i);
                    f.loadAudio(a.id).done(function(b) {
                        j[i] = b;
                        k(c, a.class2)
                    })
                }
            }
        });
        e.watch("IM:card.like", function(a, b) {
            if (b && b.target) {
                var d = b.target, e = a.liked == "true" ? "destory" : "create", f = a.id, g = a.type;
                c.api.like(f, g, e).done(function(b) {
                    if (b.success) {
                        var c = d.getElementsByTagName("i")[0], e = d.getElementsByTagName("i")[1], f = parseInt(e.innerHTML);
                        if (a.liked == "true") {
                            f--;
                            k(c, a.class2)
                        } else {
                            f++;
                            k(c, a.class1)
                        }
                        e.innerHTML = f;
                        e.style.display = f === 0 ? "none" : "";
                        a.liked = a.liked == "true" ? "false" : "true";
                        d.setAttribute("data-im-param", h.toQuery(a))
                    }
                })
            }
        });
        e.watch("IM:card.video.show", function(a, b) {
            window.open(decodeURIComponent(a.url), "_blank")
        });
        e.watch("IM:card.article.show", function(a, b) {
            window.open(decodeURIComponent(a.url), "_blank")
        });
        e.watch("IM:card.show", function(a, c) {
            if (c) {
                b.open(decodeURIComponent(a.url), "_blank")
            }
        });
        e.watch("IM:redBagFly.click", function(a, c) {
            b.open(decodeURIComponent(a.url), "_blank")
        });
        e.watch("IM:card.pri.click", function(a, c) {
            b.open(decodeURIComponent(a.url), "_blank")
        });
        e.watch("IM:invite.response", function(a, b) {
            if (b) {
                c.api.inviteResponse(a.id).done(function() {
                    var c = b.target.parentNode.childNodes;
                    for (var d = 0, e = c.length; d < e; d++) {
                        if (c[d].className == a.class1) {
                            c[d].style.display = "none"
                        } else if (c[d].className == a.class2) {
                            c[d].style.display = ""
                        }
                    }
                })
            }
        })
    })
})();
