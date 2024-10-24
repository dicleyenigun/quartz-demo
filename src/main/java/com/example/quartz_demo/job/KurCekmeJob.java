package com.example.quartz_demo.job;

import com.example.quartz_demo.model.Kur;
import com.example.quartz_demo.repository.KurRepository;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.time.LocalDate;

@Component
public class KurCekmeJob implements Job {

    @Autowired
    private KurRepository kurRepository;

    @Override
    public void execute(JobExecutionContext context) {
        try {
            String url = "https://www.tcmb.gov.tr/kurlar/today.xml"; // TCMB'nin günlük kur verileri

            // XML verisini çekmek
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new URL(url).openStream());

            // XML'den kurları parse etmek ve veritabanına kaydetmek
            doc.getDocumentElement().normalize();

            // Tüm <Currency> elemanlarını almak
            NodeList currencyList = doc.getElementsByTagName("Currency");

            for (int i = 0; i < currencyList.getLength(); i++) {
                Node currencyNode = currencyList.item(i);

                if (currencyNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element currencyElement = (Element) currencyNode;

                    // Döviz kodunu ("USD") ve adını ("ABD DOLARI") almak
                    String currencyCode = currencyElement.getAttribute("Kod");
                    String currencyName = currencyElement.getElementsByTagName("Isim").item(0).getTextContent();

                    // Kurlar: Döviz Alış, Döviz Satış, Efektif Alış, Efektif Satış
                    String forexBuyingStr = currencyElement.getElementsByTagName("ForexBuying").item(0).getTextContent();
                    String forexSellingStr = currencyElement.getElementsByTagName("ForexSelling").item(0).getTextContent();
                    String banknoteBuyingStr = currencyElement.getElementsByTagName("BanknoteBuying").item(0).getTextContent();
                    String banknoteSellingStr = currencyElement.getElementsByTagName("BanknoteSelling").item(0).getTextContent();

                    // Boş olmayan ve geçerli kurlar için işlemleri yap
                    if (!forexBuyingStr.isEmpty() && !forexSellingStr.isEmpty()) {
                        try {
                            // String değerlerini double'a çevir
                            double forexBuying = Double.parseDouble(forexBuyingStr);
                            double forexSelling = Double.parseDouble(forexSellingStr);

                            // Efektif alış ve satış değerleri boş değilse parse et
                            double banknoteBuying = banknoteBuyingStr.isEmpty() ? 0.0 : Double.parseDouble(banknoteBuyingStr);
                            double banknoteSelling = banknoteSellingStr.isEmpty() ? 0.0 : Double.parseDouble(banknoteSellingStr);

                            // Kur objesi oluştur ve bilgileri ayarla
                            Kur kur = new Kur();
                            kur.setDovizKodu(currencyCode);       // Örneğin: "USD"
                            kur.setDovizAdi(currencyName);        // Örneğin: "ABD DOLARI"
                            kur.setDovizAlis(forexBuying);        // ForexBuying
                            kur.setDovizSatis(forexSelling);      // ForexSelling
                            kur.setEfektifAlis(banknoteBuying);   // BanknoteBuying
                            kur.setEfektifSatis(banknoteSelling); // BanknoteSelling
                            kur.setTarih(LocalDate.now());        // Günlük tarih

                            // Kur verisini veritabanına kaydet
                            kurRepository.save(kur);

                          //  System.out.println("Currency: " + currencyName + ", BanknoteBuying: " + banknoteBuying + ", BanknoteSelling: " + banknoteSelling);



                        } catch (NumberFormatException e) {
                            // Eğer döviz kurları sayı olarak parse edilemiyorsa hata yakala
                            System.err.println("Error parsing currency rates for: " + currencyCode);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

