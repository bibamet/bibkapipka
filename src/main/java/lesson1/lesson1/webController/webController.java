package lesson1.lesson1.webController;

import lesson1.lesson1.ConsumerJSON.ConsumerJSON;
import lesson1.lesson1.PhoneBook.PhoneBook;
import lesson1.lesson1.PhoneBookImplementation.PhoneBookImplementation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("phonebook")
public class webController<a> {

    protected PhoneBook phoneBook;

    public webController(PhoneBookImplementation phoneBook) {
        this.phoneBook = phoneBook;
    }

    @GetMapping(value = "get")
    public String getPhoneBookEntry(String name) {

        if (!"".equals(name)) {
            return phoneBook.getInfo(name);
        }

        return "Было передано пустое имя.";

    }

    @PostMapping(value = "set")
    public String setPhoneBookEntry(String name, String number) {

        if (!"".equals(name) & !"".equals(number) & !(number == null)) {
            if (phoneBook.setPhoneBookEntry(name, number)) return "Запись успешно добавлена";
            else return "Запись не была добавлена. Повторите попытку.";
        } else return "Было передано пустое имя или пустой номер";

    }

    @PostMapping(value = "setJson")
    public @ResponseBody ResponseEntity<?> setPhoneBookEntryJson( @RequestBody ConsumerJSON consumerJSON) {

        phoneBook.setPhoneBookEntry(consumerJSON.getName(), consumerJSON.getNumber());
        return new ResponseEntity<>("Запись успешно добавлена", HttpStatus.CREATED);

    }

    @GetMapping(value = "all")
    public @ResponseBody ResponseEntity<Map<String, String>> getAllPhoneBookEntries() {

        String local = "1";

        return new ResponseEntity<Map<String, String>>(phoneBook.getRepository(), HttpStatus.OK);

    }

}
