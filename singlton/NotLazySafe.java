package il.co.ilrd.singlton;
/*
הקדמה - זה תבנית לטיפול בבעיה מסויימת
כאשר רוצים שיהיה אפשרות ליצור רק אובייקט אחד בלבד. וגם שמשתמש לא יכול ליצור אחד חדש
לדוגמא - עגלת קניות אחד בלבד באתר קניות ללקוח
 */


/*
Non-lazy initialization, thread-safe
This is the simplest thread-safe version, but it does not support lazy initialization.
 */

public class NotLazySafe {
    /*
   זה בטיחותי מכיוון שג'אווה מבטיח ששדות סטאטים מאותחלים בצורה בטיחותית של  thread-safe, ולכן
   אמנם ייתכן שיהיה 2 טרדים שעושים את המתודה returnInstance  אבל את הפעולה של אתחול שדה סטאטי - שם
   יש מאחורי הקלעים איזה סנכרון
     */
    private static final NotLazySafe instanceNotLazy = new NotLazySafe();
    /*  by private of ctor  */
    private NotLazySafe() {}
    //לא לייזי בגלל שנוצר מופע כבר בטעינה של המחלקה, ולא מחכה ליצירת אובייקט בהמשך
    //מתודה סטאטית לא מקבל this בצורה הדיפולטית ולכן יכולה לגשת רק לשדות סטאטים
    public static NotLazySafe returnInstance() {
        return instanceNotLazy;
    }
    /*
    הערה חשובה - זה לייזי מכיוון שטעינת מחלקה יכולה להיות אם היה פה עוד מתודות אחרות סטאיטיות, והיו יוצרים משתנה
    שישתמש באחד המתודות האלה. אז בשימוש הראשון הזה, ייטען המחלקה
     */

    public static void main(String[] args) {
        NotLazySafe z = NotLazySafe.instanceNotLazy;
        NotLazySafe x = NotLazySafe.returnInstance();
        NotLazySafe y = NotLazySafe.returnInstance();
      //  NotLazySafe z = NotLazySafe.returnInstance();

        System.out.println(x);
        System.out.println("Hashcode of x is " + x.hashCode());
        System.out.println("Hashcode of x is " + y.hashCode());
        System.out.println("Hashcode of x is " + z.hashCode());

        // Condition check
        if (x == y && y == z) {
            // Print statement
            System.out.println("Three objects point to the same memory location on the heap i.e, to the same object");
        } else {
            // Print statement
            System.out.println(
                    "Three objects DO NOT point to the same memory location on the heap");
        }
    }
}
