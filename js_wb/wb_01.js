function _siteId(prefix){
		var id = (new Date()).getTime() + Math.floor(Math.random() * 100000);
		return prefix ? prefix + '' + id : id;
	}
	function encodeFormData(data){
		var pairs = [], regexp = /%20/g;

		var value;
		for(var key in data){
			value = data[key].toString();

			// encodeURIComponent encodes spaces as %20 instead of "+"
			pairs.push(encodeURIComponent(key).replace(regexp, '+') +
				'=' + encodeURIComponent(value).replace(regexp, '+'));
		}

		return pairs.join('&');
	}