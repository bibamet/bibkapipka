package lesson1.lesson1.PhoneBookImplementation;

import lesson1.lesson1.PhoneBook.PhoneBook;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Component
public class PhoneBookImplementation implements PhoneBook {

    private final Map<String, String> localRepository;

    public PhoneBookImplementation() {

        this.localRepository = this.getRepository();

    }

    public Map<String, String> getRepository() {

        Map<String, String> stringMap;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Repository"))) {

            stringMap = (HashMap<String, String>) ois.readObject();
            System.out.println("Файл прочитан.");
            return stringMap;

        }catch (FileNotFoundException c) {
            System.out.println("Файл не найден.");
        } catch (IOException e) {
            System.out.println("Достигли конца файла.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Возвращаем новую коллекцию.");
        return new HashMap<>();

    }

    protected boolean saveRepository() {

        try (ObjectOutputStream oin = new ObjectOutputStream(new FileOutputStream("Repository"))) {

            oin.writeObject(localRepository);
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;

    }

    @Override
    public String getInfo(String name) {

        if (localRepository.containsKey(name)) {
            return "Имя: " + name + ", номер: " + localRepository.get(name);
        }

        return "Контакт не найден.";
    }

    @Override
    public boolean setPhoneBookEntry(String name, String number) {
        localRepository.put(name, number);
        return saveRepository();
    }

    public boolean deletePhoneBookEntry(String name) {

        boolean changed;

        if (localRepository.containsKey(name)) {
            localRepository.remove(name);
            changed = true;
        }else changed = false;

        if (changed) {
            return saveRepository();
        } else return false;

   }

}
