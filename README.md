# Agent reports microservices

Получение, просмотр и создание отчета агента. 
На микросервисах обменивающихся между собой посредством брокера сообщений kafka
1. Главный микросервис где вся логика. agent-main
2. agent-report микросервис который производит все действия с ОА
3. policy-period микросверис который получает страховые договора
4. api-gateway шлюз, основная точка входа.
5. eureka-service

## 🚀 Начало работы

Инструкции по установке и запуску проекта локально.

### Требования

- Docker
- Java 17

### Установка

```bash
https://github.com/scorp1/micro-agent-service.git
cd micro-agent-service/docker
docker-compose up
