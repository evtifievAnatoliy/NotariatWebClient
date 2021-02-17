/*Скрипт добавляющий в список */
/*даты и время */
'use strict';

(function (){
    var times = [
        '10:00',
        '11:00',
        '12:00',
        '13:00',
        '14:00',
        '15:00',
        '16:00',
        '17:00',
        '18:00'];

    var dates =[
        '7 декабря, Пн',
        '8 декабря, Вт',
        '9 декабря, Ср',
        '10 декабря, Чт',
        '11 декабря, Пт',
        '12 декабря, Сб',    
        '13 декабря, Вс',
        '14 декабря, Пн',
        '15 декабря, Вт',
        '16 декабря, Ср',
        '17 декабря, Чт',
        '18 декабря, Пт'];
    /*Находим в DOM div ,в который отвечает за отображения списка нот. документов*/
    var listDates = document.querySelector('.checkCalendarBody');

    /*Находим в DOM шаблон элемента списка*/
    var similarDate = document.querySelector('.liCalendar').content.querySelector('.liCalendarDiv');
    var indexTime = 0;

    /*Загружаем календарь. Календарь состоит из дат. Каждая дата состоит из точек времени*/
    for (var date=0; date<dates.length; date++){
        var newDate = similarDate.cloneNode(true);
        newDate.querySelector('.labelDate').innerText = dates[date];
        addTime(newDate);
        listDates.appendChild(newDate);
    }

    function addTime(newDate) {
        var similarTime = document.querySelector('.liTime').content.querySelector('.liTimeDiv');
        for (var i=0; i<times.length; i++){
            /*Клонируем шаблон*/
            var newTime = similarTime.cloneNode(true);
            /*изменяем значение элементов в добавляемом документе*/
            newTime.querySelector('.inputLiTime').value = indexTime;
            indexTime++;
            newTime.querySelector('.labelLiTime').innerText = times[i];
            /*Добавляем в список*/
            newDate.querySelector('.listTimes').appendChild(newTime);
        } 
    }

    var listCheckBoxTimes = listDates.querySelectorAll('.inputLiTime');
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

})();