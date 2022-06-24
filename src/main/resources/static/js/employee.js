$(document).ready(function () {
    $("#tbEMP").DataTable({
        "ajax": {
            "url": "/employee/get-all",
            "dataSrc": ""
        },
        "columns": [{
                "data": "id"
            },
            {
                "data": "name"
            },
            {
                "data": "email"
            },
            {
                "data": "phoneNumber"
            },
            {
                "data": "user.roles[<br>].name"
            },
            {
                "data": null,
                render: function (data, type, row, meta) {
                    return `<button class="btn btn-rounded btn-primary" onclick = "modalEmployee(${data.id})" data-bs-toggle="modal" data-bs-target="#detailEmployee"><span class="btn-icon-start text-primary"><i class="fa fa-info"></i>
                        </span>Detail</button>
                        <button class="btn btn-rounded btn-danger" onclick="deleteEmployee(${data.id})"><span class="btn-icon-start text-danger"><i class="fa fa-trash"></i></span>Delete</button>`
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
        url: "/employee/getById/" + id,
        dataType: 'json',
        contentType: ''
    }).done((result) => {
        $('#id').text(result.id);
        $('#name').text(result.name);
        $('#email').text(result.email);
        $('#phoneNumber').text(result.phoneNumber);
        $('#role').text(result.user.roles[0].name);
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
                success: function (result) {
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