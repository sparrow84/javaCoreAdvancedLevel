package com.company.lesson03;

import java.util.*;

public class Phonebook {
    String name;
    Map<String, String> pBook;

    /*
     * number   - Key
     * lastName - Value
     * */

    public Phonebook () {
        pBook = new HashMap<String, String>();
    }

    public void add (String lastName, String number) {
        pBook.put(number, lastName);
    }

    public List get (String lastName) {

        List<String> pNumbers = new ArrayList<String>();
        Set<Map.Entry<String, String>> set = pBook.entrySet();

        for (Map.Entry<String,String> o: set) {
            if (o.getValue().toLowerCase().equals(lastName.toLowerCase())) {
                pNumbers.add(o.getKey());
            }
        }

        return pNumbers;
    }
}
