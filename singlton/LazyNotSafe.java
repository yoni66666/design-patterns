package il.co.ilrd.singlton;
/*
This is the classical version, but it's not thread-safe. If more than one thread attempts to access instance at the
same time, more than one instance may be created.
 */
public class LazyNotSafe {
    private static LazyNotSafe instance = null;
    /* it is static because we dont want to creat instance */
    private LazyNotSafe() {}

    public static LazyNotSafe returnInstance() {


        if (instance == null) {
            instance = new LazyNotSafe();
        }
        return instance;
    }

    public static void main(String[] args) {
        LazyNotSafe x = LazyNotSafe.returnInstance();
        LazyNotSafe y = LazyNotSafe.returnInstance();
        LazyNotSafe z = LazyNotSafe.returnInstance();

        System.out.println(x);
        System.out.println("Hashcode of x is " + x.hashCode());
        System.out.println("Hashcode of x is " + y.hashCode());
        System.out.println("Hashcode of x is " + z.hashCode());

        // Condition check
        if (x == y && y == z) {
            // Print statement
            System.out.println("Three objects point to the same memory location on the heap i.e, to the same object");
        }
        else {
            // Print statement
            System.out.println(
                    "Three objects DO NOT point to the same memory location on the heap");
        }
    }
}

