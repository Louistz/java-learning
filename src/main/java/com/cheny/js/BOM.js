/**
 * Created by chenyong on 17/4/13.
 */
/**
 * BOM:浏览器对象模型
 */
/**
 * 核心对象: window，是js访问浏览器窗口的一个接口，又是ECMAScript规定的Global对象
 *
 * 每个框架frame都有自己的window对象
 * window.frames[0]
 * frames[0]
 * top.frames[0]
 *
 * top对象始终指向最外层的框架。parent指向当前框架的直接上层框架
 */

//窗口位置
var leftPos = (typeof window.screenLeft == 'number') ?
    window.screenLeft : window.screenX;
var topPos = (typeof window.screenTop == 'number') ?
    window.screenTop : window.screenY;

window.moveTo(0,0);
window.moveBy(-100,0);

//窗口大小
window.innerWidth,window.innerHeight,window.outerHeight,window.outerWidth
window.resizeTo(100,100); //100x100
window.resizeBy(100,50);

//导航和窗口
window.open(url,windowNameOrFrameName);  //导航到一个特定url，打开一个新窗口 ._blank,_top,_parent,_self
var wroxWin = window.open("http://www.wrox.com/","wroxWindow",
    "height=400,width=400,top=10,left=10,resizable=yes");
wroxWin.resizeTo(100,100);
wroxWin.moveTo(100,100);
wroxWin.close();

var timeoutId = setTimeout(function () {
    alert("hello,world");
},1000);
clearTimeout(timeoutId);

var intervalId = setInterval(function () {
    alert("Hello world");
},1000);
clearInterval(intervalId);

alert("Hello World");
confirm("Are you ok?");
var result = prompt("Are you Michael?", "");

/////////////////////location//////////////////
location
window.location == document.location
var location = {
    hash: "#content",
    host: "www.google.com:80",
    hostname:"www.google.com",
    href:"http://www.google.com",
    pathname: "/q/",
    port: "8080",
    protocol:"http",
    search:"?q=javascript"
}