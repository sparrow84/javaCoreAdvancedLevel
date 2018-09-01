package com.company.lesson03;

import java.util.*;

public class Main03 {
    public static void main(String[] args) {
        // Задание №1

        String[] words = {"Удобная", "графическая", "среда", "позволяет", "пользователю", "легко",
                "адаптироваться", "новой", "настроить", "систему", "добавить", "принтеры", "операционной",
                "системе", "гибкая", "программа", "установки", "настройки", "позволяет", "настроить",
                "систему", "добавить", "принтеры", "настройки", "позволяет", "настроить", "систему",
                "добавить", "принтеры", "Удобная", "графическая", "среда", "позволяет", "пользователю", "легко"
        };

        HashMap<String, Integer> statistics = new HashMap<String, Integer>();

//        Map Set<Map.Entry<String, Integer>> set = statistics.entrySet();

        for (String s: words) {
            if (statistics.containsKey(s)) {
                statistics.put(s,statistics.get(s)+1);
            } else {
                statistics.put(s,1);
            }
        }



//        System.out.println(statistics);

        for (Map.Entry<String, Integer> entry: statistics.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("\n");

        // Задание №2

        Phonebook friends = new Phonebook();

        friends.add("Jukov","+7 937 451 21 45");
        friends.add("Voevoda","+7 752 123 9698");
        friends.add("Voroshqlov","8 654 987 21 32");
        friends.add("Voevoda","+7 963 123 96 97");
        friends.add("Rokosovskiy","+7 383 3222 969");
        friends.add("Voevoda","+7 383 9874 516");

        System.out.println("Номера людей по фамилии Воевода: " + friends.get("voevoda"));
        System.out.println("Номера людей по фамилии Рокосовский: " + friends.get("rokosovskiy"));
    }
}
