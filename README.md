# TypedBundle
A type safe `Bundle` for Android development.

`Bundle` is essential component in Android development from Day 1, but the flexible key-value structure don't limiting us from put a `String` and get an `Int` back with same key (and which will cause crash not compile error). This library is aimed to use `TypedKey` and its child class (such as `StringKey` or `IntKey` in below example) to align the type on both get and set usage.

## Install

```kotlin
dependencies {
    implementation("io.github.jintin:typed-bundle:0.1.0")
}
```

## Usage

```kotlin
val textKey = StringKey("SomeStringKey")
val intKey = IntKey("SomeIntKey")
val bundle = Bundle()

bundle.put(textKey, "Some text")   // only String value is acceptable
bundle.put(intKey, 12345)          // only Int value is acceptable

val text = bundle.get(textKey, "") // guarantee result to be a String  
val int = bundle.get(intKey, -1)   // guarantee result to be an Int
```

You can go to ./app module for more information.

## License

**TypedBundle** is released under Apache License 2.0.
See [LICENSE](https://github.com/Jintin/TypedBundle/blob/master/LICENSE) for more details.

