# Spring_Security_Rest_API_App

* Необходимо реализовать REST API, которое взаимодействует с файловым хранилищем AWS S3 и предоставляет возможность получать доступ к файлам и истории загрузок. 
* Логика безопасности должна быть реализована средствами JWT токена. 
* Приложение должно быть докеризировано и готового к развертыванию в виде Docker контейнера.

# Сущности:
* User (List<Event> events,  Status status, …)
* Event (User user, File file, Status status)
* File (id, location, Status status ...)

Взаимодействие с S3 должно быть реализовано с помощью AWS SDK.

# Уровни доступа:
ADMIN - полный доступ к приложению
MODERATOR - права USER + чтение всех User + чтение/изменение/удаление всех Events + чтение/изменение/удаление всех Files 
USER - только чтение всех своих данных + загрузка файлов для себя

# Технологии:
Java, MySQL, Spring (IoC, Data, Security), AWS SDK, MySQL, Docker, JUnit, Mockito, Gradle.
