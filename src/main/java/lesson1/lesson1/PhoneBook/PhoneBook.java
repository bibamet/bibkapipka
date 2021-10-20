package lesson1.lesson1.PhoneBook;

import java.util.Map;

public interface PhoneBook {

    public String getInfo(String name);
    public boolean setPhoneBookEntry(String name, String number);
    public boolean deletePhoneBookEntry(String name);
    public Map<String, String> getRepository();

}
