package by.clevertec;

import by.clevertec.model.*;
import by.clevertec.util.Util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        final LocalDate now = LocalDate.now();
        final LocalDate years18 = now.minusYears(18);
        final LocalDate malePensionYear = now.minusYears(63).plusDays(1);
        final LocalDate femalePensionYear = now.minusYears(58).plusDays(1);
        houses.stream()
                .flatMap(house -> house.getPersonList().stream()
                        .map(person -> Map.entry("Hospital".equals(house.getBuildingType()) ? 1 : person.getDateOfBirth().isAfter(years18) ||
                                ("Male".equals(person.getGender()) && person.getDateOfBirth().isBefore(malePensionYear)) ||
                                ("Female".equals(person.getGender()) && person.getDateOfBirth().isBefore(femalePensionYear)) ? 2 : 3, person)))
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .flatMap(e -> e.getValue().stream())
                .limit(500)
                .forEach(System.out::println);
    }

    public static void task14() {
        List<Car> cars = Util.getCars();
        final BigDecimal costKilogram = BigDecimal.valueOf(7.14).multiply(BigDecimal.valueOf(0.01));
        final Map<Integer, Predicate<Car>> predicates = new LinkedHashMap<>();
        predicates.put(1, car -> (car.getCarMake().equals("Jaguar")) ||
                car.getCarModel().equals("White"));
        predicates.put(2, car -> (car.getMass() < 1500 ||
                car.getCarMake().equals("BMW") ||
                car.getCarMake().equals("Lexus") ||
                car.getCarMake().equals("Chrysler") ||
                car.getCarMake().equals("Toyota")));
        predicates.put(3, car -> (car.getColor().equals("Black") && car.getMass() > 4000) ||
                car.getCarMake().equals("GMC") ||
                car.getCarMake().equals("Dodge"));
        predicates.put(4, car -> (car.getReleaseYear() < 1982 ||
                car.getCarModel().equals("Civic") ||
                car.getCarModel().equals("Cherokee")));
        predicates.put(5, car -> (!("Yellow".equals(car.getColor()) ||
                "Red".equals(car.getColor()) ||
                "Green".equals(car.getColor()) ||
                "Blue".equals(car.getColor())) ||
                car.getPrice() > 40000));
        predicates.put(6, car -> (car.getVin() != null && car.getVin().contains("59")));
        predicates.put(7, (car) -> true);
        cars.stream()
                .map(car -> Map.entry(predicates.entrySet().stream()
                        .filter(entry -> entry.getValue().test(car))
                        .findFirst()
                        .get().getKey(), car))
                .filter(entry -> entry.getKey() < 7)
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList())))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .map(carList -> Map.entry(carList,
                        costKilogram.multiply(BigDecimal.valueOf(carList.stream()
                                .mapToInt(Car::getMass)
                                .sum()))))
                .peek(entry -> System.out.println(entry.getValue()))
                .map(carCost -> carCost.getKey().stream()
                        .map(car -> BigDecimal.valueOf(car.getPrice()))
                        .reduce(BigDecimal::add)
                        .orElse(BigDecimal.ZERO).subtract(carCost.getValue()))
                .reduce(BigDecimal::add)
                .ifPresent(System.out::println);
    }

    public static void task15() {
        List<Flower> flowers = Util.getFlowers();
        final Set<String> prefeVaseMaterialSet = Set.of("Glass", "Aluminum", "Steel");
        final double waterCostPerCubicMeter = 1.39;
        final int timeInterval = 5 * 365;
        double amountFounds = flowers.stream()
                .sorted(Comparator.comparing(Flower::getOrigin).reversed()
                        .thenComparing(Flower::getPrice)
                        .thenComparing(Comparator.comparing(Flower::getWaterConsumptionPerDay).reversed()))
                .filter(flower -> !flower.getCommonName().isEmpty())
                .filter(flower -> flower.getCommonName().charAt(0) <= 'S')
                .filter(f -> f.getCommonName().charAt(0) > 'C')
                .filter(Flower::isShadePreferred)
                .filter(flower -> flower.getFlowerVaseMaterial().stream().anyMatch(prefeVaseMaterialSet::contains))
                .mapToDouble(flower -> flower.getPrice() + flower.getWaterConsumptionPerDay() * timeInterval * waterCostPerCubicMeter / 1000)
                .sum();
        System.out.printf("The total cost of maintenance of all plants: %.2f", amountFounds);
    }

    public static void task16() {
        List<Student> students = Util.getStudents();
        students.stream()
                .filter(student -> student.getAge() < 18)
                .sorted(Comparator.comparing(Student::getSurname))
                .forEach(student -> System.out.println(student.getSurname() + ", age:" + student.getAge()));
    }

    public static void task17() {
        List<Student> students = Util.getStudents();
        students.stream()
                .map(Student::getGroup)
                .distinct()
                .forEach(System.out::println);
    }

    public static void task18() {
        List<Student> students = Util.getStudents();
        students.stream()
                .collect(Collectors.groupingBy(Student::getFaculty,
                        Collectors.averagingInt(Student::getAge)))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> System.out.println("Faculty: " + entry.getKey() + ", middle age: " + Math.round(entry.getValue())));
    }

    public static void task19() {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();
        final String groupName = "C-2";
        students.stream()
                .filter(student -> student.getGroup().equals(groupName))
                .filter(student -> examinations.stream()
                        .anyMatch(exam -> exam.getStudentId() == student.getId() && exam.getExam1() > 4 && exam.getExam2() > 4 && exam.getExam3() > 4))
                .forEach(System.out::println);
    }

    public static void task20() {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();
        students.stream()
                .collect(Collectors.groupingBy(Student::getFaculty,
                        Collectors.averagingDouble(student -> examinations.stream()
                                .filter(exam -> exam.getStudentId() == student.getId())
                                .mapToDouble(Examination::getExam1)
                                .average()
                                .orElse(0))))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(entry -> System.out.println("Faculty: " + entry.getKey() + " Max rating: " + entry.getValue()));
    }

    public static void task21() {
        List<Student> students = Util.getStudents();
        students.stream()
                .collect(Collectors.groupingBy(Student::getGroup, Collectors.counting()))
                .forEach((group, count) -> System.out.println("Group: " + group + ", count: " + count));
    }

    public static void task22() {
        List<Student> students = Util.getStudents();
        students.stream()
                .collect(Collectors.groupingBy(Student::getFaculty,
                        Collectors.minBy(Comparator.comparingInt(Student::getAge))))
                .forEach((faculty, student) ->
                        System.out.println("Faculty: " + faculty + ", min age of student: " + student.get().getAge())
                );
    }
}

