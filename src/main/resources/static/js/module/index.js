/**
 * Created by hanlei6 on 2016/7/21.
 */
$(document).ready(function () {
    $.ajaxSetup({
        cache: false
    });
});
function loadModule(mapping, container) {
    if (container) {
        $('#' + container).load(mapping);
    } else {
        $('#content').load(mapping);
    }
}

function openModuleInModal(modal, mapping) {
    $('#' + modal).modal({backdrop: 'static', keyboard: false});
    $('#' + modal).load(mapping);
}

function closeModuleInModal(modal) {
    $('#' + modal).modal('hide');
}

function pager(area, mapping, container) {
    area.find('.page-first').on('click', function (e) {
        var pageSize = area.find('.page-size').val();
        var payload = area.find('.table-payload').val();
        loadModule(mapping + '?pageNum=1&pageSize=' + pageSize + "&payload=" + payload, container);
    });
    area.find('.page-last').on('click', function (e) {
        var pageNum = area.find('.page-max').val();
        var pageSize = area.find('.page-size').val();
        var payload = area.find('.table-payload').val();
        loadModule(mapping + '?pageNum=' + pageNum + '&pageSize=' + pageSize + "&payload=" + payload, container);
    });
    area.find('.page-pre').on('click', function (e) {
        var pageNum = area.find('.page-num').val() - 1;
        var pageSize = area.find('.page-size').val();
        var payload = area.find('.table-payload').val();
        loadModule(mapping + '?pageNum=' + pageNum + '&pageSize=' + pageSize + "&payload=" + payload, container);
    });
    area.find('.page-next').on('click', function (e) {
        var pageNum = area.find('.page-num').val() + 1;
        var pageSize = area.find('.page-size').val();
        var payload = area.find('.table-payload').val();
        loadModule(mapping + '?pageNum=' + pageNum + '&pageSize=' + pageSize + "&payload=" + payload, container);
    });
    area.find('.page-index').on('click', function (e) {
        var pageNum = area.find(this).val();
        var pageSize = area.find('.page-size').val();
        var payload = area.find('.table-payload').val();
        loadModule(mapping + '?pageNum=' + pageNum + '&pageSize=' + pageSize + "&payload=" + payload, container);
    });
    area.find('.page-size').on('change', function(e){
        var pageSize = $(this).val();
        var payload = area.find('.table-payload').val();
        loadModule(mapping + '?pageNum=1&pageSize=' + pageSize + "&payload=" + payload, container);
    });
}