import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.helpers.DefaultHandler;


public class SaxTask {
    public static void main(String[] args) {
        if (args[0].equals("-i") && args.length == 2) {
            String filename = args[1];

            DefaultHandler h = new BusStopFilter();
            SAXParserFactory factory = SAXParserFactory.newInstance();
            try {
                SAXParser p = factory.newSAXParser();
                p.parse(new File(filename), h);

            } catch (Exception e) {e.printStackTrace();}
        } else if (args.length == 4) {
            String filename = args[1];

            DefaultHandler h = new BusStopFilter(Double.parseDouble(args[2]), Double.parseDouble(args[3]));
            SAXParserFactory factory = SAXParserFactory.newInstance();
            try {
                SAXParser p = factory.newSAXParser();
                p.parse(new File(filename), h);

            } catch (Exception e) {e.printStackTrace();}
        }
    }
}
