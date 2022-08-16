import com.artcruz.Gender;
import com.artcruz.Helper;
import com.artcruz.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class TestApp {

    private List<Person> people;

    @BeforeEach
    void init() {
        people = Helper.getPeople();
    }

    @Test
    void imperativeFilter() {
        List<Person> females = new ArrayList<>();

        for (Person person : people) {
            if (person.getGender().equals(Gender.FEMALE)) {
                females.add(person);
            }
        }

        females.forEach(System.out::println);
    }

    @Test
    void declarativeFilter() {
        people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    @Test
    void imperativeSort() {
        boolean sorted;

        do {
            sorted = true;

            for (int i = 0; i < people.size() - 1; i++) {
                int j = i + 1;
                Person person = people.get(i);
                Person nextPerson = people.get(j);

                if (person.getAge() > nextPerson.getAge()) {
                    sorted = false;
                    people.set(i, nextPerson);
                    people.set(j, person);
                }
            }
        } while (!sorted);

        people.forEach(System.out::println);
    }

    @Test
    void declarativeSort() {
        List<Person> sorted = people.stream()
                .sorted(Comparator.comparing(Person::getAge))
                .toList();

        sorted.forEach(System.out::println);
    }

    @Test
    void imperativeAllMatch() {
        boolean allMatch = true;

        for(Person person : people){
            if (person.getAge() <= 5) {
                allMatch = false;
            }
        }

        System.out.println(allMatch);
    }

    @Test
    void declarativeAllMatch(){
        boolean allMatch = people.stream()
                .allMatch(person -> person.getAge() > 5);

        System.out.println(allMatch);
    }

    @Test
    void imperativeAnyMatch() {
        boolean anyMatch = false;

        for(Person person : people){
            if (person.getAge() > 8) {
                anyMatch = true;
            }
        }

        System.out.println(anyMatch);

    }

    @Test
    void declarativeAnyMatch() {
        boolean anyMatch = people.stream()
                .anyMatch(person -> person.getAge() > 8);

        System.out.println(anyMatch);
    }

    @Test
    void imperativeNoneMatch() {
        boolean noneMatch = true;

        for(Person person : people){
            if ("Antonio".equals(person.getName())) {
                noneMatch = false;
            }
        }

        System.out.println(noneMatch);
    }

    @Test
    void declarativeNoneMatch() {
        boolean noneMatch = people.stream()
                .noneMatch(person -> person.getName().equals("Antonio"));

        System.out.println(noneMatch);
    }

    @Test
    void imperativeMax() {
        int maxAge = people.get(0).getAge();

        for (Person person : people) {
            if(person.getAge() > maxAge) {
                maxAge = person.getAge();
            }
        }

        System.out.println(maxAge);
    }

    @Test
    void declarativeMax() {
        Person person = people.stream()
                .max(Comparator.comparing(Person::getAge))
                .orElse(null);

        System.out.println(person);
    }

    @Test
    void imperativeMin() {
        int minAge = people.get(0).getAge();

        for (Person person : people) {
            if(person.getAge() < minAge) {
                minAge = person.getAge();
            }
        }

        System.out.println(minAge);
    }

    @Test
    void declarativeMin() {
        Person person = people.stream()
                .min(Comparator.comparing(Person::getAge))
                .orElse(null);

        System.out.println(person);
    }

    @Test
    void imperativeGroup(){
        Map<Gender, List<Person>> pplGroupedByGender = new HashMap<>();

        for (Person person : people) {
            if(pplGroupedByGender.containsKey(person.getGender())){
                pplGroupedByGender.get(person.getGender()).add(person);
            } else {
                pplGroupedByGender.put(person.getGender(), new ArrayList<>(Arrays.asList(person)));
            }
        }

        pplGroupedByGender.forEach(((gender, people) -> {
            System.out.println(gender);
            people.forEach(System.out::println);
            System.out.println();
        }));
    }

    @Test
    void declarativeGroup() {
        Map<Gender, List<Person>> pplGroupedByGender = people.stream()
                .collect(Collectors.groupingBy(Person::getGender));

        pplGroupedByGender.forEach(((gender, people) -> {
            System.out.println(gender);
            people.forEach(System.out::println);
            System.out.println();
        }));
    }

    @Test
    void streamMethodsChainExample() {
        String oldestPersonName = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .max(Comparator.comparing(Person::getAge))
                .map(person -> "Name: " + person.getName() + "\nAge: " + person.getAge())
                .orElse(null);

        System.out.println(oldestPersonName);
    }

    @Test
    void streamMethodsChainExampleImperativeEquivalent() {
        List<Person> females = new ArrayList<>();

        for (Person person : people) {
            if (person.getGender().equals(Gender.FEMALE)) {
                females.add(person);
            }
        }

        int maxAge = people.get(0).getAge();

        Person oldestPerson = people.get(0);

        for (Person person : females) {
            if(person.getAge() > maxAge) {
                maxAge = person.getAge();
                oldestPerson = person;
            }
        }

        System.out.println("Name: " + oldestPerson.getName() + "\nAge: " + oldestPerson.getAge());
    }

}
