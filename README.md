# TypedBundle
Type safe Bundle for Android development

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

