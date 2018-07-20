/*
 * $Id: NameListEntryModelIOXMLTest.java 1728 2017-06-25 08:44:02Z michael $
 */
package uebung;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import de.nm.gui.swing.NameListEntryModelIOXML.Entry;
import de.nm.gui.swing.NameListEntryModelIOXML.MyListModel;

/**
 * Test for NameListEntryModelIOXML with JUnit 4.
 *
 * The two inner classes {@link Entry} and {@link MyListModel} must be set to
 * public for the test.
 *
 * @author <a href="mailto:mgn@bsinfo.eu">Michael Niedermair</a>
 * @version $Revision: 1728 $
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NameListEntryModelIOXMLTest {

   private static final File FILE = new File("target/name.xml");

   /**
    * delete file if exists
    */
   @BeforeClass
   public static void deleteFile() {
      FILE.delete();
   }

   /**
    * create a object
    */
   @Test
   public void t_01_create_object() {

      final NameListEntryModelIOXML obj = new NameListEntryModelIOXML();
      assertNotNull(obj);
   }

   /**
    * create a entry object
    */
   @Test
   public void t_02_create_object_entry() {

      final Entry en = new Entry();
      assertNotNull(en);

      assertEquals("", en.name);
      assertEquals("", en.vorname);
      assertEquals(0, en.alter);

      en.name = "Kobold";
      en.vorname = "Pumukel";
      en.alter = 99;

      assertEquals("Kobold", en.name);
      assertEquals("Pumukel", en.vorname);
      assertEquals(99, en.alter);

      assertEquals("Kobold              Pumukel             99", en.toString());

   }

   /**
    * create a mylistmodel object
    */
   @Test
   public void t_03_create_mylistmodel() {

      final MyListModel lm = MyListModel.load();
      assertNotNull(lm);

      final Entry en = new Entry();
      assertNotNull(en);
      en.name = "Kobold";
      en.vorname = "Pumukel";
      en.alter = 99;
      lm.addElement(en);
      assertEquals(1, lm.getSize());

      final Entry en2 = new Entry();
      assertNotNull(en2);
      en2.name = "Müller";
      en2.vorname = "Peter";
      en2.alter = 17;
      lm.addElement(en2);
      assertEquals(2, lm.getSize());

      final Entry en3 = new Entry();
      assertNotNull(en3);
      en3.name = "Meier";
      en3.vorname = "Uschi";
      en3.alter = 29;
      lm.addElement(en3);
      assertEquals(3, lm.getSize());

      final Entry en4 = lm.getElementAt(0);
      assertEquals("Kobold", en4.name);
      assertEquals("Pumukel", en4.vorname);
      assertEquals(99, en4.alter);

      final Entry en5 = lm.getElementAt(1);
      assertEquals("Müller", en5.name);
      assertEquals("Peter", en5.vorname);
      assertEquals(17, en5.alter);

      final Entry en6 = lm.getElementAt(2);
      assertEquals("Meier", en6.name);
      assertEquals("Uschi", en6.vorname);
      assertEquals(29, en6.alter);

   }

   /**
    * save to xml
    */
   @Test
   public void t_04_save() {

      final MyListModel lm = MyListModel.load();
      assertNotNull(lm);

      final Entry en = new Entry();
      assertNotNull(en);
      en.name = "Kobold";
      en.vorname = "Pumukel";
      en.alter = 99;
      lm.addElement(en);
      assertEquals(1, lm.getSize());

      final Entry en2 = new Entry();
      assertNotNull(en2);
      en2.name = "Müller";
      en2.vorname = "Peter";
      en2.alter = 17;
      lm.addElement(en2);
      assertEquals(2, lm.getSize());

      final Entry en3 = new Entry();
      assertNotNull(en3);
      en3.name = "Meier";
      en3.vorname = "Uschi";
      en3.alter = 29;
      lm.addElement(en3);
      assertEquals(3, lm.getSize());

      final Entry en4 = lm.getElementAt(0);
      assertEquals("Kobold", en4.name);
      assertEquals("Pumukel", en4.vorname);
      assertEquals(99, en4.alter);

      final Entry en5 = lm.getElementAt(1);
      assertEquals("Müller", en5.name);
      assertEquals("Peter", en5.vorname);
      assertEquals(17, en5.alter);

      final Entry en6 = lm.getElementAt(2);
      assertEquals("Meier", en6.name);
      assertEquals("Uschi", en6.vorname);
      assertEquals(29, en6.alter);

      lm.save();
      assertTrue(FILE.exists());
   }

   /**
    * load from xml
    */
   @Test
   public void t_05_load() {

      final MyListModel lm = MyListModel.load();
      assertNotNull(lm);

      assertEquals(3, lm.getSize());

      final Entry en4 = lm.getElementAt(0);
      assertEquals("Kobold", en4.name);
      assertEquals("Pumukel", en4.vorname);
      assertEquals(99, en4.alter);

      final Entry en5 = lm.getElementAt(1);
      assertEquals("Müller", en5.name);
      assertEquals("Peter", en5.vorname);
      assertEquals(17, en5.alter);

      final Entry en6 = lm.getElementAt(2);
      assertEquals("Meier", en6.name);
      assertEquals("Uschi", en6.vorname);
      assertEquals(29, en6.alter);

   }

   /**
    * test the file with JDOM.
    *
    * Requires the following libraries: jdom-2.0.6-contrib.jar jdom-2.0.6.jar
    * xercesImpl.jar jaxen-1.1.6.jar xml-apis.jar
    *
    */
   @Test
   public void t_06_xml_jdom() {

      assertTrue(FILE.exists());

      final SAXBuilder saxBuilder = new SAXBuilder();
      assertNotNull(saxBuilder);

      try {
         final Document document = saxBuilder.build(FILE);
         assertNotNull(document);

         final XPathFactory factory = XPathFactory.instance();
         assertNotNull(factory);

         final XPathExpression<Element> xp = factory.compile("/root/liste/entry", Filters.element());
         assertNotNull(xp);

         final List<Element> liste = xp.evaluate(document);
         assertNotNull(liste);
         assertEquals(3, liste.size());

         final Element e1 = liste.get(0);
         assertEquals("Kobold", e1.getAttributeValue("name"));
         assertEquals("Pumukel", e1.getAttributeValue("vorname"));
         assertEquals("99", e1.getAttributeValue("alter"));

         final Element e2 = liste.get(1);
         assertEquals("Müller", e2.getAttributeValue("name"));
         assertEquals("Peter", e2.getAttributeValue("vorname"));
         assertEquals("17", e2.getAttributeValue("alter"));

         final Element e3 = liste.get(2);
         assertEquals("Meier", e3.getAttributeValue("name"));
         assertEquals("Uschi", e3.getAttributeValue("vorname"));
         assertEquals("29", e3.getAttributeValue("alter"));

      } catch (final JDOMException e) {
         assertTrue(false);
         e.printStackTrace();
      } catch (final IOException e) {
         assertTrue(false);
         e.printStackTrace();
      }
   }
}
