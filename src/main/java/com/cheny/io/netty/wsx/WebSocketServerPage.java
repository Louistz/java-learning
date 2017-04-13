package com.cheny.io.netty.wsx;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * <p>描述</p>
 *
 * @author of1610 chenyong
 * @version 1.0
 * @since 1.0
 */
public enum WebSocketServerPage {

    INSTANCE;

    public ByteBuf getContent(String webSocketLocation) {
        return Unpooled.copiedBuffer(
                    "<html>\n" +
                            " \t<head>\n" +
                            " \t\t<meta charset='utf-8'>Netty Time Server </meta>\n" +
                            " \t</head>\n" +
                            " \t<body>\n" +
                            " \t\t<script type='text/javascript'>\n" +
                            " \t\t\tvar socket ;\n" +
                            " \t\t\tif(!window.WebSocket){\n" +
                            " \t\t\t\twindow.WebSocket = window.MozWebSocket;\n" +
                            " \t\t\t}\n" +
                            " \t\t\tif(window.WebSocket){\n" +
                            " \t\t\t\tsocket = new WebSocket('"+webSocketLocation+"');\n" +
                            " \t\t\t\tsocket.onmessage = function(event){\n" +
                            " \t\t\t\t\tvar ta = document.getElementById('responseText');\n" +
                            " \t\t\t\t\tta.value = '';\n" +
                            " \t\t\t\t\tta.value = event.data;\n" +
                            " \t\t\t\t};\n" +
                            " \t\t\t\tsocket.onopen = function(event){\n" +
                            " \t\t\t\t\tvar ta = document.getElementById('responseText');\n" +
                            " \t\t\t\t\tta.value = '';\n" +
                            " \t\t\t\t\tta.value = '打开WebSocket服务正常,浏览器支持WebSocket!';\n" +
                            " \t\t\t\t};\n" +
                            " \t\t\t\tsocket.onclose = function(event){\n" +
                            " \t\t\t\t\tvar ta = document.getElementById('responseText');\n" +
                            " \t\t\t\t\tta.value = '';\n" +
                            " \t\t\t\t\tta.value = 'WebSocket关闭.'\n" +
                            " \t\t\t\t};\n" +
                            " \t\t\t}else{\n" +
                            " \t\t\t\talert('sorry,you browser is not support WebSocket.');\n" +
                            " \t\t\t\t\n" +
                            " \t\t\t}\n" +
                            " \t\t\tfunction send(message){\n" +
                            " \t\t\t\tif(!window.WebSocket){ return ;}\n" +
                            " \t\t\t\tif(socket.readyState == WebSocket.OPEN){\n" +
                            " \t\t\t\t\tsocket.send(message);\n" +
                            " \t\t\t\t}else{\n" +
                            " \t\t\t\t\talert('WebSocket connection is not established.')\n" +
                            " \t\t\t\t}\n" +
                            " \t\t\t}\n" +
                            " \t\t</script>\n" +
                            "\n" +
                            " \t\t<form onsubmit='return false;'>\n" +
                            "\t\t<input type = 'text' name='message' value='message' />\n" +
                            "\t\t<br/><br/>\n" +
                            "\t\t<input type = 'button' value='send' onclick='send(this.form.message.value)' />\n" +
                            "\t\t<hr color='blue'/>\n" +
                            "\t\t<h3>received messages</h3>\n" +
                            "\t\t<textarea id='responseText' style='width:500px;height:300px'></textarea>\n" +
                            "\t</form>\n" +
                            " </body>\n" +
                            "</html>",
                CharsetUtil.UTF_8
                );
    }
}
