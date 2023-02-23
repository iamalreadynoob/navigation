package locationThings;

import dataset.IGettingData;
import dataset.Lists;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class RoadResearcher implements IGettingData
{

    List<String> roads = new ArrayList<>();
    List<Integer> xInitialize = new ArrayList<>();
    List<Integer> yInitialize = new ArrayList<>();
    List<Integer> xFinal = new ArrayList<>();
    List<Integer> yFinal = new ArrayList<>();
    List<Integer> r = new ArrayList<>();

    public RoadResearcher(Path csvPath)
    {
        try
        {
            CSVParser roadParser = CSVParser.parse(csvPath, Charset.defaultCharset(), CSVFormat.DEFAULT.withHeader("Name", "X-initialize", "Y-initialize", "X-final", "Y-final", "R"));
            Stream<CSVRecord> csvRecordStream = StreamSupport.stream(roadParser.spliterator(), false);
            List<Map<String, String>> elements = csvRecordStream.skip(1).map(csvRecord -> csvRecord.toMap()).collect(Collectors.toList());

            roads = elements.stream().map(row -> row.get("Name")).collect(Collectors.toList());
            xInitialize = elements.stream().map(row -> Integer.parseInt(row.get("X-initialize"))).collect(Collectors.toList());
            yInitialize = elements.stream().map(row -> Integer.parseInt(row.get("Y-initialize"))).collect(Collectors.toList());
            xFinal = elements.stream().map(row -> Integer.parseInt(row.get("X-final"))).collect(Collectors.toList());
            yFinal = elements.stream().map(row -> Integer.parseInt(row.get("Y-final"))).collect(Collectors.toList());
            r = elements.stream().map(row -> Integer.parseInt(row.get("R"))).collect(Collectors.toList());
        }
        catch (Exception e){e.printStackTrace();}
    }

    @Override
    public List<Integer> getData(Lists listType)
    {
        List<Integer> returnIt = new ArrayList<>();

        switch (listType)
        {
            case XINITIALIZE: returnIt = xInitialize; break;
            case YINITIALIZE: returnIt = yInitialize; break;
            case XFINAL: returnIt = xFinal; break;
            case YFINAL: returnIt = yFinal; break;
            case R: returnIt = r; break;
        }

        return returnIt;
    }

    @Override
    public List<String> getName()
    {
        return roads;
    }

}
