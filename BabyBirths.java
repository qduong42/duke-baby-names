/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.util.*;

public class BabyBirths {
	public void printNames () {
		FileResource fr = new FileResource();
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			if (numBorn <= 100) {
				System.out.println("Name " + rec.get(0) +
						   " Gender " + rec.get(1) +
						   " Num Born " + rec.get(2));
			}
		}
	}

	public void totalBirths (FileResource fr) {
		int totalBirths = 0;
		int totalBoys = 0;
		int totalGirls = 0;
		int totalnames = 0;
		int numberOfBoysNames = 0;
		int numberOfGirlsNames = 0;
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			totalBirths += numBorn;
			if (rec.get(1).equals("M")) {
				totalBoys += numBorn;
				totalnames++;
				numberOfBoysNames++;
			}
			else {
				totalGirls += numBorn;
				totalnames++;
				numberOfGirlsNames++;
			}
		}
		System.out.println("total births = " + totalBirths);
		System.out.println("female girls = " + totalGirls);
		System.out.println("male boys = " + totalBoys);
		System.out.println("number boy names = " + numberOfBoysNames);
		System.out.println("number girl names = " + numberOfGirlsNames);
		System.out.println("Total names = " + totalnames);
	}

    public int getRank(int year, String name, String gender) {
		String fileName = "data/testing/yob" + year + "short" + ".csv";
        FileResource fr = new FileResource(fileName);

        // ArrayList<CSVRecord> records = new ArrayList<>();
        // for (CSVRecord rec : fr.getCSVParser(false)) {
        //     records.add(rec);
        // }

        // Sort the records based on the number of births (descending order)
        //records.sort(Comparator.comparingInt(rec -> -Integer.parseInt(rec.get(2))));
		//CSVRecord prevRec = null;
        int rank = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            String currentName = rec.get(0);
            String currentGender = rec.get(1);

            if (currentGender.equals(gender)) {
			//if(prevRec == null || Integer.parseInt(rec.get(2)) < Integer.parseInt(prevRec.get(2))) 
            	rank++;
			//prevRec = rec;

                if (currentName.equals(name)) {
                    return rank;
                }
            }
        }

        return -1; // Name not found in the file
    }
	public String getName(int year, int rank, String gender){
		String fileName = "data/testing/yob" + year + "short" + ".csv";
		FileResource fr = new FileResource(fileName);
		int currentRank = 0;
		for(CSVRecord record : fr.getCSVParser(false)){
			String currentName = record.get(0);
			String currentGender = record.get(1);

			if (currentGender.equals(gender)){
				currentRank++;
				if(rank == currentRank){
					return currentName;
				}
			}
		}
		return "NO NAME";
	}
	public void testTotalBirths () {
		FileResource fr = new FileResource();
		//FileResource fr = new FileResource("data/yob2014.csv");
		totalBirths(fr);
	}
	public void whatIsNameInYear(String name, int year, int newYear, String gender){
		int rank = getRank(year, name, gender);
		String nameinNewYear = getName(newYear, rank, gender);
		System.out.println(name + " born in " + year + " would be " + nameinNewYear + " if she was born in " + newYear);
	}
	public static void main(String[] args) {
		BabyBirths bb = new BabyBirths();
		//System.out.println(bb.getName(2012, 10, "M"));
		bb.whatIsNameInYear("Isabella", 2012, 2014, "F");
		//bb.testTotalBirths();
	}
}
