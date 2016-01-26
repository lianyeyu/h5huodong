$('#randCodeImage').click(function() {
	reloadRandCodeImage();
});
/**
 * 刷新验证码
 */
function reloadRandCodeImage() {
	var date = new Date();
	var img = document.getElementById("randCodeImage");
	img.src = '../randCodeImage?a=' + date.getTime();
}

function popup(id) {
	$("#" + id).show();
}

function popuphide(id) {
	$("#" + id).hide();
}

// 首页-选中商品开始杀价
function beginBargain() {
	var actId = $("#barginActId").val();
	var mainActId = $("#mainActId").val();
	var openid = $("#openid").val();
	var subscribe = $("#subscribe").val();
	var jwid = $("#jwid").val();
	var appid = $("#appid").val();
	var url = "../commonftb/toIndex.do";
	url = url + "?actId=" + actId + "&mainActId=" + mainActId + "&openid="
			+ openid + "&subscribe=" + subscribe+"&jwid=" + jwid+"&appid=" + appid;
	location.href=url;
}

// 首页-我要砍价按钮
function wantBargain(actId) {
	var mainActId = $("#mainActId").val();
	var openid = $("#openid").val();
	var subscribe = $("#subscribe").val();
	var jwid = $("#jwid").val();
	var url = "../commonftb/wantBargain.do";
	$.ajax({
		url : url,
		cache : false,
		type : "post",
		data : {
			actId : actId,
			openid : openid,
			subscribe : subscribe,
			mainActId : mainActId,
			jwid : jwid
		},
		dataType : "json",
		success : function(data) {
			if (data.success) {
				// 商品数量有剩余
				$("#barginActId").val(data.obj.id);
				if(data.attributes.join){
					beginBargain();
				}else{
					$("#actName").html(data.obj.name);
					$("#confirm").html(data.obj.confirm);
					popup('chooseConfirm');
				}
			} else {
				if (data.obj == "isNotFoucs") {
					popup('act_focus');
				} else if (data.obj == "noProduct") {
					popup('noProduct');
				} else {
					$("#tip_msg_content").html(data.msg);
					popup('tip_msg');
				}

			}
		},
		error : function(data, status, e) {
		}
	});
}

// 个人页-请人帮砍
function goshare() {
	var actId = $("#barginActId").val();
	var mainActId = $("#mainActId").val();
	var openid = $("#openid").val();
	var subscribe = $("#subscribe").val();
	var nickname = $("#nickname").val();
	var jwid = $("#jwid").val();
	var appid = $("#appid").val();
	var url = "../commonftb/goShare.do";
	var flag = "0";
	$.ajax({
		url : url,
		cache : false,
		type : "post",
		data : {
			actId : actId,
			openid : openid,
			nickname : nickname,
			subscribe : subscribe
		},
		dataType : "json",
		success : function(data) {
			if (data.success) {
				if (data.attributes.isCutMinPrice) {
					popup('cutMinPriceNoShareDiv');
				} else {
					popup('share-pop-up');
				}
			} else {
				$("#tip_msg_content").html(data.msg);
				popup('tip_msg');
			}
		},
		error : function(data, status, e) {

		}
	});
	$("#goShareButton").removeAttr("disabled");
}
// 个人页-领取奖品
function receivePrize() {
	var actId = $("#barginActId").val();
	var mainActId = $("#mainActId").val();
	var openid = $("#openid").val();
	var subscribe = $("#subscribe").val();
	var nickname = $("#nickname").val();
	var jwid = $("#jwid").val();
	var appid = $("#appid").val();
	var url = "../commonftb/receivePrize.do";
	$.ajax({
		url : url,
		type : 'post',
		dataType : 'json',
		data : {
			actId : actId,
			openid : openid,
			mainActId : mainActId,
			jwid : jwid
		},
		success : function(data, status) {
			if (data.success) {
				$("#cardPsd").html(data.obj.cardPsd);
				popup('showPsdDiv');
				popuphide('addPhone');
			} else {
				if(data.obj=="isNotCutMinPrice"){
					popup('notCutMinPriceDiv');
				}else if(data.obj=="isNotBind"){
					popup('isNotBind');
				}else if(data.obj=="addPhone"){
					popup('phoneDiv');
				}else{					
					$("#tip_msg_content").html(data.msg);
					popup('tip_msg');
				}
			}
		}
	});
}
// 个人页-需要填写信息的领取奖品
function receivePrizeAddInfo() {
	var actId = $("#barginActId").val();
	var mainActId = $("#mainActId").val();
	var openid = $("#openid").val();
	var subscribe = $("#subscribe").val();
	var nickname = $("#nickname").val();
	var jwid = $("#jwid").val();
	var appid = $("#appid").val();
	var mobile = $("#receive_prize_mobile").val();
	if (mobile == '') {
		alert("请输入手机号");
		return
	}else if (!isMobile(mobile)){
		alert("手机号格式不正确，请重新输入！");
		return
	}
	var url = "../commonftb/receivePrize.do";
	$.ajax({
		url : url,
		type : 'post',
		dataType : 'json',
		data : {
			actId : actId,
			openid : openid,
			mainActId : mainActId,
			mobile : mobile
		},
		success : function(data, status) {
			popuphide('phoneDiv');
			if (data.success) {
				$("#cardPsd").html(data.obj.cardPsd);
				popup('showPsdDiv');
			} else {
				if(data.obj=="isNotCutMinPrice"){
					popup('notCutMinPriceDiv');
				}else if(data.obj=="isNotBind"){
					popup('isNotBind');
				}else if(data.obj=="addPhone"){
					popup('phoneDiv');
				}else{					
					$("#tip_msg_content").html(data.msg);
					popup('tip_msg');
				}
			}
		}
	});
}

// 帮杀
function bargain() {
	var registrationId = $("#registrationId").val();
	var openid = $("#openid").val();
	var nickname = $("#nickname").val();
	var subscribe = $("#subscribe").val();
	var jwid = $("#jwid").val();
	if (subscribe == 0) {
		popup("act_focus");
		return;
	}
	var url = "../commonftb/bargain.do";
	$.ajax({
		url : url,
		cache : false,
		type : "post",
		data : {
			registrationId : registrationId,
			openid : openid,
			nickname : nickname,
			jwid : jwid
		},
		dataType : "json",
		success : function(data) {
			if (data.success) {
				if (data.attributes.cutCount == "first") {
					$("#act_cut_price").html(data.obj.cutPrice);
					popup('fistDiv');
				} else if (data.attributes.cutCount == "last") {
					popup('lastDiv');
				} else if (data.attributes.cutCount == "received") {
					popup('lastDiv');
				} else {
					popup('nextDiv');
				}
			} else {
				$("#tip_msg_content").html(data.msg);
				popup('tip_msg');
			}
		},
		error : function(data, status, e) {
		}
	});
}
// 帮杀页-我要参加
function join() {
	var mainActId = $("#mainActId").val();
	var openid = $("#openid").val();
	var subscribe = $("#subscribe").val();
	var jwid = $("#jwid").val();
	var appid = $("#appid").val();

	var url = "../commonftb/toIndex.do";
	url = url + "?mainActId=" + mainActId;
	url = url + "&openid=" + openid;
	url = url + "&subscribe=" + subscribe;
	url = url + "&jwid=" + jwid;
	url = url + "&appid=" + appid;
	location.href = url;
}

// 手机号数字正则验证
function isMobile(s) {
	var patrn = /^1((3\d)|(4[57])|(5[01256789])|(7[678])|(8\d))\d{8}$/;
	if (!patrn.exec(s))
		return false;
	return true;
}


//初始化下标
function resetIndex(idv) {
	$box = $("#"+idv+"");
	$box.find("tr").each(function(i){
		$(this).find("td").each(function(){
			var $this = $(this).children(), name = $this.attr('name'), val = $this.val();
			if(name!=null){
				if (name.indexOf("#index#") >= 0){
					$this.attr("name",name.replace('#index#',i));
				}else{
					var s = name.indexOf("[");
					var e = name.indexOf("]");
					var new_name = name.substring(s+1,e);
					$this.attr("name",name.replace(new_name,i));
				}
			}
		});
	});	
}