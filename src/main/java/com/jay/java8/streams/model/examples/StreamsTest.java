package com.jay.java8.streams.model.examples;

import com.jay.java8.model.User;
import com.jay.java8.streams.model.*;
import org.openjdk.jmh.annotations.*;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Collection of users, devices and operations
//   Get users by age >30 (filter, behaviour param, collect)
//   Get users by city == 'x'(filter, behaviour param, collect)
//   Get user devices sort by name (sort, collect)
// Get user devices sort by cost (sort, collect)
// What are the unique cities where users based out ?
// Return STRING of all the consumer names and sorted alphabetically
// Are any user based in Berlin ?
//   Get user 3 active(status on) devices and their operations (filter, limit, collect)
//   Get all the consumers who have devices of type Health active ?(filter, collect, )
//   Get all the users by device Type
//   Get the total amount user spent for his devices!
//   Check whether all devices are functioning and healthy :) (allMatch)

//  What is the highest device I paid for
public class StreamsTest {

    // Data preparation
   static List<Property> properties = Arrays.asList(Property.builder().name("brightness").value("120").build());

    static List<Event> bulbEvents = Arrays.asList(Event.builder().name("switchedOn").isActive(true).build(), Event.builder().name("switchedOff").isActive(true).build());
    static List<Event> motionDetectorEvents = Arrays.asList(Event.builder().name("motionCaptured").isActive(true).build());
    static List<Event> eyeVirusEvents = Arrays.asList(Event.builder().name("virusFound").isActive(true).build());
    static List<Event> waterLevelsEvents = Arrays.asList(Event.builder().name("dryField").isActive(true).build());

    static Thing bulb = Thing.builder().name("Bulb").cost(120).isRunning(true).type(Type.HOME).events(bulbEvents).build();
    static Thing motionDetector = Thing.builder().name("MotionDetector").cost(60).isRunning(true).type(Type.HOME).events(motionDetectorEvents).build();
    static Thing eyeVirusDetector = Thing.builder().name("EyeVirusDetector").cost(200).isRunning(true).type(Type.HEALTH).events(eyeVirusEvents).build();
    static Thing agriFieldSensor = Thing.builder().name("AgriFieldSensor").cost(300).isRunning(true).type(Type.FIELD).events(waterLevelsEvents).build();

    static Consumer malli = Consumer.builder().age(25).city("Hyd").name("Malli").things(Arrays.asList(bulb, motionDetector)).build();
    static Consumer sri = Consumer.builder().age(32).city("Hyd").name("Sri").things(Arrays.asList(bulb, eyeVirusDetector)).build();
    static Consumer satti = Consumer.builder().age(21).city("Hyd").name("Satti").things(Arrays.asList(agriFieldSensor, motionDetector)).build();
    static Consumer bob = Consumer.builder().age(35).city("Berlin").name("Bob").things(Arrays.asList(bulb, motionDetector, agriFieldSensor, eyeVirusDetector)).build();

    static List<Consumer> consumerList = Arrays.asList(malli, sri, satti, bob);

    public static void main(String[] args) {

        // Example 1: Get consumers by age > 30 (filter, collect(toList))
        getConsumersByAge();

        // Example 2: Get consumers by age > 30, map by their name and devices (filter, collect(toMap))
        getConsumerDevicesByAge();

        // Example 3: Get consumer 'Bob' devices sort by name (sort, collect)
        getDeviceSortByName();

        // Example 4: Get all the unique cities of consumers
        getUniqueCities();

        // Example 5: Are there any consumer living in Berlin>
        isAnyConsumerInBerlin();

        // Example 6: Return all the consumer names as STRING and sorted alphabetically
        allUsersAsSortedString();

        // Example 7: Get all the events(operations) of active/running devices
        allActiveDeviceAndOperations();

        // Example 8: Get all the consumers who have device type Health and active?
        allConsumersWhoHasHealthDevice();

        // Example 9: Get all the consumers by device Type
        allUsersByDeviceType();

        // Example 10: Get the total amount consumer spent for his all devices!
        consumerWithTotalAmount();

        // Example 11: Check whether all devices are running and healthy?
        isAlDevicesRunnig();

        // Example 12: Check whether all 'Bob' devices are running and healthy?
        bobAllDevicesRunning();

        // Example 13: Get all consumers and their each device health?
        consumerWithEachDeviceStatus();

        // Example 14: What is the highest device cost, each consumer paid for?
        highestDeviceCostCustomerPaid();

    }

    private static void highestDeviceCostCustomerPaid() {
        Map<String, Optional<Integer>> highestDeviceCostCustomerPaid = consumerList
                .stream()
                .flatMap(consumer -> consumer.getThings()
                        .stream()
                        .map(thing -> new AbstractMap.SimpleImmutableEntry<>(consumer.getName(), thing.getCost()))
                )
                .collect(Collectors.groupingBy(entry -> entry.getKey(),
                        Collectors.mapping(entry -> entry.getValue(), Collectors.maxBy(Comparator.comparingInt(value -> value)))));

        System.out.println(highestDeviceCostCustomerPaid);
        // Output: {Bob=Optional[300], Satti=Optional[300], Sri=Optional[200], Malli=Optional[120]}
    }

    private static void consumerWithEachDeviceStatus() {
        Map<String, Map<Thing, Boolean>> consumerWithEachDeviceStatus = consumerList
                .stream()
                .flatMap(consumer -> consumer.getThings()
                        .stream()
                        .map(thing -> new AbstractMap.SimpleImmutableEntry<>(consumer.getName(), thing))
                )
                .collect(Collectors.groupingBy(entry -> entry.getKey(),
                        Collectors.toMap(AbstractMap.SimpleImmutableEntry::getValue, entry -> entry.getValue().isRunning())));

        System.out.println(consumerWithEachDeviceStatus);
        //Output: {Bob={Thing(name=AgriFieldSensor)=true, Thing(name=EyeVirusDetector)=true, Thing(name=Bulb)=true, Thing(name=MotionDetector)=true}, Satti={Thing(name=AgriFieldSensor)=true, Thing(name=MotionDetector)=true}, Sri={Thing(name=EyeVirusDetector)=true, Thing(name=Bulb)=true}, Malli={Thing(name=Bulb)=true, Thing(name=MotionDetector)=true}}
    }

    private static void bobAllDevicesRunning() {
        boolean bobAllDevicesRunning = consumerList
                .stream()
                .filter(consumer -> "Bob".equals(consumer.getName()))
                .flatMap(consumer -> consumer.getThings().stream())
                .allMatch(Thing::isRunning);
        System.out.println(bobAllDevicesRunning);
        // Output : true
    }

    private static void isAlDevicesRunnig() {
        boolean isAlDevicesRunnig = consumerList
                .stream()
                .flatMap(consumer -> consumer.getThings().stream())
                // 'allMatch' is a terminal operation
                .allMatch(Thing::isRunning);
        System.out.println(isAlDevicesRunnig);
        // Output : true
    }

    private static void consumerWithTotalAmount() {
        Map<String, Integer> consumerWithTotalAmount = consumerList
                .stream()
                .flatMap(consumer -> consumer.getThings()
                        .stream()
                        .map(thing -> new AbstractMap.SimpleImmutableEntry<>(consumer.getName(), thing.getCost()))
                ).collect(Collectors.groupingBy(AbstractMap.SimpleImmutableEntry::getKey,
                        Collectors.mapping(entry -> entry.getValue(), Collectors.summingInt(value -> value))));

        System.out.println(consumerWithTotalAmount);
        // Output: {Bob=680, Satti=360, Sri=320, Malli=180}
        // In the above code, interesting thing I like is Collectors.summingInt, which is summing device cost.
    }

    private static void allUsersByDeviceType() {
        Map<Type, Set<String>> allUsersByDeviceType = consumerList
                .stream()
                .flatMap(consumer -> consumer.getThings()
                        .stream()
                        .map(thing -> new AbstractMap.SimpleImmutableEntry<>(thing.getType(), consumer.getName()))
                ).collect(Collectors.groupingBy(AbstractMap.SimpleImmutableEntry::getKey,
                        Collectors.mapping(entry -> entry.getValue(), Collectors.toSet()))
                );

        System.out.println(allUsersByDeviceType);
        //Output: {HOME=[Satti, Bob, Sri, Malli], HEALTH=[Bob, Sri], FIELD=[Satti, Bob]}
        // In the above code, interesting thing I like is Collectors.mapping, which is helping to collect map values as set.
    }

    private static void allConsumersWhoHasHealthDevice() {
        // How to loop over nested collections referring to parent elements?
        List<Consumer> allConsumersWhoHasHealthDevice = consumerList
                .stream()
                .flatMap(consumer -> consumer.getThings().stream()
                                .filter(thing -> thing.isRunning() && Type.HEALTH == thing.getType())
                                // This is cool!!!
                                .map(thing -> new AbstractMap.SimpleImmutableEntry<>(consumer, thing))
                )
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
        System.out.println(allConsumersWhoHasHealthDevice);
        // Output: [Consumer(name=Sri, city=Hyd), Consumer(name=Bob, city=Berlin)]
        // In the above code, very interesting point is, we hold the outer/parent element 'Consumer'
    }

    private static void allActiveDeviceAndOperations() {
        // How to loop nested collection using lambda streams?
        Set<Event> allActiveDeviceOperations = consumerList
                .stream()
                // 'flatMap' intermediate operation, here produces the stream of user Things(Devices)
                .flatMap(consumer -> consumer.getThings().stream())
                .filter(thing -> thing.isRunning())
                .flatMap(thing -> thing.getEvents().stream())
                .collect(Collectors.toSet());
        System.out.println(allActiveDeviceOperations);
        //Output : [Event(name=motionCaptured), Event(name=switchedOff), Event(name=switchedOn), Event(name=virusFound), Event(name=dryField)]
    }

    private static void allUsersAsSortedString() {
        consumerList
                .stream()
                // map intermediate operation
                // 'map' is used to transform one element type into other, here we transformed Consumer into String
                .map(Consumer::getName)
                // 'distinct' intermediate operation
                .distinct()
                // 'sorted' intermediate operation
                .sorted()
                // 'reduce' terminal operation
                // 'reduce' is used to produce single output
                // .reduce(String::concat);
                .reduce((preVName, nextName) -> preVName + " " + nextName)
                .ifPresent((result) -> System.out.println(result));

        // Outout: Bob Malli Satti Sri
        // In above code you can also use 'reduce(String::concat)'
        // Please read Optional to understand more on ifPresent method
    }

    private static void isAnyConsumerInBerlin() {
        boolean isAnyConsumerInBerlin = consumerList
                .stream()
                // anyMatch terminal operation
                .anyMatch(consumer -> "Berlin".equals(consumer.getCity()));
        System.out.println(String.format("Are there any user based in Berlin: %b", isAnyConsumerInBerlin));
        // Output: Are there any user based in Berlin:true
    }

    private static void getUniqueCities() {
        List<String> uniqueCities = consumerList
                .stream()
                .map(Consumer::getCity)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(uniqueCities);
        // Output: [Hyd, Berlin]
    }

    private static void getDeviceSortByName() {
        // User Bob devices sort by name
        List<Thing> bobDevicesSortByName = bob.getThings()
                .stream()
                // sorted is an intermediate operation
                .sorted(Comparator.comparing(Thing::getName))
                .collect(Collectors.toList());
        System.out.println(bobDevicesSortByName);
        // Output: [Thing(name=AgriFieldSensor), Thing(name=Bulb), Thing(name=EyeVirusDetector), Thing(name=MotionDetector)]
    }

    private static void getConsumerDevicesByAge() {
        Map<String, List<Thing>> consumerDevicesByAge =
                consumerList
                        .stream()
                        // filter is an intermediate operation
                        .filter(consumer -> consumer.getAge() > 30)
                        // collect is a terminal operation
                        .collect(Collectors.toMap(Consumer::getName, Consumer::getThings));

        // In the above code, used 'Method reference(shorthand form of lamda)' Consumer::getName which is equalent to consumer -> consumer.getName()
        System.out.println(consumerDevicesByAge);
        // Output: {Bob=[Thing(name=Bulb), Thing(name=MotionDetector), Thing(name=AgriFieldSensor), Thing(name=EyeVirusDetector)], Sri=[Thing(name=Bulb), Thing(name=EyeVirusDetector)]}
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Warmup(iterations = 20)
    @Measurement(iterations = 20)
    @Fork(3)
    private static void getConsumersByAge() {
        List<Consumer> consumersByAge =
                consumerList
                        .stream()
                        // filter is an intermediate operation
                        .filter(consumer -> consumer.getAge() > 30)
                        // collect is a terminal operation
                        .collect(Collectors.toList());

        System.out.println(consumersByAge);
        // Output: [Consumer(name=Sri, city=Hyd), Consumer(name=Bob, city=Berlin)]
    }
}
