package com.tomtom.hackathon.smartcity.services;

import com.tomtom.hackathon.smartcity.models.SchedulingCreationRequest;
import com.tomtom.hackathon.smartcity.repositories.CreateScheduleRepository;
import com.tomtom.hackathon.smartcity.repositories.CreateTimeSlotRepository;
import com.tomtom.hackathon.smartcity.views.SchedulingRequestView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CreateScheduleService {

    @Autowired
    private CreateScheduleRepository createScheduleRepository;

    @Autowired
    private CreateScheduleService createScheduleService;

    @Autowired
    private CreateTimeSlotRepository createTimeSlotRepository;

    private static Map<String,String> mapOfTimeWindow = new HashMap<>();

    @Value("${country.code}")
    private String countryCode;

    @Value("${tomtom.api.key}")
    private String apiKey;

    @Value("${tomtom.url.latAndLong}")
    private String TOMTOM_REST_URL;

    public SchedulingRequestView createSchedule(SchedulingCreationRequest schedulingCreationRequest){

        try {
            String urlResult = getUrlResult(
                              this.getValidUrl(TOMTOM_REST_URL,schedulingCreationRequest));
            String latAndLong = getLatAndLong(urlResult);
            schedulingCreationRequest.setLatAndLong(latAndLong);

            return new SchedulingRequestView(createScheduleRepository.save(schedulingCreationRequest));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new SchedulingRequestView(null);
    }

    public List<SchedulingRequestView> getSchedulingData(){
        ArrayList<SchedulingRequestView> listOfScheduleData = new ArrayList<>();
        createScheduleRepository.
                findAll().
                forEach(e ->listOfScheduleData.add(new SchedulingRequestView(e)));

        this.getTimeWindow();

        for (SchedulingRequestView viewData:listOfScheduleData) {
            viewData.setTimeWindow(mapOfTimeWindow.get(viewData.getTokenNumber()));
        }
        return listOfScheduleData;
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
     * @param schedulingCreationRequest
     * @return
     * getValidUrl method will return the url after performing some replace operation like replacing space with %20.
     */

    public String getValidUrl(String url,SchedulingCreationRequest schedulingCreationRequest){

        url = url.replaceAll("address",schedulingCreationRequest.getAddress())
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

    public static String getUrlResult(String restUrl) throws IOException {

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

}
