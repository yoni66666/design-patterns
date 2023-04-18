package il.co.ilrd.singlton;

/*
1. זה לייזי מכיוון שאין יצירת מופע עד הקריאה למתודה
2. זה כן בטיחותי מכיוון שטעינה של מחלקה סטאטית זה בטיחותי (בג'אווה מאחורי הקלעים)
 */

public class LazySafe {
    private LazySafe(){}

    public static LazySafe getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final LazySafe INSTANCE = new LazySafe();
    }



    public static void main(String[] args) {

        LazySafe x = LazySafe.getInstance();
        LazySafe y = LazySafe.getInstance();
        LazySafe z = LazySafe.getInstance();

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
