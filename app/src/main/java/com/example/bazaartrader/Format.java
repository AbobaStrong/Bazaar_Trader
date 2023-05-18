package com.example.bazaartrader;

import java.text.DecimalFormat;

public class Format {
    public static String formatNumber(double number) {
        if (number < 1000) {
            return String.valueOf(number); // Возвращаем число как строку
        }

        String suffix = "";
        double value = number;

        // Определяем суффикс
        if (number >= 1_000 && number < 1_000_000) {
            suffix = "K";
            value = number / 1_000.0;
        } else if (number >= 1_000_000 && number < 1_000_000_000) {
            suffix = "M";
            value = number / 1_000_000.0;
        } else if (number >= 1_000_000_000 && number < 1_000_000_000_000L) {
            suffix = "B";
            value = number / 1_000_000_000.0;
        } else if (number >= 1_000_000_000_000L && number < 1_000_000_000_000_000L) {
            suffix = "T";
            value = number / 1_000_000_000_000.0;
        }

        // Округляем число до 1 цифры после запятой
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        String formattedValue = decimalFormat.format(value);

        // Форматируем число с округленным значением и добавляем суффикс
        return formattedValue + suffix;
    }

    public static String convertString(String input) {
        // Разделяем строку по символу "_"
        String[] words = input.split("_");

        // Создаем новую строку для результата
        StringBuilder result = new StringBuilder();

        // Проходим по каждому слову
        for (String word : words) {
            // Пропускаем слово "ENCHANTMENT"
            if (word.equals("ENCHANTMENT")) {
                continue;
            }

            // Преобразуем первую букву в верхний регистр
            String capitalizedWord = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();

            // Добавляем преобразованное слово в результат
            result.append(capitalizedWord).append(" ");
        }

        // Удаляем последний пробел
        result.deleteCharAt(result.length() - 1);

        // Возвращаем результат в виде строки
        return result.toString();
    }
}
