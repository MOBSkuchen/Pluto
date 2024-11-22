package de.jdevr.pluto;

import com.google.gson.*;
import java.io.*;

public class DataStorageUtil {
    File thisFile;
    public DataStorageUtil(String fileName, Pluto plutoInstance) throws IOException {
        thisFile = new File(plutoInstance.getDataFolder().getAbsolutePath() + "/" + fileName);
        if (!thisFile.getParentFile().exists()) thisFile.getParentFile().mkdir();
        if (!thisFile.exists()) thisFile.createNewFile();
    }

    public FileWriter NewWriter() throws IOException {
        return new FileWriter(thisFile, false);
    }

    public FileReader NewReader() throws IOException {
        return new FileReader(thisFile);
    }

    public JsonObject getAsObject() throws IOException {
        return JsonParser.parseReader(NewReader()).getAsJsonObject();
    }

    public JsonArray getAsArray() throws IOException {
        return JsonParser.parseReader(NewReader()).getAsJsonArray();
    }

    public boolean hasKey(String key) throws IOException {
        return getAsObject().has(key);
    }

    public String getKeyAsString(String key) throws IOException {
        return getAsObject().get(key).getAsString();
    }

    public JsonElement getKeyAsRaw(String key) throws IOException {
        return getAsObject().get(key);
    }

    public void add(String key, JsonObject jsE) throws IOException {
        JsonObject jsonObject = getAsObject();
        jsonObject.add(key, jsE);
        var writer = NewWriter();
        writer.write(Pluto.gson.toJson(jsonObject));
        writer.flush();
    }
}
