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
    $('#' + modal).modal({
        keyboard: true
    });
    $('#' + modal).load(mapping);
}

function closeModuleInModal(modal) {
    $('#' + modal).modal('hide');
}

function pager(mapping, container) {
    $('.page-first').on('click', function (e) {
        var pageSize = $('.page-size').val();
        var payload = $('.table-payload').val();
        loadModule(mapping + '?pageNum=1&pageSize=' + pageSize + "&payload=" + payload, container);
    });
    $('.page-last').on('click', function (e) {
        var pageNum = $('.page-max').val();
        var pageSize = $('.page-size').val();
        var payload = $('.table-payload').val();
        loadModule(mapping + '?pageNum=' + pageNum + '&pageSize=' + pageSize + "&payload=" + payload, container);
    });
    $('.page-pre').on('click', function (e) {
        var pageNum = $('.page-num').val() - 1;
        var pageSize = $('.page-size').val();
        var payload = $('.table-payload').val();
        loadModule(mapping + '?pageNum=' + pageNum + '&pageSize=' + pageSize + "&payload=" + payload, container);
    });
    $('.page-next').on('click', function (e) {
        var pageNum = $('.page-num').val() + 1;
        var pageSize = $('.page-size').val();
        var payload = $('.table-payload').val();
        loadModule(mapping + '?pageNum=' + pageNum + '&pageSize=' + pageSize + "&payload=" + payload, container);
    });
    $('.page-index').on('click', function (e) {
        var pageNum = $(this).val();
        var pageSize = $('.page-size').val();
        var payload = $('.table-payload').val();
        loadModule(mapping + '?pageNum=' + pageNum + '&pageSize=' + pageSize + "&payload=" + payload, container);
    });
    $('.page-size').on('change', function(e){
        var pageSize = $(this).val();
        var payload = $('.table-payload').val();
        loadModule(mapping + '?pageNum=1&pageSize=' + pageSize + "&payload=" + payload, container);
    });
}