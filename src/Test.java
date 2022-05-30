import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Test {

    public static Integer sqr(Integer n) {
        try {
            return n * n;
        } catch (NullPointerException e) {
            return -1;
        }
    }

    public static String test(String filename) {
        try {
            if (filename == null)
                throw new IOException();
            return "File processing";
        } catch (IOException e) {
            System.out.print(e);
            return " File not found";
        }
    }

    private static int lineCount(String filename) throws IOException {
        try {
            int res = 0;
            FileReader fileReader = new FileReader(filename);
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                res++;
            }
            fileReader.close();
            return res;
        } catch (IOException e) {
            throw new IOException("файл не найден");
        }
    }

    public void doSomething(int n) throws IOException {

    }

    public void test(int n) throws IOException {
        try {
            doSomething(n);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new IOException();
        } finally {
            System.out.println("finally executed");
        }
    }

    public static void scanLines() {
        try (Scanner scanner = new Scanner(System.in)) {
            String tmpStr = "";
            while (!(tmpStr = scanner.nextLine()).contains("/stop")) {
                if (tmpStr.contains("Привет")) {
                    System.out.println("Здравствуйте!");
                } else if (tmpStr.contains("как дела")) {
                    System.out.println("Хорошо");
                } else {
                    System.out.println(tmpStr);
                }
            }
        }
    }

    public static String invertWords(String sentence) {
        String[] tmpStr = sentence.split(" ");
        StringBuilder result = new StringBuilder();
        for (int i = tmpStr.length - 1; i > 0; i--) {
            result.append(tmpStr[i]);
            result.append(".");
        }
        result.append(tmpStr[0]);
        return result.toString();
    }

    public static String setStars(String filename) {
        StringBuilder result = new StringBuilder();
        try (RandomAccessFile raf = new RandomAccessFile(filename, "rw")) {
            int pos = 9;
            while (pos < raf.length()) {
                raf.seek(pos);
                char byteChar = (char) raf.read();
                result.append(byteChar);
                raf.seek(pos);
                raf.write('*');
                pos += 10;
            }
        } catch (IOException e) {

        }
        return result.toString();
    }

    public static List<Integer> listAction(List<Integer> list) {
        list.remove(Collections.min(list));
        list.add(0, list.size());
        list.add(2, Collections.max(list));
        return list;
    }

    public static List<Integer> filter(List<Integer> list) {
        int res = 0;
        for (int el : list) {
            res += el;
        }
        int i = 0;
        while (i < list.size()) {
            if (list.get(i) > (res / 100)) {
                list.remove(i);
                i--;
            }
            i++;
        }
        return list;
    }

    public static void iterator3(ListIterator<Integer> iterator) {
        while (iterator.hasNext()) {
            Integer n = (Integer) iterator.next();
            if (n % 3 == 0) {
                iterator.set(iterator.nextIndex() - 1);
            }
        }
    }

    public static void iSetTest() {
        Set<Integer> iSet = new HashSet<>();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                iSet.add(i + j);
            }
        System.out.println(iSet.size());
    }

    public static void wSetTest() {
        String TEXT = "на дворе трава на траве дрова не руби дрова на траве двора";
        Set<String> wordSet = new HashSet<>(Arrays.asList(TEXT.split(" ")));

        Iterator<String> iter = wordSet.iterator();
        while (iter.hasNext())
            if (iter.next().contains("ра"))
                iter.remove();

        System.out.println(wordSet.size());
    }

    public static void cset() {
        Set<Integer> fiboSet1000 =
                Set.of(0, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987);
        int sum = 0;
        for (int i = 2; i < 10; i++)
            sum += fiboSet1000.contains(i) ? 1 : 0;
        System.out.println(sum);
    }

    public static Set<Integer> a2set(int[] a) {
        Collection<Integer> qqq = new ArrayList(List.of(a));
        Set<Integer> result = new HashSet<>();
        for (int el : a)
            result.add(el);

        return result;
    }

    static class Figure {

    }

    static class Square extends Figure {
        private double side;

        public Square(double side) {
            this.side = side;
        }

        public double getSide() {
            return side;
        }
    }

    static class Round extends Figure {
        private double diameter;

        @Override
        public String toString() {
            return "Round{" +
                    "diameter=" + diameter +
                    '}';
        }

        public Round(double diameter) {
            this.diameter = diameter;
        }

        public double getDiameter() {
            return diameter;
        }
    }

    static String figDetect(Figure fig) {
        Square sq = new Square(0);
        Round round = new Round(0);
        if (fig == null) return "Неизвестная фигура";
        if (fig.getClass() == sq.getClass()) {
            return "Сторона квадрата " + ((Square) fig).side;
        } else if (fig.getClass() == round.getClass()) {
            return "Диаметр круга " + ((Round) fig).diameter;
        } else {
            return "Неизвестная фигура";
        }
    }

    public static void doTreeSet() {
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(19);
        treeSet.add(12);
        treeSet.add(15);
        treeSet.add(10);

        String s = "";
        Iterator<Integer> iterator = treeSet.iterator();
        while (iterator.hasNext())
            s += iterator.next();
        System.out.println(s);
    }

    static class User {
        public Integer id;
        public String name;

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        User(Integer id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static TreeSet<User> createSet() {
        TreeSet<User> users = new TreeSet<>(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return -Integer.compare(o1.id, o2.id);
            }
        });
        users.add(new User(3, "wefre"));
        users.add(new User(5, "tyter"));
        users.add(new User(1, "lkyuop"));
        users.add(new User(8, "lkoasap"));
        users.add(new User(2, "qew"));
        return users;
    }

    static void dequeueTest() {
        ArrayDeque deque = new ArrayDeque<>();

        for (int i = 0; i <= 10; i++) {
            deque.offer(i);
            if (i % 2 == 0)
                deque.poll();
        }

        System.out.println(deque);
    }

    static void pqTest() {
        PriorityQueue pQueue = new PriorityQueue<>();
        pQueue.offer(10);
        pQueue.offer(1);
        pQueue.offer(9);
        pQueue.offer(3);
        System.out.println(pQueue);
    }

    static ArrayDeque<Integer> array2queue(int[] a) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int el : a) {
            deque.offer(el);
        }
        return deque;
    }

    static int sumLastAndFirst(ArrayDeque<Integer> deque) {
        if (deque.peekFirst() != null) {
            return deque.peekFirst() + deque.peekLast();
        } else {
            return 0;
        }
    }

    static HashMap<Integer, String> a2map(int[] akey, String[] aval) {
        HashMap<Integer, String> result = new HashMap<>();
        for (int i = 0; i < akey.length; i++) result.put(akey[i], aval[i]);
        return result;
    }

    static int fillHoles(Map<Integer, String> map, int size) {
        int result = 0;
        for (int i = 1; i <= size; i++) {
            if (map.putIfAbsent(i, "Число " + i) == null) result++;
        }
        return result;
    }

    static void checkAndAdd(TreeMap<Integer, String> map, Integer key, String value) {
        if (!map.isEmpty() && key > map.firstKey() && key < map.lastKey() && !map.containsKey(key))
            map.put(key, value);
    }

    static Instant createInstant() {
        //1 января 2020 года, 15 часов и одна наносекунда по Московскому времени
        ZonedDateTime zonedDateTime = ZonedDateTime.of(2020, 01, 01, 15, 0, 0, 1, ZoneId.of("Europe/Moscow"));
        Instant instant = zonedDateTime.toInstant();
        return instant;
    }

    static ZonedDateTime parseZDT(String str) {
        // "01.01.2020 16:27:14.444 +0300 Moscow Standard Time"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss.SSS Z zzzz", Locale.forLanguageTag("En/en"));
        ZonedDateTime zdt = ZonedDateTime.parse(str, formatter);
        return zdt;
    }

    static String createFolder(String name) {
        File f = new File(name);
        f.mkdir();
        Path path1 = Paths.get(name);
        return path1.toAbsolutePath().getParent().getParent().toString();
    }

    static boolean replaceF(String name) {
        try {
            Path path = Paths.get(name);
            String allSymb = Files.readString(path);
            StringBuilder newAllSymb = new StringBuilder();
            for (int i = 0; i < allSymb.length(); i++) {
                if (allSymb.charAt(i) == 'F') {
                    newAllSymb.append('f');
                } else
                    newAllSymb.append(allSymb.charAt(i));
            }
            Files.writeString(Paths.get(name), newAllSymb.toString());
            return true;
        } catch (Exception e ){
            return false;
        }
    }

    public static void main(String[] args) {

        boolean t = replaceF("1.txt");
    }
}


