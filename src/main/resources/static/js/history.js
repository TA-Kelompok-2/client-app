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
                    data-bs-target="#detailHistory" onclick="modalHistories(${data.id})" >` + row.employee.name + `</a>`
                }
            },
            {
                "data": "request.fasilitasRuang.ruang.name"
            },
            {
                "data": "request.fasilitasRuang.fasilitas.name"
            },
            {
                "data": "keterangan"
            },
            {
                "data": "status.name"
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
        //formate date
        let date = new Date(Date.parse(result.date));

        dateFormatted = date.toLocaleString('default', {
            day: 'numeric',
            month: 'long',
            year: 'numeric'
        });
        // console.log(dateFormatted);
        
        $('#date').text(dateFormatted);
        $('#fasilitas').text(result.request.fasilitasRuang.fasilitas.name);
        $('#ruang').text(result.request.fasilitasRuang.ruang.name);
        $('#keterangan').text(result.request.keterangan);
    }).fail((error) => {
        console.log(error);
    });
}

function modalHistories(id) {
    $(document).ready(function () { 
        $("#tbHST").DataTable({
            "ajax": {
                "url": "/history/getByStatus/" + id,
                "dataSrc": ""
            },
            "columns": [{
                    "data": null,
                    render: function (data, type, row, meta) {
                        return meta.row + meta.settings._iDisplayStart + 1;
                    }
                },
                {
                    "data": "request.fasilitasRuang"
                },
                {
                    "data": "request.fasilitas"
                },
                {
                    "data": "keterangan"
                },
                {
                    "data": "status.name"
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