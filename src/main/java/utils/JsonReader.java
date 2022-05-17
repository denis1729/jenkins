package utils;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class JsonReader {
    private final Logger log = Logger.getLogger(getClass());
    private JSONObject jsonObjectMain;
    private JSONArray jsonObjectMainArray = new JSONArray();

    /**
     * Constructor of JsonReader.
     *
     * @param filePath - The Current path of the Json File.
     */
    public JsonReader(String filePath, boolean jsonArray) {
        parseJSON(filePath, jsonArray);
    }

    public JsonReader() {
    }

    /**
     * Parses the json file into a JSONObject.
     *
     * @param filePath - The Current path of the Json File.
     */
    private void parseJSON(String filePath, boolean isJsonArray) {
        try {
            FileReader reader = new FileReader(filePath);
            JSONParser jsonParser = new JSONParser();
            if (isJsonArray) {
                jsonObjectMainArray.addAll((JSONArray) jsonParser.parse(reader));
            } else {
                jsonObjectMain = (JSONObject) jsonParser.parse(reader);
            }

        } catch (FileNotFoundException ex) {
            log.error("FileNotFoundException when reading the configuration file ", ex);
        } catch (ParseException ex) {
            log.error("ParseException when reading the configuration file ", ex);
        } catch (IOException ex) {
            log.error("IOException when reading the configuration file ", ex);
        } catch (NullPointerException ex) {
            log.error("NullPointerException when reading the configuration file ", ex);
        }
    }

    /**
     * Gets the jsonObject value given the key from the main json object.
     *
     * @param key - the current key.
     * @return the value from jsonObject.
     */
    public JSONObject getJSONObject(String key) {
        return (JSONObject) jsonObjectMain.get(key);
    }

    /**
     * Gets the jsonArray value given the key from the main json object.
     *
     * @param key - The current key.
     * @return The value from jsonObject.
     */
    public JSONArray getJSONArray(String key) {
        return (JSONArray) jsonObjectMain.get(key);
    }

    public void addJsonArray(int index, Object object) {
        jsonObjectMainArray.add(index, object);
    }

    public Object getObjectFromJsonArray(int index) {
        return jsonObjectMainArray.get(index);
    }

    public void setObjectIntoJsonArray(int index, String key, Object value) {
        ((JSONObject) jsonObjectMainArray.get(index)).put(key, value);
    }

    /**
     * Gets the jsonObject value given the key from a jsonObject.
     *
     * @param jsonObject - the Object.
     * @param key        - the key value.
     * @return the data with the current key.
     */
    private JSONObject getJSONObject(JSONObject jsonObject, String key) {
        return (JSONObject) jsonObject.get(key);
    }

    /**
     * Gets the string value given the key from an specific jsonObject.
     *
     * @param jsonObject - the Object.
     * @param key        - the key value.
     * @return a string value.
     */
    private String getKeyValueFromJSONObject(JSONObject jsonObject, String key) {
        return (String) jsonObject.get(key);
    }

    /**
     * Gets the key value form the key.
     *
     * @param key - value.
     * @return key value.
     */
    private String getKeyValueFromKey(String key) {
        return jsonObjectMain.get(key).toString();
    }

    /**
     * Gets key value from jsonObject.
     *
     * @param key - value.
     * @return a value from the given key.
     */
    public String getKeyValue(String key) {
        return (String) jsonObjectMain.get(key);
    }

    /**
     * Gets a array json using the key then get the value using the key.
     *
     * @param objectName - the Object.
     * @param idKey      - the idKey for the value.
     * @param key        - the current key value.
     * @return a value from the json.
     */
    public String getKeyValue(String objectName, String idKey, String key) {
        JSONObject jsonObject = getJSONObjectFromArrayById(objectName, idKey);
        JSONObject jsonObjectInternal = getJSONObject(jsonObject, idKey);
        return getKeyValueFromJSONObject(jsonObjectInternal, key);
    }

    /**
     * Gets the jsonObject which key and value matches the given parameters from a jsonArray.
     *
     * @param objectName - the jsonObject for the array.
     * @param idKey      - the key for the data.
     * @param idValue    - value for  matches.
     * @return the matched jsonObject that matches the key and value.
     */
    private JSONObject getJSONObjectFromArrayById(String objectName, String idKey, String idValue) {
        JSONObject jsonObject = null;
        JSONArray jsonArray = (JSONArray) jsonObjectMain.get(objectName);
        for (Object anArr : jsonArray) {
            jsonObject = (JSONObject) anArr;
            if (jsonObject.get(idKey).equals(idValue)) {
                break;
            }
        }
        return jsonObject;
    }

    /**
     * Gets a jsonObject from the main json and then gets a value given the key.
     *
     * @param objectName - the name of the Object.
     * @param key        - to return the data.
     * @return the data.
     */
    public String getKeyValue(String objectName, String key) {
        JSONObject jsonObject = getJSONObject(objectName);
        return getKeyValueFromJSONObject(jsonObject, key);
    }

    /**
     * Gets the jsonObject which key matches the given parameters from a jsonArray.
     *
     * @param objectName - the name of the Object.
     * @param idKey      - the key for the data.
     * @return the matched value from the key.
     */
    private JSONObject getJSONObjectFromArrayById(String objectName, String idKey) {
        JSONObject jsonObject = null;
        JSONArray jsonArray = (JSONArray) jsonObjectMain.get(objectName);
        for (Object anArr : jsonArray) {
            jsonObject = (JSONObject) anArr;
            if (jsonObject.containsKey(idKey)) {
                break;
            }
        }
        return jsonObject;
    }

    /**
     * Gets the jsonObject from the jsonArray and then gets a value given the key.
     *
     * @param objectName - the name of the Object.
     * @param idKey      - the key for the data.
     * @param idValue    - value for  matches.
     * @param key        - the current key value.
     * @return the key value.
     */
    public String getKeyValue(String objectName, String idKey, String idValue, String key) {
        JSONObject jsonObject = getJSONObjectFromArrayById(objectName, idKey, idValue);
        return getKeyValueFromJSONObject(jsonObject, key);
    }

    /**
     * Gets the jsonObject from the jsonArray and then gets a jsonObject value
     * and then gets a value given the key.
     *
     * @param objectName - the name of the Object.
     * @param idKey      - the key for the data.
     * @param idValue    - value for  matches.
     * @param objectKey  - the key of the object.
     * @param key        - the current key value.
     * @return the key value.
     */
    public String getKeyValue(String objectName, String idKey, String idValue, String objectKey, String key) {
        JSONObject jsonObject = getJSONObjectFromArrayById(objectName, idKey, idValue);
        JSONObject jsonObjectInternal = getJSONObject(jsonObject, objectKey);
        return getKeyValueFromJSONObject(jsonObjectInternal, key);
    }

    /**
     * Gets a hashMap which key is the user type and value is a hashMap
     * that contains the values for Name, Password.
     *
     * @param objectName - the name of the Object.
     * @param idKey      - the key for the data.
     * @param idValue    - value for  matches.
     * @param key        - the current key value.
     * @return the hashMap.
     */
    public HashMap<String, Object> getArrayKeyValues(String objectName, String idKey, String idValue, String key) {
        JSONObject jsonObject = getJSONObjectFromArrayById(objectName, idKey, idValue);
        JSONArray arr = (JSONArray) jsonObject.get(key);

        HashMap<String, Object> arrMap = new HashMap<String, Object>();

        //Iterate all the array of jsons to add an entry (key, map) in arrValues by each json
        for (Object anArr : arr) {
            JSONObject jsonArrElement = (JSONObject) anArr;

            //Get the key of the json element
            Iterator keysItrElement = jsonArrElement.keySet().iterator();
            String arrMapKey = (String) keysItrElement.next();

            //Get the value of the json element in a map
            JSONObject jsonSubElement = (JSONObject) jsonArrElement.get(arrMapKey);
            Iterator keysItrSubElement = jsonSubElement.keySet().iterator();

            HashMap<String, String> jsonMap = new HashMap<String, String>();
            while (keysItrSubElement.hasNext()) {
                String jsonMapKey = (String) keysItrSubElement.next();
                String jsonMapValue = (String) jsonSubElement.get(jsonMapKey);
                jsonMap.put(jsonMapKey, jsonMapValue);
            }

            //put the json element key and value in a map
            arrMap.put(arrMapKey, jsonMap);
        }
        return arrMap;
    }

    /**
     * Gets a Json array according to the given key.
     *
     * @param key - The current key value.
     * @return the Json array based on the given key value.
     */
    public JSONArray getJsonArrayByKey(String key) {
        return (JSONArray) jsonObjectMain.get(key);
    }

    public JSONObject getJsonObjectMain() {
        return jsonObjectMain;
    }

    public void setJsonObjectMain(JSONObject jsonObjectMain) {
        this.jsonObjectMain = jsonObjectMain;
    }

    public JSONArray getJsonObjectMainArray() {
        return jsonObjectMainArray;
    }

    public void setJsonObjectMainArray(JSONArray jsonObjectMainArray) {
        this.jsonObjectMainArray = jsonObjectMainArray;
    }

    /**
     * Crea un archivo json
     *
     * @param pathJson directorio del archivo json
     */
    public void createJsonFile(String pathJson, boolean isJsonArray) {
        FileWriter file = null;
        try {
            file = new FileWriter(pathJson);
            if (isJsonArray) {
                file.write(jsonObjectMainArray.toJSONString());
            } else {
                file.write(jsonObjectMain.toJSONString());
            }
            file.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
