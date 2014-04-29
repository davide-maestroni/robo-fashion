#robo-fashion

This library employes builder and fluent design patterns to enable easy iteration and manipulation of Android sparse collections.

##Overview

The Android platform introduced a few collection classes based on the sparse array implementation. Although they bring improved performances and optimized memory usage to the classic Java collections world, they sometimes become awkward to use when implementing the most basic pieces of code. For example, good old iteration is not supported, so that, while all Java developers are by now used to the short and elegant syntax:

```java
for (final Entry<Integer,MyClass> entry : map.entrySet()) {

  doSomethingWithKey(entry.getKey());
  doSomethingWithValue(entry.getValue());
}
```

when employing Android sparse collections, you quickly end up in rewriting one time to often the same ugly code:

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

Or through a more fluent syntax, like:

```java
AndroidCollections.iterate(array)
                  .only()
                  .from(4)
                  .forEach((element, index) -> {
                    // ... do your stuff ...
                  });
```

Finally, even by mixing the two:

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

The iterable implementation is not thread safe (unless differently specified) and not fail-fast, that is, if the wrapped collection is changed during the iteration no exception will be thrown, and further call to the iterator or to the entries methods might lead to unexpected results.

The entries returned by the iterator can be safely accessed only inside the iteration loop, since they have direct access to the wrapped sparse collection. This is an explicit design choice to try preserving both highest performances and lowest memory consumption.
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

The [Filters][2] class already provides several filter implementations (like last *N* elements or first *N* elements), but additional custom filters can be easily implemented and concatenated with the existing ones.


##Version

1.0

##License

[The Apache Software License, Version 2.0][1]

**It's open! Feel free to contribute!!**

[1]:http://www.apache.org/licenses/LICENSE-2.0
[2]:https://github.com/davide-maestroni/robo-fashion/blob/master/lib/src/main/java/com/bmd/android/collection/filter/Filters.java
