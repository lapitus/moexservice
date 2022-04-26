package com.lapitus.moexservice.parser;

import com.lapitus.moexservice.dto.BondsDto;
import com.lapitus.moexservice.exception.BondParsingException;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class MoexBondXmlParser implements Parser{
    @Override
    public List<BondsDto> parse(String ratesAsString) {
        var bonds = new ArrayList<BondsDto>();

        var dbf = DocumentBuilderFactory.newInstance();
        dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        dbf.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            var db = dbf.newDocumentBuilder();

            try (var reader = new StringReader(ratesAsString)) {
                Document doc = db.parse(new InputSource(reader));
                doc.getDocumentElement().normalize();

                NodeList list = doc.getElementsByTagName("row");

                for (var rowIdx = 0; rowIdx < list.getLength(); rowIdx++) {
                    var node = list.item(rowIdx);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        var element = (Element) node;
                        String ticker =  element.getAttribute("SECID");
                        String price = element.getAttribute("PREVADMITTEDQUOTE");
                        String name = element.getAttribute("SHORTNAME");
                        if(!ticker.isEmpty() && !price.isEmpty() && !name.isEmpty()) {
                            bonds.add(new BondsDto(ticker, name, Double.parseDouble(price)));
                        }
                    }
                }
            }
        } catch (Exception ex) {
            log.error("xml parsing error, xml:{}", ratesAsString, ex);
            throw new BondParsingException(ex);
        }
        return bonds;
    }
}
