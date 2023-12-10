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


	public void testTotalBirths () {
		FileResource fr = new FileResource();
		//FileResource fr = new FileResource("data/yob2014.csv");
		totalBirths(fr);
	}
	public static void main(String[] args) {
		BabyBirths bb = new BabyBirths();
		//System.out.println(bb.getRank(2012, "Ethan", "M"));
		bb.testTotalBirths();
	}
}
