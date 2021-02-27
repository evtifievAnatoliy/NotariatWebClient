
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
        var isChecked = validationOfCheckBoxes(listCheckBoxNotarialDocuments);
        var checkedNotarialDocuments =[];
        if (!isChecked){
            addNewLabel('Выберите действие (несколько действий)');
            isSingUpValidation = false;
        }
        if (isChecked){
        	var index = 0;
        	for (var i=0; i<listCheckBoxNotarialDocuments.length; i++){
                if(listCheckBoxNotarialDocuments[i].checked){
					checkedNotarialDocuments[index] = listCheckBoxNotarialDocuments[i].id;
					index++;
                }
            }
        }

        /*проверка на выбор времени*/
        var listCheckBoxTimes = document.querySelectorAll('.inputLiTime');
        isChecked = validationOfCheckBoxes(listCheckBoxTimes);
        var checkedTime;
        if (!isChecked){
            addNewLabel('Выберите дату и время');
            isSingUpValidation = false;
        }
        if (isChecked){
        	var index = 0;
        	for (var i=0; i<listCheckBoxTimes.length; i++){
                if(listCheckBoxTimes[i].checked){
					checkedTime = listCheckBoxTimes[i].id;
					index++;
                }
            }
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
        var telephoneInput = document.querySelector('.controlTelefonInput');
        if (telephoneInput.validity.valueMissing){
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
            
            /*------------Отправляем запрос на добавление записи на срвер. В ответ получаем номер записи*/
            
            
			var responceObject = {
				documentsId : checkedNotarialDocuments,
				timeId : checkedTime,
            	address : addressInput.value,
            	telephone : telephoneInput.value
            };
			var json = JSON.stringify(responceObject);
            var xhrAddNewRecord = new XMLHttpRequest();
			var onResponseAddNewRecord= function (data){
				addNewLabel('Номер записи:' + data);
			};
			xhrAddNewRecord.addEventListener('load', function(){
				var error;
				switch (xhrAddNewRecord.status){
					case 200:
						onResponseAddNewRecord(xhrAddNewRecord.response);
						break;
					case 400:
						error= 'Неверный запрос';
						break;
					case 401:
						error= 'Пользователь не авторизован';
						break;
					case 404:
						error= 'Ничего не найдено';
						break;
					default:
						error= 'Статус ответа: : ' + AddNewRecord.status + ' ' + AddNewRecord.statusText;
				}
				if (error){
					onResponseAddNewRecord(error);
				}
			});
			xhrAddNewRecord.open("POST", "http://localhost:8080/client/put-json-new-record");
			xhrAddNewRecord.setRequestHeader('Content-type', 'application/json; charset=utf-8');
			xhrAddNewRecord.send(json);
			/*!----------Отправляем запрос на добавление записи на срвер. В ответ получаем номер записи*/
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
        newSendBtn.addEventListener('click', function() {      
		    /*------------Отправляем запрос на удаление записи на сервер. В ответ получаем строку с ответом*/
		    var xhrDelRecord = new XMLHttpRequest();
			var onResponseDelRecord= function (data){
				addNewLabel('Номер записи:' + data);
			};
			xhrDelRecord.addEventListener('load', function(){
				var error;
				switch (xhrDelRecord.status){
					case 200:
						onResponseDelRecord(xhrDelRecord.response);
						break;
					case 400:
						error= 'Неверный запрос';
						break;
					case 401:
						error= 'Пользователь не авторизован';
						break;
					case 404:
						error= 'Ничего не найдено';
						break;
					default:
						error= 'Статус ответа: : ' + xhrDelRecord.status + '  ' + xhrDelRecord.statusText;
				}
			if (error){
				onResponseDelRecord(error);
			}
			});
			xhrDelRecord.open('DELETE', 'http://localhost:8080/client/delete-record?recordId=' + newInput.value + '&userTelephone=' + newControlTelefonInput.value);
			xhrDelRecord.send();
			
			/*!----------Отправляем запрос на удаление записи на сервер.*/
	    });   
    }

    /*методы для singUpValidation*/
    var validationOfCheckBoxes = function (listCheckBoxes){
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




