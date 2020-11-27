import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class BusStopFilter extends DefaultHandler {
    private BusStop bs;
    private ArrayList<BusStop> busStopArrayList;
    private boolean distflag;
    private double lat;
    private double log;


    public BusStopFilter() {
        busStopArrayList = new ArrayList<>();
        distflag = false;
    }

    public BusStopFilter(double ilat, double ilog) {
        busStopArrayList = new ArrayList<>();
        lat = ilat;
        log = ilog;
        distflag = true;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equals("node")) {
            bs = new BusStop();
        } else if (qName.equals("tag")) {
            if (attributes.getValue("v").equals("bus_stop")) {
                bs.setValid(true);
            } else if (attributes.getValue("k").equals("name")) {
                bs.setName(attributes.getValue("v"));
            } else if (attributes.getValue("k").equals("old_name")) {
                bs.setOldName(attributes.getValue("v"));
            } else if (attributes.getValue("k").equals("wheelchair")) {
                bs.setWheelchair(attributes.getValue("v"));
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equals("node")) {
            busStopArrayList.add(bs);
        }
    }

    private void ListStops() {
        for (BusStop bs :
                busStopArrayList) {
            if (bs.getValid()) {
                System.out.println(bs);
            }
        }
    }

    @Override
    public void endDocument() throws SAXException {
        BSDistCompate cmp = new BSDistCompate(lat,log);
        busStopArrayList.sort(cmp);
        ListStops();
    }
}
