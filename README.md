Docker-compose файл и скрипт для создания базы данных находятся в resources.

Работа веб-приложения выглядит следующим образом:

При попадании на главную страницу (filmLibraryWebApp/) открывается страница с кнопкой "Начать работу".
- Если Connection Pool инициализирован, то при нажатии кнопки "Начать работу" происходит переход на рабочую страницу (filmLibraryWebApp/home)
- Если же Connection Pool не инициализирован, то при нажатии кнопки "Начать работу" происходит переход на страницу с ошибкой и выбрасывается RuntimeException.


