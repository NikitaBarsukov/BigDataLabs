import model.Catalog;
import model.Cd;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CatalogService {

    Obfuscator obfuscator = new Obfuscator(8);

    public void obfuscateCatalog() throws IOException {

        Catalog catalog = new Catalog().fromXml("src/main/resources/catalog.xml");
        System.out.println(catalog.getCatalog().get(0));

        List<Cd> obfuscatedCds = catalog.getCatalog().stream()
                                                        .map(this::obfuscateCd)
                                                        .collect(Collectors.toList());
        Obfuscator obfuscator = new Obfuscator(8);

        catalog.toXml(obfuscatedCds, "obs_catalog.xml");
    }

    public void deObfuscateCatalog() throws IOException {

        Catalog catalog = new Catalog().fromXml("src/main/resources/obs_catalog.xml");
        System.out.println(catalog.getCatalog().get(0));

        List<Cd> obfuscatedCds = catalog.getCatalog().stream()
                .map(this::obfuscateCd)
                .collect(Collectors.toList());

        Obfuscator obfuscator = new Obfuscator(8);

        catalog.toXml(obfuscatedCds, "deobs.xml");
    }

    private Cd obfuscateCd(Cd cd) {
        cd.setArtist(obfuscator.obfuscate(cd.getArtist()));
        cd.setCompany(obfuscator.obfuscate(cd.getCompany()));
        cd.setCountry(obfuscator.obfuscate(cd.getCountry()));
        cd.setPrice(obfuscator.obfuscate(cd.getPrice()));
        cd.setTitle(obfuscator.obfuscate(cd.getTitle()));
        cd.setYear(obfuscator.obfuscate(cd.getYear()));
        return cd;
    }
}