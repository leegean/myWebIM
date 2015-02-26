function _siteId(prefix) {
	var id = (new Date()).getTime() + Math.floor(Math.random() * 100000);
	return prefix ? prefix + '' + id : id;
}
function encodeFormData(data) {
	var pairs = [], regexp = /%20/g;

	var value;
	for ( var key in data) {
		value = data[key].toString();

		// encodeURIComponent encodes spaces as %20 instead of "+"
		pairs.push(encodeURIComponent(key).replace(regexp, '+') + '='
				+ encodeURIComponent(value).replace(regexp, '+'));
	}

	return pairs.join('&');
}
function talkMsg(it) {
	var out = '<ul class="talk-msg">';
	var _dt;
	for ( var k = 0; k < it.data.length; k++) {
		var msg_data = it.data[k];
		var date_time = msg_data.created_at;
		var page_info = msg_data.page_info;
		if (date_time != _dt) {
			out += '<li><time class="date-talk">' + (date_time) + '</time>';
		}
		if (!msg_data.page_info) {
			if (msg_data.ext_text && msg_data.ext_text.type
					&& msg_data.self == 0) {
				out += '<section class="guest-talk" data-chatid="'
						+ (k)
						+ '" data-uid="'
						+ (msg_data.sender_id)
						+ '"><a class="avatar-talk" href="'
						+ (location.origin)
						+ '/'
						+ (msg_data.sender_id)
						+ '" title=""><img src="'
						+ (msg_data.sender.profile_image_url)
						+ '" class=""></a><div class="content-talk invite content-wb">';
				if (msg_data.ext_text.type == "attention") {
					out += '<div class="t"><strong class="t_l1">关注邀请</strong><span class="t_l2">'
							+ (msg_data.text)
							+ '</span></div><div class="op flex_c">';
					if (msg_data.ext_text.state == "0"
							|| msg_data.ext_text.state == "3") {
						out += '<span class="c_g J-agree J-attention">加关注</span><span class="J-ignore J-attention">忽略</span>';
					}
					if (msg_data.ext_text.state == "1") {
						out += '<span class="J-grey">已关注</span>';
					}
					if (msg_data.ext_text.state == "4") {
						out += '<span class="J-grey">已忽略</span>';
					}
					out += '</div>';
				}
				if (msg_data.ext_text.type == "game") {
					out += '<div class="t"><strong class="t_l1">游戏邀请</strong><span class="t_l2">'
							+ (msg_data.text)
							+ '</span></div><div class="op flex_c">';
					if (msg_data.ext_text.state == "0"
							|| msg_data.ext_text.state == "3") {
						out += '<span class="c_g J-agree J-game">接受</span><span class="J-ignore J-game">忽略</span>';
					}
					if (msg_data.ext_text.state == "1") {
						out += '<span class="J-grey">已接受</span>';
					}
					if (msg_data.ext_text.state == "4") {
						out += '<span class="J-grey">已忽略</span>';
					}
					out += '</div>';
				}
				if (msg_data.ext_text.type == "close_friend") {
					out += '<div class="t"><strong class="t_l1">密友邀请</strong><span class="t_l2">'
							+ (msg_data.text)
							+ '</span></div><div class="op flex_c">';
					if (msg_data.ext_text.state == "0"
							|| msg_data.ext_text.state == "3") {
						out += '<span class="c_g J-agree J-cfriend">加密友</span><span class="J-ignore J-cfriend">忽略</span>';
					}
					if (msg_data.ext_text.state == "1") {
						out += '<span class="J-grey">已处理</span>';
					}
					if (msg_data.ext_text.state == "4") {
						out += '<span class="J-grey">已忽略</span>';
					}
					out += '</div>';
				}
				out += '</div></section>';
			} else {
				if (msg_data.attachment) {
					out += ' ';
					for ( var j in msg_data.attachment) {
						if (j) {
							var file = msg_data.attachment[j];
							out += '<section class="';
							if (msg_data.self == 0) {
								out += 'guest-talk';
							} else {
								out += 'me-talk';
							}
							out += '" data-chatid="'
									+ (k)
									+ '" data-uid="'
									+ (msg_data.sender_id)
									+ '"><a class="avatar-talk" href="'
									+ (location.origin)
									+ '/'
									+ (msg_data.sender_id)
									+ '" title=""><img src="'
									+ (msg_data.sender.profile_image_url)
									+ '"></a><div class="content-talk content-wb">';
							if (file
									&& file.extension
									&& [ 'jpg', 'jpeg', 'png' ]
											.indexOf(file.extension
													.toLowerCase()) != -1) {
								out += '<a class="J-imgZoom" data-href="'
										+ (file.thumbnail_600)
										+ '" href="javascript:;"><img class="img_share" src="'
										+ (file.thumbnail_60) + '"/></a>';
							} else {
								out += '<p>'
										+ (msg_data.text)
										+ '：'
										+ (file.filename)
										+ '</p><p><small>（手机网页版不支持此格式的附件下载或预览）</small></p>';
							}
							out += '</div></section>';
						}
					}
				} else {
					out += '<section class="';
					if (msg_data.self == 0) {
						out += 'guest-talk';
					} else {
						out += 'me-talk';
					}
					out += '" data-chatid="'
							+ (k)
							+ '"><a class="avatar-talk" href="'
							+ (location.origin)
							+ '/'
							+ (msg_data.sender_id)
							+ '" title=""><img src="'
							+ (msg_data.sender.profile_image_url)
							+ '" class=""></a><div class="content-talk content-wb"><p>'
							+ (msg_data.text) + '</p></div></section>';
				}
			}
		} else {
			out += '    ';
			if (page_info.type != 5 && page_info.type != 4) {
				out += '            ';
				if (msg_data.self == 0) {
					out += '<section class="guest-talk"><a class="avatar-talk" href="'
							+ (location.origin)
							+ '/'
							+ (msg_data.sender_id)
							+ '" title=""><img src="'
							+ (msg_data.sender.profile_image_url)
							+ '" alt="" class=""></a><div class="tips_share">向你分享了一个链接</div></section>';
				} else {
					out += '<section class="me-talk"><div class="tips_share">向Ta分享了一个链接</div><a class="avatar-talk" href="'
							+ (location.origin)
							+ '/'
							+ (msg_data.sender_id)
							+ '" title=""><img src="'
							+ (msg_data.sender.profile_image_url)
							+ '" alt="" class=""></a></section>';
				}
			}
			if (page_info.type == 5) {
				out += '<section class="public-talk card" data-chatid="' + (k)
						+ '" data-uid="' + (msg_data.sender_id) + '" source="'
						+ (page_info.source) + '">';
				for ( var i = 0, len = page_info.cards.length; i < len; i++) {
					console.log("11");
					var card = page_info.cards[i];
					if (i == 0) {
						out += '<a href="'
								+ (card.page_url)
								+ '"><div class="tit_wrap"><span style="background-image:url(\''
								+ (card.page_pic)
								+ '\');"></span><div class="tit">'
								+ (card.content1) + '</div></div></a>';
					} else {
						out += '<a href="'
								+ (card.page_url)
								+ '"><div class="item-Infor"><!--资讯列表--><span class="avatar" style="background-image:url(\''
								+ (card.page_pic)
								+ '\');"></span><div class="tit">'
								+ (card.content1) + '</div></div></a>';
					}
				}
				out += '</section> ';
			} else if (page_info.type == 4) {
				out += '<section class="public-talk card"  data-chatid="'
						+ (k)
						+ '" data-uid="'
						+ (msg_data.sender_id)
						+ '" source="'
						+ (page_info.source)
						+ '"><a href="'
						+ (page_info.page_url)
						+ '"><div class="article_tit">'
						+ (page_info.content1)
						+ '</div><div class="tit_wrap"><span style="background-image:url('
						+ (page_info.page_pic) + ')"></span></div>';
				if (page_info.content2) {
					out += '<div class="article_txt">' + (page_info.content2)
							+ '</div>';
				}
				out += '<a href="'
						+ (page_info.page_url)
						+ '"><div class="article_more dec_arr">阅读全文</div></a></a></section>';
			} else if (page_info.type == 2) {
				out += '<section class="public-talk card" data-chatid="' + (k)
						+ '" data-uid="' + (msg_data.sender_id) + '" source="'
						+ (page_info.source) + '"><a href="'
						+ (page_info.page_url) + '"><div class="';
				if (page_info.object_type) {
					out += (page_info.object_type);
				}
				out += ' card_c" page_id="' + (page_info.page_id)
						+ '"><span class="avatar" pageurl="'
						+ (page_info.page_url)
						+ '" style="background-image:url('
						+ (page_info.page_pic)
						+ ');"></span><div class="tit ellipsis">'
						+ (page_info.page_title)
						+ '</div><div class="txt ellipsis_r2">'
						+ (page_info.content2) + '</div></div></a></section>';
			} else if (page_info.type == 10) {
				out += '<section class="public-talk card" data-chatid="'
						+ (k)
						+ '" data-uid="'
						+ (msg_data.sender_id)
						+ '" source="'
						+ (page_info.source)
						+ '"><a href="'
						+ (page_info.page_url)
						+ '"><div class="'
						+ (page_info.object_type)
						+ ' card_c" page_id="'
						+ (page_info.page_id)
						+ '"><span class="avatar" pageurl="'
						+ (page_info.page_url)
						+ '" style="background-image:url('
						+ (page_info.page_pic)
						+ ');"></span><div class="tit ellipsis_r2">'
						+ (page_info.page_title)
						+ '</div><div class="txt ellipsis"><span class="c_o"><strong>'
						+ (page_info.content2) + '</strong></span>';
				if (page_info.object_type == "product" && page_info.content3) {
					out += '  &nbsp; <del class="c_lgray"><strong>￥'
							+ (page_info.content3) + '</strong></del>';
				}
				out += '</div></div></a></section>';
			} else if (page_info.type == 1) {
				out += '<section class="public-talk card" data-chatid="' + (k)
						+ '" data-uid="' + (msg_data.sender_id) + '" source="'
						+ (page_info.source) + '"><a href="'
						+ (page_info.page_url) + '"><div class="'
						+ (page_info.object_type) + ' card_c" page_id="'
						+ (page_info.page_id) + '"><span class="avatar" href="'
						+ (page_info.page_url)
						+ '" style="background-image:url(\''
						+ (page_info.page_pic) + '\');"></span><ul><li>'
						+ (page_info.content1) + '</li><li>'
						+ (page_info.content2) + '</li><li>'
						+ (page_info.content3)
						+ '</li></ul></div></a></section> ';
			} else {
				out += '<section class="public-talk card" data-chatid="'
						+ (k)
						+ '" data-uid="'
						+ (msg_data.sender_id)
						+ '" source="'
						+ (page_info.source)
						+ '"><a href="'
						+ (page_info.page_url)
						+ '" style="position:absolute;width:100%;height:100%;z-index:1;"></a><div class="'
						+ (page_info.object_type) + ' card_c" page_id="'
						+ (page_info.page_id) + '" jump="'
						+ (page_info.page_url) + '">';
				if (page_info.buttons) {
					for ( var i = 0; i < page_info.buttons.length; i++) {
						if (page_info.buttons[i].type == 'link') {
							out += '<a style="float:right;padding-top:10px;" href="'
									+ (page_info.buttons[i].scheme)
									+ '" data-act-type="hover link"><img data-node="cImgPic" src="'
									+ (page_info.buttons[i].pic)
									+ '" height="24" width="24"> <div data-node="cImgTxt" style="font-size: 0.625rem;color:#3f9bdb;padding-top:3px;" class="txt-xxs">'
									+ (page_info.buttons[i].name)
									+ '</div> </a>';
						}
					}
				}
				out += '<span class="avatar" pageURL="' + (page_info.page_url)
						+ '" style="background-image:url('
						+ (page_info.page_pic) + ');"></span><h3>'
						+ (page_info.page_title) + '</h3><p>'
						+ (page_info.page_desc) + '</p>';
				if (page_info.tips) {
					out += '<p>' + (page_info.tips) + '</p>';
				}
				out += '</div></section> ';
			}
		}
		_dt = date_time;
		if (date_time != _dt) {
			out += '</li>';
		}
	}
	out += '   </div></ul>';
	return out;
}