robo-fashion
============

Overview
--------

The Android platform introduced a few collection classes based on the sparse array implementation. Although they bring improved performances and optimized memory usage to the classic Java collections world, they sometimes become awkward to use while implementing the most basic pieces of code. For example, good old iteration is not supported, so that, while all Java developers are by now used to the short and elegant syntax:

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

Usage
-----

TBD


Version
-------

1.0

License
-------

[The Apache Software License, Version 2.0] [1]

**It's open! Feel free to contribute!!**

[1]:http://www.apache.org/licenses/LICENSE-2.0
