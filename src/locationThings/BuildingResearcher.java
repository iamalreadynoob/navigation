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

public class BuildingResearcher implements IGettingData
{

    private List<String> places = new ArrayList<>();
    private List<Integer> corner_1x = new ArrayList<>();
    private List<Integer> corner_1y = new ArrayList<>();
    private List<Integer> corner_2x = new ArrayList<>();
    private List<Integer> corner_2y = new ArrayList<>();
    private List<Integer> corner_3x = new ArrayList<>();
    private List<Integer> corner_3y = new ArrayList<>();
    private List<Integer> corner_4x = new ArrayList<>();
    private List<Integer> corner_4y = new ArrayList<>();
    private List<Integer> entrance_x = new ArrayList<>();
    private List<Integer> entrance_y = new ArrayList<>();

    public BuildingResearcher(Path csvPath)
    {
        try
        {
            CSVParser building = CSVParser.parse(csvPath, Charset.defaultCharset(), CSVFormat.DEFAULT.withHeader("Name", "Corner-1x", "Corner-1y", "Corner-2x", "Corner-2y", "Corner-3x", "Corner-3y", "Corner-4x", "Corner-4y", "Entrance-x", "Entrance-y"));
            Stream<CSVRecord> csvRecordStream = StreamSupport.stream(building.spliterator(), false);

            List<Map<String, String>> elements = csvRecordStream.skip(1).map(csvRecord -> csvRecord.toMap()).collect(Collectors.toList());

            places = elements.stream().map(row -> row.get("Name")).collect(Collectors.toList());
            corner_1x = elements.stream().map(row -> Integer.parseInt(row.get("Corner-1x"))).collect(Collectors.toList());
            corner_1y = elements.stream().map(row -> Integer.parseInt(row.get("Corner-1y"))).collect(Collectors.toList());
            corner_2x = elements.stream().map(row -> Integer.parseInt(row.get("Corner-2x"))).collect(Collectors.toList());
            corner_2y = elements.stream().map(row -> Integer.parseInt(row.get("Corner-2y"))).collect(Collectors.toList());
            corner_3x = elements.stream().map(row -> Integer.parseInt(row.get("Corner-3x"))).collect(Collectors.toList());
            corner_3y = elements.stream().map(row -> Integer.parseInt(row.get("Corner-3y"))).collect(Collectors.toList());
            corner_4x = elements.stream().map(row -> Integer.parseInt(row.get("Corner-4x"))).collect(Collectors.toList());
            corner_4y = elements.stream().map(row -> Integer.parseInt(row.get("Corner-4y"))).collect(Collectors.toList());
            entrance_x = elements.stream().map(row -> Integer.parseInt(row.get("Entrance-x"))).collect(Collectors.toList());
            entrance_y = elements.stream().map(row -> Integer.parseInt(row.get("Entrance-y"))).collect(Collectors.toList());

        }
        catch (Exception e) { e.printStackTrace();}

    }

    @Override
    public List<Integer> getData(Lists listType)
    {
        List<Integer> returnIt = new ArrayList<>();

        switch (listType)
        {
            case CORNER1X: returnIt = corner_1x; break;
            case CORNER1Y: returnIt = corner_1y; break;
            case CORNER2X: returnIt = corner_2x; break;
            case CORNER2Y: returnIt = corner_2y; break;
            case CORNER3X: returnIt = corner_3x; break;
            case CORNER3Y: returnIt = corner_3y; break;
            case CORNER4X: returnIt = corner_4x; break;
            case CORNER4Y: returnIt = corner_4y; break;
        }

        return returnIt;
    }

    @Override
    public List<String> getName()
    {
        return places;
    }

}
