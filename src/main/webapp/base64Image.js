
var handleFileSelect = function (evt) {
    var files = evt.target.files;
    var file = files[0];

    if (files && file) {
        var reader = new FileReader();

        reader.onload = function (readerEvt) {
            var binaryString = readerEvt.target.result;
            //btoa() *Decodifica una cadena de datos que ha sido codificada en base-64.
            //document.getElementById("responsearea").value = btoa(binaryString);
            
            let myHeaders = new Headers();
            myHeaders.append("Content-Type", "application/octet-stream");

            var requestOptions = {
                method: 'POST',
                headers: myHeaders,
                body: btoa(binaryString),
                redirect: 'follow'
            };

            fetch("http://localhost:8084/fidelizados/api/addImage/", requestOptions)
                    .then((response) => response.json())
                    .then(function (data) {
                        document.getElementById("responsearea").value = JSON.stringify(data, null, 2);
                    })
                    .catch(error => console.log('error', error));
        };

        reader.readAsBinaryString(file);
    }
};

if (window.File && window.FileReader && window.FileList && window.Blob) {
    document.getElementById('filePicker').addEventListener('change', handleFileSelect, false);
} else {
    alert('The File APIs are not fully supported in this browser.');
}

