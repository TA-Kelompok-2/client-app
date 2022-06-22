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
                "data": null,
                render: function (data, type, row, meta) {
                    return `<button class="btn btn-primary" onclick = "modalCountry(${data.id})" data-bs-toggle="modal" data-bs-target="#detailCountry"><i class="fa fa-info"></i></button>
                        <button type = "button" data-bs-toggle = "modal" data-bs-target = "#editCountry" class = "btn btn-warning" onclick = "beforeUpdate(${data.id})"><i class="fa fa-edit" style="font-size:15px"></i></button>
                        <button type="button" class="btn btn-danger fst-normal px-3" onclick="deleteCountry(${data.id})"><i class="fa fa-trash"></i></button>`
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

function modalCountry(id) {
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
    }).fail((error) => {
        console.log(error);
    });
}

$('#createCountry').click(function (e) { //modal btn save
    let codeinp = $('#codeInput').val()
    let nameinp = $('#nameInput').val()
    let regioninp = $('#countryRegionInput').val()
    $.ajax({
        method: "POST",
        url: "country/createCountry",
        dataType: "json",
        beforeSend: addCsrfToken(),
        data: JSON.stringify({
            code: codeinp,
            name: nameinp,
            regionId: regioninp
        }),
        contentType: "application/json",
        success: function (result) {
            $('#tbEMP').DataTable().ajax.reload()
            $("#addCountry").modal('hide')
            Swal.fire({
                position: 'center',
                icon: 'success',
                title: 'Country has been created',
                showConfirmButton: false,
                timer: 1500
            })
        }
    })
})

function beforeUpdate(id) {
    $.ajax({
        method: "GET",
        url: "country/getById/" + id,
        dataType: "json",
        contentType: "application/json",
        success: function (result) {
            $("#editId").val(result.id) //selector, set value, data
            $("#codeEdit").val(result.code)
            $("#nameEdit").val(result.name)
            $("#countryRegionEdit").val(result.region.id)
        }
    })
}


$("#updateCountry").click(() => {
    let id = $("#editId").val() // variable = selector, get
    let code = $("#codeEdit").val()
    let name = $("#nameEdit").val()
    let regionId = $("#countryRegionEdit").val()

    $("#editCountry").modal('hide')

    Swal.fire({
        title: 'Are you sure!',
        text: "Do you want to change this country?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "PUT",
                url: "country/updateCountry/" + id,
                dataType: "json", //jenis datanya
                beforeSend: addCsrfToken(),
                data: JSON.stringify({ //objek dalam bentuk json
                    code: code, //key: value
                    name: name,
                    regionId: regionId
                }),
                contentType: "application/json", // data yang dikirim dalam bentuk json 
                success: function (result) {
                    console.log(result)
                    $('#tbEMP').DataTable().ajax.reload()
                    $("#editCountry").modal('hide')
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
    })
})

function deleteCountry(id) {
    Swal.fire({
        title: 'Are you sure?',
        text: "You won't delete this country!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                method: "DELETE",
                url: "country/deleteCountry/" + id,
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