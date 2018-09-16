// $(function () {
//     $('#datetime').datepicker({
//         duration: '',
//         showTime: true,
//         constrainInput: false
//     });
// });

$(document).ready(
    function () {
        document.getElementsByClassName('push_msg')[0].addEventListener('click', function () {
            window.location.href = 'msg_push.html';
        }, false);
        document.getElementsByClassName('back_msglist')[0].addEventListener('click', function () {
            window.location.href = 'msg.html';
        }, false);
    });