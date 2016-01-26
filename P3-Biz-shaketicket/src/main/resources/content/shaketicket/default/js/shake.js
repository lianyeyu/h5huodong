// $Id: shake.js 4169 2015-03-16 16:44:32Z zhaoff@qidapp.com $
var shakeEvent;
// 提示框
var box_dialog = function(opt){
	var box = $('<div><div class="modal-backdrop"></div><div class="gift-dialog"></div></div>').appendTo(document.body);
	var dlg = box.find('.gift-dialog');
	var head = $('<div class="title"><h4>' + (opt.title||'提示') + '</h4><a>&times;</a></div>').appendTo(dlg);
	head.find('a').click(function(){ 
		box.remove();
		if (!!shakeEvent) shakeEvent.start();
	});
	var cont = $('<div class="content"></div>').appendTo(dlg);
	cont.html(opt.content);
	$('<div class="foot"><a class="btn -share" onclick="WXC.sharetips()">分享</a><a class="btn -gifts lr">点击领取</a></div>').appendTo(dlg);
	return box;
};

// shake.event
(function(global,factory){if(typeof define==="function"&&define.amd){define(function(){return factory(global,global.document)})}else{if(typeof module!=="undefined"&&module.exports){module.exports=factory(global,global.document)}else{global.Shake=factory(global,global.document)}}}(typeof window!=="undefined"?window:this,function(window,document){function Shake(options){this.hasDeviceMotion="ondevicemotion" in window;this.options={threshold:15,timeout:1000};if(typeof options==="object"){for(var i in options){if(options.hasOwnProperty(i)){this.options[i]=options[i]}}}this.lastTime=new Date();this.lastX=null;this.lastY=null;this.lastZ=null;if(typeof document.CustomEvent==="function"){this.event=new document.CustomEvent("shake",{bubbles:true,cancelable:true})}else{if(typeof document.createEvent==="function"){this.event=document.createEvent("Event");this.event.initEvent("shake",true,true)}else{return false}}}Shake.prototype.reset=function(){this.lastTime=new Date();this.lastX=null;this.lastY=null;this.lastZ=null};Shake.prototype.start=function(){this.reset();if(this.hasDeviceMotion){window.addEventListener("devicemotion",this,false)}};Shake.prototype.stop=function(){if(this.hasDeviceMotion){window.removeEventListener("devicemotion",this,false)}this.reset()};Shake.prototype.devicemotion=function(e){var current=e.accelerationIncludingGravity;var currentTime;var timeDifference;var deltaX=0;var deltaY=0;var deltaZ=0;if((this.lastX===null)&&(this.lastY===null)&&(this.lastZ===null)){this.lastX=current.x;this.lastY=current.y;this.lastZ=current.z;return}deltaX=Math.abs(this.lastX-current.x);deltaY=Math.abs(this.lastY-current.y);deltaZ=Math.abs(this.lastZ-current.z);if(((deltaX>this.options.threshold)&&(deltaY>this.options.threshold))||((deltaX>this.options.threshold)&&(deltaZ>this.options.threshold))||((deltaY>this.options.threshold)&&(deltaZ>this.options.threshold))){currentTime=new Date();timeDifference=currentTime.getTime()-this.lastTime.getTime();if(timeDifference>this.options.timeout){window.dispatchEvent(this.event);this.lastTime=new Date()}}this.lastX=current.x;this.lastY=current.y;this.lastZ=current.z};Shake.prototype.handleEvent=function(e){if(typeof(this[e.type])==="function"){return this[e.type](e)}};return Shake}));