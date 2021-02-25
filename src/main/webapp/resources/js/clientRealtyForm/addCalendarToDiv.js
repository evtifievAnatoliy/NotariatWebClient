/*Скрипт добавляющий в список */
/*даты и время */
'use strict';

var onErrorCalendar = function (message) {
	alert(message);
}

var onSuccessCalendar = function (data){

    /*Находим в DOM div ,в который отвечает за отображения списка нот. документов*/
    var listDates = document.querySelector('.checkCalendarBody');

    /*Находим в DOM шаблон элемента списка*/
    var similarDate = document.querySelector('.liCalendar').content.querySelector('.liCalendarDiv');
    var indexTime = 0;

    /*Загружаем календарь. Календарь состоит из дат. Каждая дата состоит из точек времени*/
    for (var i=0; i<calendarDates.length; i++){
        var newDate = similarDate.cloneNode(true);
        newDate.querySelector('.labelDate').innerText = calendarDates[i].getDate() + ' ' + returnMonth(calendarDates[i].getMonth()) + ', ' +  returnWeekDays(calendarDates[i].getDay());
        addTime(newDate, calendarDates[i]);
        listDates.appendChild(newDate);
    }

    function addTime(newDate, calendarDate) {
    
    	allTimes = xhrCalendar.response;
        var similarTime = document.querySelector('.liTime').content.querySelector('.liTimeDiv');
        for (var i=0; i<allTimes.length; i++){
            var timestapm = new Date(allTimes[i].receptionTimestamp);
            if (timestapm > calendarDate.getTime()
            	&& timestapm < (calendarDate.getTime() + 9 * 3600 * 1000) &&
            	timestapm.getMinutes() != 30){
	            /*Клонируем шаблон*/
	            var newTime = similarTime.cloneNode(true);
	            /*изменяем значение элементов в добавляемом документе*/
	            newTime.querySelector('.inputLiTime').value = indexTime;
	            newTime.querySelector('.inputLiTime').id = allTimes[i].id;
	            indexTime++;
	            newTime.querySelector('.labelLiTime').innerText = allTimes[i].name;
	            /*Добавляем в список*/
	            newDate.querySelector('.listTimes').appendChild(newTime);
            }
        } 
    }

    listCheckBoxTimes = listDates.querySelectorAll('.inputLiTime');
    var youChoice = document.querySelector('.youChoiceLabel');

    /*выделение элемента checkBox. (Выбрать можно только один документ в списке)*/

    var checkTime = function (itemNumber){
        for (var i=0; i<listCheckBoxTimes.length; i++){
                listCheckBoxTimes[i].checked = false;
        }
        listCheckBoxTimes[itemNumber].checked = true;
        youChoice.innerText = 'Вы выбрали : ' + 
                listCheckBoxTimes[itemNumber].parentElement.parentElement.parentElement.querySelector('.labelDate').textContent +
                ' ' + listCheckBoxTimes[itemNumber].parentElement.querySelector('.labelLiTime').textContent;
    }
    for (var i=0; i<listCheckBoxTimes.length; i++){
        listCheckBoxTimes[i].addEventListener('click', function(evt) {
                checkTime(evt.target.value);
        });
    }

    /*привязываем скрол элемента calendarScroll с calendarBody*/
    var calendarScroll = document.querySelector('.calendarScroll');
    var calendarBody = document.querySelector('.calendarBody');

    calendarScroll.addEventListener('scroll', function() {
        calendarBody.scrollLeft = calendarScroll.scrollLeft;
    });
};

var xhrCalendar = new XMLHttpRequest();
var allTimes;
var listCheckBoxTimes;
xhrCalendar.responseType = 'json';


xhrCalendar.addEventListener('load', function(){
	var error;
	switch (xhrCalendar.status){
		case 200:
			onSuccessCalendar(xhrCalendar.response);
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
		onErrorCalendar(error);
	}
});


var calendarDates = new Array();
for (var i=0; i<10; i++){
	var calendarDate = new Date();
	calendarDate.setHours(9, 59, 0, 0);
	calendarDate.setDate(calendarDate.getDate()+i);
  	calendarDates.push(calendarDate);
 	
}

xhrCalendar.open('GET','http://localhost:8080/client/get-json-timeStampsList?firstStamp=' + calendarDates[0].getTime() + '&secondStamp=' + calendarDates[calendarDates.length-1].getTime());
xhrCalendar.send();

function returnWeekDays(a) {
  switch (a){
		case 0:
			return 'Вс';
			break;
		case 1:
			return 'Пн';
			break;
		case 2:
			return 'Вт';
			break;
		case 3:
			return 'Ср';
			break;
		case 4:
			return 'Чт';
			break;
		case 5:
			return 'Пт';
			break;
		case 6:
			return 'Сб';
			break;
		default:
			return 'Неправильный формат входных данных';
	}
}

function returnMonth(a) {
  switch (a){
		case 0:
			return 'января';
			break;
		case 1:
			return 'февраля';
			break;
		case 2:
			return 'марта';
			break;
		case 4:
			return 'апреля';
			break;
		case 5:
			return 'мая';
			break;
		case 6:
			return 'июня';
			break;
		case 7:
			return 'июля';
			break;
		case 8:
			return 'августа';
			break;
		case 9:
			return 'сентября';
			break;
		case 10:
			return 'октября';
			break;
		case 11:
			return 'ноября';
			break;
		default:
			return 'Неправильный формат входных данных';
	}
}

