package com.lanwq.java8.inaction.chapter6;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.lanwq.java8.inaction.chapter6.Dish.menu;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/**
 * @author Lan
 * @createTime 2023-11-24  09:26
 **/
public class Grouping {
    enum CaloricLevel {DIET, NORMAL, FAT}

    ;

    public static void main(String... args) {
        System.out.println("Dishes grouped by type: " + groupDishesByType());
        System.out.println("Dish names grouped by type: " + groupDishNamesByType());
        System.out.println("Dish tags grouped by type: " + groupDishTagsByType());
        System.out.println("Caloric dishes grouped by type: " + groupCaloricDishesByType());
        System.out.println("Dishes grouped by caloric level: " + groupDishesByCaloricLevel());
        System.out.println("Dishes grouped by type and caloric level: " + groupDishedByTypeAndCaloricLevel());
        System.out.println("Count dishes in groups: " + countDishesInGroups());
        System.out.println("Most caloric dishes by type: " + mostCaloricDishesByType());
        System.out.println("Most caloric dishes by type: " + mostCaloricDishesByTypeWithoutOptionals());
        System.out.println("Sum calories by type: " + sumCaloriesByType());
        System.out.println("Caloric levels by type: " + caloricLevelsByType());
    }

    private static Map<Dish.Type, List<Dish>> groupDishesByType() {
        return menu.stream().collect(groupingBy(Dish::getType));
    }

    private static Map<Dish.Type, List<String>> groupDishNamesByType() {
        return menu.stream().collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));
    }

    private static Map<Dish.Type, Set<String>> groupDishTagsByType() {
//        return menu.stream().collect(groupingBy(Dish::getType, flatMapping(dish -> dishTags.get(dish.getName()).stream(), toSet())));
        return null;
    }

    private static Map<Dish.Type, List<Dish>> groupCaloricDishesByType() {
//        return menu.stream().filter(dish -> dish.getCalories() > 500).collect(groupingBy(Dish::getType));
//        return menu.stream().collect(groupingBy(Dish::getType, filtering(dish -> dish.getCalories() > 500, toList())));
        return null;
    }

    // 一级分组
    private static Map<CaloricLevel, List<Dish>> groupDishesByCaloricLevel() {
        return menu.stream().collect(
                groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                }));
    }

    // 多级分组
    private static Map<Dish.Type, Map<CaloricLevel, List<Dish>>> groupDishedByTypeAndCaloricLevel() {
        return menu.stream().collect(
                groupingBy(Dish::getType, // 一级分类函数
                        groupingBy((Dish dish) -> {
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        }) // 二级分类函数
                )
        );
    }

    private static Map<Dish.Type, Long> countDishesInGroups() {
        return menu.stream().collect(groupingBy(Dish::getType, counting()));
    }

    private static Map<Dish.Type, Optional<Dish>> mostCaloricDishesByType() {
        return menu.stream().collect(
                groupingBy(Dish::getType,
                        reducing((Dish d1, Dish d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)));
    }

    private static Map<Dish.Type, Dish> mostCaloricDishesByType2() {
        return menu.stream().collect(
                groupingBy(Dish::getType,
                        collectingAndThen(
                                maxBy(comparingInt(Dish::getCalories)),
                                Optional::get)));
    }

    private static Map<Dish.Type, Dish> mostCaloricDishesByTypeWithoutOptionals() {
        return menu.stream().collect(
                groupingBy(Dish::getType,
                        collectingAndThen(
                                reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2),
                                Optional::get)));
    }

    private static Map<Dish.Type, Integer> sumCaloriesByType() {
        return menu.stream().collect(groupingBy(Dish::getType,
                summingInt(Dish::getCalories)));
    }

    private static Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType() {
        return menu.stream().collect(
                groupingBy(Dish::getType, mapping(
                        dish -> {
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        },
                        toSet())));
    }
}
