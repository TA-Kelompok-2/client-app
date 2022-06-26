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
                "data": "request.employee.phoneNumber"
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
        $('#phoneNumber').text(result.request.employee.phoneNumber);
        $('#date').text(result.request.date);
        $('#fasilitas').text(result.request.fasilitasRuang.fasilitas.name);
        $('#ruang').text(result.request.fasilitasRuang.ruang.name);
        $('#keterangan').text(result.request.keterangan);
    }).fail((error) => {
        console.log(error);
    });
}

$('#createEmployee').click(function (e) { //modal btn save
    let name = $('#nameInp').val()
    let email = $('#emailInp').val()
    let phoneNumber = $('#phoneNumberInp').val()
    let username = $('#usernameInp').val()
    let password = $('#passwordInp').val()
    $.ajax({
        method: "POST",
        url: "employee/createEmployee",
        dataType: "json",
        beforeSend: addCsrfToken(),
        data: JSON.stringify({
            name: name,
            email: email,
            phoneNumber: phoneNumber,
            username: username,
            password: password,
        }),
        contentType: "application/json",
        success: function (result) {
            $('#tbEMP').DataTable().ajax.reload()
            $("#addEmployee").modal('hide')
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'Employee has been created',
                showConfirmButton: false,
                timer: 1500
            })
        }
    })
})

function deleteEmployee(id) {
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't delete this employee!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "DELETE",
                url: "employee/deleteEmployee/" + id,
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