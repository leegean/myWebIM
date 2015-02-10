            var e = [];
            var f = [];
            var g = 0;
            var h = 0;
            var i = {};
            i.incoming = function(ts, tc, p) {
            	var c = {};
            	c.ts = ts;
            	c.tc = tc;
            	c.p = p;
            	//1423532063013
            	/*i: 1423532063013
            	k: 1094.5
            	l: 55192.5
            	m: 1
            	n: 1094.5
            	o: 55192.5
            	p: 1
            	this: Object
            	Closure
            	c: Object
            	d: 10
            	e: Array[1]
            	f: Array[1]
            	g: 1095
            	h: 55193*/
                        var i = (new Date).getTime();
                        var k = (i - c.tc - c.p) / 2;
                        var l = c.ts - c.tc - k;
                        print(i+'         '+k+'              '+l);
                        e.push(k);
                        f.push(l);
                        if (f.length > 10) {
                            f.shift();
                            e.shift()
                        }
                       /* "ts":1423533572618,"tc":1423533573387,"p":0
                               1423533518631         -27378              26609*/
                        var m = f.length;
                        var n = 0;
                        var o = 0;
                        for (var p = 0; p < m; ++p) {
                            n += e[p];
                            o += f[p]
                        }
                        g = parseInt((n / m).toFixed());
                        h = parseInt((o / m).toFixed());
                        
            };
            i.getTimeOffset = function() {
                return h
            };
            i.getNetworkLag = function() {
                return g
            };
            i.getServerTime = function() {
                return (new Date).getTime() + h
            };
