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
                        var i = (new Date).getTime();
                        var k = (i - c.tc - c.p) / 2;
                        var l = c.ts - c.tc - k;
                        e.push(k);
                        f.push(l);
                        if (f.length > 10) {
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
