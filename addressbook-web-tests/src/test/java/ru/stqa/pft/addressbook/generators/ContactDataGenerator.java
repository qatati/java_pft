package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contacts count")
  private int count;
  @Parameter(names = "-d", description = "File format")
  private String format;
  @Parameter(names = "-f", description = "Data format")
  private String file;


  public static void main(String[] args) throws IOException {
    ContactDataGenerator contactDataGenerator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(contactDataGenerator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    contactDataGenerator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if ("json".equals(format)) {
      saveAsJson(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format!");
    }
  }

  private void saveAsJson(List<ContactData> groups, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation()
        .registerTypeAdapter(File.class, new MyFileSerializer()).create();
    String json = gson.toJson(groups);
    try (Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    File photo = new File("src/test/resources/michael.jpeg");
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().setFirstname(String.format("Мэри %s", i))
          .setLastname(String.format("Поппинс %s", i))
          .setMobilePhone(String.format("8-900-660-60-6%s", i))
          .setEmail(String.format("qa.tatiana.qa%s@gmail.com", i))
//          .setGroup("test 1")
          .setPhoto(photo)
          .setAddress(String.format("address %s",i)).setHomePhone(String.format("5566%s", i)).setWorkPhone("")
          .setEmail2("qa.tatiana.qa2@gmail.com").setEmail3("qa.tatiana.qa3@gmail.com"));
    }
    return contacts;
  }

  private class MyFileSerializer implements JsonSerializer<File> {
      @Override
      public JsonElement serialize(File src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        obj.add("path", new JsonPrimitive(src.getPath()));
        return obj;
      }
  }
}
