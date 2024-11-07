package ru.example.myapplication

class ListRepoitory {
    val listFilms = arrayListOf<Film>(Film("Интерстеллар", 2008, "Nolan"),
        Film("Волк Уолл Стрит", 2013, "Скорсезе"),
        Film("Джентельмены", 2023, "Гай Рич"),
        Film("Сваты", 2000, "Михалков"),
        Film("Чебурашка", 2023, "Михалков"),
        Film("Драйв", 2011, "Гослинг"),
        Film("Довод", 2020, "Nolan"),
        )
    fun getList():ArrayList<Film>{
        return listFilms
    }
}