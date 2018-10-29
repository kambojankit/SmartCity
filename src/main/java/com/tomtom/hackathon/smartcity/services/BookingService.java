package com.tomtom.hackathon.smartcity.services;

import com.tomtom.hackathon.smartcity.models.BookingRequest;
import com.tomtom.hackathon.smartcity.models.RequestStatus;
import com.tomtom.hackathon.smartcity.repositories.BookingRepository;
import com.tomtom.hackathon.smartcity.repositories.CreateTimeSlotRepository;
import com.tomtom.hackathon.smartcity.views.BookingCancelView;
import com.tomtom.hackathon.smartcity.views.BookingHistoryView;
import com.tomtom.hackathon.smartcity.views.BookingRequestView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CreateTimeSlotRepository createTimeSlotRepository;

    private static Map<String,String> mapOfTimeWindow = new HashMap<>();

    @Value("${country.code}")
    private String countryCode;

    @Value("${tomtom.api.key}")
    private String apiKey;

    @Value("${tomtom.url.latAndLong}")
    private String TOMTOM_REST_URL;

    @Value("${date.format}")
    private String dateFormat;

    /**
     * creating booking for requested user.
     * @param bookingRequest
     * @return
     */
    public BookingRequestView createBooking(BookingRequest bookingRequest){

        BookingRequestView bookingRequestView = null;
        try {
            String urlResult = this.getUrlResult(this.getValidUrl(TOMTOM_REST_URL, bookingRequest));

            bookingRequest.setLatAndLong(this.getLatAndLong(urlResult));
            bookingRequest.setRequestStatus(RequestStatus.ACTIVE.getRequestStatus());
            bookingRequest.setDateOfRequest(this.getFormattedDate(bookingRequest.getDateOfRequest()));

            if(bookingRepository.findBookingByTokenAndDate(
                    bookingRequest.getTokenNumber(),bookingRequest.getDateOfRequest(),
                    bookingRequest.getMobileNumber()).size()==0)
                bookingRequestView = new BookingRequestView(bookingRepository.save(bookingRequest));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return bookingRequestView;
    }

    /**
     * retrieving the booking history and sending to the user.
     * @return
     */

    public List<BookingHistoryView> getBookingHistory(){
        ArrayList<BookingHistoryView> listOfScheduleData = new ArrayList<>();

        bookingRepository.
                findAll().
                forEach(e ->listOfScheduleData.add(new BookingHistoryView(e)));

        this.getTimeWindow();

        for (BookingHistoryView viewData:listOfScheduleData) {
            viewData.setTimeWindow(mapOfTimeWindow.get(viewData.getTokenNumber()));
        }

        return listOfScheduleData;
    }

    public String cancelUserRequest(int userId){
        bookingRepository.updateBookingRequest(RequestStatus.CANCEL.getRequestStatus(),userId);
        return "cancellation is done for bookingId "+ userId;
    }

    public  void getTimeWindow(){

        createTimeSlotRepository
                .findAll().
                forEach(
                        e -> mapOfTimeWindow.put(e.getTokenNumber(),e.getTimeWindow())
                );
    }

    /**
     *
     * @param url -- tomtom url to search for given address.
     * @param bookingRequest
     * @return
     * getValidUrl method will return the url after performing some replace operation like replacing space with %20.
     */

    private String getValidUrl(String url,BookingRequest bookingRequest){

        url = url.replaceAll("address", bookingRequest.getAddress())
                 .replaceAll("code",this.countryCode)
                 .replaceAll("apiKey",this.apiKey)
                 .replaceAll("\\s+","%20");

        return url;
    }

    /**
     *
     * @param restUrl
     * @return
     * @throws IOException
     *
     * openConnection method will open connection with given resturl.
     */

    public static HttpsURLConnection openConnection(String restUrl) throws IOException {
        return (HttpsURLConnection)new URL(restUrl).openConnection();
    }

    /**
     *
     * @param restUrl
     * @return
     * @throws IOException
     *
     * getUrlResult method will return json data fetched from url.
     */

    private String getUrlResult(String restUrl) throws IOException {

        HttpsURLConnection urlConnection = openConnection(restUrl);
        BufferedReader bf = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        StringBuilder sb = new StringBuilder();

        String line = null;
        while ((line=bf.readLine())!=null){
            sb.append(line);
        }
        bf.close();
        return sb.toString();
    }

    /**
     *  getLatAndLong will return lattitude and longitude from json result.
     * @param jsonString
     * @return
     * @throws JSONException
     */

    public static String getLatAndLong(String jsonString) throws JSONException {

        JSONArray jsonArray = null;
        JSONObject jsonObject = getJsonObj(jsonString);

        jsonArray = (JSONArray)jsonObject.get("results");
        JSONObject latAndLong = (JSONObject) jsonArray.getJSONObject(0).get("position");
        return latAndLong.get("lat").toString()
                .concat(":"+latAndLong.get("lon").toString());
    }

    /**
     *  get json object from json string.
     * @param jsonString
     * @return
     * @throws JSONException
     */
    public static JSONObject getJsonObj(String jsonString) throws JSONException {

        return new JSONObject(jsonString);
    }

    public String getFormattedDate(String date) throws ParseException {

        DateFormat dateFormat = new SimpleDateFormat(this.dateFormat);
        return dateFormat.format(dateFormat.parse(date)).toString();
        /*return   dateFormat
                .replaceAll("-","")
                .replaceAll("/","");*/
    }

}
