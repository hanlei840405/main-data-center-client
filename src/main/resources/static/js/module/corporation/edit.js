/**
 * Created by hanlei6 on 2016/8/2.
 */
$(document).ready(function () {
    flushComponents();
    flushCheckboxRadio();

    $('#corporation-edit-submit').bind('click', function () {
        $.ajax({
            url: '/corporation/update',
            type: 'POST',
            data: new FormData($('#corporation-edit-form')[0]),
            processData: false,
            contentType: false,
            success: function (res) {
                debugger;
                if (res.status == 200) {
                    var pageSize = $('.page-size').val();
                    loadModule('/corporation/index?pageNum=1&pageSize=' + pageSize);
                    closeModuleInModal('formModal');
                } else {

                }
            }
        });
    })
});