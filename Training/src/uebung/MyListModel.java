package uebung;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ListModel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="Entrys")
public class MyListModel extends AbstractListModel<Entry> implements ListModel<Entry> {

	File f = new File("target/todo.xml");
	@XmlElementWrapper(name = "liste")
	@XmlElement(name = "Einträge")
	public List<Entry> delegate = new ArrayList<>();

	public boolean addElement(final Entry val) {
		final int index = delegate.size();
		final boolean add = delegate.add(val);
		fireIntervalAdded(this, index, index);
		return add;
	}

	@Override
	public Entry getElementAt(final int idx) {
		return delegate.get(idx);
	}

	@Override
	public int getSize() {
		return delegate.size();
	}

	public boolean isEmpty() {
		return delegate.isEmpty();
	}

	public void removeAllElements() {
		final int index = delegate.size() - 1;
		delegate.clear();
		if (index <= 0) {
			fireContentsChanged(this, 0, index);
		}
	}

	public Entry removeElementAt(final int idx) {

		final Entry rv = delegate.get(idx);
		delegate.remove(idx);
		fireIntervalRemoved(this, idx, idx);
		return rv;
	}


	public void save() {
		try {
		final JAXBContext context = JAXBContext.newInstance(MyListModel.class);
		final Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			m.marshal(this, f);
		}
		catch(final JAXBException e) {

		}
	}

    public MyListModel load() {
        try {
           if (f.exists()) {

              final JAXBContext context = JAXBContext.newInstance(MyListModel.class);
              return (MyListModel) context.createUnmarshaller().unmarshal(f);
           }
        } catch (final JAXBException e) {
           // ignore: use empty list
        }
        return new MyListModel();
     }
//	public void load() {
//		final File f = new File("entrys.xml");
//		try {
//			final XMLDecoder in = new XMLDecoder(new BufferedInputStream(new FileInputStream(f)));
//			T e;
//			while((e = (T) in.readObject())!=null){
//				delegate.add(e);
//			}
//			in.close();
//		} catch (final IOException e) {
//			e.printStackTrace();
//
//		}
//
//	}
//
//	public void save() {
//		final File f = new File("entrys.xml");
//		try {
//			final XMLEncoder out = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(f)));
//			Entry e;
//			for (int i = 0; i< delegate.size();i++){
//				e = (Entry)delegate.get(i);
//				out.writeObject(e);
//			}
//			//out.writeObject(delegate);
//			out.close();
//		} catch (final IOException e) {
//			e.printStackTrace();
//		}
//	}

}
