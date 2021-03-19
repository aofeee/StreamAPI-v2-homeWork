package com.netology.homework.streamapipopcen;

import java.util.*;
import java.util.stream.Collectors;

public class Main implements Comparable {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John", "Kelly, Stanly, Bob,");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown", "Simpson");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        // Несовершеннолетние
        long young = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних людей в городе" +
                " составляет " + young + " человек.");


        // Военнообязаные
        long militaryMan = persons.stream()
                .filter(person -> person.getSex().equals(Sex.MAN))
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 27)
                .count();
        System.out.println("Общее число военнообязаных " + militaryMan + " человек.");

        // Фамилии военнообязаных
        System.out.println("Список фамилий военнообязаных");
        List<String> militarian = persons.stream()
                .filter(person ->  person.getSex().equals(Sex.MAN))
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(militarian);

        // Работоспособные
        long workMan = persons.stream()
                .filter(person -> person.getSex().equals(Sex.MAN) && person.getAge() >= 18 && person.getAge() <= 65
                && person.getEducation().equals(Education.HIGHER))
                .count();

        long workWomen = persons.stream()
                .filter(person -> person.getSex().equals(Sex.WOMEN) && person.getAge() >= 18 && person.getAge() <= 60
                && person.getEducation().equals(Education.HIGHER))
                .count();
        System.out.println("Количество работоспособных с высшим образованием " + workMan + " мужчин " +
                "и " + workWomen + " женщин.");


        // Список работоспособбных мужчин
        List<Person> workPowerMan = persons.stream()
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .filter(person -> person.getSex().equals(Sex.MAN) && person.getAge() >= 18 && person.getAge() <= 65)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println(workPowerMan);

        // Список работоспособных женщин
        List<Person> workPowerWomen = persons.stream()
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .filter(person -> person.getSex().equals(Sex.WOMEN) && person.getAge() >= 18 && person.getAge() <= 60)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println(workPowerWomen);


    }



    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
