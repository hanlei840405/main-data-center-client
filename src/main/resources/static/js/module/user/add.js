/**
 * Created by hanlei6 on 2016/8/2.
 */
$(document).ready(function () {
    $('#birthday').datetimepicker({
        pickTime: false
    });
    //$('#birthday').datetimepicker({
    //    lang: 'zh',
    //    timepicker: false,
    //    format: 'Y-m-d'//,
    //    //formatDate: 'Y/m/d'
    //});

    $('#user-add-submit').bind('click', function () {
        $.ajax({
            url: 'user/save',
            type: 'POST',
            context: '#user-grid',
            data: $('user-add-form').serialize(),
            success: function (res) {
                if (res.status == 200) {
                    $(this).reload();
                } else {

                }
            }

        });
        closeModuleInModal('addModal');
    })
});