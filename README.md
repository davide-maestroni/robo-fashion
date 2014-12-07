#robo-fashion
[![Build Status](https://travis-ci.org/davide-maestroni/robo-fashion.svg?branch=master)](https://travis-ci.org/davide-maestroni/robo-fashion)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.davide-maestroni/robo-fashion/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.davide-maestroni/robo-fashion)

This library employs builder and fluent design patterns to enable easy iteration and manipulation of Android sparse collections.

##Overview

The Android platform introduced a few collection classes based on the sparse array implementation. Although they bring improved performance and optimized memory usage to the classic Java collections world, they sometimes become awkward to use when implementing the most basic pieces of code. For example, good old iteration is not supported, so that, while all Java developers are by now used to the short and elegant syntax:

```java
for (final Entry<Integer,MyClass> entry : map.entrySet()) {

  doSomethingWithKey(entry.getKey());
  doSomethingWithValue(entry.getValue());
}
```

when employing Android sparse collections, you quickly end up in rewriting one time too often the same ugly code:

```java
final int size = collection.size();

for (int i = 0; i < size; ++i) {

  doSomethingWithKey(collection.keyAt(i));
  doSomethingWithValue(collection.valueAt(i));
}
```

The autocomplete feature of most Java or Android IDEs is not of much help here...

The purpose of this library is to provide an easy way to iterate through the elements of the Android sparse collections and manipulate them in the while.

##Usage

The specific sparse collection can be iterated both by using the classic Java syntax:

```java
for (final SparseArrayEntry<String> entry: AndroidCollections.iterate(array)) {

  // ... do your stuff ...
}
```

Through a more fluent syntax, like:

```java
AndroidCollections.iterate(array)
                  .only()
                  .from(4)
                  .forEach((element, index) -> {
                    // ... do your stuff ...
                  });
```

Or even by mixing the two:

```java
for (final SparseArrayEntry<String> entry: AndroidCollections.iterate(array)
                                                             .only()
                                                             .first(3)) {

  // ... do your stuff ...
}
```

As shown in the above examples inclusion and exclusion filters can be applied to the iteration. Besides, multiple filters can be concatenated so to include in the iteration only the elements matching specific requirements.
In order to fully understand the filter concatenation behavior, note that the output of the following code:

```java
final SparseArrayCompat<String> array = new SparseArrayCompat<String>();

for (int i = 0; i < 5; i++) {

  array.append(i, String.valueOf(i));
}

AndroidCollections.iterate(array)
                  .only()
                  .first(3)
                  .only()
                  .last(2)
                  .reverse()
                  .forEach((element, index) -> {
                    System.out.println(element.getValue());
                  });
```

will be:

```java
  2
  1
```

The [Filters][3] class already provides several filter implementations (like last *N* elements or first *N* elements), but additional custom filters can be easily implemented and concatenated with the existing ones.

The iterable implementation is not thread safe (unless differently specified) and not fail-fast, that is, if the wrapped collection is changed during the iteration no exception will be thrown, and further call to the iterator or to the entries methods might lead to unexpected results.

The entries returned by the iterator can be safely accessed only inside the iteration loop, since they have direct access to the wrapped sparse collection. This is an explicit design choice to try preserving both highest performance and lowest memory consumption.
In case the caller needed to retain an entry instance outside the loop, it must create an immutable or parcelable copy and retain that instead:

```java
final SparseArrayCompat<String> array = new SparseArrayCompat<String>();

for (int i = 0; i < 5; i++) {

  array.append(i, String.valueOf(i));
}

IntSparseObjectEntry<String> myEntry;

for (final SparseArrayEntry<String> entry: AndroidCollections.iterate(array)) {

  if ("3".equals(entry.getValue())) {

    myEntry = entry.toImmutable();

    break;
  }
}
```

##Documentation

Complete [Javadocs][4] with insights and examples are available.

The project contains an additional sample module showing how to implement an enhanced version of a [SimpleArrayMap][7] supporting parcelable serialization, iteration and proper *equals()* implementation (see issue [68406][6]).

##Performance

The iterator implementation is generally faster than the correspondent Java map collection, but, in any case, slower than directly accessing the Android collection object. In fact, there could be nothing faster than reading an element from a primitive array by its index. It is also true that the sparse collections were designed for best performance when holding a relative small number of elements. In such cases iteration is for sure not the bottleneck, so you'd better have flexibility and readability than speed.

##Further development

When Java 8 will be ported to Android, the library might need to be updated in order to support the new [Stream][8] APIs.

##Build instructions

To generate the library JAR just run on a command line:
```sh
gradlew jarR
```

For the Javadocs run:
```sh
gradlew javadocR
```

To run the tests on a connected device:
```sh
gradlew cAT
```

To run the Android lint:
```sh
gradlew lR
```

For additional commands please refer to the [Gradle Android Plugin User Guide][9].

##Version

1.0

##Dependencies

####Source dependencies

- Android SDK ([Terms and Condition][1])
- Android Support Library ([Apache License v2.0][2])

####Test dependencies

- [AssertJ][5] ([Apache License v2.0][2])

##License

[The Apache Software License, Version 2.0][2]

**It's open! Feel free to contribute!!**

###References

- [Stackoverflow answer][11] on how to generate a library Jar from an Android project with Gradle
- [Very useful blog][12] on how to automate the deployment of artifacts with Gradle
- [Sonatype User Guide][13] on how to deploy project artifacts to the Maven Central Repository
- [Another useful blog post][14] on how to upload Javadocs to a GitHub repository


[1]:http://developer.android.com/sdk/terms.html
[2]:http://www.apache.org/licenses/LICENSE-2.0
[3]:https://github.com/davide-maestroni/robo-fashion/blob/master/lib/src/main/java/com/bmd/android/collection/filter/Filters.java
[4]:http://davide-maestroni.github.io/robo-fashion/javadoc/
[5]:http://joel-costigliola.github.io/assertj/
[6]:https://code.google.com/p/android/issues/detail?id=68406
[7]:http://developer.android.com/reference/android/support/v4/util/SimpleArrayMap.html
[8]:http://docs.oracle.com/javase/8/docs/api/index.html?java/util/stream/package-summary.html
[9]:http://tools.android.com/tech-docs/new-build-system/user-guide#TOC-Android-tasks
[10]:http://search.maven.org/#artifactdetails%7Ccom.github.davide-maestroni%7Crobo-fashion%7C1.0%7Cjar
[11]:http://stackoverflow.com/a/19484146
[12]:http://jedicoder.blogspot.it/2011/11/automated-gradle-project-deployment-to.html
[13]:https://docs.sonatype.org/display/Repository/Sonatype+OSS+Maven+Repository+Usage+Guide
[14]:http://assylias.wordpress.com/2013/01/06/upload-javadoc-to-github/
