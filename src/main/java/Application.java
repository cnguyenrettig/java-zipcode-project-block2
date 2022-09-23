
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.app.data.zipcode.Place;
import com.kenzie.app.data.zipcode.ZipCodeDTO;
import com.kenzie.app.format.AddressFormatUtil;
import com.kenzie.app.http.HttpUtil;
import java.util.ArrayList;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        try {
            //declare variables
            String BASE_URL = "https://api.zippopotam.us/us/";
            Scanner scanner = new Scanner(System.in);
            String recipientName;
            String streetAddress;
            String city;
            String state;
            String zipCode;

            //read in user input - scanner
            System.out.println("Enter recipient name: ");
            recipientName = scanner.nextLine();
            System.out.println("Enter street address: ");
            streetAddress = scanner.nextLine();
            System.out.println("Enter city: ");
            city = scanner.nextLine();
            System.out.println("Enter state: ");
            state = scanner.nextLine();

            //replace spaces in city - Los Angeles
            String tempCity = city.replaceAll(" ", "%20");

            //format URL with user city and state
            String finalURL = BASE_URL + state + "/" + tempCity;
            //System.out.println(finalURL);

            //Call GET
            String httpResponse = HttpUtil.makeGETRequest(finalURL);
            //System.out.println(httpResponse);

            //if return string contains 404, don't object map
            if (httpResponse.contains("GET request failed")) {
                System.out.println("No zip code found");
                zipCode = "";
            } else {
                //ObjectMapper to retrieve zip code
                //1. Instantiate ObjectMapper
                //2. Declare DTO
                //3. Set DTO to objectMapper.readValue()
                ObjectMapper objectMapper = new ObjectMapper();
                ZipCodeDTO zipObj;
                zipObj = objectMapper.readValue(httpResponse, ZipCodeDTO.class);

                //zipObj.getPlaces().get(0);

                //if 1 zipCode returned, set zipCode
                if(zipObj.getPlaces().size() == 1){
                    zipCode = zipObj.getPlaces().get(0).getPostCode();
                }
                else if(zipObj.getPlaces().size() > 1)  {
                    //else loop and display all zipcodes
                    for (int i = 0; i < zipObj.getPlaces().size(); i++) {
                        System.out.println(i + ") " + zipObj.getPlaces().get(i).getPostCode());
                    }

                }

                //prompt user to select zipcode
                System.out.println("Choose a zipcode");
                int choice = scanner.nextInt();
                scanner.nextLine();

                //set zipCode based on selection
                zipCode = zipObj.getPlaces().get(choice).getPostCode();
            }

            //print out final address
            System.out.println(AddressFormatUtil.formatAddress(recipientName));
            System.out.println(AddressFormatUtil.formatStreetAddress(streetAddress));
            System.out.println(AddressFormatUtil.formatAddress(AddressFormatUtil.replaceCityAbbreviation(city) + "," + state + " " + zipCode));


        }
        catch(Exception e){
            System.out.println("Unexpected exception: " + e);
        }

    }

    //URL: https://api.zippopotam.us/us/mo/saint%20louis
    public static void main_backup(String[] args)   {
        try {
            String URL = "https://api.zippopotam.us/us/mo/saint%20louis";
            String httpResponse;

            httpResponse = HttpUtil.makeGETRequest(URL);

            System.out.println(httpResponse);

            //JSON Object mapping
            //1. instantiate ObjectMapper
            //2. create DTO (in order to map data into)
            //3. readValue()

            ObjectMapper objectMapper = new ObjectMapper();
            ZipCodeDTO zipObj;
            zipObj = objectMapper.readValue(httpResponse ,  ZipCodeDTO.class );

            //print out state, city(place name), zipcode(post code)
            //System.out.println("State:" + zipObj.getState());
            //System.out.println("City:" + zipObj.getPlaces().get(0).getPlace_name());
            //System.out.println("ZipCode:" + zipObj.getPlaces().get(0).getPostCode());

            zipObj.getPlaces(); //here is my list
            System.out.println("List!" + zipObj.getPlaces());

            List<Place> placeList = zipObj.getPlaces(); //can set your list to a variable


            placeList.size();
            placeList.get(0); //<-- what type is this? Place
            placeList.get(0).getPostCode(); // what type is this? String
            zipObj.getPlaces().get(0);



            //<there's only one item, set zipCode to that>
           /*
            if(zipObj.getPlaces().size() == 1){
                System.out.println("Only one zip code:" + zipObj.getPlaces().get(0).getPostCode());
            }
            //<list size is greater than 1>
            else if(zipObj.getPlaces().size() > 1){
                for (int i = 0; i < zipObj.getPlaces().size(); i++) {
                    System.out.println("Zone " + i);
                    System.out.println("State:" + zipObj.getState());
                    System.out.println("City:" + zipObj.getPlaces().get(i).getPlace_name());
                    System.out.println("ZipCode:" + zipObj.getPlaces().get(i).getPostCode());
                }

            }
        */

            //STATIC call the formatter util from main
            String testStr = "123 Main St.";
            AddressFormatUtil.initCodeTable();
            System.out.println(AddressFormatUtil.replaceAbbreviation(testStr));

            //"The Declaration of Independence"

            // > Declaraton of Independence


        }
        catch(Exception e){
            System.out.println("Unexpected exception: " + e);

        }

    }
}

