
'use strict';
(function (){

    var upLoadBtn = document.querySelector('.loadFilesBtn');
    var upLoadInput = document.querySelector('.loadFilesInput');
    var upLoadBtnLabel = document.querySelector('.buttonText');

    /*Переносим нажатие кнопки ЗАГРУЗИТЬ ДОКУМЕНТЫ на элемент input*/
    upLoadBtn.addEventListener('click', function() {
        upLoadInput.click();
    });

    /*Изменяем название кнопки ЗАГРУЗИТЬ ДОКУМЕНТЫ*/
    upLoadInput.addEventListener('change', function() {
        if (upLoadInput.files.length > 0) 
            upLoadBtnLabel.innerText = 'ЗАГРУЖЕНО ДОКУМЕНТОВ: ' + upLoadInput.files.length;
        else 
            upLoadBtnLabel.innerText ='ЗАГРУЗИТЬ ДОКУМЕНТЫ';
    });

})();