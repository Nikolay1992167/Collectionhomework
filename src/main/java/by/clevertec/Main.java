package by.clevertec;

import by.clevertec.model.Animal;
import by.clevertec.model.Car;
import by.clevertec.model.Examination;
import by.clevertec.model.Flower;
import by.clevertec.model.House;
import by.clevertec.model.Person;
import by.clevertec.model.Student;
import by.clevertec.util.Util;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
        task8();
        task9();
        task10();
        task11();
        task12();
        task13();
        task14();
        task15();
        task16();
        task17();
        task18();
        task19();
        task20();
        task21();
        task22();
    }

    public static void task1() {
        final int countAnimalForZoo = 7;
        Map<Integer, List<Animal>> groupedAnimals = new HashMap<>();
        AtomicInteger count = new AtomicInteger();
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getAge() > 10 && animal.getAge() < 20)
                .sorted(Comparator.comparing(Animal::getAge))
                .forEach(animal -> {
                    int groupNumber = count.get() / countAnimalForZoo;
                    List<Animal> group = groupedAnimals.getOrDefault(groupNumber, new ArrayList<>());
                    group.add(animal);
                    groupedAnimals.put(groupNumber, group);
                    count.incrementAndGet();
                });
        groupedAnimals.get(2).forEach(System.out::println);
    }

    public static void task2() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getOrigin().equals("Japanese"))
                .peek(animal -> animal.setBread(animal.getBread().toUpperCase()))
                .filter(animal -> animal.getGender().equals("Female"))
                .map(Animal::getBread)
                .forEach(System.out::println);
    }

    public static void task3() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getAge() > 30)
                .map(Animal::getOrigin)
                .filter(country -> country.startsWith("A"))
                .distinct()
                .forEach(System.out::println);
    }

    public static void task4() {
        List<Animal> animals = Util.getAnimals();
        long numberOfAnimalWithGenderFemale = animals.stream()
                .filter(animal -> animal.getGender().equals("Female"))
                .count();
        System.out.println("Number of all sex animals Female: " + numberOfAnimalWithGenderFemale);
    }

    public static void task5() {
        List<Animal> animals = Util.getAnimals();
        boolean isExistFromHungarian = animals.stream()
                .filter(animal -> animal.getAge() >= 20 && animal.getAge() <= 30)
                .anyMatch(animal -> animal.getOrigin().equals("Hungarian"));
        System.out.println(isExistFromHungarian);
    }

    public static void task6() {
        List<Animal> animals = Util.getAnimals();
        final boolean isExistAllMaleOrFemale = animals.stream()
                .allMatch(a -> "Male".equals(a.getGender()) || "Female".equals(a.getGender()));
        System.out.println("Are all animals of the gender Male and Female: " + isExistAllMaleOrFemale);
    }

    public static void task7() {
        List<Animal> animals = Util.getAnimals();
        boolean isNotExistAnimalFromOceania = animals.stream()
                .noneMatch(animal -> animal.getOrigin().equals("Oceania"));
        System.out.println("Does at least one animal have an Oceania country of origin: " + isNotExistAnimalFromOceania);
    }

    public static void task8() {
        List<Animal> animals = Util.getAnimals();
        int maxAge = animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(100)
                .mapToInt(Animal::getAge)
                .max()
                .orElse(0);
        System.out.println("Age of the oldest animal: " + maxAge);
    }

    public static void task9() {
        List<Animal> animals = Util.getAnimals();
        long minLengthTitleBreed = animals.stream()
                .map(Animal::getBread)
                .mapToLong(breed -> breed.chars().count())
                .min()
                .orElse(0);
        System.out.println("The length of the shortest breed name: " + minLengthTitleBreed);
    }

    public static void task10() {
        List<Animal> animals = Util.getAnimals();
        Integer sumOfAges = animals.stream()
                .reduce(0, (sumAge, animal) -> sumAge + animal.getAge(), Integer::sum);
        System.out.println("The total age of all animals: " + sumOfAges);
    }

    public static void task11() {
        List<Animal> animals = Util.getAnimals();
        double averageAgeAnimalsFromIndonesia = animals.stream()
                .filter(animal -> animal.getOrigin().equals("Indonesian"))
                .mapToInt(Animal::getAge)
                .average()
                .orElse(Double.NaN);
        System.out.println("Middle age: " + averageAgeAnimalsFromIndonesia);
    }

    public static void task12() {
        List<Person> persons = Util.getPersons();
        persons.stream()
                .filter(person -> person.getGender().equals("Male"))
                .filter(p -> {
                    int age = Period.between(p.getDateOfBirth(), LocalDate.now()).getYears();
                    return age >= 18 && age <= 27;
                })
                .sorted(Comparator.comparing(Person::getRecruitmentGroup))
                .forEach(System.out::println);
    }


    public static void task13() {
        List<House> houses = Util.getHouses();
//        houses.stream() Продолжить ...
    }

    public static void task14() {
        List<Car> cars = Util.getCars();
//        cars.stream() Продолжить ...
    }

    public static void task15() {
        List<Flower> flowers = Util.getFlowers();
//        flowers.stream() Продолжить ...
    }

    public static void task16() {
        List<Student> students = Util.getStudents();
//        students.stream() Продолжить ...
    }

    public static void task17() {
        List<Student> students = Util.getStudents();
//        students.stream() Продолжить ...
    }

    public static void task18() {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();
//        students.stream() Продолжить ...
    }

    public static void task19() {
        List<Student> students = Util.getStudents();
//        students.stream() Продолжить ...
    }

    public static void task20() {
        List<Student> students = Util.getStudents();
//        students.stream() Продолжить ...
    }

    public static void task21() {
        List<Student> students = Util.getStudents();
//        students.stream() Продолжить ...
    }

    public static void task22() {
        List<Student> students = Util.getStudents();
//        students.stream() Продолжить ...
    }
}
