import java.util.HashMap;
import java.util.Map.Entry;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class TagCounter extends DefaultHandler{
    private HashMap<String,Integer> count;

    public TagCounter() {
        count = new HashMap<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // TODO Auto-generated method stub
        //super.startElement(uri, localName, qName, attributes);
        if(count.keySet().contains(qName)){
            count.put(qName,count.get(qName)+1);
        } else {
            count.put(qName, 1);
        }

    }

    public void printResults() {
        for (Entry entity : count.entrySet()) {
            System.out.println(entity.getKey() + ": " + entity.getValue());
        }
    }

    @Override
    public void endDocument() throws SAXException {
        // TODO Auto-generated method stub
        // super.endDocument();
        printResults();
    }
}
