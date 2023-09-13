# TypedBundle

[![CircleCI](https://dl.circleci.com/status-badge/img/gh/Jintin/TypedBundle/tree/master.svg?style=shield)](https://dl.circleci.com/status-badge/redirect/gh/Jintin/TypedBundle/tree/master) [![Maven Central](https://img.shields.io/maven-central/v/io.github.jintin/typed-bundle)](https://mvnrepository.com/artifact/io.github.jintin/typed-bundle)

A type safe `Bundle` for Android development.

`Bundle` is essential component in Android development from Day 1, but the flexible key-value structure don't limiting us from put a `String` and get an `Int` back with same key (and which will cause crash not compile error). This library is aimed to transform the plain String key into a type based class (such as `StringKey` or `IntKey` in below example) to align the type usage on both get and set side.

## Install

```kotlin
dependencies {
    implementation("io.github.jintin:typed-bundle:0.3.0")
}
```

## Usage

```kotlin
val textKey = StringKey("SomeStringKey")
val intKey = IntKey("SomeIntKey")
val bundle = Bundle()

// Bundle usage
bundle.put(textKey, "Some text")   // only String value is allowed
bundle.put(intKey, 12345)          // only Int value is allowed

val text = bundle.get(textKey, "") // guarantee result to be a String  
val int = bundle.get(intKey, -1)   // guarantee result to be an Int

// Intent usage
intent.put(textKey, "Some text")   // only String value is allowed
intent.put(intKey, 12345)          // only Int value is allowed

val text = intent.get(textKey)     // guarantee result to be a String?  
val int = intent.get(intKey, -1)   // guarantee result to be an Int
```

You can go to ./app module for more information.

## License

**TypedBundle** is released under Apache License 2.0.
See [LICENSE](https://github.com/Jintin/TypedBundle/blob/master/LICENSE) for more details.

