
'use strict';

(function (){

    var signUpBtn = document.querySelector('.signUpBtn');
    var uploadZapisBtn = document.querySelector('.uploadZapisBtn');
    var relocateZapisBtn = document.querySelector('.relocateZapisBtn');
    var removeZapisBtn = document.querySelector('.removeZapisBtn');

    var openModalHref = document.querySelector('.openModalHref');
    var modalDialog = document.querySelector('.modalDialog');
    var modalDialogBody = document.querySelector('.modalDialogBody');
    var similarLabel = document.querySelector('.templateLabel').content.querySelector('.similarLabel');
    var similarNumberOfZapisInput = document.querySelector('.templateNumberOfZapisInput').content.querySelector('.controlNumberOfZapisInput');
    var similarСontrolTelefonInput = document.querySelector('.controlTelefonInput');
    var similarSendBtn = document.querySelector('.templateBtn').content.querySelector('.sendBtn');
    var similarLoadFilesBody = document.querySelector('.loadFilesBody');
    var similarCalendarBody = document.querySelector('.calendarBody');




    /*Очистка popUp*/
    var clearPopUp = function (){
        modalDialog.classList.remove('redColor');
        modalDialog.classList.remove('colorWeightHeaderFont');
        while (document.querySelector('.modalDialogBody').firstElementChild!=null){
            modalDialogBody.firstElementChild.remove();
        }
    }

    /*Валидация страницы при  нажатии кнопки ЗАПИСАТЬСЯ*/
    var singUpValidation = function (){
        clearPopUp();
        var isSingUpValidation = true;

        /*Проверка на выбор нотариального документа*/
        var listCheckBoxNotarialDocuments = document.querySelectorAll('.inputLiNotarialDocument');
        var isChecked = validationOfCheckBaxes(listCheckBoxNotarialDocuments);
        if (!isChecked){
            addNewLabel('Выберите действие (несколько действий)');
            isSingUpValidation = false;
        }

        /*проверка на выбор времени*/
        var listCheckBoxTimes = document.querySelectorAll('.inputLiTime');
        isChecked = validationOfCheckBaxes(listCheckBoxTimes);
        if (!isChecked){
            addNewLabel('Выберите дату и время');
            isSingUpValidation = false;
        }

        /*Проверка адреса объекта*/
        var addressInput = document.querySelector('.controlAddressInput');
        if (addressInput.validity.valueMissing){
            addNewLabel('Введите Адрес объекта');
            isSingUpValidation = false;
        }
        else if (addressInput.validity.tooShort){
            addNewLabel('Адрес объекта не может быть меньше 6-ти символов');
            isSingUpValidation = false;
        }
        else if (addressInput.validity.tooLong){
            addNewLabel('Адрес объекта не может быть ,jkmit 150-ти символов');
            isSingUpValidation = false;
        }

        /*Проверка телефона для связи*/
        var addressInput = document.querySelector('.controlTelefonInput');
        if (addressInput.validity.valueMissing){
            addNewLabel('Введите телефон для связи');
            isSingUpValidation = false;
        }

        /*валидация не пройдена*/
        if (!isSingUpValidation){
            modalDialog.classList.add('redColor');
        }
        else{
            modalDialog.classList.add('colorWeightHeaderFont');
            addNewLabel(document.querySelector('.youChoiceLabel').innerText);
            addNewLabel('Номер записи:');
        }

    }

    /*метод реализующий алгоритм событий после нажатия кнопки ДОСЛАТЬ ДОКУМЕНТЫ*/
    var clickUploadZapisBtnBtn = function (){
        clearPopUp();
        modalDialog.classList.add('colorWeightHeaderFont');
        addNewLabel('ДОСЛАТЬ ДОКУМЕНТЫ');
        addNewLabel('Введите номер записи:');
        var newInput = similarNumberOfZapisInput.cloneNode(true);
        modalDialogBody.appendChild(newInput);
        addNewLabel('Введите номер телефона (который указывали при регистрации записи):');
        var newControlTelefonInput = similarСontrolTelefonInput.cloneNode(true);
        newControlTelefonInput.id = 'newControlTelefonInput';
        $(function(){
            $("#newControlTelefonInput").mask("+7(999) 999-9999");
        });
        modalDialogBody.appendChild(newControlTelefonInput);
        var newLoadFilesBody = similarLoadFilesBody.cloneNode(true);
        modalDialogBody.appendChild(newLoadFilesBody);

        var newLoadFilesBtn = newLoadFilesBody.querySelector('.loadFilesBtn');
        var upLoadInput = newLoadFilesBody.querySelector('.loadFilesInput');
        var upLoadBtnLabel = newLoadFilesBody.querySelector('.buttonText');
        /*Переносим нажатие кнопки ЗАГРУЗИТЬ ДОКУМЕНТЫ на элемент input*/
        newLoadFilesBtn.addEventListener('click', function() {
            upLoadInput.click();
        });
        /*Изменяем название кнопки ЗАГРУЗИТЬ ДОКУМЕНТЫ*/
        upLoadInput.addEventListener('change', function() {
        if (upLoadInput.files.length > 0) 
            upLoadBtnLabel.innerText = 'ЗАГРУЖЕНО ДОКУМЕНТОВ: ' + upLoadInput.files.length;
        else 
            upLoadBtnLabel.innerText ='ЗАГРУЗИТЬ ДОКУМЕНТЫ';
        });

        addNewLabel('');
        var newSendBtn = similarSendBtn.cloneNode(true);
        modalDialogBody.appendChild(newSendBtn);
    }

    /*метод реализующий алгоритм событий после нажатия кнопки ПЕРЕНЕСТИ ЗАПИСЬ*/
    var clickRelocateZapisBtn = function (){
        clearPopUp();
        modalDialog.classList.add('colorWeightHeaderFont');
        addNewLabel('ДОСЛАТЬ ДОКУМЕНТЫ');
        /*
        addNewLabel('Введите номер записи:');
        var newInput = similarNumberOfZapisInput.cloneNode(true);
        modalDialogBody.appendChild(newInput);
        addNewLabel('Введите номер телефона (который указывали при регистрации записи):');
        var newControlTelefonInput = similarСontrolTelefonInput.cloneNode(true);
        newControlTelefonInput.id = 'newControlTelefonInput';
        $(function(){
            $("#newControlTelefonInput").mask("+7(999) 999-9999");
        });
        modalDialogBody.appendChild(newControlTelefonInput);


        var newCalendarBody = similarCalendarBody.cloneNode(true);
        modalDialogBody.appendChild(newCalendarBody);
        */
        addNewLabel('Этот сервис в Разработке');
        /*
        var newSendBtn = similarSendBtn.cloneNode(true);
        modalDialogBody.appendChild(newSendBtn);*/
    }

    /*метод реализующий алгоритм событий после нажатия кнопки ОТМЕНИТЬ ЗАПИСЬ*/
    var clickRemoveZapisBtn = function (){
        clearPopUp();
        modalDialog.classList.add('colorWeightHeaderFont');
        addNewLabel('ОТМЕНИТЬ ЗАПИСЬ');
        addNewLabel('Введите номер записи:');
        var newInput = similarNumberOfZapisInput.cloneNode(true);
        modalDialogBody.appendChild(newInput);
        addNewLabel('Введите номер телефона (который указывали при регистрации записи):');
        var newControlTelefonInput = similarСontrolTelefonInput.cloneNode(true);
        newControlTelefonInput.id = 'newControlTelefonInput';
        $(function(){
            $("#newControlTelefonInput").mask("+7(999) 999-9999");
        });
        modalDialogBody.appendChild(newControlTelefonInput);
        addNewLabel('');
        var newSendBtn = similarSendBtn.cloneNode(true);
        modalDialogBody.appendChild(newSendBtn);
    }

    /*методы для singUpValidation*/
    var validationOfCheckBaxes = function (listCheckBoxes){
        for (var i=0; i<listCheckBoxes.length; i++){
            if(listCheckBoxes[i].checked){
                return true;
            }
        };
        return false;
    }

    var addNewLabel = function (text){
        var newLabel = similarLabel.cloneNode(true);
        newLabel.innerText = text;
        modalDialogBody.appendChild(newLabel);
    }




    /*Переносим нажатие кнопки ЗАПИСАТЬСЯ на элемент input*/
    signUpBtn.addEventListener('click', function() {
        singUpValidation();
        openModalHref.click();
    });

    /*Переносим нажатие кнопки ДОСЛАТЬ ДОКУМЕНТЫ на элемент input*/
    uploadZapisBtn.addEventListener('click', function() {
        clickUploadZapisBtnBtn();
        openModalHref.click();
    });

    /*Переносим нажатие кнопки ПЕРЕНЕСТИ ЗАПИСЬ на элемент input*/
    relocateZapisBtn.addEventListener('click', function() {
        clickRelocateZapisBtn();
        openModalHref.click();
    });

    /*Переносим нажатие кнопки ОТМЕНИТЬ ЗАПИСЬ на элемент input*/
    removeZapisBtn.addEventListener('click', function() {
        clickRemoveZapisBtn();
        openModalHref.click();
    });

})();