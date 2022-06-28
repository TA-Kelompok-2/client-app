$(document).ready(function () {
    $("#tbEMP").DataTable({
        "ajax": {
            "url": "/request/approvaladmin",
            "dataSrc": ""
        },
        "columns": [{
                "data": null,
                render: function (data, type, row, meta) {
                    return meta.row + meta.settings._iDisplayStart + 1;
                }
            },
            {
                "data": "employee.name"
            },
            {
                "data": "keterangan"
            },
            {
                "data": "fasilitasRuang.ruang.name"
            },
            {
                "data": "fasilitasRuang.fasilitas.name"
            },
            {
                "data": "status.name"
            },
            {
                "data": null,
                render: function (data, type, row, meta) {
                    return `
                        <button class="btn btn-rounded btn-success" onclick="modalRequest(${data.id})" data-bs-toggle="modal"
                        data-bs-target="#detailRequest"><span class="btn-icon-start text-success"><i class="fa fa-check"></i></span>Approval</button>`
                }
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

function modalRequest(id) {
    $.ajax({
        type: 'GET',
        url: "/request/getById/" + id,
        dataType: 'json',
        contentType: ''
    }).done((result) => {
        $('#idT').text(result.id);
        $('#id').val(result.id);
        $('#nameT').text(result.employee.name);
        $('#name').val(result.employee.name);

        let date = new Date(Date.parse(result.date));

        dateFormatted = date.toLocaleString('default', {
            day: 'numeric',
            month: 'long',
            year: 'numeric'
        });
        $('#dateT').text(dateFormatted);
        $('#date').val(dateFormatted);
        $('#ruangT').text(result.fasilitasRuang.ruang.name);
        $('#ruang').val(result.fasilitasRuang.ruang.name);
        $('#fasilitasT').text(result.fasilitasRuang.fasilitas.name);
        $('#fasilitas').val(result.fasilitasRuang.fasilitas.name);
        $('#keteranganT').text(result.keterangan);
        $('#keterangan').val(result.keterangan);
    }).fail((error) => {
        console.log(error);
    });
}

$('#createFasilitas').click(function (e) { //modal btn save
    let name = $('#nameInp').val()
    let keterangan = $('#keteranganInp').val()
    console.log(name)
    console.log(keterangan)
    $.ajax({
        method: "POST",
        url: "fasilitas/createFasilitas",
        dataType: "json",
        beforeSend: addCsrfToken(),
        data: JSON.stringify({
            name: name,
            keterangan: keterangan,
        }),
        contentType: "application/json",
        success: function (result) {
            $('#tbEMP').DataTable().ajax.reload()
            $("#addHistory").modal('hide')
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'Fasilitas has been created',
                showConfirmButton: false,
                timer: 1500
            })
        }
    })
})

$("#approve").click(() => {
    let keterangan = $("#reason").val()
    let date = $("#date").val()
    let id = $("#id").val()
    console.log(keterangan)
    console.log(id)
    console.log(date)
    let approve = 2
    let reject = 3

    $.ajax({
        method: "POST",
        url: "request/updateRequest/" + id,
        dataType: "json",
        beforeSend: addCsrfToken(),
        data: JSON.stringify({
            status: approve,
            keterangan: keterangan
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
$("#reject").click(() => {
    let keterangan = $("#reason").val()
    let id = $("#id").val()
    let approve = 2
    let reject = 3

    $.ajax({
        method: "POST",
        url: "request/updateRequest/" + id,
        dataType: "json",
        beforeSend: addCsrfToken(),
        data: JSON.stringify({
            status: reject,
            keterangan: keterangan
        }),
        contentType: "application/json",
        success: function (result) {
            console.log(result)
            $("#detailRequest").modal('hide')
            $('#tbEMP').DataTable().ajax.reload()
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'Region has been changed',
                showConfirmButton: false,
                timer: 1500
            })
        }
    })
})

function deleteFasilitas(id) {
    let approve = 2
    let reject = 3

    Swal.fire({
        title: 'Are you sure?',
        text: "Select your approval",
        icon: 'warning',
        showDenyButton: true,
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        denyButtonText: 'Reject',
        confirmButtonText: 'Approve'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "POST",
                url: "request/updateRequest/" + id,
                dataType: "json",
                beforeSend: addCsrfToken(),
                data: JSON.stringify({
                    status: approve,
                }),
                contentType: "application/json",
                success: (result) => {
                    Swal.fire(
                        'Approved!',
                        'Request has been approved.',
                        'success'
                    )
                    $('#tbEMP').DataTable().ajax.reload()
                }
            })
        } else if (result.isDenied) {
            $.ajax({
                method: "POST",
                url: "request/updateRequest/" + id,
                dataType: "json",
                beforeSend: addCsrfToken(),
                data: JSON.stringify({
                    status: reject,
                }),
                contentType: "application/json",
                success: (result) => {
                    Swal.fire(
                        'Rejected!',
                        'Request has been rejected.',
                        'success'
                    )
                    $('#tbEMP').DataTable().ajax.reload()
                }
            })
        }
    })
}