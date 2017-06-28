package pl.edu.agh.jkolodziej.micro;

import com.google.common.base.Charsets;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.io.Files;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author Jakub Kołodziej
 */
public class ResultParser {
    //path to result textfile
    private static final String RESULT_FILE_PATH = "C:\\Users\\jakub\\Desktop\\WYNIKI_MGR\\";
    private static final String RESULT_FILE_NAME = "result_time5_bat5_values_j48.csv";
    private static final String ROUND_RESULT_NAME = "round_result_values_j48.csv";
    private static final String SERIES_RESULT_NAME = "series_to_chart_result_values_j48.csv";
    private static final int JOBS_PER_ROUND = 16;

    private static final int ROUND_PER_SERIES = 10;
    private static final int SERIES = 8;

    private static List<Long> timeRoundResultList = Lists.newArrayList();
    private static List<Long> batteryRoundResultList = Lists.newArrayList();

    public static void main(String[] args) throws IOException {
        int counter = 0;
        Long timeRoundResult = 0L;
        Long batteryRoundResult = 0L;
        for (String line : Files.readLines(new File(RESULT_FILE_PATH + RESULT_FILE_NAME), Charsets.UTF_8)) {
            timeRoundResult += Long.valueOf(line.split(";")[1]);
            batteryRoundResult += Long.valueOf(line.split(";")[3]);
            counter += 1;
            if (counter % JOBS_PER_ROUND == 0) {
                timeRoundResultList.add(Long.valueOf(timeRoundResult));
                batteryRoundResultList.add(Long.valueOf(batteryRoundResult));
                timeRoundResult = 0L;
                batteryRoundResult = 0L;
                counter = 0;
            }
        }
        FileWriter fw = new FileWriter(new File(RESULT_FILE_PATH + ROUND_RESULT_NAME));
        for (int i = 0; i < ROUND_PER_SERIES * SERIES; i++) {
            fw.append(timeRoundResultList.get(i) + ";" + batteryRoundResultList.get(i) + "\n");
        }
        fw.close();

        Multimap<Long, Long> timeMultimap = ArrayListMultimap.create();
        Multimap<Long, Long> batteryMultimap = ArrayListMultimap.create();

        for (long i = 0; i < SERIES; i++) {
            long j = i;
            while (j < SERIES * ROUND_PER_SERIES) {
                timeMultimap.put(i + 1, timeRoundResultList.get(Long.valueOf(j).intValue()));
                batteryMultimap.put(i + 1, batteryRoundResultList.get(Long.valueOf(j).intValue()));
                j += SERIES;
            }
        }

        StandardDeviation standardDeviation = new StandardDeviation();
        standardDeviation.setBiasCorrected(false);


        fw = new FileWriter(new File(RESULT_FILE_PATH + SERIES_RESULT_NAME));
        fw.append("TIME:\n\n");
        for (long i = 0; i < SERIES; i++) {
            fw.append(meanAritmetic(Lists.newArrayList(timeMultimap.get(i + 1))) + ";"
                    + standardDevation(Lists.newArrayList(timeMultimap.get(i + 1))) + "\n");
        }
        fw.append("\n\nBATTERY:\n\n");
        for (long i = 0; i < SERIES; i++) {
            fw.append(meanAritmetic(Lists.newArrayList(batteryMultimap.get(i + 1))) + ";"

                    + standardDevation(Lists.newArrayList(batteryMultimap.get(i + 1))) + "\n");
        }
        fw.close();
        System.out.println("AAA");
    }

    public static double meanAritmetic(List<Long> t) {
        double a = 0;
        for (double y : t) {
            a += y;
        }

        a /= t.size();

        return a;
    }

    /* standardDevation
     * ODCHYLENIE STANDARDOWE
     * pobiera tablice
     * zwraca odchylenie standardowe
     */
    public static double standardDevation(List<Long> t) {
        double sD = 0;
        int l = t.size();
        for (double y : t) {
            double r = (y - meanAritmetic(t));
            sD += (r * r);
        }
        sD /= l;
        sD = Math.sqrt(sD);

        return sD;
    }
}

