import java.util.Scanner;




    public class TriangleArea {
        public static void main(String[] args) {
            Scanner scnr = new Scanner(System.in);

            Triangle triangle1 = new Triangle();
            Triangle triangle2 = new Triangle();

            Triangle larger;

            triangle1.setBase(scnr.nextDouble());
            triangle1.setHeight(scnr.nextDouble());
            triangle2.setBase(scnr.nextDouble());
            triangle2.setHeight(scnr.nextDouble());

            // TODO: Read and set base and height for triangle1 (use setBase() and setHeight())

            // TODO: Read and set base and height for triangle2 (use setBase() and setHeight())
            if(triangle1.getArea() > triangle2.getArea()){
                larger = triangle1;
            } else{
                larger = triangle2;
            }
            // TODO: Determine larger triangle (use getArea())

            System.out.println("Triangle with larger area:");
            // TODO: Output larger triangle's info (use printInfo())
            larger.printInfo();

        }
    }

