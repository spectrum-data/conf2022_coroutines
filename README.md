# Практическое задание по теме "Асинхронное программирование в Kotlin"

Этот репозиторий включает 21 задание по теме асинхронного программирования.

Каждое задание описано в комментариях к классу, в котором его необходимо реализовать.
Все задания находятся в каталоге [src/main/kotlin/impl](src/main/kotlin/impl).

Вам нужно добиться корректного выполнения всех тестов из каталога [src/test/kotlin/impl](src/test/kotlin/impl).
Всего в репозитории - 64 теста. Каждый тестовый класс соответствует одному заданию. 

Выполнять задания лучше в том порядке, в котором они пронумерованы - это позволит двигаться от самых простых заданий к более сложным.

## Список заданий

### Часть 1

1. `CoroutinesP01S01` - Параллельные вызовы (launch).
2. `CoroutinesP01S02` - Параллельное получение результатов (async).
3. `CoroutinesP01S03` - Таймаут корутины.
4. `CoroutinesP01S04` - Смена контекста.
5. `CoroutinesP01S05` - Диспетчеры корутин.
6. `CoroutinesP01S06` - Диспетчеры корутин.

### Часть 2

1. `CoroutinesP02S01` - Синхронизация доступа.
2. `CoroutinesP02S02` - Синхронизация доступа.
3. `CoroutinesP02S03` - Синхронизация доступа.

### Часть 3

1. `CoroutinesP03S01` - Получение элементов из канала.
2. `CoroutinesP03S02` - Отправка элементов в канал.
3. `CoroutinesP03S03` - Параллельный доступ к каналам.

### Часть 4

1. `CoroutinesP04S01` - Создание асинхронного потока.
2. `CoroutinesP04S02` - Преобразование асинхронных потоков.
3. `CoroutinesP04S03` - Объединение потоков.
4. `CoroutinesP04S04` - Комбинация потоков.

### Часть 5

1. `CoroutinesP05S01` - Выражение Select, объединение каналов.
2. `CoroutinesP05S02` - Выражение Select, отправка сообщений в запасной канал.
3. `CoroutinesP05S03` - Выражение Select, выбор первых результатов.

### Часть 6

1. `CoroutinesP06S01` - Перехват исключений при отмене корутин.
2. `CoroutinesP06S02` - Перехват исключений в дочерних корутинах.

## Порядок выполнения заданий

1. Посмотреть видео по корутинам.
2. Склонируйте репозиторий.
3. Создайте свою ветку от `main`.
4. Выполните задания и зазелените тесты.
5. За 30 минут до окончания практики в ветке `main` будут опубликованы референсные решения. Можете затянуть их и проверить себя. 
6. [Опционально] Пушните свою ветку в GitHub, если хотите сравнить свою реализацию с реализациями других участников.

## Видео по корутинам:

1. [Часть 1. Общие принципы](https://youtu.be/VDibzvTTsSY)
2. [Часть 2. Структурированная конкурентность](https://youtu.be/KoRsGlmp2xs)
3. [Часть 3. Синхронизация доступа к памяти](https://youtu.be/aOucE8E3fpA)
4. [Часть 4. Каналы](https://youtu.be/RBYr1igSI9c)
5. [Часть 5. Асинхронные потоки](https://youtu.be/gJwQwKJqiSs)
6. [Часть 6. Select, исключения, отладка](https://youtu.be/gurCbysiOz8)