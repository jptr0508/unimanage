package pt.iade.unimanage.models;

import java.util.List;

public interface Statistical {
    double getAverage();
    double getMax();
    double getMin();
    HistogramSlot[] getHistogram(int nSlots);
    default double getRange(){
        return getMax() - getMin();
    }

    static double getGroupAverage(List<Statistical> group){
        double sum = 0;
        for(Statistical stat: group)
        sum += stat.getAverage();
        return sum / group.size();
    }
}
