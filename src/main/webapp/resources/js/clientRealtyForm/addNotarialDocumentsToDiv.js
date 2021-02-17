/*Скрипт добавляющий в список нотариальных документов*/
/*новые документы */
'use strict';

(function (){

    var notarialDocuments = [
        'Договор дарения, купли-продажи объекта недвижимости',
        'Договор мены двух объектов недвижимости',
        'Соглашение о расторжении, изменении договоров',
        'Договор займа',
        'Договор дарения (кроме недвижимого имущества)',
        'Договоры ренты',
        'Договоры залога',
        'Раздел имущества',
        'Брачный договор',
        'Соглашение об алиментах',
        'Соглашение об определении долей, о порядке пользов. комнатами'];

    /*Находим в DOM div ,в который отвечает за отображения списка нот. документов*/
    var listNotarialDocuments = document.querySelector('.checkNotarialDocumentBody');

    /*Находим в DOM шаблон элемента списка*/
    var similarNotarialDocument = document.querySelector('.liNotarialDocument').content.querySelector('.listNotarialDocuments');

    for (var i=0; i<notarialDocuments.length; i++){
        /*Клонируем шаблон*/
        var newNotarialDocument = similarNotarialDocument.cloneNode(true);
        /*изменяем значение элементов в добавляемом документе*/
        newNotarialDocument.querySelector('.inputLiNotarialDocument').value = i;
        newNotarialDocument.querySelector('.labelLiNotarialDocument').innerText = notarialDocuments[i];
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

})();
