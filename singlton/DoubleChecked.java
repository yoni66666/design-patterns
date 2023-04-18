package il.co.ilrd.singlton;
/*
חסרונות:
שימוש במילה volatile לא זמין בגירסאות ישנות
קוד לא קריא
 */

public class DoubleChecked {
    private static volatile DoubleChecked instance = null;
    /*
    עושים volatile למקרה שתהליכון אחד באמצע יצירת מופע הראשון, ותהליכון אחר בדיוק באותו זמן גם מנסה ליצור
     */
    public static DoubleChecked returnInstance() {
        //בדיקה ראשונה למקרה שכבר נוצר מופע, אז בפעם השנייה כבר לא נרצה להיכנס, שכן פעולה של סנכרון ונעילה זאת פעולה כבדה שמעמיסה על המערכת...
        if (instance == null) {
            synchronized (DoubleChecked.class) {
                //בדיקה שנייה למקרה שנכנסים 2 תהליכונים בו זמנים בפעם הראשונה
                if (instance == null) {
                    instance = new DoubleChecked();
                    /*
                    אומרים לקומפיילר לא לשנות את הסדר של הפעולות (re-ordering)
                    הvolatile אומר לקומפיילר לא לעשות אופטימיזציה. ולא טוען דברים מראש לזכרון לפני שהכל מסתיים...
                     */
                }
            }
        }

            return instance;
        }
    private DoubleChecked() {}

    /*
    Notice that we passed a parameter this to the synchronized block. This is the monitor object. The code inside the
    block gets synchronized on the monitor object. Simply put, only one thread per monitor object can execute
     inside that block of code.
     */

    public static void main(String[] args) {

        DoubleChecked x = DoubleChecked.returnInstance();
        DoubleChecked y = DoubleChecked.returnInstance();
        DoubleChecked z = DoubleChecked.returnInstance();

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