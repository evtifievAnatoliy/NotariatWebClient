<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->


<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="<c:url value = "/resources/css/clientForm/style.css"/>" rel="stylesheet">
        <title>Нотариальная контора: Запись</title>
        <script type="text/javascript" src="<c:url value = "/resources/js/lib/jquery.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value = "/resources/js/lib/jquery.maskedinput.js"/>"></script>
        <script type="text/javascript" src="<c:url value = "/resources/js/lib/jquery.maskedinput.min.js"/>"></script>
        
    </head>
    <body>
        <div class="mainDiv fontFamilyStyle">
            <div style="text-align: right; padding: 0px 20px 0px 0px;">
            	<a style="font-size: 12px;" href="http://10.10.0.44:8080/client/loginForm">admin</a>
            </div>
            <div class="headerDiv commonStyle borderBottom">
                <p> Нотариальная контора </p>
                <p class="colorWeightHeaderFont"> Запись к нотариусу </p>
            </div>
            <div class="checkNotarialDocumentDiv commonStyle headsOfCommonDivs borderBottom">
                <div class="checkNotarialDocumentHeader">
                    <p class="colorWeightHeaderFont"> 1. Выберите действие (несколько действий): </p>
                </div>
                <div class="checkNotarialDocumentBody">
                    <!-- список добавляется скриптом js/addNotarialDocumentsToDiv.js-->
                </div>
            </div>
            <div class="commonStyle headsOfCommonDivs borderBottom">
                <div class="checkCalendarHeader">
                    <div class="calendarScrollHeader">
                        <p class="colorWeightHeaderFont"> 2. Выберите дату и время: </p>
                    </div>
                    <div class="calendarScroll">
                        <div class="calendarScrollInner"></div>
                    </div>
                </div>
                <div>
                    <div class="calendarBlockRemark colorWeightHeaderFont">(Если интересующее Вас время занято, ведется прием в порядке очереди)</div>
                </div>
                <div class="calendarBody">
                    <div class="checkCalendarBody">
                        <!-- список добавляется скриптом js/addCalendarToDiv.js-->
                    </div>
                </div>
                <div class="colorWeightHeaderFont youChoiceDiv">
                    <label class="youChoiceLabel redColor">Вы выбрали : </label>
                </div>
            </div>
            <div class="loadFilesDiv commonStyle headsOfCommonDivs borderBottom">
                <div class="loadFilesHeader">
                    <p class="colorWeightHeaderFont"> 3. Загрузить документы (для предварительной подготовки договора): </p>
                </div>
                <div class="loadFilesBody">
                    <button type="button" class="button loadFilesBtn" ><!--on:click="file_upload"-->
                        <span class="buttonText redColor">ЗАГРУЗИТЬ ДОКУМЕНТЫ</span>
                    </button>
                    <input type="file" class="loadFilesInput" multiple hidden>
                </div>
            </div>
            <div class="addressTelefonSignUpDiv commonStyle headsOfCommonDivs borderBottom">
                <div class="addressTelefonSignUpkHeader">
                    <p class="colorWeightHeaderFont"> 4. Введите адрес объекта и телефон для связи:</p>
                </div>
                <div class="addressTelefonSignUpBody">
                    <div class="addressTelefonDiv commonFontSize">
                        <label class="addressTelefonLabel inlineBlock text-right">Адрес объекта:</label>
                        <input class="inlineBlock controlAddressInput" type="text" name="adressInput" minlength="6"  maxlength="150" required/>
                    </div>
                    <div class="addressTelefon commonFontSize">
                        <label class="addressTelefonLabel inlineBlock text-right">Телефон для связи:</label>
                        <input class="inlineBlock controlTelefonInput" type="text" id="telefonInput" mask="+7(999) 999-9999" placeholder="+7(   )    -    " required/>
                    </div>
                    <div class="signUp">
                        <button type="button" class="button signUpBtn"><!--on:click="file_upload"-->
                        <span class="buttonText redColor">ЗАПИСАТЬСЯ</span>
                    </button>
                    </div>
                </div>
            </div>
            
            <div class="controlButtons commonStyle">
                <div class="controlBtns">
                    <div class="uploadZapis inlineBlock">
                        <button type="button" class="button uploadZapisBtn"><!--on:click="file_upload"-->
                            <span class="buttonText redColor">ДОСЛАТЬ ДОКУМЕНТЫ</span>
                        </button>
                    </div>
                    <div class="relocateZapis inlineBlock">
                        <button type="button" class="button relocateZapisBtn"><!--on:click="file_upload"-->
                            <span class="buttonText redColor">ПЕРЕНЕСТИ ЗАПИСЬ</span>
                        </button>
                    </div>
                    <div class="removeZapis inlineBlock">
                        <button type="button" class="button removeZapisBtn"><!--on:click="file_upload"-->
                            <span class="buttonText redColor">ОТМЕНИТЬ ЗАПИСЬ</span>
                        </button>
                    </div>
                </div>
                <div class="commonFontSize controlLabeDiv">
                    <label class="controlLabel"> (если Вы уже записаны на сделку) </label>
                </div>
            </div>
        
        </div>
        
        <!-- Модальные окна  -->
        <a href="#openModal" class="openModalHref" hidden>Открыть модальное окно</a>
        <div id="openModal" class="modalDialog redColor">
            <div>
                <a href="#close" title="Закрыть" class="close">X</a>
                <div class="modalDialogBody">
                    <p>Пример простого модального окна, которое может быть создано с использованием CSS3.</p>
                    <p>Его можно использовать в широком диапазоне, начиная от вывода сообщений и заканчивая формой регистрации.</p>
                </div>
            </div>
        </div>

        
        
        <!--
        <canvas id="canvas">Ваш браузер не
                            поддерживает некоторые технологии,
                            используемые на этом сайте</canvas>
        <p id="demo">Привет, мир!</p>
        <button type="button" onclick="myFunction()">Кликни меня</button>
        
        <script src="js/helloJS.js"></script>
        <script src="js/canvasDemo.js"></script>-->
        
        <!-- шаблоны -->
        <template class="liNotarialDocument">
            <div class="listNotarialDocuments listStyle">
                <input class="inputLiNotarialDocument" type="checkbox" value="0"/>
                <label class="labelLiNotarialDocument">Нотариальный документ</label>
            </div>
        </template>
        <template class="liCalendar">
            <div class="liCalendarDiv">
                <div class="liDates">
                    <label class="labelDate">7 декабря, Пн</label>
                </div>
                <div class="listTimes listStyle">
                    <!--Вставляется шаблон liTime-->
                </div>
            </div>
        </template>
        <template class="liTime">
            <div class="liTimeDiv">
                <input class="inputLiTime" type="checkbox" value="0"/>
                <label class="labelLiTime">10:00</label>
            </div>
        </template>
        <template class="templateLabel">
            <p class="similarLabel"></p>
        </template>
        <template class="templateNumberOfZapisInput">
            <input class="controlNumberOfZapisInput" type="text" required/>
        </template>
        <template class="templateBtn">
            <button type="button" class="button sendBtn"><!--on:click="file_upload"-->
                <span class="buttonText redColor">отправить</span>
            </button>
        </template>
        <script type="text/javascript" src="<c:url value = "/resources/js/clientRealtyForm/addNotarialDocumentsToDiv.js"/>"></script>
        <script type="text/javascript" src="<c:url value = "/resources/js/clientRealtyForm/addCalendarToDiv.js"/>"></script>
        <script type="text/javascript" src="<c:url value = "/resources/js/clientRealtyForm/mask.js"/>"></script>
        <script type="text/javascript" src="<c:url value = "/resources/js/clientRealtyForm/uploadFilesBtn.js"/>"></script>
        <script type="text/javascript" src="<c:url value = "/resources/js/clientRealtyForm/popUp.js"/>"></script>
     
    </body>
</html>
