randomizer
==========

This project is still under heavy development, so please don't get too angry when things change a lot. We will do our best to avoid too 
many major changes of the architecture in the future.

`randomizer` is a Java library meant to provide randomized lists, by accessing these lists through iterators that retrieve the elements 
in a randomized order.


Build and Install
-----------------

The library can be built via Maven:

```
mvn compile
```

To use the library in another Maven based project, it is for now necessary to install the library in the local Maven repository via
```
mvn install
```
and then include it as dependency in the project.
```xml
<project>
    ...
    <dependencies>
        <dependency>
            <groupId>net.sepp-tember.lib</groupId>
            <artifactId>randomizer</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>
    ...
</project>
```
It is planned to deploy the library to some Maven repository, ideally Central repository. But for now it is needed to install the library
 in the local repository to use it as dependency.


Usage
-----

A small example on how to use `WeightedRandomizedList` to get some elements in a random order:
```java
    // create randomized list
    WeightedRandomizedList<Number> randomizedList = new WeightedRandomizedList<>();

    // fill it with some elements
    randomizedList.add(new WeightedElement<>(0.3, 23));
    randomizedList.add(new WeightedElement<>(0.6, 42));
    randomizedList.add(new WeightedElement<>(0.8, 12));
    
    // create randomized iterator
    Iterator<WeightedElement<Number>> iterator = randomizedList.randomizedIterator();
    
    for (int i = 0; i < 10000; i++) {
        // get elements in random order
        Number next = iterator.next().getElement();
        // then do something with the element
    }
```
The `WeightedRandomizedList` provides a randomized iterator that delivers an endless sequence of elements (i.e. `iterator.hasNext()` 
always returns `true`). The distribution of the elements is weighted; the higher the weight, the more often the elements gets drawn. The 
total sum of the weights in the list does not have to be 1, each weight gets normalized by the total sum of weights. So in the above 
example the number 23 gets drawn approximately 1765 times, 42 3529 times, and 12 4706 times. Of course the exact number can not be 
foreseen, because the drawing of the elements is based on a random number generator.
