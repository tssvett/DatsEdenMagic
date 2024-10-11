package dev.team.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.team.dto.MoveResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

@Slf4j
public class MoveResponseJsonService {
    private static final String PATH_DIRECTORY = "src/main/resources/responsejson";

    public static boolean saveToJson(MoveResponse moveResponse, String fileName) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Создаем директорию, если она не существует
            File directory = new File(PATH_DIRECTORY);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Сохраняем MoveRequest в JSON файл
            File file = new File(PATH_DIRECTORY + "/" + fileName);
            mapper.writeValue(file, moveResponse);

            log.info("MoveRequest успешно сохранен в файл: " + file.getAbsolutePath());
            return true; // Сохранение прошло успешно
        } catch (IOException e) {
            log.info("Ошибка сохранения: проебали " + fileName);
//            e.printStackTrace();
            return false; // Возвращаем false в случае ошибки
        }
    }


    // Метод для десериализации JSON файла в объект MoveRequest
    public static MoveResponse loadFromJson(String fileName) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Чтение JSON файла и преобразование его в объект MoveRequest
            File file = new File(PATH_DIRECTORY + "/" + fileName);
            log.info("MoveeResponse успешно загружен из файла: " + file.getAbsolutePath());
            return mapper.readValue(file, MoveResponse.class);
        } catch (IOException e) {
            log.info("Ошибка загрузки: " + fileName);
//            e.printStackTrace();
            return null; // В случае ошибки возвращаем null
        }
    }
}
