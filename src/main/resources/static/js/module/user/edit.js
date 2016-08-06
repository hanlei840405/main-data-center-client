/**
 * Created by hanlei6 on 2016/8/2.
 */
$(document).ready(function () {
    flushComponents();
    flushCheckboxRadio();
    //$('#birthday').datetimepicker({
    //    pickTime: false
    //});
    $('#birthday').datetimepicker({
        timepicker: false,
        format: 'Y-m-d'//,
        //formatDate: 'Y/m/d'
    });

    $('#user-edit-submit').bind('click', function () {
        $.ajax({
            url: '/user/update',
            type: 'POST',
            data: new FormData($('#user-edit-form')[0]),
            processData: false,
            contentType: false,
            success: function (res) {
                debugger;
                if (res.status == 200) {
                    var pageSize = $('.page-size').val();
                    loadModule('/user/index?pageNum=1&pageSize=' + pageSize);
                    closeModuleInModal('formModal');
                } else {

                }
            }
        });
    })
});