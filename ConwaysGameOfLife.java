import java.util.Scanner;
import java.io.*;
/**
 * @author Pratha Bodas
 * @version 2/2/17
 */
public class ConwaysGameOfLife {
    private int length;
    private int height;
    private int [][] genone;
    private int [][] gentwo;
    
    public ConwaysGameOfLife(String fileName) {
        File f = new File(fileName);
        Scanner s = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);
        try {
            s = new Scanner(f);
            scan = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        height = s.nextInt();
        length = s.nextInt();
        genone = new int [height][length];
        gentwo = new int [height][length];
        scan.nextInt();
        scan.nextInt();
        while (scan.hasNext()) {
            int row = scan.nextInt();
            int col = scan.nextInt();
            genone[row][col] = 1;
        }
    }

    public void runLife(int generations) {
        for (int i = 0 ; i < generations; i++) {
            nextGeneration();
        }
    }

    public int rowCount(int row) {
        int cells = 0;
        if (row < 0 || row > height)
            return -1;
        else {
           for (int i = 0; i < length; i++) {
               if (genone[row][i] == 1)
                    cells++;
           }
        }
        return cells;
    }

    public int colCount(int col) {
        int cells = 0;
        if (col < 0 || col > length)
            return -1;
        else {
           for (int i = 0; i < height; i++) {
               if (genone[i][col] == 1)
                    cells++;
           }
        }
        return cells;
    }

    public int totalCount() {
        int count = 0;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < length; col++) {
                if (genone[row][col] == 1) 
                    count++;
            }   
        }
        return count;
    }

    public void printBoard() {
        int i = 0;
        System.out.print("    ");
        for (int col = 0; col < length; col++) {
            System.out.print(i);
            i++;
            if (i > 9) 
                i = 0;
        }
        System.out.println();
        i = 0;
        for (int row = 0; row < height; row++) {
            System.out.print(i + "   "); 
            i++;
            for (int col = 0; col < length; col++) {
                if (genone[row][col] == 1)
                    System.out.print("* ");
                else 
                    System.out.print(" ");
            }
            System.out.println();
        }
        
        System.out.println();
        
        System.out.println("Number of Living Cells in Row 9 ---> " + rowCount(9));
        System.out.println("Number of Living Cells in Column 9 ---> " + colCount(9));
        System.out.println("Number of Living Cells Total ---> " + totalCount());
    }

    public void nextGeneration() {
        int row = 0;
        int col = 0;
        for (int i = 0; i < length * height; i++) {
            int neighbors = 0;
            if (row - 1 > -1) {
                if (col - 1 > - 1 && genone[row - 1][col - 1] == 1)
                    neighbors++;
                if (col + 1 < length && genone[row - 1][col + 1] == 1)
                    neighbors++;
                if (genone[row - 1][col] == 1) 
                    neighbors++;
            }

            if (row + 1 < height) {
                if (col - 1 > - 1 && genone[row + 1][col - 1] == 1)
                    neighbors++;
                if (col + 1 < length && genone[row + 1][col + 1] == 1)
                    neighbors++;
                if (genone[row + 1][col] == 1) 
                    neighbors++;
            }

            if (col - 1 > -1 && genone[row][col - 1] == 1)
                neighbors++;
            if (col + 1 < length && genone[row][col + 1] == 1)
                neighbors++;

            if (genone[row][col] == 1 && (neighbors == 2 || neighbors == 3))
                gentwo[row][col] = 1;
            else if ((genone[row][col] == 1 && neighbors < 2) || (genone[row][col] == 1 && neighbors > 3))
                gentwo[row][col] = 0;
            else if (genone[row][col] != 1 && neighbors == 3)
                gentwo[row][col] = 1;

            col++;

            if (col == length) {
                col = 0;
                if (row != height - 1)
                    row++;
            }
        }


        for (row = 0; row < height; row++) {
            for (col = 0; col < length; col++) {
                 genone[row][col] = gentwo[row][col];
                 gentwo[row][col] = 0;
            }
        }
    }
        
    public static void main (String [] args) {
        ConwaysGameOfLife f = new ConwaysGameOfLife("life100.txt");
        f.runLife(5);
        f.printBoard();
    }
}
