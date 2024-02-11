import java.util.Scanner;

public class Exercise24_03 {
    public static void main(String[] args) {
        new Exercise24_03();
    }

    public Exercise24_03() {
        TwoWayLinkedList<Double> list = new TwoWayLinkedList<>();
        System.out.print("Enter five numbers: ");
        Scanner input = new Scanner(System.in);
        double[] v = new double[5];
        for (int i = 0; i < 5; i++)
            v[i] = input.nextDouble();

        list.add(v[1]);
        list.add(v[2]);
        list.add(v[3]);
        list.add(v[4]);
        list.add(0, v[0]);
        list.add(2, 10.55);
        list.remove(3);

        java.util.ListIterator<Double> iterator1 = list.listIterator();
        System.out.print("The list in forward order: ");
        while (iterator1.hasNext())
            System.out.print(iterator1.next() + " ");

        java.util.ListIterator<Double> iterator2 = list.listIterator(list.size() - 1);
        System.out.print("\nThe list in backward order: ");
        while (iterator2.hasPrevious())
            System.out.print(iterator2.previous() + " ");

        // personal testing used values 1, 2, 3, 4, 5 for input
        System.out.println("\n\nHas 1.0: " + list.contains(1.0));
        System.out.println("Last index of 5.0: " + list.lastIndexOf(5.0));
        System.out.println("Set 2.5 at index 1:");
        list.set(1, 2.5);
        java.util.ListIterator<Double> iterator3 = list.listIterator();
        System.out.print("The list in forward order: ");
        while (iterator3.hasNext())
            System.out.print(iterator3.next() + " ");
        System.out.println("\nValue at index 2: " + list.get(2));
        System.out.println("Index of value 4.0: " + list.indexOf(4.0));

    }
}
