import com.artcruz.Gender;
import com.artcruz.Helper;
import com.artcruz.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
    void declarativeSort() {
        List<Person> sorted = people.stream()
                .sorted(Comparator.comparing(Person::getAge).reversed())
                .toList();

        sorted.forEach(System.out::println);
    }

    @Test
    void declarativeAllMatch(){
        boolean allMatch = people.stream()
                .allMatch(person -> person.getAge() > 5);

        System.out.println(allMatch);
    }

    @Test
    void declarativeAnyMatch() {
        boolean anyMatch = people.stream()
                .anyMatch(person -> person.getAge() > 8);

        System.out.println(anyMatch);
    }

    @Test
    void declarativeNoneMatch() {
        boolean noneMatch = people.stream()
                .noneMatch(person -> person.getName().equals("Antonio"));

        System.out.println(noneMatch);
    }

    @Test
    void declarativeMax() {
        Person person = people.stream()
                .max(Comparator.comparing(Person::getAge))
                .orElse(null);

        System.out.println(person);
    }

    @Test
    void declarativeMin() {
        Person person = people.stream()
                .min(Comparator.comparing(Person::getAge))
                .orElse(null);

        System.out.println(person);
    }

    @Test
    void declarativeGroup() {
        Map<Gender, List<Person>> pplGroupedByGender = people.stream()
                .collect(Collectors.groupingBy(Person::getGender));

        pplGroupedByGender.forEach(((gender, people1) -> {
            System.out.println(gender);
            people1.forEach(System.out::println);
            System.out.println();
        }));
    }

    @Test
    void streamMethodsChainExample() {
        String oldestPersonName = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .max(Comparator.comparing(Person::getAge))
                .map(Person::getName).orElse(null);

        System.out.println(oldestPersonName);
    }

}
