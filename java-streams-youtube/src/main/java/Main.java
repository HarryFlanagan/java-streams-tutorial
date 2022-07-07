import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    List<Person> people = getPeople();

    // Imperative approach ❌
    //    List<Person> females = new ArrayList<>();
    //
    //    for (Person person : people){
    //      if (person.getGender().equals(Gender.FEMALE)){
    //        females.add((person));
    //      }
    //    }


    // Declarative approach ✅

    // Filter
    List<Person> females = people.stream()
        .filter(person -> person.getGender() // filter by gender
            .equals(Gender.FEMALE)) // get females
        .collect(Collectors.toList()); // add to list
    // females.forEach(System.out::println);

    // Sort
    List<Person> sorted = people.stream()
        .sorted(Comparator.comparing(Person::getAge) // Sort by age
            .thenComparing(Person::getGender) // compare by gender
            .reversed()) // reverse
        .collect(Collectors.toList()); // add to list
    // sorted.forEach(System.out::println);

    // All match
    boolean allMatch = people.stream()
        .allMatch(person -> person.getAge() > 5);
    // System.out.println(allMatch);

    // Any match
    boolean anyMatch = people.stream()
        .anyMatch(person -> person.getAge() > 18);
    // System.out.println(anyMatch);

    // None match
    boolean noneMatch = people.stream()
        .anyMatch(person -> person.getName().equals("Antonio"));
    // System.out.println(anyMatch);

    // Max
//    people.stream()
//        .max(Comparator.comparing(Person::getAge))
//        .ifPresent(System.out::println);

    // Min
//    people.stream()
//        .min(Comparator.comparing(Person::getAge))
//        .ifPresent(System.out::println);

    // Group
    Map<Gender, List<Person>> groupByGender = people.stream()
        .collect(Collectors.groupingBy(Person::getGender));
//    groupByGender.forEach((gender, people1) -> {
//      System.out.println(gender);
//      people1.forEach(System.out::println);
//    });

    // Chain multiple streams
    Optional<String> oldestFemale = people.stream()
        .filter(person -> person.getGender().equals(Gender.FEMALE))
        .max(Comparator.comparing(Person::getAge))
        .map(Person::getName);
    oldestFemale.ifPresent(System.out::println);

  }

  private static List<Person> getPeople() {
    return List.of(
        new Person("Harry Flanagan", 20, Gender.MALE),
        new Person("Claire", 33, Gender.FEMALE),
        new Person("Lucy White", 57, Gender.FEMALE),
        new Person("Art Boz", 14, Gender.MALE),
        new Person("Jamie Goa", 99, Gender.MALE),
        new Person("Tara Cook", 7, Gender.FEMALE),
        new Person("Jasmin Brown", 120, Gender.FEMALE)
    );
  }

}
