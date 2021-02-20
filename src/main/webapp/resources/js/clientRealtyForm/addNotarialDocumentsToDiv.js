/*Скрипт добавляющий в список нотариальных документов*/
/*новые документы */
'use strict';

var onErrorDocuments = function (message) {
	alert(message);
}

var onSuccessDocuments= function (data){

	var notarialDocuments = xhrDocuments.response;
	
    /*Находим в DOM div ,в который отвечает за отображения списка нот. документов*/
    var listNotarialDocuments = document.querySelector('.checkNotarialDocumentBody');

    /*Находим в DOM шаблон элемента списка*/
    var similarNotarialDocument = document.querySelector('.liNotarialDocument').content.querySelector('.listNotarialDocuments');

    for (var i=0; i<notarialDocuments.length; i++){
        /*Клонируем шаблон*/
        var newNotarialDocument = similarNotarialDocument.cloneNode(true);
        /*изменяем значение элементов в добавляемом документе*/
        newNotarialDocument.querySelector('.inputLiNotarialDocument').value = i.id;
        newNotarialDocument.querySelector('.labelLiNotarialDocument').innerText = notarialDocuments[i].name;
        /*Добавляем в список*/
        listNotarialDocuments.appendChild(newNotarialDocument);
    }

    var listCheckBoxNotarialDocuments = listNotarialDocuments.querySelectorAll('.inputLiNotarialDocument');

    /*выделение элемента checkBox. (Выбрать можно только один документ в списке)*/
    /*
    var checkNotarialDocument = function (itemNumber){
        for (var i=0; i<listCheckBoxNotarialDocuments.length; i++){
                listCheckBoxNotarialDocuments[i].checked = false;
        }
        listCheckBoxNotarialDocuments[itemNumber].checked = true;
    }
    for (var i=0; i<listCheckBoxNotarialDocuments.length; i++){
        listCheckBoxNotarialDocuments[i].addEventListener('click', function(evt) {
                checkNotarialDocument(evt.target.value);
        });
    }
    */	

};

	
var xhrDocuments = new XMLHttpRequest();
xhrDocuments.responseType = 'json';


xhrDocuments.addEventListener('load', function(){
	var error;
	switch (xhrDocuments.status){
		case 200:
			onSuccessDocuments(xhrDocuments.response);
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
			error= 'Статус ответа: : ' + xhr.status + ' ' + shr.statusText;
	}
	if (error){
		onErrorDocuments(error);
	}
});

xhrDocuments.open('GET','http://localhost:8080/client//get-json-documentsList?documentType=Realty');
xhrDocuments.send();