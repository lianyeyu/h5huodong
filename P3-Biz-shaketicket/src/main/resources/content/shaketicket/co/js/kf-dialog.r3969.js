// $Id: kf-dialog.js 3969 2015-02-14 02:35:41Z zhaoff@qidapp.com $
var _init_module = function(){
	var _h = location.href;
	if (_h.indexOf('/reservation/') > -1) return 'reservation';
	else if (_h.indexOf('/member/') > -1) return 'member';
	else if (_h.indexOf('/restaurant/') > -1) return 'restaurant';
	else if (_h.indexOf('/mall/') > -1) return 'mall';
	else if (_h.indexOf('/house/') > -1) return 'house';
	else if (_h.indexOf('/car/') > -1) return 'car';
	else if (_h.indexOf('activity') > -1) return 'activity';
	else if (_h.indexOf('/kf/') > -1 || _h.indexOf('/s/') > -1 || _h.indexOf('interact') > -1) return null;
	return 'site';
};
var module;
$(document).ready(function(){
	WXC.check_openid(false, false);
	module = _init_module();
	if (!!!module) return;
	WXC.check_openid(false, false);
	$.getJSON((window.ACT_2HURL||baseurl) + 'plugins/kf/chat_load.dx?jsoncallback=?', REQ_CTX, function(data){
		var bizModule = (data.data + '').split(',');
		var has = false;
		if (!!module) {
			has = $.inArray(module, bizModule) > -1;
		} else {
			$(bizModule).each(function(){
				has = location.href.indexOf('/'+this+'/') > -1;
				return !has;
			});
		}
		if (has) {
			var el = $('body');
			var _a = $('<div style="position:fixed;bottom:200px;right:6px;font-size:28px;background-color:rgba(255,255,255,0.8);width:42px;height:42px;line-height:42px;text-align:center;z-index:100;border-radius:50%;box-shadow:-3px 3px 3px rgba(0,0,0,0.1)"><a style="color:#0079ff"><i class="fa fa-comments-o"></i></a></div>').appendTo(el);
			_a[0].addEventListener("touchmove", function(event){
				event.preventDefault();
				var touch = event.targetTouches[0];
				var a_x = touch.clientX;
				a_x = $('body').width() - a_x;
				a_x = ~~a_x <= 0 ? 0 : a_x;
				a_x = a_x >= $('body').width() - 50 ? $('body').width() - 50 : a_x;
				var a_y = touch.clientY;
				a_y = a_y <= 5 ? 5 : a_y; 
				a_y = a_y >= $(window).height() - 50 ? $(window).height() - 50 : a_y;
				_a.css({'right':a_x + 'px', 'top':a_y + 'px'});
			}, false);
			_a.click(function(){
				el.css('overflow','hidden');
				var _div = show_kf_dialog();
				var wdo = _div.find('iframe')[0].contentWindow;
				if ($(wdo.document).find('#btn_send').prop('disabled')) {
					wdo.location.reload();
				}
			});
		}
	});
});

var show_kf_dialog = function(){
	if ($('.kf_dialog').length == 1) {
		$('.kf_dialog').show();
		return $('.kf_dialog');
	}
	$('.kf_dialog').remove();
	var el = $('body');
	var _div = $('<div class="kf_dialog" style="position:fixed;left:14px;right:14px;top:14px;bottom:0;z-index:10000000;box-shadow:1px 0px 6px 1px rgba(0,0,0,0.4);"></div>').appendTo(el);
	var _close = $('<div style="position:fixed;width:30px;height:30px;border-radius:50%;top:0;right:0;background:#0079ff url(http://qncdn.qidapp.cn/image/icon-close.png) no-repeat center center;background-size:20px;color:#fff;font-size:24px;line-height:1;text-align:center;"></div>').appendTo(_div);
	_close.click(function(){ _div.hide(); el.css('overflow','auto'); });
	var chat_src = '../kf/chat.html';
	var path = location.href.split(module)[1];
	if (!!path && path.split('/').length > 2) {
		$(path.split('/')).each(function(ind){
			if (ind < 2) {return true;}
			chat_src = '../' + chat_src
		});
	}
	if(module == 'site') { chat_src = 'kf/chat.html'; }
	$('<iframe src="'+chat_src+'" style="width:100%;height:100%;border:0px;z-index:100000000;"></iframe>').appendTo(_div);
	return _div;
};
