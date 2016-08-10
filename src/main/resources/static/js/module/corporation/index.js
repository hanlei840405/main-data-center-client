/**
 * Created by hanlei6 on 2016/7/21.
 */
$(document).ready(function () {
    flushComponents();
    flushCheckboxRadio();
    flushImagePopup();
    pager($('.block-area'), '/corporation/index');
    $('.btn_view').on('click', function () {
        var id = $(this).val();
        openModuleInModal('formModal', '/corporation/view?id=' + id);
    });
    $('.btn_edit').on('click', function () {
        var id = $(this).val();
        openModuleInModal('formModal', '/corporation/edit?id=' + id);
    });
    $('.btn_delete').on('click', function () {
        var ids = [];
        $('.list-check:checked').each(function () {
            ids.push($(this).val())
        });
        $.ajax({
            url: '/corporation/delete',
            type: 'POST',
            data: {ids: ids.join(",")},
            success: function (res) {
                if (res.status == 200) {
                    var pageSize = $('.page-size').val();
                    loadModule('/corporation/index?pageNum=1&pageSize=' + pageSize);
                    closeModuleInModal('deleteModal');
                } else {

                }
            }
        });
    });
});

function deleteSelected() {
    if ($('.list-check:checked').size() > 0) {
        $('#deleteModal').modal({backdrop: 'static', keyboard: false});
    }
}

