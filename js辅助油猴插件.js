// ==UserScript==
// @name         chatGPT机器人辅助插件
// @namespace    http://tampermonkey.net/
// @version      0.3
// @description  由于chatgpt cloudflare限制，以前cookie方法已经失效、故采用挂浏览器js的回调的方法读取数据
// @author       夜雨
// @match        https://chat.openai.com/*
// @icon         https://www.google.com/s2/favicons?sz=64&domain=chat.openai.com
// @grant        none
// @license      MIT
// ==/UserScript==

(function() {

	'use strict';

	var sendURL = 'http://127.0.0.1:8087/msg/todo'; //发信地址

	var getQuestionURL = 'http://127.0.0.1:8087/msg/getQuestion';//收信地址

	var gid = "";//gid

	function initjob() {
		console.log("开始任务...")
		//清空
		try {
			let newchat = document.querySelectorAll("a.flex")[0] || document.querySelectorAll("button.px-3")[0]
			newchat.click()
		} catch (e) {
			//TODO handle the exception
			location.reload()
		}
		startJob();
	}

	function startJob() {
		const xhr = new XMLHttpRequest();

		// 监听 readystatechange 事件
		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE) {
				if (xhr.status === 200) {
					console.log(xhr.responseText);
					//TODO 服务器获取内容
					var retData = JSON.parse(xhr.responseText);
					var msg = retData.question;
					gid = retData.gid;
					//console.log(gid)
					//console.log(msg)
					if (msg == undefined || msg == "no" || msg == "" || msg == "。" || msg == "。。"
						|| msg == "。。。" || msg == "。。。。"){
						console.log("信息为空,10秒后继续任务")
						setTimeout(initjob, 10000)
						return
					}
					if(!document.querySelector("textarea")){
						console.log("textarea不存在,10秒后继续任务")
						setTimeout(initjob, 10000)
						return
					}
					document.querySelector("textarea").value = msg

					if(!document.querySelectorAll("button.absolute")[0]){
						console.log("button不存在,10秒后继续任务")
						setTimeout(initjob, 10000)
						return
					}
					//点击
					document.querySelectorAll("button.absolute")[0].click()

					var flag = false;
					var times = 0;//超时设置

					//监控
					var myJob = setInterval(function() {
						times++;
						console.log("等待完成....");
						if (document.querySelectorAll("svg.h-3.w-3")[0] || times > 36
							|| (!document.querySelectorAll("button.absolute")[0].hasAttribute("disabled")) ) {
							console.log("输入完成")
							//TODO 发送内容
							try {
								if (!document.querySelectorAll("div.markdown")[0]) {
									sendGetRequest(sendURL, JSON.stringify({
										anser: "请求失败,你重试",
										gid : gid
									}));
									location.reload()
								}
								console.log(document.querySelectorAll("div.markdown")[0].innerText);
								//senderMsg(document.querySelectorAll("div.markdown")[0].innerText)
								let text = document.querySelectorAll("div.markdown")[0].innerText;
								sendGetRequest(sendURL, JSON.stringify({
									anser: text,
									gid : gid
								}));
							} finally {
								clearInterval(myJob)
								times = 0;
								console.log("任务完成，10秒后继续任务")
								setTimeout(initjob, 10000)
							}

						}
					}, 5000)

				} else {
					console.error('请求失败：' + xhr.status);
					console.error("请求信息失败，10秒后继续任务")
					sendGetRequest(sendURL, JSON.stringify({
						anser: "请求失败,你重试",
						gid : gid
					}));
					location.reload()
					clearInterval(myJob)
					setTimeout(initjob, 10000)
				}
			}
		};

		// 发送请求
		xhr.open('POST', getQuestionURL);
		xhr.send("");
	}


	function sendGetRequest(url, data) {
		const xhr = new XMLHttpRequest();

		// 监听 readystatechange 事件
		xhr.onreadystatechange = function() {
			if (xhr.readyState === XMLHttpRequest.DONE) {
				if (xhr.status === 200) {
					console.log(xhr.responseText);

				} else {
					console.error('请求失败：' + xhr.status);
				}
			}
		};

		// 发送请求
		xhr.open('POST', url, false);
		xhr.send(data);
	}

	//定时刷新 每7分刷一次 可能会导致数据丢失
	setInterval(function() {
		//console.clear()
		location.reload()
	}, 420000)

	setTimeout(initjob, 10000)


})();
