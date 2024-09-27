public class HelloWorld {
    public static void main(String[] args)
    {
        int number;

        if (args.length == 0)
            number = 1;
        else
            number = Integer.parseInt(args[0]);

        for (int i = 0; i < number; i++)
            System.out.println(i + " Hello World!");
    }
}
