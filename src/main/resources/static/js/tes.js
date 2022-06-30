var minDate, maxDate;

// Custom filtering function which will search data in column four between two values
$.fn.dataTable.ext.search.push(
    function (settings, data, dataIndex) {
        var min = minDate.val();
        var max = maxDate.val();
        var date = new Date(data[2]);
        console.log(date)
        if (
            (min === null && max === null) ||
            (min === null && date <= max) ||
            (min <= date && max === null) ||
            (min <= date && date <= max)
        ) {
            return true;
        }
        return false;
    }
);

$(document).ready(function () {
    // Create date inputs
    minDate = new DateTime($('#min'), {
        format: 'MMMM DD YYYY'
    });
    maxDate = new DateTime($('#max'), {
        format: 'MMMM DD YYYY'
    });

    // DataTables initialisation
    var table = $("#tbEMP").DataTable({
        "ajax": {
            "url": "/request/get-all",
            "dataSrc": "",
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
                "data": "date",
                render: function (data, type, row, meta) {
                    return moment(row.date).format('DD MMMM YYYY, h:mm:ss a');

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

    // Refilter the table
    $('#min, #max').on('change', function () {
        table.draw();
    });
});