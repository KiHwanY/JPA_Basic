package hellojpa;

public class ValueMain {


    public static void main(String[] args) {
//        Integer aa = new Integer(10);
//        Integer bb = aa;
//
//        aa.setValue(20);
//
//        int a = 10;
//        int b = a;
//
//        a = 20;
//
//            System.out.println("a = " + a);
//            System.out.println("b = " + b);
        int a = 10;
        int b = 10;

        System.out.println("a == b" + (a == b));

        Address address1 = new Address("city" , "street","100");
        Address address2 = new Address("city" , "street","100");

        System.out.println("address1 == address2 = " + (address1 == address2));
        System.out.println("address1 equals address2 = " + (address1.equals(address2)));




    }


}
