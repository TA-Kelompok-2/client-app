$(document).ready(function () {
    $("#tbEMP").DataTable({
        "ajax": {
            "url": "/request/get-all",
            "dataSrc": ""
        },
        "columns": [{
            "data": "id"
        },
        {
            "data": "employee.name"
        },
        {
            "data": "date"
        },
        {
            "data": "gambar"
        },
        {
            "data": "keterangan"
        },
        {
            "data": "fasilitasRuang.id"
        },
        {
            "data": "status.name"
        },
        {
            "data": null,
            render: function (data, type, row, meta) {
                return `
                        <button class="btn btn-rounded btn-info" onclick="deleteFasilitas(${data.id})"><span class="btn-icon-start text-info"><i class="fa fa-check"></i></span>Approve</button>
                        <button class="btn btn-rounded btn-success" onclick="selesai(${data.id})"><span class="btn-icon-start text-success"><i class="fa fa-check"></i></span>Done</button>`
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

function deleteFasilitas(id) {
    let approve = 6
    let reject = 7

    Swal.fire({
        title: 'Are you sure?',
        text: "Select your approval",
        icon: 'warning',
        showDenyButton: true,
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        denyButtonText:'Reject',
        confirmButtonText: 'Approve'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "PUT",
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
        } else if (result.isDenied){
            $.ajax({
                method: "PUT",
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

function selesai(id) {
    let done = 5

    Swal.fire({
        title: 'Are you sure?',
        text: "Select your approval",
        icon: 'info',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Done'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "PUT",
                url: "request/updateRequest/" + id,
                dataType: "json",
                beforeSend: addCsrfToken(),
                data: JSON.stringify({
                    status: done,
                }),
                contentType: "application/json",
                success: (result) => {
                    Swal.fire(
                        'Done!',
                        'Request has been done.',
                        'success'
                    )
                    $('#tbEMP').DataTable().ajax.reload()
                }
            })
        }
    })
}