/**
 * Created by hanlei6 on 2016/8/2.
 */
$(document).ready(function () {
    flushComponents();
    flushCheckboxRadio();

    $('#corporation-add-submit').bind('click', function () {
        $.ajax({
            url: '/corporation/save',
            type: 'POST',
            data: new FormData($('#corporation-add-form')[0]),
            processData: false,
            contentType: false,
            success: function (res) {
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