# [Курсовой проект "Сетевой чат"](https://github.com/netology-code/jd-homeworks/blob/master/diploma/networkchat.md)
Автор работы: *Алексей Фалев*

## Описание:
Данный чат предназначен для быстрого и удобного обмена текстовыми сообщениями между его участниками. Все сообщения, отправленные через сервер(с указанием времени и имён пользователей), а также время входа и выхода из чата сохраняются в файлах file.log.

## Сервер:
- Приложение Server имитирует работу сервера
- Необходимо запусить первых для включения чата
- При запуске создает файл file.log - куда будут записываться все события
- Умеет одновременно ожидать новых пользователей и обрабатывать поступающие сообщения от уже подключенных пользователей
- Обеспечивает возможность подключиться к серверу в любой момент и присоединиться к чату

## Требования к серверу:
- Установка порта для подключения клиентов через файл настроек settings.txt
- Возможность подключиться к серверу в любой момент и присоединиться к чату
- Отправка новых сообщений клиентам
- Запись всех отправленных через сервер сообщений с указанием имени пользователя и времени отправки

## Клиент:
- При запуске имитирует работу конкретного участника чата
- Имеет выбор имени для участия в чате
- Для подключения к серверу реализован метод чтения IP-адреса и порта из файла настроек
- При запуске создает файл file.log - куда будут записываться все события

## Требования к клиенту:
- Выбор имени для участия в чате
- Прочитать настройки приложения из файла настроек - например, номер порта сервера
- Подключение к указанному в настройках серверу
- Для выхода из чата нужно набрать команду выхода - “/exit”
- Каждое сообщение участников должно записываться в текстовый файл - файл логирования. При каждом запуске приложения файл должен дополняться
