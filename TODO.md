# TODO - URL Shortener (Socket version)

- [x] Настройка Maven структуры проекта
- [x] Конфигурация maven-jar-plugin для сборки
- [x] Socket сервер + HTTP парсер запросов
- [x] Роутер и обработчики (handlers)
- [x] Билдер HTTP ответов
- [x] Составить openapi спецификациюс
- [x] Mock обработчик для shorten
- [x] Добавить Flyway с автомиграцией при запуске + первая миграция
- [x] Реализовать shorten функционал (генерация MD5 кода)
- [x] Добавить RedirectHandler
- [x] Переписать DatabaseManager на Connection Pool (HikariCP)
- [x] Написать свой simple connection pool (c baeldung)


- [ ] Поправить response builder 
- [ ] Автомаппинг
- [ ] Улучшить обработку ошибок (error httpResponse + универсальные проверки для входных данных)
- [ ] Логирование (SLF4J + Logback)
- [ ] Лимиты (max URL length, rate limiting)
- [ ] Добавить кэширование (Caffeine) для частых редиректов

### Тестирование
- [ ] **Юнит тесты** (JUnit)
- [ ] Интеграционные тесты с тестовой БД
- [ ] Бенчмарки производительности

### Архитектура
- [ ] Docker compose для сервера (сейчас только для БД)
- [ ] Метрики (профилирование, количество запросов, времени ответа)
- [ ] CI/CD

