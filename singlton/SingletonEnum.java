package il.co.ilrd.singlton;


public enum SingletonEnum {
    INSTANCE;

    public static void main(String[] args) {
        SingletonEnum singletonEnum = SingletonEnum.INSTANCE;
    }
}


/*
probloem whith other:

Problems With Reflection

An advanced user can change the private access modifier of the constructor to anything they want at runtime using
reflection. If this happens, our only mechanism for non-instantiability breaks. Let's see how this can be done.
 for example - the non-accessible private constructor becomes accessible and the whole idea of making the class a singleton breaks.


 Problems With Serialization and Deserialization

In order to serialize the above singleton classes, we must implement those classes with a Serializable interface.
But doing that is not enough. When deserializing a class, new instances are created.
 Now it doesn't matter the constructor is private or not.
Now there can be more than one instance of the same singleton class inside the JVM, violating the singleton property.

 */

/*
Pros:
    It is simple to write Enum Singletons
    Enums are inherently serializable
    No problems of reflection occur
    It is thread-safe to create enum instances

    By default, the Enum instance is thread-safe, and you don’t need to worry about double-checked locking.

Cons:

    By default, enums do not support lazy loading:
    class will be initialized when INSTANCE is first accessed

    Enums won’t allow changing a singleton object to multiton
    כלומר לא ניתן ליצר הרבה מופעים, ולעשות ששינוי באחד מהם יתבצע גם במקומות אחרים
 */