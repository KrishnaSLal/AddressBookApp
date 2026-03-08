package com.addressbookapp.service;

import com.addressbookapp.model.ContactPerson;
import java.util.*;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import com.addressbookapp.repository.ContactPersonRepository;

@Service
public class AddressBookService {
	
	private final ContactPersonRepository repository;
	
    public AddressBookService(ContactPersonRepository repository) {
        this.repository = repository;
    }

    public AddressBookService() {
	    this.repository = null;
	}
    
    public ContactPerson saveContact(ContactPerson person) {

        if (repository == null) {
            return person; // used for unit tests
        }

        return repository.save(person);
    }

    public List<ContactPerson> getAllContacts() {
        return repository.findAll();
    }

    public void deleteContact(Long id) {
        repository.deleteById(id);
    }

	
	//UC6
	
	private static final String JSON_STREAM_FILE = "addressbook-stream.json";
	public void writeToJsonUsingStream() throws Exception {

	    List<ContactPerson> persons = addressBooks.values()
	            .stream()
	            .flatMap(List::stream)
	            .collect(Collectors.toList());

	    mapper.writeValue(new File(JSON_STREAM_FILE), persons);
	}
	
	public void readFromJsonUsingStream() throws Exception {

	    File file = new File(JSON_STREAM_FILE);

	    if (!file.exists()) return;

	    List<ContactPerson> persons = mapper.readValue(
	            file,
	            new TypeReference<List<ContactPerson>>() {}
	    );

	    persons.forEach(person -> addContact("Default", person));
	}
	
	//UC15
	
	public void writeToCSVUsingStream() throws Exception {

	    List<String> lines = addressBooks.values()
	            .stream()
	            .flatMap(List::stream)
	            .map(person ->
	                    person.getFirstName() + "," +
	                    person.getLastName() + "," +
	                    person.getAddress() + "," +
	                    person.getCity() + "," +
	                    person.getState() + "," +
	                    person.getZip() + "," +
	                    person.getPhoneNumber() + "," +
	                    person.getEmail()
	            )
	            .collect(Collectors.toList());

	    Files.write(Paths.get("addressbook-stream.csv"), lines);
	}
	
	public void readFromCSVUsingStream() throws Exception {

	    Files.lines(Paths.get("addressbook-stream.csv"))
	            .map(line -> line.split(","))
	            .forEach(data -> {

	                ContactPerson person = new ContactPerson(
	                        data[0],
	                        data[1],
	                        data[2],
	                        data[3],
	                        data[4],
	                        data[5],
	                        data[6],
	                        data[7]
	                );

	                addContact("Default", person);
	            });
	}
	
	//UC14
	
	private static final String CSV_FILE_PATH = "addressbook-data.csv";
	


	public void writeToCSV() throws IOException {

	    FileWriter writer = new FileWriter(CSV_FILE_PATH);

	    writer.append("FirstName,LastName,Address,City,State,Zip,Phone,Email\n");

	    for (String book : addressBooks.keySet()) {

	        for (ContactPerson person : addressBooks.get(book)) {

	            writer.append(person.getFirstName()).append(",");
	            writer.append(person.getLastName()).append(",");
	            writer.append(person.getAddress()).append(",");
	            writer.append(person.getCity()).append(",");
	            writer.append(person.getState()).append(",");
	            writer.append(person.getZip()).append(",");
	            writer.append(person.getPhoneNumber()).append(",");
	            writer.append(person.getEmail()).append("\n");
	        }
	    }

	    writer.flush();
	    writer.close();
	}


	public void readFromCSV() throws IOException {

	    BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH));

	    String line;

	    reader.readLine(); // skip header

	    while ((line = reader.readLine()) != null) {

	        String[] data = line.split(",");

	        ContactPerson person = new ContactPerson(
	                data[0], data[1], data[2], data[3],
	                data[4], data[5], data[6], data[7]
	        );

	        addContact("Default", person);
	    }

	    reader.close();
	}
	
	//UC13
	
	private static final String FILE_PATH = "addressbook-data.json";
	private ObjectMapper mapper = new ObjectMapper();
	
	public void saveToFile() throws IOException {
	    mapper.writeValue(new File(FILE_PATH), addressBooks);
	}
	
	public void loadFromFile() throws IOException {

	    File file = new File(FILE_PATH);

	    if (!file.exists()) {
	        return;
	    }

	    addressBooks = mapper.readValue(
	            file,
	            new TypeReference<Map<String, List<ContactPerson>>>() {}
	    );
	}

    private Map<String, List<ContactPerson>> addressBooks = new HashMap<>();
    private Map<String, List<ContactPerson>> cityMap = new HashMap<>();
    private Map<String, List<ContactPerson>> stateMap = new HashMap<>();
    


    // UC6 - Create new Address Book
    public void createAddressBook(String bookName) {
        addressBooks.putIfAbsent(bookName, new ArrayList<>());
    }


    // Add contact to specific Address Book
    public boolean addContact(String bookName, ContactPerson contact) {

        addressBooks.putIfAbsent(bookName, new ArrayList<>());
        List<ContactPerson> contacts = addressBooks.get(bookName);

        for (ContactPerson c : contacts) {
            if (c.getFirstName().equals(contact.getFirstName()) &&
                c.getLastName().equals(contact.getLastName())) {
                return false;
            }
        }

        contacts.add(contact);

        cityMap.putIfAbsent(contact.getCity(), new ArrayList<>());
        cityMap.get(contact.getCity()).add(contact);

        stateMap.putIfAbsent(contact.getState(), new ArrayList<>());
        stateMap.get(contact.getState()).add(contact);

        return true;
    }
    
    public List<ContactPerson> getPersonsByCity(String city) {
        return cityMap.getOrDefault(city, new ArrayList<>());
    }
    public List<ContactPerson> getPersonsByState(String state) {
        return stateMap.getOrDefault(state, new ArrayList<>());
    }
    


    // View contacts from a specific Address Book
    public List<ContactPerson> getContacts(String bookName) {

        return addressBooks.getOrDefault(bookName, new ArrayList<>());
    }
    
    public List<ContactPerson> searchByCity(String city) {

        return addressBooks.values()
                .stream()
                .flatMap(List::stream)
                .filter(contact -> contact.getCity().equalsIgnoreCase(city))
                .toList();
    }
    
    public List<ContactPerson> searchByState(String state) {

        return addressBooks.values()
                .stream()
                .flatMap(List::stream)
                .filter(contact -> contact.getState().equalsIgnoreCase(state))
                .toList();
    }


    // Edit contact
    public boolean editContact(String bookName, String firstName, String lastName, ContactPerson updatedContact) {

        List<ContactPerson> contacts = addressBooks.get(bookName);

        if (contacts == null) return false;

        for (ContactPerson contact : contacts) {

            if (contact.getFirstName().equals(firstName) &&
                contact.getLastName().equals(lastName)) {

                contact.setAddress(updatedContact.getAddress());
                contact.setCity(updatedContact.getCity());
                contact.setState(updatedContact.getState());
                contact.setZip(updatedContact.getZip());
                contact.setPhoneNumber(updatedContact.getPhoneNumber());
                contact.setEmail(updatedContact.getEmail());

                return true;
            }
        }

        return false;
    }


    // Delete contact
    public boolean deleteContact(String bookName, String firstName, String lastName) {

        List<ContactPerson> contacts = addressBooks.get(bookName);

        if (contacts == null) return false;

        return contacts.removeIf(contact ->
                contact.getFirstName().equals(firstName) &&
                contact.getLastName().equals(lastName));
    }
    
   //UC10
    
    public long getCountByCity(String city) {
        return cityMap.getOrDefault(city, new ArrayList<>()).size();
    }
    
    public long getCountByState(String state) {
        return stateMap.getOrDefault(state, new ArrayList<>()).size();
    }
    
    //UC11


    public List<ContactPerson> sortByName(String bookName) {

        List<ContactPerson> contacts = addressBooks.get(bookName);

        if (contacts == null) {
            return new ArrayList<>();
        }

        return contacts.stream()
                .sorted(Comparator.comparing(ContactPerson::getFirstName)
                .thenComparing(ContactPerson::getLastName))
                .collect(Collectors.toList());
    }
    
    //UC12
    
    public List<ContactPerson> sortByCity(String bookName) {

        List<ContactPerson> contacts = addressBooks.get(bookName);

        if (contacts == null) {
            return new ArrayList<>();
        }

        return contacts.stream()
                .sorted(Comparator.comparing(ContactPerson::getCity))
                .collect(Collectors.toList());
    }
    
    public List<ContactPerson> sortByState(String bookName) {

        List<ContactPerson> contacts = addressBooks.get(bookName);

        if (contacts == null) {
            return new ArrayList<>();
        }

        return contacts.stream()
                .sorted(Comparator.comparing(ContactPerson::getState))
                .collect(Collectors.toList());
    }
    
    public List<ContactPerson> sortByZip(String bookName) {

        List<ContactPerson> contacts = addressBooks.get(bookName);

        if (contacts == null) {
            return new ArrayList<>();
        }

        return contacts.stream()
                .sorted(Comparator.comparing(ContactPerson::getZip))
                .collect(Collectors.toList());
    }

}