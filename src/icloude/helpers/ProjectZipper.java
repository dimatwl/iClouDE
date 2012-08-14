package icloude.helpers;

import icloude.frontend_backend.request_handlers.BaseRequestHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import storage.Child;
import storage.Database;
import storage.DatabaseException;
import storage.StoringType;
import storage.file.File;
import storage.file.FileReader;
import storage.project.Project;
import storage.projectitem.CompositeProjectItem;
import storage.projectitem.CompositeProjectItemType;

/**
 * @author DimaTWL Used to ZIP project
 */
public class ProjectZipper {
	public static byte[] zipProject(Project project) throws IOException,
			DatabaseException {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream(
				BaseRequestHandler.DEFAULT_BUFFER_SIZE);
		ZipOutputStream zipOut = new ZipOutputStream(outStream);

		CompositeProjectItem root = (CompositeProjectItem) Database.get(
				StoringType.COMPOSITE_PROJECT_ITEM, project.getRootKey());
		StringBuilder path = new StringBuilder('/');
		addToZip(root, path, zipOut);

		zipOut.flush();
		zipOut.close();

		return outStream.toByteArray();
	}

	private static void addToZip(CompositeProjectItem item, StringBuilder path,
			ZipOutputStream zipOut) throws IOException, DatabaseException {
		if (item.getItemType().equals(CompositeProjectItemType.PACKAGE)) {
			path.append(item.getName().replace('.', '/'));
		} else {
			path.append(item.getName());
		}
		path.append('/');
		zipOut.putNextEntry(new ZipEntry(path.toString()));
		for (Child child : item.getChildren()) {
			if (child.getType().equals(StoringType.COMPOSITE_PROJECT_ITEM)) {
				CompositeProjectItem compositeItem = (CompositeProjectItem) Database
						.get(StoringType.COMPOSITE_PROJECT_ITEM, child.getKey());
				addToZip(compositeItem, new StringBuilder(path.toString()),
						zipOut);
			} else {
				File file = (File) Database.get(StoringType.FILE,
						child.getKey());
				addToZip(file, new StringBuilder(path.toString()), zipOut);
			}
		}
	}

	private static void addToZip(File file, StringBuilder path,
			ZipOutputStream zipOut) throws IOException, DatabaseException {
		path.append(file.getName());
		zipOut.putNextEntry(new ZipEntry(path.toString()));
		FileReader reader = ((File) file).openForReading();
		char[] buf = new char[BaseRequestHandler.DEFAULT_BUFFER_SIZE];
		int charsReaded;
		while ((charsReaded = reader.read(buf)) >= 0) {
			zipOut.write(new String(buf).getBytes(), 0, charsReaded);
		}
		reader.close();
	}
}
