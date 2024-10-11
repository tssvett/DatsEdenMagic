package dev.team.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.team.dto.MoveRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

@Slf4j
public class MoveRequestJsonService {
    private static final String PATH_DIRECTORY = "src/main/resources/requestjson";

    // Метод для сохранения MoveRequest в JSON файл
    public static boolean saveToJson(MoveRequest moveRequest, String fileName) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Создаем директорию, если она не существует
            File directory = new File(PATH_DIRECTORY);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Сохраняем MoveRequest в JSON файл
            File file = new File(PATH_DIRECTORY + "/" + fileName);
            mapper.writeValue(file, moveRequest);

            log.info("MoveRequest успешно сохранен в файл: " + file.getAbsolutePath());
            return true; // Сохранение прошло успешно
        } catch (IOException e) {
            log.info("Ошибка сохранения: проебали " + fileName);
            return false; // Возвращаем false в случае ошибки
        }
    }


    // Метод для десериализации JSON файла в объект MoveRequest
    public static MoveRequest loadFromJson(String fileName) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Чтение JSON файла и преобразование его в объект MoveRequest
            File file = new File(PATH_DIRECTORY + "/" + fileName);
            log.info("MoveRequest загружен из файла: " + file.getAbsolutePath());
            return mapper.readValue(file, MoveRequest.class);
        } catch (IOException e) {
            log.info("Ошибка загрузки: " + fileName);
            return null; // В случае ошибки возвращаем null
        }
    }
}
