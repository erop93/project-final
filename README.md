## [REST API](http://localhost:8080/doc)

## Концепция:

- Spring Modulith
    - [Spring Modulith: достигли ли мы зрелости модульности](https://habr.com/ru/post/701984/)
    - [Introducing Spring Modulith](https://spring.io/blog/2022/10/21/introducing-spring-modulith)
    - [Spring Modulith - Reference documentation](https://docs.spring.io/spring-modulith/docs/current-SNAPSHOT/reference/html/)

```
  url: jdbc:postgresql://localhost:5432/jira
  username: jira
  password: JiraRush
```

- Есть 2 общие таблицы, на которых не fk
    - _Reference_ - справочник. Связь делаем по _code_ (по id нельзя, тк id привязано к окружению-конкретной базе)
    - _UserBelong_ - привязка юзеров с типом (owner, lead, ...) к объекту (таска, проект, спринт, ...). FK вручную будем
      проверять

## Аналоги

- https://java-source.net/open-source/issue-trackers

## Тестирование

- https://habr.com/ru/articles/259055/

Список выполненных задач:
1. Разобраться со структурой проекта (onboarding).
✓

2. Удалить социальные сети: vk, yandex. Easy task
Удалены классы авторизации через vk и yandex и их кнопки на странице с логином
   
3. Вынести чувствительную информацию в отдельный проперти файл
Чувствительная информация перенесена из application.yaml в sensitive.yaml

4. Переделать тесты так, чтоб во время тестов использовалась in memory БД (H2), а не PostgreSQL. 
Тесты переделаны и переведены на testcontainers.

5. Написать тесты для всех публичных методов контроллера ProfileRestController. 
Реализованы тесты для методов get и update в тестовом классе ProfileRestControllerTest.

6. Сделать рефакторинг метода com.javarush.jira.bugtracking.attachment.FileUtil#upload чтоб он использовал современный подход для работы с файловой системмой. Easy task
Переписан метод upload класса FileUtil c помощью Java NIO API.

7. Добавить новый функционал: добавления тегов к задаче (REST API + реализация на сервисе). Фронт делать необязательно. Таблица task_tag уже создана.
Добавлены методы для добавления, удаления и обновления тегов в классе TaskService, а так же их маппинг в классе TestController.

8. Написать Dockerfile для основного сервера
✓

9. Написать docker-compose файл для запуска контейнера сервера вместе с БД и nginx. Для nginx используй конфиг-файл config/nginx.conf.
✓