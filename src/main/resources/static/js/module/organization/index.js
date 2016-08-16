/**
 * Created by hanlei6 on 2016/7/21.
 */
var organizationTree;
$(document).ready(function () {
    flushComponents();
    flushCheckboxRadio();
    organizationTree = $.fn.zTree.init($("#organizationTree"), {
        view: {
            selectedMulti: false
        },
        async: {
            enable: true,
            url: "../organization/tree",
            autoParam: ["id"]
        },
        callback: {
            onClick: function (event, treeId, treeNode) {
                loadModule( '/organization/view?id=' + treeNode.id, 'organizationForm');
            }
        }
    });

    $('.btn_delete').on('click', function () {
        var ids = [];
        var nodes = organizationTree.getSelectedNodes();
        if (nodes.length == 0) {
            $.notify({
                title: "<strong>错误!</strong>:",
                message: "请选择要删除的节点"
            }, {
                type: 'warning'
            });
        } else {
            $.ajax({
                url: '/organization/delete',
                type: 'POST',
                data: {ids: ids.join(",")},
                success: function (res) {
                    if (res.status == 200) {
                        organizationTree.reAsyncChildNodes(nodes[0].getParentNode(), 'refresh');
                        closeModuleInModal('deleteModal');
                    } else {

                    }
                }
            });
        }
    });
});

function deleteSelected() {
    if ($('.list-check:checked').size() > 0) {
        $('#deleteModal').modal({backdrop: 'static', keyboard: false});
    }
}

function addOrganization() {
    var nodes = organizationTree.getSelectedNodes();
    if (nodes.length == 0) {
        $.notify({
            title: "<strong>错误!</strong>:",
            message: "请选择上级组织机构"
        }, {
            type: 'warning'
        });
    } else {
        loadModule('/organization/add', 'organizationForm');
    }
}

function editOrganization() {
    var nodes = organizationTree.getSelectedNodes();
    if (nodes.length == 0) {
        $.notify({
            title: "<strong>错误!</strong>:",
            message: "请选择要修改的节点"
        }, {
            type: 'warning'
        });
    } else {
        loadModule('/organization/edit?id=' + nodes[0].id, 'organizationForm');
    }
}

