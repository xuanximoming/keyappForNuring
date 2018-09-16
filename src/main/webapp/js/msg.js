/// <reference path="F:/typings/jquery/jquery.d.ts" />
function ToJson(data) {
    var datalist = JSON.parse(data);
    var otable = $('#msgtable')[0];
    datalist.forEach(function (ele, index) {
        var strmessageTypeText = "群发消息";
        if (ele.msgType == 2) {
            strmessageTypeText = "群发任务";
        }
        var isRead = "已读";
        if (ele.msgread == 0) {
            isRead = "未读";
        }
        var msgmemo = ele.msgmemo;
        if (msgmemo.length > 5) {
            msgmemo = msgmemo.substr(1, 5);
        }
        var strtr = '';
        strtr = '<tr class="back_msglist"><td >\
                   <input type = "checkbox" name = "checkbox2" value = "checkbox"/>\
                 </td>\
        <td> ' + ele.msgID + '</td>\
        <td> ' + strmessageTypeText + '</td>\
        <td> <a href = "msg_push.jsp?msgID=1&msgType=1&msgTitle=1&msgmemo=1111&msgTime=111" > ' + ele.msgTitle + ' </a></td> \
        <td> ' + msgmemo + ' </td>\
        <td >所有护士</td> \
        <td> ' + ele.msgTime + '</td> \
        <td> ' + isRead + ' </td> \
        <td>\
            <a href="messages.do?act=del&msgid=1">删除</a>\
        </td ></tr>';
        otable.insertRow().innerHTML = strtr;
    });
}

$(document).ready(
    function () {
        $.ajax({
            type: 'GET',
            url: 'messages.do',
            success: function (data) {
                ToJson(data);
            },
            error: function (err) {
                console.log(err);
            }
        });
        document.getElementsByClassName('push_msg')[0].addEventListener('click', function () {
            window.location.href = 'msg_push.html';
        }, false)
        document.getElementsByClassName('back_msglist')[0].addEventListener('click', function () {
            window.location.href = 'msg.html';
        }, false)
    }
)