package com.nkanaev.comics.managers;

import java.util.Comparator;

public abstract class IgnoreCaseComparator implements Comparator<Object> {

    public abstract String stringValue(Object o);

    private String preProcess(Object o){
        if (o == null)
            return "";

        String string = (o instanceof String) ? (String) o : stringValue(o);
        return string;
    }

    public int compare(Object a, Object b) {
        String strA = preProcess(a);
        String strB = preProcess(b);

        return NaturalSort.compareIgnoreCase(strA,strB);
    }

    // remove file extensions if we want to compare *file names* only
    public static abstract class FileNameComparator extends IgnoreCaseComparator{

        private String preProcess(Object o) {
            String string = "";
            if (o == null)
                return string;

            string = (o instanceof String) ? (String) o : stringValue(o);

            string = Utils.removeExtensionIfAny(string);
            return string;
        }

        public int compare(Object a, Object b) {
            String strA = preProcess(a);
            String strB = preProcess(b);

            return super.compare(strA,strB);
        }
    }
}