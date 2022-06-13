package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.*;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;

public class OrderProcessor {
    public String startPath;
    List<Order> orderList = new ArrayList<>();

    public OrderProcessor(String startPath) {
        this.startPath = startPath;
    }

    public List<Path> createListPath(LocalDate start, LocalDate finish, String inFolder) throws IOException {
        // создаем список всех файлов с нужным форматом и названием
        List<Path> resultList = new ArrayList<>();
        Path dir = Paths.get(inFolder);
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/???[-]??????[-]????.csv");
        Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                // проверяем соответствие даты здесь (а может и не надо, пока убрал)
//                LocalDateTime localDateTime = attrs.lastModifiedTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
//                if (start == null || LocalDate.from(localDateTime).compareTo(start) > -1)
//                    if (finish == null || LocalDate.from(localDateTime).compareTo(finish) < 1)
                        if (pathMatcher.matches(path))
                            resultList.add(path);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException e) {
                return FileVisitResult.CONTINUE;
            }
        });
        return resultList;
    }

    public int loadOrders(LocalDate start, LocalDate finish, String shopId) {
        int errorsCount=0;
        try {
            List<Path> pathList = createListPath(start, finish, startPath);
            for (Path el : pathList) {
                Order order = new Order();
                String fileName = String.valueOf(el.getFileName());
                order.setShopId(fileName.substring(0, 3));
                order.setOrderId(fileName.substring(4, 10));
                order.setCustomerId(fileName.substring(11, 15));
                order.setDatetime(Files.getLastModifiedTime(el).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
                order.setItems(itemsList(el));
                if (order.items.size() == 0){
                    errorsCount++;
                    continue;
                }
                double sumOrder = 0;
                for (OrderItem item : order.items) {
                    sumOrder += item.count * item.price;
                }
                order.setSum(sumOrder);
                // учитываем даты
                if (order.items.size() != 0) // если в файле не пойми что или ничего, то не добавляем этот файл
                    if (shopId == null || order.shopId.equals(shopId))
                        if (start == null || LocalDate.from(order.datetime).compareTo(start) > -1)
                            if (finish == null || LocalDate.from(order.datetime).compareTo(finish) < 1)
                                orderList.add(order);
            }
            return errorsCount;
        } catch (IOException e) {
            //orderList.clear();
            return errorsCount;
        }

    }

    public List<OrderItem> itemsList(Path path) {
        List<OrderItem> result = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(path);
            result = lineReader(lines);
        } catch (IOException e) {
               System.out.println(e.getMessage());
        }
        return result;
    }

    static List<OrderItem> lineReader(List<String> lines) {
        List<OrderItem> result = new ArrayList<>();
        for (String line : lines) {
            String[] lineList = line.split(",|;");
            if (lineList.length != 3) {
                result.clear();
                return result;
            }
            OrderItem orderItem = new OrderItem();
            orderItem.setGoogsName(lineList[0]);
            try {
                orderItem.setCount(Integer.parseInt(lineList[1]));
                orderItem.setPrice(Double.parseDouble(lineList[2]));
            } catch (NumberFormatException e) {
                result.clear();
                return result;
            }
            result.add(orderItem);
            result.sort(new Comparator<OrderItem>() {
                @Override
                public int compare(OrderItem o1, OrderItem o2) {
                    return o1.googsName.compareTo(o2.googsName);
                }
            });
        }
        return result;
    }

    public List<Order> process(String shopId) {
        List<Order> orders = new ArrayList<>();
        for (Order order : orderList) {
            if (order.shopId.equals(shopId) || shopId == null) orders.add(order);
        }
        orders.sort(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return o1.datetime.compareTo(o2.datetime);
            }
        });
        return orders;
    }

    public Map<String, Double> statisticsByShop() {
        Map<String, Double> statisticsByShopMap = new TreeMap<>();
        for (Order order : orderList) {
            statisticsByShopMap.putIfAbsent(order.shopId, (double) 0);
            statisticsByShopMap.put(order.shopId, statisticsByShopMap.get(order.shopId) + order.sum);
        }
        return statisticsByShopMap;
    }

    public Map<String, Double> statisticsByGoods() {
        Map<String, Double> statisticsByGoodsMap = new TreeMap<>();
        for (Order order : orderList) {
            for (OrderItem orderItem : order.items) {
                statisticsByGoodsMap.putIfAbsent(orderItem.googsName, (double) 0);
                statisticsByGoodsMap.put(orderItem.googsName, statisticsByGoodsMap.get(orderItem.googsName) + orderItem.count * orderItem.price);
            }
        }
        return statisticsByGoodsMap;
    }

    public Map<LocalDate, Double> statisticsByDay() {
        Map<LocalDate, Double> statisticsByDayMap = new TreeMap<>();
        for (Order order : orderList) {
            LocalDate date = LocalDate.from(order.datetime);
            statisticsByDayMap.putIfAbsent(date, (double) 0);
            statisticsByDayMap.put(date, statisticsByDayMap.get(date) + order.sum);
        }
        return statisticsByDayMap;
    }

    public static void main(String[] args) {
        String name = "D:\\Test2";
        OrderProcessor orderProcessor = new OrderProcessor(name);
        LocalDate start = LocalDate.parse("2022-05-31");
        LocalDate finish = LocalDate.parse("2022-05-31");
        //int test = orderProcessor.loadOrders(LocalDate.of(2020, Month.JANUARY, 1), LocalDate.of(2020, Month.JANUARY, 10), null);
        //int test = orderProcessor.loadOrders(null, null, null);
        int test = orderProcessor.loadOrders(null, LocalDate.of(2020, Month.JANUARY, 16), "S01");
        System.out.println(test);
//        for (Order el : orderProcessor.process(null))
//            //for (OrderItem orderItem: el.items)
//            System.out.println(el.toString());
//        List<Order> tstList = orderProcessor.process("qqq");
//        for (Order el : tstList)
//            for (OrderItem orderItem : el.items)
//                System.out.println(orderItem.toString());
//        for (Map.Entry<String, Double> goods: orderProcessor.statisticsByGoods().entrySet()){
//            System.out.println(goods);
//        }
        System.out.println(orderProcessor.statisticsByDay());
    }
}
