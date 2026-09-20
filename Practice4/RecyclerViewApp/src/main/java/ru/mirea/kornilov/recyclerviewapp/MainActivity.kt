package ru.mirea.kornilov.recyclerviewapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = EventAdapter(events())
    }

    private fun events(): List<HistoryEvent> = listOf(
        HistoryEvent(
            "476 — Падение Западной Римской империи",
            "Одоакр сверг Ромула Августула. Конец античности в Западной Европе.",
            R.drawable.ic_event_column
        ),
        HistoryEvent(
            "988 — Крещение Руси",
            "Князь Владимир принял христианство. Киевская Русь вошла в византийский мир.",
            R.drawable.ic_event_dome
        ),
        HistoryEvent(
            "1066 — Битва при Гастингсе",
            "Вильгельм Завоеватель победил Гарольда. Начало нормандской Англии.",
            R.drawable.ic_event_helmet
        ),
        HistoryEvent(
            "1380 — Куликовская битва",
            "Дмитрий Донской разбил Мамая. Символ объединения русских земель.",
            R.drawable.ic_event_sword
        ),
        HistoryEvent(
            "1453 — Падение Константинополя",
            "Мехмед II взял столицу Византии. Конец Восточной Римской империи.",
            R.drawable.ic_event_wall
        ),
        HistoryEvent(
            "1492 — Открытие Америки",
            "Экспедиция Колумба достигла Карибских островов. Начало колонизации Нового Света.",
            R.drawable.ic_event_ship
        ),
        HistoryEvent(
            "1517 — Начало Реформации",
            "Лютер обнародовал 95 тезисов в Виттенберге. Раскол западного христианства.",
            R.drawable.ic_event_book
        ),
        HistoryEvent(
            "1613 — Начало династии Романовых",
            "Земский собор избрал Михаила Романова. Конец Смутного времени.",
            R.drawable.ic_event_crown
        ),
        HistoryEvent(
            "1789 — Великая французская революция",
            "Взятие Бастилии. Декларация прав человека и конец старого порядка.",
            R.drawable.ic_event_cockade
        ),
        HistoryEvent(
            "1812 — Отечественная война",
            "Наполеон вошёл в Россию и отступил. Бородино и пожар Москвы.",
            R.drawable.ic_event_cannon
        ),
        HistoryEvent(
            "1914 — Начало Первой мировой войны",
            "Убийство в Сараево. Европейские империи вступили в мировой конфликт.",
            R.drawable.ic_event_helmet
        ),
        HistoryEvent(
            "1941 — Начало Великой Отечественной войны",
            "Нападение Германии на СССР. Перелом на Восточном фронте Второй мировой.",
            R.drawable.ic_event_star
        ),
        HistoryEvent(
            "1961 — Полёт Гагарина",
            "Юрий Гагарин облетел Землю на «Востоке-1». Человек впервые в космосе.",
            R.drawable.ic_event_rocket
        ),
        HistoryEvent(
            "1969 — Высадка на Луну",
            "Нил Армстронг ступил на поверхность Луны в миссии Apollo 11.",
            R.drawable.ic_event_moon
        ),
        HistoryEvent(
            "1989 — Падение Берлинской стены",
            "Граница ГДР открылась. Символ конца холодной войны в Европе.",
            R.drawable.ic_event_wall
        )
    )
}
