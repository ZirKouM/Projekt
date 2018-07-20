/*
 * $Id: ToDoListEntryModelIOXMLTest.java 1728 2017-06-25 08:44:02Z michael $
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



/**
 * Test for ToDoListEntryModelIOXML with JUnit 4.
 *
 * The two inner classes {@link Entry} and {@link MyListModel} must be set to
 * public for the test.
 *
 * @author <a href="mailto:mgn@bsinfo.eu">Michael Niedermair</a>
 * @version $Revision: 1728 $
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ToDoListEntryModelIOXMLTest {

   private static final File FILE = new File("target/todo.xml");

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

      final MyListModel lm = new MyListModel().load();
      assertNotNull(lm);
      assertEquals(0, lm.getSize());
   }

   /**
    * add entries
    */
   @Test
   public void t_02_add_entry() {

      final MyListModel lm = new MyListModel().load();
      assertNotNull(lm);

      final Entry en = new Entry();
      en.besch = "Bier kaufen";
      en.anz = 10;
      lm.addElement(en);
      assertEquals(1, lm.getSize());

      final Entry en2 = new Entry();
      en2.besch = "Eier kaufen";
      en2.anz = 20;
      lm.addElement(en2);
      assertEquals(2, lm.getSize());

      final Entry en3 = lm.getElementAt(0);
      assertNotNull(en3);
      assertEquals("Bier kaufen", en3.besch);
      assertEquals(10, en3.anz);

      final Entry en4 = lm.getElementAt(1);
      assertNotNull(en4);
      assertEquals("Eier kaufen", en4.besch);
      assertEquals(20, en4.anz);

   }

   /**
    * save to xml
    */
   @Test
   public void t_03_save() {

      final MyListModel lm = new MyListModel().load();
      assertNotNull(lm);

      final Entry en = new Entry();
      en.besch = "Bier kaufen";
      en.anz = 10;
      lm.addElement(en);
      assertEquals(1, lm.getSize());

      final Entry en2 = new Entry();
      en2.besch = "Eier kaufen";
      en2.anz = 20;
      lm.addElement(en2);
      assertEquals(2, lm.getSize());

      final Entry en3 = lm.getElementAt(0);
      assertNotNull(en3);
      assertEquals("Bier kaufen", en3.besch);
      assertEquals(10, en3.anz);

      final Entry en4 = lm.getElementAt(1);
      assertNotNull(en4);
      assertEquals("Eier kaufen", en4.besch);
      assertEquals(20, en4.anz);

      lm.save();
      assertTrue(FILE.exists());
   }

   /**
    * load from xml
    */
   @Test
   public void t_04_load() {

      final MyListModel lm = new MyListModel().load();
      assertNotNull(lm);

      assertEquals(2, lm.getSize());

      final Entry en3 = lm.getElementAt(0);
      assertNotNull(en3);
      assertEquals("Bier kaufen", en3.besch);
      assertEquals(10, en3.anz);

      final Entry en4 = lm.getElementAt(1);
      assertNotNull(en4);
      assertEquals("Eier kaufen", en4.besch);
      assertEquals(20, en4.anz);
   }

   /**
    * test the file with JDOM.
    *
    * Requires the following libraries: jdom-2.0.6-contrib.jar jdom-2.0.6.jar
    * xercesImpl.jar jaxen-1.1.6.jar xml-apis.jar
    *
    */
   @Test
   public void t_05_xml_jdom() {

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
         assertEquals(2, liste.size());

         final Element e1 = liste.get(0);
         assertEquals("Bier kaufen", e1.getAttributeValue("besch"));
         assertEquals("10", e1.getAttributeValue("anz"));

         final Element e2 = liste.get(1);
         assertEquals("Eier kaufen", e2.getAttributeValue("besch"));
         assertEquals("20", e2.getAttributeValue("anz"));

      } catch (final JDOMException e) {
         assertTrue(false);
         e.printStackTrace();
      } catch (final IOException e) {
         assertTrue(false);
         e.printStackTrace();
      }
   }
}
