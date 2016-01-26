var _UA = navigator.userAgent;
var ISiPhone = !!_UA.match(/iPhone/i);
var ISiOS = ISiPhone || !!_UA.match(/iPad/i);
var ISWP = !!_UA.match(/Windows\sPhone/i);
var ISAndroid = !!_UA.match(/Android/i);
var ISWeixin = !!_UA.match(/MicroMessenger/i);
(function($){
	$.ajaxSetup({
		headers:{
			'content-type':'text/plain; charset=UTF-8'
		},
		converters:{
			'text json':function(data){
				eval('var parseJSON_return = ' + data);
				return parseJSON_return;
			}
		},
		cache:false,
		beforeSend:function(a,b){
			if (b.url.indexOf('no_loadbar') > -1) return;
			var _this = this;
			_this.loadbar_el_timer = setTimeout(function(){
				_this.loadbar_el = WXC.msgload('请稍候');
			}, 900);
		},
		complete:function(a,b){
			if (this.loadbar_el_timer) clearTimeout(this.loadbar_el_timer);
			if (this.loadbar_el) this.loadbar_el.remove();
		},
		error:function(xhr){
			if (xhr.status == 0 || xhr.status == 200) return;
			//WXC.msgtips('系统繁忙！请稍候重试');
		}
	});
		// COOKIE
	$.cookie.defaults = { raw:true, path:'/', expires:30, secure:false };

	// CHECK EXP
	/*		var _baseurl = $('meta[name=wxc-baseurl]').attr('content') || '';
	var _usertoken = $('meta[name=wxc-usertoken]').attr('content') || '';
	if ($.cookie('wxc_preview_mode') == 1){
		$('<div style="z-index:999999;position:fixed;top:0;left:0;right:0;background-color:rgba(255,255,255,0.4);color:red;padding:4px;text-align:center;">预览模式</div>').appendTo(document.body);
	}else{
		$.getJSON((window.ACT_2HURL||_baseurl) + 'check_limit.dx?jsoncallback=?&no_loadbar', {usertoken:_usertoken}, function(data){
			if (!!data && !!data.data && ~~data.data.limited > 0){
				location.href = _baseurl + 's/reqlimited.dx?url=' + encodeURIComponent(location.href) + '&type=' + data.data.limited + '&usertoken=' + _usertoken;
			}
		});
	}*/
})(jQuery);
// URL参数获取
var urlp = function(key, qstring){
	qstring = qstring || window.location.search;
	if (!qstring) return (!key || key == '*') ? {} : null;
	qstring = qstring.replace(/%20/g, ' ');
	qstring = qstring.substr(1); // remove ? or #
	var param = qstring.split('&');
	var map = new Object();
	for (var i = 0, j = param.length; i < j; i++){ var pl=param[i].split('='); map[pl[0]] = pl[1]; }
	return (!key || key == '*') ? map : map[key];
};
// 正则验证工具
var regex = {
	email:function(val){ return /^[a-z0-9._%-]+@[a-z0-9.-]+\.[a-z]{2,4}$/i.test(val); },
	mobile:function(val){ return /^(13[0-9]|15[0-9]|18[0-9]|177|170)\d{8}$/.test(val); }
};

var WXC = WXC || {};
// 下拉刷新
(function($,window,document,undefined){var win=$(this),st=win.scrollTop()||window.pageYOffset,called=false;var hasTouch=function(){return !!("ontouchstart" in window)||!!("onmsgesturechange" in window)};var handlers={};var addHandler=function(name,fn){win.on(name,fn);handlers[name]=fn};var removeHandler=function(name,fn){win.off(name,handlers[name]);delete handlers[name]};var removeHandlers=function(){for(var name in handlers){removeHandler(name)}};var methods={init:function(options){return this.each(function(){var $this=$(this),settings=$this.data("hook");if(typeof(settings)==="undefined"){var defaults={reloadPage:true,dynamic:true,textRequired:false,scrollWheelSelected:false,swipeDistance:50,loaderClass:"hook-loader",spinnerClass:"hook-spinner",loaderTextClass:"hook-text",loaderText:"Reloading...",reloadEl:function(){}};settings=$.extend({},defaults,options);$this.data("hook",settings)}else{settings=$.extend({},settings,options)}if(settings.dynamic===true){var loaderElem="<div class="+settings.loaderClass+">";loaderElem+="<div class="+settings.spinnerClass+"/>";loaderElem+="</div>";var spinnerTextElem="<span class="+settings.loaderTextClass+">"+settings.loaderText+"</span>";$this.append(loaderElem);if(settings.textRequired===true){$this.addClass("hook-with-text");$this.append(spinnerTextElem)}}if(!hasTouch()){if(settings.scrollWheelSelected===true){addHandler("mousewheel",function(event,delta){methods.onScroll($this,settings,delta)})}else{addHandler("scroll",function(){methods.onScroll($this,settings)})}}else{var lastY=0,swipe=0;addHandler("touchstart",function(e){lastY=e.originalEvent.touches[0].pageY});addHandler("touchmove",function(e){swipe=e.originalEvent.touches[0].pageY+lastY;st=$(this).scrollTop();if(swipe<settings.swipeDistance){e.preventDefault()}if(swipe>settings.swipeDistance&&lastY<=40){methods.onSwipe($this,settings)}});addHandler("touchend",function(){swipe=0})}})},onScroll:function(el,settings,delta){st=win.scrollTop();if(settings.scrollWheelSelected===true&&(delta>=150&&st<=0)){if(called===false){methods.reload(el,settings);called=true}}if(settings.scrollWheelSelected===false&&st<=0){if(called===false){methods.reload(el,settings);called=true}}},onSwipe:function(el,settings){if(st<=0){methods.reload(el,settings)}},reload:function(el,settings){el.show();el.animate({"marginTop":"0px"},200);el.delay(500).slideUp(200,function(){if(settings.reloadPage){window.location.reload(true)}called=false});if(!settings.reloadPage){settings.reloadEl()}},destroy:function(){removeHandlers();return $(this).each(function(){var $this=$(this);$this.empty();$this.removeData("hook")})}};$.fn.hook=function(){var method=arguments[0];if(methods[method]){method=methods[method];arguments=Array.prototype.slice.call(arguments,1)}else{if(typeof(method)==="object"||!method){method=methods.init}else{$.error("Method "+method+" does not exist on jQuery.pluginName");return this}}return method.apply(this,arguments)}})(jQuery,window,document);
$(document).ready(function(){
	$(document.body).hook({ swipeDistance:100 });
});
// 提示
WXC.msgtips = function(msg, timeout){
	var msg = $('<div class="msgtips"><div>' + msg + '</div></div>').appendTo(document.body);
	msg.animate({ 'margin-top':-9 }, 200);
	setTimeout(function(){ msg.remove(); }, timeout || 2222);
};
WXC.msgload = function(msg){
	var msg = $('<div class="msgtips loading"><div>' + (msg||'请稍候') + '</div></div>').appendTo(document.body);
	return msg;
};
WXC.back = function(){
	if (!!!document.referrer && WeixinJSBridge) WeixinJSBridge.call('closeWindow');
	else history.go(-1);
};
// 消息框
WXC.msgbox = function(msg, title, backdrop){
	title = title || '提示';
	if ($.type(backdrop) == 'undefined') backdrop = true;
	var _wrap = $('<div class="msgbox-wrap"><div class="msgbox"><h4></h4><div class="msg"></div></div>' + (backdrop === true ? '<div class="modal-backdrop"></div>' : '')  + '</div>').appendTo(document.body);
	_wrap.find('h4').text(title);
	_wrap.find('.msg').html(msg);
	return _wrap;
};
// 分享框
WXC.sharebox = function(msg){
	var _wrap = $('<div class="sharebox-wrap"><div class="sharebox"><span class="glyphicon glyphicon-arrow-up arrow"></span><p></p></div><div class="modal-backdrop"></div></div>').appendTo(document.body);
	_wrap.find('p').html(msg || '点击右上角通过<br>【发送给朋友】或【分享到朋友圈】<br>分享给你的小伙伴吧~');
	return _wrap;
};
// 分享提示
var share_tips;
WXC.sharetips = function(){
	share_tips = $('<div id="share-wrapper-box" class="' + (ISWP ? 'wp' : '') + '"><img src="http://img1.qidapp.cn/wxcrm/img/wxico-txt' + (ISWP ? '-wp' : '') + '.png?4"></div>').appendTo(document.body);
	share_tips.focus();
	share_tips.click(function(){ share_tips.remove(); });
};
// 提示框
WXC.wxalert = function(opt){
	var html = '<div><div class="modal-backdrop"></div><div class="wxalert"><div class="title">' + (opt.title||'提示') + '</div><div class="content">' + opt.content + '</div><div class="btns b1"><a>确定</a></div></div></div>';
	var el = $(html).appendTo(document.body);
	if ($(window).width() <= 320) el.find('.wxalert').addClass('small');
	el.find('a').click(function(){ el.remove(); });
	return el;
};
WXC.wxconfirm = function(opt){
	var html = '<div><div class="modal-backdrop"></div><div class="wxalert"><div class="title">' + (opt.title||'提示') + '</div><div class="content">' + opt.content + '</div><div class="btns"><a>确定</a><a>取消</a></div></div></div>';
	var el = $(html).appendTo(document.body);
	if ($(window).width() <= 320) el.find('.wxalert').addClass('small');
	el.find('a').click(function(){ el.remove(); });
	if (!!opt.call){
		el.find('a').eq(0).click(function(){ opt.call(); });
	}
	if (!!opt.call_cancel){
		el.find('a').eq(1).click(function(){ opt.call_cancel(); });
	}
	return el;
};

// UUID
Math.uuid_chars="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".split("");
Math.uuid=function(len,radix){var chars=Math.uuid_chars,uuid=[],i;radix=radix||chars.length;if(len){for(i=0;i<len;i++){uuid[i]=chars[0|Math.random()*radix]}}else{var r;uuid[8]=uuid[13]=uuid[18]=uuid[23]="-";uuid[14]="4";for(i=0;i<36;i++){if(!uuid[i]){r=0|Math.random()*16;uuid[i]=chars[(i==19)?(r&3)|8:r]}}}return uuid.join("")};
//////////////////////////////////////
// 用户请求参数
var REQ_CTX = {};
var baseurl, usertoken, userappid, userapptype, openid;
var site_key;
(function(){
	baseurl = $('meta[name=wxc-baseurl]').attr('content') || '';
	usertoken = $('meta[name=wxc-usertoken]').attr('content') || '';
	userappid = $('meta[name=wxc-userappid]').attr('content') || '';
	userapptype = ~~($('meta[name=wxc-userapptype]').attr('content') || '1');
})();
/*
 * @noopenid_is_tips 无OPENID时是否提示
 * @noopenid_is_auth 无OPENID时是否需要授权回调（页面上还要有userappid值才会授权回调）
 */
/*WXC.check_openid = function(noopenid_is_tips, noopenid_is_auth){
	var is_from_wxshare = urlp('from');
	is_from_wxshare = (is_from_wxshare == 'timeline' || is_from_wxshare == 'singlemessage');
	site_key = $('meta[name=wxc-sitekey]').attr('content') || 'wecrm';
	openid_key = 'wxc-openid-' + site_key;
	openid = urlp('openid');
	if (!!openid && is_from_wxshare != true){  // 从URL参数 && 非分享进入
		$.cookie(openid_key, openid, { expires:360, path:'/' });
		if (window.localStorage) window.localStorage.setItem(openid_key, openid);
		var loc = location.href.replace('openid=' + openid, '');
		location.href = loc;
		return;
	}
	
	openid = location.hash;
	if (!!openid && openid.indexOf('openid=') > -1) { // hash of URL
		openid = urlp('openid', openid);
		if (is_from_wxshare == true) openid = null;  // 从分享进入不获取openid，以防串号
		if (!!openid){
			$.cookie(openid_key, openid, { expires:360, path:'/' });
			if (window.localStorage) window.localStorage.setItem(openid_key, openid);
		}
		location.hash = '';
	}
	openid = $.cookie(openid_key) || '';
	if (!!!openid) openid = window.localStorage.getItem(openid_key);
	
	// 带有userappid并且为认证公众号会自动获取openid
	if (!!!openid && !!userappid && noopenid_is_auth !== false && userapptype == 4) {
		var code = urlp('code');
		if (!!code) {
			$.getJSON(baseurl + 'user_openid_get.dx?jsoncallback=?&code=' + code + '&uid=' + urlp('state'), null, function(data){
				if (!!data && !!data.openid){
					openid = data.openid;
					$.cookie(openid_key, openid, { expires:360, path:'/' });
					if (window.localStorage) window.localStorage.setItem(openid_key, openid);
					
					var loc = location.href;
					loc = loc.replace('code=' + code, '');
					loc = loc.replace('state=' + urlp('state'), '');
					location.href = loc;
				}else{
					WXC.msgtips('无效请求。请从微信访问');
				}
			});
		} else {
			// 去授权
			var rd_url = location.href.split('#')[0];  // remove hash
			var oauth_url = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid='+userappid+'&redirect_uri='+encodeURIComponent(rd_url) + '&response_type=code&scope=snsapi_base&state='+usertoken+'#wechat_redirect';
			location.href = oauth_url;
		}
		return 9999;
	} else if (!!!openid && noopenid_is_auth === true){
		// 临时OPENID方案。与正常OPENID相比，临时OPENID为双下划线开始 __
		openid = '__' + site_key + '-' + ~~(new Date().getTime() / 1000 / 60 / 24) + Math.uuid(28);
		//openid = '__' + site_key + '-' + new Date().getTime();
		//var hlex = '1234567890abc4edfghijklmnop0qistuvwxyz';
		//for (var i = 0; i < 17; i++) openid += (hlex[~~(Math.random() * hlex.length)]||'x');
		//openid = openid.toUpperCase();
		$.cookie(openid_key, openid, { expires:360, path:'/' });
		$.cookie(openid_key + '_temp', openid, { expires:360, path:'/' });
		if (window.localStorage){
			window.localStorage.setItem(openid_key, openid);
			window.localStorage.setItem(openid_key + '_temp', openid);
		}
	}
	
	// 已获取真实OPENID，做替换
	if (!!openid && openid.substr(0,2) != '__'){
		var openid_temp = $.cookie(openid_key + '_temp');
		if (!!!openid_temp) openid_temp = window.localStorage.getItem(openid_key + '_temp');
		if (!!openid_temp){
			$.getJSON(baseurl + 'openid_2real.dx?jsoncallback=?&openid=' + openid + '&openid_temp=' + openid_temp, null, function(data){
				if (!!data && !!data.status){
					$.removeCookie(openid_key + '_temp');
					if (window.localStorage) window.localStorage.removeItem(openid_key + '_temp');
					location.reload();
				}
			});
			return 9999;
		}
	}
	
	if (!!!openid && noopenid_is_tips !== false) WXC.msgtips('无效请求。请从微信访问');
	REQ_CTX = {usertoken:usertoken, openid:openid};
};*/

//////////////////////////////////////~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// 分享通知
var WXC_SHARE = WXC_SHARE || {};
$(document).ready(function(){
	if (!ISWeixin) return;
	WXC_SHARE = {
		"img_url": WXC_SHARE.img_url || $('#share_img_url').val(),
		"link": WXC_SHARE.link || location.href,
		"title": WXC_SHARE.title || $('title').text(),
		"desc": WXC_SHARE.desc || $('meta[name="description"]').attr('content')
	};
	weixin610();
	if (typeof WeixinJSBridge == "undefined") {
		if (document.addEventListener) {
			document.addEventListener('WeixinJSBridgeReady', winxin600, false);
		} else if (document.attachEvent) {
			document.attachEvent('WeixinJSBridgeReady', winxin600);
			document.attachEvent('onWeixinJSBridgeReady', winxin600);
		}
	}
});
var reload_wxshare_params = function(res){
	if (!!!wx) return;
	wx.onMenuShareAppMessage({
		title:WXC_SHARE.title, desc:WXC_SHARE.desc, link:share_url_safe(WXC_SHARE.link), imgUrl:WXC_SHARE.img_url,
		success:function(){ record_share(1); },
		cancel:function(){}
	});
	wx.onMenuShareTimeline({
		title:WXC_SHARE.title, desc:WXC_SHARE.desc, link:share_url_safe(WXC_SHARE.link), imgUrl:WXC_SHARE.img_url,
		success:function(){ record_share(2); },
		cancel:function(){}
	});
	wx.onMenuShareQQ({
		title:WXC_SHARE.title, desc:WXC_SHARE.desc, link:share_url_safe(WXC_SHARE.link), imgUrl:WXC_SHARE.img_url,
		success:function(){ record_share(3); },
		cancel:function(){}
	});
};
var weixin610 = function(){
	var __wxapi_debug = urlp('wxapi_debug') == 'true';
	if (__wxapi_debug == true) WXC.msgtips('进入WXAPI调试模式');
	$.getJSON(baseurl + 'jsapi_token.dx?jsoncallback=?&usertoken=' + usertoken + '&url=' + encodeURIComponent(location.href.split('#')[0]), null, function(data){
		if (!data || data.status == 0){
			if (__wxapi_debug == true) WXC.msgtips('无法获取微信接口');
			return;
		}
		data = data.data;
		wx.config({
			debug: __wxapi_debug,
			appId: userappid,
			jsApiList: ['onMenuShareTimeline', 'onMenuShareAppMessage', 'onMenuShareQQ', 'onMenuShareWeibo', 'previewImage', 'getNetworkType', 'hideOptionMenu', 'showOptionMenu', 'closeWindow', 'scanQRCode', 'chooseImage', 'uploadImage'],
			timestamp: data.timestamp,
			nonceStr: data.noncestr,
			signature: data.signature
		});
		wx.error(function(res){
			if (__wxapi_debug == true) WXC.msgtips('初始化微信接口失败('+(res.errMsg||'').toUpperCase()+')');
		});
		wx.ready(function(res){
			reload_wxshare_params(res);
		});
	});
};
function winxin600() {
    WeixinJSBridge.on('menu:share:appmessage', function(argv) {
        WeixinJSBridge.invoke('sendAppMessage', {
            "img_url": WXC_SHARE.img_url || 'http://img1.qidapp.cn/image/icon_wxfirend.png',
            "link": share_url_safe(WXC_SHARE.link),
            "title": WXC_SHARE.title,
            "desc": WXC_SHARE.desc || ''
        }, function(res) {
        	if (res['err_msg'] == 'send_app_msg:ok') record_share(1);
        });
    });
    WeixinJSBridge.on('menu:timeline:share', function(argv) {
        WeixinJSBridge.invoke('shareTimeline', {
            "img_url": WXC_SHARE.img_url || 'http://img1.qidapp.cn/image/icon_wxfirend.png',
            "link": share_url_safe(WXC_SHARE.link),
            "title": WXC_SHARE.title,
            "desc": WXC_SHARE.desc || ''
        }, function(res) {
        	if (res['err_msg'] == 'share_timeline:ok') record_share(2);
        });
    });
}
// remove openid in url of share
var share_url_safe = function(url){
	url = url || location.href;
	var openid_inquery = urlp('openid', url);
	if (!!openid_inquery){
		url = url.replace('openid=' + openid_inquery, '');
	}
	url = url.split('#')[0];
	return url;
};
// send to server
var record_share = function(type){
	$.getJSON(baseurl + 'user_share_record.dx?jsoncallback=?&type=' + type + '&openid=' + openid + '&usertoken=' + usertoken + '&url=' + encodeURIComponent(location.href));
	if (!!share_tips) share_tips.remove();
};
//////////////////////////////////////~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~