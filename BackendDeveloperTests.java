// --== CS400 Project One File Header ==--
// Name: Ark Dutt
// CSL Username: ark
// Email: dutt3@wisc.edu
// Lecture #: 004 15:30-16:20
// Notes to Grader: <any optional extra notes to your grader>

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BackendDeveloperTests {


    /**
     * test1 method checks if the loadData can load the data from the data folder
     * Backend Tester Method
     * @return true if the data exists otherwise false
     */
    public static boolean test1() {
            try {
                HashtableWithDuplicateKeysBD<String, PostInterface> table = new HashtableWithDuplicateKeysBD<>();
                PostReaderDW post = new PostReaderDW();
                CHSearchBackendBD bd = new CHSearchBackendBD(table, post);
                bd.loadData("data/small.txt");
            } catch (FileNotFoundException e) {
                System.out.println("ERROR! No file found!");
                return false;
            }
            return true;
    }


    /**
     * test2 method checks if the post exists by the bodywords given by the user.
     * Backend Tester method
     *
     * @return true if the title of the post is returned, otherwise false
     */
    public static boolean test2() {
        try {
            HashtableWithDuplicateKeysAE<String, PostInterface> table = new HashtableWithDuplicateKeysAE<>();
            PostReaderBD post = new PostReaderBD();
            CHSearchBackendBD bd = new CHSearchBackendBD(table, post);
            bd.loadData("data/small.txt");
            List<String> titless = bd.findPostsByBodyWords("forget");
            List<String> arr = new ArrayList<>();
            arr.add("takeout!");
            arr.add("orange");

            for(int i=0; i<titless.size(); i++) {
                if (!arr.get(i).equals(titless.get(i))) {
                    return true;
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("ERROR! No file found!");
            return false;
        }
        return false;
    }

    //testing the findPostsByBodyWords()

    /**
     * test3 method checks if the post exist by the title words given by the user.
     * @return true if the post exists, other false
     */
    public static boolean test3() {
        try {
            HashtableWithDuplicateKeysAE<String, PostInterface> table = new HashtableWithDuplicateKeysAE<>();
            PostReaderBD post = new PostReaderBD();
            CHSearchBackendBD backend = new CHSearchBackendBD(table, post);
            backend.loadData("data/small.txt");
            List<String> titless = backend.findPostsByTitleWords("ORANGE CHICKEN");
            List<String> arr = new ArrayList<>();
            arr.add("Forget takeout!");
            arr.add("Orange");
            arr.add("Money");

            for(int i=0; i<titless.size(); i++) {
                if(!arr.get(i).equals(titless.get(i))) {
                    return true;
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("ERROR! No file found!");
            return false;
        }
        return false;
    }

    /**
     * test4 method checks if the post exist by the title words given by the user.
     * @return
     */
    public static boolean test4() {
        try {
            HashtableWithDuplicateKeysAE<String, PostInterface> table = new HashtableWithDuplicateKeysAE<>();
            PostReaderBD post = new PostReaderBD();
            CHSearchBackendBD backend = new CHSearchBackendBD(table, post);
            backend.loadData("filename.txt");
            List<String> titless = backend.findPostsByTitleWords("Protein overload");
            List<String> arr = new ArrayList<>();
            arr.add("Nice!");
            arr.add("Protein");
            arr.add("Takeout");

            for(int i=0; i<titless.size(); i++) {
                if(!arr.get(i).equals(titless.get(i))) {
                    return true;
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("ERROR! No file found!");
            return false;
        }
        return false;
    }

    //Backend: testing findPostsByTitleorBodyWords()

    /**
     * test5 method checks if the post exist by the title words and body words given by the user.
     * @return
     */
    public static boolean test5() {
        try {
            HashtableWithDuplicateKeysAE<String, PostInterface> table = new HashtableWithDuplicateKeysAE<>();
            PostReaderBD post = new PostReaderBD();
            CHSearchBackendBD backend = new CHSearchBackendBD(table, post);
            backend.loadData("filename.txt");
            List<String> titless = backend.findPostsByTitleWords("Orange is the new black!");
            List<String> arr = new ArrayList<>();
            arr.add("Forget takeout!");
            arr.add("Orange");
            arr.add("Money");
            arr.add("Hi");
            for(int i=0; i<titless.size(); i++) {
                if(!arr.get(i).equals(titless.get(i))) {
                    return true;
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("ERROR! No file found!");
            return false;
        }
        return false;
    }

    /**
     * Test6 method is an integration method which checks whether the data is being loaded from the data sets
     * @return true if it's being loaded, otherwise false
     */
    public static boolean test6() {
        PostReaderDW post = new PostReaderDW();
        HashtableWithDuplicateKeysInterface<String, PostInterface> hash = new HashtableWithDuplicateKeysAE<>();
        if(hash.getNumberOfValues() != 0) {
            return false;
        }
        CHSearchBackendInterface bd = new CHSearchBackendBD(hash, post);
        Scanner scnr = new Scanner(System.in);

        try {
            bd.loadData("data/small.txt");
        } catch(Exception e) {
            System.out.println(e);
            return false;
        }

        try {
            bd.loadData("data/fake.txt");
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }


    /**
     * Test6 method is another integration method
     * @return true if it's being loaded, otherwise false
     */
    public static boolean test7() {
        PostReaderInterface post = new PostReaderDW();
        HashtableWithDuplicateKeysInterface<String, PostInterface> hash = new HashtableWithDuplicateKeysAE<>();
        if(hash.getNumberOfValues() != 0) {
            return false;
        }
        CHSearchBackendInterface bd = new CHSearchBackendBD(hash, post);
        Scanner scnr = new Scanner(System.in);
        CHSearchFrontendFD fd = new CHSearchFrontendFD(scnr, bd);
        try {
            bd.loadData("file.txt");
            System.out.println();
        } catch(Exception e) {
            System.out.println(e);
        }
        return false;
    }

    /**
     * Frontend Tester: tests the searchTitleCommand method
     *
     * @return true if the method is functional otherwise false
     */
    public static boolean test8() {
        TextUITester test8 = new TextUITester("first" + "\n" + "move");
        CHSearchFrontendFD fd = new CHSearchFrontendFD(new Scanner(System.in),
                new CHSearchBackendBD(null,null));
        List<String> listA = fd.chooseSearchWordsPrompt();
        fd.searchTitleCommand(listA);

        String req = test8.checkOutput();

        if(req.contains("Loading")) {
            return false;
        }
        return true;
    }

    /**
     * test9 is a frontend tester which tests the run command loop
     * @return True if it is function, otherwise false
     */
    public static boolean test9() {
        /* test code here */
        TextUITester test9 = new TextUITester("Delicious\n");
        CHSearchFrontendFD fd = new CHSearchFrontendFD(new Scanner(System.in),
                new CHSearchBackendBD(null, null));
        fd.runCommandLoop();
        String output = test9.checkOutput();
        System.out.println(output);
        if (output.contains("Goodbye!")) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(test1());
        System.out.println(test2());
        System.out.println(test3());
        System.out.println(test4());
        System.out.println(test5());
        System.out.println(test6());
        System.out.println(test7());
        System.out.println(test8());
        System.out.println(test9());
    }
}
