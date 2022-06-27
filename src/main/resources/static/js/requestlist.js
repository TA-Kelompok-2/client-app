$(document).ready(function () {
    $("#tbEMP").DataTable({
        "ajax": {
            "url": "/request/approval/1" ,
            "dataSrc": ""
        },
        "columns": [{
            "data": "id"
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
            "data": "employee.name"
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
                        <button class="btn btn-rounded btn-success" onclick="deleteFasilitas(${data.id})"><span class="btn-icon-start text-success"><i class="fa fa-check"></i></span>Approve</button>`
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
    let approve = 2
    let reject = 3

    Swal.fire({
        title: 'Are you sure?',
        text: "Select your approval",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        cancelButtonText:'Reject',
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
        } else{
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