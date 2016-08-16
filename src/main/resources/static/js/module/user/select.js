$(document).ready(function () {
    flushComponents();
    flushCheckboxRadio();
    $('.btn_select').bind('click', function () {
        var id = $(this).val();
        var name = $(this).attr('data');
        selectUserCallback(id, name);
        closeModuleInModal('selectUserModal');
    })
});