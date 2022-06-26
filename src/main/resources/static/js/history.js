$(document).ready(function () {
    $("#tbEMP").DataTable({
        "ajax": {
            "url": "/history/get-all",
            "dataSrc": ""
        },
        "columns": [{
                "data": null,
                render: function (data, type, row, meta) {
                    return meta.row + meta.settings._iDisplayStart + 1;
                }
            },
            {
                "data": null,
                render: function (data, type, row, meta) {
                    return `<a class="text-info" href="historyapprove" data-bs-toggle="modal"
                    data-bs-target="#detailRequest" onclick="modalHistory(${data.id})" onclick="approve(${data.id})">` + row.request.employee.name + `</a>`
                }
            },
            {
                "data": "request.id"
            },
            {
                "data": "request.date"
            },
            {
                "data": "request.fasilitasRuang.fasilitas.name"
            },
            {
                "data": "request.fasilitasRuang.ruang.name"
            },
            {
                "data": "request.keterangan"
            },
            {
                "data": "request.status.name"
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

function modalHistory(id) {
    $.ajax({
        type: 'GET',
        url: "/history/getById/" + id,
        dataType: 'json',
        contentType: ''
    }).done((result) => {
        $('#name').text(result.request.employee.name);
        $('#request_id').text(result.request.id);
        $('#date').text(result.request.date);
        $('#fasilitas').text(result.request.fasilitasRuang.fasilitas.name);
        $('#ruang').text(result.request.fasilitasRuang.ruang.name);
        $('#keterangan').text(result.request.keterangan);
    }).fail((error) => {
        console.log(error);
    });
}

function approve(id) {
    let status = 2
    console.log(id)

    $.ajax({
        method: "PUT",
        url: "request/getById/" + id,
        dataType: "json",
        beforeSend: addCsrfToken(),
        data: JSON.stringify({
            status: status,
        }),
        contentType: "application/json", // data yang dikirim dalam bentuk json 
        success: function (result) {
            console.log(result)
            $('#tbEMP').DataTable().ajax.reload()
            $("#detailRequest").modal('hide')
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'Country has been changed',
                showConfirmButton: false,
                timer: 1500
            })
        }
    })
}