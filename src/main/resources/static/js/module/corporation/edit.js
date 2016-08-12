/**
 * Created by hanlei6 on 2016/8/2.
 */
$(document).ready(function () {
    flushComponents();
    flushCheckboxRadio();
    $('#provinceCode').bind('change', function (e) {
        $.ajax({
            url: '/common/cities',
            cache: true,
            data: {province: $(this).val()},
            success: function (res) {
                var cities = res.result;
                $('#cityCode').empty();
                debugger;
                for (var i = 0; i < cities.length; i++) {
                    var option = $('<option />');
                    option.val(cities[i].code);
                    option.append(cities[i].name);
                    $('#cityCode').append(option);
                }
                //$('#cityCode').next('.bootstrap-select').remove();
                flushComponents();
            }
        });
    })

    $('#corporation-edit-submit').bind('click', function () {
        var formData = new FormData($('#corporation-edit-form')[0]);
        formData.append( "province", $("#provinceCode").find("option:selected").text());
        formData.append( "city", $("#cityCode").find("option:selected").text());
        $.ajax({
            url: '/corporation/update',
            type: 'POST',
            data: formData,
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