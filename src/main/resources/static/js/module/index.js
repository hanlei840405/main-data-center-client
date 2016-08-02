/**
 * Created by hanlei6 on 2016/7/21.
 */
$(document).ready(function () {
    $.ajaxSetup({
        cache:false
    });
});
function loadModule(mapping) {
    $('#content').load(mapping);
}

function openModuleInModal(modal, mapping) {
    $('#' + modal).modal({
        keyboard: true
    });
    $('#addModal').load(mapping);
}

function closeModuleInModal(modal) {
    $('#' + modal).modal('hide');
}