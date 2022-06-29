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
                    data-bs-target="#detailRequest" onclick="modalRequest(${data.id})" >` + row.employee.name + `</a>`
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