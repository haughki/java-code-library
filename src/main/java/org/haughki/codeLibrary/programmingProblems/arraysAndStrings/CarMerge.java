package org.haughki.codeLibrary.programmingProblems.arraysAndStrings;


import java.util.List;
import java.util.Objects;

class CarMerge {
    private static int LIST_LENGTH = 5;
    String mergeCars(List<Integer> list1, List<Integer> list2){
        String result = "";
        int index1 = 0;
        int index2 = 0;
        while (index1 < LIST_LENGTH || index2 < LIST_LENGTH) {
            if (index1 >= LIST_LENGTH) {
                result += getRest(list2, index2);
                break;
            }
            if (index2 >= LIST_LENGTH) {
                result += getRest(list1, index1);
                break;
            }
            Integer current1 = list1.get(index1);
            Integer current2 = list2.get(index2);
            if (Objects.equals(current1, current2)) {
                result += current1.toString();
                result += current2.toString();
                index1++;
                index2++;
            } else if (current1 < current2) {
                result += current1;
                index1++;
            } else {
                result += current2;
                index2++;
            }
        }
        System.out.println(result);
        return result;
    }

    private String getRest(List<Integer> list, int index) {
        String result = "";
        while (index < LIST_LENGTH) {
            result += list.get(index);
            index++;
        }
        return result;
    }
}
