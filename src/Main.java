import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
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
        long age18 = persons.stream()
                .filter(s1 -> s1.getAge() < 18)
                .count();
        System.out.println(age18);
        System.out.println();

        List<String> conscripts = persons.stream()
                .filter(s -> s.getAge() >= 18 && s.getAge() <=27)
                .filter(s -> s.getSex() == Sex.MAN).map(p->p.getFamily())
                .limit(10) // Для проверки
                .collect(Collectors.toList());
        conscripts.forEach(System.out::println);

        List<Person> workPeople = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getAge() >= 18)
                .filter(person -> (person.getSex() == Sex.WOMAN && person.getAge() < 60)
                        || (person.getSex() == Sex.MAN && person.getAge() < 65))
                .sorted(Comparator.comparing(Person::getFamily))
                .limit(10) // Для проверки
                .collect(Collectors.toList());
        workPeople.forEach(System.out::println);




    }
}
