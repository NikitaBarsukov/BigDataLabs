package model;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@Setter
@Getter
@ToString
public class Catalog {

    URL resource = Catalog.class.getResource("abc");

    public Catalog() { }

    private Catalog(List<Cd> cds) { this.catalog = cds; }

    @JacksonXmlProperty(localName = "cd")
    @JacksonXmlElementWrapper(useWrapping = false)
    List<Cd> catalog;

    public Catalog fromXml(String path) throws IOException {
        File file = new File(path).getAbsoluteFile();
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.readValue(file, Catalog.class);
    }

    public void toXml(List<Cd> cds, String filename) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writeValue(new File("src/main/resources/" + filename), new Catalog(cds));
    }
}