$(document).ready(function () {
    $("#tbEMP").DataTable({
        "ajax": {
            "url": "/fasilitasruang/get-all",
            "dataSrc": ""
        },
        "columns": [{
                "data": null,
                render: function (data, type, row, meta) {
                    return meta.row + meta.settings._iDisplayStart + 1;
                }
            },
            {
                "data": "ruang.name"
            },
            {
                "data": "fasilitas.name"
            },
            {
                "data": null,
                render: function (data, type, row, meta) {
                    return `<button class="btn btn-rounded btn-primary" onclick = "modalEmployee(${data.id})" data-bs-toggle="modal" data-bs-target="#detailHistory"><span class="btn-icon-start text-primary"><i class="fa fa-info"></i>
                        </span>Detail</button>
                        <button class="btn btn-rounded btn-danger" onclick="deleteFasilitas(${data.id})"><span class="btn-icon-start text-danger"><i class="fa fa-trash"></i></span>Delete</button>`
                }
            }
        ],
        language: {
            paginate: {
                next: '<i class="fa fa-angle-double-right" aria-hidden="true"></i>',
                previous: '<i class="fa fa-angle-double-left" aria-hidden="true"></i>'
            }
        }
    });
});

function modalEmployee(id) {
    $.ajax({
        type: 'GET',
        url: "/fasilitas/getById/" + id,
        dataType: 'json',
        contentType: ''
    }).done((result) => {
        $('#id').text(result.id);
        $('#name').text(result.name);
        $('#keterangan').text(result.keterangan);
    }).fail((error) => {
        console.log(error);
    });
}

$('#createFasilitas').click(function (e) { //modal btn save
    let ruang_id = $('#ruang').val()
    let fasilitas_id = $('#fasilitas').val()
    console.log(ruang_id)
    console.log(fasilitas_id)
    $.ajax({
        method: "POST",
        url: "fasilitasruang/createFasilitasRuang",
        dataType: "json",
        beforeSend: addCsrfToken(),
        data: JSON.stringify({
            ruang_id: ruang_id,
            fasilitas_id: fasilitas_id,
        }),
        contentType: "application/json",
        success: function (result) {
            $('#tbEMP').DataTable().ajax.reload()
            $("#addHistory").modal('hide')
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'Fasilitas and Ruang has been created',
                showConfirmButton: false,
                timer: 1500
            })
        }
    })
})

function deleteFasilitas(id) {
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't delete this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "DELETE",
                url: "fasilitasruang/deleteFasilitasRuang/" + id,
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