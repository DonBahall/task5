$(document).ready(function () {
    $('.btn-delete').click(function () {
        var id = $(this).data('id');
        doAjaxDelete(id);
    });

    function doAjaxDelete(id) {
        $.ajax({
                type: "DELETE",
                url: "/delete/" + id,
                success: function (response) {
                    document.location.reload()
                },
                error: function (e) {
                    alert(e)
                }
            }
        )
        return false;
    }
});
