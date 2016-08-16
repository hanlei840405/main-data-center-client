var organizationTreeForSelectUser;
$(document).ready(function () {
    organizationTreeForSelectUser = $.fn.zTree.init($("#organizationTreeForSelectUser"), {
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
                loadModule('/user/select?pageNum=1&pageSize=10&organizationId=' + treeNode.id, 'user-select-grid');
            }
        }
    });
    $('#selectUserModal').on('show.bs.modal', function () {
        var nodes = organizationTreeForSelectUser.getNodes();
        if (nodes.length > 0) {
            loadModule('/user/select?pageNum=1&pageSize=10&organizationId=' + nodes[0].id, 'user-select-grid');
        } else {
            loadModule('/user/select?pageNum=1&pageSize=10', 'user-select-grid');
        }
    })
});

function selectUserCallback(id, name) {
    $('#userId').val(id);
    $('#username').val(name);
}