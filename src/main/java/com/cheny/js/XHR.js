/**
 * Created by chenyong on 17/4/13.
 */
function createXHR(){
    if (typeof XMLHttpRequest != "undefined"){
        return new XMLHttpRequest();
    } else if (typeof ActiveXObject != "undefined"){
        if (typeof arguments.callee.activeXString != "string"){
            var versions = [ "MSXML2.XMLHttp.6.0", "MSXML2.XMLHttp.3.0", "MSXML2.XMLHttp"], i, len;
            for (i=0,len=versions.length; i < len; i++){
                try {
                    new ActiveXObject(versions[i]);
                    arguments.callee.activeXString = versions[i];
                    break;
                } catch (ex){ //
                } }
        }
        return new ActiveXObject(arguments.callee.activeXString);
    } else {
        throw new Error("No XHR object available.");
    }
}

var xhr = createXHR();
xhr.onreadystatechange = function () {
    if(xhr.readyState == 4){
        if((xhr.status >= 200 && xhr.status < 300) || xhr.status ==304){
            alert(xhr.responseText);
        }else{
            alert("Request was unsuccessful : " + xhr.status);
        }
    }
}
xhr.setRequestHeader("MyHeader","MyValue");
var header = xhr.getResponseHeader("");
var headers = xhr.getAllResponseHeaders();


xhr.open("get/post","example.txt", false);
xhr.send(null);

var form = document.getElementById("formId");
xhr.send(serialize(form));


//XMLHttpRequest 2级
var data = new FormData();
data.append("","");
var data = new FormData(document.forms[0]);

//timeout
xhr.timeout = 1000;
xhr.ontimeout = function () {
    alert("timeout");
};
xhr.send();

///////////////////////跨域问题//////////////////////
/**
 *  CORS : w3c
 *
 * Origin: http://www.nczonline.net
 *
 * Access-Control-Allow-Origin: http://www.nczonline.net
 *
 * 服务器接受就返回同样的Origin信息，公共资源就返回 *
 * */

//IE 的XDR

/*其他跨域方式*/
//1.图片Ping
var img = new Image();
img.onload = img.onerror = function () {
    alert("Done");
}
img.src = "http://www.example.com/test?name=Jack";
//只能get,而且无法访问服务器的响应文本

//2.JSONP json with padding
callback({"name":"jack"})
//回调函数和数据 http://justcoding.iteye.com/blog/1366102/
/*
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
    <script type="text/javascript">
    function jsonpCallback(result) {
        //alert(result);
        for(var i in result) {
            alert(i+":"+result[i]);//循环输出a:1,b:2,etc.
        }
    }
var JSONP=document.createElement("script");
JSONP.type="text/javascript";
JSONP.src="http://crossdomain.com/services.php?callback=jsonpCallback";
document.getElementsByTagName("head")[0].appendChild(JSONP);
</script>
//或者
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
    <script type="text/javascript">
    function jsonpCallback(result) {
        alert(result.a);
        alert(result.b);
        alert(result.c);
        for(var i in result) {
            alert(i+":"+result[i]);//循环输出a:1,b:2,etc.
        }
    }
    </script>
    <script type="text/javascript" src="http://crossdomain.com/services.php?callback=jsonpCallback"></script>
javaScript的连接必须在function下面
*/
//服务端返回
JsonpCallback({});

/*Comet**/
//实时推送
//1.长轮询
//2.HTTP流(客户端向服务器发送请求，而服务端保持连接打开,实时flush)
function createStreamingClient(url, progress, finished){
    var xhr = new XMLHttpRequest(),received = 0;
    xhr.open("get",url,true);
    xhr.onreadystatechange = function () {
        var result ;
        if(xhr.readyState == 3){
            //只获取最新数据并调整计数器
            result = xhr.responseText.substring(received);
            received += result.length;

            //处理
            progress(result);
        } else if(xhr.readyState == 4){
            //结束
            finished(xhr.responseText);
        }
    }
    xhr.send(null);
    return xhr;
}
var client = createStreamingClient("streaming.url", function (data) {
    alert("received" + data);
}, function () {
    alert("Done");
});

//SSE server-sent events
var source = new EventSource("myevents.php");
source.onmessage = function (event) {
    var data = event.data;
}
source.close();

//web socket :  ws://  wss://
var socket = new WebSocket("ws://www.example.com/server.php");
socket.send("Hello");
socket.onopen = function () {

}
socket.onmessage = function (event) {
    var data = event.data;
}
socket.onerror = function () {

}
socket.onclose = function (event) {

}
socket.close();
