# Overview  <br />
*************insert summary/description here**********


Please use the link below to see a general design flowchart for this program:
   https://www.lucidchart.com/documents/view/a110c72c-bc0b-496c-9ca9-3e61ece059b3/0_0 
   

# Prerequisites <br />
The following items are required to effectively run this program: <br />
  *	Java SE <br />
  *	Eclipse IDE <br />
  *	JSON Jar File* <br />

*The JSON Jar File is only required to run the WeatherAPI Class. To avoid long run-times, this method is not explicitly run from the main Runner class but can be run using the main method within. <br />

To append the JSON Jar File to the WeatherAPI Class use the following steps: <br />
  *	Project (right click) <br />
  *	Build Bath <br />
  *	Configure Build Path <br />
  *	Libraries <br />
  *	Add External Libraries <br />
  *	Select downloaded Jar File <br />


# Program Components <br />
This program involves three main components:<br />
  * Data Extraction & Population<br />
    * Data for this program was taken from Zillow Housing Data Archive, the NOAA Global Historical Climatology Network API, and the most recent census data provided by the State of California’s Department of Finance.<br />
    * Key Classes <br />
        * WeatherAPI – This class utilizes the NOAA API for weather data to extract and write weather data by California Zip Code into distinct CSV files. Weather data of concern includes Average Daily Maximum Temperature, Average Daily Minimum Temperature, Average Daily Precipitation (rainfall), and Average Daily Snowfall.<br />
        * DataReader – This abstract class provides two concrete methods and one abstract method utilized by other *reader sub-classes to extract from each of the data types and merge them into a single HashMap. The final HashMap stores DataBook objects (object that holds all data fields of concern) by Zip Code.<br />
        * CensusReader/HomeReader/WeatherReader – The sub-classes to the DataReader class that collect and clean data for each respective category.<br />
        * DataCompiler – This class compiles all data using the *Reader classes for cleaner insertion into the main method.
  *	Graphical User Interface<br />
      *	Key Class<br />
         * HousingGUI – This class houses the main Graphical User Interface that users interact with. After filling in each of the data fields, the answer is stored and run through the HomeMatchScorer class to score the user’s answers against the compiled data for each location (zip code).<br />
  *	Scoring<br />
      *	Key Class<br />
          *	HomeMatchScorer – This class takes the compiled data and answers provided by the user in the HousingGUI class to effectively determine which zip codes provide the most comparable characteristics to those provided by the user. The output of this class is then formatted in a final GUI Frame that exits the program upon close.<br />


# Running <br />
To run this program, please download the entirety of this GitHub repository. Then, within the HomeFinderRunner class, run the program using your Eclipse IDE.


# Data Sources <br />
Weather Data – https://www.ncdc.noaa.gov/cdo-web/ <br />
Census/Demographic Data – http://www.dof.ca.gov/Reports/Demographic_Reports/Census_2010/#SF1 <br />
Home Data - https://www.zillow.com/research/data/ <br />
Icon - Icon made by Freepik from www.flaticon.com <br />
       https://www.flaticon.com/free-icon/home_169482?term=house%20gps&page=1&position=91 <br />
