$(document).ready(function () {
    $("#tbEMP").DataTable({
        "ajax": {
            "url": "/request/get-all",
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
                "data": null,
                render: function (data, type, row, meta) {
                        if (row.status.name == "Diajukan") {
                            return `<span class="badge badge-info">` + row.status.name + `</span> `
                        } else if (row.status.name == "Disetujui oleh Admin" || row.status.name == "Disetujui oleh IT Support") {
                            return `<span class="badge badge-warning">` + row.status.name + `</span> `
                        } else if (row.status.name == "Ditolak oleh Admin" || row.status.name == "Ditolak oleh IT Support") {
                            return `<span class="badge badge-danger">` + row.status.name + `</span> `
                        } else if (row.status.name == "Diproses") {
                            return `<span class="badge badge-primary">` + row.status.name + `</span> `
                        } else if (row.status.name == "Selesai") {
                            return `<span class="badge badge-success">` + row.status.name + `</span> `
                        } else {
                            return `<span class="badge badge-warning">` + row.status.name + `</span> `
                        }
                    
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
                    "data": "picName"
                },
                {
                    "data": "date",
                    render: function(data, type, row, meta) {
                        return moment(row.date).format('DD MMMM YYYY, LT');
                        
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
                    "data": null,
                    render: function (data, type, row, meta) {
                            if (row.status.name == "Diajukan") {
                                return `<span class="badge badge-info">` + row.status.name + `</span> `
                            } else if (row.status.name == "Disetujui oleh Admin" || row.status.name == "Disetujui oleh IT Support") {
                                return `<span class="badge badge-warning">` + row.status.name + `</span> `
                            } else if (row.status.name == "Ditolak oleh Admin" || row.status.name == "Ditolak oleh IT Support") {
                                return `<span class="badge badge-danger">` + row.status.name + `</span> `
                            } else if (row.status.name == "Diproses") {
                                return `<span class="badge badge-primary">` + row.status.name + `</span> `
                            } else if (row.status.name == "Selesai") {
                                return `<span class="badge badge-success">` + row.status.name + `</span> `
                            } else {
                                return `<span class="badge badge-warning">` + row.status.name + `</span> `
                            }
                        
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
}

function tutup(){
    window.location.reload();
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