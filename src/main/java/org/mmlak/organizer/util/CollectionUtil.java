package org.mmlak.organizer.util;

import java.util.ArrayList;
import java.util.List;

public abstract class CollectionUtil {

    public static <T> List<T> toList(final Iterable<T> iterable){
        ArrayList<T> resultList = new ArrayList<>();
        iterable.forEach(resultList::add);
        return resultList;
    }
}
