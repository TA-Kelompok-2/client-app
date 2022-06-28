
// When an option is changed, search the above for matching choices
$('#options').on('change', function () {
    // Set selected option as variable
    var selectValue = $(this).val();
    console.log(selectValue)

    var lookup = {
        'Option 1': ['Option 1 - Choice 1', 'Option 1 - Choice 2', 'Option 1 - Choice 3'],
        'Option 2': ['Option 2 - Choice 1', 'Option 2 - Choice 2'],
        'Option 3': ['Option 3 - Choice 1'],
    };
    // Empty the target field
    $('#choices').empty();

    // For each chocie in the selected option
    for (i = 0; i < lookup[selectValue].length; i++) {
        // Output choice in the target field
        $('#choices').append("<option value='" + lookup[selectValue][i] + "'>" + lookup[selectValue][i] + "</option>");
    }
});

// // When an option is changed, search the above for matching choices
// $('#ruang').on('change', function () {
//     // Set selected option as variable
//     var selectValuem = $(this).val();
//     console.log(selectValuem)

//     var ruangan = $(this).val('options', $('#ruang option').clone());
//     console.log(ruangan);
//     var ruangans = $(this).data('options', $('#fasilitasruang option').clone());
//     console.log(ruangans);

//     var lookup = {
//         1: ['Option 1 - Choice 1', 'Option 1 - Choice 2', 'Option 1 - Choice 3'],
//         2: ['Option 2 - Choice 1', 'Option 2 - Choice 2'],
//         'Option 3': ['Option 3 - Choice 1'],
//     };
//     // Empty the target field
//     $('#fasilitasruang').empty();

//     // For each chocie in the selected option
//     for (i = 0; i < lookup[selectValuem].length; i++) {
//         // Output choice in the target field
//         $('#fasilitasruang').append("<option value='" + lookup[ruangan][i] + "'>" + lookup[ruangan][i] + "</option>");
//     }
// });

$("#select1").change(function () {
    let tes =  $(this).data('options')
    console.log(tes)
    if (tes === undefined) {
        /*Taking an array of all options-2 and kind of embedding it on the select1*/
        $(this).data('options', $('#select2 option').clone());
    }
    var id = $(this).val();
    var options = $(this).data('options').filter('[value=' + id + ']');
    $('#select2').html(options);
});
$("#ruang").change(function () {
    let tes =  $(this).data('options')
    console.log(tes)
    if (tes === undefined) {
        /*Taking an array of all options-2 and kind of embedding it on the select1*/
        $(this).data('options', $('#fasilitasruang option').clone());
    }
    var id = $(this).val();
    var options = $(this).data('options').filter('[value=' + id + ']');
    $('#fasilitasruang').html(options);
});