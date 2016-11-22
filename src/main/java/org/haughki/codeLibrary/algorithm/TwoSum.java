package org.haughki.codeLibrary.algorithm;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

class TwoSum {
    final private List<Integer> l1;
    final private List<Integer> l2;
    final private ListIterator<Integer> leader;
    final private ListIterator<Integer> follower;

    TwoSum(List<Integer> l1, List<Integer> l2) {
        this.l1 = l1;
        this.l2 = l2;
        if (l1.size() > l2.size()) {
            leader = l1.listIterator();
            follower = l2.listIterator();
        } else {
            leader = l2.listIterator();
            follower = l1.listIterator();
        }
    }
    
    List<Integer> sumLists() {

        List<Integer> ret = new LinkedList<>();
        boolean carry = false;
        while (leader.hasNext()) {
            Integer currLeader = leader.next();
            Integer currFollower = follower.hasNext() ? follower.next() : 0;

            Integer sum = currLeader + currFollower;
            if (carry)
                sum++;

            Integer sumDigit = sum % 10;
            ret.add(sumDigit);
            carry = sum > 9;
            if (!leader.hasNext() && carry)
                ret.add(1);
        }
        return ret;
    }

    class ListAndCarry{
        LinkedList<Integer> list;
        boolean carry = false;
    }

    List<Integer> reverseSum() {
        
        
        ListAndCarry res = recurse();
        if (res.carry)
            res.list.addFirst(1);
        return res.list;
    }
    
    private ListAndCarry recurse() {
        Integer currLeader;
        Integer currFollower;
        ListAndCarry ret;
        if(leader.hasNext()) {
            currLeader = leader.next();
            currFollower = follower.hasNext() ? follower.next() : 0;
            ret = recurse();
        } else {
            ret = new ListAndCarry();
            ret.list = new LinkedList<>();
            return ret;
        }

        Integer sum = currLeader + currFollower;
        if (ret.carry)
            sum++;

        Integer sumDigit = sum % 10;
        ret.list.addFirst(sumDigit);
        ret.carry = sum > 9;
        return ret;
    }
}

