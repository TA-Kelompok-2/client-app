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
                    data-bs-target="#detailHistory" onclick="modalHistories(${data.id})" onclick="beforeDone(${data.id})" >` + row.employee.name + `</a>`
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
        $('#requestGambar').html(`
          <img src="/request-photos/${result.id}/${result.gambar}"  width="300" />
        `);
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

function beforeDone(id) {
    $.ajax({
        type: 'GET',
        url: "/history/getById/" + id,
        dataType: 'json',
        contentType: ''
    }).done((result) => {
        $('#id').val(result.request.id);
    }).fail((error) => {
        console.log(error);
    });
}

$("#approve").click(() => {
    let keterangan = $("#reason").val()
    console.log(keterangan)
    let picName = $("#picName").val()
    console.log(picName)
    
    let date = Date.now
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
            date: date,
            picName: picName

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