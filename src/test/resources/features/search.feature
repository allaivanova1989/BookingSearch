Feature: Поиск в Booking
  Scenario: Поиск результатов по Гродно
    Given Город для поиска "Гродно"
    When Пользователь ищет отель по городу
    Then Отель с названием "Apartment D.Haradzeinskaha 2.(in the city centr)" на первой странице
    And Рейтинг должен быть "9,2"


