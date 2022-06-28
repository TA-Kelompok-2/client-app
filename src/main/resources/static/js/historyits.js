$(document).ready(function () {
    $("#tbEMP").DataTable({
        "ajax": {
            "url": "/request/approvalits",
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
                "data": "fasilitasRuang.ruang.name"
            },
            {
                "data": "fasilitasRuang.fasilitas.name"
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
}

function tutup(){
    window.location.reload();
}

$("#approve").click(() => {
    let keterangan = $("#reason").val()
    console.log(keterangan)
    let date = $("#date").val()
    let id = $("#id").val()
    let approve = 5

    $.ajax({
        method: "POST",
        url: "request/updateRequest/" + id,
        dataType: "json",
        beforeSend: addCsrfToken(),
        data: JSON.stringify({
            status: approve,
            keterangan: keterangan,
            date: date
        }),
        contentType: "application/json",
        success: function (result) {
            console.log(result)
            $("#detailRequest").modal('hide')
            $('#tbEMP').DataTable().ajax.reload()
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'Data has been changed',
                showConfirmButton: false,
                timer: 1500
            })
        }
    })
})