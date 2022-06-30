$(document).ready(function () {
    $("#tbEMP").DataTable({
        "ajax": {
            "url": "/ruang/get-all",
            "dataSrc": ""
        },
        "columns": [{
                "data": "id"
            },
            {
                "data": "name"
            },
            {
                "data": "lantai"
            },
            {
                "data": null,
                render: function (data, type, row, meta) {
                    return `<button class="btn btn-rounded btn-primary" onclick = "modalRuang(${data.id})" data-bs-toggle="modal" data-bs-target="#detailRuang"><span class="btn-icon-start text-primary"><i class="fa fa-info"></i>
                        </span>Detail</button>
                        <button class="btn btn-rounded btn-danger" onclick="deleteRuang(${data.id})"><span class="btn-icon-start text-danger"><i class="fa fa-trash"></i></span>Delete</button>`
                },
                "orderable": false
            }
        ],
        language: {
            paginate: {
                next: '<i class="fa fa-angle-double-right" aria-hidden="true"></i>',
                previous: '<i class="fa fa-angle-double-left" aria-hidden="true"></i>'
            }
        }
        // console.log()
    });
});

function modalRuang(id) {
    $.ajax({
        type: 'GET',
        url: "/ruang/getById/" + id,
        dataType: 'json',
        contentType: ''
    }).done((result) => {
        $('#id').text(result.id);
        $('#name').text(result.name);
        $('#lantai').text(result.lantai);
    }).fail((error) => {
        console.log(error);
    });
}

$('#createRuang').click(function (e) { //modal btn save
    let name = $('#nameInp').val()
    let lantai = $('#lantaiInp').val()
    $.ajax({
        method: "POST",
        url: "ruang/createRuang",
        dataType: "json",
        beforeSend: addCsrfToken(),
        data: JSON.stringify({
            name: name,
            lantai: lantai
        }),
        contentType: "application/json",
        success: function (result) {
            $('#tbEMP').DataTable().ajax.reload()
            $("#addRuang").modal('hide')
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'Ruang has been created',
                showConfirmButton: false,
                timer: 1500
            })
        }
    })
})

function deleteRuang(id) {
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't delete this Ruang!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "DELETE",
                url: "ruang/deleteRuang/" + id,
                dataType: "json",
                beforeSend: addCsrfToken(),
                contentType: "application/json",
                success: (result) => {
                    Swal.fire(
                        'Deleted!',
                        'Your file has been deleted.',
                        'success'
                    )
                    $('#tbEMP').DataTable().ajax.reload()
                }
            })
        }
    })
}