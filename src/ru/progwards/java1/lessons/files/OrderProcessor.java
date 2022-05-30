package ru.progwards.java1.lessons.files;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.*;

public class OrderProcessor {
    static int errorsCount;
    public String startPath;
    List<Order> orderList = new ArrayList<>();

    public OrderProcessor(String startPath) {
        this.startPath = startPath;
    }

    public static List<Path> createFilesList(String inFolder) throws IOException {
        // создаем список всех файлов с нужным форматом и названием
        errorsCount = 0;
        List<Path> resultList = new ArrayList<>();
        Path dir = Paths.get(inFolder);
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**/???[-]??????[-]????.csv");
        Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
                if (pathMatcher.matches(path))
                    resultList.add(path);
                else
                    errorsCount++;
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException e) {
                errorsCount++;
                return FileVisitResult.CONTINUE;
            }
        });
        return resultList;
    }

    public int loadOrders(LocalDate start, LocalDate finish, String shopId) throws IOException {
        int result = 0;
        List<Path> pathList = createFilesList(startPath);
        if (errorsCount != 0) {
            orderList.clear();
            return errorsCount;
        }
        for (Path el : pathList) {
            Order order = new Order();
            String fileName = String.valueOf(el.getFileName());
            order.setShopId(fileName.substring(0, 3));
            order.setOrderId(fileName.substring(4, 10));
            order.setCustomerId(fileName.substring(11, 15));
            String tmp = Files.getAttribute(el, "lastModifiedTime").toString().substring(0, 27);

            order.setDatetime(LocalDateTime.from(DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(tmp)));
            order.setItems(itemsList(el));
            double sumOrder = 0;
            for (OrderItem item : order.items) {
                sumOrder += item.count * item.price;
            }
            order.setSum(sumOrder);
            // учитываем даты
            if (shopId == null || order.shopId.equals(shopId))
                if (start == null || LocalDate.from(order.datetime).compareTo(start) == 1)
                    if (finish == null || LocalDate.from(order.datetime).compareTo(finish) == -1)
                        orderList.add(order);

            // добавим все заказы
            //orderList.add(order);
        }
        return 0;
    }

    public List<OrderItem> itemsList(Path path) throws IOException {
        List<OrderItem> result = new ArrayList<>();
        List<String> lines = Files.readAllLines(path);
        for (String line : lines) {
            String[] lineList = line.split(",|;");
            //  по уму надо бы проверить на "правильность" строк в файле через исключения
            OrderItem orderItem = new OrderItem(lineList[0], Integer.parseInt(lineList[1]), Double.parseDouble(lineList[2]));
            result.add(orderItem);
            result.sort(new Comparator<OrderItem>() {
                @Override
                public int compare(OrderItem o1, OrderItem o2) {
                    return o1.goodsName.compareTo(o2.goodsName);
                }
            });
        }
        return result;
    }

    public List<Order> process(String shopId) {
        List<Order> orders = new ArrayList<>(orderList);
        orders.sort(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return o1.datetime.compareTo(o2.datetime);
            }
        });
        return orders;
    }

    public Map<String, Double> statisticsByShop() throws IOException {
        Map<String, Double> statisticsByShopMap = new TreeMap<>();
        loadOrders(null, null, null);
        for (Order order : orderList) {
            statisticsByShopMap.putIfAbsent(order.shopId, (double) 0);
            statisticsByShopMap.put(order.shopId, statisticsByShopMap.get(order.shopId) + order.sum);
        }
        return statisticsByShopMap;
    }

    public Map<String, Double> statisticsByGoods() throws IOException {
        Map<String, Double> statisticsByGoodsMap = new TreeMap<>();
        loadOrders(null, null, null);
        for (Order order : orderList) {
            for (OrderItem orderItem : order.items) {
                statisticsByGoodsMap.putIfAbsent(orderItem.goodsName, (double) 0);
                statisticsByGoodsMap.put(orderItem.goodsName, statisticsByGoodsMap.get(orderItem.goodsName) + orderItem.count * orderItem.price);
            }
        }
        return statisticsByGoodsMap;
    }

    public Map<LocalDate, Double> statisticsByDay() throws IOException {
        Map<LocalDate, Double> statisticsByDayMap = new TreeMap<>();
        loadOrders(null, null, null);
        for (Order order : orderList) {
            LocalDate date = LocalDate.from(order.datetime);
            statisticsByDayMap.putIfAbsent(date, (double) 0);
            statisticsByDayMap.put(date, statisticsByDayMap.get(date) + order.sum);
        }
        return statisticsByDayMap;
    }

    public static void main(String[] args) throws IOException {
        String name = "D:\\Test2";
        OrderProcessor orderProcessor = new OrderProcessor(name);
        LocalDate start = LocalDate.parse("2022-05-29");
        LocalDate finish = LocalDate.parse("2022-05-30");
        int test = orderProcessor.loadOrders(start, finish, "qqq");

//        for (Order el: orderProcessor.orderList )
//            for (OrderItem orderItem: el.items)
//                System.out.println(orderItem.toString());
//        List<Order> tstList = orderProcessor.process("qqq");
//        for (Order el : tstList)
//            for (OrderItem orderItem : el.items)
//                System.out.println(orderItem.toString());
        System.out.println(orderProcessor.statisticsByDay());
    }
}
